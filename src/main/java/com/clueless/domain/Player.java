package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Player {
	
	public static final int TOTAL = 6; // 6 players
	
	public static final String MISS_SCARLET = "missScarlet";
	public static final String COLONEL_MUSTARD = "colonelMustard";
	public static final String PROFESSOR_PLUM = "professorPlum";
	public static final String MR_GREEN = "mrGreen";
	public static final String MRS_WHITE = "mrsWhite";
	public static final String MRS_PEACOCK = "mrsPeacock";
	
	private String name;
	private ArrayList<Card> cardsInHand;
	private boolean canDisprove;
	private boolean failedAccusation;
	private String location;
	
	public Player(String name) {
		this.setName(name);
		cardsInHand = new ArrayList<Card>();
		canDisprove = false;
		setFailedAccusation(false);
	}
	
	public Player(String name, ArrayList<Card> cards) {
		this.setName(name);
		this.cardsInHand = cards;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	@JsonIgnore
	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}

	public void setCardsInHand(ArrayList<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}

	public boolean isCanDisprove() {
		return canDisprove;
	}

	public void setCanDisprove(boolean canDisprove) {
		this.canDisprove = canDisprove;
	}

	public boolean isFailedAccusation() {
		return failedAccusation;
	}

	public void setFailedAccusation(boolean failedAccusation) {
		this.failedAccusation = failedAccusation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
