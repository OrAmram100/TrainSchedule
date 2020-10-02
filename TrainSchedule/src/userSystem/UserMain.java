package userSystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import TrainSchedule.IsearchRides;
import TrainSchedule.RidesManagement;

public class UserMain implements IsearchRides {

	public static void main(String[] args) throws Exception {
		boolean isHtml = args[0].equalsIgnoreCase("html") & args.length > 0;
		if (!isHtml) {
			String out = new SimpleDateFormat("yyyy-MM-dd '.txt'").format(new Date());
			File f = new File("/home/afeka/git/TrainSchedule/TrainSchedule/railWay " + out);
			Scanner s = new Scanner(f);
			RidesManagement.FiletoUserInsertion(s);
			int hour = Integer.parseInt(args[3]);
			int minute = Integer.parseInt(args[4]);
			RidesManagement.sortAll();
			RidesManagement.stringRides();
			IsearchRides.search(args[1], args[2], hour, minute);
		} else {                      // need to write in html code
			
		}

	}
}
