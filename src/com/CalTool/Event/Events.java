package com.CalTool.Event;

import java.awt.Color;
import java.util.ArrayList;

public class Events {
	
	enum EventType {
		WEEKLY, REGULAR;
	}
	
	enum EventCategory {
		HOLIDAY, BIRTHDAY, NONE
	}
	
	private ArrayList<Event> eventList;
	
	public class Event {
		
		public String name;
		
		public int eventDate;
		
		public EventType type;
		
		public Color color;
		
		public EventCategory category = EventCategory.NONE;
		
		public Event(String name, int eventDate, EventType type, Color color) {
			this.name = name;
			this.eventDate = eventDate;
			this.type = type;
			this.color = color;
		}
		
	}
	
	public Events() {
		eventList = new ArrayList<Event>();
	}
	
	public void addEvent(Event event) {
			eventList.add(event);
	}
	
	ArrayList<Event> getEventsToEdit() {
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
	
	
}
