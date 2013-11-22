/**
 * 
 */
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
	
}
