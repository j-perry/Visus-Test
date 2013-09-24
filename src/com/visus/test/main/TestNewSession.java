package com.visus.test.main;

import java.math.BigDecimal;

import org.junit.*;

import android.test.AndroidTestCase;

public class TestNewSession extends AndroidTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConvertIntegersToFloat() {
		int mins = 3;
		int secs = 4;
		float tmpSecs = (float) secs / 10.0f;
		float product = mins + tmpSecs;
		
		product = (float) mins + tmpSecs;
		
		assertEquals(3.4f, product);
	}
	
	/**
	 * NB: Date omitted
	 */
	public void testTimeAccumulatedToday() {
		float duration = 0.0f;
		float durationResult = 6.2f;
		int elapsedMinutes = 0,
		    elapsedSeconds = 0;
		
		int tmpMinute = 5,
		    tmpSecond = 80; // 5 mins + 80 secs (+ 1min + 20 secs)
		
		elapsedMinutes += tmpMinute;
		elapsedSeconds += tmpSecond;
		
		// convert seconds to decimal places
		float tmpElapsedSeconds = (float) elapsedSeconds / 100.0f;
		
		float product = elapsedMinutes + tmpElapsedSeconds;
		
		/*****************************************************************************************
		 * 		Calculate overlap in seconds and true representation of accumulated duration
		 */
		// take a copy of session time (just created) - mm:ss
		float productCpy = product;
		
		// find decimal product derived from mm:ss
		float durationSecondsToday = product % 1;
		
		// calculate no. of minutes accumulated - i.e., 2.5 - 0.5 = 2 mins
		productCpy = productCpy - durationSecondsToday;
		
		// if over 60 seconds
		if(durationSecondsToday > 0.6f) {
			float incrMin = 1.0f;
			
			// copy number of seconds accumulated
			float durationSecsTodayCpy = durationSecondsToday;
			durationSecondsToday = 0.0f;
			
			// calculate no. of seconds left over 60 seconds (0.6)
			durationSecondsToday = (durationSecsTodayCpy - 0.6f);
			
			// initialise no. of mins + secs - i.e., 3.1, appending an additional minute for accumulated seconds over 60 seconds
			duration = productCpy + incrMin + durationSecondsToday;
			
			// format the duration to 2 decimal places - e.g., X.XX
			BigDecimal durationTodayReformatted = new BigDecimal(duration).setScale(2, BigDecimal.ROUND_HALF_UP);
			duration = durationTodayReformatted.floatValue();
		}
		else {
			// format the duration to 2 decimal places - e.g., X.XX
			BigDecimal durationTodayReformatted = new BigDecimal(duration).setScale(2, BigDecimal.ROUND_HALF_UP);
			duration = durationTodayReformatted.floatValue();
		}
		
		assertEquals(durationResult, duration);
	}
	
}
