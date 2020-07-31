
import java.util.Scanner;

public class Train {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int choice = 0;
		boolean ifContinue = true;
		do {
			System.out.println("Welcome to israel railways.\n Choose one of the following options:");
			System.out.println("1- Travel details\n 2-View travel details\n 9- exit");
			choice = scan.nextInt();
//sssssssssssssssssssssssssssssssssssssssssssssssssssssssss
			switch (choice) {

			case 1:
				System.out.println("please enter Starting station");
				String station = scan.next();
				System.out.println("Please enter leaving time");
				scan.useDelimiter(":|\\s+");
				int h = scan.nextInt();
				int m = scan.nextInt();
				System.out.println(h+":"+m);
				System.out.println("Please enter Destination station");
				String destiniation = scan.next();
				System.out.println("Please enter Expected arrival time");
				scan.useDelimiter(":|\\s+");
				int h1 = scan.nextInt();
				int m1 = scan.nextInt();
				System.out.println(h+":"+m);
				ArrayList<Integer> hours = new ArrayList<Integer>();
				hours.add(h1);
				ArrayList<Integer> minutes = new ArrayList<Integer>();
				minutes.add(m1);

				break;

			case 2:
				
		//		sort(hours.get(index),minutes);
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
}
