package hu.bearmaster.itm.common.test.dao;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.impl.GenericDaoImpl;
import hu.bearmaster.itm.common.dao.model.UserDO;
import hu.bearmaster.itm.common.test.utils.TestPropertyLoader;
import static hu.bearmaster.itm.common.test.utils.TestUserConstants.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:test-spring-context.xml")
public class TestUserDao extends AbstractTransactionalTestNGSpringContextTests {
	
	public static final String USER_DAO_PROPS = "/testUser.properties";
	
	public static final String QUERY_USER_ID_BY_EMAIL = "select id from users where email = ?";
	public static final String QUERY_USER_NAME_BY_EMAIL = "select name from users where email = ?";
	public static final String QUERY_USER_COUNT = "select count(id) from users";
	
	@Resource
	private UserDao userDao;
	
	@PersistenceContext
	private EntityManager em;
	
	private TestPropertyLoader p;
	
	@BeforeClass
	public void setUp() {
		try {
			p = new TestPropertyLoader(USER_DAO_PROPS);
		} catch (IOException e) {
			//TODO what to do in this case?
			logger.error("Resource not found: " + USER_DAO_PROPS, e);
		}
	}
	
	@Test
	public void testDaoProperties() {
		
		assertNotNull(userDao, "UserDao object shouldn't be null!");
		
		assertTrue(userDao instanceof GenericDaoImpl<?, ?>, "UserDao object doesn't extend GenericDaoImpl!");
		
		assertNotNull(((GenericDaoImpl<?,?>)userDao).getEntityManager(), "Entity manager shouldn't be null!");
	}
	
	@Test
	public void queryExistingUser() {
		UserDO userDo = userDao.find(p.getLong(EXISTING_USER_ID));
		
		assertNotNull(userDo, "Cannot load test user with id " + p.get(EXISTING_USER_ID));
		
		assertEquals(userDo.getName(), p.get(EXISTING_USER_NAME), "Test user name is expected to be " + p.get(EXISTING_USER_NAME));
		assertEquals(userDo.getEmail(), p.get(EXISTING_USER_EMAIL), "Test user email is expected to be " + p.get(EXISTING_USER_EMAIL));
		assertEquals(userDo.isAdmin(), p.getBoolean(EXISTING_USER_ADMIN));
	}
	
	@Test
	public void createNewUserSuccessfully() {
		UserDO newUserDo = createNewUser();
		
		logger.info("New test user created: " + newUserDo);
		
		newUserDo = userDao.create(newUserDo);		
		assertNotNull(newUserDo.getId(), "Persisted user should have an assigned id after save!");
		
		long id = simpleJdbcTemplate.queryForLong(QUERY_USER_ID_BY_EMAIL, p.get(NEW_USER_EMAIL));		
		assertEquals(newUserDo.getId(), (Long)id, "Persisted user not found in the database!");
	}
	
	@Test(expectedExceptions = PersistenceException.class)
	public void attemptToCreateUserWithExistingEmail() {
		UserDO existingUserDo = createNewUser();
		existingUserDo.setEmail(p.get(EXISTING_USER_EMAIL));
		
		userDao.create(existingUserDo); //should fail with PersistenceException
	}
	
	@Test(expectedExceptions = PersistenceException.class)
	public void attemptToCreateUserWithExistingName() {
		UserDO existingUserDo = createNewUser();
		existingUserDo.setName(p.get(EXISTING_USER_NAME));
		
		userDao.create(existingUserDo); //should fail with PersistenceException
	}
	
	@Test
	public void getAllUsers() {
		List<UserDO> userList = userDao.findAll();
		
		for (UserDO userDo : userList) {
			long id = simpleJdbcTemplate.queryForLong(QUERY_USER_ID_BY_EMAIL, userDo.getEmail());		
			assertEquals(userDo.getId(), (Long)id, "Loaded user not found in the database!");
		}
		
		long count = simpleJdbcTemplate.queryForLong(QUERY_USER_COUNT);		
		assertEquals(userList.size(), count, "Returned user count and database count doesn't match!");
	}
	
	@Test
	public void countAllUsers() {
		
		long count = userDao.countAll();
		
		long sqlCount = simpleJdbcTemplate.queryForLong(QUERY_USER_COUNT);		
		assertEquals(count, sqlCount, "Returned user count and database count doesn't match!");
		
	}
	
	@Test
	public void updateUser() {
		UserDO userDo = userDao.find(p.getLong(EXISTING_USER_ID));
		
		userDo.setName(p.get(UPDATED_USER_NAME));
		
		userDao.update(userDo);
		
		em.flush();
		
		String updatedName = simpleJdbcTemplate.queryForObject(QUERY_USER_NAME_BY_EMAIL, String.class, p.get(EXISTING_USER_EMAIL));
		assertEquals(updatedName, p.get(UPDATED_USER_NAME));
		
	}
	
	@Test(expectedExceptions = PersistenceException.class)
	public void updateUserWithExistingName() {
		UserDO userDo = userDao.find(p.getLong(EXISTING_USER_ID));
		
		userDo.setName(p.get(EXISTING_USER2_NAME));
		
		userDao.update(userDo);
		
		em.flush(); //should fail with persistence exception
	}
	
	@Test(expectedExceptions = PersistenceException.class)
	public void updateUserWithExistingEmail() {
		UserDO userDo = userDao.find(p.getLong(EXISTING_USER_ID));
		
		userDo.setEmail(p.get(EXISTING_USER2_EMAIL));
		
		userDao.update(userDo);
		
		em.flush(); //should fail with persistence exception
	}
	
	@Test
	public void deleteUser() {
		long countBefore = simpleJdbcTemplate.queryForLong(QUERY_USER_COUNT);
		
		userDao.delete(p.getLong(EXISTING_USER_ID));
		em.flush();
		
		long countAfter = simpleJdbcTemplate.queryForLong(QUERY_USER_COUNT);
		
		assertEquals(countAfter + 1, countBefore);
	}
	
	@Test(expectedExceptions = PersistenceException.class)
	public void deleteNonExistingUser() {
		userDao.delete(p.getLong(NON_EXISTING_USER_ID));
		em.flush(); //should fail with persistence exception
	}
	
	@Test
	public void findExistingUserByName() {
		UserDO user = userDao.findByUsername(p.get(EXISTING_USER_NAME));
		
		assertEquals(user.getEmail(), p.get(EXISTING_USER_EMAIL));
	}
	
	@Test(expectedExceptions = NoResultException.class)
	public void findNonExistingUserByName() {
		UserDO user = userDao.findByUsername(p.get(NON_EXISTING_USER_NAME));
	}
	
	public UserDO createNewUser() {
		return new UserDO(p.get(NEW_USER_NAME), p.get(NEW_USER_EMAIL), p.get(NEW_USER_HASHEDPW), p.get(NEW_USER_SALT), p.getBoolean(NEW_USER_ADMIN));
	}
	
	

}
