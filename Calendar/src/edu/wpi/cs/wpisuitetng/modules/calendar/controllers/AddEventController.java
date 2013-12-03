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


import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddEventController {
		
	private AddEventRequestObserver observer;
	
	private static AddEventController instance; 
	
	/**
	 * Construct an AddEventController for the given model, view pair
	 */
	private AddEventController() {
		observer = new AddEventRequestObserver(this);
	}
	
	public static AddEventController getInstance()
	{
		if(instance == null)
		{
			instance = new AddEventController();
		}
		
		return instance;
	}

	/**
	 * This method adds a event to the server.
	 * @param newEvent is the event to be added to the server.
	 */
	public void addEvent(Event newEvent) 
	{
		final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.PUT); // PUT == create
		request.setBody(newEvent.toJSON()); // put the new event in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
