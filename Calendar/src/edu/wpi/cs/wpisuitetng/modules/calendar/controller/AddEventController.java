package edu.wpi.cs.wpisuitetng.modules.calendar.controller;


import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEventController implements ActionListener{
		
	/**
	 * Construct an AddEventController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddEventController(PostBoardModel model, BoardPanel view) {
		this.model = model;
		this.view = view;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String message = view.getTxtNewMessage().getText();
		
		// Make sure there is text
		if (message.length() > 0) {
			// Clear the text field
			view.getTxtNewMessage().setText("");
			
			// Send a request to the core to save this message
			final Request request = Network.getInstance().makeRequest("postboard/postboardmessage", HttpMethod.PUT); // PUT == create
			request.setBody(new PostBoardMessage(message).toJSON()); // put the new message in the body of the request
			request.addObserver(new AddMessageRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}
	}

	/**
	 * When the new message is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addMessageToModel(PostBoardMessage message) {
		model.addMessage(message);
	}
}
