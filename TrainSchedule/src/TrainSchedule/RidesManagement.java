package TrainSchedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class RidesManagement {
	private static List<Ride> allRides = new ArrayList<Ride>();
	private static List<clockTrain> leaviningTime = new ArrayList<clockTrain>();
	private static List<clockTrain> destiniationTime = new ArrayList<clockTrain>();
	private static int numOfRides;
	private static int numsOfStations;


	
	public List<Ride> getAllRides() {
		return allRides;
	}

	public static void setNumOfRides(int numOfRides) {
		RidesManagement.numOfRides = numOfRides;
	}

	public static void DataInsertionViaFile(Scanner sFile) throws Exception {
		int doContinue = sFile.nextInt();
		do {
			sFile.nextLine();
			String Origin = sFile.nextLine();
			sFile.useDelimiter(":|\\s+");
			int OHour = sFile.nextInt();
			int OMin = sFile.nextInt();
			String destiniation = sFile.nextLine();
			destiniation += sFile.nextLine();
			sFile.useDelimiter(":|\\s+");
			int DHour = sFile.nextInt();
			int DMin = sFile.nextInt();
			leaviningTime.add(new clockTrain(OHour, OMin));
			destiniationTime.add(new clockTrain(DHour, DMin));
			allRides.add(new Ride(Origin, destiniation, destiniationTime.get(numOfRides),
					leaviningTime.get(numsOfStations)));
			Station newStation = new Station(Origin, leaviningTime.get(numsOfStations));
			Station destinateStation = new Station(destiniation, destiniationTime.get(numOfRides));
			allRides.get(numOfRides).addStation(newStation);
			allRides.get(numOfRides).addStation(destinateStation);
			sFile.nextLine();
			numsOfStations++;
			String interCheck = sFile.next();
			while (interCheck.equalsIgnoreCase("yes")) {
				String intermediate = sFile.nextLine();
				intermediate += sFile.nextLine();
				sFile.useDelimiter(":|\\s+");
				int ILHour = sFile.nextInt();
				int ILMin = sFile.nextInt();
				leaviningTime.add(new clockTrain(ILHour, ILMin));
				Station newInter = new Station(intermediate, leaviningTime.get(numsOfStations));
				allRides.get(numOfRides).addStation(newInter);
				sFile.nextLine();
				numsOfStations++;
				interCheck = sFile.next();
			}
			numOfRides++;
			doContinue = sFile.nextInt();
		} while (doContinue == 1);
		System.out.println("Data insertion completed successfully. \n\n");
	}

	public static void DataInsertionViaKeyboard(Scanner scan) throws Exception {
		String doContinue;
		do {
			System.out.println("Enter your Origin station");
			String Origin = scan.nextLine();
			Origin += scan.nextLine();
			boolean isValid = true;
			do {
				try {
					System.out.println("Enter train's leaving time");
					scan.useDelimiter(":|\\s+");
					int OHour = scan.nextInt();
					int OMin = scan.nextInt();
					leaviningTime.add(new clockTrain(OHour, OMin));
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
			do {
				try {
					System.out.println("Enter Expected arrival time");
					scan.useDelimiter(":|\\s+");
					int DHour = scan.nextInt();
					int DMin = scan.nextInt();
					destiniationTime.add(new clockTrain(DHour, DMin));
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
			System.out.println("Enter Destination station");
			String destiniation = scan.nextLine();
			destiniation += scan.nextLine();
			allRides.add(new Ride(Origin, destiniation, destiniationTime.get(numOfRides),
					leaviningTime.get(numsOfStations)));
			Station newStation = new Station(Origin, leaviningTime.get(numsOfStations));
			Station destinateStation = new Station(destiniation, destiniationTime.get(numOfRides));
			allRides.get(numOfRides).addStation(newStation);
			allRides.get(numOfRides).addStation(destinateStation);
			numsOfStations++;
			System.out.println("Are there intermediate stop? (yes/no)");
			String interCheck = scan.next();
			while (interCheck.equalsIgnoreCase("yes")) {
				System.out.println("Enter station's name");
				String intermediate = scan.nextLine();
				intermediate += scan.nextLine();
				System.out.println("Enter train's leaving time");
				scan.useDelimiter(":|\\s+");
				int ILHour = scan.nextInt();
				int ILMin = scan.nextInt();
				leaviningTime.add(new clockTrain(ILHour, ILMin));
				Station newInter = new Station(intermediate, leaviningTime.get(numsOfStations));
				allRides.get(numOfRides).addStation(newInter);
				numsOfStations++;
				System.out.println("Would you like to add more intermediate station?");
				interCheck = scan.next();
			}
			numOfRides++;
			System.out.println("Would you like to add another RIDE? (yes/no)");
			doContinue = scan.next();
		} while (doContinue.equalsIgnoreCase("yes"));
	}

	public static void sortAll() {
		for (int i = 0; i < allRides.size(); i++) {
			allRides.get(i).sortStations();
		}
		sortRides(allRides);
	}

	public static String stringRides() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < allRides.size(); i++) {
			sb.append((allRides.get(i).toString()));
		}
		return sb.toString();
	}

	private static List<Ride> sortRides(List<Ride> allRides) {
		Comparator<Ride> compareByTime = new Comparator<Ride>() {
			public int compare(Ride o1, Ride o2) {
				if (o1.getAllStaions().get(0).getLeaviningTime().getHour() < o2.getAllStaions().get(0)
						.getLeaviningTime().getHour() // Compare by hour
						|| (o1.getAllStaions().get(0).getLeaviningTime().getHour() == o2.getAllStaions().get(0)
								.getLeaviningTime().getHour() // Compare by minute
								&& o1.getAllStaions().get(0).getLeaviningTime().getMinute() < o2.getAllStaions().get(0)
										.getLeaviningTime().getMinute()))
					return -1;
				else if (o1.getAllStaions().get(0).getLeaviningTime().getMinute() == o2.getAllStaions().get(0)
						.getLeaviningTime().getMinute())
					return 0;
				return 1;
			}
		};
		Collections.sort(allRides, compareByTime);

		return allRides;
	}

	public static String LocateRide(String des, String station, int sHour, int sMin) {
		String route = "";
		for (int i = 0; i < allRides.size(); i++) {
			for (int startDes = 0; startDes < allRides.get(i).getAllStaions().size(); startDes++) {
				if (allRides.get(i).getAllStaions().get(startDes).getName().equalsIgnoreCase(station) // Compare name
						&& (allRides.get(i).getAllStaions().get(startDes).getLeaviningTime().getHour() > sHour // Compare
																												// by
																												// hour
								|| (allRides.get(i).getAllStaions().get(startDes).getLeaviningTime().getHour() == sHour
										&& allRides.get(i).getAllStaions().get(startDes).getLeaviningTime()
												.getMinute() >= sMin))) { // Compare by minute
					for (int lastDes = startDes; lastDes < allRides.get(i).getAllStaions().size(); lastDes++) {
						if (allRides.get(i).getAllStaions().get(lastDes).getName().equalsIgnoreCase(des)) {
							route = "The train will arraive to the destination at :"
									+ allRides.get(i).getAllStaions().get(lastDes).getLeaviningTime() + "\n";
							for (int midRide = startDes; midRide <= lastDes; midRide++) {
								route += allRides.get(i).getAllStaions().get(midRide) + "\n";
							}
						}
					}
				}
			}
		}
		return route;
	}

	public static void save(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		for (int i = 0; i < allRides.size(); i++) {
			pw.print( 1 + "\n");
			allRides.get(i).save(pw);
		}
		pw.close();
	}

	public static List<clockTrain> getLeaviningTime() {
		return leaviningTime;
	}

	public static List<clockTrain> getDestiniationTime() {
		return destiniationTime;
	}
}


