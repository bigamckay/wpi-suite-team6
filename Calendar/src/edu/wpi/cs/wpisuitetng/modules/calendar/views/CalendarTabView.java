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

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;

/**
 *  Contains the GUI elements of the Tab Panel
 *  Includes the day view and the event and commitment lists
 *
 */
@SuppressWarnings("serial")
public class CalendarTabView extends JTabbedPane {

	public JTable dayTable;
	//private JTable eventTable;
	private JTable commitTable;
	
	public CalendarTabView() {
		initialize();
	}
	
	private void initialize() {
		//setBounds(5, 324, 250, 412);
		setPreferredSize(new Dimension(250,256));
		setLocation(5, 324);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		
		JScrollPane dayScrollPane = new JScrollPane();
		addTab("Day", null, dayScrollPane, null);
		setBackgroundAt(0, UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		
		dayTable = new JTable();
		dayTable.setBackground(Color.WHITE);
		dayTable.getTableHeader().setReorderingAllowed(false);
		dayTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"12:00 AM", null},
				{"1:00 AM", null},
				{"2:00 AM", null},
				{"3:00 AM", null},
				{"4:00 AM", null},
				{"5:00 AM", null},
				{"6:00 AM", null},
				{"7:00 AM", null},
				{"8:00 AM", null},
				{"9:00 AM", null},
				{"10:00 AM", null},
				{"11:00 AM", null},
				{"12:00 PM", null},
				{"1:00 PM", null},
				{"2:00 PM", null},
				{"3:00 PM", null},
				{"4:00 PM", null},
				{"5:00 PM", null},
				{"6:00 PM", null},
				{"7:00 PM", null},
				{"8:00 PM", null},
				{"9:00 PM", null},
				{"10:00 PM", null},
				{"11:00 PM", null},
			},
			new String[] {
				"Time", ""
			}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
			   //all cells false
			   return false;
		    }
		});
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		dayTable.getColumnModel().getColumn(0).setMinWidth(65);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		dayTable.getColumnModel().getColumn(1).setMinWidth(200);
		dayScrollPane.setViewportView(dayTable);
		
		JScrollPane eventScrollPane = new JScrollPane();
		//addTab("Events", null, eventScrollPane, null);
		
		/*eventTable = new JTable();
		eventTable.getTableHeader().setReorderingAllowed(false);
		eventTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Date", "Event Name"
			}
		));
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		eventTable.getColumnModel().getColumn(0).setMinWidth(65);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		eventTable.getColumnModel().getColumn(1).setMinWidth(200);
		eventScrollPane.setViewportView(eventTable);*/
		
		JScrollPane scrollPane_2 = new JScrollPane();
		addTab("Commitments", null, scrollPane_2, null);
		
		commitTable = new JTable();
		commitTable.getTableHeader().setReorderingAllowed(false);
		commitTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Date", "Commitment Name"
			}
		));
		commitTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		commitTable.getColumnModel().getColumn(0).setMinWidth(65);
		commitTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		commitTable.getColumnModel().getColumn(1).setMinWidth(200);
		scrollPane_2.setViewportView(commitTable);
	}
	/*@SuppressWarnings("static-access")
	public void eventIterator(JTable day, ArrayList<Event> events){
		int size = events.size();
		for(Event e : events){
			day.getModel().setValueAt(e.getStart().get(Calendar.HOUR) + ":" + e.getStart().get(Calendar.MINUTE), , is)
		}
	}*/
	
}
//LEROOOOY
