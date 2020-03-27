package com.CalTool.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window {

	// Declare all containers
	JFrame frame;
	
	// Create all objects
	Monthly monthlyView;
	Weekly weeklyView;
	
	
	// Declare enum used to track the view (
	View CurrView;
	
	public Window() {
		// Create the JFrame object with the needed attributes
		createJFrame();
			
		// Create the different panels 
		monthlyView = new Monthly();
		
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
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());

		
	}
	
	
	// This method will be invoked every time the application must change the view mode
	public void updateViewMode() {

		
		switch(CurrView) {
			case MONTHLY_VIEW:
				frame.add(monthlyView.getPanel());
				break;
				
			case DAILY_VIEW:
//				frame.add(pDaily);
				break;
				
			case WEEKLY_VIEW:
				frame.add(weeklyView.getPanel());
				break;
				
			case AGENDA_VIEW:
//				frame.add(pAgenda);
				break;
				
			default:
				System.out.println("[UPDATE ERROR] View mode needs to be updated");
		}

		frame.add(monthlyView.getPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
}




