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

import java.util.Calendar;
import java.util.Collection;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.calendar.utils.ValidationUtils;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;

/** 
 *  Events are user-created objects. As a result, their creation must be very secure and able to handle
 *  bad input. this class contains the structure and methods for events
 */

public class Event extends AbstractCalendarModel {

	private String name; //event name
	private String location; //event location
	private String description; //event description
	private Calendar start; //when the event starts
	private Calendar end; //when the event ends
	public static final String PERSONAL_FIELD_NAME = "personal"; //name of the 'personal' field
	private boolean personal; //is this a personal event? (or a team one)
	
	/**
	 * Dummy constructor, used for database stuff. Not to be used otherwise.
	 */
	public Event(){
		super("", true);
	}
	
	/**
	 * constructor for Event. Checks for invalid input and throws exception if invalid input is found
	 * @param name the name of the event, limited to SHORT_MAX characters
	 * @param location where the event is taking place, limited to SHORT_MAX characters
	 * @param start stores the date/time of the start of the event
	 * @param end stores the date/time of the end of the event
	 * @param owner the username of the owner of this event
	 * @param description a description of the event, optional field
	 * @param personal true for personal events, false for project-wide events
	 */
	public Event(String name, String location, Calendar start, Calendar end, String description, String owner, boolean personal) throws WPISuiteException{
		
		super(owner, false);
		
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
		this.description = description;
		this.setPersonal(personal);

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
	 * returns the boolean indicating if the event is personal or a team event; necessary because this is a private variable
	 * @return true for personal, false for team
	 */
	public boolean getPersonal(){
		return this.personal;
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
	 * returns true if the event is a personal event
	 * @return true for personal event, false for team event
	 */
	public boolean isPersonal() {
		return personal;
	}

	/**
	 * updates the personal field of the event, necessary because this is a private variable
	 * @param personal
	 */
	public void setPersonal(boolean personal) {
		this.personal = personal;
	}

	
	@Override
	/**
	 * uses json to communicate with server
	 */
	public String toJSON() {
		// TODO Auto-generated method stub
		return new Gson().toJson(this, Event.class);
	}
}
