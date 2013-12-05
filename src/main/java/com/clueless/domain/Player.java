package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Player {
	
	private String suspect;
	private ArrayList<String> cardsInHand;
	private boolean canDisprove;
	private boolean failedAccusation;
	private String location;
	private ArrayList<String> movableLocations;
	private int turnPosition;
	
	public Player(String suspect) {
		this.setSuspect(suspect);
		cardsInHand = new ArrayList<String>();
		canDisprove = false;
		setFailedAccusation(false);
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

	public void addCard(String card) {
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
