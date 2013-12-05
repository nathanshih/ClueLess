package com.clueless.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Card {
	
	public static final int NUM_SUSPECTS = 6;
	public static final int NUM_ROOMS = 9;
	public static final int NUM_WEAPONS = 6;
	public static final int TOTAL = NUM_ROOMS+NUM_SUSPECTS+NUM_WEAPONS;
	
	public static final int TYPE_SUSPECT = 0;
	public static final int TYPE_WEAPON = 1;
	public static final int TYPE_ROOM = 2;
	
	public static final int ROOM_HALL = 0;
	public static final int ROOM_LOUNGE = 1;
	public static final int ROOM_DINING = 2;
	public static final int ROOM_KITCHEN = 3;
	public static final int ROOM_BALLROOM = 4;
	public static final int ROOM_CONSERVATORY = 5;
	public static final int ROOM_BILLIARD = 6;
	public static final int ROOM_STUDY = 7;
	public static final int ROOM_LIBRARY = 8;
	
	public static final int SUSPECT_SCARLET = 0;
	public static final int SUSPECT_WHITE = 1;
	public static final int SUSPECT_PLUM = 2;
	public static final int SUSPECT_MUSTARD = 3;
	public static final int SUSPECT_GREEN = 4;
	public static final int SUSPECT_PEACOCK = 5;
	
	public static final int WEAPON_KNIFE = 0;
	public static final int WEAPON_ROPE = 1;
	public static final int WEAPON_REVOLVER = 2;
	public static final int WEAPON_WRENCH = 3;
	public static final int WEAPON_PIPE = 4;
	public static final int WEAPON_CANDLE = 5;
	
	
	private int type;
	private int value;
	private String desc;

	public Card() {
	
	}
	
	public Card(int type, int value) {
		this.type = type;
		this.value = value;
		this.setDesc(toString());
	}

	@JsonIgnore
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@JsonIgnore
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		String desc = null;
		
        switch (type) {
        case TYPE_SUSPECT: 
            switch (value) {
            case SUSPECT_SCARLET: desc = SuspectToken.MISS_SCARLET; break;
            case SUSPECT_PLUM: desc = SuspectToken.PROFESSOR_PLUM; break;
            case SUSPECT_WHITE: desc = SuspectToken.MRS_WHITE; break;
            case SUSPECT_MUSTARD: desc = SuspectToken.COLONEL_MUSTARD; break;
            case SUSPECT_GREEN: desc = SuspectToken.MR_GREEN; break;
            case SUSPECT_PEACOCK: desc = SuspectToken.MRS_PEACOCK; break;
            }
            break;
        case TYPE_ROOM:  
            switch (value) {
            case ROOM_HALL: desc = Room.HALL; break;
            case ROOM_LOUNGE: desc = Room.LOUNGE; break;
            case ROOM_DINING: desc = Room.DINING_ROOM; break;
            case ROOM_KITCHEN: desc = Room.KITCHEN; break;
            case ROOM_BALLROOM: desc = Room.BALLROOM; break;
            case ROOM_CONSERVATORY: desc = Room.CONSERVATORY; break;
            case ROOM_BILLIARD: desc = Room.BILLIARD_ROOM; break;
            case ROOM_STUDY: desc = Room.STUDY; break;
            case ROOM_LIBRARY: desc = Room.LIBRARY; break;
            }
            break;
        case TYPE_WEAPON:  
            switch (value) {
            case WEAPON_KNIFE: desc = WeaponToken.KNIFE; break;
            case WEAPON_ROPE: desc = WeaponToken.ROPE; break;
            case WEAPON_REVOLVER: desc = WeaponToken.REVOLVER; break;
            case WEAPON_WRENCH: desc = WeaponToken.WRENCH; break;
            case WEAPON_PIPE: desc = WeaponToken.LEAD_PIPE; break;
            case WEAPON_CANDLE: desc = WeaponToken.CANDLESTICK; break;
            }
            break;
        }
        
        return desc;
        
	}
}
	
