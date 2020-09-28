package userSystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import TrainSchedule.IsearchRides;
import TrainSchedule.RidesManagement;

public class UserMain implements IsearchRides{

	public static void main(String[] args) throws Exception {
		String out = new SimpleDateFormat("yyyy-MM-dd'.txt'").format(new Date());
		String inputChoice = "auto";
		Scanner scan = new Scanner("2021-12-03");
		//String inputChoice = args[4];
		//Scanner scan = new Scanner(args[5]);
		out = ""+ RidesManagement.fileName(scan, inputChoice);
		File file = new File("railWay " + out);
		
		Scanner userFile = new Scanner(file);
		InsertData(userFile);
//		int hour = Integer.parseInt(args[2]);
//		int minute = Integer.parseInt(args[3]);
//		IsearchRides.search(args[0], args[1], hour, minute);
	}
	
	private static void InsertData(Scanner userFile)throws Exception{
		boolean fileInsertionCheck = RidesManagement.FiletoUserInsertion(userFile);
		if(fileInsertionCheck)
			System.out.println("update complited");
		else if(!fileInsertionCheck)
			System.out.println("Rides update error");
	}

}
