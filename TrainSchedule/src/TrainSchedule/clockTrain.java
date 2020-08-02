package TrainSchedule;

public class clockTrain  {
	private int hour, minute;

	public clockTrain(int hours, int minutes) {
		setClock(hours, minutes);
	}

	public void setClock(int hours, int minutes) {
		if (0 <= hours && hours < 24)
			hour = hours;
		else
			hour = 0;
		if (0 <= minutes && minutes < 60)
			minute = minutes;
		else
			minute = 0;
	}

	public String toString() {
		String sb = new String();
		if (hour < 10)
			sb += "0" + hour + ":";
		else
			sb += hour + ":";
		if (minute < 10)
			sb += "0" + minute;
		else
			sb += minute;
		return sb;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	public void tick() {
		if(minute >= 60)
	    {
	        hour++;
	        minute = 0;
	    }
	    if(hour >=24)
	    {
	        hour = 0;
	    } 
}
}
