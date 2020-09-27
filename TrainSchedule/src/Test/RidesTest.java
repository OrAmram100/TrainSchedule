package Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import TrainSchedule.RidesManagement;

public class RidesTest implements CallToMethod {

	@Test
	public void checkRidesIsValid() throws Exception {
		File file3 = new File("rides.txt");
		Scanner Tscan = new Scanner(file3);
		StringBuffer sb = new StringBuffer();

		while (Tscan.hasNext()) {
			String ride = Tscan.nextLine();
			if (ride.equals(" "))
				break;
			sb.append(ride + "\n");
		}
		sb.append("\n");
		while (Tscan.hasNext()) {
			String ride = Tscan.nextLine();
			if (ride.equals(" "))
				break;
			sb.append(ride + "\n");
		}
		sb.append("\n");
		while (Tscan.hasNext()) {
			String ride = Tscan.nextLine();
			if (ride.equals(" "))
				break;
			sb.append(ride + "\n");
		}
		sb.append("\n");
		RidesManagement.reseRideList();
		CallToMethod.RmCheck();
		assertEquals(sb.toString(), RidesManagement.stringRides().toString());
		Tscan.close();
	}

	@Test
	public void checkRidesIfSorted() throws Exception {
		File file3 = new File("sortRides.txt");
		Scanner Tscan = new Scanner(file3);
		StringBuffer sb = new StringBuffer();

		while (Tscan.hasNext()) {
			String ride = Tscan.nextLine();
			if (ride.equals(" "))
				break;
			sb.append(ride + "\n");
		}
		sb.append("\n");
		while (Tscan.hasNext()) {
			String ride = Tscan.nextLine();
			if (ride.equals(" "))
				break;
			sb.append(ride + "\n");
		}
		sb.append("\n");
		while (Tscan.hasNext()) {
			String ride = Tscan.nextLine();
			if (ride.equals(" "))
				break;
			sb.append(ride + "\n");
		}
		CallToMethod.RmCheck();
		sb.append("\n");
		assertEquals(sb.toString(), RidesManagement.sortAll().toString());
		Tscan.close();

	}

}
