package edu.wpi.cs.wpisuitetng.modules.calendar.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Color;


public class GUIv4 {

	private JFrame frameCalendar;
	private JTextField eventDescription;
	private JTextField eventLocation;
	private JTextField startTime;
	private JTextField startDay;
	private JTextField endTime;
	private JTextField endDay;
	private JTextField eventName;
	private JTable dayTable;
	private JTextField searchBar;
	private JTable eventTable;
	private JTable calendarDayHeader;
	private JTable comitTable;
	private JTable monthDayHeaders;
	private JTable weekDayHeaders;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIv4 window = new GUIv4();
					window.frameCalendar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIv4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCalendar = new JFrame();
		frameCalendar.getContentPane().setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		frameCalendar.setResizable(false);
		frameCalendar.setBounds(100, 100, 1024, 768);
		frameCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCalendar.getContentPane().setLayout(null);
		
		JPanel eventPanel = new JPanel();
		eventPanel.setBounds(5, 86, 292, 234);
		eventPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		eventPanel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		frameCalendar.getContentPane().add(eventPanel);
		eventPanel.setLayout(null);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.setBounds(10, 203, 112, 23);
		eventPanel.add(btnCreateEvent);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setBounds(170, 203, 112, 23);
		eventPanel.add(btnDeleteEvent);
		
		eventDescription = new JTextField();
		eventDescription.setText("Description...");
		eventDescription.setBounds(10, 145, 272, 52);
		eventPanel.add(eventDescription);
		eventDescription.setColumns(10);
		
		eventLocation = new JTextField();
		eventLocation.setHorizontalAlignment(SwingConstants.CENTER);
		eventLocation.setText("Location");
		eventLocation.setBounds(10, 118, 272, 20);
		eventPanel.add(eventLocation);
		eventLocation.setColumns(10);
		
		startTime = new JTextField();
		startTime.setHorizontalAlignment(SwingConstants.CENTER);
		startTime.setText("00:00");
		startTime.setBounds(10, 87, 112, 20);
		eventPanel.add(startTime);
		startTime.setColumns(10);
		
		startDay = new JTextField();
		startDay.setHorizontalAlignment(SwingConstants.CENTER);
		startDay.setText("mm/dd/yyyy");
		startDay.setBounds(10, 56, 112, 20);
		eventPanel.add(startDay);
		startDay.setColumns(10);
		
		endTime = new JTextField();
		endTime.setHorizontalAlignment(SwingConstants.CENTER);
		endTime.setText("00:00");
		endTime.setBounds(170, 87, 112, 20);
		eventPanel.add(endTime);
		endTime.setColumns(10);
		
		endDay = new JTextField();
		endDay.setHorizontalAlignment(SwingConstants.CENTER);
		endDay.setText("mm/dd/yyyy");
		endDay.setBounds(170, 56, 112, 20);
		eventPanel.add(endDay);
		endDay.setColumns(10);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		lblStart.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(42, 38, 46, 14);
		eventPanel.add(lblStart);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		lblEnd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd.setBounds(204, 38, 46, 14);
		eventPanel.add(lblEnd);
		
