package com.clueless.service;

import com.clueless.domain.Card;
import com.clueless.model.ClueLessModel;
import com.clueless.model.SolutionModel;
import com.clueless.model.SuggestionModel;

/**
 * The ClueLessService exposes all the methods for the ClueLess game logic.
 * @author nshih
 */
public interface ClueLessService {
	
	/**
	 * This fetchs the current game state.
	 * @return ClueLessModel
	 */
	public ClueLessModel getClueLess();
	
	/**
	 * This joins a new client to the current ClueLess game session. The session ID and name of the ClueLess character
	 * are linked together so that the server knows which ClueLess character is being played by which client.
	 * @param sessionId session ID of client joining game
	 * @param player name of the character joining the game (i.e., Mr. Green, Miss Scarlet)
	 * @return ClueLessModel
	 */
	public ClueLessModel joinClueLess(String sessionId, String player);
		
	/**
	 * This removes a client from the current ClueLess game session.
	 * @param sessionId session ID of client leaving game
	 * @return ClueLessModel
	 */
	public ClueLessModel leaveClueLess(String sessionId);
	
	/**
	 * Deals the remainings cards (after cards for the solution have been picked and removed from the deck) to all players.
	 * @return ClueLessModel
	 */
	public ClueLessModel dealCards();
	
	/**
	 * Moves a player to a new location.
	 * @param sessionId session ID of the player to be moved
	 * @param location location to move player to (use class static variables i.e., Room.HALL, Hallway.HALLWAY3)
	 * @return ClueLessModel 
	 */
	public ClueLessModel movePlayer(String sessionId, String location);
	
	/**
	 * Allows a player to make a suggestion. Information needed are the room, suspect, and weapon used to commit the crime.
	 * @param sessionId session ID of player making the suggestion
	 * @param room room which the crime took place (use class static variables i.e., Room.HALL, Room.BILLIARD_ROOM)
	 * @param suspect name of the suspect who committed the crime (use class static variables i.e., Player.MISS_SCARLET)
	 * @param weapon weapon used to commit the crime (use class static variables i.e., Weapon.KNIFE, Weapon.LEAD_PIPE)
	 * @return SuggestionModel
	 */
	public SuggestionModel makeSuggestion(String sessionId, String room, String suspect, String weapon);
	
	/**
	 * Allows a player to disprove a suggestion by showing a card matching the suggestion.
	 * @param sessionId session ID of player disproving the suggestion
	 * @param card a card to disprove the suggestion
	 * @return SuggestionModel
	 */
	public SuggestionModel disproveSuggestion(String sessionId, Card card);
	
	/**
	 * Allows a player to make an accuestion. Information needed are the room, suspect, and weapon used to commit the crime.
	 * @param sessionId session ID of player making the suggestion
	 * @param room room which the crime took place (use class static variables i.e., Room.HALL, Room.BILLIARD_ROOM)
	 * @param suspect name of the suspect who committed the crime (use class static variables i.e., Player.MISS_SCARLET)
	 * @param weapon weapon used to commit the crime (use class static variables i.e., Weapon.KNIFE, Weapon.LEAD_PIPE)
	 * @return SolutionModel
	 */
	public SolutionModel makeAccusation(String sessionId, String room, String suspect, String weapon);
	
	/**
	 * Allows a player to end their turn.
	 * @param sessionId session ID of player ending their turn
	 * @return
	 */
	public ClueLessModel endTurn(String sessionId);
}
