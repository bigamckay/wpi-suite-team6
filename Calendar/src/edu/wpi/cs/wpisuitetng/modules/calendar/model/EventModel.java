package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * This is a model for the post board. It contains all of the messages
 * to be displayed on the board. It extends AbstractListModel so that
 * it can provide the model data to the JList component in the BoardPanel.
 * 
 * @author Eric Guleksen
 *
 */
@SuppressWarnings({"serial"})
public class EventModel extends AbstractListModel {
	
	/** The list of all the events on the calendar */
	private List<EventMessage> events;
	
	/**
	 * Constructs a new board with no messages.
	 */
	public EventModel() {
		events = new ArrayList<EventMessage>();
	}

	/**
	 * Adds the given message to the Calendar
	 * 
	 * @param newMessage the new message to add
	 */
	public void addMessage(EventMessage newMessage) {
		// Add the message
		events.add(newMessage);
		
		// Notify the model that it has changed so the GUI will be updated
		this.fireIntervalAdded(this, 0, 0);
	}
	
	/**
	 * Adds the given array of Events to the Calendar
	 * 
	 * @param messages the array of messages to add
	 */
	public void addMessages(EventMessage[] messages) {
		for (int i = 0; i < messages.length; i++) {
			this.events.add(messages[i]);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
	}
	
	/**
	 * Removes all Events from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each message
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<EventMessage> iterator = events.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
	}
	
	/** 
	 * Returns the message at the given index. This method is called
	 * internally by the JList in BoardPanel. Note this method returns
	 * elements in reverse order, so newest messages are returned first.
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return events.get(events.size() - 1 - index).toString();
	}

	/**
	 * Returns the number of messages in the model. Also used internally
	 * by the JList in BoardPanel.
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return events.size();
	}

}
