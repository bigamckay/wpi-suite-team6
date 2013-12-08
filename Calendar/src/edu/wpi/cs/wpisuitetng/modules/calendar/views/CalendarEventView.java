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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.janeway.Janeway;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.EventListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.utils.DateTimeUtils;

/**
 * Contains the GUI elements of the Event Panel
 *
 */

@SuppressWarnings("serial")
public class CalendarEventView extends JTabbedPane {
	
	private JTextField eventDescription;
	private JTextField eventLocation;
	private JTextField startTime;
	private JTextField startDay;
	private JTextField endTime;
	private JTextField endDay;
	private JTextField eventName;
	private JTextField dueDate;
	private JTextField commitmentName;
	private JLabel eventFeedbackLabel;
	
	private JPanel eventPane;
	private JPanel commitmentPane;
	private boolean eventTypeSwitch = true;  // true = personal, false = team
	private JRadioButton radioBtnEventPersonal;
	private JRadioButton radioBtnEventTeam;
	
	private boolean commitTypeSwitch = true;  // true = personal, false = team
	private JRadioButton radioBtnCommitPersonal;
	private JRadioButton radioBtnCommitTeam;
	
	private CalendarCalendarView calView;
	
	public CalendarEventView() {
		
	}
	
	private void initialize() {
		
		
		setPreferredSize(new Dimension(292,295));
		setLocation(5, 0);
		//setBounds(5, 86, 292, 234);
		//setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		//setLayout(null);
		
		ImageIcon homeIcon = new ImageIcon(Janeway.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif"));
		
		JPanel defaultPane = new JPanel();
		defaultPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addTab("", homeIcon, defaultPane, null);
		defaultPane.setLayout(null);
		
		JButton createEvent = new JButton("Create Event");
		createEvent.setBounds(64, 40/*size().height/2 - (size().height/2)/2 - 36*/, 170, 36);
		defaultPane.add(createEvent);
		
		
		createEvent.addActionListener(
			new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(indexOfTab("Create Commitment") == 1)
						removeTabAt(1);
					addTab("Create Event", null, eventPane, null);
					eventPane.setLayout(null);
					setSelectedIndex(1);
				}
				
			});
		
		JButton createCommitment = new JButton("Create Commitment");
		createCommitment.setBounds(64, 195/*size().height/2 + (size().height/2)/2 - 36*/, 170, 36);
		defaultPane.add(createCommitment);
		createCommitment.setEnabled(false);
		
