package edu.wpi.cs.wpisuitetng.modules.calendar.controllers;


/*
 * Author: Andrew McKay
 */

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
		final Request request = Network.getInstance().makeRequest("calander/commitment", HttpMethod.PUT); // PUT == create
		request.setBody(newCommitment.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
