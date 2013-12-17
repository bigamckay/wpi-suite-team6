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

package edu.wpi.cs.wpisuitetng.modules.calendar.utils;

import java.util.Calendar;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;

/**
 * This class contains isValid functions for Events and Commitments to check input.
 *
 */
public class ValidationUtils {

	// character limit for name and location field
	private static final int SHORT_MAX = 40;
	// character limit for description field
	private static final int LONG_MAX = 200;


	/**
	 *  Confirms that the name is valid:
	 *  - cannot exceed SHORT_MAX characters
	 *  - cannot be NULL
	 *  - can only contain ascii between 32 and 126 (inclusive)
	 *  - throws WPI Suite Exception with description if any of these requirements are not fulfilled
	 *  @param name the name to validate
	 *  @return 0 on success
	 */
	public static int isValidName(String name) throws WPISuiteException{
		//restriction on field length
		
		if(name.length() > SHORT_MAX)
			throw new WPISuiteException("Name too long.");
		
		if(name.length() <= 1){
			throw new WPISuiteException("Name must be longer than one character.");
		}
		
		if(name.length() == 0)
			throw new WPISuiteException("Name cannot be empty."); 
		
		// restrictions on characters (ascii 32 - 126 inclusive)
		for(int i = 1; i<32; i++){
			String j = "" + (char) i; //cast as char to convert ascii value to character
			//then append with empty string to convert to string
			if(name.contains(j))
				throw new WPISuiteException("Name cannot contain character " + j);
		}
		
		// check for the backspace (ASCII 127)
		String j = "" + (char) 127;
		if(name.contains(j))
			throw new WPISuiteException("Name cannot contain character " + j); //also check for ascii 127
		//if method hasn't thrown exception by this point, name should be valid. return
		
		// Ensure that the name contains an alphanumeric character
		boolean alphanumeric = false;
		for (int i = 0; i < name.length() && !alphanumeric; i++){
			int thisChar = (int) name.charAt(i);
			if( (thisChar >= 48 && thisChar <= 57) || (thisChar >= 65 && thisChar <= 90) || (thisChar >= 97 && thisChar <= 122)){
				alphanumeric = true;
			}
		}
		if (!alphanumeric){
			throw new WPISuiteException("Name must contain at least one alphanumeric character.");
		}
		
		if (name.equals(new String("Event Name"))){
			throw new WPISuiteException("Please assign an Event Name.");
		}
		
		return 0;
	}
	
	/**
	 *  Confirms that the location is valid:
	 *  - cannot exceed SHORT_MAX characters
	 *  - can only contain ascii between 32 and 126 (inclusive)
	 *  - CAN be empty; optional field
	 *  - throws WPI Suite Exception with description if any of these requirements is not fulfilled
	 *  @param location the string to validate
	 *  @return 0 on success
	 */
	public static int isValidLocation(String location) throws WPISuiteException{
		//same as name, minus nonempty requirement. location an optional field.
		if(location.length() > SHORT_MAX)
			throw new WPISuiteException("Location name too long.");
		for(int i = 1; i<32; i++){
			String j = "" + (char) i;
		if(location.contains(j))
			throw new WPISuiteException("Location name cannot contain character " + j);
		}
		String j = "" + (char) 127;
		if(location.contains(j))
			throw new WPISuiteException("Location name cannot contain character " + j);
		//if method hasn't thrown exception by this point, name location be valid. return
		return 0;
	}
	
	/**
	 *  Confirms that the description is valid:
	 *  - cannot exceed LONG_MAX characters
	 *	- can only contain ascii 0 and 32 - 126 inclusive
	 * 	- CAN be empty; optional field
	 * 	- throws a WPI Suite Exception with description if any of these requirements is not fulfilled
	 * @param desc the string to be validated
	 * @return 0 on success
	 */
	public static int isValidDescription(String desc) throws WPISuiteException{
		//same as location, but with larger character cap
		if(desc.length() > LONG_MAX)
			throw new WPISuiteException("Description too long.");
		for(int i = 1; i<32; i++){
			String j = "" + (char) i;
			if(desc.contains(j))
				throw new WPISuiteException("Description cannot contain character " + j);
		}
		String j = "" + (char) 127;
		if(desc.contains(j))
			throw new WPISuiteException("Description cannot contain character " + j);
		return 0;
	}
	
	/**
	 * Confirms that a date is in the future. Events should not be created in the past.
	 * - throws a WPI Suite Exception with description if invalid
	 * @param cal the calendar containing the date to be validated
	 * @return 0 on success
	 */
	public static int isValidDate(Calendar cal) throws WPISuiteException{
		// Date has to be in the present or future (no longer required)
	/*
		if(cal.compareTo(Calendar.getInstance()) < 0)
			throw new WPISuiteException("Events must occur in the future.");
	*/
		return 0;
	}
	
	/**
	 * Confirms that an event's end date is after its start date
	 * - throws a WPI Suite Exception with description if invalid
	 * @param startDate the calendar containing the time at which the event starts
	 * @param endDate the calendar containing the time at which the event ends
	 * @return 0 on success
	 */
	public static int isValidDateOrder(Calendar startDate, Calendar endDate) throws WPISuiteException{
		// make sure end date is after start date
		if (!endDate.after(startDate))
			throw new WPISuiteException("Events must end after they begin.");
		// accept combinations of dates if and only if isValidDate(startDate) && isValidDate(endDate) && isValidDateOrder(startDate, endDate)
		return 0;
	}
	
}
