package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;

public class EventTest {
		
	Event dummyEvent;
	Event basicEvent;
	Event basicEvent2;
	Event basicEvent3;
	
	// create test users for the events
	User testUser = new User("Jean Valjean", "jvaljean", "mynameisjeanvaljean", 42601);
	User testUser2 = new User("Spongebob", "ssquarepants", "iheartpatrick", 12345);
	User testUser3 = new User("Golem", "smeagol", "myprecious", 98765);
	
	// create test dates for the events
	Calendar testStart = new GregorianCalendar(2014, Calendar.NOVEMBER, 14, 18, 0);
	Calendar testStart2 = new GregorianCalendar(2014, Calendar.JANUARY, 21, 18, 0);
	Calendar testEnd = new GregorianCalendar(2014, Calendar.NOVEMBER, 14, 22, 0);
	Calendar testEnd2 = new GregorianCalendar(2014, Calendar.JANUARY, 21, 20, 0);
	
	//create test events
	LinkedList<User> users = new LinkedList<User>();
	LinkedList<User> users2 = new LinkedList<User>();
	LinkedList<User> users3 = new LinkedList<User>();
	
	@Before
	public void setup() {
		// add users to the user lists for invited and attending
		users.add(testUser);
		users.add(testUser2);
		
		users2.add(testUser2);
		users2.add(testUser3);
		
		users3.add(testUser3);
		
		dummyEvent = new Event();
		try{
			basicEvent = new Event("Team 6 Meeting", "Flower", testStart, testEnd, testUser,"Funtimes!", users, users);
		} catch(WPISuiteException e){
			// does this if throws exception
			// compare e.getMessage() to expected
		}
		// does this if it works
		
		try{
			basicEvent2 = new Event("PlayDate", "Bancroft Towers", testStart2, testEnd2, testUser3, "Ring Toss", users2, users2);
		}catch(WPISuiteException e){
			
		}
		
		try{
			basicEvent3 = new Event("Another Event","Somewhere", testStart2,testEnd2,testUser2,"Doing something?",users3,users3);
		}catch(WPISuiteException e){}
		
		
	}

	// test the dummy constructor
	@Test
	public void testDummyConstructor() {
		assertNotNull(dummyEvent);
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
		assertNotEquals(basicEvent.getId().compareTo(new UUID(0, 0)), 0); //it's been randomized
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
		assertNotNull(basicEvent.getCreator());
		assertEquals(testUser, basicEvent.getCreator());
	}
	
	// tests the getter for the description
	@Test
	public void testGetDescription() {
		assertNotNull(basicEvent.getDescription());
		assertEquals("Funtimes!", basicEvent.getDescription());
	}
	
	// tests the getter for the list of invited people
	@Test
	public void testGetInvited() {
		assertNotNull(basicEvent.getInvited());
		assertEquals(users, basicEvent.getInvited());
	}

