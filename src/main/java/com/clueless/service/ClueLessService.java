package com.clueless.service;

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
	 * This joins a new client to the current ClueLess game session. The session ID and name of the ClueLess suspect
	 * are linked together so that the server knows which ClueLess suspect is being played by which client.
	 * @param playerName name of the player
	 * @param suspect name of the suspect to be played (i.e., Mr. Green, Miss Scarlet)
	 * @return ClueLessModel
	 */
	public ClueLessModel joinClueLess(String playerName, String suspect);
		
	/**
	 * This removes a client from the current ClueLess game session.
	 * @param playerName name of the player leaving game
	 * @return ClueLessModel
	 */
	public ClueLessModel leaveClueLess(String playerName);
	
	/**
	 * This initializes the game of ClueLess by performing these actions:
	 * <br>1. Deals the remaining cards (after cards for the solution have been picked and removed from the deck) to all players.
	 * <br>2. Orders the player turns according to their chosen suspect token.
	 * <br>3. Sets the playedBy field in the suspect tokens to the session ID of the player controlling it.
	 * @return ClueLessModel
	 */
	public ClueLessModel initClueLess();
	
	/**
	 * Moves a player to a new location.
	 * @param playerName name of the player to be moved
	 * @param location location to move player to (use class static variables i.e., Room.HALL, Hallway.HALLWAY3)
	 * @return ClueLessModel 
	 */
	public ClueLessModel movePlayer(String playerName, String location);
	
	/**
	 * This returns the current suggestion information when a player is able to disprove a suggestion. 
	 * @return SuggestionModel
	 */
	public SuggestionModel getSuggestion();
	
	/**
	 * Allows a player to make a suggestion. Information needed are the suspect and weapon used to commit the crime.
	 * Since suggestions can only be made while in a room, the player's location will be used in the suggestion.
	 * @param playerName name of the player making the suggestion
	 * @param suspect name of the suspect who committed the crime (use class static variables i.e., Player.MISS_SCARLET)
	 * @param weapon weapon used to commit the crime (use class static variables i.e., Weapon.KNIFE, Weapon.LEAD_PIPE)
	 * @return SuggestionModel
	 */
	public SuggestionModel makeSuggestion(String playerName, String suspect, String weapon);
	
	/**
	 * Allows a player to disprove a suggestion by selecting a card to disprove with.
	 * @param card a card to disprove the suggestion
	 * @return SuggestionModel
	 */
	public SuggestionModel disproveSuggestion(String card);
	
	/**
	 * Allows a player to make an accuestion. Information needed are the room, suspect, and weapon used to commit the crime.
	 * @param playerName name of the player making the suggestion
	 * @param room room which the crime took place (use class static variables i.e., Room.HALL, Room.BILLIARD_ROOM)
	 * @param suspect name of the suspect who committed the crime (use class static variables i.e., Player.MISS_SCARLET)
	 * @param weapon weapon used to commit the crime (use class static variables i.e., Weapon.KNIFE, Weapon.LEAD_PIPE)
	 * @return SolutionModel
	 */
	public SolutionModel makeAccusation(String playerName, String room, String suspect, String weapon);
	
	/**
	 * Allows a player to end their turn and sets whose turn to the next player.
	 * @param playerName name of the player ending their turn
	 * @return ClueLessModel
	 */
	public ClueLessModel endTurn(String playerName);
}
