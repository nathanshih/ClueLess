package com.clueless.domain;

public class SuspectToken extends Token {
	
	public static final int TOTAL = 6; // 6 suspect tokens
	
	public static final String MISS_SCARLET = "Miss Scarlet";
	public static final String COLONEL_MUSTARD = "Colonel Mustard";
	public static final String PROFESSOR_PLUM = "Professor Plum";
	public static final String MR_GREEN = "Mr. Green";
	public static final String MRS_WHITE = "Mrs. White";
	public static final String MRS_PEACOCK = "Mrs. Peacock";
	
	private String playedBy;
	
	public SuspectToken() {
		this.type = Token.SUSPECT;
	}
	
	public SuspectToken(String tokenName) {
		this.type = Token.SUSPECT;
		this.tokenName = tokenName;
	}

	public String getPlayedBy() {
		return playedBy;
	}

	public void setPlayedBy(String playedBy) {
		this.playedBy = playedBy;
	}
}
