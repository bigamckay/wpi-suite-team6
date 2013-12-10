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

import java.util.Calendar;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;

public class GetEventRequestObserver implements RequestObserver{
	
	private GetEventController controller;
	
	/**
	 * Constructs the observer given a GetRequirementsController
	 * @param controller the controller used to retrieve requirements
	 */
	public GetEventRequestObserver(GetEventController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the requirements out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Convert the JSON array of requirements to a Requirement object array
		Event[] events = Event.fromJSONArray(iReq.getResponse().getBody(), Event[].class);
		
		// Pass these Requirements to the controller
		controller.receivedEvents(events);
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	/**
	 * handle the request failing.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		/*
		 * I modified this to send a blank list of events because there is no longer
		 * a dummy Event constructor. Hopefully this doesn't break things.
		 * (I am fairly sure it will be fine.)
		 * -John French
		 */
		//This is kind of silly, we should do something better (like print a fail statement)
		Event[] noEvents = { };
		controller.receivedEvents(noEvents);
	}

}
