package edu.wpi.cs.wpisuitetng.modules.calendar.models.commitments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.commitmentcontroller.AddCommitmentController;


public class CommitmentModel extends AbstractListModel {

	/**
	 * The list in which all the Iterations for a single project are contained
	 */
	private ArrayList<Commitment> listOfCommitments;
	private int nextID; // the next available ID number for the Iterations that
						// are added.
	
	private Commitment backlog;

	// the static object to allow the Iteration model to be
	private static CommitmentModel instance;

	/**
	 * Constructs an empty list of Iterations for the project
	 */
	private CommitmentModel() {
		backlog = null;
		listOfCommitments = new ArrayList<Commitment>();
		nextID = 0;
	}

	/**
	
	 * @return the instance of the Iteration model singleton. */
	public static CommitmentModel getInstance() {
		if (instance == null) {
			instance = new CommitmentModel();
		}

		return instance;
	}
	
	/**
	 * Sets the backlog iteration for the iteration model
	
	 * @param iter Iteration
	 */
	public void setBacklog(Commitment comit)
	{
		this.backlog = comit;
	}

	/**
	 * Adds a single Iteration to the Iterations of the project
	 * 
	
	 * @param newIter Iteration
	 */
	public void addIteration(Commitment newComit) {
		// add the Iteration
		listOfCommitments.add(newComit);
		try {
			AddCommitmentController.getInstance().addCommitment(newComit);
		} catch (Exception e) {

		}
		
		//ViewEventController.getInstance().refreshTree();
	}

	/**
	 * Provides the number of elements in the list of Iterations for the
	 * project. This function is called internally by the JList in
	 * NewIterationPanel. Returns elements in reverse order, so the newest
	 * Iteration is returned first.
	 * 
	
	
	
	 * @return the number of Iterations in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return listOfCommitments.size();
	}

	/**
	 * 
	 * Provides the next ID number that should be used for a new Iteration that
	 * is created.
	 * 
	
	 * @return the next open id number */
	public int getNextID() {

		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the Iteration in the list of
	 * Iterations for the project. Used internally by the JList in
	 * NewIterationModel.
	 * 
	 * @param index
	 *            The index of the Iteration to be returned
	
	
	
	 * @return the Iteration associated with the provided index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Commitment getElementAt(int index) {
		return listOfCommitments.get(listOfCommitments.size() - 1 - index);
	}

	/**
	 * Removes all Iterations from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of the model, because
	 * other classes in this module have references to it. Hence, we manually
	 * remove each Iteration from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Commitment> iterator = listOfCommitments.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
//		ViewEventController.getInstance().refreshTable();
//		ViewEventController.getInstance().refreshTree();
	}

	/**
	 * Adds the given array of Iterations to the list
	 * 
	 * @param Iterations
	 *            the array of Iterations to add
	 */
	public void addIterations(Commitment[] Commitments) {
		System.out.println("Got iterations.." + Commitments.length);
		for (int i = 0; i < Commitments.length; i++) {
			if(Commitments[i].getName().equals("Backlog")) backlog = Commitments[i];
			this.listOfCommitments.add(Commitments[i]);
			if (Commitments[i].getId() >= nextID) nextID = Commitments[i].getId() + 1;
		}
		
		if(backlog == null)
		{
			backlog = new Commitment(getNextID(), "Backlog");
			addIteration(backlog);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
//		ViewEventController.getInstance().refreshTree();
	}

	/**
	 * Returns the list of the Iterations
	 * 
	
	 * @return the Iterations held within the Iterationmodel. */
	public List<Commitment> getIterations() {
		return listOfCommitments;
	}
	
	/**
	 * Return the iteration with the specified name
	 * @param forName name of the iteration
	
	 * @return the iteration */
	public Commitment getIteration(String forName)
	{
		if(forName == null) return backlog;
		if(forName.equals("") || forName.equals("Backlog")) return backlog;
		for(Commitment iter : listOfCommitments)
		{
			if(iter.getName().equals(forName)) return iter;
		}
		
		return null;
	}

	/**
	 * Returns the  earliest iteration that conflicts with the given dates
	 * @param start the begin date
	 * @param end the end date
	
	 * @return the conflicting iteration */
	public Commitment getConflictingCommitment(Calendar due) {
		Commitment isValid = null;
		
		if (due == null) {
			return isValid;
		}
		
		for(Commitment commit : listOfCommitments)
		{
			if(commit == backlog) continue;
			boolean startGreaterOrEqual = (due.after(commit.getDueDate()));
			

			if(!(startGreaterOrEqual))
			{
				isValid = commit;
			}
		}
		
		return isValid;
	}

	/**
	 * Returns the iterations that the given date falls in.
	 * A date can fall within 2 iterations if it is the end of one
	 * iteration and the start of another iteration.
	 * @param date the date to check for
	
	 * @return the iterations */
	public List<Commitment> getIterationForDate(Calendar date) {
		List<Commitment> iter = new ArrayList<Commitment>();
		if(date == null) return iter;

		for(Commitment comit2 : listOfCommitments)
		{
			if(comit2 == backlog) continue;
			
			boolean startValid = comit2.getDueDate().before(date);
			
			if(startValid)
			{
				iter.add(comit2);
			}
		}
		
		return iter;
	}
	
	/**
	 * Returns the backlog iteration
	
	 * @return backlog iteration */
	public Commitment getBacklog() {
		return backlog;
	}
	
}
