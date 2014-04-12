package main;

public class BoardPiece {
	BoardPieceState state;
	String present;

	public BoardPiece() {
		state = BoardPieceState.STATE_FREE;
	}
	
	public String getPresent() {
		switch (state) {
		case STATE_FREE:
			return "___|";
		case STATE_BUSY:
			return "_#_|";
		case STATE_HIT:
			return "_X_|";
		case STATE_MISS:
			return "_o_|";
		default:
			return "????";
		}
	}
}


