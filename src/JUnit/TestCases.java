package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import 

import com.CalTool.Event.Events;
import com.CalTool.Event.Events.Event;

public class TestCases {

	@Test
	public void case1 () {
		Events events = new Events();
		events.addEvent(new Event(null, 0, null, null));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
		
	@Test (expected = ClassExceptionError.class)
	public void case2 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
	
	@Test
	public void case3 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
	
	@Test
	public void case4 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
	
	@Test
	public void case5 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
	
	@Test
	public void case6 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
	
	@Test
	public void case7 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
	
	
	
}

class ClassException extends Exception { }
