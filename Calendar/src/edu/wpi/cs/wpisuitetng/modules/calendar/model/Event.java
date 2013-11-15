/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rachel Wigell
 *    Craig Nesbitt
 *    Liam Dunn
 *    John French
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Event extends AbstractModel {
	public static String ID_FIELD_NAME = "id";
	private final UUID id; //ID for database storage.
	private String name; //event name
	private String location; //event location
	private String description; //event description
	private Calendar start; //when the event starts
	private Calendar end; //when the event ends
	private User creator; //person who made the event
	private Collection<User> invited; //invited people
	private Collection<User> attending; //people who are attending

	
	/*
	 * Empty constructor for creating dummy events objects because the Data object was written
	 * by some idiot who'd never heard of the Class class (passing Event.class instead of
	 * a dummy Event object). DO NOT USE for general use.
	 */
	//TODO remove this constructor if possible (see notes in EventEntityManager)
	public Event(){
		this.id = new UUID(0, 0);
	}
	
	//constructor with only required fields
	public Event(String name, String location, Calendar start, Calendar end, User creator){
		this.id = UUID.randomUUID();
		this.name = name;
		this.location = location;
		this.start = start;
		this.end = end;
		this.creator = creator;
		this.description = "";
		this.invited = new LinkedList<User>();
		this.attending = new LinkedList<User>();
	}
	
	//constructor including optional fields
	public Event(String name, String location, Calendar start, Calendar end, User creator,
			String description, Collection<User> invited, Collection<User> attending){
		this.id = UUID.randomUUID();
		this.name = name;
		this.location = location;
		this.start = start;
		this.end = end;
		this.creator = creator;
		this.description = description;
		this.invited = invited;
		this.attending = attending;
	}
	
	//getters
	
	public UUID getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Calendar getStart(){
		return this.start;
	}
	
	public Calendar getEnd(){
		return this.end;
	}
	
	public Collection<User> getInvited(){
		return this.invited;
	}
	
	public Collection<User> getAttending(){
		return this.attending;
	}
	
	//setters - returns previous value of the variable (whatever was just overwritten)
	public String setName(String to){
		String previous = this.name;
		this.name = to;
		return previous;
	}
	
	public String setLocation(String to){
		String previous = this.location;
		this.location = to;
		return previous;
	}
	
	public String setDescription(String to){
		String previous = this.description;
		this.description = to;
		return previous;
	}
	
	public Calendar setStart(Calendar to){
		Calendar previous = this.start;
		this.start = to;
		return previous;
	}
	
	public User getCreator() {
		return creator;
	}

	public Calendar setEnd(Calendar to){
		Calendar previous = this.end;
		this.end = to;
		return previous;
	}
	
	//replaces entire invited list
	public Collection<User> setInvited(Collection<User> to){
		Collection<User> previous = this.invited;
		this.invited = to;
		return previous;
	}
	
	//replaces entire attending list
	public Collection<User> setAttending(Collection<User> to){
		Collection<User> previous = this.attending;
		this.attending = to;
		return previous;
	}
	
	//appends a user to the end of the invited list
	public Collection<User> addInvited(User toAdd){
		Collection<User> previous = this.invited;
		this.invited.add(toAdd);
		return previous;
	}
	
	//removes the user at the given index from the attending list
	public Collection<User> removeInvited(int toRemove){
		Collection<User> previous = this.invited;
		this.invited.remove(toRemove);
		return previous;
	}	
	
	//appends a user to the end of the attending list
	public Collection<User> addAttending(User toAdd){
		Collection<User> previous = this.attending;
		this.attending.add(toAdd);
		return previous;
	}
		
	//removes the user at the given index from the attending list
	public Collection<User> removeAttending(int toRemove){
		Collection<User> previous = this.attending;
		this.attending.remove(toRemove);
		return previous;
	}
	
	
	
	
	/*
		private String name; //event name
	private String location; //event location
	private String description; //event description
	private Date startDate; //year/month/day that event starts
	private Date endDate; //year/month/day that event ends
	private User creator; //person who made the event
	private Collection<User> invited; //invited people
	private Collection<User> attending; //people who are attending
	*/
	


	// Validation funnctions for input
	// make sure that name is valid:
	private boolean isValidName(String name){
		// size limitation
		// restrictions on characters (ascii 32 - 126 inclusive)
		// minimum length 1
		
		
		// return 1 if valid, 0 if invalid
		return true;
	}
	
	// make sure that location is valid:
	private boolean isValidLocation(String location){
		// size limitation
		// restrictions on characters (ascii 32 - 126 inclusive)
		// no minimum length, allowed to be empty
		
		// return 1 if valid, 0 if invalid
		return true;
	}
	
	// make sure that Description is valid:
	private boolean isValidDescription(String desc){
		// size limitation (big, check with gui for size constraints)
		// restrictions on characters (ascii 32 - 126 inclusive)
		// no minimum length, allowed to be empty
		
		// return 1 if valid, 0 if invalid
		return true;
	}
	
	
	private boolean isValidDate(Calendar cal){
		// date has to exist
		// Date has to be in the present or future
		// valid day for given month
		// valid hour for given day
		/*
		 * 	try {
    			cal.setTime(yourDate);
			}
			catch (Exception e) {
  				//System.out.println("Invalid date");
  				 return false;
			}
		*/
	
		return true;
	}
	
	// Make sure that the endDate is after the StartDate
	private boolean isValidDateOrder(Calendar startDate, Calendar endDate){
		// make sure end date is after start date
		// endDate.after(startDate) should be true
		// accept combinations of dates if and only if isValidDate(startDate) && isValidDate(endDate) && isValidDateOrder(startDate, endDate)
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * JSON stuff!
	 */
	public static Event fromJson(String json) {
		return new Gson().fromJson(json, Event.class);
	}
	
	public static Event[] fromJsonArray(String json) {
		return new Gson().fromJson(json, Event[].class);
	}

	/*
	 * Here begin the methods for implementing the Model interface
	 */
	
	//We don't need to implement save or delete right now
	//TODO determine if actual implementation is needed
	@Override
	public void save() {}

	//TODO determine if actual implementation is needed
	@Override
	public void delete() {}

	//Serialize this event as JSON
	@Override
	public String toJSON() {
		return new Gson().toJson(this, Event.class);
	}

	//We can use the default implementation for now
	//TODO determine if actual implementation is needed
	@Override
	public Boolean identify(Object o) {
		return null;
	}
}
