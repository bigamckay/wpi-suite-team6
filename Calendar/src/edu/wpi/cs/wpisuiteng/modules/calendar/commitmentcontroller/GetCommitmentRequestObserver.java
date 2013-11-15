//package edu.wpi.cs.wpisuiteng.modules.calendar.commitmentcontroller;
//
//import edu.wpi.cs.wpisuiteng.modules.requirementmanager.models.commitments.Commitment;
//import edu.wpi.cs.wpisuitetng.network.RequestObserver;
//import edu.wpi.cs.wpisuitetng.network.models.IRequest;
//
//public class GetCommitmentRequestObserver implements RequestObserver{
//
//private GetCommitmentController controller;
//	
//	/**
//	 * Constructs the observer given a GetIterationsController
//	 * @param controller the controller used to retrieve Iterations
//	 */
//	public GetCommitmentRequestObserver(GetCommitmentController controller) {
//		this.controller = controller;
//	}
//
//	/**
//	 * Parse the Iterations out of the response body and pass them to the controller
//	 * 
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
//	 */
//	@Override
//	public void responseSuccess(IRequest iReq) {
//		// Convert the JSON array of Iterations to a Iteration object array
//		Commitment[] Commitments = Commitment.fromJsonArray(iReq.getResponse().getBody());
//		
//		// Pass these Iterations to the controller
//		controller.receivedCommitments(Commitments);
//	}
//
//	/**
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
//	 */
//	@Override
//	public void responseError(IRequest iReq) {
//		fail(iReq, null);
//	}
//
//	/**
//	 * Put an error Iteration in the PostBoardPanel if the request fails.
//	 * 
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
//	 */
//	@Override
//	public void fail(IRequest iReq, Exception exception) {
//		Commitment[] errorCommitment = { new Commitment(-1, "Error") };
//		controller.receivedCommitments(errorCommitment);
//	}
//	
//	
//	
//}
