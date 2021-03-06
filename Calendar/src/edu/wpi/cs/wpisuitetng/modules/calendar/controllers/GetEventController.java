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
 * server controller for getting events
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.EventListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.views.CalendarCalendarView;

public class GetEventController implements ActionListener{
	
	private GetEventRequestObserver observer;
	private static GetEventController instance;
	private CalendarCalendarView calView;

	/**
	 * Constructs the controller given a EventModel
	 */
	private GetEventController() {
		this.calView = null;
		observer = new GetEventRequestObserver(this);
	}
	
	/**	
	 * @return the instance of the GetEventController or creates one if it does not
	 * exist. 
	 */
	public static GetEventController getInstance()
	{
		if(instance == null)
		{
			instance = new GetEventController();
		}
		
		return instance;
	}

	/**
	 * Sends an HTTP request to store a event when the
	 * update button is pressed
	 * @param e ActionEvent	
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		retrieveEvents();
	}
	
	/**
	 * Sends an HTTP request to retrieve all events
	 */
	public void retrieveEvents() {
		//Check this address for the database
		final Request request = Network.getInstance().makeRequest("calendar/event", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}

	/**
	 * Add the given events to the local model (they were received from the core).
	 * This method is called by the GetEventsRequestObserver 
	 * @param events array of events received from the server
	 */
	public void receivedEvents(Event[] events) {
		// Empty the local model to eliminate duplications

		EventListModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (events != null) {
			
			// add the events to the local model
			EventListModel.getInstance().addEvents(events);
			
		}

		// tell the GUI to update the calendar
		calView.PopulateCalendarCalendarView();
		System.out.println("GetEventController populated gui!?");
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
	
}
