/**
 * 
 */
package com.clueless.service;

import org.springframework.stereotype.Service;

import com.clueless.model.ClueLessModel;

/**
 * @author nshih
 *
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
		this.clueLessModel.addPlayer(sessionId, player);
		int numOfPlayers = this.clueLessModel.getPlayers().size();
		if (numOfPlayers >= 3) {
			this.clueLessModel.setGameReady(true);
		}
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel leaveClueLess(String sessionId) {
		this.clueLessModel.removePlayer(sessionId);
		return this.clueLessModel;
	}
}
