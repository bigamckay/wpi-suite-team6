package edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;

//import edu.wpi.cs.wpisuiteng.modules.calendar.commitmentcontroller.UpdateCommitmentController;




import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Commitment extends AbstractModel{
	
	// character limit for name and location field
	private static final int SHORT_MAX = 40;
	
	/** the ID of the Commitment */
	private UUID id; // TODO: move ID stuff to server side? (copied from
					// requirement impl.)

	/** the name of the commitment */
	private String name;
		
	/** start and end date associated with the commitment */
	private Calendar duedate;

	private User creator;
	
	public Commitment() {
		this.id = new UUID(0,0);
	}

	/**
	 * Construct an Commitment with required properties provided and others set
	 * to default
	 * 
	 * @param id
	 *            The ID number of the Commitment
	 * @param name
	 *            The name of the Commitment
	 * @param due
	 * 			  The start of the Commitment
	 */
	public Commitment(String name, Calendar due, User creator) throws WPISuiteException{
		this.id = UUID.randomUUID();
		try{
			isValidName(name);
			isValidDate(due);
		}catch(WPISuiteException e){
			throw e;
		}
		
		this.name = name;
		this.duedate = due;
		this.creator = creator;
	}
	

	/**
	 * Getter for the id
	 * 
	
	 * @return the id */
	public UUID getId() {
		return this.id;
	}

	
	/**
	 * getter for the name
	 * 
	
	 * @return the name */
	public String getName() {
		return this.name;
	}


	/**
	
	 * @return the duedate of the commitment */
	public Calendar getDueDate() {
		return this.duedate;
	}

	public User getCreator(){
		return this.creator;
	}

//	/**
//
//	/**
//	 * represents commitment as a string which is currently just the name
//	 * 	
//	 * @return the name * @see edu.wpi.cs.wpisuitetng.modules.Model#toString() * @see edu.wpi.cs.wpisuitetng.modules.Model#toString() * @see edu.wpi.cs.wpisuitetng.modules.Model#toString()
//	 */
//	// should this be extended for the other fields?
//	public String toString() {
//		return name;
//	}
	
	public String setName(String to) throws WPISuiteException{
		try{
			isValidName(to);
		}catch(WPISuiteException e){
			throw e;
		}
		
		String previous = this.name;
		this.name = to;
		return previous;
	}
	
	
	public Calendar setDueDate(Calendar to) throws WPISuiteException{
		try{
			isValidDate(to);
		}catch (WPISuiteException e){
			throw e;
		}
		
		Calendar previous = this.duedate;
		this.duedate = to;
		return previous;
	}

	
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
	 * compares two commitment for equality based on name
	 * 
	 * @param that
	 *            commitment to compare to
	
	 * @return boolean for equality */
	// should this also be extended for the other fields?
	public boolean equalsCommitment(Commitment that) {
		if (this.name.equals(that.getName()))
			return true;
		else
			return false;
	}

	/**
	 * Copies all of the values from the given commitment to this commitment
	 * excluding the Id.
	 * 
	 * @param toCopyFrom
	 *            the commitment to copy from.
	 */
	public void copyFrom(Commitment toCopyFrom) {
		this.name = toCopyFrom.name;
		this.duedate = toCopyFrom.duedate;
		this.creator = toCopyFrom.creator;
	}

	/**
	 * Returns an array of Commitments parsed from the given JSON-encoded
	 * string.
	 * 
	 * @param body	string containing a JSON-encoded array of Commitments
	
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
	 * Returns an instance of Commitment constructed using the given
	 * Iteration encoded as a JSON string.
	 * 
	 * @param body the
	 *            JSON-encoded Iteration to deserialize
	
	 * @return the Commitment contained in the given JSON */
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
