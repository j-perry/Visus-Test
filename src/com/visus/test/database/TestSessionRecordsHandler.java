package com.visus.test.database;

import org.junit.*;

import com.visus.database.ISessionsRecordTable;

import android.test.AndroidTestCase;

public class TestSessionRecordsHandler extends AndroidTestCase {

	private StringBuilder resultExpected;
	private String resultActual;
	
	private String SELECT,
				   FROM,
				   WHERE;				   
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testQueryGetRecords() {
		// expected query
		resultExpected = new StringBuilder();
		resultExpected.append("SELECT * ");
		resultExpected.append("FROM " + ISessionsRecordTable.TABLE_NAME + " ");
		resultExpected.append("WHERE " + ISessionsRecordTable.KEY_ID + " = 1");
		
		// actual query
		SELECT = "SELECT * ";
		FROM   = "FROM " + ISessionsRecordTable.TABLE_NAME + " ";
		WHERE  = "WHERE " + ISessionsRecordTable.KEY_ID + " = 1";
		resultActual = SELECT + FROM + WHERE;
		
		assertEquals(resultExpected.toString(), resultActual);
	}
	
	/**
	 * Write query tests for each method in SessionRecordsHandler
	 */
	@Test
	public void testQueryGetActivityRecordByName() {
		String activity = "Test";
		
		// expected query
		resultExpected = new StringBuilder();
		resultExpected.append("SELECT * ");
		resultExpected.append("FROM " + ISessionsRecordTable.TABLE_NAME + " ");
		resultExpected.append("WHERE " + ISessionsRecordTable.KEY_ACTIVITY + " = " + activity);
		
		// actual result
		SELECT = "SELECT * ";
		FROM   = "FROM " + ISessionsRecordTable.TABLE_NAME + " ";
		WHERE  = "WHERE " + ISessionsRecordTable.KEY_ACTIVITY + " = " + activity;
		resultActual = SELECT + FROM + WHERE;
		
		assertEquals(resultExpected.toString(), resultActual);
	}
	
	@Test
	public void testQueryGetRecordsDesc() {
		// expected query
		resultExpected = new StringBuilder();
		resultExpected.append("SELECT * ");
		resultExpected.append("FROM " + ISessionsRecordTable.TABLE_NAME + " ");
		resultExpected.append("WHERE " + ISessionsRecordTable.KEY_ACTIVITY_DURATION + " DESC");
		
		// actual query
		SELECT = "SELECT * ";
		FROM   = "FROM " + ISessionsRecordTable.TABLE_NAME + " ";
		WHERE  = "WHERE " + ISessionsRecordTable.KEY_ACTIVITY_DURATION + " DESC";
		resultActual = SELECT + FROM + WHERE;
		
		assertEquals(resultExpected.toString(), resultActual);
	}
	
	@Test
	public void testQueryUpdateActivityRecordByName() {
		// expected query
		resultExpected = new StringBuilder();
		resultExpected.append("UPDATE " + ISessionsRecordTable.TABLE_NAME + " ");
		resultExpected.append("WHERE " + ISessionsRecordTable.KEY_ID + " = 1");
				
		// actual
		String UPDATE = "UPDATE " + ISessionsRecordTable.TABLE_NAME + " ";
		WHERE 		  = "WHERE " + ISessionsRecordTable.KEY_ID + " = 1";
		resultActual = UPDATE + WHERE;
		
		assertEquals(resultExpected.toString(), resultActual);
	}
	
	@Test
	public void testQueryDeleteActivityByName() {
		// expected where clause
		resultExpected = new StringBuilder();
		resultExpected.append(ISessionsRecordTable.KEY_ID + " = " + "1");
		resultExpected.append(" AND ");
		resultExpected.append(ISessionsRecordTable.KEY_ACTIVITY + " = " + "something");
		
		// actual
		WHERE = ISessionsRecordTable.KEY_ID + " = " + "1" +
				" AND " +
				ISessionsRecordTable.KEY_ACTIVITY + " = " + "something";
		resultActual = WHERE;
		
		assertEquals(resultExpected.toString(), resultActual);
	}
	
	@Test
	public void testQueryDeleteAllActivities() {
		// expected where clause
		resultExpected = new StringBuilder();
		resultExpected.append(ISessionsRecordTable.KEY_ID + " = " + "1");
		
		// actual
		WHERE = ISessionsRecordTable.KEY_ID + " = " + "1";
		resultActual = WHERE;
		
		assertEquals(resultExpected.toString(), resultActual);
	}
}
