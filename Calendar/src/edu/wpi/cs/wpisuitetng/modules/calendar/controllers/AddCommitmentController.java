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
 * server controller for adding commitments
 */
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.views.CalendarCalendarView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddCommitmentController {
		
	private AddCommitmentRequestObserver observer;
	private static AddCommitmentController instance;
	private CalendarCalendarView calView;
	
	/**
	 * Construct an AddCommitmentController for the given model, view pair
	 */
	private AddCommitmentController() {
		this.calView = null;
		observer = new AddCommitmentRequestObserver(this);
	}
	
	public static AddCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new AddCommitmentController();
		}
		
		return instance;
	}

	/**
	 * This method adds a commitment to the server.
	 * @param newCommitment is the commitment to be added to the server.
	 */
	public void addCommitment(Commitment newCommitment) 
	{
		final Request request = Network.getInstance().makeRequest("calendar/commitment/project", HttpMethod.PUT); // PUT == create
		request.setBody(newCommitment.toJSON()); // put the new commitment in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
	
	
	/** Calendar Module provides access to the AddCommitmentRequestObserver 
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
	 * @param commitment
	 */
	public void successfulAddition(){
		// tell the GUI to update the calendar
		calView.PopulateCalendarCalendarView();
	}
}
