package main;

public class Board {

	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;

	public BoardPiece[][] pieces = new BoardPiece[DEFAULT_WIDTH][DEFAULT_HEIGHT];

	public Board() {
		for (int i = 0; i < pieces.length; ++i) {
			for (int j = 0; j < pieces.length; ++j) {
				pieces[i][j] = new BoardPiece();
			}
		}
	}

	public void place() {
		// TODO Auto-generated method stub

	}

	public static void printBoard(Board board1, Board board2) {
		for (int i = 0; i < Board.DEFAULT_WIDTH; ++i) {
			System.out.print("  " + i);
		}
		System.out.print("       ");
		for (int i = 0; i < Board.DEFAULT_WIDTH; ++i) {
			System.out.print("  " + i);
		}

		System.out.println("");
		for (int x = 0; x < Board.DEFAULT_HEIGHT; x++) {
			System.out.print((char) (65 + x) + " ");
			for (int y = 0; y < Board.DEFAULT_WIDTH; y++) {
				System.out.print(board1.pieces[x][y].getPresent());
			}
			System.out.print("     ");
			System.out.print((char) (65 + x) + " ");
			for (int z = 0; z < Board.DEFAULT_WIDTH; ++z) {
				System.out.print(board2.pieces[x][z].getPresent());
			}
			System.out.println();
		}
	}
	public static void printBoard(Board board1, Board board2, boolean yourturn) {
		for (int i = 0; i < Board.DEFAULT_WIDTH; ++i) {
			System.out.print("  " + i);
		}
		System.out.print("       ");
		for (int i = 0; i < Board.DEFAULT_WIDTH; ++i) {
			System.out.print("  " + i);
		}

		System.out.println("");
		for (int x = 0; x < Board.DEFAULT_HEIGHT; x++) {
			System.out.print((char) (65 + x) + " ");
			for (int y = 0; y < Board.DEFAULT_WIDTH; y++) {
				if (yourturn) {
					System.out.print(board1.pieces[x][y].getPresent());
				} else {
					System.out.print(board1.pieces[x][y].getPresent().replace('#','_'));
				}
			}
			System.out.print("     ");
			System.out.print((char) (65 + x) + " ");
			for (int z = 0; z < Board.DEFAULT_WIDTH; ++z) {
				if (yourturn) {
					System.out.print(board2.pieces[x][z].getPresent().replace('#','_'));
				} else {
					System.out.print(board2.pieces[x][z].getPresent());
				}
			}
			System.out.println();
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < Board.DEFAULT_WIDTH; ++i) {
			sb.append("  " + i);
		}

		sb.append('\n');
		for (int x = 0; x < Board.DEFAULT_HEIGHT; x++) {
			sb.append((char) (65 + x) + " ");
			for (int y = 0; y < Board.DEFAULT_WIDTH; y++) {
				sb.append(this.pieces[x][y].getPresent());
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	public String checkMove(String move){

		try {
			if(((int)move.charAt(0)-65) >= 10)
				return "Invalid Row. Can only be A-J. Enter another move: ";	
			if(((int)move.charAt(1)-48) >= 10)
				return "Invalid Column. Can only be 0-9. Enter another move: ";
			if(this.pieces[((int)move.charAt(0)-65)][((int)move.charAt(1)-49 + 1)].selected)  
				return "Already chosen. Enter another move: ";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Invalid!";
		} catch (StringIndexOutOfBoundsException e) {
			return "Invalid character!";
		}
		
		return "ok";
	}
}
