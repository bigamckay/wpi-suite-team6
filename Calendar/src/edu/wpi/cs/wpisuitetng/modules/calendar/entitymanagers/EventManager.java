/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    John French
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.entitymanagers;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;

/**
 * The entity manager for the Event in the Calendar module.
 * 
 * @author John French
 */
public class EventManager extends AbstractCalendarEntityManager<Event> {
	
	public EventManager(Data db) {
		super(db, Event.class);
	}

	@Override
	/**
	 * Get events from a specific calendar and date range
	 * Use the format "calendar/event/personal/userName/" to get events from a specific user's calendar
	 * Use the format "calendar/event/project/projectName/" to get events from a project's calendar
	 */
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		throw new NotImplementedException();
	}
}
