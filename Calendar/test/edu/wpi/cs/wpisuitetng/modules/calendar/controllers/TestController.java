/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.controllers;

import static org.junit.Assert.*;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.*;

import org.junit.Test;

/**
 * @author eric
 *
 */
public class TestController {

	@Test
	public void testAddObjectCreation() {
		AddEventController d = AddEventController.getInstance();
		assertNotNull(d);
	}
	
	@Test
	public void testEditObjectCreation() {
		EditEventController d = EditEventController.getInstance();
		assertNotNull(d);
	}
	
	@Test
	public void testGetEventController() {
		GetEventController d = GetEventController.getInstance();
		assertNotNull(d);
	}
	
	@Test
	public void testConcurrencyBetweenInstances() {
		AddEventController a = AddEventController.getInstance();
		AddEventController b = AddEventController.getInstance();
		assertEquals(a, b);
	}
}
