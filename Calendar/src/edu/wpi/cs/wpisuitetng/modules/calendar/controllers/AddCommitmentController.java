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


import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddCommitmentController {
		
	private AddCommitmentRequestObserver observer;
	
	private static AddCommitmentController instance; 
	
	/**
	 * Construct an AddCommitmentController for the given model, view pair
	 */
	private AddCommitmentController() {
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
	
	// test comment

	/**
	 * This method adds a commitment to the server.
	 * @param newCommitment is the commitment to be added to the server.
	 */
	public void addCommitment(Commitment newCommitment) 
	{
		final Request request = Network.getInstance().makeRequest("calender/commitment/project", HttpMethod.PUT); // PUT == create
		request.setBody(newCommitment.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
