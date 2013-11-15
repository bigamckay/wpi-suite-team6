package edu.wpi.cs.wpisuiteng.modules.calendar.models.commitments;

import java.util.Calendar;

import com.google.gson.Gson;

//import edu.wpi.cs.wpisuiteng.modules.calendar.commitmentcontroller.UpdateCommitmentController;
import edu.wpi.cs.wpisuiteng.modules.calendar.models.characteristics.CommitmentDate;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class Commitment extends AbstractModel{
	/** the ID of the iteration */
	private int id; // TODO: move ID stuff to server side? (copied from
					// requirement impl.)

	/** the name of the commitment */
	private String name;
		
	/** start and end date associated with the iteration */
	private CommitmentDate duedate;

	public Commitment() {
		super();
		id = -1;
		name = "";
		duedate = null;
	}

	/**
	 * Construct an Iteration with required properties provided and others set
	 * to default
	 * 
	 * @param id
	 *            The ID number of the Commitment
	 * @param name
	 *            The name of the Commitment
	 * @param due
	 * 			  The start of the Commitment
	 */
	public Commitment(int id, String name, Calendar due) {
		this.id = id;
		this.name = name;
		if (name.trim().length() == 0)
			this.name = "Backlog";
		this.duedate = new CommitmentDate(due);
	}
	
	/**
	 * Constructor for Commitment.
	 * @param id int
	 * @param name String
	 */
	public Commitment(int id, String name) {
		this.id = id;
		this.name = name;
		if (name.trim().length() == 0)
			this.name = "Backlog";
		this.duedate = null;
	}
	
	/**
	 * Sets the due date for the commitment
	 * @param start start date
	 */
	public void setDueDate(Calendar due) {
		this.duedate = new CommitmentDate(due);
	}

	/**
	 * Getter for the id
	 * 
	
	 * @return the id */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @param name
	 *            the name to set
	 */
//	public void setName(String name) {
//		this.name = name;
//		if (name.trim().length() == 0)
//			this.name = "Backlog";
//		UpdateCommitmentController.getInstance().updateCommitment(this);
//	}

	

	/**
	
	 * @return the duedate of the iteration */
	public CommitmentDate getDueDate() {
		return duedate;
	}

	/**
	 * @param start the start of the iteration
	 */
//	public void setDueDate(CommitmentDate due) {
//		this.duedate = due;
//		UpdateCommitmentController.getInstance().updateCommitment(this);
//	}

	/**

	/**
	 * represents iteration as a string which is currently just the name
	 * 	
	 * @return the name * @see edu.wpi.cs.wpisuitetng.modules.Model#toString() * @see edu.wpi.cs.wpisuitetng.modules.Model#toString() * @see edu.wpi.cs.wpisuitetng.modules.Model#toString()
	 */
	// should this be extended for the other fields?
	public String toString() {
		return name;
	}

	/**
	 * compares two iterations for equality based on name
	 * 
	 * @param that
	 *            iteration to compare to
	
	 * @return boolean for equality */
	// should this also be extended for the other fields?
	public boolean equals(Commitment that) {
		if (this.name.equals(that.getName()))
			return true;
		else
			return false;
	}

	/**
	 * Copies all of the values from the given iteration to this iteration
	 * excluding the Id.
	 * 
	 * @param toCopyFrom
	 *            the iteration to copy from.
	 */
	public void copyFrom(Commitment toCopyFrom) {
		this.name = toCopyFrom.name;
		this.duedate = toCopyFrom.duedate;
	}

	/**
	 * Returns an array of Iterations parsed from the given JSON-encoded
	 * string.
	 * 
	 * @param body	string containing a JSON-encoded array of Iteration
	
	 * @return an array of Requirement deserialized from the given JSON string */
	public static Commitment[] fromJsonArray(String body) {
		final Gson parser = new Gson();
		return parser.fromJson(body, Commitment[].class);
	}

	/**
	 * Method toJSON.
	
	
	 * @return String * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON() * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON()
	 */
	public String toJSON() {
		return new Gson().toJson(this, Commitment.class);
	}

	/**
	 * Returns an instance of Iteration constructed using the given
	 * Iteration encoded as a JSON string.
	 * 
	 * @param body the
	 *            JSON-encoded Iteration to deserialize
	
	 * @return the Iteration contained in the given JSON */
	public static Commitment fromJson(String body) {
		final Gson parser = new Gson();
		Commitment test = parser.fromJson(body, Commitment.class);

		return test;
	}

	/**
	 * Method identify.
	 * @param o Object
	
	
	 * @return Boolean * @see edu.wpi.cs.wpisuitetng.modules.Model#identify(Object) * @see edu.wpi.cs.wpisuitetng.modules.Model#identify(Object)
	 */
	@Override
	public Boolean identify(Object o) {
		return null;
	}

	/**
	 * Method save.
	 * @see edu.wpi.cs.wpisuitetng.modules.Model#save()
	 */
	@Override
	public void save() {

	}

	/**
	 * Method delete.
	 * @see edu.wpi.cs.wpisuitetng.modules.Model#delete()
	 */
	@Override
	public void delete() {

	}
}
