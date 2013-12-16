/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Seal Team 6
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.models;


/**
 * this class contains the structure and methods for commitments
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.naming.event.EventContext;
import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.AddEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.GetEventController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.RemoveEventController;

/**
 * NOTE that this is a model in the swing sense, NOT the WPISuite sense
 * This is a model for the post board. It contains all of the messages
 * to be displayed on the board. It extends AbstractListModel so that
 * it can provide the model data to the JList component in the BoardPanel.
 *
 */
@SuppressWarnings({"serial"})
public class EventListModel extends AbstractListModel {
	/**
	 * The list in which all the events for a single project are contained
	 */
	private List<Event> events;
	
	//the static object to allow the event model to be 
	private static EventListModel instance; 
	
	private boolean isSuccessfulLogin;
	private boolean isInitialized;

	/**
	 * Constructs an empty list of events for the project
	 */
	private EventListModel (){
		events = Collections.synchronizedList(new ArrayList<Event>());
		
		this.isSuccessfulLogin = false;
		this.isInitialized = false;
	}
	
	/**
	
	 * @return the instance of the event model singleton. */
	public static EventListModel getInstance()
	{
		if(instance == null)
		{
			instance = new EventListModel();			
		}
		
		return instance;
	}
	
	/**
	 * Adds a single event to the events of the project
	 * 
	 * @param newReq The event to be added to the list of events in the project
	 */
	public void addEvent(Event newReq){
		try 
		{
			AddEventController.getInstance().addEvent(newReq);
			//ViewEventController.getInstance().refreshTable();
			//ViewEventController.getInstance().refreshTree();
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**
	 * Used by Janeway to declare that we can access the server
	 */
	public void LoginSuccess(){
		this.isSuccessfulLogin = true;
		getEvents();
	}
	
	/** 
	 * Adds an event to the private events class.
	 * This should only be called by the AddEventRequestObserver
	 * as the response from the server is the event with the ID field
	 * filled in.
	 * 
	 * @param response : Event that was retrieved as a response from the server
	 */
	public void addEventFromObserver(Event response) throws WPISuiteException{
		if(response.getId() != 0){
			events.add(response);
			System.out.println("Added Event to EventListModel: " + response.getName() + ", ID: " + response.getId());
		}
		else{
			throw new WPISuiteException("Cannot add an event with ID of zero as it is not stored on the detabase.");
		}
	}
	
	
	/**
	 * Returns the Event with the given ID
	 * 
	 * @param id The ID number of the event to be returned
	
	 * @return the event for the id or null if the event is not found */
	public Event getEvent(int id)
	{
		Event temp = null;
		// iterate through list of events until id is found
		for (int i=0; i < this.events.size(); i++){
			temp = events.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	/**
	 * Sends request to the server to remove the event with the specified ID form the database.
	 * On success, the RemoveEventRequestObserver sends request to the server to update the 
	 * EventListModel by calling GetEventController.getInstance().retrieveEvents()
	 * 
	 * @param removeId The ID number of the event to be removed from the list of events in the project
	 */
	public void removeEvent(int removeId){
		RemoveEventController.getInstance().RemoveEvent(removeId);
	}
	

	/**
	 * Provides the number of elements in the list of events for the project. This
	 * function is called internally by the JList in NewEventPanel. Returns elements
	 * in reverse order, so the newest event is returned first.
	 * 
	
	
	
	 * @return the number of events in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {				
		return events.size();
	}

	/**
	 * This function takes an index and finds the event in the list of events
	 * for the project. Used internally by the JList in NewEventModel.
	 * 
	 * @param index The index of the event to be returned
	
	
	
	 * @return the event associated with the provided index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Event getElementAt(int index) {
		return events.get(events.size() - 1 - index);
	}

	/**
	 * Removes all events from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each event
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		synchronized(events){
			Iterator<Event> iterator = events.iterator();
			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		/*try{
			ViewEventController.getInstance().refreshTable();
			ViewEventController.getInstance().refreshTree();
		}
		catch (Exception e) {}*/
	}
	
	/**
	 * Adds the given array of events to the list
	 * 
	 * @param events the array of events to add
	 */
	public void addEvents(Event[] events) {
		for (int i = 0; i < events.length; i++) {
			this.events.add(events[i]);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		/*ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().refreshTree();*/
	}

	/**
	 * Returns the list of the events. If the list has not been requested from
	 * the server, send that request.
	
	 * @return the events held within the eventmodel. */
	public List<Event> getEvents() {
		if (this.isSuccessfulLogin && !this.isInitialized){
			GetEventController.getInstance().retrieveEvents();
			this.isInitialized = true;
		}
		return events;
	}
	
	/**
	 * Returns the list of requirements that are assigned to the given iteration
	 * @param name the iteration name
	 * 
	 * THIS CAN BE USEFUL LATER
	
	 * @return the list of requirements */
	/*public List<Requirement> getRequirementsForIteration(String name) {
		List<Requirement> reqForIteration = new LinkedList<Requirement>();
		
		boolean backlog = false;
		if(name.trim().length() == 0) backlog = true;
		
		for(Requirement req : requirements)
		{
			if(backlog)
			{
				if(req.getIteration().equals("Backlog") || req.getIteration().trim().equals(""))
				{
					reqForIteration.add(req);
				}
			}
			else
			{
				if(req.getIteration().equals(name))
				{
					reqForIteration.add(req);
				}
			}
		}
		
		return reqForIteration;
	}*/

}
