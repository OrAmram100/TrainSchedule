package Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import TrainSchedule.RidesManagement;
import TrainSchedule.clockTrain;

public class RideManagementTest {
	
	@Test
	public void checkTimeTest() throws Exception {
		File TFile = new File("RideTest.txt");
		Scanner Tscan = new Scanner(TFile);
		
		List<clockTrain> TestleaviningTime = new ArrayList<clockTrain>();
		//for(int time = 1; time<=11; time++) {
			
			while(Tscan.hasNext()) {
			Tscan.useDelimiter(":|\\s+");
			int THour = Tscan.nextInt();
			int TMin = Tscan.nextInt();
			System.out.println(THour+":" +TMin);
			TestleaviningTime.add(new clockTrain(THour, TMin));
			Tscan.nextLine();
		}
		
		File sfile= new File("TrainSchedule.txt");
		Scanner sFile = new Scanner(sfile);
		RidesManagement.DataInsertionViaFile(sFile);
		
		assertEquals(TestleaviningTime.toString(), RidesManagement.getLeaviningTime());
		Tscan.close();
}
}
