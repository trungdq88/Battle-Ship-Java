package main;

import java.io.Serializable;


public class Ship implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5097733353200317795L;
	/**
	 * Possible type:
		1•	Aircraft carrier (5 squares)
		2•	Battleship (4 squares)
		3•	Destroyer (3 squares)
		4•	Submarine (3 squares)
		5•	Patrol boat (2 squares)
	 */
	public int type;
	private int length;
	boolean placed;
	boolean destroyed;
	public String location;
	public char orientation;
	
	public Ship(int type) {
		this.type = type;
		placed = false;
		destroyed = false;
		location = "";
		orientation = ' ';
	}
	
	public int getLength() {
		switch (type) {
		case 1:return 5;
		case 2:return 4;
		case 3: return 3;
		case 4: return 3;
		case 5: return 2;
		default: return -1;
		}
	}
}
