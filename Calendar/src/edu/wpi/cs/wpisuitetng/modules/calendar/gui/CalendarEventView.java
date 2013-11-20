/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;

/**
 * Contains the GUI elements of the Event Panel
 *
 */
public class CalendarEventView extends JPanel {
	
	private JTextField eventDescription;
	private JTextField eventLocation;
	private JTextField startTime;
	private JTextField startDay;
	private JTextField endTime;
	private JTextField endDay;
	private JTextField eventName;
	
	public CalendarEventView() {
		initialize();
	}
	
	private void initialize() {
		
		setPreferredSize(new Dimension(292,264));
		setLocation(5, 0);
		//setBounds(5, 86, 292, 234);
		//setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		setLayout(null);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.setBounds(10, 203, 112, 23);
		add(btnCreateEvent);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setBounds(170, 203, 112, 23);
		add(btnDeleteEvent);
		
		
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
		add(eventDescription);
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
		add(eventLocation);
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
		startTime.setBounds(10, 87, 112, 20);
		add(startTime);
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
		startDay.setBounds(10, 56, 112, 20);
		add(startDay);
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
		endTime.setBounds(170, 87, 112, 20);
		add(endTime);
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
		endDay.setBounds(170, 56, 112, 20);
		add(endDay);
		endDay.setColumns(10);
		//
		
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		lblStart.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(42, 38, 46, 14);
		add(lblStart);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		lblEnd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd.setBounds(204, 38, 46, 14);
		add(lblEnd);
		
		// User Feedback Label

		JLabel userFeedbackLabel =new JLabel("#YOLO");
		userFeedbackLabel.setForeground(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"));
		userFeedbackLabel.setBounds(10, 238, 270, 16);
		add(userFeedbackLabel);
		
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
		add(eventName);
		eventName.setColumns(10);
		//
		
		//End Day Text Field Boxes
		
		btnCreateEvent.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	            	Event newEvent = new Event();
	            	Calendar eventStart;
	            	Calendar eventEnd;
	            	try{
	            		eventStart = newEvent.dateTimeParser(startDay.getText(), startTime.getText());
	            		eventEnd = newEvent.dateTimeParser(endDay.getText(), endTime.getText());
	            	}
	            	catch(WPISuiteException exception){
	            		//add user message
	            		return;
	            	}
	            	try{
	            		newEvent.setName(eventName.getText());
	            		newEvent.setLocation(eventLocation.getText());
	            		newEvent.setDescription(eventDescription.getText());
	            		newEvent.setStart(eventStart);
	            		newEvent.setEnd(eventEnd);
	            	}
	            	catch(WPISuiteException exception2){
	            		//add user message
	            		return;
	            	}
	            	//do something with the event with the GUI here
	        }
	        @Override
	        public void focusLost(FocusEvent e){

	        }
	      });
	}
	
}


