package Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import TrainSchedule.RidesManagement;
import TrainSchedule.clockTrain;

public class RideManagementTest implements CallToMethod {
    
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
		}
		RidesManagement.resetRideList();
		CallToMethod.ReadInsertCheck();
		assertEquals(TestArraivalTime.toString(), RidesManagement.getDestiniationTime().toString());
		Tscan.close();
	}
	
	@Test
	public void LocateRide() throws Exception {
			String des = "Tel Aviv-Hashalom";
			String station = "Haifa";
			int sHour = 7;
			int sMin = 30;
			String ridePlan = "Station Haifa --> Station Tel Aviv-Hashalom\n" + 
					"\n" + 
					"Railway  : \n" + 
					"\n" + 
					" The train will arraive to the destination at :08:38\n" + 
					"Haifa - 08:00\n" + 
					"Tel Aviv-Hashalom - 08:38\n" + 
					"\n" + 
					"Station Haifa --> Station Tel Aviv-Hashalom\n" + 
					"\n" + 
					"Railway  : \n" + 
					"\n" + 
					" The train will arraive to the destination at :08:40\n" + 
					"Haifa - 08:12\n" + 
					"Tel Aviv-Hashalom - 08:40\n" + 
					"\n" + 
					"Station Haifa --> Station Tel Aviv-Hashalom\n" + 
					"\n" + 
					"Railway  : \n" + 
					"\n" + 
					" The train will arraive to the destination at :17:40\n" + 
					"Haifa - 16:12\n" + 
					"Tel Aviv-HaHagana - 17:12\n" + 
					"Tel Aviv-Hashalom - 17:40\n";
			CallToMethod.ReadInsertCheck();
			RidesManagement.sortAll();
			assertEquals(ridePlan, RidesManagement.LocateRide(des, station, sHour, sMin));
		}

	}





