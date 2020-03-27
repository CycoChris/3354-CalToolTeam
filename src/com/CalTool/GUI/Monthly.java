package com.CalTool.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Monthly {
	
	// Create all GUI containers
	private JPanel panel;
	private DefaultTableModel table;
	private JTable jtable;
	
	//headers for the table
    private String[] Days = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
    
    // Object used to Fill table
	
	public Monthly() {
		// Instantiate all necessary objects
 		panel = new JPanel();
 		table = new DefaultTableModel();
 		jtable = new JTable(table);
		
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
        };
        
 		// Add table headers
 		for (int i = 0; i < columns.length; i ++) {
 			mdl.addColumn(columns[i]);
 		}
 		
 		for (int i = 0; i < data.length; i ++) {
 			mdl.addRow(data[i]);
 		}
 		
 		
        //add the table to the frame
        pMonthly.add(new JScrollPane(table));
           
//		        frame.pack();
        pMonthly.setVisible(true);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
}

// A class used as a data structure for the information shown in each cell
class CellInformation {
	int day;
	String[] events;
	
	public CellInformation(int day, String[] events) {
		this.day = day;
		this.events = events;
	}
}

// A class for making the table look exactly how we need it

