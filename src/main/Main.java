package main;

import java.util.Scanner;

public class Main {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		String again;
		
		Game game = new Game();
		game.play();
		
		System.out.println("Would you like to play again?(Y/N): ");
		again = in.nextLine();
		while(again.charAt(0) == 'Y'){
			game = new Game();
			game.play();
			System.out.println("Would you like to play again?(Y/N): ");
			again = in.nextLine();
		}
		in.close();
		System.out.println("Good Bye!");
	}
}