	// tests the getter for the list of attending people
	@Test
	public void testGetAttending() {
		assertNotNull(basicEvent.getAttending());
		assertEquals(users, basicEvent.getAttending());
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

	// tests the setter for the list of invited people
	@Test
	public void testSetInvited() {
		try{
		basicEvent.setInvited(users2);
		}catch(WPISuiteException e){}
		assertEquals(users2, basicEvent.getInvited());
	}

	// tests the setter for the list of attending people
	@Test
	public void testSetAttending() {

		try{
			basicEvent.setAttending(users3);
			assertEquals("Attending users of basic event should be users3.", users3, basicEvent.getAttending());
		}catch(WPISuiteException e){
			assertTrue("Exception was encountered", false);
			return;
		}
		
	}

	// test for adding a user to the invited users list
	@Test
	public void testAddInvited() {
		try{
		basicEvent.setInvited(users3);
		}catch(WPISuiteException e){}
		try{
		basicEvent.addInvited(testUser2);
		}catch(WPISuiteException e){}
		
		assertEquals(users3,basicEvent.getInvited());
	}

	
	// tests for removing a user from the invited users list
	@Test
	public void testRemoveInvited() {
		try{
			basicEvent2.removeInvited(testUser2);
		}catch(WPISuiteException e){
			System.out.println("EXCEPTION");
		}
		
		assertEquals(basicEvent3.getInvited(),basicEvent2.getInvited());
	}

	
	// tests the adding of a user to the attending users list
	@Test
	public void testAddAttending() {
		try{
			basicEvent3.addAttending(testUser2);
		}catch(WPISuiteException e){}
	
		LinkedList<User> testUsers = new LinkedList<User>();
		testUsers.add(testUser3);
		testUsers.add(testUser2);
		
		assertEquals(basicEvent3.getAttending(), testUsers);
		
	}

	// tests the removal of a user from the list of attending users
	@Test
	public void testRemoveAttending() {
		
		try{
			basicEvent2.removeAttending(testUser2);
		}catch(WPISuiteException e){}
		
		
		assertEquals(basicEvent3.getAttending(), basicEvent2.getAttending());
	}
	
	/* TEST NAME EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when entering a null name
	public void testException_SetName_NULL() {
		try{
			basicEvent.setName("");
			// Should throw exception and not execute the following line
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception thrown by changing to Null. This should not have happened", false);
		}
	}
	
	/* TEST DESCRIPTION EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when entering an invalid character
	public void testException_SetDescription_InvalidCharacter() {
		try{
			basicEvent.setDescription("I like to tab!		");
			// Should throw exception and not execute the following line
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception not thrown when shouled have.", false);
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
			assertTrue("Exception thrown by changing to Null. This should not have happened", false);
		}
	}
	
	
	/* TEST END DATE EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when trying to set an end date before the start
	public void testException_SetEndDate_DateBeforeStart() {
		try{
			basicEvent.setEnd(new GregorianCalendar(2014, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			assertTrue("Exception not thrown when shouled have.", false);
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events end after they start", e.getMessage(), "Events must end after they begin.");
		}
	}
	
	@Test
	// Test that exception is thrown when trying to set an invalid date
	public void testException_SetEndDate_DateInPast() {
		try{
			basicEvent.setEnd(new GregorianCalendar(2012, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			assertTrue("Exception not thrown when shouled have.", false);
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events must occur in the future.", e.getMessage(), "Events must occur in the future.");
		}
	}
	
	/* TEST START DATE EXCEPTIONS */
	@Test
	// Test that exception is thrown when trying to set an end date before the start
	public void testException_SetStartDate_DateBeforeStart() {
		try{
			basicEvent.setStart(new GregorianCalendar(2015, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			assertTrue("Exception not thrown when shouled have.", false);
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that event must start before it ends.", e.getMessage(), "Events must end after they begin.");
		}
	}
	
	@Test
	// Test that exception is thrown when trying to set an invalid date
	public void testException_SetEStartDate_DateInPast() {
		try{
			basicEvent.setStart(new GregorianCalendar(2012, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			assertTrue("Exception not thrown when shouled have.", false);
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events must occur in the future.", e.getMessage(), "Events must occur in the future.");
		}
	}

	@Test
	public void testFromJson() {
		fail("Not yet implemented");
	}

	@Test
	public void testFromJsonArray() {
		fail("Not yet implemented");
	}

//	@Test
//	public void testSave() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testToJSON() {
		fail("Not yet implemented");
	}

	@Test
	public void testToFromJson() {
		String jsonbasicevent = basicEvent.toJSON();
		Event returned = Event.fromJson(jsonbasicevent);
		assertEquals(returned.getId(), basicEvent.getId());
		assertEquals(returned.getName(), basicEvent.getName());
	}
	
//	@Test
//	public void testIdentify() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testParserValidInput(){
		try{
			Calendar testCal1 = dummyEvent.dateTimeParser("11/14/2014", "18:00");
			assertEquals(testStart, testCal1);
		}
		catch(WPISuiteException e){
			fail("Threw exception");
		}	
	}
	
	@Test
	public void testParserInvalidDateFormat(){
		try{
			dummyEvent.dateTimeParser("4/30/14", "12:00");
			fail("Failed to throw invalid date exception");
		}
		catch(WPISuiteException e){
			assertEquals("Exception that date length is wrong", e.getMessage(), "Date must be in form mm/dd/yyyy");
		}
	}
	
	@Test
	public void testParserInvalidDate(){
		try{
			dummyEvent.dateTimeParser("04/31/2014", "12:00");
			fail("Failed to throw invalid date exception");
		}
		catch(WPISuiteException e){
			assertEquals("Exception that date is invalid", e.getMessage(), "Invalid date/time input");
		}
	}

}
