package userSystem;

import java.io.File;
import java.util.Scanner;

import TrainSchedule.IsearchRides;

public class UserMain implements IsearchRides{

	public static void main(String[] args) throws Exception {
		File file = new File("yyyy-MM-dd '.txt'");
		Scanner sFile = new Scanner(file);
		int hour = Integer.parseInt(args[2]) ;
		int minute = Integer.parseInt(args[3]);
			IsearchRides.search(args[0], args[1], hour, minute);

	}

}