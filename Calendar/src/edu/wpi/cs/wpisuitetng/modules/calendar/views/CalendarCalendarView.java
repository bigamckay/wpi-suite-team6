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

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;
import java.lang.Math;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.EventListModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.utils.ListUtils;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * Contains the GUI elements of the Calendar Panel Includes week, month, and
 * year view
 * 
 */
@SuppressWarnings("serial")
public class CalendarCalendarView extends JTabbedPane {

	// TODO get rid of this it is a hack
	public ArrayList<Event> testList;

	private boolean personalViewSelected = true;
	private boolean teamViewSelected = false;

	private CalendarTabView tabView;

	public JTable weekDayHeaders;
	private JTable JanDayTable;
	private JTable MarDayTable;
	private JTable FebDayTable;
	private JTable AprDayTable;
	private JTable MayDayTable;
	private JTable JunDayTable;
	private JTable JulDayTable;
	private JTable AugDayTable;
	private JTable SepDayTable;
	private JTable OctDayTable;
	private JTable NovDayTable;
	private JTable DecDayTable;

	public JLabel weekLabel;
	private String currentFocus = "week";

	public int weekStart;
	public int weekEnd;

	private JTable monthView;

	public JLabel monthLabel;
	public int yearNullRan = 0;

	public JTable[] monthArray;
	private final DefaultTableModel clearedModel = new DefaultTableModel(
			new Object[][] { { null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null }, },
			new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" });

	private final DefaultTableModel clearedWeekModel = new DefaultTableModel(
			new Object[][] {
					{ "12:00 AM", null, null, null, null, null, null, null },
					{ "1:00 AM", null, null, null, null, null, null, null },
					{ "2:00 AM", null, null, null, null, null, null, null },
					{ "3:00 AM", null, null, null, null, null, null, null },
					{ "4:00 AM", null, null, null, null, null, null, null },
					{ "5:00 AM", null, null, null, null, null, null, null },
					{ "6:00 AM", null, null, null, null, null, null, null },
					{ "7:00 AM", null, null, null, null, null, null, null },
					{ "8:00 AM", null, null, null, null, null, null, null },
					{ "9:00 AM", null, null, null, null, null, null, null },
					{ "10:00 AM", null, null, null, null, null, null, null },
					{ "11:00 AM", null, null, null, null, null, null, null },
					{ "12:00 PM", null, null, null, null, null, null, null },
					{ "1:00 PM", null, null, null, null, null, null, null },
					{ "2:00 PM", null, null, null, null, null, null, null },
					{ "3:00 PM", null, null, null, null, null, null, null },
					{ "4:00 PM", null, null, null, null, null, null, null },
					{ "5:00 PM", null, null, null, null, null, null, null },
					{ "6:00 PM", null, null, null, null, null, null, null },
					{ "7:00 PM", null, null, null, null, null, null, null },
					{ "8:00 PM", null, null, null, null, null, null, null },
					{ "9:00 PM", null, null, null, null, null, null, null },
					{ "10:00 PM", null, null, null, null, null, null, null },
					{ "11:00 PM", null, null, null, null, null, null, null }, },
			new String[] { "Time", "Sunday", "Monday", "Tuesday", "Wednesday",
					"Thursday", "Friday", "Saturday" });

	public int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	public int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	public int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	public int currentDotw = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	private String month = new String();

	public CalendarCalendarView() {
		testList = new ArrayList<Event>();
		initialize();
	}

	private void initialize() {

		// viewTabbedPane.setBounds(302, 54, 716, 686);
		setPreferredSize(new Dimension(716, 550));
		setLocation(302, 54);

		/**
		 * Week View
		 */

		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(UIManager
				.getColor("InternalFrame.borderShadow"));
		weekPanel.setFocusable(true);
		addTab("Week View", null, weekPanel, null);
		weekPanel.setLayout(null);

		// JButton weekPrevButton = new JButton("Previous Week");
		// weekPrevButton.setBounds(10, 15, 133, 26);
		// weekPanel.add(weekPrevButton);

		JPanel weekName = new JPanel();
		weekName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		weekName.setBackground((Color) null);
		weekName.setBounds(155, 7, 400, 40);
		weekPanel.add(weekName);

		weekLabel = new JLabel();
		updateWeekName(currentDay, currentMonth, currentYear);
		weekLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		weekLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		weekName.add(weekLabel);

		JPanel weekDays = new JPanel();
		weekDays.setBounds(10, 58, 691, 411);
		weekPanel.add(weekDays);
		weekDays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		weekDays.setBackground((Color) null);
		weekDays.setLayout(new CardLayout(0, 0));

		JScrollPane weekScrollPane = new JScrollPane();
		weekDays.add(weekScrollPane, "name_37189630620584");

		weekDayHeaders = new JTable();
		weekDayHeaders.setRowSelectionAllowed(false);
		weekDayHeaders.getTableHeader().setReorderingAllowed(false);
		weekDayHeaders.setModel(new DefaultTableModel(new Object[][] {
				{ "12:00 AM", null, null, null, null, null, null, null },
				{ "1:00 AM", null, null, null, null, null, null, null },
				{ "2:00 AM", null, null, null, null, null, null, null },
				{ "3:00 AM", null, null, null, null, null, null, null },
				{ "4:00 AM", null, null, null, null, null, null, null },
				{ "5:00 AM", null, null, null, null, null, null, null },
				{ "6:00 AM", null, null, null, null, null, null, null },
				{ "7:00 AM", null, null, null, null, null, null, null },
				{ "8:00 AM", null, null, null, null, null, null, null },
				{ "9:00 AM", null, null, null, null, null, null, null },
				{ "10:00 AM", null, null, null, null, null, null, null },
				{ "11:00 AM", null, null, null, null, null, null, null },
				{ "12:00 PM", null, null, null, null, null, null, null },
				{ "1:00 PM", null, null, null, null, null, null, null },
				{ "2:00 PM", null, null, null, null, null, null, null },
				{ "3:00 PM", null, null, null, null, null, null, null },
				{ "4:00 PM", null, null, null, null, null, null, null },
				{ "5:00 PM", null, null, null, null, null, null, null },
				{ "6:00 PM", null, null, null, null, null, null, null },
				{ "7:00 PM", null, null, null, null, null, null, null },
				{ "8:00 PM", null, null, null, null, null, null, null },
				{ "9:00 PM", null, null, null, null, null, null, null },
				{ "10:00 PM", null, null, null, null, null, null, null },
				{ "11:00 PM", null, null, null, null, null, null, null }, },
				new String[] { "Time", "Sunday", "Monday", "Tuesday",
						"Wednesday", "Thursday", "Friday", "Saturday" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});

		weekScrollPane.setViewportView(weekDayHeaders);

		// JButton weekNextButton = new JButton("Next Week");
		// weekNextButton.setBounds(568, 15, 133, 26);
		// weekPanel.add(weekNextButton);

		/**
		 * Month View
		 */
		
		Object[][] emptyArray = new Object[][] {
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null } };

		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(null);
		monthPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthPanel.setBackground(UIManager.getColor("Button.select"));
		addTab("Month View", null, monthPanel, null);

		JScrollPane monthDays = new JScrollPane();
		monthDays.setBounds(12, 50, 685, 425);

		monthView = new JTable();
		monthView.setBounds(1, 1, 684, 402);
		monthView.setRowSelectionAllowed(false);
		monthView.getTableHeader().setReorderingAllowed(false);
		monthView.setModel(new DefaultTableModel(emptyArray, 
				new String[] {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		monthView.setRowHeight(67);
		monthDays.setViewportView(monthView);
		monthPanel.add(monthDays);

		JPanel monthName = new JPanel();
		monthName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthName.setBackground((Color) null);
		monthName.setBounds(10, 7, 691, 40);
		monthPanel.add(monthName);

		monthLabel = new JLabel(getCurrentMonth(currentMonth));
		monthLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		monthLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		monthName.add(monthLabel);

		/**
		 * Year View
		 */

		JPanel yearPanel = new JPanel();
		yearPanel.setBackground(UIManager
				.getColor("InternalFrame.borderShadow"));
		addTab("Year View", null, yearPanel, null);
		yearPanel.setLayout(new CardLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(UIManager
				.getColor("InternalFrame.borderShadow"));
		yearPanel.add(layeredPane, "name_37461493840748");
		layeredPane.setLayout(null);

		JScrollPane JanScrollPane = new JScrollPane();
		JanScrollPane.setViewportBorder(null);
		JanScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JanScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JanScrollPane.setBounds(10, 10, 224, 118);
		layeredPane.setLayer(JanScrollPane, 0);
		JanScrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		// Add JAN label
		JLabel lblJAN = new JLabel("JAN", CENTER);
		lblJAN.setBounds(10, 10, 224, 118);
		lblJAN.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblJAN.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblJAN, new Integer(10));
		layeredPane.add(JanScrollPane);

		JanDayTable = new JTable();
		JanDayTable.setRowSelectionAllowed(false);
		JanDayTable.getTableHeader().setReorderingAllowed(false);
		JanDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});

		// populateYear(monthArray);

		JanDayTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(2).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(3).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(4).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(6).setPreferredWidth(15);
		JanScrollPane.setViewportView(JanDayTable);

		JScrollPane FebScrollPane = new JScrollPane();
		FebScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		FebScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		FebScrollPane.setBounds(244, 10, 224, 118);
		// Add FEB label
		JLabel lblFEB = new JLabel("FEB", CENTER);
		lblFEB.setBounds(244, 10, 224, 118);
		lblFEB.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblFEB.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblFEB, new Integer(10));
		layeredPane.add(FebScrollPane);

		FebDayTable = new JTable();
		FebDayTable.setRowSelectionAllowed(false);
		FebDayTable.getTableHeader().setReorderingAllowed(false);
		FebDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		FebScrollPane.setViewportView(FebDayTable);

		JScrollPane MarScrollPane = new JScrollPane();
		MarScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		MarScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		MarScrollPane.setBounds(478, 10, 224, 118);
		// Add MAR label
		JLabel lblMAR = new JLabel("MAR", CENTER);
		lblMAR.setBounds(478, 10, 224, 118);
		lblMAR.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblMAR.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblMAR, new Integer(10));
		layeredPane.add(MarScrollPane);

		MarDayTable = new JTable();
		MarDayTable.setRowSelectionAllowed(false);
		MarDayTable.getTableHeader().setReorderingAllowed(false);
		MarDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		MarScrollPane.setViewportView(MarDayTable);

		JScrollPane AprScrollPane = new JScrollPane();
		AprScrollPane.setViewportBorder(null);
		AprScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		AprScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		AprScrollPane.setAlignmentY(0.0f);
		AprScrollPane.setBounds(10, 138, 224, 118);
		// Add APR label
		JLabel lblAPR = new JLabel("APR", CENTER);
		lblAPR.setBounds(10, 138, 224, 118);
		lblAPR.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblAPR.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblAPR, new Integer(10));
		layeredPane.add(AprScrollPane);

