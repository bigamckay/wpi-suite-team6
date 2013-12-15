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

package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.AbstractCalendarModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.utils.DateTimeUtils;

public class EventTest {
		
	Event dummyEvent;
	Event basicEvent;
	Event basicEvent2;
	Event basicEvent3;
	Event multiDayEvent;
	
	// create test users for the events
	String testUser = "jvaljean";
	String testUser2 = "ssquarepants";
	String testUser3 = "smeagol";
	
	// create test dates for the events
	Calendar testStart = new GregorianCalendar(2014, Calendar.NOVEMBER, 14, 18, 0);
	Calendar testStart2 = new GregorianCalendar(2014, Calendar.JANUARY, 21, 18, 0);
	Calendar testEnd = new GregorianCalendar(2014, Calendar.NOVEMBER, 14, 22, 0);
	Calendar testEnd2 = new GregorianCalendar(2014, Calendar.JANUARY, 21, 20, 0);
	
	Calendar testStart3 = new GregorianCalendar(2014, Calendar.JANUARY, 21, 18, 0);
	Calendar testEnd3 = new GregorianCalendar(2014, Calendar.JANUARY, 28, 20, 0);
	
	@Before
	public void setup() {
		
		try{
			basicEvent = new Event("Team 6 Meeting", "Flower", testStart, testEnd, "Funtimes!", testUser, true);
		} catch(WPISuiteException e){
			// does this if throws exception
			// compare e.getMessage() to expected
		}
		// does this if it works
		
		try{
			basicEvent2 = new Event("PlayDate", "Bancroft Towers", testStart2, testEnd2, "Funtimes!", testUser3, true);
		}catch(WPISuiteException e){
			
		}
		
		try{
			basicEvent3 = new Event("Another Event","Somewhere", testStart2,testEnd2,"Doing something?",testUser2, true);
		}catch(WPISuiteException e){}
		
		
		try{
			multiDayEvent = new Event("Yet Another Event","Somewhere Else", testStart3 ,testEnd3,"Doing something else?",testUser2, true);
		}catch(WPISuiteException e){}
	}
	
	
	// test the basic event to make sure it is created successfully
	@Test
	public void testBasicConstructor() {
		assertNotNull(basicEvent);
	}

	// tests the getter for the ids
	@Test
	public void testGetId() {
		assertNotNull(basicEvent.getId()); //the ID is not null and...
		//assertNotEquals(basicEvent.getId().compareTo(new UUID(0, 0)), 0); //it's been randomized
	}

	// tests the getter for the name
	@Test
	public void testGetName() {
		assertNotNull(basicEvent.getName());
		assertTrue(basicEvent.getName().equals("Team 6 Meeting"));
	}

	// tests the getter for the location
	@Test
	public void testGetLocation() {
		assertNotNull(basicEvent.getLocation());
		assertEquals(basicEvent.getLocation(), "Flower");
	}

	// tests the getter for the start date
	@Test
	public void testGetStartDate() {
		assertTrue(basicEvent.getStart() instanceof GregorianCalendar);
		assertEquals(testStart, basicEvent.getStart());
	}

	// tests the getter for the end date
	@Test
	public void testGetEndDate() {
		assertNotNull(basicEvent.getEnd());
		assertEquals(testEnd ,basicEvent.getEnd());
	}

	// tests the getter for the creator
	@Test
	public void testGetCreator() {
		assertNotNull(basicEvent.getOwner());
		assertEquals(testUser, basicEvent.getOwner());
	}
	
	// tests the getter for the description
	@Test
	public void testGetDescription() {
		assertNotNull(basicEvent.getDescription());
		assertEquals("Funtimes!", basicEvent.getDescription());
	}

	// tests the setter for the name
	@Test
	public void testSetName() {
		try{
			basicEvent.setName("IamTheWalrus!");
		}catch (WPISuiteException e){
			System.out.print("Name not valid");
		}
		
		assertEquals("IamTheWalrus!", basicEvent.getName());
	}

	// tests the setter for the location
	@Test
	public void testSetLocation() {
		try{
			basicEvent.setLocation("Bathroom");
		}catch (WPISuiteException e){}
		assertEquals("Bathroom",basicEvent.getLocation());
	}

