package com.CalTool.GUI;

import javax.swing.*;

public class Window {

	JFrame frame;
	JPanel panel;
	
	public Window() {
		
		// Create objects needed
		frame = new JFrame();
		panel = new JPanel();
		
		
		// Creating the initial window
		frame.setTitle("CalTool");
		frame.setSize(600, 500);
		frame.setLocation(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Add panel into frame
		frame.add(panel);
		
	}
	
}
