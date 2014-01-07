package com.visus.test.database;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import com.visus.database.ISessionTable;
import com.visus.database.SessionHandler;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestSessionHandler extends AndroidTestCase {

	private SessionHandler dbSession;
	private int day;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void getBeginningOfWeek() {
		String targetDay = "Sun";
		
		int dayNo = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()) );
		dayNo =- -2;
		String day = new SimpleDateFormat("EEE").format(dayNo);
			
		assertTrue("Equals Sun", day.contains(targetDay));
//		fail("Does not contain Sun");
	}
	
	/**
	 * Test used to check whether pre-defined query matches query components
	 * Implemented to check the design of the query before implementation in production
	 * code.
	 */
	@Test
	public void testQueryStringGetSessionsByLength() {
		StringBuilder qryTestExpected = new StringBuilder("SELECT * " +
														  "FROM " + ISessionTable.TABLE_NAME + " " +
														  "WHERE " + ISessionTable.KEY_USER_ID + " = 1");
		StringBuilder qryTestActual = new StringBuilder();

		// query components
		String fields = "SELECT * ";
		String dbTable = "FROM " + ISessionTable.TABLE_NAME + " ";
		String whereCond = "WHERE " + ISessionTable.KEY_USER_ID + " = 1";
		
		// append
		qryTestActual.append(fields);
		qryTestActual.append(dbTable);
		qryTestActual.append(whereCond);
		
		assertEquals(qryTestExpected.toString(), qryTestActual.toString() );
	}
}