		eventName = new JTextField();
		eventName.setHorizontalAlignment(SwingConstants.CENTER);
		eventName.setText("Event Name");
		eventName.setBounds(10, 11, 272, 20);
		eventPanel.add(eventName);
		eventName.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 324, 292, 412);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		frameCalendar.getContentPane().add(tabbedPane);
		
		JScrollPane dayScrollPane = new JScrollPane();
		tabbedPane.addTab("Day", null, dayScrollPane, null);
		tabbedPane.setBackgroundAt(0, UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		
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
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(45);
		dayTable.getColumnModel().getColumn(0).setMinWidth(45);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		dayTable.getColumnModel().getColumn(1).setMinWidth(200);
		dayScrollPane.setViewportView(dayTable);
		
		JScrollPane eventScrollPane = new JScrollPane();
		tabbedPane.addTab("Events", null, eventScrollPane, null);
		
		eventTable = new JTable();
		eventTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Date", "Event Name"
			}
		));
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		eventTable.getColumnModel().getColumn(0).setMinWidth(35);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		eventTable.getColumnModel().getColumn(1).setMinWidth(200);
		eventScrollPane.setViewportView(eventTable);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Commitments", null, scrollPane_2, null);
		
		comitTable = new JTable();
		comitTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Date", "Commitment Name"
			}
		));
		comitTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		comitTable.getColumnModel().getColumn(0).setMinWidth(35);
		comitTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		comitTable.getColumnModel().getColumn(1).setMinWidth(200);
		scrollPane_2.setViewportView(comitTable);
		
		JTabbedPane viewTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		viewTabbedPane.setBounds(302, 54, 716, 686);
		frameCalendar.getContentPane().add(viewTabbedPane);
		
		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		viewTabbedPane.addTab("Week View", null, weekPanel, null);
		weekPanel.setLayout(null);
		
		JButton monthUpButtonWV = new JButton("UP");
		monthUpButtonWV.setBounds(10, 0, 691, 32);
		weekPanel.add(monthUpButtonWV);
		monthUpButtonWV.setForeground(UIManager.getColor("Button.foreground"));
		monthUpButtonWV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JButton monthDownButtonWV = new JButton("DOWN");
		monthDownButtonWV.setBounds(10, 617, 691, 32);
		weekPanel.add(monthDownButtonWV);
		monthDownButtonWV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JButton weekPrevButton = new JButton("Previous Week");
		weekPrevButton.setBounds(10, 97, 133, 26);
		weekPanel.add(weekPrevButton);
		
		JPanel weekName = new JPanel();
		weekName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		weekName.setBackground((Color) null);
		weekName.setBounds(155, 89, 400, 40);
		weekPanel.add(weekName);
		
		JLabel weekLabel = new JLabel("Week (mm/dd/yyyy)");
		weekLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		weekLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		weekName.add(weekLabel);
		
		JPanel monthNameWV = new JPanel();
		monthNameWV.setBounds(10, 39, 691, 40);
		weekPanel.add(monthNameWV);
		monthNameWV.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthNameWV.setBackground((Color) null);
		
		JLabel monthLabelWV = new JLabel("Month");
		monthLabelWV.setForeground(UIManager.getColor("Button.darkShadow"));
		monthLabelWV.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		monthNameWV.add(monthLabelWV);
		
		JPanel weekDays = new JPanel();
		weekDays.setBounds(10, 140, 691, 411);
		weekPanel.add(weekDays);
		weekDays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		weekDays.setBackground((Color) null);
		weekDays.setLayout(new CardLayout(0, 0));
		
		JScrollPane weekScrollPane = new JScrollPane();
		weekDays.add(weekScrollPane, "name_37189630620584");
		
		weekDayHeaders = new JTable();
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
				"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
			}
		));
		
		weekScrollPane.setViewportView(weekDayHeaders);
		
		JButton weekNextButton = new JButton("Next Week");
		weekNextButton.setBounds(568, 97, 133, 26);
		weekPanel.add(weekNextButton);
		
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(null);
		monthPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthPanel.setBackground(UIManager.getColor("Button.select"));
		viewTabbedPane.addTab("Month View", null, monthPanel, null);
		
		JButton monthUpButton = new JButton("UP");
		monthUpButton.setForeground(UIManager.getColor("Button.foreground"));
		monthUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		monthUpButton.setBounds(10, 0, 691, 32);
		monthPanel.add(monthUpButton);
		
		JButton monthDownButton = new JButton("DOWN");
		monthDownButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		monthDownButton.setBounds(10, 617, 691, 32);
		monthPanel.add(monthDownButton);
		
		JPanel monthViewButtons = new JPanel();
		monthViewButtons.setLayout(null);
		monthViewButtons.setBounds(12, 117, 685, 485);
		monthPanel.add(monthViewButtons);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(0, 0, 98, 81);
		monthViewButtons.add(button_3);
		
		JButton button_6 = new JButton("");
		button_6.setBounds(0, 81, 98, 81);
		monthViewButtons.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setBounds(0, 162, 98, 81);
		monthViewButtons.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.setBounds(0, 243, 98, 81);
		monthViewButtons.add(button_8);
		
		JButton button_12 = new JButton("");
		button_12.setBounds(0, 324, 98, 81);
		monthViewButtons.add(button_12);
		
		JButton button_13 = new JButton("");
		button_13.setBounds(0, 405, 98, 81);
		monthViewButtons.add(button_13);
		
		JButton button_14 = new JButton("");
		button_14.setBounds(98, 0, 98, 81);
		monthViewButtons.add(button_14);
		
		JButton button_18 = new JButton("");
		button_18.setBounds(98, 81, 98, 81);
		monthViewButtons.add(button_18);
		
		JButton button_19 = new JButton("");
		button_19.setBounds(98, 162, 98, 81);
		monthViewButtons.add(button_19);
		
		JButton button_20 = new JButton("");
		button_20.setBounds(98, 243, 98, 81);
		monthViewButtons.add(button_20);
		
		JButton button_24 = new JButton("");
		button_24.setBounds(98, 324, 98, 81);
		monthViewButtons.add(button_24);
		
		JButton button_25 = new JButton("");
		button_25.setBounds(98, 405, 98, 81);
		monthViewButtons.add(button_25);
		
		JButton button_26 = new JButton("");
		button_26.setBounds(196, 0, 98, 81);
		monthViewButtons.add(button_26);
		
		JButton button_30 = new JButton("");
		button_30.setBounds(196, 81, 98, 81);
		monthViewButtons.add(button_30);
		
		JButton button_31 = new JButton("");
		button_31.setBounds(196, 162, 98, 81);
		monthViewButtons.add(button_31);
		
		JButton button_36 = new JButton("");
		button_36.setBounds(196, 243, 98, 81);
		monthViewButtons.add(button_36);
		
		JButton button_37 = new JButton("");
		button_37.setBounds(196, 324, 98, 81);
		monthViewButtons.add(button_37);
		
		JButton button_42 = new JButton("");
		button_42.setBounds(196, 405, 98, 81);
		monthViewButtons.add(button_42);
		
		JButton button_43 = new JButton("");
		button_43.setBounds(294, 0, 98, 81);
		monthViewButtons.add(button_43);
		
		JButton button_44 = new JButton("");
		button_44.setBounds(294, 81, 98, 81);
		monthViewButtons.add(button_44);
		
		JButton button_45 = new JButton("");
		button_45.setBounds(294, 162, 98, 81);
		monthViewButtons.add(button_45);
		
		JButton button_46 = new JButton("");
		button_46.setBounds(294, 243, 98, 81);
		monthViewButtons.add(button_46);
		
		JButton button_47 = new JButton("");
		button_47.setBounds(294, 324, 98, 81);
		monthViewButtons.add(button_47);
		
		JButton button_48 = new JButton("");
		button_48.setBounds(294, 405, 98, 81);
		monthViewButtons.add(button_48);
		
		JButton button_49 = new JButton("");
		button_49.setBounds(392, 0, 98, 81);
		monthViewButtons.add(button_49);
		
		JButton button_50 = new JButton("");
		button_50.setBounds(392, 81, 98, 81);
		monthViewButtons.add(button_50);
		
		JButton button_51 = new JButton("");
		button_51.setBounds(392, 162, 98, 81);
		monthViewButtons.add(button_51);
		
		JButton button_52 = new JButton("");
		button_52.setBounds(392, 243, 98, 81);
		monthViewButtons.add(button_52);
		
		JButton button_53 = new JButton("");
		button_53.setBounds(392, 324, 98, 81);
		monthViewButtons.add(button_53);
		
		JButton button_54 = new JButton("");
		button_54.setBounds(392, 405, 98, 81);
		monthViewButtons.add(button_54);
		
		JButton button_55 = new JButton("");
		button_55.setBounds(490, 0, 98, 81);
		monthViewButtons.add(button_55);
		
		JButton button_56 = new JButton("");
		button_56.setBounds(490, 81, 98, 81);
		monthViewButtons.add(button_56);
		
		JButton button_57 = new JButton("");
		button_57.setBounds(490, 162, 98, 81);
		monthViewButtons.add(button_57);
		
		JButton button_58 = new JButton("");
		button_58.setBounds(490, 243, 98, 81);
		monthViewButtons.add(button_58);
		
		JButton button_59 = new JButton("");
		button_59.setBounds(490, 324, 98, 81);
		monthViewButtons.add(button_59);
		
		JButton button_60 = new JButton("");
		button_60.setBounds(490, 405, 98, 81);
		monthViewButtons.add(button_60);
		
		JButton button_61 = new JButton("");
		button_61.setBounds(588, 0, 98, 81);
		monthViewButtons.add(button_61);
		
		JButton button_62 = new JButton("");
		button_62.setBounds(588, 81, 98, 81);
		monthViewButtons.add(button_62);
		
		JButton button_63 = new JButton("");
		button_63.setBounds(588, 162, 98, 81);
		monthViewButtons.add(button_63);
		
		JButton button_64 = new JButton("");
		button_64.setBounds(588, 243, 98, 81);
		monthViewButtons.add(button_64);
		
		JButton button_65 = new JButton("");
		button_65.setBounds(588, 324, 98, 81);
		monthViewButtons.add(button_65);
		
		JButton button_66 = new JButton("");
		button_66.setBounds(588, 405, 98, 81);
		monthViewButtons.add(button_66);
		
		JPanel monthDays = new JPanel();
		monthDays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthDays.setBackground((Color) null);
		monthDays.setBounds(10, 90, 691, 516);
		monthPanel.add(monthDays);
		monthDays.setLayout(new CardLayout(0, 0));
		
		JScrollPane monthScrollPane = new JScrollPane();
		monthDays.add(monthScrollPane, "name_15573090932055");
		
		monthDayHeaders = new JTable();
		monthDayHeaders.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			}
		));
		monthDayHeaders.setBackground(UIManager.getColor("Button.select"));
		monthScrollPane.setViewportView(monthDayHeaders);
		
		JPanel monthName = new JPanel();
		monthName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthName.setBackground((Color) null);
		monthName.setBounds(10, 39, 691, 40);
		monthPanel.add(monthName);
		
		JLabel monthLabel = new JLabel("Month");
		monthLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		monthLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		monthName.add(monthLabel);
		
		JPanel yearPanel = new JPanel();
		viewTabbedPane.addTab("Year View", null, yearPanel, null);
		viewTabbedPane.setBackgroundAt(2, UIManager.getColor("Menu.selectionBackground"));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 1018, 80);
		titlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		titlePanel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		frameCalendar.getContentPane().add(titlePanel);
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
		
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBounds(302, 78, 711, 650);
		calendarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		calendarPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		frameCalendar.getContentPane().add(calendarPanel);
		calendarPanel.setLayout(null);
		
		JButton upButton = new JButton("UP");
		upButton.setForeground(UIManager.getColor("InternalFrame.activeTitleForeground"));
		upButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		upButton.setBounds(10, 0, 691, 32);
		calendarPanel.add(upButton);
		
		JButton downButton = new JButton("DOWN");
		downButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		downButton.setBounds(10, 617, 691, 32);
		calendarPanel.add(downButton);
		
		JPanel calendarDayButtons = new JPanel();
		calendarDayButtons.setBounds(12, 117, 685, 485);
		calendarPanel.add(calendarDayButtons);
		calendarDayButtons.setLayout(null);
		
		JButton day00 = new JButton("");
		day00.setBounds(0, 0, 98, 81);
		calendarDayButtons.add(day00);
		
		JButton day10 = new JButton("");
		day10.setBounds(0, 81, 98, 81);
		calendarDayButtons.add(day10);
		
		JButton day20 = new JButton("");
		day20.setBounds(0, 162, 98, 81);
		calendarDayButtons.add(day20);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(0, 243, 98, 81);
		calendarDayButtons.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(0, 324, 98, 81);
		calendarDayButtons.add(button_5);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(0, 405, 98, 81);
		calendarDayButtons.add(button_1);
		
		JButton day01 = new JButton("");
		day01.setBounds(98, 0, 98, 81);
		calendarDayButtons.add(day01);
		
		JButton day11 = new JButton("");
		day11.setBounds(98, 81, 98, 81);
		calendarDayButtons.add(day11);
		
		JButton day21 = new JButton("");
		day21.setBounds(98, 162, 98, 81);
		calendarDayButtons.add(day21);
		
		JButton button_9 = new JButton("");
		button_9.setBounds(98, 243, 98, 81);
		calendarDayButtons.add(button_9);
		
		JButton button_10 = new JButton("");
		button_10.setBounds(98, 324, 98, 81);
		calendarDayButtons.add(button_10);
		
		JButton button_11 = new JButton("");
		button_11.setBounds(98, 405, 98, 81);
		calendarDayButtons.add(button_11);
		
		JButton day02 = new JButton("");
		day02.setBounds(196, 0, 98, 81);
		calendarDayButtons.add(day02);
		
		JButton day12 = new JButton("");
		day12.setBounds(196, 81, 98, 81);
		calendarDayButtons.add(day12);
		
		JButton day22 = new JButton("");
		day22.setBounds(196, 162, 98, 81);
		calendarDayButtons.add(day22);
		
		JButton button_15 = new JButton("");
		button_15.setBounds(196, 243, 98, 81);
		calendarDayButtons.add(button_15);
		
		JButton button_16 = new JButton("");
		button_16.setBounds(196, 324, 98, 81);
		calendarDayButtons.add(button_16);
		
		JButton button_17 = new JButton("");
		button_17.setBounds(196, 405, 98, 81);
		calendarDayButtons.add(button_17);
		
		JButton day03 = new JButton("");
		day03.setBounds(294, 0, 98, 81);
		calendarDayButtons.add(day03);
		
		JButton day13 = new JButton("");
		day13.setBounds(294, 81, 98, 81);
		calendarDayButtons.add(day13);
		
		JButton day23 = new JButton("");
		day23.setBounds(294, 162, 98, 81);
		calendarDayButtons.add(day23);
		
		JButton button_21 = new JButton("");
		button_21.setBounds(294, 243, 98, 81);
		calendarDayButtons.add(button_21);
		
		JButton button_22 = new JButton("");
		button_22.setBounds(294, 324, 98, 81);
		calendarDayButtons.add(button_22);
		
		JButton button_23 = new JButton("");
		button_23.setBounds(294, 405, 98, 81);
		calendarDayButtons.add(button_23);
		
		JButton day04 = new JButton("");
		day04.setBounds(392, 0, 98, 81);
		calendarDayButtons.add(day04);
		
		JButton day14 = new JButton("");
		day14.setBounds(392, 81, 98, 81);
		calendarDayButtons.add(day14);
		
		JButton day24 = new JButton("");
		day24.setBounds(392, 162, 98, 81);
		calendarDayButtons.add(day24);
		
		JButton button_27 = new JButton("");
		button_27.setBounds(392, 243, 98, 81);
		calendarDayButtons.add(button_27);
		
		JButton button_28 = new JButton("");
		button_28.setBounds(392, 324, 98, 81);
		calendarDayButtons.add(button_28);
		
		JButton button_29 = new JButton("");
		button_29.setBounds(392, 405, 98, 81);
		calendarDayButtons.add(button_29);
		
		JButton day05 = new JButton("");
		day05.setBounds(490, 0, 98, 81);
		calendarDayButtons.add(day05);
		
		JButton day15 = new JButton("");
		day15.setBounds(490, 81, 98, 81);
		calendarDayButtons.add(day15);
		
		JButton button_32 = new JButton("");
		button_32.setBounds(490, 162, 98, 81);
		calendarDayButtons.add(button_32);
		
		JButton button_33 = new JButton("");
		button_33.setBounds(490, 243, 98, 81);
		calendarDayButtons.add(button_33);
		
		JButton button_34 = new JButton("");
		button_34.setBounds(490, 324, 98, 81);
		calendarDayButtons.add(button_34);
		
		JButton button_35 = new JButton("");
		button_35.setBounds(490, 405, 98, 81);
		calendarDayButtons.add(button_35);
		
		JButton day06 = new JButton("");
		day06.setBounds(588, 0, 98, 81);
		calendarDayButtons.add(day06);
		
		JButton day16 = new JButton("");
		day16.setBounds(588, 81, 98, 81);
		calendarDayButtons.add(day16);
		
		JButton button_38 = new JButton("");
		button_38.setBounds(588, 162, 98, 81);
		calendarDayButtons.add(button_38);
		
		JButton button_39 = new JButton("");
		button_39.setBounds(588, 243, 98, 81);
		calendarDayButtons.add(button_39);
		
		JButton button_40 = new JButton("");
		button_40.setBounds(588, 324, 98, 81);
		calendarDayButtons.add(button_40);
		
		JButton button_41 = new JButton("");
		button_41.setBounds(588, 405, 98, 81);
		calendarDayButtons.add(button_41);
		
		JPanel calendarDays = new JPanel();
		calendarDays.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		calendarDays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		calendarDays.setBounds(10, 90, 691, 516);
		calendarPanel.add(calendarDays);
		calendarDays.setLayout(new CardLayout(0, 0));
		
		JScrollPane calendarScrollPane = new JScrollPane();
		calendarDays.add(calendarScrollPane, "name_34947127198587");
		
		calendarDayHeader = new JTable();
		calendarDayHeader.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		calendarDayHeader.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			}
		));
		calendarScrollPane.setViewportView(calendarDayHeader);
		
		JPanel calendarMonth = new JPanel();
		calendarMonth.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		calendarMonth.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		calendarMonth.setBounds(10, 39, 691, 40);
		calendarPanel.add(calendarMonth);
		
		JLabel calendarMonthLabel = new JLabel("Month");
		calendarMonthLabel.setForeground(UIManager.getColor("InternalFrame.borderDarkShadow"));
		calendarMonthLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		calendarMonth.add(calendarMonthLabel);
		
		
		
	}
}
