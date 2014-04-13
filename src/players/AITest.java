package players;

import main.Board;
import main.BoardPieceState;
import main.Player;


public class AITest {

	private static Player ai = new RandomAI(null, new Board(), "RandomAI");
	private static Player enemy = new RandomAI(null, new Board(), "Random Guy");
	public static void main(String args[]) {
		
		ai.placeShips();
		enemy.placeShips();
		int numberOfTestCases = 100;
		int score = 0;
		for (int i = 0; i < numberOfTestCases; ++i) {
			int count = 0;
			enemy = new RandomAI(null, new Board(), "Random Guy");
			enemy.placeShips();
			while (!checkGameOver()) {
				count++;
				System.out.println("count = " + count);
				ai.shoot(enemy.board);
			}
			score += count;
		}
		System.out.println("RandomAI score: " + (1.0*score / numberOfTestCases));
	}
	
	private static boolean checkGameOver() {
		// Check if player win
		int enemyDeaths = 0;
		for (int i = 0; i < Board.DEFAULT_HEIGHT; ++i) {
			for (int j = 0; j < Board.DEFAULT_WIDTH; ++j) {
				enemyDeaths += enemy.board.pieces[i][j].state == BoardPieceState.STATE_HIT ? 1 : 0;
			}
		}
		
		if (enemyDeaths == 17) {
			System.out.println(ai.name + " win!");
			return true;
		}
		return false;
	}
}
