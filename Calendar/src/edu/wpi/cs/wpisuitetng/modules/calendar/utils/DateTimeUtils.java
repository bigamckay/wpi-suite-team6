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
import java.util.GregorianCalendar;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;

/**
 * This class contains various utilities and helper functions for dealing with date and time.
 *
 */

public class DateTimeUtils {
	/**
	 * Takes in user's input for a date and a time, in string form, validates, and converts to a calendar object
	 * - throws a WPI Suite Exception with description if date or time is not in correct form or is invalid
	 * @param date the user's input for date
	 * @param time the user's input for time
	 * @return calendar object if successfully created
	 */
	public static Calendar dateTimeParser(String date, String time) throws WPISuiteException{
		//hoping to receive date input in mm/dd/yyyy form: check for correct length and placement of /'s
		int[] dateArray = new int[10];
		
		
		if(date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/'){
			throw new WPISuiteException("Date must be in form mm/dd/yyyy");
		}
		
		for(int i = 0; i < 10; i++){
			if(i != 2 && i !=5){ //ignore /'s
				dateArray[i] = (int) date.charAt(i); //cast to int to get ascii number
				if(dateArray[i] < 48 || dateArray[i] > 57){ //allow only digits
					throw new WPISuiteException("Month, day, and year must be numbers.");
				}
			}
		}
		//convert to int and place first two characters of string into month, 3rd and 4th into day, and 6-9 into year
		int year = Integer.parseInt(date.substring(6, 10));
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(3, 5));
		
		//hoping to receive time input in hh:mm form: check for correct length and placement of :
		int[] timeArray = new int[5];
		
		if(time.length() != 5 || time.charAt(2) != ':'){
			throw new WPISuiteException("Time must be in form hh:mm");
		}
		
		for(int i=0; i<5; i++){
			if(i != 2){ //ignore :
				timeArray[i] = (int) time.charAt(i); //cast to int to get ascii number
				if(timeArray[i] < 48 || timeArray[i] > 57){ //only allow digits
					throw new WPISuiteException("Hour and minute must be numbers.");
				}
			}
		}
		//convert to int and place first two characters of string into hour, last two into minute
		System.out.println("but we continued anyway");
		System.out.println(date.charAt(0));
		System.out.println(time.charAt(0));
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3, 5));
		
		Calendar dateTime = new GregorianCalendar(); //create a calendar object to hold the date and time
		dateTime.setLenient(false); //non-lenient mode will check for invalid dates such as April 31st
		dateTime = new GregorianCalendar(year, month-1, day, hour, minute); //must use month-1 because months start at 0; e.g. January = 0 but user will input 01 for January
		//Calendar constructor handles invalid dates such as April 31st by changing the user input into a valid date.
		//Therefore it is possible to check for invalid dates by comparing the date that was attempted to use
		//and the date that was actually used and seeing if they are equal.
		if(dateTime.get(Calendar.YEAR) != year || dateTime.get(Calendar.MONTH) != month-1 || dateTime.get(Calendar.DAY_OF_MONTH) != day || dateTime.get(Calendar.HOUR_OF_DAY) != hour || dateTime.get(Calendar.MINUTE) != minute){
			throw new WPISuiteException("Invalid date/time input");
		}
		return dateTime;
	}
	
	public static String reverseParser(boolean isDate, boolean isEnd){
		Calendar today = Calendar.getInstance();
		String toPrint = "";
		if(isDate){
			String month = Integer.toString(today.get(Calendar.MONTH) + 1);
			if (month.length() < 2)
				month = "0" + month;
			String day = Integer.toString(today.get(Calendar.DAY_OF_MONTH));
			if(day.length() < 2)
				day = "0" + day;
			String year = Integer.toString(today.get(Calendar.YEAR));
			toPrint = month + "/" + day + "/" + year;
		}
		else if(!isDate && !isEnd){
			String hour = Integer.toString(today.get(Calendar.HOUR_OF_DAY) + 1);
			if(hour.length() < 2)
				hour = "0" + hour;
			toPrint = hour + ":00";
		}
		else if(!isDate && isEnd){
			String hour = Integer.toString(today.get(Calendar.HOUR_OF_DAY) + 2);
			if(hour.length() < 2)
				hour = "0" + hour;
			toPrint = hour + ":00";
		}
		return toPrint;
	}
	
}
