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
import java.util.Calendar;
import java.lang.Math;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;


/**
 *  Contains the GUI elements of the Calendar Panel
 *
 */
@SuppressWarnings("serial")
public class CalendarCalendarView extends JTabbedPane{

	private JTable monthView;
	private JTable weekDayHeaders;
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
	private String currentFocus = "someBullShitString";
	
	public int yearNullRan = 0;
	
	public JTable[] monthArray;
	private final DefaultTableModel clearedModel = new DefaultTableModel(
			new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			);
	
	public int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	public int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	private String month = new String();
	
	
	public CalendarCalendarView() {
		initialize();
	}
	
	private void initialize() {
		
		//viewTabbedPane.setBounds(302, 54, 716, 686);
		setPreferredSize(new Dimension(716,550));
		setLocation(302, 54);
		
		/**
		 * Week View
		 */
		
		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		weekPanel.setFocusable(true);
		addTab("Week View", null, weekPanel, null);
		weekPanel.setLayout(null);
		
		JButton weekPrevButton = new JButton("Previous Week");
		weekPrevButton.setBounds(10, 15, 133, 26);
		weekPanel.add(weekPrevButton);
		
		JPanel weekName = new JPanel();
		weekName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		weekName.setBackground((Color) null);
		weekName.setBounds(155, 7, 400, 40);
		weekPanel.add(weekName);
		
		JLabel weekLabel = new JLabel("Start Day / End Day");
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
		weekDayHeaders.setModel(new DefaultTableModel(
			new Object[][] {
				{"12:00 AM", null, null, null, null, null, null, null},
				{"1:00 AM", null, null, null, null, null, null, null},
				{"2:00 AM", null, null, null, null, null, null, null},
				{"3:00 AM", null, null, null, null, null, null, null},
				{"4:00 AM", null, null, null, null, null, null, null},
				{"5:00 AM", null, null, null, null, null, null, null},
				{"6:00 AM", null, null, null, null, null, null, null},
				{"7:00 AM", null, null, null, null, null, null, null},
				{"8:00 AM", null, null, null, null, null, null, null},
				{"9:00 AM", null, null, null, null, null, null, null},
				{"10:00 AM", null, null, null, null, null, null, null},
				{"11:00 AM", null, null, null, null, null, null, null},
				{"12:00 PM", null, null, null, null, null, null, null},
				{"1:00 PM", null, null, null, null, null, null, null},
				{"2:00 PM", null, null, null, null, null, null, null},
				{"3:00 PM", null, null, null, null, null, null, null},
				{"4:00 PM", null, null, null, null, null, null, null},
				{"5:00 PM", null, null, null, null, null, null, null},
				{"6:00 PM", null, null, null, null, null, null, null},
				{"7:00 PM", null, null, null, null, null, null, null},
				{"8:00 PM", null, null, null, null, null, null, null},
				{"9:00 PM", null, null, null, null, null, null, null},
				{"10:00 PM", null, null, null, null, null, null, null},
				{"11:00 PM", null, null, null, null, null, null, null},
			},
			new String[] {
				"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			}
		));
		
		weekScrollPane.setViewportView(weekDayHeaders);
		
		JButton weekNextButton = new JButton("Next Week");
		weekNextButton.setBounds(568, 15, 133, 26);
		weekPanel.add(weekNextButton);
		
		/**
		 * Month View
		 */
		
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(null);
		monthPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthPanel.setBackground(UIManager.getColor("Button.select"));
		addTab("Month View", null, monthPanel, null);
		
		JScrollPane monthDays = new JScrollPane();
		monthDays.setBounds(12, 50, 685, 425);
		
		monthView = new JTable();
		monthView.setBounds(1, 1, 684, 402);
		monthView.setRowSelectionAllowed(false);
		monthView.getTableHeader().setReorderingAllowed(false);
		monthView.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
			}
		));
		monthView.setRowHeight(67);
		monthDays.setViewportView(monthView);
		monthPanel.add(monthDays);
		
		JPanel monthName = new JPanel();
		monthName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthName.setBackground((Color) null);
		monthName.setBounds(10, 7, 691, 40);
		monthPanel.add(monthName);
		
		JLabel monthLabel = new JLabel(getCurrentMonth(currentMonth));
		monthLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		monthLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		monthName.add(monthLabel);
		
		/**
		 * Year View
		 */
		
		JPanel yearPanel = new JPanel();
		yearPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		addTab("Year View", null, yearPanel, null);
		yearPanel.setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		yearPanel.add(layeredPane, "name_37461493840748");
		layeredPane.setLayout(null);
		
		JScrollPane JanScrollPane = new JScrollPane();
		JanScrollPane.setViewportBorder(null);
		JanScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JanScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JanScrollPane.setBounds(10, 10, 224, 118);
		layeredPane.setLayer(JanScrollPane, 0);
		JanScrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		layeredPane.add(JanScrollPane);
		
		JanDayTable = new JTable();
		JanDayTable.setRowSelectionAllowed(false);
		JanDayTable.getTableHeader().setReorderingAllowed(false);
		JanDayTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
			}
		));
		
		//populateYear(monthArray);
		
		JanDayTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(2).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(3).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(4).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		JanDayTable.getColumnModel().getColumn(6).setPreferredWidth(15);
		JanScrollPane.setViewportView(JanDayTable);
		
		JScrollPane FebScrollPane = new JScrollPane();
		FebScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		FebScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		FebScrollPane.setBounds(244, 10, 224, 118);
		layeredPane.add(FebScrollPane);
		
		FebDayTable = new JTable();
		FebDayTable.setRowSelectionAllowed(false);
		FebDayTable.getTableHeader().setReorderingAllowed(false);
		FebDayTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
			}
		));
		FebScrollPane.setViewportView(FebDayTable);
		
		JScrollPane MarScrollPane = new JScrollPane();
		MarScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		MarScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		MarScrollPane.setBounds(478, 10, 224, 118);
		layeredPane.add(MarScrollPane);
		
		MarDayTable = new JTable();
		MarDayTable.setRowSelectionAllowed(false);
		MarDayTable.getTableHeader().setReorderingAllowed(false);
		MarDayTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
			}
		));
		MarScrollPane.setViewportView(MarDayTable);
		
		JScrollPane AprScrollPane = new JScrollPane();
		AprScrollPane.setViewportBorder(null);
		AprScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		AprScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		AprScrollPane.setAlignmentY(0.0f);
		AprScrollPane.setBounds(10, 138, 224, 118);
		layeredPane.add(AprScrollPane);
		
		AprDayTable = new JTable();
		AprDayTable.setRowSelectionAllowed(false);
		AprDayTable.getTableHeader().setReorderingAllowed(false);
		AprDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		AprScrollPane.setViewportView(AprDayTable);
		
		JScrollPane MayScrollPane = new JScrollPane();
		MayScrollPane.setViewportBorder(null);
		MayScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		MayScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		MayScrollPane.setAlignmentY(0.0f);
		MayScrollPane.setBounds(244, 138, 224, 118);
		layeredPane.add(MayScrollPane);
		
		MayDayTable = new JTable();
		MayDayTable.setRowSelectionAllowed(false);
		MayDayTable.getTableHeader().setReorderingAllowed(false);
		MayDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		MayScrollPane.setViewportView(MayDayTable);
		
		JScrollPane JunScrollPane = new JScrollPane();
		JunScrollPane.setViewportBorder(null);
		JunScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JunScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JunScrollPane.setAlignmentY(0.0f);
		JunScrollPane.setBounds(478, 138, 224, 118);
		layeredPane.add(JunScrollPane);
		
		JunDayTable = new JTable();
		JunDayTable.setRowSelectionAllowed(false);
		JunDayTable.getTableHeader().setReorderingAllowed(false);
		JunDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		JunScrollPane.setViewportView(JunDayTable);
		
		JScrollPane JulScrollPane = new JScrollPane();
		JulScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JulScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JulScrollPane.setBounds(10, 266, 224, 118);
		layeredPane.add(JulScrollPane);
		
		JulDayTable = new JTable();
		JulDayTable.setRowSelectionAllowed(false);
		JulDayTable.getTableHeader().setReorderingAllowed(false);
		JulDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		JulScrollPane.setViewportView(JulDayTable);
		
		JScrollPane AugScrollPane = new JScrollPane();
		AugScrollPane.setViewportBorder(null);
		AugScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		AugScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		AugScrollPane.setAlignmentY(0.0f);
		AugScrollPane.setBounds(244, 266, 224, 118);
		layeredPane.add(AugScrollPane);
		
		AugDayTable = new JTable();
		AugDayTable.setRowSelectionAllowed(false);
		AugDayTable.getTableHeader().setReorderingAllowed(false);
		AugDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		AugScrollPane.setViewportView(AugDayTable);
		
		JScrollPane SepScrollPane = new JScrollPane();
		SepScrollPane.setViewportBorder(null);
		SepScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		SepScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SepScrollPane.setAlignmentY(0.0f);
		SepScrollPane.setBounds(478, 266, 224, 118);
		layeredPane.add(SepScrollPane);
		
		SepDayTable = new JTable();
		SepDayTable.setRowSelectionAllowed(false);
		SepDayTable.getTableHeader().setReorderingAllowed(false);
		SepDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		SepScrollPane.setViewportView(SepDayTable);
		
		JScrollPane OctScrollPane = new JScrollPane();
		OctScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		OctScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		OctScrollPane.setBounds(10, 394, 224, 118);
		layeredPane.add(OctScrollPane);
		
		OctDayTable = new JTable();
		OctDayTable.setRowSelectionAllowed(false);
		OctDayTable.getTableHeader().setReorderingAllowed(false);
		OctDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		OctScrollPane.setViewportView(OctDayTable);
		
		JScrollPane NovScrollPane = new JScrollPane();
		NovScrollPane.setViewportBorder(null);
		NovScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		NovScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		NovScrollPane.setAlignmentY(0.0f);
		NovScrollPane.setBounds(244, 394, 224, 118);
		layeredPane.add(NovScrollPane);
		
		NovDayTable = new JTable();
		NovDayTable.setRowSelectionAllowed(false);
		NovDayTable.getTableHeader().setReorderingAllowed(false);
		NovDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		NovScrollPane.setViewportView(NovDayTable);
		
		JScrollPane DecScrollPane = new JScrollPane();
		DecScrollPane.setViewportBorder(null);
		DecScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		DecScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DecScrollPane.setAlignmentY(0.0f);
		DecScrollPane.setBounds(478, 394, 224, 118);
		layeredPane.add(DecScrollPane);
		
		DecDayTable = new JTable();
		DecDayTable.setRowSelectionAllowed(false);
		DecDayTable.getTableHeader().setReorderingAllowed(false);
		DecDayTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
				}
			));
		DecScrollPane.setViewportView(DecDayTable);
		
		/*JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 1018, 80);
		titlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		titlePanel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel lblCalendar = new JLabel("Calendar");
		lblCalendar.setBounds(700, 7, 188, 64);
		lblCalendar.setForeground(UIManager.getColor("Label.disabledForeground"));
		lblCalendar.setFont(new Font("Segoe UI", Font.ITALIC, 48));
		titlePanel.add(lblCalendar);
		
		searchBar = new JTextField();
		searchBar.setBounds(12, 51, 164, 20);
		searchBar.setText("Search...");
		titlePanel.add(searchBar);
		searchBar.setColumns(10);
		
		JPanel calendarDayButtons = new JPanel();
		calendarDayButtons.setBounds(12, 117, 685, 485);
		calendarPanel.add(calendarDayButtons);
		calendarDayButtons.setLayout(null);*/
		
		monthArray = new JTable[]{
				JanDayTable,
				FebDayTable,
				MarDayTable,
				AprDayTable,
				MayDayTable,
				JunDayTable,
				JulDayTable,
				AugDayTable,
				SepDayTable,
				OctDayTable,
				NovDayTable,
				DecDayTable
				};

		//populateMonth(monthArray[0], 2, 31);
		//populateMonth(monthArray[1], 6, 28);
		simulateYear(currentYear);
		populateYear(monthArray, currentYear);
	}
	
	public int populateMonth(JTable month, int startDay, int daysInMonth){
		Integer dayCounter = 1;
		int j=startDay;
		for(int i=0; i<6; i++){
			for(; j<7; j++){
				month.getModel().setValueAt(dayCounter.toString(), i, j);
				if (dayCounter == daysInMonth){
					return j+1;
				}
				dayCounter++;
			}
			j =0;
		}
		return j+1;
	}
	
	public int simulateMonth(int startDay, int daysInMonth) {
		Integer dayCounter = 1;
		int j=startDay;
		for(int i=0; i<6; ++i){
			for(; j<7; ++j){
				if (dayCounter == daysInMonth){
					return j+1;
				}
				dayCounter++;
			}
			j =0;
		}
		return j+1;
	}
	
	public void populateMonthNull(JTable month){
		int j=0;
		for(int i=0; i<6; ++i){
			for(; j<7; ++j){
				month.getModel().setValueAt(null, i, j);
			}
			j =0;
		}
		return;
	}

	int determineStartingDay(int year) 	// taken from http://mathforum.org/library/drmath/view/55837.html
	{									// modified specifically to find the first day of the year
		double startDay;

		year -= 1;

		startDay = (35 + year + Math.floor(year / 4) - Math.floor(year / 100) + Math.floor(year / 400) + 1);
		startDay = startDay % 7;

		return (int)startDay;
	}
	
	public void populateYear(JTable[] monthArray, int year){
		int startDay = 0;
		for(int i=0; i<12; i++){
			populateMonthNull(monthArray[i]);
			if(i==0)
				startDay = populateMonth(monthArray[i], determineStartingDay(year), daysInMonth(i,year));
			else
				startDay = populateMonth(monthArray[i], startDay, daysInMonth(i,year));
		}
		return;
	}
	
	public void simulateYear(int year) {
		int startDay = 0;
		for(int i=0; i<12; ++i){
			if(i==0) {
				startDay = simulateMonth(determineStartingDay(year), daysInMonth(i,year));
				
				if(currentMonth == 0) {
					startDay = determineStartingDay(year);
					populateMonth(monthView, startDay, daysInMonth(i,year));
				}
			}
			else if (i==currentMonth) {
				populateMonth(monthView, startDay, daysInMonth(i,year));
				return;
			}
			else
				startDay = simulateMonth(startDay, daysInMonth(i,year));
		}
		return;
	}
	
	public void populateYearNull(){
			for(int i=0; i<12; i++){
				monthArray[i].setModel(clearedModel);
			}
			yearNullRan++;
			System.out.println("Worked!" + yearNullRan);
			return;
		}
	
	public int daysInMonth(int month, int year){
		int result = 0;								// for the respective month value and prints that month and the year to console
		boolean leapYear = isLeapYear(year);
		
		switch(month)
		{
		case 0:
			result = 31;
			break;
		case 1:
			if(leapYear)
			{
				result = 29;
			}
			else
			{
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
	
	public boolean isLeapYear(int year)		//takes an input year and checks to see if it's a leap year: 1 for true, 0 for false
	{
		boolean leapYear;

		if((year % 400) == 0)
		{
			leapYear = true;
		}
		else if((year % 100) == 0)
		{
			leapYear = false;
		}
		else if((year % 4) == 0)
		{
			leapYear = true;
		}
		else
		{
			leapYear = false;
		}

		return leapYear;
	}
	
	public String getCurrentMonth(int currMonth)
	{
		switch(currMonth)
		{
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

	public String getCurrentFocus()
	{
		return this.currentFocus;
	}	
	
	public JTable getMonthView() {
		return monthView;
	}
}
