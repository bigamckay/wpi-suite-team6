package edu.wpi.cs.wpisuitetng.modules.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.gui.MainView;

public class CalendarModule implements IJanewayModule {
	
	List<JanewayTabModel> tabs;
	public CalendarModule() {
		
		tabs = new ArrayList<JanewayTabModel>();
		
		//CalendarGUI gui = new CalendarGUI();
		MainView mainView = new MainView();

		
		// Create a JPanel to hold the toolbar
		JPanel searchPanel = new JPanel();
		//searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.setLayout(new BorderLayout());
		//searchPanel.add(new JLabel("Calendar toolbar I edited"));
		JTextField searchField = new JTextField();
		searchField.setText("What are you looking for?");
		searchField.setBounds(10, 10, 10, 3);
		searchPanel.add(searchField, BorderLayout.LINE_START);
		searchField.setColumns(20);
		JPanel topper = new JPanel();
		topper.setPreferredSize(new Dimension(10, 35));
		JPanel bottomer = new JPanel();
		bottomer.setPreferredSize(new Dimension(10,35));
		searchPanel.add(topper, BorderLayout.NORTH);
		searchPanel.add(bottomer, BorderLayout.SOUTH);
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(10, 203, 112, 23);
		searchPanel.add(btnSearch, BorderLayout.EAST);
		
		//Navigation Panel for moving around calendars using GridBagLayout
		JPanel rightPanel = new JPanel(new GridBagLayout());
		//rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel currDayPanel = new JPanel(new BorderLayout());
		//currDayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel currDay = new JLabel("Nov 20, 2013");
		currDayPanel.add(currDay);
		currDayPanel.add(new JLabel(), BorderLayout.SOUTH);
		currDayPanel.add(new JLabel(), BorderLayout.NORTH);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.NORTH;
		rightPanel.add(currDayPanel, c);
		
		JButton btnPrevious = new JButton("<<");
		btnPrevious.setBounds(10, 203, 112, 23);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START; //left center
		c.insets = new Insets(0,100,0,0);  //top padding
		rightPanel.add(btnPrevious, c);
		
		JButton btnNext = new JButton(">>");
		btnNext.setBounds(10, 203, 112, 23);
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END; //right center
		c.insets = new Insets(0,0,0,100);  //right padding
		rightPanel.add(btnNext, c);
		
		JButton btnCurrent = new JButton("Today");
		btnCurrent.setBounds(10, 203, 112, 23);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.insets = new Insets(0,10,0,10);  //center padding
		rightPanel.add(btnCurrent, c);
		
		//Splitpane system for the toolbar
		JSplitPane toolbarPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT){
			// location is the pixel value for the divider
			private final int location = 300;
			{
				setDividerLocation(location);
			}
			@Override
			public int getDividerLocation() {
				return location;
			}
			@Override
			public int getLastDividerLocation(){
				return location;
			}
		};
		//toolbarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		toolbarPanel.setLeftComponent(searchPanel);
		toolbarPanel.setRightComponent(rightPanel);
		
		// Create a JPanel to hold the main contents of the tab
		JPanel mainPanel = new JPanel();
		//mainPanel.add(new JLabel("YOLOSWAG"));
		mainPanel.add(mainView);
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.green, 2));

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
