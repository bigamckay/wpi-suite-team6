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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.AddCommitmentController;

/**
 * NOTE that this is a model in the swing sense, NOT the WPISuite sense
 * This is a model for the post board. It contains all of the messages
 * to be displayed on the board. It extends AbstractListModel so that
 * it can provide the model data to the JList component in the BoardPanel.
 *
 */
@SuppressWarnings({"serial"})
public class CommitmentListModel extends AbstractListModel {
	/**
	 * The list in which all the commitments are contained
	 */
	private List<Commitment> commitments;
	private int nextID; // the next available ID number for the commitments that are added.
	
	//the static object to allow the commitment model to be 
	private static CommitmentListModel instance; 

	/**
	 * Constructs an empty list of commitments
	 */
	private CommitmentListModel (){
		commitments = new ArrayList<Commitment>();
		nextID = 0;
	}
	
	/**
	
	 * @return the instance of the commitment model singleton. */
	public static CommitmentListModel getInstance()
	{
		if(instance == null)
		{
			instance = new CommitmentListModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds a single commitment to the commitments
	 * 
	 * @param newReq The commitment to be added to the list of commitments
	 */
	public void addCommitment(Commitment newCommit){
		// add the requirement
		commitments.add(newCommit);
		try 
		{
			AddCommitmentController.getInstance().addCommitment(newCommit);
			//ViewCommitmentController.getInstance().refreshTable();
			//ViewCommitmentController.getInstance().refreshTree();
		}
		catch(Exception e)
		{
			
		}
	}
	/**
	 * Returns the Commitment with the given ID
	 * 
	 * @param id The ID number of the commitment to be returned
	
	 * @return the commitment for the id or null if the commitment is not found */
	public Commitment getCommitment(UUID id)
	{
		Commitment temp = null;
		// iterate through list of events until id is found
		for (int i=0; i < this.commitments.size(); i++){
			temp = commitments.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	/**
	 * Removes the commitment with the given ID
	 * 
	 * @param removeId The ID number of the commitment to be removed from the list of commitments
	 */
	public void remove(UUID removeId){
		// iterate through list of requirements until id of project is found
		for (int i=0; i < this.commitments.size(); i++){
			if (commitments.get(i).getId() == removeId){
				// remove the id
				commitments.remove(i);
				break;
			}
		}
		/*try {
			ViewCommitmentController.getInstance().refreshTable();
			ViewCommitmentController.getInstance().refreshTree();
		}
		catch(Exception e) {}*/
	}

	/**
	 * Provides the number of elements in the list of commitments. This
	 * function is called internally by the JList in NewRequirementPanel. Returns elements
	 * in reverse order, so the newest commitment is returned first.
	 * 
	
	
	
	 * @return the number of commitments * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return commitments.size();
	}
	
	/**
	 * 
	 * Provides the next ID number that should be used for a new commitment that is created.
	 * 
	
	 * @return the next open id number */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the commitment in the list of commitments.
	 * Used internally by the JList in NewRequirementModel.
	 * 
	 * @param index The index of the commitment to be returned
	
	
	
	 * @return the commitment associated with the provided index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Commitment getElementAt(int index) {
		return commitments.get(commitments.size() - 1 - index);
	}

	/**
	 * Removes all commitments from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each commitment
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Commitment> iterator = commitments.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		/*try{
			ViewCommitmentController.getInstance().refreshTable();
			ViewCommitmentController.getInstance().refreshTree();
		}
		catch (Exception e) {}*/
	}
	
	/**
	 * Adds the given array of commitments to the list
	 * 
	 * @param commitments the array of commitments to add
	 */
	public void addCommitments(Commitment[] commitments) {
		for (int i = 0; i < commitments.length; i++) {
			this.commitments.add(commitments[i]);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		/*ViewCommitmentController.getInstance().refreshTable();
		ViewCommitmentController.getInstance().refreshTree();*/
	}

	/**
	 * Returns the list of the commitments
	
	 * @return the commitments held within the commitmentmodel. */
	public List<Commitment> getCommitments() {
		return commitments;
	}	
	
	/**
	 * Returns the list of commitments that are assigned to the given user
	 * @param name the user name
	
	 * @return the list of commitments */
	/*public List<Requirement> getRequirementsForIteration(String name) {
		List<Requirement> reqForIteration = new LinkedList<Requirement>();
		
		boolean backlog = false;
		if(name.trim().length() == 0) backlog = true;
		
		for(Requirement req : requirements)
		{
			if(backlog)
			{
				if(req.getIteration().equals("Backlog") || req.getIteration().trim().equals(""))
				{
					reqForIteration.add(req);
				}
			}
			else
			{
				if(req.getIteration().equals(name))
				{
					reqForIteration.add(req);
				}
			}
		}
		
		return reqForIteration;
	}*/

}
