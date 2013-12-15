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
 * server controller for editing commitments
 */
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class EditCommitmentController {
	
	private static EditCommitmentController instance;
	private EditCommitmentRequestObserver observer;
	
	/**
	 * Construct an UpdateCommitmentController for the given model, view pair	
	 */
	private EditCommitmentController() {
		observer = new EditCommitmentRequestObserver(this);
	}
	
	/**	
	 * @return the instance of the UpdateCommitmentController or creates one if it does not
	 * exist. 
	 */
	public static EditCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new EditCommitmentController();
		}
		
		return instance;
	}

	/**
	 * This method updates a commitment to the server.
	 * @param newCommitment is the commitment to be updated to the server.
	 */
	public void updateCommitment(Commitment newCommitment) 
	{
		Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.POST); // POST == update
		request.setBody(newCommitment.toJSON()); // put the new commitment in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