		AprDayTable = new JTable();
		AprDayTable.setRowSelectionAllowed(false);
		AprDayTable.getTableHeader().setReorderingAllowed(false);
		AprDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		AprScrollPane.setViewportView(AprDayTable);

		JScrollPane MayScrollPane = new JScrollPane();
		MayScrollPane.setViewportBorder(null);
		MayScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		MayScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		MayScrollPane.setAlignmentY(0.0f);
		MayScrollPane.setBounds(244, 138, 224, 118);
		// Add MAY label
		JLabel lblMAY = new JLabel("MAY", CENTER);
		lblMAY.setBounds(244, 138, 224, 118);
		lblMAY.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblMAY.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblMAY, new Integer(10));
		layeredPane.add(MayScrollPane);

		MayDayTable = new JTable();
		MayDayTable.setRowSelectionAllowed(false);
		MayDayTable.getTableHeader().setReorderingAllowed(false);
		MayDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		MayScrollPane.setViewportView(MayDayTable);

		JScrollPane JunScrollPane = new JScrollPane();
		JunScrollPane.setViewportBorder(null);
		JunScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JunScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JunScrollPane.setAlignmentY(0.0f);
		JunScrollPane.setBounds(478, 138, 224, 118);
		// Add JUN label
		JLabel lblJUN = new JLabel("JUN", CENTER);
		lblJUN.setBounds(478, 138, 224, 118);
		lblJUN.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblJUN.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblJUN, new Integer(10));
		layeredPane.add(JunScrollPane);

		JunDayTable = new JTable();
		JunDayTable.setRowSelectionAllowed(false);
		JunDayTable.getTableHeader().setReorderingAllowed(false);
		JunDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		JunScrollPane.setViewportView(JunDayTable);

		JScrollPane JulScrollPane = new JScrollPane();
		JulScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JulScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JulScrollPane.setBounds(10, 266, 224, 118);
		// Add JUL label
		JLabel lblJUL = new JLabel("JUL", CENTER);
		lblJUL.setBounds(10, 266, 224, 118);
		lblJUL.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblJUL.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblJUL, new Integer(10));
		layeredPane.add(JulScrollPane);

		JulDayTable = new JTable();
		JulDayTable.setRowSelectionAllowed(false);
		JulDayTable.getTableHeader().setReorderingAllowed(false);
		JulDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		JulScrollPane.setViewportView(JulDayTable);

		JScrollPane AugScrollPane = new JScrollPane();
		AugScrollPane.setViewportBorder(null);
		AugScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		AugScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		AugScrollPane.setAlignmentY(0.0f);
		AugScrollPane.setBounds(244, 266, 224, 118);
		// Add AUG label
		JLabel lblAUG = new JLabel("AUG", CENTER);
		lblAUG.setBounds(244, 266, 224, 118);
		lblAUG.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblAUG.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblAUG, new Integer(10));
		layeredPane.add(AugScrollPane);

		AugDayTable = new JTable();
		AugDayTable.setRowSelectionAllowed(false);
		AugDayTable.getTableHeader().setReorderingAllowed(false);
		AugDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		AugScrollPane.setViewportView(AugDayTable);

		JScrollPane SepScrollPane = new JScrollPane();
		SepScrollPane.setViewportBorder(null);
		SepScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		SepScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SepScrollPane.setAlignmentY(0.0f);
		SepScrollPane.setBounds(478, 266, 224, 118);
		// Add SEP label
		JLabel lblSEP = new JLabel("SEP", CENTER);
		lblSEP.setBounds(478, 266, 224, 118);
		lblSEP.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblSEP.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblSEP, new Integer(10));
		layeredPane.add(SepScrollPane);

		SepDayTable = new JTable();
		SepDayTable.setRowSelectionAllowed(false);
		SepDayTable.getTableHeader().setReorderingAllowed(false);
		SepDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		SepScrollPane.setViewportView(SepDayTable);

		JScrollPane OctScrollPane = new JScrollPane();
		OctScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		OctScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		OctScrollPane.setBounds(10, 394, 224, 118);
		// Add OCT label
		JLabel lblOCT = new JLabel("OCT", CENTER);
		lblOCT.setBounds(10, 394, 224, 118);
		lblOCT.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblOCT.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblOCT, new Integer(10));
		layeredPane.add(OctScrollPane);

		OctDayTable = new JTable();
		OctDayTable.setRowSelectionAllowed(false);
		OctDayTable.getTableHeader().setReorderingAllowed(false);
		OctDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		OctScrollPane.setViewportView(OctDayTable);

		JScrollPane NovScrollPane = new JScrollPane();
		NovScrollPane.setViewportBorder(null);
		NovScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		NovScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		NovScrollPane.setAlignmentY(0.0f);
		NovScrollPane.setBounds(244, 394, 224, 118);
		// Add NOV label
		JLabel lblNOV = new JLabel("NOV", CENTER);
		lblNOV.setBounds(244, 394, 224, 118);
		lblNOV.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblNOV.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblNOV, new Integer(10));
		layeredPane.add(NovScrollPane);

		NovDayTable = new JTable();
		NovDayTable.setRowSelectionAllowed(false);
		NovDayTable.getTableHeader().setReorderingAllowed(false);
		NovDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		NovScrollPane.setViewportView(NovDayTable);

		JScrollPane DecScrollPane = new JScrollPane();
		DecScrollPane.setViewportBorder(null);
		DecScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		DecScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DecScrollPane.setAlignmentY(0.0f);
		DecScrollPane.setBounds(478, 394, 224, 118);
		// Add DEC label
		JLabel lblDEC = new JLabel("DEC", CENTER);
		lblDEC.setBounds(478, 394, 224, 118);
		lblDEC.setFont(new Font("Helvetica", Font.BOLD, 80));
		lblDEC.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.2f));
		layeredPane.add(lblDEC, new Integer(10));
		layeredPane.add(DecScrollPane);

		DecDayTable = new JTable();
		DecDayTable.setRowSelectionAllowed(false);
		DecDayTable.getTableHeader().setReorderingAllowed(false);
		DecDayTable.setModel(new DefaultTableModel(emptyArray, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		});
		DecScrollPane.setViewportView(DecDayTable);

		/*
		 * JPanel titlePanel = new JPanel(); titlePanel.setBounds(0, 0, 1018,
		 * 80); titlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
		 * null, null)); titlePanel.setBackground(UIManager.getColor(
		 * "InternalFrame.activeTitleGradient")); add(titlePanel);
		 * titlePanel.setLayout(null);
		 * 
		 * JLabel lblCalendar = new JLabel("Calendar");
		 * lblCalendar.setBounds(700, 7, 188, 64);
		 * lblCalendar.setForeground(UIManager
		 * .getColor("Label.disabledForeground")); lblCalendar.setFont(new
		 * Font("Segoe UI", Font.ITALIC, 48)); titlePanel.add(lblCalendar);
		 * 
		 * searchBar = new JTextField(); searchBar.setBounds(12, 51, 164, 20);
		 * searchBar.setText("Search..."); titlePanel.add(searchBar);
		 * searchBar.setColumns(10);
		 * 
		 * JPanel calendarDayButtons = new JPanel();
		 * calendarDayButtons.setBounds(12, 117, 685, 485);
		 * calendarPanel.add(calendarDayButtons);
		 * calendarDayButtons.setLayout(null);
		 */

		monthArray = new JTable[] { JanDayTable, FebDayTable, MarDayTable,
				AprDayTable, MayDayTable, JunDayTable, JulDayTable,
				AugDayTable, SepDayTable, OctDayTable, NovDayTable, DecDayTable };

		// populateMonth(monthArray[0], 2, 31);
		// populateMonth(monthArray[1], 6, 28);
		simulateYear(currentYear);
		populateYear(monthArray, currentYear);
		// populateDay(weekDayHeaders, testList);
	}

	/**
	 * Fills given month with days, starting from the appropriate date.
	 * Determines the day of the week of the starting day of the next month.
	 * Used in both month and year views.
	 * 
	 * @param month
	 *            - The JTable to populate.
	 * @param startDay
	 *            - The day of the week the month begins. Can be 0 to 6.
	 * @param daysInMonth
	 *            - How many days are in the given month. Can be 28 to 31.
	 * @param whatMonth
	 *            - Which month in the year the given month is. Can be 0 to 11 -
	 *            0 is January, 1 February, and so on.
	 * @return The starting day of the next month in the year. If December,
	 *         returns the starting day of the next January
	 */
	public int populateMonth(JTable month, int startDay, int daysInMonth,
			int whatMonth) {
		// System.out.println("populateMonth is running");
		// Calendar testStart = new GregorianCalendar(2013, Calendar.NOVEMBER,
		// 14, 18, 0);
		// Calendar testStart2 = new GregorianCalendar(2013, Calendar.JANUARY,
		// 21, 18, 0);
		// Calendar testStart3 = new GregorianCalendar(2013, Calendar.DECEMBER,
		// 02, 14, 0);

		try {
			System.out.println("Not doing the test events");
			// Event testEvent1 = new Event("Team 6 Meeting", "Flower",
			// testStart, testStart,"Funtimes!", "hi", false);
			// Event testEvent2 = new Event("PlayDate", "Bancroft Towers",
			// testStart2, testStart2, "Ring Toss", "sup", true);
			// testList.add(testEvent1);
			// testList.add(testEvent2);
		} catch (Exception e) {
			System.out.println("What are you doing");
		}
		Integer dayCounter = 1;
		int j = startDay;

		for (int i = 0; i < 6; i++) {
			for (; j < 7; j++) {
				String personalEventStr = " ";
				String teamEventStr = " ";
				List<Event> personalEventList = isThereAPersonalEventOnThisDate(
						testList /* EventListModel.getInstance().getEvents() */,
						currentYear, whatMonth, dayCounter);
				List<Event> teamEventList = isThereATeamEventOnThisDate(
						testList, currentYear, whatMonth, dayCounter);
				if (personalViewSelected && teamViewSelected) {
					if (personalEventList.size() != 0
							&& teamEventList.size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(i, 2);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(month,
								dayCounter, false, false, i, j);
						month.getColumnModel().getColumn(j)
								.setCellRenderer(cellRender);
						if (month.equals(monthView)) {
							for (Event z : personalEventList) {
								personalEventStr = personalEventStr
										+ z.getName() + '\n';
								break;
							}
							for (Event z : teamEventList) {
								teamEventStr = teamEventStr + z.getName()
										+ '\n';
								break;
							}
						}
					} else if (personalEventList.size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(i, 0);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(month,
								dayCounter, false, false, i, j);
						month.getColumnModel().getColumn(j)
								.setCellRenderer(cellRender);
						if (month.equals(monthView)) {
							for (Event z : personalEventList) {
								personalEventStr = personalEventStr
										+ z.getName() + '\n';
								break;
							}
						}
					} else if (teamEventList.size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(i, 1);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(month,
								dayCounter, false, false, i, j);
						month.getColumnModel().getColumn(j)
								.setCellRenderer(cellRender);
						if (month.equals(monthView)) {
							for (Event z : teamEventList) {
								teamEventStr = teamEventStr + z.getName()
										+ '\n';
								break;
							}
						}
					}

				} else if (personalViewSelected) {
					if (personalEventList.size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(i, 0);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(month,
								dayCounter, false, false, i, j);
						month.getColumnModel().getColumn(j)
								.setCellRenderer(cellRender);
						if (month.equals(monthView)) {
							for (Event z : personalEventList) {
								personalEventStr = personalEventStr
										+ z.getName() + '\n';
								break;
							}
						}
					}
				} else if (teamViewSelected) {
					if (teamEventList.size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(i, 1);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(month,
								dayCounter, false, false, i, j);
						month.getColumnModel().getColumn(j)
								.setCellRenderer(cellRender);
						if (month.equals(monthView)) {
							for (Event z : teamEventList) {
								teamEventStr = teamEventStr + z.getName()
										+ '\n';
								break;
							}
						}
					}
				}
				/*
				 * month.getModel().setValueAt(dayCounter.toString(), i, j); if
				 * (dayCounter == daysInMonth){ return j+1; } dayCounter++;
				 */

				if (month.equals(monthView))
					month.getModel().setValueAt(
							dayCounter.toString() + personalEventStr
									+ teamEventStr, i, j);
				else
					month.getModel().setValueAt(dayCounter, i, j);
				// monthView.setValueAt(dayCounter.toString() + personalEventStr
				// + teamEventStr, i, j);
				if (dayCounter == daysInMonth) {
					return j + 1;
				}
				dayCounter++;
			}
			j = 0;
		}
		return j + 1;
	}

	/**
	 * Calculates the day of the week of the starting day of the next month.
	 * Should never run on a December. Used only in month view.
	 * 
	 * @param startDay
	 *            - Day of the week the given month begins on. Can be 0 to 6.
	 * @param daysInMonth
	 *            - How many days are in the given month. Can be 28 to 31.
	 * @return The day of the week the next month starts on.
	 */
	public int simulateMonth(int startDay, int daysInMonth) {
		Integer dayCounter = 1;
		int j = startDay;
		for (int i = 0; i < 6; ++i) {
			for (; j < 7; ++j) {
				if (dayCounter == daysInMonth) {
					return j + 1;
				}
				dayCounter++;
			}
			j = 0;
		}
		return j + 1;
	}

	/**
	 * Clears the month and year views of events.
	 * 
	 * @param month
	 *            - The JTable to clear.
	 */
	public void populateMonthNull(JTable month) {
		int j = 0;
		for (int i = 0; i < 6; ++i) {
			for (; j < 7; ++j) {
				month.getModel().setValueAt(null, i, j);
				month.getColumnModel().getColumn(j)
						.setCellRenderer(new DefaultTableCellRenderer());
			}
			j = 0;
		}
		return;
	}

	/**
	 * Clears the week view of events.
	 * 
	 * @param week
	 *            - The JTable to clear.
	 */
	public void populateWeekNull(JTable week) {
		int j;
		for (int i = 0; i < 24; ++i) {
			for (j = 1; j < 8; ++j) {
				week.getModel().setValueAt(null, i, j);
				// week.getColumnModel().getColumn(j).setCellRenderer(new
				// DefaultTableCellRenderer());
			}
			j = 0;
		}
		return;
	}

	/**
	 * Clears the day view of events, which is in the Event View panel.
	 * 
	 * @param day
	 *            - The JTable to clear.
	 */
	public void populateDayNull(JTable day) {
		for (int i = 0; i < 24; ++i) {
			day.getModel().setValueAt(null, i, 1);
		}
		return;
	}

	/**
	 * Calculates the day of the week the given year begins on.
	 * 
	 * @param year
	 *            - The year to determine the starting day of.
	 * @return Day of the week January 1st falls on for the given year.
	 */
	int determineStartingDay(int year) // taken from
										// http://mathforum.org/library/drmath/view/55837.html
	{ // modified specifically to find the first day of the year
		double startDay;

		year -= 1;

		startDay = (35 + year + Math.floor(year / 4) - Math.floor(year / 100)
				+ Math.floor(year / 400) + 1);
		startDay = startDay % 7;

		return (int) startDay;
	}

	/**
	 * Fills an array of 12 JTables with days, starting from the appropriate day
	 * of the week for January 1st. Used only in year view.
	 * 
	 * @param monthArray
	 *            - 12 JTables representing the year's months. Individual tables
	 *            are populated using populateMonth.
	 * @param year
	 *            - Used to determine the starting day.
	 */
	public void populateYear(JTable[] monthArray, int year) {
		int startDay = 0;
		System.out.println("populateYear is running");
		for (int i = 0; i < 12; i++) {
			populateMonthNull(monthArray[i]);
			if (i == 0)
				// i is month
				startDay = populateMonth(monthArray[i],
						determineStartingDay(year), daysInMonth(i, year), i);
			else
				startDay = populateMonth(monthArray[i], startDay,
						daysInMonth(i, year), i);
		}
		return;
	}

	/**
	 * Calculates the day of the week the currently selected month starts on for
	 * the given year. Current month determined by class variable currentMonth.
	 * Used only in month view.
	 * 
	 * @param year
	 *            - The year to simulate.
	 * @return The day of the week the current month begins on.
	 */
	public int simulateYear(int year) {
		int startDay = 0;
		for (int i = 0; i < 12; ++i) {
			if (i == 0) {
				startDay = simulateMonth(determineStartingDay(year),
						daysInMonth(i, year));

				if (currentMonth == 0) {
					startDay = determineStartingDay(year);
					populateMonth(monthView, startDay, daysInMonth(i, year), i);
				}
			} else if (i == currentMonth) {
				populateMonthNull(monthView);
				populateMonth(monthView, startDay, daysInMonth(i, year), i);
				return startDay;
			} else
				startDay = simulateMonth(startDay, daysInMonth(i, year));
		}
		return -1;
	}

	/**
	 * Fills the array of JTables in the year view with empty cells. Used only
	 * in year view.
	 */
	public void populateYearNull() {
		for (int i = 0; i < 12; i++) {
			monthArray[i].setModel(clearedModel);
		}
		yearNullRan++;
		System.out.println("Worked!" + yearNullRan);
		return;
	}

	/**
	 * Determines how many days are in the given month.
	 * 
	 * @param month
	 *            - Which month of the year the given month is. Can be 0 to 11.
	 *            0 is January, 1 is February, and so on.
	 * @param year
	 *            - The year the given month is in. Only changes the number of
	 *            days in February if it is a leap year.
	 * @return The number of days in the given month.
	 */
	public int daysInMonth(int month, int year) {
		int result = 0; // for the respective month value and prints that month
						// and the year to console
		boolean leapYear = isLeapYear(year);

		switch (month) {
		case 0:
			result = 31;
			break;
		case 1:
			if (leapYear) {
				result = 29;
			} else {
				result = 28;
			}
			break;
		case 2:
			result = 31;
			break;
		case 3:
			result = 30;
			break;
		case 4:
			result = 31;
			break;
		case 5:
			result = 30;
			break;
		case 6:
			result = 31;
			break;
		case 7:
			result = 31;
			break;
		case 8:
			result = 30;
			break;
		case 9:
			result = 31;
			break;
		case 10:
			result = 30;
			break;
		case 11:
			result = 31;
			break;
		}

		return result;
	}

	/**
	 * Determines if the given year is a leap year. Helper function to
	 * daysInMonth.
	 * 
	 * @param year
	 *            - The year to test.
	 * @return True if the given year is a leap year.
	 */
	public boolean isLeapYear(int year) {
		boolean leapYear;

		if ((year % 400) == 0) {
			leapYear = true;
		} else if ((year % 100) == 0) {
			leapYear = false;
		} else if ((year % 4) == 0) {
			leapYear = true;
		} else {
			leapYear = false;
		}

		return leapYear;
	}

	/**
	 * Gets the three-letter abbreviation of the current month's name as a
	 * string. Used for the labels in year view.
	 * 
	 * @param currMonth
	 *            - The month in question.
	 * @return Current month's name as a three-letter string.
	 */
	public String getShortMonth(int currMonth) {
		String month = "Jan";

		switch (currMonth) {
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

	/**
	 * Gets a string containing the current month's name. Used for the label in
	 * month view.
	 * 
	 * @param currMonth
	 *            - The month in question.
	 * @return String with the current month's name.
	 */
	public String getCurrentMonth(int currMonth) {
		switch (currMonth) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "Febuary";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		case 12:
			currentMonth = 0;
			month = "January";
			break;
		case -1:
			currentMonth = 11;
			month = "December";
			break;
		}
		return month;
	}

	/**
	 * Gets the name of the tab that is currently in focus
	 * 
	 * @return Name of the tab in focus. Can be "week", "month", or "year".
	 */
	public String getCurrentFocus() {
		return this.currentFocus;
	}

	/**
	 * Gets the table in the month view
	 * 
	 * @return The JTable shown in the month view.
	 */
	public JTable getMonthView() {
		return monthView;
	}

	// method to take in list of events (received from server) and populate year
	// view
	// year view indicates event presence by color code on that day
	/**
	 * 
	 * @param eventList
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public List<Event> isThereAPersonalEventOnThisDate(List<Event> eventList,
			int year, int month, int day) {
		// TODO get call to sort to work
		// ListUtils.eventByDateListSort(eventList);
		List<Event> personalEvents = new ArrayList<Event>();
		// Calendar date = new GregorianCalendar(year, month, day);
		for (Event e : eventList) {
			if (e.getPersonal()) {
				/*
				 * if(e.getStart().before(date)){ return false; }
				 */
				if (e.getStart().get(Calendar.YEAR) == year
						&& e.getStart().get(Calendar.MONTH) == month
						&& e.getStart().get(Calendar.DATE) == day) {

					// System.out.println("IN CUSTOM RENDERER"); THE CODE NEVER
					// GETS HERE!!!!!!!!!
					personalEvents.add(e);
				}
			}

		}
		return personalEvents;
	}

	/**
	 * 
	 * @param eventList
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public List<Event> isThereATeamEventOnThisDate(List<Event> eventList,
			int year, int month, int day) {
		// TODO get call to sort working
		// ListUtils.eventByDateListSort(eventList);
		List<Event> teamEvents = new ArrayList<Event>();
		// Calendar date = new GregorianCalendar(year, month, day);
		for (Event e : eventList) {
			if (!e.getPersonal()) {
				/*
				 * if(e.getStart().before(date)){ return false; }
				 */
				if (e.getStart().get(Calendar.YEAR) == year
						&& e.getStart().get(Calendar.MONTH) == month
						&& e.getStart().get(Calendar.DATE) == day) {

					// System.out.println("IN CUSTOM RENDERER"); THE CODE NEVER
					// GETS HERE!!!!!!!!!
					teamEvents.add(e);
				}
			}

		}
		return teamEvents;
	}

	/**
	 * 
	 * @param currDay
	 * @param currMonth
	 * @param currYear
	 */
	public void updateWeekName(int currDay, int currMonth, int currYear) {
		String month1, month2, startDayStr, endDayStr;
		int currentDotw = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		weekStart = currDay + 1 - currentDotw;
		weekEnd = currDay + 7 - currentDotw;

		startDayStr = String.valueOf(weekStart);
		endDayStr = String.valueOf(weekEnd);

		month1 = getCurrentMonth(currentMonth);
		month2 = getCurrentMonth(currentMonth);
		if (currDay + 1 - currentDotw <= 0) {
			if (currentMonth <= 0) {
				month1 = getCurrentMonth(11);
				startDayStr = String.valueOf(currDay + 1 - currentDotw
						+ daysInMonth(11, currentYear));
			} else {
				startDayStr = String.valueOf(currDay + 1 - currentDotw
						+ daysInMonth(currentMonth - 1, currentYear));
				month1 = getCurrentMonth(currentMonth - 1);
			}
		}
		if (currDay + 7 - currentDotw > daysInMonth(currentMonth, currentYear)) {
			if (currentMonth >= 11) {
				month2 = getCurrentMonth(0);
				endDayStr = String.valueOf(currDay + 7 - currentDotw
						- daysInMonth(0, currentYear));
			} else {
				endDayStr = String.valueOf(currDay + 7 - currentDotw
						- daysInMonth(currentMonth, currentYear));
				month2 = getCurrentMonth(currentMonth + 1);
			}
		}

		weekLabel.setText(month1 + " " + startDayStr + " to " + month2 + " "
				+ endDayStr);
	}

	/**
	 * 
	 * @param anEvent
	 */
	public void displayNewEvent(Event anEvent) {
		System.out.println("display new event is running");
		int month = anEvent.getStart().get(Calendar.MONTH);
		int year = anEvent.getStart().get(Calendar.YEAR);
		populateYear(monthArray, year);
		populateMonthNull(monthView);
		populateMonth(monthView, simulateYear(year), daysInMonth(month, year),
				month);
	}

	/**
	 * 
	 * @param to
	 */
	public void setPersonalViewSelected(boolean to) {
		this.personalViewSelected = to;
	}

	/**
	 * 
	 * @param to
	 */
	public void setTeamViewSelected(boolean to) {
		this.teamViewSelected = to;
	}

	/**
	 * 
	 * @param day
	 * @param events
	 */
	public void populateWeek(JTable day, List<Event> events) {
		int goflag = 0;
		populateWeekNull(day);
		for (int i = 1; i < 7; i++)
			day.getColumnModel().getColumn(i)
					.setCellRenderer(new DefaultTableCellRenderer());
		for (Event e : events) {
			if (e.getStart().get(Calendar.YEAR) != currentYear) {
				if (currentMonth >= 11) {
					if (e.getStart().get(Calendar.MONTH) <= 0) {
						if (e.getStart().get(Calendar.YEAR) == (currentYear + 1)) {
							goflag = 1;
						}
					}
				} else if (currentMonth <= 0) {
					if (e.getStart().get(Calendar.MONTH) >= 11) {
						if (e.getStart().get(Calendar.YEAR) == (currentYear - 1)) {
							goflag = 1;
						}
					}
				}
			} else {
				goflag = 1;
			}

			if (goflag != 1) {
				continue;
			}
			// DAY_OF_WEEK returns number 1-7 for Sunday - Saturday
			// HOUR_OF_DAY returns number 0-23 for hours in day
			if (e.getStart().get(Calendar.MONTH) == currentMonth
					&& (weekStart <= e.getStart().get(Calendar.DATE))
					&& (weekEnd >= e.getStart().get(Calendar.DATE))) {
				/*
				 * day.getModel().setValueAt(e.getName(),
				 * e.getStart().get(Calendar.HOUR_OF_DAY),
				 * e.getStart().get(Calendar.DAY_OF_WEEK)); if
				 * (e.getEnd().get(Calendar.MONTH) == currentMonth) {
				 * day.getModel().setValueAt("*",
				 * e.getEnd().get(Calendar.HOUR_OF_DAY),
				 * e.getEnd().get(Calendar.DAY_OF_WEEK)); } }
				 */

				if (personalViewSelected && teamViewSelected) {
					if (isThereAPersonalEventOnThisDate(testList, currentYear,
							e.getEnd().get(Calendar.MONTH),
							e.getEnd().get(Calendar.DATE)).size() != 0
							&& isThereATeamEventOnThisDate(testList,
									currentYear,
									e.getEnd().get(Calendar.MONTH),
									e.getEnd().get(Calendar.DATE)).size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(e
								.getEnd().get(Calendar.HOUR_OF_DAY), 2);
						// System.out.println("row passed in " +
						// Calendar.e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e
								.getEnd().get(Calendar.DATE), false, false, e
								.getEnd().get(Calendar.HOUR_OF_DAY), e.getEnd()
								.get(Calendar.DAY_OF_WEEK));
						day.getColumnModel()
								.getColumn(e.getEnd().get(Calendar.DAY_OF_WEEK))
								.setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(),
								e.getEnd().get(Calendar.HOUR_OF_DAY),
								e.getEnd().get(Calendar.DAY_OF_WEEK));
					} else if (isThereAPersonalEventOnThisDate(/*
																 * EventListModel.
																 * getInstance
																 * ().
																 * getEvents(),
																 */testList,
							currentYear, e.getEnd().get(Calendar.MONTH),
							e.getEnd().get(Calendar.DATE)).size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(e
								.getEnd().get(Calendar.HOUR_OF_DAY), 0);
						// System.out.println("row passed in " +
						// e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e
								.getEnd().get(Calendar.DATE), false, false, e
								.getEnd().get(Calendar.HOUR_OF_DAY), e.getEnd()
								.get(Calendar.DAY_OF_WEEK));
						day.getColumnModel()
								.getColumn(e.getEnd().get(Calendar.DAY_OF_WEEK))
								.setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(),
								e.getEnd().get(Calendar.HOUR_OF_DAY),
								e.getEnd().get(Calendar.DAY_OF_WEEK));
					} else if (isThereATeamEventOnThisDate(/*
															 * EventListModel.
															 * getInstance
															 * ().getEvents(),
															 */testList,
							currentYear, e.getEnd().get(Calendar.MONTH),
							e.getEnd().get(Calendar.DATE)).size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(e
								.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						// System.out.println("row passed in " +
						// e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e
								.getEnd().get(Calendar.DATE), false, false, e
								.getEnd().get(Calendar.HOUR_OF_DAY), e.getEnd()
								.get(Calendar.DAY_OF_WEEK));
						day.getColumnModel()
								.getColumn(e.getEnd().get(Calendar.DAY_OF_WEEK))
								.setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(),
								e.getEnd().get(Calendar.HOUR_OF_DAY),
								e.getEnd().get(Calendar.DAY_OF_WEEK));
					}
				} else if (personalViewSelected) {
					if (isThereAPersonalEventOnThisDate(/*
														 * EventListModel.
														 * getInstance
														 * ().getEvents(),
														 */testList,
							currentYear, e.getEnd().get(Calendar.MONTH),
							e.getEnd().get(Calendar.DATE)).size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(e
								.getEnd().get(Calendar.HOUR_OF_DAY), 0);
						// System.out.println("row passed in " +
						// e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e
								.getEnd().get(Calendar.DATE), false, false, e
								.getEnd().get(Calendar.HOUR_OF_DAY), e.getEnd()
								.get(Calendar.DAY_OF_WEEK));
						day.getColumnModel()
								.getColumn(e.getEnd().get(Calendar.DAY_OF_WEEK))
								.setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(),
								e.getEnd().get(Calendar.HOUR_OF_DAY),
								e.getEnd().get(Calendar.DAY_OF_WEEK));
					}
				} else if (teamViewSelected) {
					if (isThereATeamEventOnThisDate(/*
													 * EventListModel.getInstance
													 * ().getEvents(),
													 */testList, currentYear,
							e.getEnd().get(Calendar.MONTH),
							e.getEnd().get(Calendar.DATE)).size() != 0) {
						MyCellRenderer cellRender = new MyCellRenderer(e
								.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(day, e
								.getEnd().get(Calendar.DATE), false, false, e
								.getEnd().get(Calendar.HOUR_OF_DAY), e.getEnd()
								.get(Calendar.DAY_OF_WEEK));
						day.getColumnModel()
								.getColumn(e.getEnd().get(Calendar.DAY_OF_WEEK))
								.setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(),
								e.getEnd().get(Calendar.HOUR_OF_DAY),
								e.getEnd().get(Calendar.DAY_OF_WEEK));
					}
				}
				// day.getModel().setValueAt(e.getName(),
				// e.getEnd().get(Calendar.HOUR_OF_DAY),
				// e.getEnd().get(Calendar.DAY_OF_WEEK));
			}
		}
	}

	/**
	 * 
	 * @param day
	 * @param events
	 */
	public void populateDay(JTable day, List<Event> events) {
		int goflag = 0;
		populateDayNull(day);
		day.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer());
		for (Event e : events) {
			if (e.getStart().get(Calendar.YEAR) != currentYear) {
				if (currentMonth >= 11) {
					if (e.getStart().get(Calendar.MONTH) <= 0) {
						if (e.getStart().get(Calendar.YEAR) == (currentYear + 1)) {
							goflag = 1;
						}
					}
				} else if (currentMonth <= 0) {
					if (e.getStart().get(Calendar.MONTH) >= 11) {
						if (e.getStart().get(Calendar.YEAR) == (currentYear - 1)) {
							goflag = 1;
						}
					}
				}
			} else {
				goflag = 1;
			}

			if (goflag != 1) {
				continue;
			}
			// DAY_OF_WEEK returns number 1-7 for Sunday - Saturday
			// HOUR_OF_DAY returns number 0-23 for hours in day
			if (e.getStart().get(Calendar.MONTH) == currentMonth
					&& (weekStart <= e.getStart().get(Calendar.DATE))
					&& (weekEnd >= e.getStart().get(Calendar.DATE))) {
				/*
				 * day.getModel().setValueAt(e.getName(),
				 * e.getStart().get(Calendar.HOUR_OF_DAY),
				 * e.getStart().get(Calendar.DAY_OF_WEEK)); if
				 * (e.getEnd().get(Calendar.MONTH) == currentMonth) {
				 * day.getModel().setValueAt("*",
				 * e.getEnd().get(Calendar.HOUR_OF_DAY),
				 * e.getEnd().get(Calendar.DAY_OF_WEEK)); } }
				 */

				if (personalViewSelected && teamViewSelected) 
				{
					if (isThereAPersonalEventOnThisDate(testList, currentYear, 
							e.getEnd().get(Calendar.MONTH), e.getEnd().get(Calendar.DATE)).size() != 0 
							&& isThereATeamEventOnThisDate(testList, currentYear, e.getEnd().get(Calendar.MONTH), 
									e.getEnd().get(Calendar.DATE)).size() != 0) 
					{
						MyCellRenderer cellRender = new MyCellRenderer(e.getEnd().get(Calendar.HOUR_OF_DAY), 2);
						// System.out.println("row passed in " +
						// Calendar.e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e.getEnd().get(Calendar.DATE), 
								false, false, e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						day.getColumnModel().getColumn(1).setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(), e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
					} else if (isThereAPersonalEventOnThisDate(/*EventListModel.getInstance().getEvents(),*/
							testList,currentYear, e.getEnd().get(Calendar.MONTH), 
							e.getEnd().get(Calendar.DATE)).size() != 0) 
					{
						MyCellRenderer cellRender = new MyCellRenderer(e.getEnd().get(Calendar.HOUR_OF_DAY), 0);
						// System.out.println("row passed in " +
						// e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e.getEnd().get(Calendar.DATE), 
								false, false, e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						day.getColumnModel().getColumn(1).setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(),e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
					} else if (isThereATeamEventOnThisDate(/*EventListModel.getInstance().getEvents(),*/
							testList, currentYear, e.getEnd().get(Calendar.MONTH),
							e.getEnd().get(Calendar.DATE)).size() != 0) 
					{
						MyCellRenderer cellRender = new MyCellRenderer(e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						// System.out.println("row passed in " +
						// e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e.getEnd().get(Calendar.DATE), 
								false, false, e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						day.getColumnModel().getColumn(1).setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(), e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
					}
				} else if (personalViewSelected) {
					if (isThereAPersonalEventOnThisDate(/*EventListModel.getInstance().getEvents(),*/
						testList, currentYear, e.getEnd().get(Calendar.MONTH),
						e.getEnd().get(Calendar.DATE)).size() != 0) 
					{
						MyCellRenderer cellRender = new MyCellRenderer(e.getEnd().get(Calendar.HOUR_OF_DAY), 0);
						// System.out.println("row passed in " +
						// e.getEnd().get(Calendar.HOUR_OF_DAY));
						cellRender.getTableCellRendererComponent(day, e.getEnd().get(Calendar.DATE),
								false, false, e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						day.getColumnModel().getColumn(1).setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(), e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
					}
				} else if (teamViewSelected) {
					if (isThereATeamEventOnThisDate(/*EventListModel.getInstance().getEvents(),*/
						testList, currentYear, e.getEnd().get(Calendar.MONTH),
						e.getEnd().get(Calendar.DATE)).size() != 0) 
					{
						MyCellRenderer cellRender = new MyCellRenderer(e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						// System.out.println("row passed in " + i);
						cellRender.getTableCellRendererComponent(day, e.getEnd().get(Calendar.DATE), 
								false, false, e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
						day.getColumnModel().getColumn(1).setCellRenderer(cellRender);
						day.getModel().setValueAt(e.getName(), e.getEnd().get(Calendar.HOUR_OF_DAY), 1);
					}
				}
				// day.getModel().setValueAt(e.getName(),
				// e.getEnd().get(Calendar.HOUR_OF_DAY),1);
			}
		}
	}

}
