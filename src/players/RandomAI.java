package players;

import java.util.ArrayList;
import java.util.Random;

import main.Board;
import main.Game;
import main.Player;
import main.Ship;

public class RandomAI extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5910024849717579343L;

	public RandomAI(Game game, Board board, String string) {
		this.game = game;
		this.board = board;
		this.name = string;
	}

	@Override
	public void placeShips() {
		Random rnd = new Random();
		this.ships = new ArrayList<Ship>();
		for (int type = 1; type <= 5; ++type) {
			Ship ship = new Ship(type);
			this.ships.add(ship);
			int ran1 = rnd.nextInt(10);
			int ran2 = rnd.nextInt(10);
			while (!placeBoard(
					this.board, 
					"VH".charAt(rnd.nextInt(2)), 
					ship, 
					"ABCDEFGHIJ".substring(ran1, ran1 + 1) + "0123456789".substring(ran2, ran2 + 1)
					)
					) {
				ran1 = rnd.nextInt(10);
				ran2 = rnd.nextInt(10);
			}
		}
		System.out.println(this.name + " have placed the ships");
		System.out.println(this.board);
	}

	@Override
	public void shoot(Board board) {

		Random rnd = new Random();
		int ran1 = rnd.nextInt(10);
		int ran2 = rnd.nextInt(10);
		String move = "ABCDEFGHIJ".substring(ran1, ran1 + 1) + "0123456789".substring(ran2, ran2 + 1);
		String valid = "";
		
		valid = board.checkMove(move);
		
		while(valid != "ok") {
			ran1 = rnd.nextInt(10);
			ran2 = rnd.nextInt(10);
			move = "ABCDEFGHIJ".substring(ran1, ran1 + 1) + "0123456789".substring(ran2, ran2 + 1);
			valid = board.checkMove(move);
		}
		
		processMove(board, move);
		
	}

}
