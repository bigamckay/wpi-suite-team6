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

import java.util.UUID;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public abstract class AbstractCalendarModel extends AbstractModel {
	
	//Name of ID field
	public static final String OWNER_FIELD_NAME = "owner";
	public static String ID_FIELD_NAME = "id";

	//ID used for database stuff
	private final UUID id;
	
	private String owner; //the username of the owner
		
	/**
	 * Default constructor for AbstractCalendarModel
	 * Initializes id to a random UUID (unless isDummy is true, in which case id is just 0)
	 * @param isDummy true if this is a dummy object (for passing into Data methods), false if it is a real one
	 */
	public AbstractCalendarModel(String username, boolean isDummy){
		this.owner = username;
		id = isDummy?new UUID(0l,0l):UUID.randomUUID();
	}
	
	/**
	 * Gets the database ID of this object
	 * @return the database ID
	 */
	public final UUID getId(){
		return id;
	}
	
	/**
	 * gets the owner of this object; necessary because this is  private variable 
	 * @return the owner
	 */
	public final String getOwner() {
		return owner;
	}

	/**
	 * sets the owner of this project; necessary because this is a private variable
	 * @param username
	 */
	public final void setOwner(String username) {
		this.owner = username;
	}

	@Override
	/**
	 * saves the object
	 */
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * deletes the object
	 */
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	
	public static <T extends AbstractCalendarModel> T fromJSON(String jsonString, Class<T> cls){
		return new Gson().fromJson(jsonString, cls);
	}
	
	public static <T extends AbstractCalendarModel> T[] fromJSONArray(String jsonString, Class<T[]> cls){
		return new Gson().fromJson(jsonString, cls);
	}
	
	//don't override toJSON, doing that gets super messy because we don't know the class we are

	//documentation from AbstractModel is fine, so we don't need a javadoc here
	@Override
	public final Boolean identify(Object o) {
		if(o instanceof UUID){
			return o.equals(getId());
		} else if(o instanceof AbstractCalendarModel){
			return ((AbstractCalendarModel)o).getId().equals(getId());
		} else {
			return false;
		}
	}

}
