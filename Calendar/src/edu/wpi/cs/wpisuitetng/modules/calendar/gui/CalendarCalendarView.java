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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;


/**
 *  Contains the GUI elements of the Calendar Panel
 *
 */
public class CalendarCalendarView extends JTabbedPane{

	private JTable calendarDayHeader;
	private JTable monthDayHeaders;
	private JTable weekDayHeaders;
	private JTextField searchBar;
	
	public CalendarCalendarView() {
		initialize();
	}
	
	private void initialize() {
		
		//viewTabbedPane.setBounds(302, 54, 716, 686);
		setPreferredSize(new Dimension(716,686));
		setLocation(302, 54);
		
		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		addTab("Week View", null, weekPanel, null);
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
		addTab("Month View", null, monthPanel, null);
		
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