		createCommitment.addActionListener(
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(indexOfTab("Create Event") == 1)
							removeTabAt(1);
						addTab("Create Commitment", null, commitmentPane, null);
						commitmentPane.setLayout(null);
						setSelectedIndex(1);
					}
					
				});
		
		// Event Tab
		
		eventPane = new JPanel();
		//addTab("Create Event", null, eventPane, null);
		//eventPane.setLayout(null);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.setBounds(10, 203, 112, 23);
		btnCreateEvent.setEnabled(true);  
		eventPane.add(btnCreateEvent);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setBounds(170, 203, 112, 23);
		btnDeleteEvent.setVisible(false);
		btnDeleteEvent.setEnabled(false);  
		eventPane.add(btnDeleteEvent);
		
		//Make a new Calendar with the current date and time
		Calendar localTime = Calendar.getInstance();
		localTime.getTime();
		System.out.println(localTime.toString());
		
		
		//Event Description Text Boxes
		eventDescription = new JTextField();
		eventDescription.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	            	if(eventDescription.getText().equals("Description...")){
	            		eventDescription.setText("");
	            	}
	        }

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(eventDescription.getText().equals(""))
            	{
            		eventDescription.setText("Description...");
            	}
				
			}
	    });
		eventDescription.setText("Description...");
		eventDescription.setBounds(10, 145, 272, 52);
		eventPane.add(eventDescription);
		eventDescription.setColumns(10);
		//
		
		//Location Text Field Boxes
		eventLocation = new JTextField();
		eventLocation.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	            	if(eventLocation.getText().equals("Location Location Location")){
	            			eventLocation.setText("");
	            	}
	        }

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(eventLocation.getText().equals(""))
            	{
					eventLocation.setText("Location Location Location");
            	}
			}
	    });
		eventLocation.setHorizontalAlignment(SwingConstants.CENTER);
		eventLocation.setText("Location Location Location");
		eventLocation.setBounds(10, 118, 272, 20);
		eventPane.add(eventLocation);
		eventLocation.setColumns(10);
		//
		
		//Start Time Text Field Boxes
		startTime = new JTextField();
		   startTime.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(startTime.getText().equals("hh:mm")){
		            			startTime.setText("");
		            	}
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(startTime.getText().equals(""))
	            	{
						startTime.setText("hh:mm");
	            	}
				}
		    });
		startTime.setHorizontalAlignment(SwingConstants.CENTER);
		startTime.setText("hh:mm");
		startTime.setBounds(10, 87, 80, 20);
		eventPane.add(startTime);
		startTime.setColumns(10);
		//

		//Start Day Text Field Boxes
		startDay = new JTextField();
		 startDay.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(startDay.getText().equals("mm/dd/yyyy")){
		            			startDay.setText("");
		            	}
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(startDay.getText().equals(""))
	            	{
						startDay.setText("mm/dd/yyyy");
	            	}
				}
		    });
		startDay.setHorizontalAlignment(SwingConstants.CENTER);
		startDay.setText("mm/dd/yyyy");
		startDay.setBounds(10, 56, 80, 20);
		eventPane.add(startDay);
		startDay.setColumns(10);
		//
		
		//End Time Text Field Boxes
		endTime = new JTextField();
		 endTime.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(endTime.getText().equals("hh:mm")){
		            			endTime.setText("");
		            	}
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(endTime.getText().equals(""))
	            	{
						endTime.setText("hh:mm");
	            	}
				}
		    });
		endTime.setHorizontalAlignment(SwingConstants.CENTER);
		endTime.setText("hh:mm");
		endTime.setBounds(102, 87, 80, 20);
		eventPane.add(endTime);
		endTime.setColumns(10);
		//
		
		//End Day Text Field Boxes
		endDay = new JTextField();
		 endDay.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(endDay.getText().equals("mm/dd/yyyy")){
		            			endDay.setText("");
		            	}
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(endDay.getText().equals(""))
	            	{
						endDay.setText("mm/dd/yyyy");
	            	}
				}
		    });
		endDay.setHorizontalAlignment(SwingConstants.CENTER);
		endDay.setText("mm/dd/yyyy");
		endDay.setBounds(102, 56, 80, 20);
		eventPane.add(endDay);
		endDay.setColumns(10);
		//
		
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		lblStart.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(26, 38, 46, 14);
		eventPane.add(lblStart);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		lblEnd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd.setBounds(119, 38, 46, 14);
		eventPane.add(lblEnd);
		
		// User Feedback Label

		eventFeedbackLabel =new JLabel(" ");
		eventFeedbackLabel.setForeground(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"));
		eventFeedbackLabel.setBounds(10, 238, 270, 16);
		eventPane.add(eventFeedbackLabel);
		
		//Event Name Text Field Box
		eventName = new JTextField();
		 eventName.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(eventName.getText().equals("Event Name")){
		            			eventName.setText("");
		            	}
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(eventName.getText().equals(""))
	            	{
						eventName.setText("Event Name");
	            	}
				}
		    });
		eventName.setHorizontalAlignment(SwingConstants.CENTER);
		eventName.setText("Event Name");
		eventName.setBounds(10, 11, 272, 20);
		eventPane.add(eventName);
		eventName.setColumns(10);
		
		radioBtnEventPersonal = new JRadioButton("Personal");
		radioBtnEventPersonal.setSelected(true);
		radioBtnEventPersonal.setBounds(200, 54, 81, 24);
		eventPane.add(radioBtnEventPersonal);
		
		radioBtnEventTeam = new JRadioButton("Team");
		radioBtnEventTeam.setBounds(200, 85, 64, 24);
		eventPane.add(radioBtnEventTeam);
		
		radioBtnEventPersonal.addActionListener(
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(eventTypeSwitch == false){
							radioBtnEventTeam.setSelected(false);
							eventTypeSwitch = true;
						}
					}
					
				});
		
		radioBtnEventTeam.addActionListener(
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(eventTypeSwitch == true){
							radioBtnEventPersonal.setSelected(false);
							eventTypeSwitch = false;
						}
					}
					
				});
		
		//End Day Text Field Boxes
		
		btnCreateEvent.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	            	Event newEvent;
	            	Calendar eventStart;
	            	Calendar eventEnd;
	            	try{
	            		eventStart = DateTimeUtils.dateTimeParser(startDay.getText(), startTime.getText());
	            		eventEnd = DateTimeUtils.dateTimeParser(endDay.getText(), endTime.getText());
	            	}
	            	catch(WPISuiteException exception){
	            		eventFeedbackLabel.setText(exception.getMessage());
	            		return;
	            	}
	            	try{
	            		System.out.println("entered try");
	            		newEvent = new Event(
	            				eventName.getText(),
	            				eventLocation.getText(),
	            				eventStart,
	            				eventEnd,
	            				eventDescription.getText(),
	            				ConfigManager.getConfig().getUserName(),
	            				eventTypeSwitch);
	            		calView.testList.add(newEvent);
	            		System.out.println("added to list");
	            		calView.displayNewEvent(newEvent);
	            	}
	            	catch(WPISuiteException exception2){
	            		eventFeedbackLabel.setText(exception2.getMessage());
	            		return;
	            	}
	            	
	            	//Inject server request code to add event here
	            	EventListModel.getInstance().addEvent(newEvent);
	            	
	            	eventName.setText("Event Name");
	            	eventLocation.setText("Location Location Location");
	            	eventDescription.setText("Description...");
	            	startTime.setText("hh:mm");
	            	startDay.setText("mm/dd/yyyy");
	            	endTime.setText("hh:mm");
	            	endDay.setText("mm/dd/yyyy");
	            	eventFeedbackLabel.setText("Event created.");
	            	//do something with the event with the GUI here
	        }
	        @Override
	        public void focusLost(FocusEvent e){

	        }
	      });
		
		// Commitment Tab
		
		commitmentPane = new JPanel();
		//commitmentPane.setLayout(null);
		//addTab("Commitment Edit", null, commitmentPane, null);
				
		JButton btnCreateCommitment = new JButton("Create Commitment");
		btnCreateCommitment.setBounds(10, 165, 276, 23);
		btnCreateCommitment.setEnabled(true);
		commitmentPane.add(btnCreateCommitment);
		
		JButton btnDeleteCommitment = new JButton("Delete Commitment");
		btnDeleteCommitment.setBounds(10, 203, 276, 23);
		btnDeleteCommitment.setVisible(false);
		btnDeleteCommitment.setEnabled(true);
		commitmentPane.add(btnDeleteCommitment);
		
		dueDate = new JTextField();
			dueDate.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(dueDate.getText().equals("mm/dd/yyyy")){
		            			dueDate.setText("");
		            	}
		        }
	
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(dueDate.getText().equals(""))
	            	{
						dueDate.setText("mm/dd/yyyy");
	            	}
				}
		    });
		dueDate.setText("mm/dd/yyyy");
		dueDate.setHorizontalAlignment(SwingConstants.CENTER);
		dueDate.setColumns(10);
		dueDate.setBounds(10, 56, 272, 20);
		commitmentPane.add(dueDate);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDueDate.setForeground(UIManager.getColor("Button.darkShadow"));
		lblDueDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDueDate.setBounds(85, 38, 120, 14);
		commitmentPane.add(lblDueDate);
		
		commitmentName = new JTextField();
			commitmentName.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		            	if(commitmentName.getText().equals("Commitment Name")){
		            			commitmentName.setText("");
		            	}
		        }
	
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(commitmentName.getText().equals(""))
	            	{
						commitmentName.setText("Commitment Name");
	            	}
				}
		    });
		commitmentName.setHorizontalAlignment(SwingConstants.CENTER);
		commitmentName.setText("Commitment Name");
		commitmentName.setColumns(10);
		commitmentName.setBounds(10, 11, 272, 20);
		commitmentPane.add(commitmentName);
		
		JLabel commitFeedbackLabel = new JLabel("");
		commitFeedbackLabel.setForeground(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"));
		commitFeedbackLabel.setBounds(10, 238, 270, 16);
		commitmentPane.add(commitFeedbackLabel);
		
		radioBtnCommitTeam = new JRadioButton("Team");
		radioBtnCommitTeam.setBounds(10, 120, 64, 24);
		commitmentPane.add(radioBtnCommitTeam);
		
		radioBtnCommitPersonal = new JRadioButton("Personal");
		radioBtnCommitPersonal.setSelected(true);
		radioBtnCommitPersonal.setBounds(10, 92, 81, 24);
		commitmentPane.add(radioBtnCommitPersonal);
		
		radioBtnCommitPersonal.addActionListener(
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(commitTypeSwitch == false){
							radioBtnCommitTeam.setSelected(false);
							commitTypeSwitch = true;
						}
					}
					
				});
		
		radioBtnCommitTeam.addActionListener(
				new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(commitTypeSwitch == true){
							radioBtnCommitPersonal.setSelected(false);
							commitTypeSwitch = false;
						}
					}
					
				});
		
	}
	
	public void getCalendar(CalendarCalendarView newCal) {
		calView = newCal;
		initialize();
	}
	
}


