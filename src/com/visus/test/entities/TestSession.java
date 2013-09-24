package com.visus.test.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.*;

import android.test.AndroidTestCase;

import com.visus.entities.sessions.Session;

/**
 * Test units for entity class Session
 * 
 * Aim to refactor tests so they retain a clean and optimised design
 * @author Jonathan Perry
 *
 */
public class TestSession extends AndroidTestCase {

	private Session session;
	
	private final static String dayNoFormat = "dd";
	private final static String dayFormat = "EEE";
	private final static String monthFormat = "MMM";
	private final static String yearFormat = "yyyy";
	
	// we'll miss seconds (:ss) off. No need to get too precise!
	private final static String hourMinuteFormat = "h:mm";
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		session = new Session();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
		
	public void testDayNoFormat() {
		int valDayNo = 15;	
		
		session.setDayNo(Integer.parseInt(new SimpleDateFormat(dayNoFormat).format(new Date()) ));
		assertEquals(valDayNo, session.getDayNo());
	}
	
	public void testDayFormat() {
		String valDay = "Thur";
		
		session.setDay(new SimpleDateFormat(dayFormat).format(new Date() ));
		assertEquals(valDay, session.getDay());
	}
	
	public void testMonthFormat() {
		String valMonth = "Aug";
		
		session.setMonth(new SimpleDateFormat(monthFormat).format(new Date() ));
		assertEquals(valMonth, session.getMonth());
	}
	
	public void testYearFormat() {
		int valYear = 2015;
		
		session.setYear(Integer.parseInt( new SimpleDateFormat(yearFormat).format(new Date() ) ));
		assertEquals(valYear, session.getYear());
	}
			
	/**
	 * Test's the format of the current time.
	 * It should format the time as, e.g., 12:00 
	 */
	public void testTimeFormat() {
		String valTime = "10:12";
		assertEquals(valTime, new SimpleDateFormat(hourMinuteFormat).format(new Date()) );
	}
	
	/**
	 * Test's the format of the session time.
	 * 
	 * It should equal the String validation variable 
	 * to it's integer counterpart components
	 */
	public void testSessionDurationFormat() {
		int min = 29;
		int secs = 39;
		session.setDuration(min, secs);
		
		// assert the value of valSessionTime equals that of int vars
		assertTrue(session.getDuration().equals(min + ":" + secs));
	}
	
	public void testComputeExistingDurationToday() {
		float existingDurationToday = 17.57f; // 17-mins
		float durationToday = 0.0f;
		float testResult = 21.27f;
		
		// compute session
		int mins = 3; // mins
		float tmpSessionSecs = (float) 7 / 10.0f; // secs
		float product = (float) mins + tmpSessionSecs;
		
		// compute duration today
		durationToday = (float) existingDurationToday + product;
				
		assertEquals(testResult, durationToday);
	}
	
	public void testComputeExistingDurationMonth() {
		float existingDurationMonth = 45.09f; // 41.09-mins
		float durationMonth = 0.0f;
		double tmpDurationMonth = 0.0f;
		float session = 5.42f; // 5 mins + 42 secs
		float testResult = 50.51f;
		// compute duration
		tmpDurationMonth = Math.round( (existingDurationMonth + session) * 100) / 100.0;
		durationMonth = (float) tmpDurationMonth;
						
		assertEquals(testResult, durationMonth);
	}
}
