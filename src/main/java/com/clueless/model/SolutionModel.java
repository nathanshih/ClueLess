package com.clueless.model;

/**
 * The SolutionModel holds the solution for this game as well as if it has been solved by
 * any active player.
 * @author nshih
 */
public class SolutionModel {

	private static final SolutionModel instance = new SolutionModel();
	
	private String suspect;
	private String weapon;
	private String room;
	private String solvedBy;

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

	public String getSolvedBy() {
		return solvedBy;
	}

	public void setSolvedBy(String solvedBy) {
		this.solvedBy = solvedBy;
	}	
}
