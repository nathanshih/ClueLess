package com.clueless.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clueless.domain.Card;
import com.clueless.model.ClueLessModel;
import com.clueless.model.SolutionModel;
import com.clueless.model.SuggestionModel;
import com.clueless.service.ClueLessService;

/**
 * The ClueLessController is the interface between the client and the game logic. Requests sent from
 * the client are mapped to methods here which are then passed to the ClueLessService for processing.
 * @author nshih
 */
@Controller
@RequestMapping("v1")
public class ClueLessController {
		
	ClueLessService clueLessService;

	@Autowired
	public ClueLessController(ClueLessService clueLessService) {
		this.clueLessService = clueLessService;
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=join")
	@ResponseBody
	public ClueLessModel joinClueLess(HttpSession session, @RequestParam("player") String player) {
		return clueLessService.joinClueLess(session.getId(), player);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ClueLessModel getClueLess(HttpSession session) {
		return clueLessService.getClueLess();
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=leave")
	@ResponseBody
	public ClueLessModel leaveClueLess(HttpSession session) {
		return clueLessService.leaveClueLess(session.getId());
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=init")
	@ResponseBody
	public ClueLessModel initClueLess() {
		return clueLessService.dealCards();
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=move")
	@ResponseBody
	public ClueLessModel movePlayer(HttpSession session, @RequestParam("location") String location) {
		return clueLessService.movePlayer(session.getId(), location);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=suggestion")
	@ResponseBody
	public SuggestionModel makeSuggestion(HttpSession session, @RequestParam("room") String room,
			@RequestParam("suspect") String suspect, @RequestParam("weapon") String weapon) {
		return clueLessService.makeSuggestion(session.getId(), room, suspect, weapon);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=disprove")
	@ResponseBody
	public SuggestionModel disproveSuggestion(HttpSession session, @RequestBody Card card) {
		return clueLessService.disproveSuggestion(session.getId(), card);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=accusation")
	@ResponseBody
	public SolutionModel makeAccusation(HttpSession session, @RequestParam("room") String room,
			@RequestParam("suspect") String suspect, @RequestParam("weapon") String weapon) {
		return clueLessService.makeAccusation(session.getId(), room, suspect, weapon);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=endTurn")
	@ResponseBody
	public ClueLessModel endTurn(HttpSession session) {
		return clueLessService.endTurn(session.getId());
	}
}
