package TrainSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ride {
	private String firstStaion;
	private String destiniation;
	private List<Station> allStaions;

	public boolean addStation(Station newStation) {
		allStaions.add(newStation);
		return true;
	}

	public Ride(String firstStaion, String destiniationstatsion) {
		this.firstStaion = firstStaion;
		this.destiniation = destiniationstatsion;
		this.allStaions = new ArrayList<Station>();
	}
//	public int getTimeOfStations() {
//		for(int i=0; i<allStaions.size();i++ ) {
//			if(allStaions.get(i).getLeaviningTime().getHour())
//		}
//	}
	public  void showrailWays() {
		Comparator<Station> compareByTime = new Comparator<Station>() {

			public int compare(Station o1, Station o2) { // conditions to sort
				if (o1.getLeaviningTime().getHour() < o2.getLeaviningTime().getHour())
					return -1;
				else if (o1.getLeaviningTime().getHour() == o2.getLeaviningTime().getHour())
					if (o1.getLeaviningTime().getMinute() < o2.getLeaviningTime().getMinute())
						return -1;
					else if (o1.getLeaviningTime().getMinute() == o2.getLeaviningTime().getMinute())
						return 0;
				return 1;
			}
		};
		Collections.sort(allStaions, compareByTime);
	}
	public Ride(String firstStaion, String destiniationstatsion, String intermediateStops, clockTrain leaviningTime,
			clockTrain arrivalTime) {
		this.firstStaion = firstStaion;
		this.destiniation = destiniationstatsion;
	}

	public String getFirstStaion() {
		return firstStaion;
	}

	public void setFirstStaion(String firstStaion) {
		this.firstStaion = firstStaion;
	}

	public String getDestiniationstatsion() {
		return destiniation;
	}

	public void setDestiniationstatsion(String destiniationstatsion) {
		this.destiniation = destiniationstatsion;
	}


	public List<Station> getAllStaions() {
		return allStaions;
	}



	public void setAllStaions(List<Station> allStaions) {
		this.allStaions = allStaions;
	}



	@Override
	public String toString() {
		StringBuffer sf = new StringBuffer();
			sf.append(("the first station is " + allStaions.get(0).toString()));
		sf.append("The final destination is " + destiniation + "\n");
		if(allStaions.size()>1) {			
			sf.append(" the intermediate station are:\n");	
		for (int i = 1; i < allStaions.size(); i++) {
			sf.append( i + "," + allStaions.get(i).toString() + "The final destination is " + destiniation + "\n"+ "\n <<<<<< \n ");			
		}
		}
		return sf.toString();
	}
}




