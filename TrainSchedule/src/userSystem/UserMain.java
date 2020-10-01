package userSystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import TrainSchedule.IsearchRides;
import TrainSchedule.RidesManagement;

public class UserMain implements IsearchRides {

	public static void main(String[] args) throws Exception {
		String out = new SimpleDateFormat("yyyy-MM-dd '.txt'").format(new Date());
		File f = new File("/home/afeka/git/TrainSchedule/TrainSchedule/railWay " + out);
		Scanner s = new Scanner(f);
		RidesManagement.FiletoUserInsertion(s);
		int hour = Integer.parseInt(args[2]);
		int minute = Integer.parseInt(args[3]);
		RidesManagement.sortAll();
		RidesManagement.stringRides();
		IsearchRides.search(args[0], args[1], hour, minute);
	}

}
