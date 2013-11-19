/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Seal Team Six
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;

/** 
 *  Needs to be documented
 *  @author Rachel Wigell
 *  @author Craig Nesbitt
 *  
 *  Events are user-created objects. As a result, their creation must be very secure and able to handle
 *  bad input.
 */

public class Event extends AbstractModel {
	
	// character limit for name and location field
	private static final int SHORT_MAX = 40;
	// character limit for description field
	private static final int LONG_MAX = 200;
	
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
	
	//constructor for Event. Checks for invalid input and throws exception if invalid input is found
	public Event(String name, String location, Calendar start, Calendar end, User creator,
			String description, Collection<User> invited, Collection<User> attending) throws WPISuiteException{
		
		// Why is the ID random? And what do we need it for?
		this.id = UUID.randomUUID();
		
		// validate all input before assigning values
		try{
			isValidName(name);
			isValidLocation(location);
			isValidDescription(description);
			isValidDate(start);
			isValidDate(end);
			isValidDateOrder(start,end);
		} catch(WPISuiteException e){
			throw e;
		}
		
		// assign values
		this.name = name;
		this.location = location;
		this.start = start;
		this.end = end;
		this.creator = creator;
		this.description = description;
		this.invited = invited;
		this.attending = attending;
	}
	
	/*GETTERS*/
	
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
	
	// get event creator
	public User getCreator() {
		return creator;
	}
	
	/* SETTERS*/
	//setters - returns previous value of the variable (whatever was just overwritten)
	
	// Set Name
	public String setName(String to) throws WPISuiteException{
		try{
			isValidName(to);
		} catch (WPISuiteException e){
			throw e;
		}
		String previous = this.name;
		this.name = to;
		return previous;
	}
	
	// Set Location
	public String setLocation(String to) throws WPISuiteException{
		try{
			isValidLocation(to);
		} catch (WPISuiteException e){
			throw e;
		}
		
		String previous = this.location;
		this.location = to;
		return previous;
	}
	
	// Set Description
	public String setDescription(String to) throws WPISuiteException{
		try{
			isValidDescription(to);
		} catch (WPISuiteException e){
			throw e;
		}
		String previous = this.description;
		this.description = to;
		return previous;
	}
	
	// Set start date
	public Calendar setStart(Calendar to) throws WPISuiteException{
		try{
			isValidDate(to);
			isValidDateOrder(to, this.end);
		} catch (WPISuiteException e){
			throw e;
		}
		Calendar previous = this.start;
		this.start = to;
		return previous;
	}
	
	// Set end date
	public Calendar setEnd(Calendar to) throws WPISuiteException{
		try{
			isValidDate(to);
			isValidDateOrder(this.start, to);
		} catch (WPISuiteException e){
			throw e;
		}
		Calendar previous = this.end;
		this.end = to;
		return previous;
	}
	
	//replaces entire invited list
	public Collection<User> setInvited(Collection<User> to) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.invited;
		this.invited = to;
		return previous;
	}
	
	
	//replaces entire attending list
	public Collection<User> setAttending(Collection<User> to) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.attending;
		this.attending = to;
		return previous;
	}
	
	//appends a user to the end of the invited list
	public Collection<User> addInvited(User toAdd) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.invited;
		this.invited.add(toAdd);
		return previous;
	}
	
	//removes the user at the given index from the attending list
	public Collection<User> removeInvited(User toRemove) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.invited;
		this.invited.remove(toRemove);
		return previous;
	}	
	
	//appends a user to the end of the attending list
	public Collection<User> addAttending(User toAdd) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.attending;
		this.attending.add(toAdd);
		return previous;
	}
		
	//removes the user at the given index from the attending list
	public Collection<User> removeAttending(User toRemove) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.attending;
		this.attending.remove(toRemove);
		return previous;
	}
	
	
	/* VALIDATION METHODS*/
	// Checks if input is valid for the Event Class

	// Confirms that the name is valid:
	//  - cannot exceed certain length
	//  - cannot be NULL
	//  - can only contain ascii between 32 and 126 (inclusive)
	private int isValidName(String name) throws WPISuiteException{
		//restriction on field length
		if(name.length() > SHORT_MAX)
			throw new WPISuiteException("Name too long.");
		if(name.length() == 0)
			throw new WPISuiteException("Name cannot be empty."); 
		// restrictions on characters (ascii 32 - 126 inclusive)
		for(int i = 1; i<32; i++){
			String j = "" + (char) i; //cast as char to convert ascii value to character
			//then append with empty string to convert to string
			if(name.contains(j))
				throw new WPISuiteException("Name cannot contain character " + j);
		}
		String j = "" + (char) 127;
		if(name.contains(j))
			throw new WPISuiteException("Name cannot contain character " + j); //also check for ascii 127
		//if method hasn't thrown exception by this point, name should be valid. return
		return 0;
	}
	
	// Confirms that the location is valid:
	//  - cannot exceed certain length
	//  - can only contain ascii between 32 and 126 (inclusive)
	private int isValidLocation(String location) throws WPISuiteException{
		//same as name, minus nonempty requirement. location an optional field.
		if(location.length() > SHORT_MAX)
			throw new WPISuiteException("Location name too long.");
		for(int i = 1; i<32; i++){
			String j = "" + (char) i;
		if(location.contains(j))
			throw new WPISuiteException("Location name cannot contain character " + j);
		}
		String j = "" + (char) 127;
		if(location.contains(j))
			throw new WPISuiteException("Location name cannot contain character " + j);
		//if method hasn't thrown exception by this point, name location be valid. return
		return 0;
	}
	
	// Confirms that the description is valid:
	//  - cannot exceed certain length
	//  - can only contain ascii 0 and 32 - 126 inclusive
	private int isValidDescription(String desc) throws WPISuiteException{
		//same as location, but with larger character cap
		if(desc.length() > LONG_MAX)
			throw new WPISuiteException("Description too long.");
		for(int i = 1; i<32; i++){
			String j = "" + (char) i;
			if(desc.contains(j))
				throw new WPISuiteException("Description cannot contain character " + j);
		}
		String j = "" + (char) 127;
		if(desc.contains(j))
			throw new WPISuiteException("Description cannot contain character " + j);
		return 0;
	}
	
	// Confirms that date is in the future
	private int isValidDate(Calendar cal) throws WPISuiteException{
		// Date has to be in the present or future
		if(cal.compareTo(Calendar.getInstance()) < 0)
			throw new WPISuiteException("Events must occur in the future.");
		return 0;
	}
	
	// Confirms that the endDate is after the StartDate
	private int isValidDateOrder(Calendar startDate, Calendar endDate) throws WPISuiteException{
		// make sure end date is after start date
		if (startDate.after(endDate))
			throw new WPISuiteException("Events must end after they begin.");
		// accept combinations of dates if and only if isValidDate(startDate) && isValidDate(endDate) && isValidDateOrder(startDate, endDate)
		return 0;
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
