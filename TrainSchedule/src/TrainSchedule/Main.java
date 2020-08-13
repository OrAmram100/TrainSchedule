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
		int choice = 0;
		boolean ifContinue = true;
		List<clockTrain> leaviningTime = new ArrayList<clockTrain>();
		List<Ride> allRides = new ArrayList<Ride>();
		List<clockTrain> destiniationTime = new ArrayList<clockTrain>();
		do {
			System.out.println("Welcome to israel railways.\n Choose one of the following options:");
			System.out.println("1- Travel details\n 2-View travel details \n3-Planning a train route\n 9- exit");
			choice = scan.nextInt();

			switch (choice) {

			case 1:

				System.out.println("please enter Starting station");
				String name= scan.nextLine();
				name+=scan.nextLine();
				System.out.println("Please enter leaving time");
				scan.useDelimiter(":|\\s+");
				int h = scan.nextInt();
				int m = scan.nextInt();
				System.out.println("Please enter Destination station");
				String destiniation = scan.nextLine();
				destiniation+=scan.nextLine();
				System.out.println("Please enter Expected arrival time");
				scan.useDelimiter(":|\\s+");
				int h1 = scan.nextInt();
				int m1 = scan.nextInt();
				leaviningTime.add(new clockTrain(h, m));
				destiniationTime.add(new clockTrain(h1, m1));
				Station s= new Station(name, leaviningTime.get(numsOfStations),destiniationTime.get(numsOfStations));
				allRides.add(new Ride(name, destiniation));
				allRides.get(numOfRides).addStation(s);
				scan.nextLine();
				numsOfStations++;
				System.out.println("Are there intermediate stop?");				
				String answer = scan.next();
				while(answer.equalsIgnoreCase("yes")) {
					System.out.println("Please enter name station");
					String name2 = scan.nextLine();
					name2+=scan.nextLine();
					System.out.println("Please enter Expected arrival time");
					scan.useDelimiter(":|\\s+");
					int h2 = scan.nextInt();
					int m2 = scan.nextInt();
					System.out.println("Please enter leaving time");
					scan.useDelimiter(":|\\s+");
					int h3= scan.nextInt();
					int m3 = scan.nextInt();
					leaviningTime.add(new clockTrain(h3, m3));
					destiniationTime.add(new clockTrain(h2, m2));
					Station s1= new Station(name2, leaviningTime.get(numsOfStations),destiniationTime.get(numsOfStations));
					allRides.get(numOfRides).addStation(s1);
					scan.nextLine();
					System.out.println("Do you want to add more intermediate station ?");
					answer =scan.next();

					numsOfStations++;
				}
				numOfRides++;
				break;

			case 2:
				for(int i=0; i < allRides.size();i++)
					allRides.get(i).sortStations();
				System.out.println(sortRides(allRides));
				break;

			case 3:
				//&&allRides.get(i).getAllStaions().get(j).getName().equalsIgnoreCase(des)
				System.out.println("Please enter starting station");
				String station = scan.nextLine();
				station+=scan.nextLine();
				System.out.println("Please enter destiniation station");
				String des = scan.nextLine();
				des+=scan.nextLine();
				System.out.println("Please enter leaving time");
				scan.useDelimiter(":|\\s+");
				int h4= scan.nextInt();
				int m5 = scan.nextInt();
				String sb = "";
				for(int i=0; i < allRides.size();i++) {
					for(int startDes=0; startDes < allRides.get(i).getAllStaions().size();startDes++) {
						if(allRides.get(i).getAllStaions().get(startDes).getName().equalsIgnoreCase(station)&&allRides.get(i).getAllStaions().get(startDes).getLeaviningTime().getHour() >= h4){
							
							for(int lastDes=startDes; lastDes < allRides.get(i).getAllStaions().size(); lastDes++) {
								if(allRides.get(i).getAllStaions().get(lastDes).getName().equalsIgnoreCase(des)) {
								
									for(int midRide=startDes; midRide <= lastDes; midRide++) {
										sb+=allRides.get(i).getAllStaions().get(midRide)+"\n";
									}
								}
							}
						}
					}
				}
				if(!sb.equals(""))
				System.out.println("Station " + station + " --> Station " + des +"\n " + sb  );
				else
					System.out.println("Sorry there is no rides for your request today!!");
				break;


			case 9: // Exit:
				System.out.println("Exit.\nThank you for using.\nhave a great day !");
				ifContinue = false;
				break;

			default:
				System.out.println("This choise does not exist in the menu"); // case choice is not valid
				break;
			}
		} while (ifContinue);

	}

	private static String sortRides(List<Ride> allRides) {
		StringBuilder sb = new StringBuilder();

		Comparator<Ride> compareByTime = new Comparator<Ride>() {


			public int compare(Ride o1, Ride o2) {
				if(o1.getAllStaions().get(0).getLeaviningTime().getHour() < o2.getAllStaions().get(0).getLeaviningTime().getHour())
					return -1;
				else if(o1.getAllStaions().get(0).getLeaviningTime().getHour() == o2.getAllStaions().get(0).getLeaviningTime().getHour())									
					if(o1.getAllStaions().get(0).getLeaviningTime().getMinute() <o2.getAllStaions().get(0).getLeaviningTime().getMinute())
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
//




