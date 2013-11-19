package edu.wpi.cs.wpisuiteng.modules.calendar.models.characteristics;

import java.util.Calendar;

public class CommitmentDate {
private Calendar date;
	
	/**
	 * Constructor for IterationDate.
	 * @param curr date
	 */
	public CommitmentDate(Calendar curr) {
		this.date = curr;
	}

	/**
	
	 *  * @return the date */
	public Calendar getDate()
	{
		return this.date;
	}
	
	/**
	 * Sets the date
	 * @param date the new date.
	 */
	public void setDate(Calendar date)
	{
		this.date = date;
	}
}
