package TrainSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
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

				System.out.println("Please enter your Origin station");
				String Origin= scan.nextLine();
				Origin+=scan.nextLine();
				System.out.println("Please enter train's leaving time");
				scan.useDelimiter(":|\\s+");
				int OHour = scan.nextInt();
				int OHin = scan.nextInt();
				System.out.println("Please enter Destination station");
				String destiniation = scan.nextLine();
				destiniation+=scan.nextLine();
				System.out.println("Please enter Expected arrival time");
				scan.useDelimiter(":|\\s+");
				int DHour = scan.nextInt();
				int DMin = scan.nextInt();
				leaviningTime.add(new clockTrain(OHour, OHin));
				destiniationTime.add(new clockTrain(DHour, DMin));
				allRides.add(new Ride(Origin, destiniation,destiniationTime.get(numOfRides),leaviningTime.get(numOfRides)));
				Station newStation= new Station(Origin, leaviningTime.get(numsOfStations));
				Station destinateStation = new Station(destiniation,destiniationTime.get(numsOfStations));		
				allRides.get(numOfRides).addStation(newStation);
				allRides.get(numOfRides).addStation(destinateStation);
				scan.nextLine();
				numsOfStations++;
				System.out.println("Are there intermediate stop?");				
				String interCheck = scan.next();
				while(interCheck.equalsIgnoreCase("yes")) {
					System.out.println("Please enter station's name");
					String intermediate = scan.nextLine();
					intermediate+=scan.nextLine();
					System.out.println("Please enter train's leaving time");
					scan.useDelimiter(":|\\s+");
					int ILHour= scan.nextInt();
					int ILMin = scan.nextInt();
					leaviningTime.add(new clockTrain(ILHour, ILMin));
					Station newInter = new Station(intermediate, leaviningTime.get(numsOfStations));
					allRides.get(numOfRides).addStation(newInter);
					scan.nextLine();
					System.out.println("would you like to add more intermediate station?");
					interCheck =scan.next();
					if(interCheck.equalsIgnoreCase("yes"))
						numsOfStations++;
				}
				numOfRides++;
				break;

			case 2://View schedule
				System.out.println("Israel-RailWays schedule: \n");
				for(int i=0; i < allRides.size();i++) {
					allRides.get(i).sortStations();
				}
				System.out.println(sortRides(allRides));
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
					System.out.println("Station " + station + " --> Station " + des +"\n " + route );
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

	private static String sortRides(List<Ride> allRides) {
		StringBuilder sb = new StringBuilder();
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
		for(int i=0; i < allRides.size();i++) {
			sb.append(( allRides.get(i).toString()));
		}
		return sb.toString();
	}

}





