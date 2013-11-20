package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;
import static org.junit.Assert.*;

import org.junit.*;

public class CommitmentTest {
	// declare sample commitments
	Commitment testCommitment;
	Commitment testCommitment1;
	Commitment testCommitment2;
	
	//set up example calendars
	Calendar testDueDate = new GregorianCalendar(2014, Calendar.NOVEMBER, 14, 22, 0);
	Calendar testDueDate1 = new GregorianCalendar(2015, Calendar.NOVEMBER, 15, 17, 0);
	Calendar testDueDate2 = new GregorianCalendar(2199, Calendar.NOVEMBER, 17, 18, 0);
	
	// set up example user
	User testUser = new User("Jean Valjean", "jvaljean", "mynameisjeanvaljean", 42601);
	
		
	@Before
	public void setUp() {
		//make a fake simulated network to test JSON things
		//Network.initNetwork(new MockNetwork());
		//Network.getInstance().setDefaultNetworkConfiguration(
//				new NetworkConfiguration("http://wpisuitetng"));
		//set up example commitments
		testCommitment = new Commitment("thisIsATest", testDueDate, testUser);
		testCommitment1 = new Commitment("thisIsAlsoATest",testDueDate2, testUser);
		testCommitment2 = new Commitment("thisIsATest", testDueDate1, testUser);
	}	
	
	@Test
	public void testCommitment() {
		assertNotNull(testCommitment);
		assertTrue(testCommitment instanceof Commitment);
	}

	@Test
	public void testCommitmentCompleteConstructor() {
		assertNotNull(testCommitment.getName());
		assertNotNull(testCommitment.getDueDate());
		assertNotNull(testCommitment.getOwner());
		assertTrue(testCommitment.getName() instanceof String); 
		assertTrue(testCommitment.getDueDate() instanceof Calendar); 
	}
	


	@Test
	public void testGetId() {
		assertNotNull(testCommitment.getId()); //the ID is not null and...
		assertNotEquals(testCommitment.getId().compareTo(new UUID(0, 0)), 0); //it's been randomized
	}


	@Test
	public void testGetName() {
		assertNotNull(testCommitment.getName());
		assertTrue(testCommitment.getName() == "thisIsATest");
	}

	@Test
	public void testGetDueDate() {
		assertNotNull(testCommitment.getDueDate());
		assertTrue(testCommitment.getDueDate().equals(testDueDate));
	}
	
	@Test
	public void testGetCreator(){
		assertNotNull(testCommitment.getOwner());
		assertEquals(testUser, testCommitment.getOwner());
	}

	@Test
	public void testSetName() throws WPISuiteException{
		assertNotNull(testCommitment.getName());
		testCommitment.setName("This is a new test!");
		
		assertEquals(testCommitment.getName(), "This is a new test!");
	}
	
	@Test
	// Test that exception is thrown when entering a null name
	public void testException_SetName_NULL() {
		testCommitment.setName("");
		// Should throw exception and not execute the following line
		assertTrue("Exception not thrown when shouled have.", false);
	}
	
	@Test
	// Test that exception is thrown when entering an invalid character
	public void testException_SetName_InvalidCharacter() {
		testCommitment.setName("myNameis	");
		// Should throw exception and not execute the following line
		assertTrue("Exception not thrown when shouled have.", false);
	}
	
	@Test
	// Test that exception is thrown when entering a really long name
	public void testException_SetName_NameTooLong() {
		testCommitment.setName("1234567890 1234567890 1234567890 1234567980");
		// Should throw exception and not execute the following line
		assertTrue("Exception not thrown when shouled have.", false);
	}
	
	
	
	@Test
	public void testSetDueDate() {
		assertNotNull(testCommitment.getDueDate());
		testCommitment.setDueDate(testDueDate1);
		
		assertEquals(testCommitment.getDueDate(), testDueDate1);
	}
	
	
	/* TEST DATE EXCEPTIONS */

	@Test
	// Test that exception is thrown when trying to set an invalid date
	public void testException_SetDueDate_DateInPast() {
		testCommitment.setDueDate(new GregorianCalendar(2012, Calendar.JUNE, 10, 18, 0));
		// Should throw exception and not execute the following line
		assertTrue("Exception not thrown when shouled have.", false);
	}
	
	
	@Test
	public void testEqualsCommitment() {
		/*
		assertTrue(testCommitment.equalsCommitment(testCommitment2));
		assertFalse(testCommitment.equalsCommitment(testCommitment1));
		*/
		fail("Need to reimplement this, perhaps by checking ids or just calling commitment.identify");
	}

//	@Test
//	public void testCopyFrom() {
//		blankCommitment.copyFrom(testCommitment);
//		
//		//assertFalse(testCommitment.equalsCommitment(blankCommitment));
//		assertTrue(testCommitment.getName().equals(blankCommitment.getName()));
//		assertTrue(testCommitment.getDueDate().equals(blankCommitment.getDueDate()));
//		assertNotEquals(testCommitment.getId(), (blankCommitment.getId()));
//	}
	
	@Test
	public void testToFromJson() {
		String jsoncom = testCommitment.toJSON();
		Commitment returned = Commitment.fromJson(jsoncom);
		assertEquals(returned.getId(), testCommitment.getId());
		assertEquals(returned.getName(), testCommitment.getName());
	}




	@Test
	public void testIdentify() {
		//the identify method just returns null no matter what
		assertNull(testCommitment.identify(testCommitment));
	}


}
