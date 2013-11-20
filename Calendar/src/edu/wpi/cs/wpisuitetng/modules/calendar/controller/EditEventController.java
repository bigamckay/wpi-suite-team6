package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class EditEventController {
	
	private static EditEventController instance;
	private EditEventRequestObserver observer;
	
	/**
	 * Construct an UpdateRequirementController for the given model, view pair
	
	
	 */
	private EditEventController() {
		observer = new EditEventRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the UpdateRequirementController or creates one if it does not
	 * exist. */
	public static EditEventController getInstance()
	{
		if(instance == null)
		{
			instance = new EditEventController();
		}
		
		return instance;
	}

	/**
	 * This method updates a requirement to the server.
	 * @param newRequirement is the requirement to be updated to the server.
	 */
	public void updateRequirement(Event newRequirement) 
	{
		Request request = Network.getInstance().makeRequest("requirementmanager/event", HttpMethod.POST); // POST == update
		request.setBody(newRequirement.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
