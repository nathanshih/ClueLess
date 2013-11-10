package com.clueless.model;

/**
 * The SuggestionModel holds information on who is making the suggestion, who is disproving the
 * suggestion, and which card is being used to disprove the suggestion.
 * @author nshih
 */
public class SuggestionModel {

	private String makingSuggestion;
	private String disprovingSuggestion;
	private String whichCard;
	
	public SuggestionModel() {
		
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

	public String getWhichCard() {
		return whichCard;
	}

	public void setWhichCard(String whichCard) {
		this.whichCard = whichCard;
	}
}
