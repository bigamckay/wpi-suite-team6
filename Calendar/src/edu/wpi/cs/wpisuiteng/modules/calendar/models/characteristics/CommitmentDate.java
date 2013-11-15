package edu.wpi.cs.wpisuiteng.modules.calendar.models.characteristics;

import java.util.Date;

public class CommitmentDate {
private Date date;
	
	/**
	 * Constructor for IterationDate.
	 * @param curr date
	 */
	public CommitmentDate(Date curr) {
		this.date = curr;
	}

	/**
	
	 *  * @return the date */
	public Date getDate()
	{
		return this.date;
	}
	
	/**
	 * Sets the date
	 * @param date the new date.
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}
}
