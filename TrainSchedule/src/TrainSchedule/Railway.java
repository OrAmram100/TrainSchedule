package TrainSchedule;


public class Railway {
		private String firstStaion;
		private String destiniationstatsion;
		private clockTrain leaviningTime;
		private clockTrain arrivalTime;
		public Railway(String firstStaion, String destiniationstatsion, clockTrain leaviningTime, clockTrain arrivalTime) {
			this.firstStaion = firstStaion;
			this.destiniationstatsion = destiniationstatsion;
			this.leaviningTime = leaviningTime;
			this.arrivalTime = arrivalTime;
		}
		public String getFirstStaion() {
			return firstStaion;
		}
		public void setFirstStaion(String firstStaion) {
			this.firstStaion = firstStaion;
		}
		public String getDestiniationstatsion() {
			return destiniationstatsion;
		}
		public void setDestiniationstatsion(String destiniationstatsion) {
			this.destiniationstatsion = destiniationstatsion;
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
			return "Railway [firstStaion=" + firstStaion + ", destiniationstatsion=" + destiniationstatsion
					+ ", leaviningTime=" + leaviningTime + ", arrivalTime=" + arrivalTime  +"\n";
		}
		

	}
