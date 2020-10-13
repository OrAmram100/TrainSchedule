package TrainSchedule;


public interface IsearchRides {
	public static String search(String station, String destination,int hour, int minutes) throws Exception {
		
		String route = RidesManagement.LocateRide(destination, station, hour, minutes);
		if (!route.equals(""))
			return route;
		else
			return "Sorry there is no rides for your request today!!";
			
	}

}
