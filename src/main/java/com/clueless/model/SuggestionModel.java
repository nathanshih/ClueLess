package com.clueless.model;

import java.util.ArrayList;

/**
 * The SuggestionModel holds information on which card is being used to disprove the suggestion
 * and cards which can be used to disprove the suggestion.
 * @author nshih
 */
public class SuggestionModel {

	private boolean canBeDisproven;
	private String disprovingCard;
	private ArrayList<String> disprovableCards;
	
	public SuggestionModel() {
		disprovableCards = new ArrayList<String>();
		setCanBeDisproven(false);
	}

	public boolean isCanBeDisproven() {
		return canBeDisproven;
	}

	public void setCanBeDisproven(boolean canBeDisproven) {
		this.canBeDisproven = canBeDisproven;
	}

	public String getDisprovingCard() {
		return disprovingCard;
	}

	public void setDisprovingCard(String disprovingCard) {
		this.disprovingCard = disprovingCard;
	}

	public ArrayList<String> getDisprovableCards() {
		return disprovableCards;
	}

	public void setDisprovableCards(ArrayList<String> disprovableCards) {
		this.disprovableCards = disprovableCards;
	}
	
	public void addDisprovableCard(String disprovableCard) {
		disprovableCards.add(disprovableCard);
	}
}
