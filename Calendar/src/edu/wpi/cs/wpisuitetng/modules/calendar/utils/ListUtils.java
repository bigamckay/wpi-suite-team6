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

import java.util.List;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;

/**
 * This class contains as of now only a quicksort implementation
 *  to sort the list of calendar event entries on date
 * 
 */

public class ListUtils {

	public static void eventSwap (List<Event> A, int x, int y) {
		Event temp = A.get(x);
		A.set(x, A.get(y));
		A.set(y, temp);
	}
	
	// Reorganizes the given list so all elements less than the first are 
	// before it and all greater elements are after it.                   
	public static int eventDatePartition(List<Event> A, int f, int l) {
		Event pivot = A.get(f);
		while (f < l) {
			while (A.get(f).getStart().compareTo(pivot.getStart()) <= 0) f++;
			while (A.get(l).getStart().compareTo(pivot.getStart()) >= 0) l--;
			eventSwap (A, f, l);
		}
		return f;
	}
	

	 public static void eventDateQuicksort(List<Event> A, int f, int l) {
		 if (f >= l) return;
		 int pivot_index = eventDatePartition(A, f, l);
		 eventDateQuicksort(A, f, pivot_index);
		 eventDateQuicksort(A, pivot_index+1, l);
	 }
	
	/**
	 * Takes in an event list and outputs an event list sorted by date using quicksort
	 * @param eList event list given
	 */
	public static void eventByDateListSort(List<Event> eList) {
		eventDateQuicksort(eList, 0, eList.size() - 1);
		return;
	}
}
