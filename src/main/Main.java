package main;

import java.util.Scanner;

public class Main {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("===================================================");
		System.out.println("=                                                 =");
		System.out.println("=       *** *** *** BATTLE SHIP *** *** ***       =");
		System.out.println("=                                                 =");
		System.out.println("===================================================");
		System.out.println(" 1. New game ");
		System.out.println(" 2. Load game ");
		System.out.println(" 3. Exit ");
		
		int number;
	    do {
	        System.out.println("Your choice: ");
	        while (!in.hasNextInt()) {
	            System.out.println("That's not a number!");
	            in.next(); // this is important!
	        }
	        number = in.nextInt();
	    } while (number <= 0 && number > 3);

		Game game = new Game();
		String again;
	    if (number == 1) {
			game.play();
	    } else if (number == 2) {
	    	System.out.println("Enter file name: ");
			in.nextLine();
			String path = in.nextLine();
	    	game.loadGame(path);
	    }

		System.out.println("Would you like to start a new game?(Y/N): ");
		again = in.nextLine();
	    while(again.charAt(0) == 'Y'){
			game = new Game();
			game.play();
			System.out.println("Would you like to start a new game?(Y/N): ");
			again = in.nextLine();
		}
		in.close();
		System.out.println("Good Bye!");
	}
}
