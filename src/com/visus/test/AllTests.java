package com.visus.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.visus.test.entities.*;

import android.test.AndroidTestCase;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestUser.class, 
	TestSession.class
})
public class AllTests extends AndroidTestCase {

	public void setUp() throws Exception {
		super.setUp();
	}
	
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
}
