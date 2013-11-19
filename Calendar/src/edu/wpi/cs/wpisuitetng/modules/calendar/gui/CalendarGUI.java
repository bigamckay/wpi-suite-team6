package edu.wpi.cs.wpisuitetng.modules.calendar.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

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

//import org.jdesktop.swingx.JXMonthView;

public class CalendarGUI{
	
	private JPanel panelCalendar;
	private JTextField eventDescription;
	private JTextField eventLocation;
	private JTextField startTime;
	private JTextField startDay;
	private JTextField endTime;
	private JTextField endDay;
	private JTextField eventName;
	private JTable dayTable;
	private JTextField searchBar;
	//private JXMonthView monthView;

	/**
	 * Launch the application.
	 * Unnecessary - launched by Janeway
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarGUI window = new CalendarGUI();
					window.panelCalendar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public CalendarGUI() {
		initialize();
	}
	
	/**
	 * Gets the panel that contains the entire GUI
	 */
	public JPanel getPanel() {
		return panelCalendar;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		panelCalendar = new JPanel();
		panelCalendar.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		//panelCalendar.setResizable(false);
		panelCalendar.setBounds(100, 100, 1024, 768);
		//panelCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelCalendar.setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 1018, 80);
		titlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		titlePanel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		panelCalendar.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel lblCalendar = new JLabel("Calendar");
		lblCalendar.setBounds(369, 7, 188, 64);
		lblCalendar.setForeground(UIManager.getColor("Label.disabledForeground"));
		lblCalendar.setFont(new Font("Segoe UI", Font.ITALIC, 48));
		titlePanel.add(lblCalendar);
		
		searchBar = new JTextField();
		searchBar.setBounds(880, 51, 120, 20);
		searchBar.setText("Search...");
		titlePanel.add(searchBar);
		searchBar.setColumns(10);
		
		JPanel eventPanel = new JPanel();
		eventPanel.setBounds(5, 86, 292, 234);
		eventPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		eventPanel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		panelCalendar.add(eventPanel);
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
		panelCalendar.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Events", null, scrollPane, null);
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
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		dayTable.getColumnModel().getColumn(0).setMinWidth(35);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		dayTable.getColumnModel().getColumn(1).setMinWidth(200);
		scrollPane.setViewportView(dayTable);
		
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBounds(302, 86, 711, 650);
		calendarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		calendarPanel.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		panelCalendar.add(calendarPanel);
		calendarPanel.setLayout(null);
		
		JButton upButton = new JButton("UP");
		upButton.setForeground(UIManager.getColor("InternalFrame.activeTitleForeground"));
		upButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		upButton.setBounds(10, 0, 691, 32);
		calendarPanel.add(upButton);
		
		JButton downButton = new JButton("DOWN");
		downButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		downButton.setBounds(10, 618, 691, 32);
		calendarPanel.add(downButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 90, 691, 517);
		calendarPanel.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 39, 691, 40);
		calendarPanel.add(panel_1);
		
		/*monthView = new JXMonthView();
		monthView.setPreferredRowCount(1);
		monthView.setPreferredColumnCount(1);
		calendarPanel.add(monthView, BorderLayout.CENTER);*/
		
	}
}
