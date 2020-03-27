package com.CalTool.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Window {

	// Declare all containers
	JFrame frame;
	JPanel pMonthly, pWeekly, pDaily, pAgenda;
	
	JTable table;
	
	// Declare enum used to track the view (
	View CurrView;
	
	public Window() {
		// Create the JFrame object with the needed attributes
		createJFrame();
			
		// Create the different panels 
		createMonthlyPanel();
		
		// instantiate the current view TODO: this value can be read in the JSON data file
		CurrView = View.MONTHLY_VIEW;
		
		// Add the first panel to "frame"
		updateViewMode();
	}
	
	public void createJFrame() {
		// Create JFrame object
		frame = new JFrame();
		
		// Creating the initial window
		frame.setTitle("CalTool");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);			
	}
	
	public void createMonthlyPanel() {
		// Create the JPanel object
 		pMonthly = new JPanel();
		
		//headers for the table
        String[] columns = new String[] {"Id", "Name", "Hourly Rate", "Part Time"};
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
        };
        
       
 		
 		DefaultTableModel mdl = new DefaultTableModel();
 		table = new JTable(mdl);
        
        
 		// Add data
 		for (int i = 0; i < columns.length; i ++) {
 			mdl.addColumn(columns[i]);
 		}
 		
 		for (int i = 0; i < data.length; i ++) {
 			mdl.addRow(data[i]);
 		}
 		
 		
        //add the table to the frame
        pMonthly.add(new JScrollPane(table));
           
//        frame.pack();
        pMonthly.setVisible(true);
		
	}
	
	
	// This method will be invoked every time the application must change the view mode
	public void updateViewMode() {
//		frame.removeAll();
		
		switch(CurrView) {
			case MONTHLY_VIEW:
				frame.add(pMonthly);
				break;
				
			case DAILY_VIEW:
				frame.add(pDaily);
				break;
				
			case WEEKLY_VIEW:
				frame.add(pWeekly);
				break;
				
			case AGENDA_VIEW:
				frame.add(pAgenda);
				break;
				
			default:
				System.out.println("[UPDATE ERROR] View mode needs to be updated");
		}
	}
}