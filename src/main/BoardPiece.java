package main;

public class BoardPiece {
	public BoardPieceState state;
	String present;
	int type;
	public boolean used;
	public boolean selected;

	public BoardPiece() {
		state = BoardPieceState.STATE_FREE;
	}
	
	public String getPresent() {
		switch (state) {
		case STATE_FREE:
			return "__|";
		case STATE_BUSY:
			return "_#|";
		case STATE_HIT:
			return "_X|";
		case STATE_MISS:
			return "_o|";
		default:
			return "???";
		}
	}
}


