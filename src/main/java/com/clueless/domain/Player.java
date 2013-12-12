package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Player {
	
	private String suspect;
	private ArrayList<String> cardsInHand;
	private boolean failedAccusation;
	private boolean canSuggest;
	private String location;
	private ArrayList<String> movableLocations;
	private int turnPosition;
	
	public Player(String suspect) {
		this.setSuspect(suspect);
		cardsInHand = new ArrayList<String>();
		setFailedAccusation(false);
		setCanSuggest(false);
		movableLocations = new ArrayList<String>(4); // at most 4 movable locations (ex. the Billard Room)
	}
	
	public Player(String suspect, String location) {
		this.setSuspect(suspect);
		this.location = location;
	}
	
	public String getSuspect() {
		return suspect;
	}

	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}	

	public ArrayList<String> getCardsInHand() {
		return cardsInHand;
	}

	public void setCardsInHand(ArrayList<String> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}

	public void addCard(String card) {
		cardsInHand.add(card);
	}
	
	public void removeCard(Card card) {
		cardsInHand.remove(card);
	}
	
	public boolean isFailedAccusation() {
		return failedAccusation;
	}

	public void setFailedAccusation(boolean failedAccusation) {
		this.failedAccusation = failedAccusation;
	}

	public boolean isCanSuggest() {
		return canSuggest;
	}

	public void setCanSuggest(boolean canSuggest) {
		this.canSuggest = canSuggest;
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
		movableLocations.add(location);
	}
	
	public void clearMovableLocations() {
		movableLocations.clear();
	}
	
	@JsonIgnore
	public int getTurnPosition() {
		return turnPosition;
	}

	public void setTurnPosition(int turnPosition) {
		this.turnPosition = turnPosition;
	}

	@Override
	public String toString() {
		return suspect;
	}
}
