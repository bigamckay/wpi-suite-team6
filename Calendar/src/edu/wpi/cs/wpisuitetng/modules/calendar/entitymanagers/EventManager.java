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

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
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
	 * Use the format "calendar/event/personal/" to get events from the user calendar
	 * Use the format "calendar/event/project/" to get events from the project calendar
	 */
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		Event[] allEvents = getAll(s);
		List<Event> results = new ArrayList<Event>();
		
		switch(args[2]){
		case "personal":
			//get all personal events for this session's user's persional calendar
			for(Event e : allEvents){
				if(e.isPersonal() && e.getOwner().equals(s.getUsername())){
					results.add(e);
				}
			}
			break;
		case "project":
			//get all nonpersonal (project) events for this session's project's calendar
			for(Event e : allEvents){
				if(!(e.isPersonal())){
					results.add(e);
				}
			}
			break;
		default:
			throw new WPISuiteException("Invalid URL");
		}
		
		return new Gson().toJson((Event[]) results.toArray());
	}
}
