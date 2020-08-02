package TrainSchedule;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numOfOlaining=0;
		int choice = 0;
		boolean ifContinue = true;
		List<clockTrain> leaviningTime = new ArrayList<clockTrain>();
		List<Railway> dp = new ArrayList<Railway>();
		List<clockTrain> destiniationTime = new ArrayList<clockTrain>();
		do {
			System.out.println("Welcome to israel railways.\n Choose one of the following options:");
			System.out.println("1- Travel details\n 2-View travel details\n 9- exit");
			choice = scan.nextInt();

			switch (choice) {

			case 1:

				System.out.println("please enter Starting station");
				String station = scan.next();
				System.out.println("Please enter leaving time");
				scan.useDelimiter(":|\\s+");
				int h = scan.nextInt();
				int m = scan.nextInt();
				System.out.println("Please enter Destination station");
				String destiniation = scan.next();
				System.out.println("Please enter Expected arrival time");
				scan.useDelimiter(":|\\s+");
				int h1 = scan.nextInt();
				int m1 = scan.nextInt();
				leaviningTime.add(new clockTrain(h, m));
				destiniationTime.add(new clockTrain(h1, m1));
				dp.add(new Railway(station, destiniation, leaviningTime.get(numOfOlaining),destiniationTime.get(numOfOlaining)));
				numOfOlaining++;

				break;

			case 2:	
			System.out.println(	showrailWays(dp));
				break;


			case 9: // Exit:
				System.out.println("Exit.\nThank you for using.\nhave a great day !");
				ifContinue = false;
				break;

			default:
				System.out.println("This choise does not exist in the menu"); // case choice is not valid
				break;
			}
		} while (ifContinue);


	}

	private static String showrailWays(List railWays) {
		StringBuilder sb = new StringBuilder();
		Comparator<Railway> compareByTime = new Comparator<Railway>() {


			public int compare(Railway o1, Railway o2) {    //התנאים למיון
				if(o1.getLeaviningTime().getHour() <o2.getLeaviningTime().getHour() )
					return -1;
				else if(o1.getLeaviningTime().getHour() == o2.getLeaviningTime().getHour())	
						if(o1.getLeaviningTime().getMinute()< o2.getLeaviningTime().getMinute())
							return -1;
						else if(o1.getLeaviningTime().getMinute()==o2.getLeaviningTime().getMinute())
							return 0;
				return 1;
			}	
		};
		Collections.sort(railWays, compareByTime);
		for(int i=0; i < railWays.size();i++) {
			sb.append(( railWays.get(i).toString()));
		}
		return sb.toString();
	}


}


//	private static void sort(ArrayList<String> hours, ArrayList<String> minutes) {
//		// TODO Auto-generated method stub
//		
//	}
//}
