package com.clueless.model;

/**
 * The SolutionModel holds the solution for the current game.
 * @author nshih
 */
public class SolutionModel {

	private static final SolutionModel instance = new SolutionModel();
	
	private String suspect;
	private String weapon;
	private String room;

	private SolutionModel() {
		
	}
	
	/**
	 * Returns the SolutionModel as a singleton.
	 * @return
	 */
	public static SolutionModel getInstance() {
		return instance;
	}

	public String getSuspect() {
		return suspect;
	}

	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}
