package com.clueless.model;

import java.util.ArrayList;

/**
 * The SuggestionModel holds information on who is making the suggestion, who is disproving the
 * suggestion, and which card is being used to disprove the suggestion.
 * @author nshih
 */
public class SuggestionModel {

	private String makingSuggestion;
	private String disprovingCard;
	private ArrayList<String> disprovableCards;
	
	public SuggestionModel() {
		disprovableCards = new ArrayList<String>();
	}
	
	public String getMakingSuggestion() {
		return makingSuggestion;
	}

	public void setMakingSuggestion(String makingSuggestion) {
		this.makingSuggestion = makingSuggestion;
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
