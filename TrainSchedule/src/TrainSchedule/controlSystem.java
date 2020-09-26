package TrainSchedule;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;



public class controlSystem {

	public static void main(String[] args) throws Exception {
		File file = new File("TrainSchedule.txt");
		Scanner sFile = new Scanner(file);
		Scanner scan = new Scanner(System.in);
		int choice;
		boolean ifContinue = true;

		do {
			System.out.println("Welcome to Israel-RailWays!\n");
			System.out.println(
					" 1) Insert schedule \n 2) View today's Schedule \n 3) Plan your route \n 4) save to file \n 9) Exit");
			choice = scan.nextInt();

			switch (choice) {

			case 1:// Data insertion
				System.out.println("Enter data via FILE / KEYBOARD?");
				String port = scan.next();
				if (port.equalsIgnoreCase("file")) {
					RidesManagement.DataInsertionViaFile(sFile);
				} else if (port.equalsIgnoreCase("keyboard")) {
					RidesManagement.DataInsertionViaKeyboard(scan);
				}
				break;

			case 2:// View schedule

				RidesManagement.sortAll();
				System.out.println(RidesManagement.stringRides());
				System.out.println(RidesManagement.getLeaviningTime());
				break;

			case 3:// Plan trip
				RidesManagement.sortAll();
				RidesManagement.stringRides();
				System.out.println("Enter starting station");
				String station = scan.nextLine();
				station += scan.nextLine();
				System.out.println("Enter destiniation station");
				String des = scan.nextLine();
				boolean isValid = true;
				int SHour = 0;
				int SMin = 0;
				do {
					try {
						System.out.println("Enter leaving time");
						scan.useDelimiter(":|\\s+");
						 SHour = scan.nextInt();
						 SMin = scan.nextInt();
						clockTrain.checkClock(SHour, SMin);
						isValid = true;
					} catch (InputMismatchException c) {
						System.out.println("must  input : between mintues and hours");
						isValid = false;
						scan.nextLine();

					} catch (Exception e) {
						System.out.println(e.getMessage());
						isValid = false;
					}
				} while (!isValid);
				String route = RidesManagement.LocateRide(des, station, SHour, SMin);
				if (!route.equals(""))
					System.out.println("Station " + station + " --> Station " + des + "\n" + route);
				else
					System.out.println("Sorry there is no rides for your request today!!");
				break;

			case 4: // save to file
				String out = new SimpleDateFormat("yyyy-MM-dd '.txt'").format(new Date());
				RidesManagement.save("railWay " + out);
				break;

			case 9: // Exit
				System.out.println("Exit.\nThank you for using.\nhave a great day !");
				ifContinue = false;
				scan.close();
				sFile.close();
				break;

			default:
				System.out.println("This choise does not exist in the menu"); // case choice is not valid
				break;
			}

		} while (ifContinue);

	}

}
