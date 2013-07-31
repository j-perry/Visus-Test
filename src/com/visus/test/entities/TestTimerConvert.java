package com.visus.test.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.visus.entities.*;

/**
 * Test units for entity class TimerParser
 * 
 * @author Jonathan Perry
 *
 */
public class TestTimerConvert extends AndroidTestCase {

	private TimerConvert tConvert;
	
	private int minutes;
	private int seconds;
	private int milliseconds;
			
	public TestTimerConvert() {
		
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tConvert = new TimerConvert();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConvertMinutesToMillisecs() {
		minutes = 4;
		milliseconds = 240000;
		
		tConvert.minutesToMilliseconds(minutes);
		assertEquals(milliseconds, tConvert.getMillisecondsFromMinutes() );
	}
	
	public void testConvertSecondsToMillisecs() {
		seconds = 47;
		milliseconds = 47000;
		
		tConvert.secondsToMilliseconds(seconds);
		assertEquals(milliseconds, tConvert.getMillisecondsFromMinutes() );
	}

	/**
	 * Tests whether inputed minutes and seconds is converted
	 * to an appropriate number in milliseconds
	 */
	public void testConvertMinutesAndSecondsToMillisecs() {
		minutes = 3;
		seconds = 30;
		
		// used to validate
		milliseconds = 180000; // mins
		milliseconds += 30000; // secs
		
		assertEquals(milliseconds, tConvert.minutesAndSecondsToMilliseconds(minutes, seconds) );
	}

}
