package com.clueless.model;

import java.util.ArrayList;

/**
 * The SuggestionModel holds information on who is making the suggestion, who is disproving the
 * suggestion, and which card is being used to disprove the suggestion.
 * @author nshih
 */
public class SuggestionModel {

	private static final SuggestionModel instance = new SuggestionModel();
	
	private String makingSuggestion;
	private String disprovingSuggestion;
	private String disprovingCard;
	private ArrayList<String> disprovableCards;
	
	private SuggestionModel() {
		disprovableCards = new ArrayList<String>();
	}
	
	/**
	 * Returns the SuggestionModel as a singleton.
	 * @return SuggestionModel
	 */
	public static SuggestionModel getInstance() {
		return instance;
	}

	public String getMakingSuggestion() {
		return makingSuggestion;
	}

	public void setMakingSuggestion(String makingSuggestion) {
		this.makingSuggestion = makingSuggestion;
	}

	public String getDisprovingSuggestion() {
		return disprovingSuggestion;
	}

	public void setDisprovingSuggestion(String disprovingSuggestion) {
		this.disprovingSuggestion = disprovingSuggestion;
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
