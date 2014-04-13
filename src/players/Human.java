package players;

import java.util.ArrayList;
import java.util.Scanner;

import main.Board;
import main.Player;
import main.Ship;

public class Human extends Player {

	Scanner in = new Scanner(System.in);;

	public Human() {
		ships = new ArrayList<Ship>();
	}

	public Human(Board board, String name) {
		this.board = board;
		this.name = name;
		ships = new ArrayList<Ship>();
	}

	@Override
	public void placeShips() {
		ships = new ArrayList<Ship>();
		System.out.println("==================== " + this.name
				+ " place ships ====================");
		System.out.println(this.board);
		System.out.println("Place your ships on the board");
		System.out
				.println("You have 5 ships:  1: Patrol boat (2 squares),   2: Submarine (3 squares), ");
		System.out
				.println("                   3: Destroyer (3 squares),     4: Battleship (4 squares), ");
		System.out
				.println("                   5: Aircraft carrier (5 squares)");
		System.out
				.println("First, select the number of the ship. "
						+ "Then select the first place where you want the ship to be placed, like 'C4'.");
		System.out
				.println("Then select the orientation, H=horizontal, V=vertical");
		System.out.println("For example, '2 B5 V', then press enter");
		System.out.println("When you are done, enter 'S' to start.");

		String input = "";
		boolean start = false;

		while (!start) {
			input = in.nextLine();

			if (input.length() == 1) {
				if (input.charAt(0) == 'S') {
					if (this.isAllShipPlaced())
						start = true;
					else
						System.out
								.println("You need to place all of your ships.");
				} else {
					System.out.println("Invalid Input. Try Again.");
				}
			} else if (input.length() == 6) {
				if (((int) input.charAt(0) - 48) >= 6) {
					System.out.println("Invalid Ship Type. Try Again.");
				} else if (((int) input.charAt(2) - 65) >= Board.DEFAULT_HEIGHT) {
					System.out.println("Invalid Row. Try Again.");
				} else if (((int) input.charAt(3) - 48) >= Board.DEFAULT_WIDTH) {
					System.out.println("Invalid Column. Try Again.");
				} else if ((int) input.charAt(5) != 72
						&& (int) input.charAt(5) != 86) {
					System.out.println("Invalid Orientation. Try Again.");
				} else { // Valid Input
					
					if ((int) input.charAt(5) == 72
							&& (new Ship((int) input.charAt(0)-48)).getLength() > (Board.DEFAULT_HEIGHT - ((int) input.charAt(3) - 48))) {
						System.out.println("Invalid Horizontal Placement. Not enough room.");
					} else if ((int) input.charAt(5) == 86
							&& (new Ship((int) input.charAt(0)-48)).getLength() > (Board.DEFAULT_WIDTH + 1 - ((int) input.charAt(2) - 64))) {
						System.out.println("Invalid Vertical Placement. Not enough room.");
					} else { // Valid Placement, except for overlap
						
						int type = (int) input.charAt(0) - 48;
						Ship ship = new Ship(type);
						
						// Find if the ship type is already exists
						for (int i = 0; i < this.ships.size(); ++i) {
							if (this.ships.get(i).type == type) {
								ship = this.ships.get(i);
								break;
							}
						}
						
						this.ships.add(ship);

						placeBoard(this.board, input.charAt(5), ship, input.substring(2,4));

					}
				}
			} else {
				System.out.println("Invalid Input. Try Again.");
			}
		}
	}

	@Override
	public Board shoot(Board board) {

		System.out.println(name + " go:");
		System.out.println("Enter your moves in the form 'C4'");
		
		String move = "";
		String valid = "";

		System.out.println("Enter move: ");	
		move = in.nextLine();
			
		valid = board.checkMove(move);
		
		while(valid != "ok") {
			System.out.println("ERROR: "+ valid);
			move = in.nextLine();
			valid = board.checkMove(move);
		}
		
		
		return processMove(board, move);
	}
	
	public void processMove(String move){
		
		count++;
		char himi = ' ';
		String message = "";
		
		if(turn == 'X'){
			if(eboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].used){   
				himi = 'X';
				message = "Hit!";
			}
			else{
				himi = 'o';
				message = "Miss!";
			}
			eboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].piece = "_"+himi+"_|";
			eboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].selected = true;
		}
		else{
			if(pboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].used){   
				himi = 'X';
				message = "Hit!";
				AI.himi2 = AI.himi;
				AI.himi = 'H';
				AI.count++;
			}
			else{
				himi = 'o';
				message = "Miss!";
				AI.himi2 = AI.himi;
				AI.himi = 'M';
				AI.count++;
			}
			pboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].piece = "_"+himi+"_|";
			pboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].selected = true;
		}
		
		int counter = 0;
		if(himi == 'X'){
			if(turn == 'X'){
				int stype = eboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].type;
				
				for(int x=0;x<6;x++){
					for(int y=0;y<6;y++){
						if(eboard[x][y].selected && eboard[x][y].type == stype)
							counter++;
					}
				}
				if(counter == (stype + 1)){
					String xship = "";
					if(stype == 1){
						xship = "Submarine!";
						esub.destroyed = true;
					}
					else if(stype == 2){
						xship = "Destroyer!";
						edestroyer.destroyed = true;
					}
					else{
						xship = "Battleship!";
						ebattleship.destroyed = true;
					}
					message += " You destroyed the " + xship;
				}
			}
			else{
				int stype = pboard[((int)move.charAt(0)-65)][((int)move.charAt(1)-49)].type;
				
				for(int x=0;x<6;x++){
					for(int y=0;y<6;y++){
						if(pboard[x][y].selected && pboard[x][y].type == stype)
							counter++;
					}
				}
				if(counter == (stype + 1)){
					String xship = "";
					if(stype == 1){
						xship = "Submarine!";
						sub.destroyed = true;
					}
					else if(stype == 2){
						xship = "Destroyer!";
						destroyer.destroyed = true;
					}
					else{
						xship = "Battleship!";
						battleship.destroyed = true;
					}
					message += " They've destroyed your " + xship;
					AI.himi = 'M';  //Make it choose a random # next time, because ship destroyed
					AI.himi2 = 'M';
				}
			}
		}
		if(turn == 'O')
			message = "Enemy " + message;
		System.out.println(message);
	}
}
