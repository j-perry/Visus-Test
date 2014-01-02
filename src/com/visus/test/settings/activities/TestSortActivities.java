package com.visus.test.settings.activities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.visus.settings.activities.ActivitiesManager;

import android.test.AndroidTestCase;

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
		Stack<double []> activities = new Stack<double []>();
		ActivitiesManager am = new ActivitiesManager();
		double [] activity1 = { 24.59, 24.59, 14.31, 7.11 };
		double [] activity2 = { 24.20, 21.31, 14.31, 7.11, 13.41 };
		double [] activity3 = { 10.40, 24.59, 3.51, 7.03 };
		double [] activity4 = { 2.39, 12.19, 18.12, 22.13 };
		
		// test results
		double testResult1 = 70.60;
		double testResult2 = 80.34;
		double testResult3 = 45.53;
		double testResult4 = 54.83;
		String testResult = new String(String.valueOf(testResult2) + ", " +
								   	   String.valueOf(testResult1) + ", " +
								       String.valueOf(testResult4) + ", " +
								       String.valueOf(testResult3) );
		String result = null;
		
		@SuppressWarnings("unused")
		double [] unsortedActivities = null,
				  sortedActivities= null;
		int i = 0;
		
		activities.push(activity1);
		activities.push(activity2);
		activities.push(activity3);
		activities.push(activity4);
		
		// ok; we now need to add each of the values for each activity
		// run a while loop that takes input of each set of integers
		// returning the value of each set	
		while(activities.size() != 0) {
			double [] activity = activities.pop();
			unsortedActivities[i++] = am.add(activity);
		}
		
		sortedActivities = am.sort(unsortedActivities);
		
		result = new String(String.valueOf(sortedActivities[0]) + ", " +
							String.valueOf(sortedActivities[1]) + ", " +
							String.valueOf(sortedActivities[2]) + ", " +
							String.valueOf(sortedActivities[3]) );
		
		// check whether sorted results match with sorted results defined
		assertEquals(result, testResult);
	}
}
