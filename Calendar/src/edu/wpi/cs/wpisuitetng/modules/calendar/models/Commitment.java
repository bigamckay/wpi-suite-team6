package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import java.util.Calendar;
import java.util.Collection;
import java.util.UUID;

import com.google.gson.Gson;










import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.utils.ValidationUtils;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment extends AbstractModel{
	
	public static String ID_FIELD_NAME = "id";
	private final UUID id; //ID for database storage.
	private String name; //commitment name
	private String location; //commitment location
	private String description; //commitment description
	private Calendar duedate; //when the commitment starts
	private User owner; //person who owns the commitment
	

	
	
	
	/**
	 * Dummy Commitment constructor
	 * SHOULD NOT BE USED except by the CommitmentManager
	 */
	public Commitment() {
		id = new UUID(0l, 0l);
	}

	/**
	 * constructor for Commitment. Checks for invalid input and throws exception if invalid input is found
	 * @param name the name of the commitment, limited to SHORT_MAX characters
	 * @param location where the commitment is taking place, limited to SHORT_MAX characters
	 * @param start stores the date/time of the start of the commitment
	 * @param owner the user who created the commitment, who should have sole rights to delete or edit it
	 * @param description a description of the commitment, optional field
	 * @param invited a list of users invited to the commitment
	 * @param attending a list of users who have committed to attending the commitment
	 */
	public Commitment(String name, Calendar duedate, User owner,
			String description) throws WPISuiteException{
		
		// Why is the ID random? And what do we need it for?
		this.id = UUID.randomUUID();
		
		// validate all input before assigning values
		try{
			ValidationUtils.isValidName(name);
			ValidationUtils.isValidDescription(description);
			ValidationUtils.isValidDate(duedate);
		} catch(WPISuiteException e){
			throw e;
		}
		
		// assign values
		this.name = name;
		this.duedate = duedate;
		this.owner = owner;
		this.description = description;

	}


	/*GETTERS*/
	
	/**
	 * returns the ID field of a commitment; necessary because this is a private variable
	 * @return the UUID of the commitment
	 */
	public UUID getId(){
		return this.id;
	}
	
	/**
	 * returns the name field of a commitment; necessary because this is a private variable
	 * @return the name of the commitment
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * returns the location field of a commitment; necessary because this is a private variable
	 * @return the location of the commitment
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * returns the description field of a commitment; necessary because this is a private variable
	 * @return the description of the commitment
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * returns the start field of a commitment; necessary because this is a private variable
	 * @return the start of the commitment
	 */
	public Calendar getDueDate(){
		return this.duedate;
	}
	
	
	
	/**
	 * returns the owner field of a commitment; necessary because this is a private variable
	 * @return the owner of the commitment
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
	
	
	// needs to be further addressed
	/**
	 * Changes the owner of this event
	 * @param owner The new owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
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
	public Calendar setDueDate(Calendar to) throws WPISuiteException{
		try{
			ValidationUtils.isValidDate(to);
		} catch (WPISuiteException e){
			throw e;
		}
		Calendar previous = this.duedate;
		this.duedate = to;
		return previous;
	}
	


	
	
	

	

	/**
	 * Returns an array of Commitments parsed from the given JSON-encoded
	 * string.
	 * 
	 * @param body	string containing a JSON-encoded array of Commitments
	 * @return an array of Commitment deserialized from the given JSON string
	 */
	public static Commitment[] fromJsonArray(String body) {
		final Gson parser = new Gson();
		return parser.fromJson(body, Commitment[].class);
	}

	/**
	 * Encode a commitment to JSON.
	 * @return String containing the JSON encoding of the commitment
	 */
	public String toJSON() {
		return new Gson().toJson(this, Commitment.class);
	}

	/**
	 * Returns an instance of Commitment constructed using the given
	 * Commitment encoded as a JSON string.
	 * 
	 * @param body the JSON-encoded Iteration to deserialize
	 * @return the Commitment contained in the given JSON
	 */
	public static Commitment fromJson(String body) {
		final Gson parser = new Gson();
		Commitment test = parser.fromJson(body, Commitment.class);

		return test;
	}

	
	@Override
	public void save() {

	}
	
	@Override
	public void delete() {

	}

	@Override
	public Boolean identify(Object o) {
		if(o instanceof UUID){
			return o.equals(getId());
		} else if(o instanceof Commitment){
			return ((Commitment)o).getId().equals(getId());
		} else {
			return false;
		}
	}
}
