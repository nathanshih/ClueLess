package com.clueless.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Player {
	
	private String name;
	private ArrayList<Card> cardsInHand;
	private boolean canDisprove;
	private boolean failedAccusation;
	
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
}
