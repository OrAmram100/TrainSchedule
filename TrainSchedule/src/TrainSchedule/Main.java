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
		File file = new File("C:\\Users\\Administrator\\git\\TrainSchedule\\TrainSchedule\\src\\TrainSchedule\\TrainSchedule.txt");
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
			System.out.println("Welcome to Israel-RailWays!	 Menu: \n");
			System.out.println(" 1) Enter today's train schedule \n 2) View travel details \n 3) Planning a train route \n 9) Exit");
			choice = scan.nextInt();

			switch (choice) {

			case 1://Data insertion
				
				int doContinue = sFile.nextInt();
				do {
					sFile.nextLine();
				System.out.println("Please enter your Origin station");
				String Origin= sFile.nextLine();
				System.out.println(Origin);
				//Origin+=sFile.nextLine();
				System.out.println("Please enter train's leaving time");
				sFile.useDelimiter(":|\\s+");
				int OHour = sFile.nextInt();
				int OHin = sFile.nextInt();
				System.out.println("Please enter Destination station");
				String destiniation = sFile.nextLine();
				destiniation+=sFile.nextLine();
				System.out.println("Please enter Expected arrival time");
				sFile.useDelimiter(":|\\s+");
				int DHour = sFile.nextInt();
				int DMin = sFile.nextInt();
				leaviningTime.add(new clockTrain(OHour, OHin));
				destiniationTime.add(new clockTrain(DHour, DMin));
				allRides.add(new Ride(Origin, destiniation,destiniationTime.get(numOfRides),leaviningTime.get(numsOfStations)));
				Station newStation= new Station(Origin, leaviningTime.get(numsOfStations));
				Station destinateStation = new Station(destiniation,destiniationTime.get(numOfRides));		
				allRides.get(numOfRides).addStation(newStation);
				allRides.get(numOfRides).addStation(destinateStation);
				sFile.nextLine();
				numsOfStations++;
				System.out.println("Are there intermediate stop?");				
				String interCheck = sFile.next();
				while(interCheck.equalsIgnoreCase("yes")) {
					System.out.println("Please enter station's name");
					String intermediate = sFile.nextLine();
					intermediate+=sFile.nextLine();
					System.out.println("Please enter train's leaving time");
					sFile.useDelimiter(":|\\s+");
					int ILHour= sFile.nextInt();
					int ILMin = sFile.nextInt();
					leaviningTime.add(new clockTrain(ILHour, ILMin));
					Station newInter = new Station(intermediate, leaviningTime.get(numsOfStations));
					allRides.get(numOfRides).addStation(newInter);
					sFile.nextLine();
					numsOfStations++;
					System.out.println("would you like to add more intermediate station?");
					interCheck =sFile.next();
				}
				numOfRides++;
				doContinue = sFile.nextInt();	
				} while (doContinue == 1);
				sFile.close();
				break;

			case 2://View schedule
				System.out.println("Israel-RailWays schedule: \n");
				for(int i=0; i < allRides.size();i++) {
					allRides.get(i).sortStations();
				}
				sortRides(allRides);
				System.out.println(toStringRide(allRides));
				break;

			case 3://Plan trip
				for(int i=0; i < allRides.size();i++) {
					allRides.get(i).sortStations();
				}
				sortRides(allRides);
				System.out.println("Please enter starting station");
				String station = scan.nextLine();
				station+=scan.nextLine();
				System.out.println("Please enter destiniation station");
				String des = scan.nextLine();
				System.out.println("Please enter leaving time");
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
				break;

			default:
				System.out.println("This choise does not exist in the menu"); // case choice is not valid
				break;
			}

		} while (ifContinue);

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
							//route = " "  + allRides.get(i).getAllStaions().get(lastDes)+"\n";
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


private static List sortRides(List<Ride> allRides) {
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




