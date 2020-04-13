package com.CalTool.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.List;
import java.time.YearMonth;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.CalTool.Event.Event;
import com.CalTool.Event.Events;

public class CalendarView {

	// Declare all containers
	JFrame frame;
	
	// Create all objects
	Monthly monthlyView;
	Weekly weeklyView;
	Events events;

	// Declare enum used to track the view (
	View CurrentView;
	
	public CalendarView() {
		// Create the JFrame object with the needed attributes
		createView();
			
		// Create the different panels 
		monthlyView = new Monthly();
		events = new Events();
		
		// instantiate the current view TODO: this value can be read in the JSON data file
		CurrentView = View.MONTHLY_VIEW;
		
		// Add the first panel to "frame"
		updateViewMode();
	}
	
	public void createView() {
		// Create JFrame object
		frame = new JFrame();
		
		// Creating the initial window
		frame.setTitle("CalTool");
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
	}
	
	
	// This method will be invoked every time the application must change the view mode
	public void updateViewMode() {
		frame.getContentPane().removeAll();
		
		switch(CurrentView) {
			case MONTHLY_VIEW:
				frame.add(monthlyView());
				break;
							
			case WEEKLY_VIEW:
				frame.add(weeklyView());
				break;
				
			case DAILY_VIEW:
//				frame.add(pDaily);
				break;
				
			case AGENDA_VIEW:
//				frame.add(pAgenda);
				break;
				
			default:
				System.out.println("[UPDATE ERROR] View mode needs to be updated");
		}
		
		frame.setVisible(true);
		
	}
	
	// Returns a data structure that stores today's date
	public YearMonth readTodaysDate() {
		return YearMonth.now();	
	}
	
	// Returns the name of the current day
	public String readTodaysDateName() {
		String[] days = new String[] {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
		int i = readTodaysDate().atDay(readTodaysDate().getMonthValue()).getDayOfWeek().getValue()%7;
		return days[i];		
	}
	
	// Returns the data structure for the events
	public ArrayList<Event> getEvents() {
		return events.getEventsToEdit();
	}
	
	public int getDate(Event e) {
		return e.eventDate;
	}
	
	
	// Returns the monthly view
	public Container monthlyView() {
		return monthlyView.getPanel();
	}


	// Returns the weekly view
	public Container weeklyView() {
		return weeklyView.getPanel();
	}
	

	// Returns the daily view
	public Container dailyView() {
		/* TODO: To be implemented later*/
		return null;
	}
	
	// Returns the agenda view
	public Container agendaView() {
		/* TODO: To be implemented later*/
		return null;
	}
	
	
	public boolean checkNotification() {
		return events.checkEventAlert();
	}
	
	// Makes the calendar bigger
	public void zoomInView() {
		frame.setSize(frame.getWidth()+10, frame.getHeight()+10);
	}
	
	// Makes the calendar smaller
	public void zoomOutView() {
		frame.setSize(frame.getWidth()-10, frame.getHeight()-10);
	}
	
	public void outputView() {
		frame.setVisible(true);
	}
}




