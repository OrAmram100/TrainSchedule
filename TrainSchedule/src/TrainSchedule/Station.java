package TrainSchedule;

public class Station {
	private String name;
	private clockTrain LeaviningTime;

	public Station(String name, clockTrain LeaviningTime) {
		this.name = name;
		this.LeaviningTime = LeaviningTime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public clockTrain getLeaviningTime() {
		return LeaviningTime;
	}

	public void setLeaviningTime(clockTrain leaviningTime) {
		this.LeaviningTime = leaviningTime;
	}

	@Override
	public String toString() {
		return name  + " - " + LeaviningTime + ".";
	}

}
