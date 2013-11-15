//package edu.wpi.cs.wpisuiteng.modules.calendar.commitmentcontroller;
//
//import edu.wpi.cs.wpisuiteng.modules.requirementmanager.models.commitments.Commitment;
//import edu.wpi.cs.wpisuitetng.network.Network;
//import edu.wpi.cs.wpisuitetng.network.Request;
//import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
//
//// import the Commitment class
//
//
//public class AddCommitmentController {
//	
//	private static AddCommitmentController instance;
//	private AddCommitmentRequestObserver observer;
//	
//	/**
//	 * Construct an AddIterationController for the given model, view pair
//	
//	
//	 */
//	private AddCommitmentController() {
//		observer = new AddCommitmentRequestObserver(this);
//	}
//	
//	/**
//	
//	 * @return the instance of the AddIterationController or creates one if it does not
//	 * exist. */
//	public static AddCommitmentController getInstance()
//	{
//		if(instance == null)
//		{
//			instance = new AddCommitmentController();
//		}
//		
//		return instance;
//	}
//
//	/**
//	 * This method adds a Iteration to the server.
//	 * @param newIteration is the Iteration to be added to the server.
//	 */
//	public void addCommitment(Commitment newIteration) 
//	{
//		final Request request = Network.getInstance().makeRequest("calendar/commitment", HttpMethod.PUT); // PUT == create
//		request.setBody(newIteration.toJSON()); // put the new Iteration in the body of the request		
//		request.addObserver(observer); // add an observer to process the response
//		request.send(); 
//	}
//	
//	
//	
//	
//	
//}
