package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		void checkTimeTest1() throws Exception { // should be error.
			int hours = 10;
			int minutes = 30;
			boolean res = clockTrain.checkClock(hours, minutes);
			assertNotEquals(false, res);
	
	}
//
//		@Test
//		void timeToComperTest() {
//			
//			c1.checkClock("12:00");
//			int res = c1.timeToCompare();
//			assertEquals(1200,res);
//	
}
