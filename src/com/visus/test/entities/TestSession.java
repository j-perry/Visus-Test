package com.visus.test.entities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.*;

import android.test.AndroidTestCase;
import android.util.Log;

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
	
	@Test
	public void testFormatSessionDuration() {
		DecimalFormat df = new DecimalFormat("0.00");
		
		// expected result		
		double testRecordDuration = 4.07;
		
		// both used to produce the 'actual' result
		int sessionMins = 3,
			sessionSecs = 67;
		
		double recordDuration = 0.0,
			   recordDurationMins = 0.0,
			   recordDurationSecs = 0.0;
		
		double tmpRecord = 0.0;
		
		String strRecord = null;
		
		if(sessionMins != 0) {
			strRecord = df.format(sessionMins);
			recordDurationMins = Double.valueOf(strRecord); // format minutes as m.00
		}		
		
		recordDurationSecs = ((double) sessionSecs / 100);
		recordDuration += (recordDurationMins + recordDurationSecs);
		Log.e("Visus", "recordDuration: " + recordDuration);
						
		if((recordDuration % 1) > 0.6) {
			Log.e("Visus", "wohoo: " + recordDuration);
			tmpRecord = recordDuration; // 1.0
			recordDuration = 1.0;
			recordDuration += tmpRecord - 0.6;
			
			// convert to .2 decimal places for precision
			strRecord = df.format(recordDuration);
			recordDuration = Double.valueOf(strRecord);
			
			Log.e("Visus", "Session: " + df.format(recordDuration));
		}
		else {
			Log.e("Visus", "NOT TRUE: " + recordDuration); // 0.4 - 0.2 (x2)
		}
		
		assertEquals(testRecordDuration, recordDuration);
	}
	
	@Test
	public void testFormatSessionDurations() {
		DecimalFormat df = new DecimalFormat("0.00");
		
		// expected result		
		double testRecordDuration = 5.29;
		
		// both used to produce the 'actual' result
		// NB: session seconds will not be above 60 secs
		int sessionMins = 3,
			sessionSecs = 55;
		
		double recordDuration = 0.0,
			   recordDurationMins = 0.0,
			   recordDurationSecs = 0.0;
		
		double exRecordDuration = 1.34; // input
		
		double tmpRecord = 0.0;
		
		String strRecord = null;
		
		/*
		 * 	First pass - previous session/s
		 */
		if((exRecordDuration % 1) > 0.6) {
			tmpRecord = exRecordDuration;
			exRecordDuration = 1.0;
			exRecordDuration += tmpRecord - 0.6;				

			// convert to .2 decimal places for precision
			strRecord = df.format(exRecordDuration);
			exRecordDuration = Double.valueOf(exRecordDuration);
			
			Log.e("Visus", "Exrecord: " + df.format(exRecordDuration));
		}
		else {
			recordDuration = exRecordDuration;
		}
		
		
		/* 
		 * 	Second pass - session just passed
		 */
		// if any minutes have been accumulated
		if(sessionMins != 0) {
			strRecord = df.format(sessionMins);
			recordDurationMins = Double.valueOf(strRecord); // format minutes as m.00
		}
		
		recordDurationSecs = ((double) sessionSecs / 100);
		recordDuration += (recordDurationMins + recordDurationSecs);
		Log.e("Visus", "recordDuration: " + recordDuration);
						
		if((recordDuration % 1) > 0.6) {
			Log.e("Visus", "wohoo: " + recordDuration);
			tmpRecord = recordDuration; // 1.0
			recordDuration = 1.0;
			recordDuration += tmpRecord - 0.6;
			
			// convert to .2 decimal places for precision
			strRecord = df.format(recordDuration);
			recordDuration = Double.valueOf(strRecord);
			
			Log.e("Visus", "Session: " + df.format(recordDuration));
		}
		else {
			Log.e("Visus", "NOT TRUE: " + recordDuration); // 0.4 - 0.2 (x2)
		}
		
		
		/**
		 * 	Third pass
		 */
		if((recordDuration % 1) > 0.6) {
			tmpRecord = (recordDuration + exRecordDuration);
			recordDuration = 1.0 + (tmpRecord - 0.6);

			// convert to .2 decimal places for precision
			strRecord = df.format(recordDuration);
			recordDuration = Double.valueOf(strRecord);
			
			Log.e("Visus", "Final result: " + df.format(recordDuration));
		}
		else {
			Log.e("Visus", "Final result: " + recordDuration);
		}
		
		assertEquals(testRecordDuration, recordDuration);
	}
}
