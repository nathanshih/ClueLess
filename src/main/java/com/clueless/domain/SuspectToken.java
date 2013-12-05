package com.clueless.domain;

public class SuspectToken extends Token {
	
	public static final int TOTAL = 6; // 6 suspect tokens
	
	public static final String MISS_SCARLET = "Miss Scarlet";
	public static final String COLONEL_MUSTARD = "Colonel Mustard";
	public static final String MRS_WHITE = "Mrs. White";
	public static final String MR_GREEN = "Mr. Green";
	public static final String MRS_PEACOCK = "Mrs. Peacock";
	public static final String PROFESSOR_PLUM = "Professor Plum";
	
	public static final int MISS_SCARLET_TURN_POSITION = 0;
	public static final int COLONEL_MUSTARD_TURN_POSITION = 1;
	public static final int MRS_WHITE_TURN_POSITION = 2;
	public static final int MR_GREEN_TURN_POSITION = 3;
	public static final int MRS_PEACOCK_TURN_POSITION = 4;
	public static final int PROFESSOR_PLUM_TURN_POSITION = 5;
	
	public static final String MISS_SCARLET_START_LOCATION = Hallway.HALLWAY2;
	public static final String COLONEL_MUSTARD_START_LOCATION = Hallway.HALLWAY5;
	public static final String MRS_WHITE_START_LOCATION = Hallway.HALLWAY12;
	public static final String MR_GREEN_START_LOCATION = Hallway.HALLWAY11;
	public static final String MRS_PEACOCK_START_LOCATION = Hallway.HALLWAY8;
	public static final String PROFESSOR_PLUM_START_LOCATION = Hallway.HALLWAY3;
	
	private String playedBy;
	
	public SuspectToken() {
		type = Token.SUSPECT;
	}
	
	public SuspectToken(String tokenName) {
		type = Token.SUSPECT;
		this.tokenName = tokenName;
	}

	public String getPlayedBy() {
		return playedBy;
	}

	public void setPlayedBy(String playedBy) {
		this.playedBy = playedBy;
	}
}
