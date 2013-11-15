package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class EventTest {
		
	Event dummyEvent;
	Event basicEvent;
	
	@Before
	public void setup() {
		User testUser = new User("Jean Valjean", "jvaljean", "mynameisjeanvaljean", 42601);
		Calendar testStart = new GregorianCalendar(2013, Calendar.NOVEMBER, 14, 18, 0);
		Calendar testEnd = new GregorianCalendar(2013, Calendar.NOVEMBER, 14, 22, 0);
		
		dummyEvent = new Event();
		basicEvent = new Event("Team 6 Meeting", "Flower", testStart, testEnd, testUser);
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
	public void testGetDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStartDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEndDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInvited() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAttending() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStartDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreator() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEndDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetInvited() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAttending() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInvited() {
		fail("Not yet implemented");
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
