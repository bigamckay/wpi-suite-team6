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

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

/**
 * This is the entity manager for the Event in the Calendar module.
 * 
 * @author John French
 *
 */
public class EventEntityManager implements EntityManager<Event> {

	//database
	private Data db;
	
	/**
	 * Constructs the entity manager. This constructor is called by
	 * {@link edu.wpi.cs.wpisuitetng.ManagerLayer#ManagerLayer()}. To make sure
	 * this happens, be sure to place add this entity manager to the map in
	 * the ManagerLayer file.
	 * 
	 * @param db a reference to the persistent database
	 */
	public EventEntityManager(Data db) {
		this.db = db;
	}
	
	@Override
	public Event makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		
		//parse the JSON to get the event:
		final Event newEvent = Event.fromJson(content);
		
		//Store it or throw an exception if storing it fails
		if(!db.save(newEvent, s.getProject())){
			throw new WPISuiteException();
		}
		
		//return the event we just stored
		return newEvent;
	}

	@Override
	public Event[] getEntity(Session s, String id) throws NotFoundException,
			WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public Event[] getAll(Session s) throws WPISuiteException {
		//Get all events from the database.
		//TODO look into using Event.class instead of a dummy event.
		//Probably would require modifying the main WPI Suite a bit.
		List<Model> events = db.retrieveAll(new Event(), s.getProject());
		
		//return the list as an array
		return (Event[]) events.toArray();
	}

	@Override
	public Event update(Session s, String content) throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public void save(Session s, Event model) throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public int Count() throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
}

	@Override
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

	@Override
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		// TODO implement this function
		throw new WPISuiteException("This functionality is not implemented yet");
	}

}
