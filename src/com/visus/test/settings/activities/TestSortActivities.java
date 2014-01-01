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
		double [] activity2 = { 24.20, 21.31, 14.31, 7.11, 13.41, 24.59 };
		double [] activity3 = { 24.59, 24.59, 14.31, 7.11 };
		double [] activity4 = { 24.59, 24.59, 14.31, 7.11 };
		
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
		
		// check whether sorted results match with sorted results defined
//		assertTrue();
	}
}
