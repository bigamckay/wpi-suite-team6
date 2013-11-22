package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;




import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment extends AbstractCalendarModel{
	
	/** the name of the commitment */
	private String name;
		
	/** start and end date associated with the commitment */
	private Calendar duedate;
	
	private User owner;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Dummy Commitment constructor
	 * SHOULD NOT BE USED except by the CommitmentManager
	 */
	public Commitment() {
		super(true);
	}

	/**
	 * Construct a Commitment.
	 * 
	 * @param id The ID number of the Commitment
	 * @param name The name of the Commitment
	 * @param due The start of the Commitment
	 */
	public Commitment(String name, Calendar due, User owner) {
		super(false);
		this.name = name;
		if (name.trim().length() == 0)
			this.name = "Backlog";
		this.duedate = due;
		this.owner = owner;
	}
	
	/**
	 * Sets the due date for the commitment
	 * @param start start date
	 */
	public void setDueDate(Calendar due) {
		this.duedate = due;
	}

	/**
	 * getter for the name
	 * 
	
	 * @return the name */
	public String getName() {
		return name;
	}

	/**
	 * setter for the name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	

	/**
	 * @return the duedate of the commitment
	 */
	public Calendar getDueDate() {
		return duedate;
	}

	/**
	 * Encode a commitment to JSON.
	 * @return String containing the JSON encoding of the commitment
	 */
	public String toJSON() {
		return new Gson().toJson(this, Commitment.class);
	}
}
