package main;

import java.util.List;

public abstract class Player {
	public abstract void placeShips();

	
	public static final int MAX_SHIP_PER_USER = 5;

	public Board board;
	
	public String name;
	
	public List<Ship> ships;
	
	
	/**
	 * Make a shoot to the board.
	 * If this player is a human being, you should show the board state before taking a shoot.
	 * @param board The board to shoot
	 * @return the board state after the shoot
	 */
	public abstract Board shoot(Board board);
}
