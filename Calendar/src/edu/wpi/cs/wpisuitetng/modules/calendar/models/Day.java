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
 * this class contains the methods for having a day with a collection of events on it
 */
import java.util.Calendar;
import java.util.Collection;

public class Day {
	private Calendar date;
	private Collection<Event> events; //any events occurring on this day
	
	//constructor
	public Day(Calendar date, Collection<Event> events){
		this.date = date;
		this.events = events;
	}
	
	//getters
	public Calendar getDate(){
		return this.date;
	}
	
	//gets an entire events list
	public Collection<Event> getEvents(){
		return this.events;
	}
	
	//setters - returns previous value of the variable (whatever was just overwritten)
	public Calendar setDate(Calendar to){
		Calendar previous = this.date;
		this.date = to;
		return previous;
	}
	
	//replaces entire events list
	public Collection<Event> setEvents(Collection<Event> to){
		Collection<Event> previous = this.events;
		this.events = to;
		return previous;
	}
	
	//appends an event to the end of an events list
	public Collection<Event> addEvent(Event toAdd){
		Collection<Event> previous = this.events;
		this.events.add(toAdd);
		return previous;
	}
	
	//removes the event at the given index from an events list
	public Collection<Event> removeEvent(int toRemove){
		Collection<Event> previous = this.events;
		this.events.remove(toRemove);
		return previous;
	}
}
