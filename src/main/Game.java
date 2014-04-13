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
			enemy.board = player.shoot(enemy.board);
		} else {
			player.board = enemy.shoot(player.board);
		}
		yourturn = !yourturn;
		gameover = checkGameOver();
	}

	private boolean checkGameOver() {
		if (player.ships.size() == 0) {
			System.out.println(enemy.name + " win!");
			return true;
		}
		if (enemy.ships.size() == 0) {
			System.out.println(player.name + " win!");
			return true;
		}

		return false;
	}


}