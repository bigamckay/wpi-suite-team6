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

public class EventTest {
		
	Event dummyEvent;
	Event basicEvent;
	
	User testUser = new User("Jean Valjean", "jvaljean", "mynameisjeanvaljean", 42601);
	User testUser2 = new User("Spongebob", "ssquarepants", "iheartpatrick", 12345);
	User testUser3 = new User("Golem", "smeagol", "myprecious", 98765);
	Calendar testStart = new GregorianCalendar(2013, Calendar.NOVEMBER, 14, 18, 0);
	Calendar testStart2 = new GregorianCalendar(2013, Calendar.JANUARY, 21, 18, 0);
	Calendar testEnd = new GregorianCalendar(2013, Calendar.NOVEMBER, 14, 22, 0);
	Calendar testEnd2 = new GregorianCalendar(2013, Calendar.JANUARY, 21, 20, 0);
	
	LinkedList<User> users = new LinkedList<User>();
	LinkedList<User> users2 = new LinkedList<User>();
	LinkedList<User> users3 = new LinkedList<User>();
	
	@Before
	public void setup() {
		
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
			basicEvent = new Event("PlayDate", "Bancroft Towers", testStart2, testEnd2, testUser3, "Ring Toss", users2, users2);
		}catch(WPISuiteException e){
			
		}
		
		
	}

	@Test
	public void testDummyConstructor() {
		assertNotNull(dummyEvent);
	}
	
	@Test
	public void testBasicConstructor() {
		assertNotNull(basicEvent);
	}

	@Test
	public void testAdvancedConstructor() {
		fail("Test not yet implemented");
	}

	@Test
	public void testGetId() {
		assertNotNull(basicEvent.getId()); //the ID is not null and...
		assertNotEquals(basicEvent.getId().compareTo(new UUID(0, 0)), 0); //it's been randomized
	}

	@Test
	public void testGetName() {
		assertNotNull(basicEvent.getName());
		assertTrue(basicEvent.getName().equals("Team 6 Meeting"));
	}

	@Test
	public void testGetLocation() {
		assertNotNull(basicEvent.getLocation());
		assertTrue(basicEvent.getLocation().equals("Flower"));
	}


	@Test
	public void testGetStartDate() {
		assertNotNull(basicEvent.getStart());
		assertEquals(new GregorianCalendar(2013, Calendar.NOVEMBER, 14, 18, 0), basicEvent.getStart());
	}

	@Test
	public void testGetEndDate() {
		assertNotNull(basicEvent.getEnd());
		assertEquals(new GregorianCalendar(2013, Calendar.NOVEMBER, 14, 22, 0),basicEvent.getEnd());
	}

	@Test
	public void testGetCreator() {
		assertNotNull(basicEvent.getCreator());
		assertEquals(testUser, basicEvent.getCreator());
	}
	
	@Test
	public void testGetDescription() {
		assertNotNull(basicEvent.getDescription());
		assertEquals("Funtimes!", basicEvent.getDescription());
	}
	
	@Test
	public void testGetInvited() {
		assertNotNull(basicEvent.getInvited());
		assertEquals(users, basicEvent.getInvited());
	}

	@Test
	public void testGetAttending() {
		assertNotNull(basicEvent.getAttending());
		assertEquals(users, basicEvent.getAttending());
	}

	@Test
	public void testSetName() {
		try{
			basicEvent.setName("Poopies!");
		}catch (WPISuiteException e){
			System.out.print("Name not valid");
		}
		
		assertEquals("Poopies!", basicEvent.getName());
	}

	@Test
	public void testSetLocation() {
		try{
			basicEvent.setLocation("Bathroom");
		}catch (WPISuiteException e){}
		assertEquals("Bathroom",basicEvent.getLocation());
	}

	@Test
	public void testSetStartDate() {
		try{
			basicEvent.setStart(new GregorianCalendar(2013, Calendar.JUNE, 6, 18, 0));
		}catch(WPISuiteException e){}
		assertEquals(new GregorianCalendar(2013, Calendar.JUNE, 6, 18, 0),basicEvent.getStart());
	}
	
	@Test
	public void testSetEndDate() {
		try{
			basicEvent.setEnd(new GregorianCalendar(2013, Calendar.JUNE, 10, 18, 0));
		}catch(WPISuiteException e){}
		assertEquals(new GregorianCalendar(2013, Calendar.JUNE, 6, 18, 0), basicEvent.getEnd());
	}
	
	@Test
	public void testSetDescription() {
		try{
			basicEvent.setDescription("You don't want to know.");
		}catch(WPISuiteException e){}
		assertEquals("You don't want to know.", basicEvent.getDescription());
	}

	@Test
	public void testSetInvited() {
		try{
		basicEvent.setInvited(users2);
		}catch(WPISuiteException e){}
		assertEquals(users2, basicEvent.getInvited());
	}

	@Test
	public void testSetAttending() {
		try{
		basicEvent.setAttending(users3);
		}catch(WPISuiteException e){}
		assertEquals(users3, basicEvent.getAttending());
	}

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

	@Test
	public void testRemoveInvited() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAttending() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAttending() {
		fail("Not yet implemented");
	}

	@Test
	public void testFromJson() {
		fail("Not yet implemented");
	}

	@Test
	public void testFromJsonArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJSON() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentify() {
		fail("Not yet implemented");
	}

}
