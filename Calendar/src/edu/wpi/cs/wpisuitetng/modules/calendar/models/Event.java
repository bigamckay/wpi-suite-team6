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

package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import java.util.Calendar;
import java.util.Collection;
import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.modules.calendar.utils.ValidationUtils;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;

/** 
 *  @author Rachel Wigell
 *  @author Craig Nesbitt
 *  
 *  Events are user-created objects. As a result, their creation must be very secure and able to handle
 *  bad input.
 */

public class Event extends AbstractCalendarModel {

	private String name; //event name
	private String location; //event location
	private String description; //event description
	private Calendar start; //when the event starts
	private Calendar end; //when the event ends
	private User owner; //person who owns the event
	
	// not currently used
	private Collection<User> invited; //invited people
	private Collection<User> attending; //people who are attending
	
	/**
	 * Empty constructor for creating dummy events objects because the Data object was written
	 * by some idiot who'd never heard of the Class class (passing Event.class instead of
	 * a dummy Event object). DO NOT USE for general use.
	 */
	//TODO remove this constructor if possible (see notes in EventEntityManager)
	public Event(){
		super(true);
	}
	
	/**
	 * constructor for Event. Checks for invalid input and throws exception if invalid input is found
	 * @param name the name of the event, limited to SHORT_MAX characters
	 * @param location where the event is taking place, limited to SHORT_MAX characters
	 * @param start stores the date/time of the start of the event
	 * @param end stores the date/time of the end of the event
	 * @param owner the user who created the event, who should have sole rights to delete or edit it
	 * @param description a description of the event, optional field
	 * @param invited a list of users invited to the event
	 * @param attending a list of users who have committed to attending the event
	 */
	public Event(String name, String location, Calendar start, Calendar end, User owner,
			String description, Collection<User> invited, Collection<User> attending) throws WPISuiteException{
		
		super(false);
		
		// validate all input before assigning values
		try{
			ValidationUtils.isValidName(name);
			ValidationUtils.isValidLocation(location);
			ValidationUtils.isValidDescription(description);
			ValidationUtils.isValidDate(start);
			ValidationUtils.isValidDate(end);
			ValidationUtils.isValidDateOrder(start,end);
		} catch(WPISuiteException e){
			throw e;
		}
		
		// assign values
		this.name = name;
		this.location = location;
		this.start = start;
		this.end = end;
		this.owner = owner;
		this.description = description;
		this.invited = invited;
		this.attending = attending;
	}
	
	/*GETTERS*/
	
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
	 * returns the owner field of an Event; necessary because this is a private variable
	 * @return the owner of the event
	 */
	public User getOwner() {
		return owner;
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
			ValidationUtils.isValidName(to);
		} catch (WPISuiteException e){
			throw e;
		}
		String previous = this.name;
		this.name = to;
		return previous;
	}
	
	/**
	 * Changes the owner of this event
	 * @param owner The new owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * updates the location field of the event, necessary because this is a private variable
	 * @param to the new location for the event
	 * @return the old location of the event
	 */
	public String setLocation(String to) throws WPISuiteException{
		try{
			ValidationUtils.isValidLocation(to);
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
			ValidationUtils.isValidDescription(to);
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
			ValidationUtils.isValidDate(to);
			ValidationUtils.isValidDateOrder(to, this.end);
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
			ValidationUtils.isValidDate(to);
			ValidationUtils.isValidDateOrder(this.start, to);
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

	@Override
	public String toJSON() {
		return new Gson().toJson(this, Event.class);
	}
}
