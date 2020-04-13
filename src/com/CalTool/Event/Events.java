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
	private ArrayList<Event> eventCategories;
	
	class Event {
		
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
	
	Events(long startTime, long duration, String name, String description) {
		eventList = new ArrayList<Event>();
	}
	
	void addEvents(Event[] event) {
		for(Event e : event) {
			eventList.add(e);
			System.out.println("added an event!!1!1 :)");
		}
	}
	
	void deleteEvent(String name) {
		for(int x=0; x<eventList.size(); x++) {
			if(eventList.get(x).name.equals(name)) {
				eventList.remove(x);
				x--;
			}
		}
	}
	
	void addWeeklyEvent(String name, int eventDate, EventType type, Color color) {
		eventList.add(new Event(name, eventDate, type, color));
	}
	
	boolean checkTimeConflicts() {
		
		for(Event e : eventList) {
			for(Event ee : eventList) {
				if(e != ee && e.eventDate == ee.eventDate) {
					return true;
				}
			}
		}
		return false;
	}
	
	void colorMarkings() {
		System.out.println("I colored it! :)");
	}
	
	boolean checkEventAlert() {
		
		int currentTime = 0x6969;
		
		for(Event e : eventList) {
			if(currentTime == e.eventDate) {
				return true;
			}
		}
		
		return false;
	}
	
	void addEventCategory(Event event, EventCategory category) {
		event.category = category;
	}
	
}
