package hu.bearmaster.itm.common.test.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.exception.ItmException.ErrorCode;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;
import hu.bearmaster.itm.common.test.utils.TestPropertyLoader;
import static hu.bearmaster.itm.common.test.utils.TestUserConstants.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:test-spring-context.xml")
public class TestUserService extends AbstractTransactionalTestNGSpringContextTests {	
	
	@Resource
	private UserService userService;
	
	private TestPropertyLoader p;
	
	@BeforeClass
	public void setUp() {
		try {
			p = new TestPropertyLoader(TEST_USER_PROPS);
		} catch (IOException e) {
			//TODO what to do in this case?
			logger.error("Resource not found: " + TEST_USER_PROPS, e);
		}
	}
	
	@Test
	public void getExistingUserById() throws ItmException {
		UserVO user = userService.getUser(p.getLong(EXISTING_USER_ID));
		
		assertNotNull(user);
		assertEquals(user.getId(), (Long)p.getLong(EXISTING_USER_ID));
		assertEquals(user.getEmail(), p.get(EXISTING_USER_EMAIL));
	}
	
	@Test
	public void getExistingUserByName() throws ItmException {
		UserVO user = userService.getUser(p.get(EXISTING_USER_NAME));
		
		assertNotNull(user);
		assertEquals(user.getId(), (Long)p.getLong(EXISTING_USER_ID));
		assertEquals(user.getEmail(), p.get(EXISTING_USER_EMAIL));
	}
	
	@Test
	public void getNonExistingUserById() {
		try {
			userService.getUser(p.getLong(NON_EXISTING_USER_ID));
		} catch (ItmException e) {
			assertEquals(e.getErrorCode(), ErrorCode.USER_NOT_FOUND);
			return;
		}
		
		assertTrue(false, "ItmExpetion should be raised by getUser() call!");
	}
	
	@Test
	public void getNonExistingUserByName() {
		try {
			userService.getUser(p.get(NON_EXISTING_USER_NAME));
		} catch (ItmException e) {
			assertEquals(e.getErrorCode(), ErrorCode.USER_NOT_FOUND);
			return;
		}
		
		assertTrue(false, "ItmExpetion should be raised by getUser() call!");
	}
	
	@Test
	public void getUserList() {
		List<UserVO> userList = userService.listUsers();
		
		assertNotNull(userList);
		assertTrue(userList.size() > 0);
		
		for (UserVO user : userList) {
			assertNotNull(user);
			
		}
	}
	
	@Test
	public void registerUserSuccessfully() throws ItmException {
		
		UserVO user = createNewUser();
		
		user = userService.registerUser(user);
		
		assertNotNull(user.getId());
	}
	
	@Test
	public void registerUserWithExistingName() {
		UserVO user = createNewUser();
		user.setName(p.get(EXISTING_USER_NAME));
		
		try {
			userService.registerUser(user);
		} catch (ItmException e) {
			assertEquals(e.getErrorCode(), ErrorCode.USER_REGISTRATION_FAILED);
			return;
		}
		
		assertTrue(false, "Registration with existing username should fail!");
	}
	
	@Test
	public void registerUserWithExistingEmail() {
		UserVO user = createNewUser();
		user.setEmail(p.get(EXISTING_USER_EMAIL));
		
		try {
			userService.registerUser(user);
		} catch (ItmException e) {
			assertEquals(e.getErrorCode(), ErrorCode.USER_REGISTRATION_FAILED);
			return;
		}
		
		assertTrue(false, "Registration with existing username should fail!");
	}
	
	@Test
	public void authenticateUserSuccessfully() throws ItmException {
		UserVO user = userService.authenticateUser(p.get(REG_USER_NAME), p.get(REG_USER_PASSWORD));
		
		assertEquals(user.getName(), p.get(REG_USER_NAME));
		assertEquals(user.getEmail(), p.get(REG_USER_EMAIL));
		assertEquals(user.isAdmin(), p.getBoolean(REG_USER_ADMIN));
	}
	
	@Test
	public void authenticateUserWithIncorrectPassword() {
		try {
			userService.authenticateUser(p.get(REG_USER_NAME), p.get(REG_USER_INCORRECT_PW));
		} catch (ItmException e) {
			assertEquals(e.getErrorCode(), ErrorCode.USER_AUTHENTICATION_FAILED);
			return;
		}
		assertTrue(false, "Authentication should fail for incorrect password!");
	}
	
	@Test
	public void authenticateUserWithNonExistingUsername() {
		try {
			userService.authenticateUser(p.get(NON_EXISTING_USER_NAME), p.get(REG_USER_INCORRECT_PW));
		} catch (ItmException e) {
			assertEquals(e.getErrorCode(), ErrorCode.USER_AUTHENTICATION_FAILED);
			return;
		}
		assertTrue(false, "Authentication should fail for non-existing username!");
	}
	
	public UserVO createNewUser() {
		UserVO user = new UserVO();
		
		user.setName(p.get(NEW_USER_NAME));
		user.setEmail(p.get(NEW_USER_EMAIL));
		user.setPassword(p.get(NEW_USER_PASSWORD));
		user.setAdmin(p.getBoolean(NEW_USER_ADMIN));		
		
		return user;
	}

}