	// tests the setter for the start date
	@Test
	public void testSetStartDate() {
		try{
			basicEvent.setStart(new GregorianCalendar(2014, Calendar.JUNE, 6, 18, 0));
		}catch(WPISuiteException e){}
		assertEquals(new GregorianCalendar(2014, Calendar.JUNE, 6, 18, 0),basicEvent.getStart());
	}
	
	// tests the setter for the end date
	@Test
	public void testSetEndDate() {
		try{
			basicEvent.setEnd(new GregorianCalendar(2015, Calendar.JUNE, 10, 18, 0));
		}catch(WPISuiteException e){}
		assertEquals("New end date should be applied without exception.",new GregorianCalendar(2015, Calendar.JUNE, 10, 18, 0), basicEvent.getEnd());
	}
	
	// tests the setter for the description
	@Test
	public void testSetDescription() {
		try{
			basicEvent.setDescription("You don't want to know.");
		}catch(WPISuiteException e){}
		assertEquals("You don't want to know.", basicEvent.getDescription());
	}
	
	/* TEST NAME EXCEPTIONS */
	
	@Test
	public void testException_SetName_SPACES(){
		try{
			basicEvent.setName("     ");
			assertTrue("Exception not thrown when should have.", false);
		}catch (WPISuiteException e){
			assertEquals("Exception thrown that event's name should contain alphanumeric character.", 
					e.getMessage(), "Name must contain at least one alphanumeric character.");
		}
	}
	
	@Test
	public void testException_SetName_EventName(){
		try{
			basicEvent.setName("Event Name");
			assertTrue("Exception not thrown when should have.", false);
		}catch(WPISuiteException e){
			assertEquals("Exception thrown that event's name shouldn't be Event Name.", 
					e.getMessage(), "Please assign an Event Name.");
		}
	}
	
	
	@Test
	// Test that exception is thrown when entering a null name
	public void testException_SetName_NULL() {
		try{
			basicEvent.setName("");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is that the name must exist
			assertEquals("Exception thrown that event's name can't be empty.", e.getMessage(), "Name cannot be empty.");
		}
	}
	
	@Test
	// Test that exception is thrown when entering an invalid character
	public void testException_SetName_InvalidCharacter() {
		try{
			basicEvent.setName("myNameis	");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is existence of tab
			assertEquals("Exception thrown that event's name must have valid character.", e.getMessage(), "Name cannot contain character 	");
		}
	}
	
	@Test
	// Test that exception is thrown when entering a really long name
	public void testException_SetName_NameTooLong() {
		try{
			basicEvent.setName("1234567890 1234567890 1234567890 1234567980");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is length
			assertEquals("Exception thrown that event's name has a size limit.", e.getMessage(), "Name too long.");
		}
	}
	
