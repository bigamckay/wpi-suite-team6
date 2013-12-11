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

/**
 * server controller for adding events
 */
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.views.CalendarCalendarView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddEventController {
		
	private AddEventRequestObserver observer;
	private static AddEventController instance;
	private CalendarCalendarView calView;
	
	/**
	 * Construct an AddEventController for the given model, view pair
	 */
	private AddEventController() {
		this.calView = null;
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
		final Request request = Network.getInstance().makeRequest("calendar/event/project", HttpMethod.PUT); // PUT == create
		request.setBody(newEvent.toJSON()); // put the new event in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
	
	
	/** Calendar Module provides access to the AddEventRequestObserver 
	 * 
	 * @param calView the CalendarCalendarView that the observer can pass its update
	 */
	public void AssignCalendarView(CalendarCalendarView calView){
		if (this.calView == null){
			this.calView = calView;
		}
	}
	
	/**
	 * Called by oObserver, sends message to CalendarCalendarView to update
	 * @param event
	 */
	public void successfulAddition(){
		// tell the GUI to update the calendar
		calView.PopulateCalendarCalendarView();
	}
}
