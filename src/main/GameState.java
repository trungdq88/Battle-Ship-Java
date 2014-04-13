package main;

import java.io.Serializable;

public class GameState implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8951504952544166075L;
	boolean yourturn;
	boolean playwithhuman;
	Player player;
	Player enemy;
}
