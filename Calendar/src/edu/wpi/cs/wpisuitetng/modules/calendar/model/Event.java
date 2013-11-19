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
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;

/** 
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
	
	/**
	 * Empty constructor for creating dummy events objects because the Data object was written
	 * by some idiot who'd never heard of the Class class (passing Event.class instead of
	 * a dummy Event object). DO NOT USE for general use.
	 */
	//TODO remove this constructor if possible (see notes in EventEntityManager)
	public Event(){
		this.id = new UUID(0, 0);
	}
	
	/**
	 * constructor for Event. Checks for invalid input and throws exception if invalid input is found
	 * @param name the name of the event, limited to SHORT_MAX characters
	 * @param location where the event is taking place, limited to SHORT_MAX characters
	 * @param start stores the date/time of the start of the event
	 * @param end stores the date/time of the end of the event
	 * @param creator the user who created the event, who should have sole rights to delete or edit it
	 * @param description a description of the event, optional field
	 * @param invited a list of users invited to the event
	 * @param attending a list of users who have committed to attending the event
	 */
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
	
	/**
	 * returns the ID field of an Event; necessary because this is a private variable
	 * @return the UUID of the event
	 */
	public UUID getId(){
		return this.id;
	}
	
	/**
	 * returns the name field of an Event; necessary because this is a private variable
	 * @return the name of the event
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * returns the location field of an Event; necessary because this is a private variable
	 * @return the location of the event
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * returns the description field of an Event; necessary because this is a private variable
	 * @return the description of the event
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * returns the start field of an Event; necessary because this is a private variable
	 * @return the start of the event
	 */
	public Calendar getStart(){
		return this.start;
	}
	
	/**
	 * returns the end field of an Event; necessary because this is a private variable
	 * @return the end of the event
	 */
	public Calendar getEnd(){
		return this.end;
	}
	
	/**
	 * returns the invited field of an Event; necessary because this is a private variable
	 * @return the invited of the event
	 */
	public Collection<User> getInvited(){
		return this.invited;
	}
	
	/**
	 * returns the attending field of an Event; necessary because this is a private variable
	 * @return the attending of the event
	 */
	public Collection<User> getAttending(){
		return this.attending;
	}
	
	/**
	 * returns the creator field of an Event; necessary because this is a private variable
	 * @return the creator of the event
	 */
	public User getCreator() {
		return creator;
	}
	
	/* SETTERS*/
	//setters - returns previous value of the variable (whatever was just overwritten)
	
	/**
	 * updates the name field of the event, necessary because this is a private variable
	 * @param to the new name for the event
	 * @return the old name of the event
	 */
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
	
	/**
	 * updates the location field of the event, necessary because this is a private variable
	 * @param to the new location for the event
	 * @return the old location of the event
	 */
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
	
	/**
	 * updates the description field of the event, necessary because this is a private variable
	 * @param to the new description for the event
	 * @return the old description of the event
	 */
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
	
	/**
	 * updates the start field of the event, necessary because this is a private variable
	 * @param to the new start for the event
	 * @return the old start of the event
	 */
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
	
	/**
	 * updates the end field of the event, necessary because this is a private variable
	 * @param to the new end for the event
	 * @return the old end of the event
	 */
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
	
	/**
	 * replaces the entire list of invited people of the event, necessary because this is a private variable
	 * @param to the new invited list for the event
	 * @return the old invited list of the event
	 */
	public Collection<User> setInvited(Collection<User> to) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.invited;
		this.invited = to;
		return previous;
	}
	
	
	/**
	 * replaces the entire list of attending people of the event, necessary because this is a private variable
	 * @param to the new attending list for the event
	 * @return the old attending list of the event
	 */
	public Collection<User> setAttending(Collection<User> to) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.attending;
		this.attending = to;
		return previous;
	}
	
	/**
	 * appends a person to the list of invited people of the event, necessary because this is a private variable
	 * @param to the addition to the invited list for the event
	 * @return the old invited list of the event
	 */
	public Collection<User> addInvited(User toAdd) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.invited;
		this.invited.add(toAdd);
		return previous;
	}
	
	/**
	 * removes the given a person from the list of invited people of the event, necessary because this is a private variable
	 * @param to the person to be removed from the invited list for the event
	 * @return the old invited list of the event
	 */
	public Collection<User> removeInvited(User toRemove) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.invited;
		this.invited.remove(toRemove);
		return previous;
	}	
	
	/**
	 * appends a person to the list of attending people of the event, necessary because this is a private variable
	 * @param to the addition to the attending list for the event
	 * @return the old attending list of the event
	 */
	public Collection<User> addAttending(User toAdd) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.attending;
		this.attending.add(toAdd);
		return previous;
	}
		
	/**
	 * removes the given a person from the list of attending people of the event, necessary because this is a private variable
	 * @param to the person to be removed from the attending list for the event
	 * @return the old attending list of the event
	 */
	public Collection<User> removeAttending(User toRemove) throws WPISuiteException{
		// exception stuffs
		Collection<User> previous = this.attending;
		this.attending.remove(toRemove);
		return previous;
	}
	
	
	/* VALIDATION METHODS*/
	// Checks if input is valid for the Event Class

	/**
	 *  Confirms that the name is valid:
	 *  - cannot exceed SHORT_MAX characters
	 *  - cannot be NULL
	 *  - can only contain ascii between 32 and 126 (inclusive)
	 *  - throws WPI Suite Exception with description if any of these requirements are not fulfilled
	 *  @param name the name to validate
	 *  @return 0 on success
	 */
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
	
	/**
	 *  Confirms that the location is valid:
	 *  - cannot exceed SHORT_MAX characters
	 *  - can only contain ascii between 32 and 126 (inclusive)
	 *  - CAN be empty; optional field
	 *  - throws WPI Suite Exception with description if any of these requirements is not fulfilled
	 *  @param location the string to validate
	 *  @return 0 on success
	 */
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
	
	/**
	 *  Confirms that the description is valid:
	 *  - cannot exceed LONG_MAX characters
	 *	- can only contain ascii 0 and 32 - 126 inclusive
	 * 	- CAN be empty; optional field
	 * 	- throws a WPI Suite Exception with description if any of these requirements is not fulfilled
	 * @param desc the string to be validated
	 * @return 0 on success
	 */
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
	
	/**
	 * Confirms that a date is in the future. Events should not be created in the past.
	 * - throws a WPI Suite Exception with description if invalid
	 * @param cal the calendar containing the date to be validated
	 * @return 0 on success
	 */
	private int isValidDate(Calendar cal) throws WPISuiteException{
		// Date has to be in the present or future
		if(cal.compareTo(Calendar.getInstance()) < 0)
			throw new WPISuiteException("Events must occur in the future.");
		return 0;
	}
	
	/**
	 * Confirms that an event's end date is after its start date
	 * - throws a WPI Suite Exception with description if invalid
	 * @param startDate the calendar containing the time at which the event starts
	 * @param endDate the calendar containing the time at which the event ends
	 * @return 0 on success
	 */
	private int isValidDateOrder(Calendar startDate, Calendar endDate) throws WPISuiteException{
		// make sure end date is after start date
		if (startDate.after(endDate))
			throw new WPISuiteException("Events must end after they begin.");
		// accept combinations of dates if and only if isValidDate(startDate) && isValidDate(endDate) && isValidDateOrder(startDate, endDate)
		return 0;
	}
	
	private Calendar dateTimeParser(String date, String time) throws WPISuiteException, IllegalArgumentException{
		if(date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/'){
			throw new WPISuiteException("Date must be in form mm/dd/yyyy");
		}
		int year = Integer.parseInt(date.substring(6, 10));
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(3, 5));
		
		if(time.length() != 5 || time.charAt(2) != ':'){
			throw new WPISuiteException("Time must be in form hh:mm");
		}		
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3, 5));
		
		Calendar dateTime = new GregorianCalendar();
		dateTime.setLenient(false);
		try{
			dateTime = new GregorianCalendar(year, month, day, hour, minute);
		}
		catch(IllegalArgumentException e){
			throw new WPISuiteException("Invalid date/time input");
		}
		return dateTime;
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
