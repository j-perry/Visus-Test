package com.visus.test.entities;

import com.visus.entities.User;

import org.junit.*;

import android.test.AndroidTestCase;

/**
 * Test units for entity class User 
 * @author Jonathan Perry
 *
 */
public class TestUser extends AndroidTestCase {

	private User user;
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSetFirstname() {
		user.setFirstname("Jonathan");
		assertEquals("Jonathan", user.getFirstname());
	}
	
	public void testSetAge() {
		int age = 22;
		user.setAge(22);
		assertEquals(age, user.getAge());
	}
	
	public void testSetAgeIsAnInteger() {
		String s_age = "22";
		user.setAge(22);
		assertEquals(Integer.parseInt(s_age), user.getAge());
	}
	
	public void testSetGender() {
		String gender = "Male";
		user.setGender(gender);
		assertEquals(gender, user.getGender());
	}
}
