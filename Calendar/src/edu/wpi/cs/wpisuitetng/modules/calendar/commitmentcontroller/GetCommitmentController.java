package edu.wpi.cs.wpisuitetng.modules.calendar.commitmentcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments.CommitmentModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class GetCommitmentController implements ActionListener{

	
	private GetCommitmentRequestObserver observer;
	private static GetCommitmentController instance;

	/**
	 * Constructs the controller given a IterationModel
	 */
	private GetCommitmentController() {
		
		observer = new GetCommitmentRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the GetIterationController or creates one if it does not
	 * exist. */
	public static GetCommitmentController getInstance()
	{
		if(instance == null)
		{
			instance = new GetCommitmentController();
		}
		
		return instance;
	}

	/**
	 * Sends an HTTP request to store a Iteration when the
	 * update button is pressed
	 * @param e ActionEvent
	
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this Iteration
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}
	
	/**
	 * Sends an HTTP request to retrieve all Iterations
	 */
	public void retrieveCommitments() {
		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}

	/**
	 * Add the given Iterations to the local model (they were received from the core).
	 * This method is called by the GetIterationsRequestObserver
	 * 
	 * @param Iterations array of Iterations received from the server
	 */
	public void receivedCommitments(Commitment[] Commitments) {
		// Make sure the response was not null
		if (Commitments != null) 
		{	
			// add the Iterations to the local model
			CommitmentModel.getInstance().addIterations(Commitments);
		}
	}
	
	
}
