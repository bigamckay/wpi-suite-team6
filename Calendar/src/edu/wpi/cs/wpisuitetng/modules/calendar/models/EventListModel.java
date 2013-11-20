package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddEventController;

/**
 * NOTE that this is a model in the swing sense, NOT the WPISuite sense
 * This is a model for the post board. It contains all of the messages
 * to be displayed on the board. It extends AbstractListModel so that
 * it can provide the model data to the JList component in the BoardPanel.
 * 
 * @author Eric Guleksen
 *
 */
@SuppressWarnings({"serial"})
public class EventListModel extends AbstractListModel {
	/**
	 * The list in which all the requirements for a single project are contained
	 */
	private List<Event> events;
	private int nextID; // the next available ID number for the requirements that are added.
	
	//the static object to allow the requirement model to be 
	private static EventListModel instance; 

	/**
	 * Constructs an empty list of requirements for the project
	 */
	private EventListModel (){
		events = new ArrayList<Event>();
		nextID = 0;
	}
	
	/**
	
	 * @return the instance of the requirement model singleton. */
	public static EventListModel getInstance()
	{
		if(instance == null)
		{
			instance = new EventListModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds a single requirement to the requirements of the project
	 * 
	 * @param newReq The requirement to be added to the list of requirements in the project
	 */
	public void addEvent(Event newReq){
		// add the requirement
		events.add(newReq);
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
	 * Returns the Requirement with the given ID
	 * 
	 * @param id The ID number of the requirement to be returned
	
	 * @return the requirement for the id or null if the requirement is not found */
	public Event getEvent(UUID id)
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
	 * Removes the requirement with the given ID
	 * 
	 * @param removeId The ID number of the requirement to be removed from the list of requirements in the project
	 */
	public void removeEvent(UUID removeId){
		// iterate through list of requirements until id of project is found
		for (int i=0; i < this.events.size(); i++){
			if (events.get(i).getId() == removeId){
				// remove the id
				events.remove(i);
				break;
			}
		}
		/*try {
			ViewEventController.getInstance().refreshTable();
			ViewEventController.getInstance().refreshTree();
		}
		catch(Exception e) {}*/
	}

	/**
	 * Provides the number of elements in the list of requirements for the project. This
	 * function is called internally by the JList in NewRequirementPanel. Returns elements
	 * in reverse order, so the newest requirement is returned first.
	 * 
	
	
	
	 * @return the number of requirements in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return events.size();
	}
	
	/**
	 * 
	 * Provides the next ID number that should be used for a new requirement that is created.
	 * 
	
	 * @return the next open id number */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the requirement in the list of requirements
	 * for the project. Used internally by the JList in NewRequirementModel.
	 * 
	 * @param index The index of the requirement to be returned
	
	
	
	 * @return the requirement associated with the provided index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Event getElementAt(int index) {
		return events.get(events.size() - 1 - index);
	}

	/**
	 * Removes all requirements from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each requirement
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Event> iterator = events.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		/*try{
			ViewEventController.getInstance().refreshTable();
			ViewEventController.getInstance().refreshTree();
		}
		catch (Exception e) {}*/
	}
	
	/**
	 * Adds the given array of requirements to the list
	 * 
	 * @param requirements the array of requirements to add
	 */
	public void addEvents(Event[] requirements) {
		for (int i = 0; i < requirements.length; i++) {
			this.events.add(requirements[i]);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		/*ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().refreshTree();*/
	}

	/**
	 * Returns the list of the requirements
	
	 * @return the requirements held within the requirementmodel. */
	public List<Event> getEvents() {
		return events;
	}	
	
	/**
	 * Returns the list of requirements that are assigned to the given iteration
	 * @param name the iteration name
	
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
