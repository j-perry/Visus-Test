package com.visus.test.database;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import com.visus.database.SessionHandler;

import android.test.AndroidTestCase;

public class TestSessionHandler extends AndroidTestCase {

	private SessionHandler dbSession;
	private int day;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void getBeginningOfWeek() {
		String targetDay = "Sun";
		
		int dayNo = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()) );
		dayNo =- -2;
		String day = new SimpleDateFormat("EEE").format(dayNo);
			
		assertTrue("Equals Sun", day.contains(targetDay));
//		fail("Does not contain Sun");
	}

}
