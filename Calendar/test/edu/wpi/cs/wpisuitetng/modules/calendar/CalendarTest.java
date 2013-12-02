/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Seal Team 6
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class CalendarTest {
	
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
