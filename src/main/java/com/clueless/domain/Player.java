package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Player {
	
	public static final int TOTAL = 6; // 6 players
	
	public static final String MISS_SCARLET = "Miss Scarlet";
	public static final String COLONEL_MUSTARD = "Colonel Mustard";
	public static final String PROFESSOR_PLUM = "Professor Plum";
	public static final String MR_GREEN = "Mr. Green";
	public static final String MRS_WHITE = "Mrs. White";
	public static final String MRS_PEACOCK = "Mrs. Peacock";
	
	private String name;
	private ArrayList<Card> cardsInHand;
	private boolean canDisprove;
	private boolean failedAccusation;
	private String location;
	private ArrayList<String> movableLocations;
	
	public Player(String name) {
		this.setName(name);
		cardsInHand = new ArrayList<Card>();
		canDisprove = false;
		setFailedAccusation(false);
		movableLocations = new ArrayList<String>(4); // at most 4 movable locations (ex. the Billard Room)
	}
	
	public Player(String name, String location) {
		this.setName(name);
		this.location = location;
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

	public void addCard(Card card) {
		cardsInHand.add(card);
	}
	
	public void removeCard(Card card) {
		cardsInHand.remove(card);
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<String> getMovableLocations() {
		return movableLocations;
	}

	public void setMovableLocations(ArrayList<String> movableLocations) {
		this.movableLocations = movableLocations;
	}
	
	public void addMovableLocation(String location) {
		this.movableLocations.add(location);
	}
	
	public void clearMovableLocations() {
		this.movableLocations.clear();
	}
}
