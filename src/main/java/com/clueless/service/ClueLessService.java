package com.clueless.service;

import com.clueless.model.ClueLessModel;

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
	 * @param sessionId The client's session ID.
	 * @param player The name of the character joining the game (i.e., Mr. Green, Miss Scarlet).
	 * @return ClueLessModel
	 */
	public ClueLessModel joinClueLess(String sessionId, String player);
		
	/**
	 * This removes a client from the current ClueLess game session.
	 * @param sessionId The client's session ID.
	 * @return ClueLessModel
	 */
	public ClueLessModel leaveClueLess(String sessionId);
	
	/**
	 * This initializes a new ClueLess game by performing these operations:<br>
	 * 1. Randomly distributes each weapon in each room with only one weapon per room.<br> 
	 * 2. Shuffles the deck.<br>
	 * 3. Generates the solution by picking one card of each type: weapon, suspect, room.<br>
	 * 4. Deals the remainings cards to all players.
	 * @return ClueLessModel
	 */
	public ClueLessModel initClueLess();
}
