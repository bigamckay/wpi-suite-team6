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

package edu.wpi.cs.wpisuitetng.modules.calendar.views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * This panel fills the main content area of the tab for the calendar module.
 *
 */
@SuppressWarnings("serial")
public class MainView extends JPanel {

	private final CalendarEventView eventPanel;
	private final CalendarTabView tabPanel;
	private final CalendarCalendarView calendarPanel;
	
	public MainView() {
		eventPanel = new CalendarEventView();
		tabPanel = new CalendarTabView();
		calendarPanel = new CalendarCalendarView();
		
		eventPanel.getCalendar(calendarPanel);
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
		LeftPanel.add(eventPanel);
		LeftPanel.add(tabPanel);
		add(LeftPanel);
		add(calendarPanel);
		
	}

	public CalendarCalendarView getCalendar() {
		return calendarPanel;
	}
	
	public CalendarTabView getTabView() {
		return tabPanel;
	}
	
}
