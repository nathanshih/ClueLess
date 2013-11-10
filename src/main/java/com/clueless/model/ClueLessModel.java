package com.clueless.model;

import java.util.HashMap;

import com.clueless.domain.Hallway;
import com.clueless.domain.Player;
import com.clueless.domain.Room;

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
	//TODO: solution *hidden*
	
	private ClueLessModel() {
		gameReady = false;
		players = new HashMap<String, Player>();
		rooms = new HashMap<String, Room>();
		hallways = new HashMap<String, Hallway>();

		// initialize hallways
		hallways.put("hallway1", new Hallway("study", "hall"));
		hallways.put("hallway2", new Hallway("hall", "lounge"));
		hallways.put("hallway3", new Hallway("study", "library"));
		hallways.put("hallway4", new Hallway("hall", "billiardRoom"));
		hallways.put("hallway5", new Hallway("lounge", "diningRoom"));
		hallways.put("hallway6", new Hallway("library", "billiardRoom"));
		hallways.put("hallway7", new Hallway("billiardRoom", "diningRoom"));
		hallways.put("hallway8", new Hallway("library", "conservatory"));
		hallways.put("hallway9", new Hallway("billiardRoom", "ballroom"));
		hallways.put("hallway10", new Hallway("diningRoom", "kitchen"));
		hallways.put("hallway11", new Hallway("conservatory", "ballroom"));
		hallways.put("hallway12", new Hallway("ballroom", "kitchen"));
		
		// initialize rooms
		rooms.put("study", new Room("hallway1", "hallway3", "kitchen"));
		rooms.put("hall", new Room("hallway1", "hallway2", "hallway4"));
		rooms.put("lounge", new Room("hallway2", "conservatory", "hallway5"));
		rooms.put("library", new Room("hallway3", "hallway6", "hallway8"));
		rooms.put("billardRoom", new Room("hallway4", "hallway6", "hallway7", "hallway9"));
		rooms.put("diningRoom", new Room("hallway5", "hallway7", "hallway10"));
		rooms.put("conservatory", new Room("hallway8", "lounge", "hallway11"));
		rooms.put("ballroom", new Room("hallway11", "hallway9", "hallway12"));
		rooms.put("kitchen", new Room("hallway12", "study", "hallway10"));
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
