package TrainSchedule;

public class Station {
	private String name;
	private clockTrain arrivalTime;
	private clockTrain leaviningTime;

	public Station(String name, clockTrain leaviningTime, clockTrain arrivalTime) {
		this.name = name;
		this.leaviningTime = leaviningTime;
		this.arrivalTime = arrivalTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public clockTrain getLeaviningTime() {
		return leaviningTime;
	}

	public void setLeaviningTime(clockTrain leaviningTime) {
		this.leaviningTime = leaviningTime;
	}

	public clockTrain getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(clockTrain arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public String toString() {
		return "Station :name=" + name  + ", arrivalTime=" + arrivalTime + ", leaviningTime=" + leaviningTime + ".";
	}

}
