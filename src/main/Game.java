package main;

import players.Human;

public class Game {
	Player player; // It's you
	Player enemy; // Your friend, or an AI

	boolean gameover = false;

	boolean yourturn = true; // You always go first.

	public Game() {
		init();
	}

	public void init() {
		player = new Human(new Board(), "Player 1");
		enemy = new Human(new Board(), "Player 2");
	}

	public void play() {
		System.out.println("                " + this.player.name
				+ "                                       " + this.enemy.name);

		Board.printBoard(player.board, enemy.board);
		player.placeShips();
		enemy.placeShips();


		System.out.println("===================================================");
		System.out.println("=                                                 =");
		System.out.println("=        *** *** *** GAME START *** *** ***       =");
		System.out.println("=                                                 =");
		System.out.println("===================================================");
		
		while (!gameover) {
			move();
		}
	}

	public void move() {
		if (yourturn) {
			player.shoot(enemy.board);
		} else {
			enemy.shoot(player.board);
		}
		yourturn = !yourturn;
		gameover = checkGameOver();
	}

	private boolean checkGameOver() {
		
		// Check if enemy win
		int playerDeaths = 0;
		for (int i = 0; i < Board.DEFAULT_HEIGHT; ++i) {
			for (int j = 0; j < Board.DEFAULT_WIDTH; ++j) {
				playerDeaths += player.board.pieces[i][j].state == BoardPieceState.STATE_HIT ? 1 : 0;
			}
		}
		
		if (playerDeaths == 17) {
			System.out.println(enemy.name + " win!");
			return true;
		}
		
		// Check if player win
		int enemyDeaths = 0;
		for (int i = 0; i < Board.DEFAULT_HEIGHT; ++i) {
			for (int j = 0; j < Board.DEFAULT_WIDTH; ++j) {
				enemyDeaths += enemy.board.pieces[i][j].state == BoardPieceState.STATE_HIT ? 1 : 0;
			}
		}
		
		if (enemyDeaths == 17) {
			System.out.println(player.name + " win!");
			return true;
		}
		return false;
	}


}