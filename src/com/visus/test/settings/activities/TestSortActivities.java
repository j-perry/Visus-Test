package com.visus.test.settings.activities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.visus.settings.activities.ActivitiesManager;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestSortActivities extends AndroidTestCase {
	
	private final Double [] activity1 = { 24.59, 24.59, 14.31, 7.11 };
	private final Double [] activity2 = { 24.20, 21.31, 14.31, 7.11, 13.41 };
	private final Double [] activity3 = { 10.40, 24.59, 3.51, 7.03 };
	private final Double [] activity4 = { 2.39, 12.19, 18.12, 22.13 };
	
	private StringBuilder testResult;

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@SuppressWarnings("null")
	@Test
	public void testSortActivitiesBySize() {
		// assume we have retrieved each activity type and the durations 
		// for the sessions associated with each one from the db
		// activity 'n'
		Stack<Double []> activities = new Stack<Double []>();
		ActivitiesManager am = new ActivitiesManager();
		NumberFormat nf = new DecimalFormat("#.##"); 
		
		/*
		 * Test results
		 */		
		// 70.60
		Double testResult1 = Double.valueOf(nf.format(activity1[0] + activity1[1] + activity1[2] + activity1[3]) );
		
		// 80.34
		Double testResult2 = Double.valueOf(nf.format(activity2[0] + activity2[1] + activity2[2] + activity2[3] + activity2[4]) );
		
		// 45.53
		Double testResult3 = Double.valueOf(nf.format(activity3[0] + activity3[1] + activity3[2] + activity3[3]) );
		
		// 54.83
		Double testResult4 = Double.valueOf(nf.format(activity4[0] + activity4[1] + activity4[2] + activity4[3]) );
		
		String testExpectedResult = new String(String.valueOf(testResult2) + ", " +
								   	           String.valueOf(testResult1) + ", " +
								               String.valueOf(testResult4) + ", " +
								               String.valueOf(testResult3) );
		testResult = new StringBuilder();
		
		@SuppressWarnings("unused")
		Double [] unsortedActivities = new Double[4],
				  sortedActivities = new Double[4];
		int i = 0;
		
		activities.push(activity1);
		activities.push(activity2);
		activities.push(activity3);
		activities.push(activity4);
		
		// ok; we now need to add each of the values for each activity
		// run a while loop that takes input of each set of integers
		// returning the value of each set
		while(activities.size() != 0) {
			Double [] activity = activities.pop();
			unsortedActivities[i++] = am.add(activity);
		}
		
		sortedActivities = am.sort(unsortedActivities);
		
		ArrayList<Double> newResult = new ArrayList<Double>(Arrays.asList(sortedActivities) );
		Collections.reverse(newResult);
		
		for(i = 0; i < newResult.size(); i++) {
			if(i == newResult.size() -1) {
				testResult.append(String.valueOf(newResult.get(i) ));
			}
			else {
				testResult.append(String.valueOf(newResult.get(i) ) + ", ");
			}
		}
		
		// check whether sorted results match with sorted results defined
		assertEquals(testExpectedResult, testResult.toString());
		Log.e("Visus", "testResult: " + testResult);
		Log.e("Visus", "result: " + testResult.toString());
	}
	
	@Test
	public void testGetActivitiesByType() {
		HashMap<String, Double> data = new HashMap<String, Double>();
		ArrayList<String> activities = new ArrayList<String>();
		
		// activities
		activities.add("Work");
		activities.add("Coding");
		activities.add("Email");		
		
		// test data set
		// activity 1
		data.put("Work", activity1[0]);
		data.put("Work", activity1[1]);
		data.put("Work", activity1[2]);

		// activity 2
		data.put("Coding", activity2[0]);
		data.put("Coding", activity2[1]);
		data.put("Coding", activity2[2]);

		// activity 3
		data.put("Email", activity3[0]);
		data.put("Email", activity3[1]);
		data.put("Email", activity3[2]);
		
//		Double [] act1 = data.get(activities.get(0)); // gets work
	}
}