	/* TEST LOCATION EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when entering an invalid character
	public void testException_SetLocation_InvalidCharacter() {
		try{
			basicEvent.setLocation("Located at 		");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is existence of tab
			assertEquals("Exception thrown that event's location must have valid character.", e.getMessage(), "Location name cannot contain character 	");
		}
	}
	
	@Test
	// Test that exception is thrown when entering a really long name
	public void testException_SetLocation_NameTooLong() {
		try{
			basicEvent.setLocation("1234567890 1234567890 1234567890 1234567980");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is length
			assertEquals("Exception thrown that event's location has a size limit.", e.getMessage(), "Location name too long.");
		}
	}
	
	@Test
	// Test that exception is NOT thrown when changing name to empty
	public void testException_SetLocation_NULL() {
		try{
			basicEvent.setLocation("");
			// confirm changes 
			assertEquals("Exception not thrown when event's location is NULL", basicEvent.getLocation(), "");
		}catch(WPISuiteException e){
			fail("Exception thrown by changing to Null. This should not have happened");
		}
	}
	
	/* TEST DESCRIPTION EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when entering an invalid character
	public void testException_SetDescription_InvalidCharacter() {
		try{
			basicEvent.setDescription("I like to tab!		");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is existence of tab
			assertEquals("Exception thrown that Description must have valid character.", e.getMessage(), "Description cannot contain character 	");
		}
	}
	
	@Test
	// Test that exception is thrown when entering a really long name
	public void testException_SetDescription_NameTooLong() {
		try{
			basicEvent.setDescription("1234567890 1234567890 1234567890 1234567980 1234567980"
					+ "1234567890 1234567890 1234567890 1234567980 1234567980"
					+ "1234567890 1234567890 1234567890 1234567980 1234567980"
					+ "1234567890 1234567890 1234567890 1234567980 1234567980");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is length
			assertEquals("Exception thrown that event's description is too long.", e.getMessage(), "Description too long.");
		}
	}
	
	@Test
	// Test that exception is NOT thrown when changing name to empty
	public void testException_SetDescription_NULL() {
		try{
			basicEvent.setDescription("");
			// confirm changes 
			assertEquals("Exception not thrown when event's description is NULL", basicEvent.getDescription(), "");
		}catch(WPISuiteException e){
			fail("Exception thrown by changing to Null. This should not have happened");
		}
	}
	
	
	/* TEST END DATE EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when trying to set an end date before the start
	public void testException_SetEndDate_DateBeforeStart() {
		try{
			basicEvent.setEnd(new GregorianCalendar(2014, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events end after they start", e.getMessage(), "Events must end after they begin.");
		}
	}
	
	/* We dont care about date in the past
	@Test
	// Test that exception is thrown when trying to set an invalid date
	public void testException_SetEndDate_DateInPast() {
		try{
			basicEvent.setEnd(new GregorianCalendar(2012, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events must occur in the future.", e.getMessage(), "Events must occur in the future.");
		}
	}
	*/
	
	/* TEST START DATE EXCEPTIONS */
	@Test
	// Test that exception is thrown when trying to set an end date before the start
	public void testException_SetStartDate_DateBeforeStart() {
		try{
			basicEvent.setStart(new GregorianCalendar(2015, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that event must start before it ends.", e.getMessage(), "Events must end after they begin.");
		}
	}
	/* We now allow dates in the past
	@Test
	// Test that exception is thrown when trying to set an invalid date
	public void testException_SetEStartDate_DateInPast() {
		try{
			basicEvent.setStart(new GregorianCalendar(2012, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events must occur in the future.", e.getMessage(), "Events must occur in the future.");
		}
	}*/

	// test multiday events
	@Test
	public void testIsDayPartOfEvent(){
		Calendar dateBeforeEvent = new GregorianCalendar(2014, Calendar.JANUARY, 20, 18, 0);
		Calendar dateOnStart = new GregorianCalendar(2014, Calendar.JANUARY, 21, 22, 0);
		Calendar dateDuringEvent = new GregorianCalendar(2014, Calendar.JANUARY, 25, 18, 0);
		Calendar dateOnEnd = new GregorianCalendar(2014, Calendar.JANUARY, 28, 18, 0);
		Calendar dateAfterEvent = new GregorianCalendar(2014, Calendar.JANUARY, 29, 18, 0);
		
		assertFalse(DateTimeUtils.isDayPartOfEvent(multiDayEvent, dateBeforeEvent));
		assertTrue(DateTimeUtils.isDayPartOfEvent(multiDayEvent, dateOnStart));
		assertTrue(DateTimeUtils.isDayPartOfEvent(multiDayEvent, dateDuringEvent));
		assertTrue(DateTimeUtils.isDayPartOfEvent(multiDayEvent, dateOnEnd));
		assertFalse(DateTimeUtils.isDayPartOfEvent(multiDayEvent, dateAfterEvent));
	}
	
	
	
	
	//	@Test
//	public void testFromJson() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testFromJsonArray() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testSave() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testToJSON() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testToFromJson() {
		String jsonbasicevent = basicEvent.toJSON();
		Event returned = Event.fromJSON(jsonbasicevent, Event.class);
		assertEquals(returned.getId(), basicEvent.getId());
		assertEquals(returned.getName(), basicEvent.getName());
	}
	
	/*@Test
	public void testSetOwnerToCurrentUser(){
		basicEvent.setOwner(new User(null, ConfigManager.getConfig().getUserName(), null, 0));
		System.out.println(basicEvent.getOwner().getUsername());
	}*/
	
//	@Test
//	public void testIdentify() {
//		fail("Not yet implemented");
//	}

}
