package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import players.Human;
import players.RandomAI;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1857097362692281859L;
	public Player player; // It's you
	public Player enemy; // Your friend, or an AI

	boolean gameover = false;

	public boolean yourturn = true; // You always go first.

	public Game(boolean playwithhuman) {
		if (playwithhuman) {
			player = new Human(this, new Board(), "Player 1");
			enemy = new Human(this, new Board(), "Player 2");
		} else {
			player = new Human(this, new Board(), "Player 1");
			enemy = new RandomAI(this, new Board(), "Computer");
		}
		
	}


	public void play() {
		System.out.println("                " + this.player.name
				+ "                         " + this.enemy.name);

		Board.printBoard(player.board, enemy.board);
		player.placeShips();
		enemy.placeShips();

		clearScreen();
		System.out.println("===================================================");
		System.out.println("=                                                 =");
		System.out.println("=        *** *** *** GAME START *** *** ***       =");
		System.out.println("=                                                 =");
		System.out.println("===================================================");
		
		startTheLoop();
	}

	public void startTheLoop() {
		while (!gameover) {
			move();
		}
	}
	
	public void move() {
		if (yourturn) {
			player.game.yourturn = yourturn; // This is important!
			player.shoot(enemy.board);
			yourturn = player.game.yourturn;
		} else {
			enemy.game.yourturn = yourturn; // This is important!
			enemy.shoot(player.board);
			yourturn = enemy.game.yourturn;
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

	private void clearScreen() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public void saveGame() {
		// yourturn
		// player
		// enemy
		GameState gameState = new GameState();
		gameState.yourturn = yourturn;
		gameState.player = player;
		gameState.enemy = enemy;
		
		try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream("BattleShip.txt")))
	    {
	        write.writeObject((Serializable)gameState);
	        System.out.println("Game saved to file: BattleShip.txt!");
	    }
//	    catch(NotSerializableException nse)
//	    {
//	        System.out.println("Something wrong! " + nse.getMessage());
//	    }
	    catch(IOException eio)
	    {
	        System.out.println("Could not create file! Please check the read/write permission.");
	        eio.printStackTrace();
	    }
	}


	public void loadGame(String path) {
		GameState data = null;

	    try(ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path)))
	    {
	        data = (GameState)inFile.readObject();
	        
	        if (data != null) {
	        	yourturn = data.yourturn;
	        	player = data.player;
	        	enemy = data.enemy;
	        	
	        	
	    		System.out.println("===================================================");
	    		System.out.println("=                                                 =");
	    		System.out.println("=     *** *** *** GAME RE-STARTED *** *** ***     =");
	    		System.out.println("=                                                 =");
	    		System.out.println("===================================================");
	    		startTheLoop();
	        } else {
		        System.out.println("Cannot load game from this file: file data wrong!");
	        }
	        
	    }
	    catch(ClassNotFoundException cnfe)
	    {
	        System.out.println("Cannot load game from this file: file data wrong!");
	    }
	    catch(FileNotFoundException fnfe)
	    {
	        System.out.println("File not found: " + path);
	    }
	    catch(IOException e)
	    {
	        System.out.println("Cannot load game from this file: read failed!");
	    }

	}
}