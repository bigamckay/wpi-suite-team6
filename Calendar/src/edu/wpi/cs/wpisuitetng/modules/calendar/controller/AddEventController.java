package edu.wpi.cs.wpisuitetng.modules.calendar.controller;


import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddEventController {
		
	private AddEventRequestObserver observer;
	
	private static AddEventController instance; 
	
	/**
	 * Construct an AddRequirementController for the given model, view pair
	 */
	private AddEventController() {
		observer = new AddEventRequestObserver(this);
	}
	
	public static AddEventController getInstance()
	{
		if(instance == null)
		{
			instance = new AddEventController();
		}
		
		return instance;
	}

	/**
	 * This method adds a requirement to the server.
	 * @param newRequirement is the requirement to be added to the server.
	 */
	public void addEvent(Event newRequirement) 
	{
		final Request request = Network.getInstance().makeRequest("calander/eventmessage", HttpMethod.PUT); // PUT == create
		request.setBody(newRequirement.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
