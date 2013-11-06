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
}
