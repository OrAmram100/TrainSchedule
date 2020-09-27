package TrainSchedule;


public interface IsearchRides {
	public static void search(String station, String destination,int hour, int minutes) throws Exception {
		
		String route = RidesManagement.LocateRide(destination, station, hour, minutes);
		if (!route.equals(""))
			System.out.println("Station " + station + " --> Station " + destination + "\n" + route);
		else
			System.out.println("Sorry there is no rides for your request today!!");
		
	}

}
