/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.controllers;

import java.util.Calendar;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * @author Andrew McKay
 *
 */
public class GetCommitmentRequestObserver implements RequestObserver{
	
	private GetCommitmentController controller;
	
	/**
	 * Constructs the observer given a GetCommitmentController
	 * @param controller the controller used to retrieve commitments
	 */
	public GetCommitmentRequestObserver(GetCommitmentController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the commitments out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Convert the JSON array of requirements to a Commitments object array
		Commitment[] commitments = Commitment.fromJSONArray(iReq.getResponse().getBody(), Commitment[].class);
		
		// Pass these Commitments to the controller
		controller.receivedCommitments(commitments);
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	/**
	 * Handle request failure.
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		Commitment[] noCommitments = { };
		controller.receivedCommitments(noCommitments);
	}

}
