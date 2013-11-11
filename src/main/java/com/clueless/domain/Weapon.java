package com.clueless.domain;

import java.util.ArrayList;

public class Weapon {

	public static final int TOTAL = 6; // 6 weapons
	
	public static final String ROPE = "rope";
	public static final String LEAD_PIPE = "leadPipe";
	public static final String KNIFE = "knife";
	public static final String WRENCH = "wrench";
	public static final String CANDLESTICK = "candlestick";
	public static final String REVOLVER = "revolver";
	
	private ArrayList<String> weaponList;
	
	public Weapon() {
		weaponList = new ArrayList<String>(Weapon.TOTAL); // at most 6 weapons
		weaponList.add(ROPE);
		weaponList.add(LEAD_PIPE);
		weaponList.add(KNIFE);
		weaponList.add(WRENCH);
		weaponList.add(CANDLESTICK);
		weaponList.add(REVOLVER);
	}

	public ArrayList<String> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(ArrayList<String> weaponList) {
		this.weaponList = weaponList;
	}
}
