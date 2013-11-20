package edu.wpi.cs.wpisuitetng.modules.calendar.entitymanagers;


import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;

/**
 * The entity manager for Commitments.
 * 
 * @author John French
 */
public class CommitmentManager implements EntityManager<Commitment> {
	
	//the database
	private final Data db;
	
	/**
	 * Create a CommitmentManager which stores its commitments in a particular data source.
	 * @param db
	 */
	public CommitmentManager(Data db){
		this.db = db;
	}
	
	@Override
	public Commitment makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public Commitment[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public Commitment[] getAll(Session s) throws WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public Commitment update(Session s, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public void save(Session s, Commitment model) throws WPISuiteException {
		throw new NotImplementedException();
		
	}

	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		throw new NotImplementedException();	
	}

	@Override
	public int Count() throws WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}

	@Override
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		throw new NotImplementedException();
	}
}
