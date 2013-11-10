package com.clueless.domain;

import java.util.ArrayList;

public class Room {

	private ArrayList<String> currentPlayers;
	private ArrayList<String> currentWeapons;
	private ArrayList<String> adjacentTo;
	
	public Room() {
		currentPlayers = new ArrayList<String>(6); // at most 6 players
		currentWeapons = new ArrayList<String>(6); // only 6 weapons
		adjacentTo = new ArrayList<String>(4);
	}
	
	/**
	 * Set adjacent areas for new room object with 3 adjacent areas.
	 * @param adjacentTo1
	 * @param adjacentTo2
	 * @param adjacentTo3
	 */
	public Room(String adjacentTo1, String adjacentTo2, String adjacentTo3) {
		currentPlayers = new ArrayList<String>(6);
		currentWeapons = new ArrayList<String>(6);
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
		currentPlayers = new ArrayList<String>(6);
		currentWeapons = new ArrayList<String>(6);
		adjacentTo = new ArrayList<String>(3);
		
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
		int i = 0;
		for (String currentPlayer : this.currentPlayers) {
			if (currentPlayer.equals(player)) {
				this.currentPlayers.remove(i);
			}
			i++;
		}
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
		int i = 0;
		for (String currentWeapon : this.currentWeapons) {
			if (currentWeapon.equals(weapon)) {
				this.currentWeapons.remove(i);
			}
			i++;
		}
	}

	public ArrayList<String> getAdjacentTo() {
		return adjacentTo;
	}

	public void setAdjacentTo(ArrayList<String> adjacentTo) {
		this.adjacentTo = adjacentTo;
	}
}
