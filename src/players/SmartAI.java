package players;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.Board;
import main.BoardPieceState;
import main.Game;
import main.Player;
import main.Ship;

/**
 * This AI is simple: when a place is hit, the AI will try to shoot nearby
 * places. If there is no more available nearby placed, random placed will be
 * choosen to shoot.
 * 
 * Avg Score: 75
 * 
 */
public class SmartAI extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6766051984084120362L;

	public SmartAI(Game game, Board board, String string) {
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
					"ABCDEFGHIJ".substring(ran1, ran1 + 1)
							+ "0123456789".substring(ran2, ran2 + 1))) {
				ran1 = rnd.nextInt(10);
				ran2 = rnd.nextInt(10);
			}
		}
		System.out.println(this.name + " have placed the ships");
		System.out.println(this.board);
	}

	@Override
	public void shoot(Board board) {

		processMove(board, calcMove(board));

	}

	private String calcMove(Board board) {
		String move = "";
		for (int x = 0; x < Board.DEFAULT_HEIGHT; ++x) {
			for (int y = 0; y < Board.DEFAULT_WIDTH; ++y) {
				if (board.pieces[x][y].state == BoardPieceState.STATE_HIT) {

					try {
						if (board.pieces[x + 1][y].state == BoardPieceState.STATE_FREE
								|| board.pieces[x + 1][y].state == BoardPieceState.STATE_BUSY) {
							move = "ABCDEFGHIJ".substring(x + 1, x + 1 + 1)
									+ "0123456789".substring(y, y + 1);
						}
						if (board.pieces[x - 1][y].state == BoardPieceState.STATE_FREE
								|| board.pieces[x - 1][y].state == BoardPieceState.STATE_BUSY) {
							move = "ABCDEFGHIJ".substring(x - 1, x - 1 + 1)
									+ "0123456789".substring(y, y + 1);
						}
						if (board.pieces[x][y + 1].state == BoardPieceState.STATE_FREE
								|| board.pieces[x][y + 1].state == BoardPieceState.STATE_BUSY) {
							move = "ABCDEFGHIJ".substring(x, x + 1)
									+ "0123456789".substring(y + 1, y + 1 + 1);
						}
						if (board.pieces[x][y - 1].state == BoardPieceState.STATE_FREE
								|| board.pieces[x][y - 1].state == BoardPieceState.STATE_BUSY) {
							move = "ABCDEFGHIJ".substring(x, x + 1)
									+ "0123456789".substring(y - 1, y - 1 + 1);
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						// This may happend, don't worry
					}

					// System.out.println("OK move: " + move);
					// System.out.println("board: " + board);
					// (new Scanner(System.in)).next();

					if (board.checkMove(move) == "ok") {
						break;
					}
				}
			}
		}

		if (board.checkMove(move) != "ok") {
			Random rnd = new Random();
			int ran1 = rnd.nextInt(10);
			int ran2 = rnd.nextInt(10);

			move = "ABCDEFGHIJ".substring(ran1, ran1 + 1)
					+ "0123456789".substring(ran2, ran2 + 1);
			String valid = board.checkMove(move);

			while (valid != "ok") {
				ran1 = rnd.nextInt(10);
				ran2 = rnd.nextInt(10);

				move = "ABCDEFGHIJ".substring(ran1, ran1 + 1)
						+ "0123456789".substring(ran2, ran2 + 1);
				valid = board.checkMove(move);
			}
		}
		return move;
	}

}
