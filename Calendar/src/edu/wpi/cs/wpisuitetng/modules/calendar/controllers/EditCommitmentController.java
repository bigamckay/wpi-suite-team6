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
		
	private EditCommitmentRequestObserver observer;
	
	private static EditCommitmentController instance; 
	
	/**
	 * Construct an EditCommitmentController for the given model, view pair
	 */
	private EditCommitmentController() {
		observer = new EditCommitmentRequestObserver(this);
	}
	
	public static EditCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new EditCommitmentController();
		}
		
		return instance;
	}
	
	// test comment

	/**
	 * This method adds a requirement to the server.
	 * @param newRequirement is the requirement to be added to the server.
	 */
	public void editCommitment(Commitment newCommitment) 
	{
		final Request request = Network.getInstance().makeRequest("calender/commitment", HttpMethod.PUT); // PUT == create
		request.setBody(newCommitment.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
