package com.CalTool.GUI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Monthly {
	
	
	//headers for the table
    JPanel p;
    
    // Object used to Fill table
    Object[][] eventsdataStructs;
	
	public Monthly() {
		// Instantiate the super class (for the JPanel)
 		p = new JPanel();
 		
 		populateCellData();
// 		
// 		// Create the table
// 		jtable = new JTable(eventsdataStructs, Days);
// 		
//// 		add(new JButton("THis should work?"));
// 		add(new JScrollPane(jtable));
 		
 		
 		//headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
        };
        
        
        String[] Days = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        
        
        Object[][] eventsdataStructs = new Object[][]{
			{1, 2, 3, 4, 5, 6, 7},	
			{1, 2, 3, 4, 5, 6, 7},	
			{1, 2, 3, 4, 5, 6, 7},
        };
        
        //create table with data
        JTable jtable = new JTable(data, columns);
         
        //add the table to the frame
        p.add(new JScrollPane(jtable));
         
 		
	}
	
	public void populateCellData() {
		eventsdataStructs = new Object[][]{
				{1, 2, 3, 4, 5, 6, 7},	
				{1, 2, 3, 4, 5, 6, 7},	
				{1, 2, 3, 4, 5, 6, 7},
		};
	}
	
	public JPanel getPanel() {
		return p;
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

