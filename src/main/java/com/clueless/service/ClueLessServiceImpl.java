package com.clueless.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.clueless.model.ClueLessModel;
import com.clueless.model.Player;

/**
 * The ClueLessServiceImpl implements the ClueLessService interface. All the actual game logic code is here.
 * @author nshih
 */
@Service
public class ClueLessServiceImpl implements ClueLessService {

	ClueLessModel clueLessModel = ClueLessModel.getInstance();
	
	@Override
	public ClueLessModel getClueLess() {
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel joinClueLess(String sessionId, String player) {
		HashMap<String, Player> players = this.clueLessModel.getPlayers();
		players.put(sessionId, new Player(player));
		this.clueLessModel.setPlayers(players);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() >= 3 && hasMissScarletJoined) {
			this.clueLessModel.setGameReady(true);
		}
		
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel leaveClueLess(String sessionId) {
		HashMap<String, Player> players = this.clueLessModel.getPlayers();
		players.remove(sessionId);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() < 3 || hasMissScarletJoined == false) {
			this.clueLessModel.setGameReady(false);
		}
		this.clueLessModel.setPlayers(players);
		
		return this.clueLessModel;
	}
	
	/**
	 * Sets the sessionId associated with Miss Scarlet to the whoseTurn boolean in the ClueLessModel 
	 * since miss Scarlet always starts first.
	 * @return true if Miss Scarlet has joined otherwise false 
	 */
	private boolean checkMissScarletJoinStatus() {
		HashMap<String, Player> players = this.clueLessModel.getPlayers();
		for (String key : players.keySet()) {
			Player player = players.get(key);
			if (player.getName().equals("Miss Scarlet")) {
				this.clueLessModel.setWhoseTurn(key);
				return true;
			}
		}
		this.clueLessModel.setWhoseTurn(null);
		return false;
	}
}
