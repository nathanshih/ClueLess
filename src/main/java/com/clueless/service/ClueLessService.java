/**
 * 
 */
package com.clueless.service;

import com.clueless.model.ClueLessModel;

/**
 * @author nshih
 *
 */
public interface ClueLessService {
	public ClueLessModel getClueLess();
	public ClueLessModel joinClueLess(String sessionId, String player);
	public ClueLessModel leaveClueLess(String sessionId);
}
