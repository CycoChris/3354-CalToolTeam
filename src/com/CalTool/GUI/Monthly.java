package com.CalTool.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class Monthly extends JPanel{
	
	// Object used to get info about current month 
//	private Calendar calendar;
//	Date date;
	YearMonth currYearMonth;
	
	//headers for the table
    private JTable jtable;    
    private CellTableModel tableModel;
    
    // Objects for the panel above the monthly calendar 
    private JButton prev, next;
    private JLabel currMonth;	// The label used to distinguish which month is being displayed
    private SimpleDateFormat currMonthFormatter;
    
    // Object used to Fill table
    private String[] days;
    ArrayList<CellInformation> data;
	
	public Monthly() {
		// Instantiate the super class (for the JPanel) and all other needed objects
 		super();
 		currMonthFormatter = new SimpleDateFormat("MM/yyyy");
		currYearMonth = YearMonth.now();
		// TODO: move to constructor
		days = new String[] {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
		data = new ArrayList<CellInformation>();

		// Set the correct layout (BoxLayout will waste more space with the buttons)
		BorderLayout layout = new BorderLayout();
 		setLayout(layout);
 		
 		// Add the initial data at first
 		populateData();
 		
 		// Define all attributes of the table
 		tableModel = new CellTableModel(data, days);
 		jtable = new JTable(tableModel);
 		jtable.setDefaultRenderer(CellInformation.class, new Cell(tableModel));
 		jtable.setDefaultEditor(CellInformation.class, new Cell(tableModel));
 		jtable.setRowHeight(180);
 		
 		
 		// Add the menu panel and the table to the main JPanel
 		add(getMenuPanel(), BorderLayout.NORTH);
 		add(new JScrollPane(jtable));
	}
	
	// This can be the function that calls a JSON parser to get all of the information
	public void populateData() {
		
		// Clear all residual data
		data.clear();		
		System.out.println("data size() = " + data.size());
		
		// Calculate the number of days in the current and previous months
 		int daysInCurrMonth = currYearMonth.lengthOfMonth();
 		int daysInPrevMonth = currYearMonth.plusMonths(-1).lengthOfMonth();
 		
 		currYearMonth.plusMonths(1);	// Get it back to the current month
 		
 		// Calculate which day is the first day of the current month, and how many days are in the current month
 		// Do modulo 7 to find which weekday it is (Sun = 0, Mon = 1, Tues = 2, Wed = 3, Thurs = 4, Fri = 5, Sat = 6)
 		int weekDayCurrMonth = currYearMonth.atDay(1).getDayOfWeek().getValue() % 7;
 		


 		System.out.println("Month = " + currYearMonth.getMonthValue());
 		System.out.println("Days in a month = " + currYearMonth.lengthOfMonth());
		System.out.println("weekday curr month = " + currYearMonth.atDay(1).getDayOfWeek()); 
		System.out.println(currYearMonth.atDay(1).getDayOfWeek().getValue()%7);
		// Sun = 0, Mon = 1, Tues = 2, Wed = 3, Thurs = 4, Fri = 5, Sat = 6
		
		
 		// Add in all of the days still visible from the previous month
		for (int i = 0; i < weekDayCurrMonth; i ++) {
			data.add(0, new CellInformation(daysInPrevMonth - i, new String[] {"NOT IN CURR", "MONTH"}));
		}

		System.out.println("data size() = " + data.size());
		
		// Add all of the days visible for the current month
		for (int i = 1; i <= daysInCurrMonth; i ++) {
			data.add(new CellInformation(i, new String[] {"Event 1", "Event 2", "3", "4", "5"}));
		}


		System.out.println("data size() = " + data.size());
		
		// Add all of the days visible from the next month
		int i = 1;
		while (data.size()%7 != 0) {
			data.add(new CellInformation(i, new String[] {"NEXT", "MONTH"}));
			i ++;
		}

		System.out.println("data size() = " + data.size());
	}
	
	public JPanel getMenuPanel() {
		// create and add buttons
 		JPanel menuPanel = new JPanel();
 		prev = new JButton();
 		next = new JButton();
 		currMonth = new JLabel();
 		
 		// Set all attributes of the subcomponents in the menu panel
 		prev.setText("<<");
 		prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Change the month value 
				currYearMonth = currYearMonth.plusMonths(-1);
				currMonth.setText(currYearMonth.getMonthValue() + "/" + currYearMonth.getYear());
				
				// Update the data and the table
				populateData();
				tableModel.fireTableDataChanged();	// To update the table				
			}
		});
 		next.setText(">>");
 		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Change the month value 
				currYearMonth = currYearMonth.plusMonths(1);
				currMonth.setText(currYearMonth.getMonthValue() + "/" + currYearMonth.getYear());
				
				// Update the data and the table
				populateData();
				tableModel.fireTableDataChanged();	// To update the table
			}
		});
 		
		currMonth.setText(currYearMonth.getMonthValue() + "/" + currYearMonth.getYear());
 		
 		// Add all required elements and return
 		menuPanel.add(prev);
 		menuPanel.add(Box.createHorizontalStrut(10));
 		menuPanel.add(currMonth);
 		menuPanel.add(Box.createHorizontalStrut(10));
 		menuPanel.add(next);
 		return menuPanel;
	}
	
	public Container getPanel() {
		return this;
	}
	
}

// A class used as a data structure for the information shown in each cell
class CellInformation {
	int day;
	String[] events;	// This can be changed to an object later on
	boolean[] completed;
	
	public CellInformation(int day, String[] events) {
		this.day = day;
		this.events = events;
		completed = new boolean[events.length];
	}
}

// A class for making each cell in the table look exactly how we need it
class CellTableModel extends AbstractTableModel {

	List cellInfo;
	String[] columnHeaders;
	
