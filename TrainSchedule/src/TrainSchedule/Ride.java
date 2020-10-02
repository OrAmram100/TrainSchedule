package TrainSchedule;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ride {
	private String firstStaion;
	private String destiniation;
	private clockTrain arrivalTime;
	private clockTrain leaviningTime;
	private List<Station> allStaions;

	public boolean addStation(Station newStation) {

		allStaions.add(newStation);
		return true;
	}

	public Ride(String firstStaion, String destiniation, clockTrain arrivalTime, clockTrain leaviningTime) {
		this.firstStaion = firstStaion;
		this.destiniation = destiniation;
		this.arrivalTime = arrivalTime;
		this.leaviningTime = leaviningTime;
		this.allStaions = new ArrayList<Station>();

	}

	public clockTrain getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(clockTrain arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public clockTrain getLeaviningTime() {
		return leaviningTime;
	}

	public void setLeaviningTime(clockTrain leaviningTime) {
		this.leaviningTime = leaviningTime;
	}

	public void sortStations() {
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
		StringBuffer schedule = new StringBuffer();
		schedule.append("Origin: " + firstStaion + " - " + getLeaviningTime() + ". \n");
			if (allStaions.size() == 3)
				schedule.append("Stop-station on the way:\n");
			else if(allStaions.size() >3) {
				schedule.append("Stop-stations on the way:\n");
			}
			for (int i = 1; i < allStaions.size() - 1; i++) {

				schedule.append(allStaions.get(i).toString() + "\n");
			}
			schedule.append("Destination: " + destiniation + " - " + getArrivalTime() + "\n");
		
		schedule.append("\n");
		return schedule.toString();
	}

	public void save(PrintWriter pw) {
		for (int i = 0; i < allStaions.size(); i++) {
			if(i == 0) {	//pw first and last stations
				pw.println(allStaions.get(i).getName()+"\n"+allStaions.get(i).getLeaviningTime().toString());
				pw.println("Destination"+"\n" + allStaions.get(allStaions.size()-1).getName() + "\n" + allStaions.get(allStaions.size()-1).getLeaviningTime().toString());
			}
			else if(i > 0 && i < allStaions.size()-1)	//pw only Intermediate stations
				pw.println("Intermediate"+"\n" + allStaions.get(i).getName() + "\n" + allStaions.get(i).getLeaviningTime().toString());			
			 
		}
	}
}
