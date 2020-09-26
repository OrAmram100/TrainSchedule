package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import TrainSchedule.clockTrain;

public class CheckTimeTest {
	@Test
	void checkTimeTest() throws Exception { // should be correct.
		int hours = 10;
		int minutes = 30;
		boolean res = clockTrain.checkClock(hours, minutes);
		assertEquals(true, res);
	}
		@Test
		void checkhour() throws Exception { // should be error.
			int hours = 28;
			int minutes = 30;
			try {
			    assertEquals("28:30", clockTrain.checkClock(hours, minutes));
			} 
			catch (Exception e) {
			    String expectedMessage = "the hour is not valid";
			    assertEquals( expectedMessage, e.getMessage() );
			}
	}
		@Test
		void checkMinutes() throws Exception { // should be error.
			int hours = 22;
			int minutes = 90;
			try {
			    assertEquals("22:90", clockTrain.checkClock(hours, minutes));
			} 
			catch (Exception e) {
			    String expectedMessage = "the minutes are not valid";
			    assertEquals( expectedMessage, e.getMessage() );
			}
	}

}
