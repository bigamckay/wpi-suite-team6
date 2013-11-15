package edu.wpi.cs.wpisuitetng.modules.calendar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.gui.CalendarGUI;

public class CalendarModule extends CalendarGUI implements IJanewayModule {
	
	List<JanewayTabModel> tabs;
	
	public CalendarModule() {
		
		tabs = new ArrayList<JanewayTabModel>();
		
		//CalendarGUI gui = new CalendarGUI();
		
		// Create a JPanel to hold the toolbar for the tab
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.add(new JLabel("Calendar toolbar placeholder")); // add a label with some placeholder text
		toolbarPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2)); // add a border so you can see the panel

		// Create a JPanel to hold the main contents of the tab
		JPanel mainPanel = new JPanel();
		mainPanel.add(new JLabel("Calendar placeholder"));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.green, 2));

		// Create a tab model that contains the toolbar panel and the main content panel
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarPanel, mainPanel);

		// Add the tab to the list of tabs owned by this module
		tabs.add(tab1);
		
		
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Calendar";
	}

	@Override
	public List<JanewayTabModel> getTabs() {
		// TODO Auto-generated method stub
		return tabs;
	}

}
