/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.gui;

import javax.swing.JPanel;

/**
 * This panel fills the main content area of the tab for the calendar module.
 *
 */
public class MainView extends JPanel {

	private final CalendarEventView eventPanel;
	
	public MainView() {
		eventPanel = new CalendarEventView();
		add(eventPanel);
	}
	
}
