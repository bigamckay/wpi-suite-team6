package edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments;

import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments.*;
import static org.junit.Assert.*;

import org.junit.*;

public class CommitmentTest {

	Calendar testDueDate = new GregorianCalendar(2014, Calendar.NOVEMBER, 15, 16, 0);
	Calendar testDueDate1 = new GregorianCalendar(2015, Calendar.NOVEMBER, 15, 17, 0);
	
	Commitment testCommitment = new Commitment(2, "thisIsATest", testDueDate);
	Commitment testCommitment1 = new Commitment(3, "thisIsAlsoATest");
	Commitment testCommitment2 = new Commitment(4, "thisIsATest", testDueDate1);
	Commitment blankCommitment = new Commitment(0, "", testDueDate);
	
	
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
		assertTrue(testCommitment.getDueDate().getDate() instanceof Calendar); 
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
		assertEquals(testCommitment.getDueDate().getDate(), testDueDate1);
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
		assertTrue(testCommitment.getDueDate().getDate().equals(testDueDate));
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
	public void testFromJsonArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJSON() {
		fail("Not yet implemented");
	}

	@Test
	public void testFromJson() {
		fail("Not yet implemented");
	}

	@Test
	public void testIdentify() {
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

}
