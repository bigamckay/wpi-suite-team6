package edu.wpi.cs.wpisuitetng.modules.calendar.model;

import java.util.Date;
import java.util.Collection;
import java.util.LinkedList;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Event {
	private String name; //event name
	private String location; //event location
	private String description; //event description
	private Date startDate; //year/month/day that event starts
	private Date endDate; //year/month/day that event ends
	private User creator; //person who made the event
	private Collection<User> invited; //invited people
	private Collection<User> attending; //people who are attending

	//constructor with only required fields
	public Event(String name, String location, Date startDate, Date endDate, User creator){
		this.name = name;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creator = creator;
		this.description = "";
		this.invited = new LinkedList<User>();
		this.attending = new LinkedList<User>();
	}
	
	//constructor including optional fields
	public Event(String name, String location, Date startDate, Date endDate, User creator,
			String description, Collection<User> invited, Collection<User> attending){
		this.name = name;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creator = creator;
		this.description = description;
		this.invited = invited;
		this.attending = attending;
	}
	
	//getters
	public String getName(){
		return this.name;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Date getStartDate(){
		return this.startDate;
	}
	
	public Date getEndDate(){
		return this.endDate;
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
	
	public Date setStartDate(Date to){
		Date previous = this.startDate;
		this.startDate = to;
		return previous;
	}
	
	public User getCreator() {
		return creator;
	}

	public Date setEndDate(Date to){
		Date previous = this.endDate;
		this.endDate = to;
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
}
