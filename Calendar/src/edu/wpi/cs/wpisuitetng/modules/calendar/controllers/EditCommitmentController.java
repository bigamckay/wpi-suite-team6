package edu.wpi.cs.wpisuitetng.modules.calendar.controllers;


/*
 * Author: Andrew McKay
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
		final Request request = Network.getInstance().makeRequest("calander/commitment", HttpMethod.PUT); // PUT == create
		request.setBody(newCommitment.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
