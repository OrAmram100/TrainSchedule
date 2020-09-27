package TrainSchedule;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;



public class controlSystem implements IsearchRides {

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
				break;

			case 3:// search trip
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
				IsearchRides.search(station, des, SHour,SMin);
				break;

			case 4: // save to file
				StringBuffer date = new StringBuffer();
				RidesManagement.sortAll();
				System.out.println("Enter date by auto/keyboard");
				String inputChoice = scan.next();
				date = fileName(scan, inputChoice);
				System.out.println(date.toString());
				RidesManagement.save("railWay " + date);
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

	private static StringBuffer fileName(Scanner scan,String inputChoice) {
		String format ="";
		StringBuffer date = new StringBuffer();
		if (inputChoice.equalsIgnoreCase("auto")) {
			format = new SimpleDateFormat("yyyy-MM-dd'.txt'").format(new Date());
			date.append(format);
		} else if (inputChoice.equalsIgnoreCase("keyboard")) {
			date.append(DateViaKeyboard(scan, date));
			System.out.println(date.toString());
		}
		return date;
	}

	private static StringBuffer DateViaKeyboard(Scanner scan, StringBuffer date) {
		boolean ValidDate;
		do {
			System.out.println("Date format: yyyy-MM-dd");
			scan.useDelimiter("-|\\s+");
			int year = scan.nextInt();
			int month = scan.nextInt();
			int day =  scan.nextInt();
			ValidDate = isValidDate(year,month,day);
			} while(!ValidDate);
		return date;
	}

	private static boolean isValidDate(int year, int month, int day) {
		boolean boolCheck = true;
		if(year < 2020)
			boolCheck = false;
		if((month>12) || (month<1))
			boolCheck = false;
		if((day<1) || (day>31))
			boolCheck = false;
		if(!boolCheck) {
			System.out.println("not ok");
			return false;
		}
		return true;
	}
}




