package edu.wpi.cs.wpisuiteng.modules.calendar.models.commitments;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class CommitmentEntityManager implements EntityManager<Commitment> {

	/** The database */
	Data db;
	
	/**
	 * Constructs the entity manager. This constructor is called by
	 * {@link edu.wpi.cs.wpisuitetng.ManagerLayer#ManagerLayer()}. To make sure
	 * this happens, be sure to place add this entity manager to the map in
	 * the ManagerLayer file.
	 * 
	 * @param db a reference to the persistent database
	 */
	public CommitmentEntityManager(Data db) {
		this.db = db; 
	}

	/**
	 * Saves a Iteration when it is received from a client
	 * 
	
	 * @param s the session
	 * @param content an iteration that has been converted to Json
	
	
	
	 * @return the iteration passed into the method converted from Json to an iteration * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String) */
	@Override
	public Commitment makeEntity(Session s, String content) throws WPISuiteException {
		final Commitment newCommitment = Commitment.fromJson(content);
		if(!db.save(newCommitment, s.getProject())) {
			throw new WPISuiteException();
		}
		return newCommitment;
	}
	
	/**
	 * Retrieves a single Iteration from the database
	 * @param s the session
	 * @param id the id number of the Iteration to retrieve
	
	
	
	
	 * @return the Iteration matching the given id * @throws NotFoundException * @throws NotFoundException * @throws NotFoundException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String) */
	@Override
	public Commitment[] getEntity(Session s, String id) throws NotFoundException {
		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException();
		}
		Commitment[] Commitments = null;
		try {
			Commitments = db.retrieve(Commitment.class, "id", intId, s.getProject()).toArray(new Commitment[0]);
		} catch (WPISuiteException e) {
			e.printStackTrace();
		}
		if(Commitments.length < 1 || Commitments[0] == null) {
			throw new NotFoundException();
		}
		return Commitments;
	}

	/**
	 * Retrieves all Iterations from the database
	 * @param s the session
	
	
	
	 * @return array of all stored Iterations * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session)
	 */
	@Override
	public Commitment[] getAll(Session s) {
		return db.retrieveAll(new Commitment(), s.getProject()).toArray(new Commitment[0]);
	}

	/**
	 * Saves a data model to the database
	 * @param s the session
	 * @param model the model to be saved
	 */
	@Override
	public void save(Session s, Commitment model) {
		db.save(model, s.getProject());
	}
	
	/**
	 * Ensures that a user is of the specified role
	 * @param session the session
	 * @param role the role being verified
	
	 * @throws WPISuiteException user isn't authorized for the given role */
	private void ensureRole(Session session, Role role) throws WPISuiteException {
		User[] userArray = new User[2];
		User user = db.retrieve(User.class, "username", session.getUsername()).toArray(userArray)[0];
		if(!user.getRole().equals(role)) {
			throw new UnauthorizedException();
		}
	}
	
	/**
	 * Deletes a Iteration from the database
	 * @param s the session
	 * @param id the id of the Iteration to delete
	
	
	
	
	 * @return true if the deletion was successful * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String) */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		ensureRole(s, Role.ADMIN);
		return (db.delete(getEntity(s, id)[0]) != null) ? true : false;
	}
	
	/**
	 * Deletes all Iterations from the database
	 * @param s the session
	
	
	 * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session) * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session)
	 */
	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new Commitment(), s.getProject());
	}
	
	/**
	 * Returns the number of Iterations in the database
	
	
	
	
	 * @return number of Iterations stored * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count() */
	@Override
	public int Count() throws WPISuiteException {
		return db.retrieveAll(new Commitment()).size();
	}

	/**
	 * Updates the given iteration in the database
	 * @param session the session the iteration to be updated is in
	 * @param content the updated iteration as a Json string
	
	
	
	
	 * @return the old iteration prior to updating * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String) */
	@Override
	public Commitment update(Session session, String content) throws WPISuiteException {
		
		Commitment updatedCommitment = Commitment.fromJson(content);
		/*
		 * Because of the disconnected objects problem in db4o, we can't just save updatedIteration.
		 * We have to get the original iteration from db4o, copy properties from updatedIteration,
		 * then save the original iteration again.
		 */
		List<Model> oldCommitments = db.retrieve(Commitment.class, "id", updatedCommitment.getId(), session.getProject());
		if(oldCommitments.size() < 1 || oldCommitments.get(0) == null) {
			throw new BadRequestException("Iteration with ID does not exist.");
		}
				
		Commitment existingCommitment = (Commitment)oldCommitments.get(0);		

		// copy values to old Iteration and fill in our changeset appropriately
		existingCommitment.copyFrom(updatedCommitment);
		
		if(!db.save(existingCommitment, session.getProject())) {
			throw new WPISuiteException();
		}
		
		return existingCommitment;
	}

	/**
	 * Method advancedGet.
	 * @param arg0 Session
	 * @param arg1 String[]
	
	
	
	 * @return String * @throws NotImplementedException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(Session, String[]) * @throws NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(Session, String[])
	 */
	@Override
	public String advancedGet(Session arg0, String[] arg1) throws NotImplementedException {
		throw new NotImplementedException();
	}

	/**
	 * Method advancedPost.
	 * @param arg0 Session
	 * @param arg1 String
	 * @param arg2 String
	
	
	
	 * @return String * @throws NotImplementedException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(Session, String, String) * @throws NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(Session, String, String)
	 */
	@Override
	public String advancedPost(Session arg0, String arg1, String arg2) throws NotImplementedException {
		throw new NotImplementedException();
	}

	/**
	 * Method advancedPut.
	 * @param arg0 Session
	 * @param arg1 String[]
	 * @param arg2 String
	
	
	
	 * @return String * @throws NotImplementedException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String) * @throws NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String)
	 */
	@Override
	public String advancedPut(Session arg0, String[] arg1, String arg2) throws NotImplementedException {
		throw new NotImplementedException();
	}
	
	
}
