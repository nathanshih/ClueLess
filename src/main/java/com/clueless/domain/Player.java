package com.clueless.domain;

import java.util.ArrayList;

public class Player {
	
	private String suspect;
	private ArrayList<Card> cardsInHand;
	private boolean canDisprove;
	private boolean failedAccusation;
	private String location;
	private ArrayList<String> movableLocations;
	
	public Player(String suspect) {
		this.setSuspect(suspect);
		cardsInHand = new ArrayList<Card>();
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
	
	@Override
	public String toString() {
		return suspect;
	}
}
