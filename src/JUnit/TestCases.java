package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCases {

	@Test
	public void case1 () {
		Events events = new Events();
		events.addEvent(new Event("Event 1", 10)));
		
		assertEquals("Checking if event added successfully", 1, events.getSize());
	}
		
	@Test
	public void case2 () {
		assertEquals("Here is the test for multiplication", 200, 200);
	}
	
	@Test
	public void case3 () {
		assertEquals("Here is the test for multiplication", 200, 200);
	}
	
	@Test
	public void case4 () {
		assertEquals("Here is the test for multiplication", 200, 200);
	}
	
	@Test
	public void case5 () {
		assertEquals("Here is the test for multiplication", 200, 200);
	}
	
	@Test
	public void case6 () {
		assertEquals("Here is the test for multiplication", 200, 200);
	}
	
	@Test
	public void case7 () {
		assertEquals("Here is the test for multiplication", 200, 200);
	}
	
	
	
}
