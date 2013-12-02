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

package edu.wpi.cs.wpisuitetng.modules.calendar.entitymanagers;

import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;

/**
 * The entity manager for Commitments.
 * 
 */
public class CommitmentManager extends AbstractCalendarEntityManager<Commitment> {

	public CommitmentManager(Data db, Class<Commitment> tClass) {
		super(db, tClass);
		// TODO Auto-generated constructor stub
	}
	
}
