package com.CalTool.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Monthly extends JPanel {
	
	
	//headers for the table
    private JTable jtable;
    
    
    // Object used to Fill table
    private Object[][] eventsdataStructs;
    private String[] days;
	
	public Monthly() {
		// Instantiate the super class (for the JPanel)
 		super();
 		
 		populateData();
 		
 		jtable = new JTable(eventsdataStructs, days);
 		add(new JScrollPane(jtable));
 		

	}
	
	// This can be the function that calls a JSON parser to get all of the information
	public void populateData() {
	   eventsdataStructs = new Object[][]{
			{1, 2, 3, 4, 5, 6, 7},	
			{1, 2, 3, 4, 5, 6, 7},	
			{1, 2, 3, 4, 5, 6, 7},
        };
        
        days = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
	}
	
	public JPanel getPanel() {
		return this;
	}
	
}

// A class used as a data structure for the information shown in each cell
class CellInformation {
	int day;
	String[] events;	// This can be changed to an object later on
	
	public CellInformation(int day, String[] events) {
		this.day = day;
		this.events = events;
	}
}

// A class for making the table look exactly how we need it

