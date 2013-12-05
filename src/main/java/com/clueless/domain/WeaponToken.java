package com.clueless.domain;

public class WeaponToken extends Token {

	public static final int TOTAL = 6; // 6 weapon tokens
	
	public static final String ROPE = "Rope";
	public static final String LEAD_PIPE = "LeadPipe";
	public static final String KNIFE = "Knife";
	public static final String WRENCH = "Wrench";
	public static final String CANDLESTICK = "Candlestick";
	public static final String REVOLVER = "Revolver";
	
	public WeaponToken() {
		type = Token.WEAPON;
	}
	
	public WeaponToken(String tokenName) {
		type = Token.WEAPON;
		this.tokenName = tokenName;
	}
}
