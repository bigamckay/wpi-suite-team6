package edu.wpi.cs.wpisuitetng.modules.calendar.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;


/**
 *  Contains the GUI elements of the Calendar Panel
 *
 */
@SuppressWarnings("serial")
public class CalendarCalendarView extends JTabbedPane{

	private JTable monthDayHeaders;
	private JTable weekDayHeaders;
	
	public CalendarCalendarView() {
		initialize();
	}
	
	private void initialize() {
		
		//viewTabbedPane.setBounds(302, 54, 716, 686);
		setPreferredSize(new Dimension(716,652));
		setLocation(302, 54);
		
		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		addTab("Week View", null, weekPanel, null);
		weekPanel.setLayout(null);
		
		/*JButton monthUpButtonWV = new JButton("UP");
		monthUpButtonWV.setBounds(10, 0, 691, 32);
		weekPanel.add(monthUpButtonWV);
		monthUpButtonWV.setForeground(UIManager.getColor("Button.foreground"));
		monthUpButtonWV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JButton monthDownButtonWV = new JButton("DOWN");
		monthDownButtonWV.setBounds(10, 617, 691, 32);
		weekPanel.add(monthDownButtonWV);
		monthDownButtonWV.setFont(new Font("Segoe UI", Font.PLAIN, 16));*/
		
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
		
		/*JPanel monthNameWV = new JPanel();
		monthNameWV.setBounds(10, 39, 691, 40);
		weekPanel.add(monthNameWV);
		monthNameWV.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthNameWV.setBackground((Color) null);
		
		JLabel monthLabelWV = new JLabel("Month");
		monthLabelWV.setForeground(UIManager.getColor("Button.darkShadow"));
		monthLabelWV.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		monthNameWV.add(monthLabelWV);*/
		
		JPanel weekDays = new JPanel();
		weekDays.setBounds(10, 58, 691, 411);
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
		weekNextButton.setBounds(568, 15, 133, 26);
		weekPanel.add(weekNextButton);
		
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(null);
		monthPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthPanel.setBackground(UIManager.getColor("Button.select"));
		addTab("Month View", null, monthPanel, null);
		
		/*JButton monthUpButton = new JButton("UP");
		monthUpButton.setForeground(UIManager.getColor("Button.foreground"));
		monthUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		monthUpButton.setBounds(10, 0, 691, 32);
		monthPanel.add(monthUpButton);
		
		JButton monthDownButton = new JButton("DOWN");
		monthDownButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		monthDownButton.setBounds(10, 617, 691, 32);
		monthPanel.add(monthDownButton);*/
		
		JPanel monthViewButtons = new JPanel();
		monthViewButtons.setLayout(null);
		monthViewButtons.setBounds(12, 45, 685, 402);
		monthPanel.add(monthViewButtons);
		
		JButton sunday_0 = new JButton("");
		sunday_0.setBounds(0, 0, 98, 67);
		monthViewButtons.add(sunday_0);
		
		JButton sunday_1 = new JButton("");
		sunday_1.setBounds(0, 67, 98, 67);
		monthViewButtons.add(sunday_1);
		
		JButton sunday_2 = new JButton("");
		sunday_2.setBounds(0, 134, 98, 67);
		monthViewButtons.add(sunday_2);
		
		JButton sunday_3 = new JButton("");
		sunday_3.setBounds(0, 201, 98, 67);
		monthViewButtons.add(sunday_3);
		
		JButton sunday_4 = new JButton("");
		sunday_4.setBounds(0, 268, 98, 67);
		monthViewButtons.add(sunday_4);
		
		JButton sunday_5 = new JButton("");
		sunday_5.setBounds(0, 335, 98, 67);
		monthViewButtons.add(sunday_5);
		
		JButton monday_0 = new JButton("");
		monday_0.setBounds(98, 0, 98, 67);
		monthViewButtons.add(monday_0);
		
		JButton monday_1 = new JButton("");
		monday_1.setBounds(98, 67, 98, 67);
		monthViewButtons.add(monday_1);
		
		JButton monday_2 = new JButton("");
		monday_2.setBounds(98, 134, 98, 67);
		monthViewButtons.add(monday_2);
		
		JButton monday_3 = new JButton("");
		monday_3.setBounds(98, 201, 98, 67);
		monthViewButtons.add(monday_3);
		
		JButton monday_4 = new JButton("");
		monday_4.setBounds(98, 268, 98, 67);
		monthViewButtons.add(monday_4);
		
		JButton monday_5 = new JButton("");
		monday_5.setBounds(98, 335, 98, 67);
		monthViewButtons.add(monday_5);
		
		JButton tuesday_0 = new JButton("");
		tuesday_0.setBounds(196, 0, 98, 67);
		monthViewButtons.add(tuesday_0);
		
		JButton tuesday_1 = new JButton("");
		tuesday_1.setBounds(196, 67, 98, 67);
		monthViewButtons.add(tuesday_1);
		
		JButton tuesday_2 = new JButton("");
		tuesday_2.setBounds(196, 134, 98, 67);
		monthViewButtons.add(tuesday_2);
		
		JButton tuesday_3 = new JButton("");
		tuesday_3.setBounds(196, 201, 98, 67);
		monthViewButtons.add(tuesday_3);
		
		JButton tuesday_4 = new JButton("");
		tuesday_4.setBounds(196, 268, 98, 67);
		monthViewButtons.add(tuesday_4);
		
		JButton tuesday_5 = new JButton("");
		tuesday_5.setBounds(196, 335, 98, 67);
		monthViewButtons.add(tuesday_5);
		
		JButton wednesday_0 = new JButton("");
		wednesday_0.setBounds(294, 0, 98, 67);
		monthViewButtons.add(wednesday_0);
		
		JButton wednesday_1 = new JButton("");
		wednesday_1.setBounds(294, 67, 98, 67);
		monthViewButtons.add(wednesday_1);
		
		JButton wednesday_2 = new JButton("");
		wednesday_2.setBounds(294, 134, 98, 67);
		monthViewButtons.add(wednesday_2);
		
		JButton wednesday_3 = new JButton("");
		wednesday_3.setBounds(294, 201, 98, 67);
		monthViewButtons.add(wednesday_3);
		
		JButton wednesday_4 = new JButton("");
		wednesday_4.setBounds(294, 268, 98, 67);
		monthViewButtons.add(wednesday_4);
		
		JButton wednesday_5 = new JButton("");
		wednesday_5.setBounds(294, 335, 98, 67);
		monthViewButtons.add(wednesday_5);
		
		JButton thursday_0 = new JButton("");
		thursday_0.setBounds(392, 0, 98, 67);
		monthViewButtons.add(thursday_0);
		
		JButton thursday_1 = new JButton("");
		thursday_1.setBounds(392, 67, 98, 67);
		monthViewButtons.add(thursday_1);
		
		JButton thursday_2 = new JButton("");
		thursday_2.setBounds(392, 134, 98, 67);
		monthViewButtons.add(thursday_2);
		
		JButton thursday_3 = new JButton("");
		thursday_3.setBounds(392, 201, 98, 67);
		monthViewButtons.add(thursday_3);
		
		JButton thursday_4 = new JButton("");
		thursday_4.setBounds(392, 268, 98, 67);
		monthViewButtons.add(thursday_4);
		
		JButton thursday_5 = new JButton("");
		thursday_5.setBounds(392, 335, 98, 67);
		monthViewButtons.add(thursday_5);
		
		JButton friday_0 = new JButton("");
		friday_0.setBounds(490, 0, 98, 67);
		monthViewButtons.add(friday_0);
		
		JButton friday_1 = new JButton("");
		friday_1.setBounds(490, 67, 98, 67);
		monthViewButtons.add(friday_1);
		
		JButton friday_2 = new JButton("");
		friday_2.setBounds(490, 134, 98, 67);
		monthViewButtons.add(friday_2);
		
		JButton friday_3 = new JButton("");
		friday_3.setBounds(490, 201, 98, 67);
		monthViewButtons.add(friday_3);
		
		JButton friday_4 = new JButton("");
		friday_4.setBounds(490, 268, 98, 67);
		monthViewButtons.add(friday_4);
		
		JButton friday_5 = new JButton("");
		friday_5.setBounds(490, 335, 98, 67);
		monthViewButtons.add(friday_5);
		
		JButton saturday_0 = new JButton("");
		saturday_0.setBounds(588, 0, 98, 67);
		monthViewButtons.add(saturday_0);
		
		JButton saturday_1 = new JButton("");
		saturday_1.setBounds(588, 67, 98, 67);
		monthViewButtons.add(saturday_1);
		
		JButton saturday_2 = new JButton("");
		saturday_2.setBounds(588, 134, 98, 67);
		monthViewButtons.add(saturday_2);
		
		JButton saturday_3 = new JButton("");
		saturday_3.setBounds(588, 201, 98, 67);
		monthViewButtons.add(saturday_3);
		
		JButton saturday_4 = new JButton("");
		saturday_4.setBounds(588, 268, 98, 67);
		monthViewButtons.add(saturday_4);
		
		JButton saturday_5 = new JButton("");
		saturday_5.setBounds(588, 335, 98, 67);
		monthViewButtons.add(saturday_5);
		
		JPanel monthDays = new JPanel();
		monthDays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthDays.setBackground((Color) null);
		monthDays.setBounds(10, 18, 691, 433);
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
		
		/*JPanel monthName = new JPanel();
		monthName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		monthName.setBackground((Color) null);
		monthName.setBounds(10, 7, 691, 40);
		monthPanel.add(monthName);
		
		JLabel monthLabel = new JLabel("Month");
		monthLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		monthLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		monthName.add(monthLabel);*/
		
		JPanel yearPanel = new JPanel();
		addTab("Year View", null, yearPanel, null);
		setBackgroundAt(2, UIManager.getColor("Menu.selectionBackground"));
		
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
	}
}
