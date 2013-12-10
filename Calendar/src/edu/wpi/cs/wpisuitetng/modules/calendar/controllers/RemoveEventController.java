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

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


public class RemoveEventController {

	private RemoveEventRequestObserver observer;
	private static RemoveEventController instance;

	/**
	 * Constructs the controller given a EventModel
	 */
	private RemoveEventController() {
		observer = new RemoveEventRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the RemoveEventController or creates one if it does not
	 * exist. */
	public static RemoveEventController getInstance()
	{
		if(instance == null)
		{
			instance = new RemoveEventController();
		}
		
		return instance;
	}
	
	/**
	 * Sends an HTTP request to retrieve all events
	 * @param id Integer : ID of event that is used for reference in 
	 */
	public void RemoveEvent(int id) {
		//Check this address for the database
		final Request request = Network.getInstance().makeRequest("calendar/event/project/" + id, HttpMethod.DELETE);
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}
	
	

}
