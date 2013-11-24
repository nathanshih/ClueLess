package com.clueless.domain;

public class WeaponToken extends Token {

	public static final int TOTAL = 6; // 6 weapon tokens
	
	public static final String ROPE = "rope";
	public static final String LEAD_PIPE = "leadPipe";
	public static final String KNIFE = "knife";
	public static final String WRENCH = "wrench";
	public static final String CANDLESTICK = "candlestick";
	public static final String REVOLVER = "revolver";
	
	public WeaponToken() {
		this.type = Token.WEAPON;
	}
	
	public WeaponToken(String tokenName) {
		this.type = Token.WEAPON;
		this.tokenName = tokenName;
	}
}
