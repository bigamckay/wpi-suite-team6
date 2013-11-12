package mainView;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;

public class MainView {

	protected Shell shlSealTeam;
	private Text txtSearch;
	private Table timeTable;
	private Text txtDescription;
	private Text txtLocation;
	private Text fromTime;
	private Text toTime;
	private Text toDate;
	private Text fromDate;
	private Text txtEventName;
	private Table tableMonthNov;
	private Table tableMonthDec;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainView window = new MainView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSealTeam.open();
		shlSealTeam.layout();
		while (!shlSealTeam.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSealTeam = new Shell();
		shlSealTeam.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		shlSealTeam.setMinimumSize(new Point(1024, 768));
		shlSealTeam.setSize(450, 300);
		shlSealTeam.setText("Seal Team 6 - Calendar Module of Awesome");
		shlSealTeam.setLayout(new GridLayout(1, false));
		
		Composite headerComposite = new Composite(shlSealTeam, SWT.BORDER);
		headerComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		headerComposite.setLayout(null);
		
		Label lblCalendar = new Label(headerComposite, SWT.CENTER);
		lblCalendar.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		lblCalendar.setFont(SWTResourceManager.getFont("Segoe UI", 36, SWT.ITALIC));
		lblCalendar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblCalendar.setBounds(10, 10, 832, 70);
		lblCalendar.setText("Calendar");
		
		txtSearch = new Text(headerComposite, SWT.BORDER | SWT.SEARCH);
		txtSearch.setText("Search...");
		txtSearch.setBounds(848, 49, 150, 21);
		
		Composite bodyComposite = new Composite(shlSealTeam, SWT.BORDER);
		bodyComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		bodyComposite.setLayout(null);
		
		TabFolder tabFolder = new TabFolder(bodyComposite, SWT.BORDER);
		tabFolder.setBounds(4, 236, 302, 405);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		TabItem tbtmWeek = new TabItem(tabFolder, SWT.NONE);
		tbtmWeek.setText("Day");
		
		timeTable = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		timeTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tbtmWeek.setControl(timeTable);
		timeTable.setHeaderVisible(true);
		timeTable.setLinesVisible(true);
		
		TableColumn tblclmnTime = new TableColumn(timeTable, SWT.NONE);
		tblclmnTime.setWidth(60);
		tblclmnTime.setText("Time");
		
		TableItem tableTime7 = new TableItem(timeTable, SWT.NONE);
		tableTime7.setText(new String[] {"[]"});
		tableTime7.setText("7:00 AM");
		
		TableItem tableTime8 = new TableItem(timeTable, 0);
		tableTime8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tableTime8.setText(new String[] {"[]"});
		tableTime8.setText("8:00 AM");
		
		TableItem tableTime9 = new TableItem(timeTable, 0);
		tableTime9.setText(new String[] {"[]"});
		tableTime9.setText("9:00 AM");
		
		TableItem tableTime10 = new TableItem(timeTable, 0);
		tableTime10.setText(new String[] {"[]"});
		tableTime10.setText("10:00 AM");
		
		TableItem tableTime11 = new TableItem(timeTable, 0);
		tableTime11.setText(new String[] {"[]"});
		tableTime11.setText("11:00 AM");
		
		TableItem tableTime12 = new TableItem(timeTable, 0);
		tableTime12.setText(new String[] {"[]"});
		tableTime12.setText("12:00 PM");
		
		TableItem tableTime13 = new TableItem(timeTable, 0);
		tableTime13.setText(new String[] {"[]"});
		tableTime13.setText("1:00 PM");
		
		TableItem tableTime14 = new TableItem(timeTable, 0);
		tableTime14.setText(new String[] {"[]"});
		tableTime14.setText("2:00 PM");
		
		TableItem tableTime15 = new TableItem(timeTable, 0);
		tableTime15.setText(new String[] {"[]"});
		tableTime15.setText("3:00 PM");
		
		TableItem tableTime16 = new TableItem(timeTable, 0);
		tableTime16.setText(new String[] {"[]"});
		tableTime16.setText("4:00 PM");
		
		TableItem tableTime17 = new TableItem(timeTable, 0);
		tableTime17.setText(new String[] {"[]"});
		tableTime17.setText("5:00 PM");
		
		TableItem tableTime18 = new TableItem(timeTable, 0);
		tableTime18.setText(new String[] {"[]"});
		tableTime18.setText("6:00 PM");
		
		TableItem tableTime19 = new TableItem(timeTable, 0);
		tableTime19.setText(new String[] {"[]"});
		tableTime19.setText("7:00 PM");
		
		TableItem tableTime20 = new TableItem(timeTable, 0);
		tableTime20.setText(new String[] {"[]"});
		tableTime20.setText("8:00 PM");
		
		TableItem tableTime21 = new TableItem(timeTable, 0);
		tableTime21.setText(new String[] {"[]"});
		tableTime21.setText("9:00 PM");
		
		TableItem tableTime22 = new TableItem(timeTable, 0);
		tableTime22.setText(new String[] {"[]"});
		tableTime22.setText("10:00 PM");
		
		TableItem tableTime23 = new TableItem(timeTable, 0);
		tableTime23.setText(new String[] {"[]"});
		tableTime23.setText("11:00 PM");
		
		TableItem tableTime24 = new TableItem(timeTable, 0);
		tableTime24.setText(new String[] {"[]"});
		tableTime24.setText("12:00 AM");
		
		TabItem tbtmEvents = new TabItem(tabFolder, SWT.NONE);
		tbtmEvents.setText("Events");
		
		Composite eventComposite = new Composite(bodyComposite, SWT.BORDER);
		eventComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		eventComposite.setBounds(4, 4, 303, 228);
		eventComposite.setLayout(null);
		
		Button btnCreateEvent = new Button(eventComposite, SWT.NONE);
		btnCreateEvent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCreateEvent.setBounds(10, 197, 120, 25);
		btnCreateEvent.setText("Create Event");
		
		Button btnEditEvent = new Button(eventComposite, SWT.NONE);
		btnEditEvent.setBounds(170, 197, 120, 25);
		btnEditEvent.setText("Edit Event");
		
		txtDescription = new Text(eventComposite, SWT.BORDER);
		txtDescription.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtDescription.setText("Description...");
		txtDescription.setToolTipText("Description...");
		txtDescription.setBounds(10, 142, 279, 49);
		
		txtLocation = new Text(eventComposite, SWT.BORDER | SWT.CENTER);
		txtLocation.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtLocation.setText("Location");
		txtLocation.setToolTipText("Location");
		txtLocation.setBounds(10, 115, 279, 21);
		
		fromTime = new Text(eventComposite, SWT.BORDER | SWT.CENTER);
		fromTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		fromTime.setText("Time");
		fromTime.setBounds(10, 88, 120, 21);
		
		toTime = new Text(eventComposite, SWT.BORDER | SWT.CENTER);
		toTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toTime.setText("Time");
		toTime.setBounds(170, 88, 120, 21);
		
		toDate = new Text(eventComposite, SWT.BORDER | SWT.CENTER);
		toDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toDate.setText("Date");
		toDate.setBounds(170, 61, 120, 21);
		
		fromDate = new Text(eventComposite, SWT.BORDER | SWT.CENTER);
		fromDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		fromDate.setText("Date");
		fromDate.setBounds(10, 61, 120, 21);
		
		Label lblFrom = new Label(eventComposite, SWT.CENTER);
		lblFrom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblFrom.setBounds(44, 40, 55, 15);
		lblFrom.setText("From");
		
		Label lblTo = new Label(eventComposite, SWT.CENTER);
		lblTo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblTo.setBounds(203, 40, 55, 15);
		lblTo.setText("To");
		
		txtEventName = new Text(eventComposite, SWT.BORDER | SWT.CENTER);
		txtEventName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtEventName.setText("Event Name");
		txtEventName.setBounds(10, 10, 280, 21);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(bodyComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(313, 4, 686, 637);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite_1 = new Composite(scrolledComposite, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		
		tableMonthNov = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableMonthNov.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		tableMonthNov.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tableMonthNov.setBounds(5, 40, 656, 480);
		tableMonthNov.setHeaderVisible(true);
		tableMonthNov.setLinesVisible(true);
		
		TableColumn tblclmnSunday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnSunday.setWidth(93);
		tblclmnSunday.setText("Sunday");
		
		TableColumn tblclmnMonday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnMonday.setWidth(93);
		tblclmnMonday.setText("Monday");
		
		TableColumn tblclmnTuesday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnTuesday.setWidth(93);
		tblclmnTuesday.setText("Tuesday");
		
		TableColumn tblclmnWednesday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnWednesday.setWidth(93);
		tblclmnWednesday.setText("Wednesday");
		
		TableColumn tblclmnThursday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnThursday.setWidth(93);
		tblclmnThursday.setText("Thursday");
		
		TableColumn tblclmnFriday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnFriday.setWidth(93);
		tblclmnFriday.setText("Friday");
		
		TableColumn tblclmnSaturday = new TableColumn(tableMonthNov, SWT.NONE);
		tblclmnSaturday.setWidth(93);
		tblclmnSaturday.setText("Saturday");
		
		Label lblNovember = new Label(composite_1, SWT.NONE);
		lblNovember.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNovember.setAlignment(SWT.CENTER);
		lblNovember.setBounds(5, 5, 656, 29);
		lblNovember.setText("November 2013");
		
		Label lblDecember = new Label(composite_1, SWT.NONE);
		lblDecember.setText("December 2013");
		lblDecember.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblDecember.setAlignment(SWT.CENTER);
		lblDecember.setBounds(5, 526, 656, 29);
		
		tableMonthDec = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableMonthDec.setLinesVisible(true);
		tableMonthDec.setHeaderVisible(true);
		tableMonthDec.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tableMonthDec.setBounds(5, 561, 656, 480);
		
		TableColumn tableColumn = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn.setWidth(93);
		tableColumn.setText("Sunday");
		
		TableColumn tableColumn_1 = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn_1.setWidth(93);
		tableColumn_1.setText("Monday");
		
		TableColumn tableColumn_2 = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn_2.setWidth(93);
		tableColumn_2.setText("Tuesday");
		
		TableColumn tableColumn_3 = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn_3.setWidth(93);
		tableColumn_3.setText("Wednesday");
		
		TableColumn tableColumn_4 = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn_4.setWidth(93);
		tableColumn_4.setText("Thursday");
		
		TableColumn tableColumn_5 = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn_5.setWidth(93);
		tableColumn_5.setText("Friday");
		
		TableColumn tableColumn_6 = new TableColumn(tableMonthDec, SWT.NONE);
		tableColumn_6.setWidth(93);
		tableColumn_6.setText("Saturday");
		scrolledComposite.setContent(composite_1);
		scrolledComposite.setMinSize(composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}
}
