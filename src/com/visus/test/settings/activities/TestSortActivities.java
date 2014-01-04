package com.visus.test.settings.activities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.visus.settings.activities.ActivitiesManager;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestSortActivities extends AndroidTestCase {

	public TestSortActivities() {
	}

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
		Double [] activity1 = { 24.59, 24.59, 14.31, 7.11 };
		Double [] activity2 = { 24.20, 21.31, 14.31, 7.11, 13.41 };
		Double [] activity3 = { 10.40, 24.59, 3.51, 7.03 };
		Double [] activity4 = { 2.39, 12.19, 18.12, 22.13 };
		
		// test results
		Double testResult1 = 70.60;
		Double testResult2 = 80.34;
		Double testResult3 = 45.53;
		Double testResult4 = 54.83;
		String testResult = new String(String.valueOf(testResult2) + ", " +
								   	   String.valueOf(testResult1) + ", " +
								       String.valueOf(testResult4) + ", " +
								       String.valueOf(testResult3) );
		StringBuilder result = null;
		
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
		result = new StringBuilder();
		
		for(i = 0; i < newResult.size(); i++) {
			if(i == newResult.size() -1) {
				result.append( String.valueOf(newResult.get(i) ));
			}
			else {
				result.append( String.valueOf(newResult.get(i) ) + ", ");
			}
		}
		
		// check whether sorted results match with sorted results defined
		assertEquals(testResult, result.toString());
		Log.e("Visus", "testResult: " + testResult);
		Log.e("Visus", "result: " + result.toString());
	}
}
