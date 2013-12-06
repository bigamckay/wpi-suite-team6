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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ToolbarView extends JSplitPane{
	
	private CalendarCalendarView calView;
	// location is the pixel value for the divider
	private int location = 300;
	private int startYear;
	private JLabel currDay;
	private JTextField searchField;
	private int currentDay;
	private String month = new String();
	private String currentFocus = new String();
	public ToolbarView() {
		
	}
	
	private void initialize() {	
		
		//initialize month and day to current month
		//calculate number of days in current month
		currentDay = Calendar.getInstance().get(Calendar.DATE);
		currentFocus = "week";
		
		calView.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            if(calView.getTitleAt(calView.getSelectedIndex()).equals("Week View"))
	            {
	            	currentFocus = "week";
	            }
	            else if(calView.getTitleAt(calView.getSelectedIndex()).equals("Year View"))
	            {
	            	currentFocus = "year";
	            }
	            else if(calView.getTitleAt(calView.getSelectedIndex()).equals("Month View"))
	            {
	            	currentFocus = "month";
	            }
	        }
	    });
		
		// Create a JPanel to hold the search side
		JPanel searchPanel = new JPanel();
		//searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.setLayout(new BorderLayout());
		//searchPanel.add(new JLabel("Calendar toolbar I edited"));
		/*searchField = new JTextField();
			searchField.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(searchField.getText().equals("What are you looking for?")){
		            			searchField.setText("");
		            	}
		        }
	
				@Override
				public void focusLost(FocusEvent e) {
					if(searchField.getText().equals(""))
	            	{
						searchField.setText("What are you looking for?");
	            	}
				}
		    });
		searchField.setText("What are you looking for?");
		searchField.setBounds(10, 10, 10, 3);
		//searchField.setVisible(false);
		searchPanel.add(searchField, BorderLayout.LINE_START);
		searchField.setColumns(20);*/		
		
		JPanel topper = new JPanel();
		topper.setPreferredSize(new Dimension(10, 35));
		JCheckBox btnTeamView = new JCheckBox("Team View");
		JCheckBox btnPersonalView = new JCheckBox("Personal View");
		btnPersonalView.setSelected(true);
		topper.add(btnTeamView, BorderLayout.CENTER);
		topper.add(btnPersonalView, BorderLayout.CENTER);
		searchPanel.add(topper, BorderLayout.NORTH);
		
		/*JPanel bottomer = new JPanel();
		bottomer.setPreferredSize(new Dimension(10,35));
		searchPanel.add(bottomer, BorderLayout.SOUTH);
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(10, 203, 112, 23);
		//btnSearch.setVisible(false);
		searchPanel.add(btnSearch, BorderLayout.EAST);*/
		
		//Navigation Panel for moving around calendars using GridBagLayout
		JPanel rightPanel = new JPanel(new GridBagLayout());
		searchPanel.setPreferredSize(new Dimension(300, 60));
		//rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel currDayPanel = new JPanel(new BorderLayout());
		//currDayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		currDay = new JLabel(getCurrentMonth(calView.currentMonth) + " " + String.valueOf(currentDay) + ", " + String.valueOf(calView.currentYear));
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
		
		btnPrevious.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String currentYearStr;
				String currentMonthStr;
				String currentDayStr;
				
				if(currentFocus == "week")
				{
					currentDay--;
					
					if(currentDay < 1)
					{
						calView.currentMonth--;
						currentDay = calView.daysInMonth(calView.currentMonth, calView.currentYear);
					}
					if(currentDay < 1 && calView.currentMonth < 0)
					{
						calView.currentMonth = 11;
						calView.currentYear--;
						currentDay = calView.daysInMonth(calView.currentMonth, calView.currentYear);
					}
					
				}
				else if(currentFocus == "month")
				{
					calView.currentMonth--;
					
					if(calView.currentMonth < 0)
					{
						calView.currentMonth = 11;
						calView.currentYear--;
					}
					calView.populateMonthNull(calView.getMonthView());
					calView.simulateYear(calView.currentYear);
					
					calView.monthLabel.setText(calView.getCurrentMonth(calView.currentMonth));
					
				}
				else if(currentFocus == "year")
				{
					calView.currentYear--;
				}				
	        	
	        	//convert integer values of days, months to strings to be displayed
	        	currentDayStr = String.valueOf(currentDay);
	        	currentYearStr = String.valueOf(calView.currentYear);
	        	currentMonthStr = getCurrentMonth(calView.currentMonth);
	        	currDay.setText(currentMonthStr + " " + currentDayStr + ", " + currentYearStr);
	        	
	        	//calView.populateYearNull();
	        	calView.populateYear(calView.monthArray, calView.currentYear);
			}
		});
		
		rightPanel.add(btnPrevious, c);
		
		JButton btnNext = new JButton(">>");
		btnNext.setBounds(10, 203, 112, 23);
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END; //right center
		c.insets = new Insets(0,0,0,100);  //right padding
		
		
		btnNext.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String currentYearStr;
				String currentMonthStr;
				String currentDayStr;
				
				if(currentFocus == "week")
				{
					currentDay++;
					
					if(currentDay > calView.daysInMonth(calView.currentMonth, calView.currentYear))
					{
						calView.currentMonth++;
						currentDay = 1;
					}
					if(currentDay > calView.daysInMonth(calView.currentMonth, calView.currentYear) && calView.currentMonth > 11)
					{
						calView.currentMonth = 0;
						calView.currentYear++;
						currentDay = 1;
					}
					
				}
				else if(currentFocus == "month")
				{
					calView.currentMonth++;
					
					if(calView.currentMonth > 11)
					{
						calView.currentMonth = 0;
						calView.currentYear++;
					}
					calView.populateMonthNull(calView.getMonthView());
					calView.simulateYear(calView.currentYear);
					
					calView.monthLabel.setText(calView.getCurrentMonth(calView.currentMonth));
				}
				else if(currentFocus == "year")
				{
					calView.currentYear++;
				}
	        	
	        	//convert integer values of days, months to strings to be displayed
	        	currentDayStr = String.valueOf(currentDay);
	        	currentYearStr = String.valueOf(calView.currentYear);
	        	currentMonthStr = getCurrentMonth(calView.currentMonth);
	        	currDay.setText(currentMonthStr + " " + currentDayStr + ", "+ currentYearStr);
	        	
	        	//calView.populateYearNull();
	        	calView.populateYear(calView.monthArray, calView.currentYear);
			}
		});
		
		rightPanel.add(btnNext, c);
		
		JButton btnCurrent = new JButton("Today");
		btnCurrent.setBounds(10, 203, 112, 23);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.insets = new Insets(0,10,0,10);  //center padding
		
		btnCurrent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String currentYearStr;
				String currentMonthStr;
				String currentDayStr;
				
				calView.currentYear = startYear;
				currentDay = Calendar.getInstance().get(Calendar.DATE);
				calView.currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	        	
	        	//convert integer values of days, months to strings to be displayed
				currentDayStr = String.valueOf(currentDay);
	        	currentYearStr = String.valueOf(calView.currentYear);
	        	currentMonthStr = getCurrentMonth(calView.currentMonth);
	        	
	        	currDay.setText(currentMonthStr + " " + currentDayStr + ", "+ currentYearStr);
	        	//calView.populateYearNull();
	        	calView.populateMonthNull(calView.getMonthView());
				calView.simulateYear(calView.currentYear);
	        	calView.populateYear(calView.monthArray, calView.currentYear);
	        	
	        	if(currentFocus == "month")
				{	
					calView.monthLabel.setText(calView.getCurrentMonth(calView.currentMonth));
				}
			}
		});
		
		rightPanel.add(btnCurrent, c);
		
		//Splitpane system for the toolbar
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(location);
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setLeftComponent(searchPanel);
		setRightComponent(rightPanel);
	}
	
	public void getCalendar(CalendarCalendarView newCal) {
		calView = newCal;
		startYear = calView.currentYear;
		initialize();
	}
	
	@Override
	public int getDividerLocation() {
		return location;
	}
	@Override
	public int getLastDividerLocation(){
		return location;
	}
	
	public String getCurrentMonth(int currMonth)
	{
		switch(currMonth)
		{
			case 0:
				month = "Jan";
				break;
			case 1:
				month = "Feb";
				break;
			case 2:
				month = "Mar";
				break;
			case 3:
				month = "Apr";
				break;
			case 4:
				month = "May";
				break;
			case 5:
				month = "Jun";
				break;
			case 6:
				month = "Jul";
				break;
			case 7:
				month = "Aug";
				break;
			case 8:
				month = "Sep";
				break;
			case 9:
				month = "Oct";
				break;
			case 10:
				month = "Nov";
				break;
			case 11:
				month = "Dec";
				break;
			case 12:
				calView.currentMonth = 0;
				month = "Jan";
				break;
			case -1:
				calView.currentMonth = 11;
				month = "Dec";
				break;
		}
		return month;
	}
}
