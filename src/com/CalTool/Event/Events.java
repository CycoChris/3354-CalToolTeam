package com.CalTool.Event;

import java.awt.Color;
import java.util.ArrayList;

public class Events {
	
	public enum EventType {
		WEEKLY, REGULAR;
	}
	
	public enum EventCategory {
		HOLIDAY, BIRTHDAY, NONE
	}
	
	private ArrayList<Event> eventList;
	
	
	public Events() {
		eventList = new ArrayList<Event>();
	}
	
	public void addEvent(Object o) throws ClassExceptionError {
		try {
			Event event = (Event)o;
			eventList.add(event);
		} catch (Exception e) {
			throw new ClassExceptionError();
		}
	}
	
	public ArrayList<Event> getEventsToEdit() {
		return eventList;
	}
	
	public void deleteEvent(String name) {
		for(int x=0; x<eventList.size(); x++) {
			if(eventList.get(x).name.equals(name)) {
				eventList.remove(x);
				x--;
			}
		}
	}
	
	public void addWeeklyEvent(String name, int eventDate, EventType type, Color color) {
		eventList.add(new Event(name, eventDate, type, color));
	}
	
	public boolean checkTimeConflicts() {
		
		for(Event e : eventList) {
			for(Event ee : eventList) {
				if(e != ee && e.eventDate == ee.eventDate) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void colorMarkings() {
		System.out.println("I colored it! :)");
	}
	
	public boolean checkEventAlert() {
		
		int currentTime = 0x6969;
		
		for(Event e : eventList) {
			if(currentTime == e.eventDate) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addEventCategory(Event event, EventCategory category) {
		event.category = category;
	}
	
	public void editEventCategory(Event event, EventCategory change) {
		event.category = change;
	}
	
	public void removeEventCategory(Event event) {
		event.category = EventCategory.NONE;
	}
	
	public void clearEvents() {
		eventList.clear();
	}
	
}

