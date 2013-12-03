package edu.wpi.cs.wpisuitetng.modules.calendar;

import static org.junit.Assert.*;


import java.awt.Robot;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import java.lang.Object;
import junit.framework.TestCase;
//import junit.extensions.abbot;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
//import 
//import abbot.tester

public class CalendarTest {
	
	//Robot bot = new Robot();
	
	Calendar dummyCalendar;
	Calendar dummyCalendar2;
	
	@Before
	public void setup() {
		User testUser = new User("James John", "jj", "jjpass", 23458);
		dummyCalendar = new GregorianCalendar(2013, Calendar.NOVEMBER, 15, 01, 0);
	}
	
	@Test
	public void testDummyCalendar() {
		
		
		
		assertNotNull(dummyCalendar);
	}

	@Test
	public void testCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTabs() {
		fail("Not yet implemented");
	}

}
