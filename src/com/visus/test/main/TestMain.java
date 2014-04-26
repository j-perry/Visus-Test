package com.visus.test.main;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestMain extends AndroidTestCase {

	public TestMain() {
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testConvertMinutesToHours() {
		int data = 125; // minutes ~ 2 hours + 5 minutes
		float expected = 2.05f;
		float actual = 0.0f;
		float hours = 0;
		
		float result = (float) 125 / 100; // 1.25
		Log.e("Visus", "Before: " + String.valueOf(result));
				
		while(result > 0.6) {	// true - 1.25, 0.65, 0.05
			hours += 1.0;
			result = (result - 0.6f); // (1) 0.65, (2) 	0.05
			
			BigDecimal dc = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP);
			result = dc.floatValue();
			
			Log.e("Visus", "During: " + String.valueOf(result));
		}
		
		result = hours + result;
		
		assertEquals(expected, result);		
	}

}
