package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;




import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment extends AbstractModel{
	
	public static String ID_FIELD_NAME = "id";
	private final UUID id; //ID for database storage.

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
		id = new UUID(0l, 0l);
	}

	/**
	 * Construct a Commitment.
	 * 
	 * @param id The ID number of the Commitment
	 * @param name The name of the Commitment
	 * @param due The start of the Commitment
	 */
	public Commitment(String name, Calendar due, User owner) {
		id = UUID.randomUUID();
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
	 * Getter for the id
	 * 
	
	 * @return the id */
	public UUID getId() {
		return id;
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
