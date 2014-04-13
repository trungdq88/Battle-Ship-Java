package players;

import java.util.ArrayList;
import java.util.Scanner;

import main.Board;
import main.BoardPieceState;
import main.Game;
import main.Player;
import main.Ship;

public class Human extends Player {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1648355870712332336L;

	public Human() {
		ships = new ArrayList<Ship>();
	}

	public Human(Game game, Board board, String name) {
		this.game = game;
		this.board = board;
		this.name = name;
		ships = new ArrayList<Ship>();
	}

	@Override
	public void placeShips() {
		Scanner in = new Scanner(System.in);
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
	public void shoot(Board board) {


		System.out.println("             " + this.game.player.name
				+ "                            " + this.game.enemy.name);

		Board.printBoard(this.game.player.board, this.game.enemy.board, this.game.yourturn);
		
		//////////////
		
		Scanner in = new Scanner(System.in);
		System.out.println(name + " go:");
		System.out.println("Enter your moves in the form 'C4'");
		
		String move = "";
		String valid = "";

		System.out.println("('exit' - quit game; 'save' - save game)");	
		System.out.println("Enter move: ");	
		move = in.nextLine();
			
		if (move.equals("exit")) {
			System.out.println("Goodbye");
			System.exit(0);
		} else if (move.equals("save")) {
			game.yourturn = !game.yourturn; // Player can continue to move after saving
			game.saveGame();
		} else {
			valid = board.checkMove(move);
			
			while(valid != "ok") {
				System.out.println("ERROR: "+ valid);
				move = in.nextLine();
				valid = board.checkMove(move);
			}
			
			
			processMove(board, move);
		}
	}
	
	public void processMove(Board board, String move){
		
		BoardPieceState result = BoardPieceState.STATE_MISS;
		String message = "";


		if(board.pieces[((int)move.charAt(0)-65)][((int)move.charAt(1)-49 + 1)].used){   
			result = BoardPieceState.STATE_HIT;
			message = "Hit!";
		}
		else{
			result = BoardPieceState.STATE_MISS;
			message = "Miss!";
		}
		board.pieces[((int)move.charAt(0)-65)][((int)move.charAt(1)-49 + 1)].state = result;
		board.pieces[((int)move.charAt(0)-65)][((int)move.charAt(1)-49 + 1)].selected = true;
			
		System.out.println(this.name + " " + message);
	}
}
