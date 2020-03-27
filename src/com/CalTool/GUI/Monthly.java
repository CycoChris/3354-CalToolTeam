package com.CalTool.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Monthly {
	
	// Create all GUI containers
	JPanel panel;
	DefaultTableModel table;
	JTabel jtabel;
	
	//headers for the table
    String[] Days = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
     
	
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
        
       
 		
 		
        
        
 		// Add data
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
