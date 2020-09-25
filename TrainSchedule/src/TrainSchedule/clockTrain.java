package TrainSchedule;

public class clockTrain {
	private int hour, minute;

	public clockTrain(int hours, int minutes) throws Exception {
		if (checkClock(hours, minutes)) {
			this.hour = hours;
			this.minute = minutes;
		}
	}

	public static boolean checkClock(int hours, int minutes) throws Exception {
		if (0 > hours || hours > 24) {
			throw new Exception("the hour is not valid");
		}
		if (0 > minutes || minutes > 60) {
			throw new Exception("the minutes are not valid");
		}
		return true;

	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public void tick() {
		if (minute >= 60) {
			hour++;
			minute = 0;
		}
		if (hour >= 24) {
			hour = 0;
		}
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
}
