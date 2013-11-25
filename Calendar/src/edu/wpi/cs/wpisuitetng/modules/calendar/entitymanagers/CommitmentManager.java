package edu.wpi.cs.wpisuitetng.modules.calendar.entitymanagers;

import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;

/**
 * The entity manager for Commitments.
 * 
 * @author John French
 */
public class CommitmentManager extends AbstractCalendarEntityManager<Commitment> {

	public CommitmentManager(Data db, Class<Commitment> tClass) {
		super(db, tClass);
		// TODO Auto-generated constructor stub
	}
	
}
