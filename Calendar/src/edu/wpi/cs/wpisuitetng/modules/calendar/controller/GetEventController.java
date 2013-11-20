/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.EventListModel;

/**
 * @author Eric Guleksen
 *
 */
public class GetEventController implements ActionListener{
	
	private GetEventRequestObserver observer;
	private static GetEventController instance;

	/**
	 * Constructs the controller given a RequirementModel
	 */
	private GetEventController() {
		
		observer = new GetEventRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the GetRequirementController or creates one if it does not
	 * exist. */
	public static GetEventController getInstance()
	{
		if(instance == null)
		{
			instance = new GetEventController();
		}
		
		return instance;
	}

	/**
	 * Sends an HTTP request to store a requirement when the
	 * update button is pressed
	 * @param e ActionEvent
	
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this requirement
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}
	
	/**
	 * Sends an HTTP request to retrieve all requirements
	 */
	public void retrieveRequirements() {
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.GET); // GET == read
		request.addObserver(observer); // add an observer to process the response
		request.send(); // send the request
	}

	/**
	 * Add the given requirements to the local model (they were received from the core).
	 * This method is called by the GetRequirementsRequestObserver
	 * 
	 * @param requirements array of requirements received from the server
	 */
	public void receivedEvents(Event[] requirements) {
		// Empty the local model to eliminate duplications

		EventListModel.getInstance().emptyModel();
		
		// Make sure the response was not null
		if (requirements != null) {
			
			// add the requirements to the local model
			EventListModel.getInstance().addEvents(requirements);
			
		}
	}

}
