package main;

import java.io.Serializable;
import java.util.List;

public abstract class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1022976898280162790L;

	public abstract void placeShips();

	public static final int MAX_SHIP_PER_USER = 5;

	public Board board;

	public Game game;

	public String name;

	public List<Ship> ships;

	/**
	 * Make a shoot to the board. If this player is a human being, you should
	 * show the board state before taking a shoot.
	 * 
	 * @param board
	 *            The board to shoot
	 * @return the board state after the shoot
	 */
	public abstract void shoot(Board board);

	public boolean isAllShipPlaced() {
		boolean shipTypes[] = { false, false, false, false, false };
		int typeCount = 0;
		for (int i = 0; i < this.ships.size(); ++i) {
			if (shipTypes.length > i && !shipTypes[ships.get(i).type - 1]) {
				typeCount++;
			}
		}
		return typeCount == shipTypes.length;
	}

	public boolean placeBoard(Board board, char layout, Ship current,
			String locale) {
		boolean overlap = false;
		try {
			if (layout == 'H') {
				for (int x = ((int) locale.charAt(1) - 49 + 1); x < ((int) locale
						.charAt(1) - 49 + 1) + current.getLength(); x++) {
					if (board.pieces[((int) locale.charAt(0) - 65)][x].type != current.type
							&& board.pieces[((int) locale.charAt(0) - 65)][x].used == true)
						overlap = true;
				}
			} else { // layout == 'V'
				for (int x = ((int) locale.charAt(0) - 65); x < ((int) locale
						.charAt(0) - 65) + current.getLength(); x++) {
					if (board.pieces[x][((int) locale.charAt(1) - 49 + 1)].type != current.type
							&& board.pieces[x][((int) locale.charAt(1) - 49 + 1)].used == true)
						overlap = true;
				}
			}
			if (overlap == true)
				return false;

			if (current.placed) {
				if (current.orientation == 'H') {
					for (int x = ((int) current.location.charAt(1) - 49 + 1); x < ((int) current.location
							.charAt(1) - 49 + 1) + current.getLength(); x++) {
						board.pieces[((int) current.location.charAt(0) - 65)][x].state = BoardPieceState.STATE_FREE;
						board.pieces[((int) current.location.charAt(0) - 65)][x].used = false;
						board.pieces[((int) current.location.charAt(0) - 65)][x].type = 0;
					}
				} else { // orientation == 'V'
					for (int x = ((int) current.location.charAt(0) - 65); x < ((int) current.location
							.charAt(0) - 65) + current.getLength(); x++) {
						board.pieces[x][((int) current.location.charAt(1) - 49 + 1)].state = BoardPieceState.STATE_FREE;
						board.pieces[x][((int) current.location.charAt(1) - 49 + 1)].used = false;
						board.pieces[x][((int) current.location.charAt(1) - 49 + 1)].type = 0;
					}
				}
			}
			if (layout == 'H') {
				for (int x = ((int) locale.charAt(1) - 49 + 1); x < ((int) locale
						.charAt(1) - 49 + 1) + current.getLength(); x++) {
					board.pieces[((int) locale.charAt(0) - 65)][x].state = BoardPieceState.STATE_BUSY;
					board.pieces[((int) locale.charAt(0) - 65)][x].used = true;
					board.pieces[((int) locale.charAt(0) - 65)][x].type = current.type;
				}
			} else { // layout == 'V'
				for (int x = ((int) locale.charAt(0) - 65); x < ((int) locale
						.charAt(0) - 65) + current.getLength(); x++) {
					board.pieces[x][((int) locale.charAt(1) - 49 + 1)].state = BoardPieceState.STATE_BUSY;
					board.pieces[x][((int) locale.charAt(1) - 49 + 1)].used = true;
					board.pieces[x][((int) locale.charAt(1) - 49 + 1)].type = current.type;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// validate process was wrong
			return false;
		}
		current.placed = true;
		current.location = locale;
		current.orientation = layout;
		return true;
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
