package com.clueless.model;

import java.util.HashMap;

/**
 * The ClueLessModel is the repository for the game state data. The ClueLessModel is returned back to
 * the client as a JSON body which contains all the information needed for the client to update its state.
 * @author nshih
 */
public class ClueLessModel {

	private static final ClueLessModel instance = new ClueLessModel();
	
	private HashMap<String, String> players;
	private boolean gameReady;

	private ClueLessModel() {
		players = new HashMap<String, String>();
		gameReady = false;
	}
	
	/**
	 * Returns the ClueLessModel game state as a singleton.
	 * @return ClueLessModel
	 */
	public static ClueLessModel getInstance() {
		return instance;
	}
	
	public HashMap<String, String> getPlayers() {
		return this.players;
	}

	public void setActivePlayers(HashMap<String, String> players) {
		this.players = players;
	}
	
	public void addPlayer(String sessionId, String player) {
		this.players.put(sessionId, player);
	}
	
	public void removePlayer(String sessionId) {
		this.players.remove(sessionId);
	}

	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
	}
}
