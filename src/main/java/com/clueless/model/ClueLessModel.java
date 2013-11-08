package com.clueless.model;

import java.util.HashMap;

/**
 * The ClueLessModel is the repository for the game state data. The ClueLessModel is returned back to
 * the client as a JSON body which contains all the information needed for the client to update its state.
 * @author nshih
 */
public class ClueLessModel {

	private static final ClueLessModel instance = new ClueLessModel();
	
	private boolean gameReady;
	private String whoseTurn;
	private HashMap<String, Player> players;
	
	private ClueLessModel() {
		gameReady = false;
		players = new HashMap<String, Player>();
	}
	
	/**
	 * Returns the ClueLessModel game state as a singleton.
	 * @return ClueLessModel
	 */
	public static ClueLessModel getInstance() {
		return instance;
	}
	
	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
	}

	public String getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(String whoseTurn) {
		this.whoseTurn = whoseTurn;
	}
	
	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers(HashMap<String, Player> playerss) {
		this.players = playerss;
	}
}
