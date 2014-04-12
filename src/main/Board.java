package main;

public class Board {
	
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	
	
	BoardPiece[][] board = new BoardPiece[DEFAULT_WIDTH][DEFAULT_HEIGHT];
	
	public Board() {
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board.length; ++j) {
				board[i][j] = new BoardPiece();
			}
		}
	}

	public void place() {
		// TODO Auto-generated method stub
		
	}
}
