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
		for (int i = 1; i <= MAX_SHIP_PER_USER; ++i) {
			ships.add(new Ship(i));
			System.out.println(name + " placed a ship ("+i+")!");
		}
	}

	@Override
	public Board shoot(Board board) {
		ships.remove(0);
		System.out.println(name + " shooted!");
		return board;
	}


}
