package players;

import main.Board;
import main.Game;
import main.Player;

public class RandomAI extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5910024849717579343L;

	public RandomAI(Game game, Board board, String string) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.board = board;
		this.name = string;
	}

	@Override
	public void placeShips() {
		// TODO Auto-generated method stub

		System.out.println("I don't place ships");
	}

	@Override
	public void shoot(Board board) {
		System.out.println("I don't take a shoot");
		
	}

}
