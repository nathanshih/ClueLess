package com.clueless.model;

import java.util.HashMap;

public class ClueLessModel {

	private static final ClueLessModel instance = new ClueLessModel();
	
	private HashMap<String, String> players = new HashMap<String, String>();
	private boolean gameReady = false;

	private ClueLessModel() {}
	
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
