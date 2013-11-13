package com.clueless.domain;

import java.util.ArrayList;

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
	
	private ArrayList<String> currentPlayers;
	private ArrayList<String> currentWeapons;
	private ArrayList<String> adjacentTo;
	
	public Room() {
		currentPlayers = new ArrayList<String>(Player.TOTAL); // at most 6 players
		currentWeapons = new ArrayList<String>(Weapon.TOTAL); // only 6 weapons
		adjacentTo = new ArrayList<String>(4); // at most 4 areas
	}
	
	/**
	 * Set adjacent areas for new room object with 3 adjacent areas.
	 * @param adjacentTo1
	 * @param adjacentTo2
	 * @param adjacentTo3
	 */
	public Room(String adjacentTo1, String adjacentTo2, String adjacentTo3) {
		currentPlayers = new ArrayList<String>(Player.TOTAL);
		currentWeapons = new ArrayList<String>(Weapon.TOTAL);
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
		currentPlayers = new ArrayList<String>(Player.TOTAL);
		currentWeapons = new ArrayList<String>(Weapon.TOTAL);
		adjacentTo = new ArrayList<String>(4);
		
		adjacentTo.add(adjacentTo1);
		adjacentTo.add(adjacentTo2);
		adjacentTo.add(adjacentTo3);
		adjacentTo.add(adjacentTo4);
	}

	public ArrayList<String> getCurrentPlayers() {
		return currentPlayers;
	}

	public void setCurrentPlayers(ArrayList<String> currentPlayers) {
		this.currentPlayers = currentPlayers;
	}

	public void addPlayer(String player) {
		this.currentPlayers.add(player);
	}
	
	public void removePlayer(String player) {
		this.currentPlayers.remove(player);
	}
	
	public ArrayList<String> getCurrentWeapons() {
		return currentWeapons;
	}

	public void setCurrentWeapons(ArrayList<String> currentWeapons) {
		this.currentWeapons = currentWeapons;
	}
	
	public void addWeapon(String weapon) {
		this.currentWeapons.add(weapon);
	}
	
	public void removeWeapon(String weapon) {
		this.currentPlayers.remove(weapon);
	}

	public ArrayList<String> getAdjacentTo() {
		return adjacentTo;
	}

	public void setAdjacentTo(ArrayList<String> adjacentTo) {
		this.adjacentTo = adjacentTo;
	}
}
