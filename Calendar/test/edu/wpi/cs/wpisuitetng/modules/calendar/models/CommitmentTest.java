package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;
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
	
	
	// create test users for the events
	String testUser = "jvaljean";
	String testUser2 = "ssquarepants";
	String testUser3 = "smeagol";
	
	
		
	@Before
	public void setUp() throws WPISuiteException {
		try{
			testCommitment = new Commitment("This Is A Test", testDueDate, testUser,"A Descriptive Description");
			testCommitment1 = new Commitment("This Is Also A Test",  testDueDate2, testUser, "Prologue");
			testCommitment2 = new Commitment("The Test of the Test", testDueDate1, testUser, "For the Glory of the Tests");
		}catch(WPISuiteException e){
			
		}
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
		//assertNotEquals(testCommitment.getId().compareTo(new UUID(0, 0)), 0); //it's been randomized
	}


	@Test
	public void testGetName() {
		assertNotNull(testCommitment.getName());
		assertTrue(testCommitment.getName().equals("This Is A Test"));
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

	
	// TEST VALID INPUTS
	@Test
	public void testSetName(){
		assertNotNull(testCommitment.getName());
		try{
			testCommitment.setName("This is a new test!");
			assertEquals(testCommitment.getName(), "This is a new test!");
		} catch(WPISuiteException e){
			fail("Received Exception with a valid entry");
		}
	}
	
	
	@Test
	public void testSetDueDate() {
		assertNotNull(testCommitment.getDueDate());
		try{
			testCommitment.setDueDate(testDueDate1);
			assertEquals(testCommitment.getDueDate(), testDueDate1);
		} catch(WPISuiteException e){
			fail("Received Exception with a valid entry");
		}
	}
	
	
	
	

	/* TEST NAME EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when entering a null name
	public void testException_SetName_NULL() {
		try{
			testCommitment.setName("");
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
			testCommitment.setName("myNameis	");
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
			testCommitment.setName("1234567890 1234567890 1234567890 1234567980");
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is length
			assertEquals("Exception thrown that event's name has a size limit.", e.getMessage(), "Name too long.");
		}
	}
	
	

	
/* TEST DESCRIPTION EXCEPTIONS */
	
	@Test
	// Test that exception is thrown when entering an invalid character
	public void testException_SetDescription_InvalidCharacter() {
		try{
			testCommitment.setDescription("I like to tab!		");
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
			testCommitment.setDescription("1234567890 1234567890 1234567890 1234567980 1234567980"
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
			testCommitment.setDescription("");
			// confirm changes 
			assertEquals("Exception not thrown when event's description is NULL", testCommitment.getDescription(), "");
		}catch(WPISuiteException e){
			fail("Exception thrown by changing to Null. This should not have happened");
		}
	}
	
	

	/* TEST DUEDATE EXCEPTIONS */
	
	/* no longer checking for date in past
	@Test
	// Test that exception is thrown when trying to set an invalid date
	public void testException_SetDueDate_DateInPast() {
		try{
			testCommitment.setDueDate(new GregorianCalendar(2012, Calendar.JUNE, 10, 18, 0));
			// Should throw exception and not execute the following line
			fail("Exception not thrown when shouled have.");
		}catch(WPISuiteException e){
			// confirm that the cause of exception is a date in the past
			assertEquals("Exception thrown that events must occur in the future.", e.getMessage(), "Events must occur in the future.");
		}
	}
	*/
	
	/* Not currently implemented
	@Test
	public void testEqualsCommitment() {
		
		assertTrue(testCommitment.equalsCommitment(testCommitment2));
		assertFalse(testCommitment.equalsCommitment(testCommitment1));
		
		fail("Need to reimplement this by comparing ids, possibly with the identify method in commitment");
	}
	*/

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
		Commitment returned = Commitment.fromJSON(jsoncom, Commitment.class);
		assertEquals(returned.getId(), testCommitment.getId());
		assertEquals(returned.getName(), testCommitment.getName());
	}




	@Test
	public void testIdentify() {
		//the identify method just returns null no matter what
		assertNull(testCommitment.identify(testCommitment));
	}


}
