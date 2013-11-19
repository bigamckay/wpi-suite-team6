package edu.wpi.cs.wpisuitetng.modules.calendar.commitmentcontroller;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments.Commitment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class UpdateCommitmentController {

	private static UpdateCommitmentController instance;
	private UpdateCommitmentRequestObserver observer;
	
	/**
	 * Construct an UpdateIterationController for the given model, view pair
	
	
	 */
	private UpdateCommitmentController() {
		observer = new UpdateCommitmentRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the UpdateIterationController or creates one if it does not
	 * exist. */
	public static UpdateCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateCommitmentController();
		}
		
		return instance;
	}

	/**
	 * This method updates a Iteration to the server.
	 * @param newIteration is the Iteration to be updated to the server.
	 */
	public void updateCommitment(Commitment newCommitment) 
	{                                                       // CHECK THISSSSSSS ???????
		Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.POST); // POST == update
		request.setBody(newCommitment.toJSON()); // put the new Iteration in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
	
}
