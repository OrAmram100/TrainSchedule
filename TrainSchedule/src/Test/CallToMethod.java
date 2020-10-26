package Test;

import java.io.File;
import java.util.Scanner;

import TrainSchedule.RidesManagement;

public interface CallToMethod {
	public static void ReadInsertCheck() throws Exception{
		File sfile = new File("TrainSchedule.txt");
		Scanner sFile = new Scanner(sfile);
		RidesManagement.DataInsertionViaFile(sFile);
	}
}
