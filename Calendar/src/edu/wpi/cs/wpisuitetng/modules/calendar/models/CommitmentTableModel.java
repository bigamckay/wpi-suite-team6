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


/**
 * this class contains the structure and methods for commitments
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controllers.RemoveCommitmentController;

/**
 * NOTE that this is a model in the swing sense, NOT the WPISuite sense
 * 
 * This is the swing model for the commitments table in CalendarTabView.
 *
 */
@SuppressWarnings({"serial"})
public class CommitmentTableModel extends AbstractTableModel {
	
	private static final int DAY_COLUMN = 0;
	private static final int NAME_COLUMN = 1;
	
	/**
	 * The list in which all the commitments for a single project are contained
	 */
	private List<Commitment> commitments;
	
	//the static object to allow the commitment model to be 
	private static CommitmentTableModel instance; 
	
	private boolean isSuccessfulLogin;
	private boolean isInitialized;

	/**
	 * Constructs an empty list of commitments for the project
	 */
	private CommitmentTableModel (){
		commitments = Collections.synchronizedList(new ArrayList<Commitment>());
		
		this.isSuccessfulLogin = false;
		this.isInitialized = false;
	}
	
	/**
	
	 * @return the instance of the commitment model singleton. */
	public static CommitmentTableModel getInstance()
	{
		if(instance == null)
		{
			instance = new CommitmentTableModel();			
		}
		
		return instance;
	}
	
	/**
	 * Adds a single commitment to the commitments of the project
	 * 
	 * @param newReq The commitment to be added to the list of commitments in the project
	 */
	public void addCommitment(Commitment newReq){
		try 
		{
			AddCommitmentController.getInstance().addCommitment(newReq);
			//ViewCommitmentController.getInstance().refreshTable();
			//ViewCommitmentController.getInstance().refreshTree();
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**
	 * Used by Janeway to declare that we can access the server
	 */
	public void LoginSuccess(){
		this.isSuccessfulLogin = true;
		getCommitments();
	}
	
	/** 
	 * Adds an commitment to the private commitments class.
	 * This should only be called by the AddCommitmentRequestObserver
	 * as the response from the server is the commitment with the ID field
	 * filled in.
	 * 
	 * @param response : Commitment that was retrieved as a response from the server
	 */
	public void addCommitmentFromObserver(Commitment response) throws WPISuiteException{
		if(response.getId() != 0){
			commitments.add(response);
			System.out.println("Added Commitment to CommitmentListModel: " + response.getName() + ", ID: " + response.getId());
		}
		else{
			throw new WPISuiteException("Cannot add an commitment with ID of zero as it is not stored on the detabase.");
		}
	}
	
	
	/**
	 * Returns the Commitment with the given ID
	 * 
	 * @param id The ID number of the commitment to be returned
	
	 * @return the commitment for the id or null if the commitment is not found */
	public Commitment getCommitment(int id)
	{
		Commitment temp = null;
		// iterate through list of commitments until id is found
		for (int i=0; i < this.commitments.size(); i++){
			temp = commitments.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	/**
	 * Sends request to the server to remove the commitment with the specified ID form the database.
	 * On success, the RemoveCommitmentRequestObserver sends request to the server to update the 
	 * CommitmentListModel by calling GetCommitmentController.getInstance().retrieveCommitments()
	 * 
	 * @param removeId The ID number of the commitment to be removed from the list of commitments in the project
	 */
	public void removeCommitment(int removeId){
		RemoveCommitmentController.getInstance().RemoveCommitment(removeId);
	}
	

	/**
	 * Provides the number of elements in the list of commitments for the project. This
	 * function is called internally by the JList in NewCommitmentPanel. Returns elements
	 * in reverse order, so the newest commitment is returned first.
	 * 
	
	
	
	 * @return the number of commitments in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {				
		return commitments.size();
	}

	/**
	 * This function takes an index and finds the commitment in the list of commitments
	 * for the project. Used internally by the JList in NewCommitmentModel.
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
		this.fireTableRowsDeleted(0, oldSize);
		/*try{
			ViewCommitmentController.getInstance().refreshTable();
			ViewCommitmentController.getInstance().refreshTree();
		}
		catch (Exception e) {}*/
	}
	
	
	/**
	 * 'Updates' the Commitment with the ID matching the updatedCommitment
	 * Should only be called by EditCommitmentController
	 * 
	 * @param updatedCommitment commitment from EditCommitmentController
	
	 * @return the commitment for the id or null if the commitment is not found */
	public void editCommitment(Commitment updatedCommitment)
	{
		Commitment indexedCommitment = null;
		// iterate through list of commitments until id is found
		for (int i=0; i < this.commitments.size(); i++){
			indexedCommitment = commitments.get(i);
			if (indexedCommitment.getId() == updatedCommitment.getId()){
				this.commitments.remove(i);
				this.commitments.add(updatedCommitment);
			}
		}
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
		this.fireTableRowsInserted(0, Math.max(getSize() - 1, 0));
		/*ViewCommitmentController.getInstance().refreshTable();
		ViewCommitmentController.getInstance().refreshTree();*/
	}

	/**
	 * Returns the list of the commitments. If the list has not been requested from
	 * the server, send that request.
	
	 * @return the commitments held within the commitmentmodel. */
	public List<Commitment> getCommitments() {
		if (this.isSuccessfulLogin && !this.isInitialized){
			GetCommitmentController.getInstance().retrieveCommitments();
			this.isInitialized = true;
		}
		return commitments;
	}

	@Override
	public int getRowCount() {
		return commitments.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case DAY_COLUMN:
			return commitments.get(rowIndex).getDueDate();
		case NAME_COLUMN:
			return commitments.get(rowIndex).getName();
		default:
			return null;
		}
	}
	
	/**
	 * Returns the list of requirements that are assigned to the given iteration
	 * @param name the iteration name
	 * 
	 * THIS CAN BE USEFUL LATER
	
	 * @return the list of requirements */
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
