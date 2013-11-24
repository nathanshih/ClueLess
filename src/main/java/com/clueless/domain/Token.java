package com.clueless.domain;

public abstract class Token {
	
	public static final int TOTAL = WeaponToken.TOTAL + SuspectToken.TOTAL; // 12 tokens
	
	public static final String SUSPECT = "suspect";
	public static final String WEAPON = "weapon";
	
	protected String type;
	protected String tokenName;
	protected String location;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return tokenName;
	}
}
