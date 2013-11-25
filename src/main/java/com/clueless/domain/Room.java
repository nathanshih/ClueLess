package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Room {

	public static final int TOTAL = 9; // 9 rooms
	
	public static final String STUDY = "study";
	public static final String HALL = "hall";
	public static final String LOUNGE = "lounge";
	public static final String LIBRARY = "library";
	public static final String BILLIARD_ROOM = "billardRoom";
	public static final String DINING_ROOM = "diningRoom";
	public static final String CONSERVATORY = "conservatory";
	public static final String BALLROOM = "ballroom";
	public static final String KITCHEN = "kitchen";
	
	// private ArrayList<String> currentPlayers;
	private ArrayList<String> tokens;
	private ArrayList<String> adjacentTo;
	
	public Room() {
		//currentPlayers = new ArrayList<String>(Player.TOTAL); // at most 6 players
		tokens = new ArrayList<String>(Token.TOTAL);
		adjacentTo = new ArrayList<String>(4); // at most 4 areas
	}
	
	/**
	 * Set adjacent areas for new room object with 3 adjacent areas.
	 * @param adjacentTo1
	 * @param adjacentTo2
	 * @param adjacentTo3
	 */
	public Room(String adjacentTo1, String adjacentTo2, String adjacentTo3) {
		//currentPlayers = new ArrayList<String>(Player.TOTAL);
		tokens = new ArrayList<String>(Token.TOTAL);
		adjacentTo = new ArrayList<String>(3);
		
		adjacentTo.add(adjacentTo1);
		adjacentTo.add(adjacentTo2);
		adjacentTo.add(adjacentTo3);
	}
	
	/**
	 * Set adjacent areas for new room object with 4 adjacent areas.
	 * @param adjacentTo1
	 * @param adjacentTo2
	 * @param adjacentTo3
	 * @param adjacentTo4
	 */
	public Room(String adjacentTo1, String adjacentTo2, String adjacentTo3, String adjacentTo4) {
		//currentPlayers = new ArrayList<String>(Player.TOTAL);
		tokens = new ArrayList<String>(Token.TOTAL);
		adjacentTo = new ArrayList<String>(4);
		
		adjacentTo.add(adjacentTo1);
		adjacentTo.add(adjacentTo2);
		adjacentTo.add(adjacentTo3);
		adjacentTo.add(adjacentTo4);
	}

	public ArrayList<String> getTokens() {
		return tokens;
	}

	public void setTokens(ArrayList<String> tokens) {
		this.tokens = tokens;
	}
	
	public void addToken(String token) {
		this.tokens.add(token);
	}
	
	public void removeToken(String token) {
		this.tokens.remove(token);
	}

	@JsonIgnore
	public ArrayList<String> getAdjacentTo() {
		return adjacentTo;
	}

	public void setAdjacentTo(ArrayList<String> adjacentTo) {
		this.adjacentTo = adjacentTo;
	}
}
