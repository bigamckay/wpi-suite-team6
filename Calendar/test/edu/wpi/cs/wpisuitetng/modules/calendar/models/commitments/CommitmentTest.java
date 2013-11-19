package edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments;

import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments.*;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockData;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.calendar.MockRequest;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;
import static org.junit.Assert.*;

import org.junit.*;

public class CommitmentTest {

	//set up example calendars
	Calendar testDueDate = new GregorianCalendar(2014, Calendar.NOVEMBER, 15, 16, 0);
	Calendar testDueDate1 = new GregorianCalendar(2015, Calendar.NOVEMBER, 15, 17, 0);
	
	//set up example commitments
	Commitment testCommitment = new Commitment(2, "thisIsATest", testDueDate);
	Commitment testCommitment1 = new Commitment(3, "thisIsAlsoATest");
	Commitment testCommitment2 = new Commitment(4, "thisIsATest", testDueDate1);
	Commitment blankCommitment = new Commitment(0, "", testDueDate);
	
	@Before
	public void setUp() throws Exception {
		//make a fake simulated network to test JSON things
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
	}	
	
	@Test
	public void testCommitment() {
		assertNotNull(testCommitment);
		assertTrue(testCommitment instanceof Commitment);
	}

	@Test
	public void testCommitmentIntStringCalendar() {
		assertNotNull(testCommitment.getId());
		assertNotNull(testCommitment.getName());
		assertNotNull(testCommitment.getDueDate());
		//NEEDS A WAY TO TEST THAT IT IS AN INT
		//assertEquals(testCommitment.getId(), Integer.class);  
		assertTrue(testCommitment.getName() instanceof String); 
		assertTrue(testCommitment.getDueDate() instanceof Calendar); 
		fail("NEEDS FIXED!");
	}
	
	@Test
	public void testCommitmentIntString() {
		assertNotNull(testCommitment1.getId());
		assertNotNull(testCommitment1.getName());
		assertNull(testCommitment1.getDueDate());
		//NEEDS A WAY TO TEST THAT IT IS AN INT
		//assertEquals(testCommitment.getId(), Integer.class);  
		assertTrue(testCommitment.getName() instanceof String); 
		fail("NEEDS FIXED!");
	}

	@Test
	public void testSetDueDate() {
		assertNotNull(testCommitment.getDueDate());
		testCommitment.setDueDate(testDueDate1);
		assertEquals(testCommitment.getDueDate(), testDueDate1);
	}

	@Test
	public void testGetId() {
		assertNotNull(testCommitment.getId());
		assertTrue(testCommitment.getId()==2);
		assertFalse(testCommitment.getId() != 2);
	}

	@Test
	public void testSetId() {
		assertNotNull(testCommitment.getId());
		testCommitment.setId(4);
		assertEquals(testCommitment.getId(), 4);
		assertNotEquals(testCommitment.getId(), 2);
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
	public void testToString() {
		assertNotNull(testCommitment.toString());
		assertTrue(testCommitment.toString() == "thisIsATest");
	}

	@Test
	public void testEqualsCommitment() {
		assertTrue(testCommitment.equalsCommitment(testCommitment2));
		assertFalse(testCommitment.equalsCommitment(testCommitment1));
	}

	@Test
	public void testCopyFrom() {
		blankCommitment.copyFrom(testCommitment);
		
		//assertFalse(testCommitment.equalsCommitment(blankCommitment));
		assertTrue(testCommitment.getName().equals(blankCommitment.getName()));
		assertTrue(testCommitment.getDueDate().equals(blankCommitment.getDueDate()));
		assertNotEquals(testCommitment.getId(), (blankCommitment.getId()));
	}
	
	@Test
	public void testToFromJson() {
		Commitment com = new Commitment(12, "jsontest");
		String jsoncom = com.toJSON();
		Commitment returned = Commitment.fromJson(jsoncom);
		assertEquals(returned.getId(), com.getId());
		assertEquals(returned.getName(), com.getName());
	}

	@Test
	public void testFromJsonArray() {
		fail("Not yet implemented");
	}


	@Test
	public void testIdentify() {
		//the identify method just returns null no matter what
		assertNull(testCommitment.identify(testCommitment));
	}


}
