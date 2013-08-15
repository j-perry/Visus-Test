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
	
	/**
	 * Test used when collecting dates from the database for each session.
	 * Test used to inform and verify format of the required date format.
	 */
	public void testDateFormat() {
		// validation variables
		String valDay = "Saturday ";
		String valMonth = "June ";
		String valYear = "2013";
		String valDate = valDay + valMonth + valYear;
				
		// day, month, year
		session.setDate(new SimpleDateFormat(dayNoFormat).format(new Date() ),
				        new SimpleDateFormat(dayFormat).format(new Date() ),
						new SimpleDateFormat(monthFormat).format(new Date() ), 
						new SimpleDateFormat(yearFormat).format(new Date() ));
		
		/* 	Assert validation data co-ordinates with the formatted types:
		*
		*	EEEE (day), 
		*	MMMM (month), 
		*	yyyy (year)
		*/ 
		assertEquals(valDate,
				     session.getDate() );
	}
	
	public void testDayNoFormat() {
		int valDayNo = 15;	
		
		session.setDayNo(Integer.parseInt(new SimpleDateFormat(dayNoFormat).format(new Date()) ));
		assertEquals(valDayNo, session.getDayNo());
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
}
