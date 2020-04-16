package JUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.CalTool.GUI.CalendarView;

public class JUnitTester {

	public static void main(String[] args) {
		  
	      Result result = JUnitCore.runClasses(TestCases.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println("Result = " + result.wasSuccessful());

	}
	
}
