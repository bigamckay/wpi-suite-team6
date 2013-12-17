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
 * server controller for getting commitments
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CommitmentListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.views.CalendarCalendarView;

public class GetCommitmentController implements ActionListener{
	
	private GetCommitmentRequestObserver observer;
	private static GetCommitmentController instance;
	private CalendarCalendarView calView;

	/**
	 * Constructs the controller given a CommitmentModel
	 */
	private GetCommitmentController() {
		this.calView = null;
		observer = new GetCommitmentRequestObserver(this);
	}
	
	/**	
	 * @return the instance of the GetCommitmentController or creates one if it does not
	 * exist. 
	 */
	public static GetCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new GetCommitmentController();
		}
		
		return instance;
	}

	/**
	 * Sends an HTTP request to store a commitment when the
	 * update button is pressed
	 * @param e ActionEvent	
	 * @see java.awt.commitment.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		retrieveCommitments();
	}
	
	/**
	 * Sends an HTTP request to retrieve all commitments
	 */
	public void retrieveCommitments() {
		//Check this address for the database
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}

	/**
	 * Add the given commitments to the local model (they were received from the core).
	 * This method is called by the GetCommitmentsRequestObserver 
	 * @param commitments array of commitments received from the server
	 */
	public void receivedCommitments(Commitment[] commitments) {
		// Empty the local model to eliminate duplications

		CommitmentListModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (commitments != null) {
			
			// add the commitments to the local model
			CommitmentListModel.getInstance().addCommitments(commitments);
			
		}

		// tell the GUI to update the calendar
		calView.PopulateCalendarCalendarView();
		System.out.println("GetCommitmentController populated gui!?");
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
	
}
