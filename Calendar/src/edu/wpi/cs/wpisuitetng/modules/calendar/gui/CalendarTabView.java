/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 *  Contains the GUI elements of the Tab Panel
 *
 */
public class CalendarTabView extends JTabbedPane {

	private JTable dayTable;
	private JTable eventTable;
	private JTable comitTable;
	
	public CalendarTabView() {
		initialize();
	}
	
	private void initialize() {
		//setBounds(5, 324, 250, 412);
		setPreferredSize(new Dimension(250,330));
		setLocation(5, 324);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		
		JScrollPane dayScrollPane = new JScrollPane();
		addTab("Day", null, dayScrollPane, null);
		setBackgroundAt(0, UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		
		dayTable = new JTable();
		dayTable.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
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
		));
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		dayTable.getColumnModel().getColumn(0).setMinWidth(65);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		dayTable.getColumnModel().getColumn(1).setMinWidth(200);
		dayScrollPane.setViewportView(dayTable);
		
		JScrollPane eventScrollPane = new JScrollPane();
		addTab("Events", null, eventScrollPane, null);
		
		eventTable = new JTable();
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
		eventScrollPane.setViewportView(eventTable);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		addTab("Commitments", null, scrollPane_2, null);
		
		comitTable = new JTable();
		comitTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Date", "Commitment Name"
			}
		));
		comitTable.getColumnModel().getColumn(0).setPreferredWidth(65);
		comitTable.getColumnModel().getColumn(0).setMinWidth(65);
		comitTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		comitTable.getColumnModel().getColumn(1).setMinWidth(200);
		scrollPane_2.setViewportView(comitTable);
	}
}
