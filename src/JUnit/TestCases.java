package JUnit;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import com.CalTool.Event.ClassExceptionError;
import com.CalTool.Event.Event;
import com.CalTool.Event.Events;

public class TestCases {

	@Test
	public void case1() {
		Events events = new Events();
		
		try {
			events.addEvent(new Event("Event Name", 10, Color.BLACK));
		} catch (ClassExceptionError e) {
			
		}
		
		assertEquals("Checking if event added successfully", 1, events.getEventsToEdit().size());
		
	}
		
	@Test (expected = ClassExceptionError.class)
	public void case2 () throws ClassExceptionError {
		Events events = new Events();
		
		events.addEvent(new ArrayList<Integer>());
		
		assertEquals("Checking if event added successfully", 0, events.getEventsToEdit().size());
	}
	
	@Test
	public void case3() {
		Events events = new Events();
		
		try {
			events.addEvent(new Event("Expected Event Name", 100, Color.CYAN));
		} catch (ClassExceptionError e) {
			
		}
		
		assertEquals("Checking if eventName added correctly.", "Expected Event Name", events.getEventsToEdit().get(0).name);
		assertEquals("Checking if eventDate added correctly.", 100, events.getEventsToEdit().get(0).eventDate);
		assertEquals("Checking if eventColor added correctly.", Color.CYAN, events.getEventsToEdit().get(0).color);
		
	}
	
	
	
}

