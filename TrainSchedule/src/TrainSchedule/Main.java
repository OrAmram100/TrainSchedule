package TrainSchedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("C:\\Users\\oramr\\git\\TrainSchedule\\TrainSchedule\\src\\TrainSchedule\\TrainSchedule.txt");
		Scanner sFile = new Scanner(file);
		Scanner scan = new Scanner(System.in);
		int numOfRides = 0;
		 int numsOfStations =0;
		int choice;
		boolean ifContinue = true;
		List<clockTrain> leaviningTime = new ArrayList<clockTrain>();
		List<Ride> allRides = new ArrayList<Ride>();
		List<clockTrain> destiniationTime = new ArrayList<clockTrain>();
		do {
			System.out.println("Welcome to Israel-RailWays!\n");
			System.out.println(" 1) Insert schedule \n 2) View today's Schedule \n 3) Plan your route \n 9) Exit");
			choice = scan.nextInt();

			switch (choice) {

			case 1://Data insertion
				
				System.out.println("Enter data via FILE / KEYBOARD?");
				String port = scan.next();
				if(port.equalsIgnoreCase("file")) {
					DataInsertionViaFile(sFile, leaviningTime, destiniationTime, allRides, numOfRides, numsOfStations);
				} else if(port.equalsIgnoreCase("keyboard")) {
					DataInsertionViaKeyboard(scan, leaviningTime, destiniationTime, allRides, numOfRides, numsOfStations);
				}
				break;

			case 2://View schedule
				
				sortAll(allRides);
				System.out.println("Israel-RailWays schedule: \n");
				System.out.println(toStringRide(allRides));
				break;

			case 3://Plan trip
				
				for(int i=0; i < allRides.size();i++) {
					allRides.get(i).sortStations();
				}
				sortRides(allRides);
				System.out.println("Enter starting station");
				String station = scan.nextLine();
				station+=scan.nextLine();
				System.out.println("Enter destiniation station");
				String des = scan.nextLine();
				System.out.println("Enter leaving time");
				scan.useDelimiter(":|\\s+");
				int SHour= scan.nextInt();
				int SMin = scan.nextInt();
				String route = LocateRide(allRides, des, station, SHour, SMin);
				if(!route.equals(""))
					System.out.println("Station " + station + " --> Station " + des +"\n" + route );
				else
					System.out.println("Sorry there is no rides for your request today!!");
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

	private static void sortAll(List<Ride> allRides) {
		for(int i=0; i < allRides.size();i++) {
			allRides.get(i).sortStations();
		}
		sortRides(allRides);		
	}

	private static void DataInsertionViaKeyboard(Scanner scan, List<clockTrain> leaviningTime, List<clockTrain> destiniationTime, List<Ride> allRides, int numOfRides, int numsOfStations) {
		String doContinue;
		do {
		System.out.println("Enter your Origin station");
		String Origin= scan.nextLine();
		Origin+=scan.nextLine();
		System.out.println("Enter train's leaving time");
		scan.useDelimiter(":|\\s+");
		int OHour = scan.nextInt();
		int OMin = scan.nextInt();
		System.out.println("Enter Destination station");
		String destiniation = scan.nextLine();
		destiniation+=scan.nextLine();
		System.out.println("Enter Expected arrival time");
		scan.useDelimiter(":|\\s+");
		int DHour = scan.nextInt();
		int DMin = scan.nextInt();
		leaviningTime.add(new clockTrain(OHour, OMin));
		destiniationTime.add(new clockTrain(DHour, DMin));
		allRides.add(new Ride(Origin, destiniation,destiniationTime.get(numOfRides),leaviningTime.get(numsOfStations)));
		Station newStation= new Station(Origin, leaviningTime.get(numsOfStations));
		Station destinateStation = new Station(destiniation,destiniationTime.get(numOfRides));		
		allRides.get(numOfRides).addStation(newStation);
		allRides.get(numOfRides).addStation(destinateStation);
		numsOfStations++;
		System.out.println("Are there intermediate stop? (yes/no)");				
		String interCheck = scan.next();
		while(interCheck.equalsIgnoreCase("yes")) {
			System.out.println("Enter station's name");
			String intermediate = scan.nextLine();
			intermediate+=scan.nextLine();
			System.out.println("Enter train's leaving time");
			scan.useDelimiter(":|\\s+");
			int ILHour= scan.nextInt();
			int ILMin = scan.nextInt();
			leaviningTime.add(new clockTrain(ILHour, ILMin));
			Station newInter = new Station(intermediate, leaviningTime.get(numsOfStations));
			allRides.get(numOfRides).addStation(newInter);
			numsOfStations++;
			System.out.println("Would you like to add more intermediate station?");
			interCheck =scan.next();
		}
		numOfRides++;
		System.out.println("Would you like to add another RIDE? (yes/no)");
		doContinue = scan.next();	
		} while (doContinue.equalsIgnoreCase("yes"));
	}

	private static void DataInsertionViaFile(Scanner sFile, List<clockTrain> leaviningTime, List<clockTrain> destiniationTime, List<Ride> allRides, int numOfRides, int numsOfStations) {
		
		int doContinue = sFile.nextInt();
		do {
			sFile.nextLine();
		String Origin= sFile.nextLine();
		sFile.useDelimiter(":|\\s+");
		int OHour = sFile.nextInt();
		int OMin = sFile.nextInt();
		String destiniation = sFile.nextLine();
		destiniation+=sFile.nextLine();
		sFile.useDelimiter(":|\\s+");
		int DHour = sFile.nextInt();
		int DMin = sFile.nextInt();
		leaviningTime.add(new clockTrain(OHour, OMin));
		destiniationTime.add(new clockTrain(DHour, DMin));
		allRides.add(new Ride(Origin, destiniation,destiniationTime.get(numOfRides),leaviningTime.get(numsOfStations)));
		Station newStation= new Station(Origin, leaviningTime.get(numsOfStations));
		Station destinateStation = new Station(destiniation,destiniationTime.get(numOfRides));		
		allRides.get(numOfRides).addStation(newStation);
		allRides.get(numOfRides).addStation(destinateStation);
		sFile.nextLine();
		numsOfStations++;
		String interCheck = sFile.next();
		while(interCheck.equalsIgnoreCase("yes")) {
			String intermediate = sFile.nextLine();
			intermediate+=sFile.nextLine();
			sFile.useDelimiter(":|\\s+");
			int ILHour= sFile.nextInt();
			int ILMin = sFile.nextInt();
			leaviningTime.add(new clockTrain(ILHour, ILMin));
			Station newInter = new Station(intermediate, leaviningTime.get(numsOfStations));
			allRides.get(numOfRides).addStation(newInter);
			sFile.nextLine();
			numsOfStations++;
			interCheck =sFile.next();
		}
		numOfRides++;
		doContinue = sFile.nextInt();	
		} while (doContinue == 1);
		System.out.println("Data insertion completed successfully. \n\n");
	}

	private static String LocateRide(List<Ride> allRides,String des,String station, int SHour, int SMin) {
		String route = ""; 
		for(int i=0; i < allRides.size();i++) {
			for(int startDes=0; startDes < allRides.get(i).getAllStaions().size();startDes++) {
				if(allRides.get(i).getAllStaions().get(startDes).getName().equalsIgnoreCase(station)					//Compare name
						&& (allRides.get(i).getAllStaions().get(startDes).getLeaviningTime().getHour() > SHour	 		//Compare by hour
								|| 	(allRides.get(i).getAllStaions().get(startDes).getLeaviningTime().getHour() == SHour		
								&& allRides.get(i).getAllStaions().get(startDes).getLeaviningTime().getMinute() >= SMin))){ //Compare by minute
					for(int lastDes=startDes; lastDes < allRides.get(i).getAllStaions().size(); lastDes++) {
						if(allRides.get(i).getAllStaions().get(lastDes).getName().equalsIgnoreCase(des)){
							route = "The train will arraive to the destination at :" + allRides.get(i).getAllStaions().get(lastDes).getLeaviningTime() +"\n";
							for(int midRide=startDes; midRide <= lastDes; midRide++) {
								route+=allRides.get(i).getAllStaions().get(midRide)+"\n"   ;								
							}							
						}
					}
				}
			}
		}
		return route;
	}

	private static String toStringRide(List<Ride> allRides) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < allRides.size();i++) {
			sb.append(( allRides.get(i).toString()));
		}
		return sb.toString();
	}


private static List<Ride> sortRides(List<Ride> allRides) {
	Comparator<Ride> compareByTime = new Comparator<Ride>() {
		public int compare(Ride o1, Ride o2) {
			if(o1.getAllStaions().get(0).getLeaviningTime().getHour() < o2.getAllStaions().get(0).getLeaviningTime().getHour() 				//Compare by hour
					|| (o1.getAllStaions().get(0).getLeaviningTime().getHour() == o2.getAllStaions().get(0).getLeaviningTime().getHour()	//Compare by minute
					&& o1.getAllStaions().get(0).getLeaviningTime().getMinute() < o2.getAllStaions().get(0).getLeaviningTime().getMinute()))
				return -1;
			else if(o1.getAllStaions().get(0).getLeaviningTime().getMinute() ==o2.getAllStaions().get(0).getLeaviningTime().getMinute())
				return 0;
			return 1;
		}	
	};
	Collections.sort(allRides, compareByTime);
	
	return allRides;
}
}




