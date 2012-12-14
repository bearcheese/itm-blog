package hu.bearmaster.itm.common.test;

import java.util.Date;

import javax.annotation.Resource;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.model.UserDO;
import hu.bearmaster.itm.common.validators.TimestampUpdateListener;
import hu.bearmaster.itm.common.validators.TimestampedEntity;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.Test;


@ContextConfiguration(locations = "classpath:test-spring-context.xml")
public class BasicTest extends AbstractTestNGSpringContextTests {
	
	@Resource
	private UserDao userDao;
	
	@Test
	public void testMethod() {
		System.out.println("Testing...");
		assertNotNull(userDao);
		
		UserDO userDo = userDao.find(1L);
		
		assertEquals(userDo.getEmail(), "test.elek@itm-blog.hu");
	}
	
	@Test
	public void badTest() {
		TimestampedEntity entity = new TimestampedEntity() {
			
			@Override
			public void setUpdatedAt(Date updatedAt) {}
			
			@Override
			public void setCreatedAt(Date createdAt) {}
			
			@Override
			public Date getUpdatedAt() {
				return null;
			}
			
			@Override
			public Date getCreatedAt() {
				return null;
			}
		};
		
		TimestampUpdateListener listener = new TimestampUpdateListener();
		
		listener.setTimestampAtPersist(entity);
		
		listener.setTimestampAtUpdate(entity);
	}

}
