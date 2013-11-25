package com.clueless.model;

import java.util.HashMap;

import com.clueless.domain.Hallway;
import com.clueless.domain.Player;
import com.clueless.domain.Room;
import com.clueless.domain.SuspectToken;

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
	private HashMap<String, Room> rooms;
	private HashMap<String, Hallway> hallways;
	
	private ClueLessModel() {
		gameReady = false;
		players = new HashMap<String, Player>(SuspectToken.TOTAL);
		rooms = new HashMap<String, Room>(Room.TOTAL);
		hallways = new HashMap<String, Hallway>(Hallway.TOTAL);
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
	
	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}
	
	public HashMap<String, Hallway> getHallways() {
		return hallways;
	}

	public void setHallways(HashMap<String, Hallway> hallways) {
		this.hallways = hallways;
	}
}
