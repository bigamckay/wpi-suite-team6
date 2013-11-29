package edu.wpi.cs.wpisuitetng.modules.calendar.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.calendar.CalendarModule;

@SuppressWarnings("serial")
public class ToolbarView extends JSplitPane{
	
	private CalendarCalendarView calView;
	// location is the pixel value for the divider
	private int location = 300;
	private int startYear;
	private JLabel currDay;
	private JTextField searchField;
	private int currentMonth;
	private int currentDay;
	private String month = new String();
	private int daysInMonth;
	
	public ToolbarView() {
		
	}
	
	private void initialize() {	
		
		//initialize month and day to current month
		//calculate number of days in current month
		currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentDay = Calendar.getInstance().get(Calendar.DATE);
		daysInMonth = calView.daysInMonth(currentMonth, calView.currentYear);
		
		// Create a JPanel to hold the toolbar
		JPanel searchPanel = new JPanel();
		//searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.setLayout(new BorderLayout());
		//searchPanel.add(new JLabel("Calendar toolbar I edited"));
		searchField = new JTextField();
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
		currDay = new JLabel(getCurrentMonth(currentMonth) + " " + String.valueOf(currentDay) + ", " + String.valueOf(calView.currentYear));
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
				
				currentMonth--;
	        	calView.currentYear--;
	        	currentDay--;
	        	
	        	//convert integer values of days, months to strings to be displayed
	        	currentDayStr = String.valueOf(currentDay);
	        	currentYearStr = String.valueOf(calView.currentYear);
	        	currentMonthStr = getCurrentMonth(currentMonth);
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
				
				currentMonth++;
	        	calView.currentYear++;
	        	currentDay++;
	        	
	        	//convert integer values of days, months to strings to be displayed
	        	currentDayStr = String.valueOf(currentDay);
	        	currentYearStr = String.valueOf(calView.currentYear);
	        	currentMonthStr = getCurrentMonth(currentMonth);
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
				currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	        	
	        	//convert integer values of days, months to strings to be displayed
				currentDayStr = String.valueOf(currentDay);
	        	currentYearStr = String.valueOf(calView.currentYear);
	        	currentMonthStr = getCurrentMonth(currentMonth);
	        	
	        	currDay.setText(currentMonthStr + " " + currentDayStr + ", "+ currentYearStr);
	        	//calView.populateYearNull();
	        	calView.populateYear(calView.monthArray, calView.currentYear);
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
				currentMonth = 0;
				month = "Jan";
				break;
			case -1:
				currentMonth = 11;
				month = "Dec";
				break;
		}
		return month;
	}
}
