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

package edu.wpi.cs.wpisuitetng.modules.calendar.controllers;

import static org.junit.Assert.*;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.*;

import org.junit.Test;

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
