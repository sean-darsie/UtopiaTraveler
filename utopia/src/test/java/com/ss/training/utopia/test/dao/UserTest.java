package com.ss.training.utopia.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ss.training.utopia.dao.UserDAO;
import com.ss.training.utopia.entity.User;

@DataJpaTest
public class UserTest {
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private TestEntityManager testEntityManager;
	

	@Test
	public void findByUsernameTest() {
		String thisUsername = "ThisUsername";
		String otherUsername = "OtherUsername";
		String notAUserName = "NotAUserName";
		User thisUser = new User(null, thisUsername, null, null, null);
		User otherUser = new User(null, otherUsername, null, null, null);
		
		testEntityManager.persist(thisUser);
		testEntityManager.persist(otherUser);
		testEntityManager.flush();
		
				
		assertEquals(thisUser, userDao.findByUsername(thisUsername));
		assertNull(userDao.findByUsername(notAUserName));
	}
	

	
}
