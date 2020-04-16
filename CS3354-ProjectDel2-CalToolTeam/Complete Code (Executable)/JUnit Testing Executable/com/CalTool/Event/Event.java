package com.CalTool.Event;

import java.awt.Color;

import com.CalTool.Event.Events.EventCategory;
import com.CalTool.Event.Events.EventType;

public class Event {
	
	public String name;
	
	public int eventDate;
	
	public EventType type;
	
	public Color color;
	
	public EventCategory category = EventCategory.NONE;
	
	public Event(String name, int eventDate, Color color) {
		this.name = name;
		this.eventDate = eventDate;
		this.type = EventType.REGULAR;
		this.color = color;
	}
	
	public Event(String name, int eventDate, EventType type, Color color) {
		this.name = name;
		this.eventDate = eventDate;
		this.type = type;
		this.color = color;
	}
	
}