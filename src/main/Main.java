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
		System.out.println(" 2. New game with Computer ");
		System.out.println(" 3. Load game ");
		System.out.println(" 4. Exit ");
		
		int number;
		boolean playwithhuman = true;
		
	    do {
	        System.out.println("Your choice: ");
	        while (!in.hasNextInt()) {
	            System.out.println("That's not a number!");
	            in.next(); // this is important!
	        }
	        number = in.nextInt();
	    } while (number <= 0 && number > 4);

		Game game = new Game(playwithhuman);
		String again;
	    if (number == 1) {
			game.play();
	    } else if (number == 2) {
	    	playwithhuman = false;
    		game = new Game(playwithhuman);
    		game.play();
	    } else if (number == 3) {
	    	System.out.println("Enter file name: ");
			in.nextLine();
			String path = in.nextLine();
	    	game.loadGame(path);
	    } else if (number == 4) {
			System.out.println("Good Bye!");
			System.exit(0);
	    }

		System.out.println("Would you like to start a new game?(Y/N): ");
		again = in.nextLine();
	    while(again.charAt(0) == 'Y'){
			game = new Game(playwithhuman);
			game.play();
			System.out.println("Would you like to start a new game?(Y/N): ");
			again = in.nextLine();
		}
		in.close();
		System.out.println("Good Bye!");
	}
}
