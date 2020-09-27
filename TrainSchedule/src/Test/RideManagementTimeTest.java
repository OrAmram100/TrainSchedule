package Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import TrainSchedule.RidesManagement;
import TrainSchedule.clockTrain;

public class RideManagementTimeTest implements CallToMethod {
    
	@Test
	public void checkLeavingTimeTest() throws Exception {
		File File1 = new File("leavingTimeTest.txt");
		Scanner Tscan = new Scanner(File1);

		List<clockTrain> TestleaviningTime = new ArrayList<clockTrain>();

		while (Tscan.hasNext()) {
			Tscan.useDelimiter(":|\\s+");
			int THour = Tscan.nextInt();
			int TMin = Tscan.nextInt();
			TestleaviningTime.add(new clockTrain(THour, TMin));
			Tscan.nextLine();
		}

		assertEquals(TestleaviningTime.toString(), RidesManagement.getLeaviningTime().toString());
		Tscan.close();
	}

	@Test
	public void checkDestinationTimeTest() throws Exception {
		File TFile = new File("destinationTime.txt");
		Scanner Tscan = new Scanner(TFile);

		List<clockTrain> TestArraivalTime = new ArrayList<clockTrain>();

		while (Tscan.hasNext()) {
			Tscan.useDelimiter(":|\\s+");
			int THour = Tscan.nextInt();
			int TMin = Tscan.nextInt();
			TestArraivalTime.add(new clockTrain(THour, TMin));
			Tscan.nextLine();
		}
		CallToMethod.RmCheck();
		assertEquals(TestArraivalTime.toString(), RidesManagement.getDestiniationTime().toString());
		Tscan.close();
	}

}