	public CellTableModel(List cellInfo, String[] columnHeaders) {
		this.cellInfo = cellInfo;
		this.columnHeaders = columnHeaders;
	}
	
	@Override
	public int getRowCount() {
		return (cellInfo == null) ? 0 : cellInfo.size()/7; 
	}

	@Override
	public int getColumnCount() {
		return columnHeaders.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {	// TODO: Change so it gets the correct one
		
		if (cellInfo == null) {
			return null;
		}
		
		if (rowIndex*7 + columnIndex >= cellInfo.size()) {
			return null;
		} else 
			return cellInfo.get(rowIndex*7 + columnIndex);
	}
	
	public Class getColumnClass(int columnIndex) { 
		return CellInformation.class; 
	}
	
	public boolean isCellEditable(int columnIndex, int rowIndex) { 
		return true;
	}
	
	public String getColumnName (int columnIndex) {
		return columnHeaders[columnIndex];
	}
}

class Cell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	JPanel panel;
	JLabel dayNum;
	JPanel[] aligner;
	JPanel dayNumAligner;
	JLabel[] label;
	JCheckBox[] completed;
	CellInformation cellInfo;
	CellTableModel tableModel;
	CheckBoxActionListener[] actionListeners;
	
	public Cell(CellTableModel tableModel) {
		this.tableModel = tableModel;
		panel = new JPanel();
		dayNum = new JLabel();
	    label = new JLabel[20];
	    completed = new JCheckBox[20];
		aligner = new JPanel[20];
		actionListeners = new CheckBoxActionListener[20];
	    
	    // Change the Layout Manager of the JPanel
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    // Style the day number of each cell and make it left aligned
	    dayNumAligner = new JPanel();
	    dayNumAligner.setLayout(new FlowLayout(FlowLayout.LEFT));
	    dayNumAligner.add(dayNum);	
	    dayNum.setBorder(BorderFactory.createLineBorder(Color.blue));
	    dayNum.setOpaque(true);
	    dayNum.setBackground(Color.lightGray);
	    
	    // Start adding in all of the elements
	    panel.add(dayNumAligner);
	    for (int i = 0; i < label.length; i ++) {
	    	// Create all objects
	    	label[i] = new JLabel();
	    	completed[i] = new JCheckBox();
	    	aligner[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    	actionListeners[i] = new CheckBoxActionListener(tableModel, i);
	    	
	    	// Add the action listener to each check box
	    	completed[i].addActionListener(actionListeners[i]);
	    	
	    	// Align the current check box and event name
	    	aligner[i].add(completed[i]);
	    	aligner[i].add(label[i]);
	    	
	    	// Add the aligned elements to the cell's main JPanel and decrease the space between the next set of aligned elements
	    	panel.add(aligner[i]);
		    panel.add(Box.createVerticalStrut(-9));
	    }
	    panel.add(Box.createVerticalGlue());
	}
	
	private void updateData(CellInformation cellInfo, boolean isSelected, JTable table) {
		this.cellInfo = cellInfo;
		

		
		// If the cell is defined
		if (cellInfo != null) {
			
			dayNum.setText(" " + cellInfo.day + " ");
			dayNum.setVisible(true);
			
			for (int i = 0; i < cellInfo.events.length; i ++) {
				// Set the current information
				label[i].setText(cellInfo.events[i]);
				completed[i].setSelected(cellInfo.completed[i]);
				
				// Change the action listener's cellInfo object reference to the correct one
				actionListeners[i].setCellInfo(cellInfo);
				
				//SET TO VISIBLE
				label[i].setVisible(true);
				completed[i].setVisible(true);
			}
			for (int i = cellInfo.events.length; i < 20; i ++) {
				//SET TO inVISIBLE
				label[i].setText(" ");
				label[i].setVisible(false);
				completed[i].setVisible(false);
			}
			
			
			
		}
		// If the cell is not defined (null)
		else {
			dayNum.setText(" ");
			dayNum.setVisible(false);
			for (int i = 0; i < 20; i ++) {
				label[i].setText(" ");
				
				//SET TO inVISIBLE
				label[i].setVisible(false);
				completed[i].setVisible(false);
			}
		}
		
		// Make it so that when an element is selected, the cells are highlighted
		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
			dayNumAligner.setBackground(table.getSelectionBackground());
			for (int i = 0; i < 20; i ++) {
				aligner[i].setBackground(table.getSelectionBackground());
				completed[i].setBackground(table.getSelectionBackground());
			}
			
		}
		else {
			panel.setBackground(table.getBackground());
			dayNumAligner.setBackground(table.getBackground());
			for (int i = 0; i < 20; i ++) {
				aligner[i].setBackground(table.getBackground());
				completed[i].setBackground(table.getBackground());
			}
		}
		
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		CellInformation cellInfo = (CellInformation)value;
		updateData(cellInfo, isSelected, table);
		return panel;
	}
	
	public Object getCellEditorValue() {
		return null;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		CellInformation cellInfo = (CellInformation)value;
		updateData(cellInfo, isSelected, table);
		return panel;
	}
}

class CheckBoxActionListener implements ActionListener {
	
	CellTableModel tableModel;
	CellInformation cellInfo;
	int eventIndex;
	
	public CheckBoxActionListener(CellTableModel tableModel, int eventIndex) {
		this.tableModel = tableModel;
		this.eventIndex = eventIndex;
	}
	
	public void setCellInfo(CellInformation cellInfo) {
		this.cellInfo = cellInfo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Preformed on day: " + cellInfo.day);
		if (((JCheckBox)e.getSource()).isSelected()) {
			cellInfo.completed[eventIndex] = true;
		} else {
			cellInfo.completed[eventIndex] = false;
		}
		tableModel.fireTableDataChanged();	// To update the table
	}

}

























