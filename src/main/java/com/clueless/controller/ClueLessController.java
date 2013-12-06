package com.clueless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ClueLessModel joinClueLess(@CookieValue("playerName") String playerName, @RequestParam("suspect") String suspect) {
		return clueLessService.joinClueLess(playerName, suspect);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ClueLessModel getClueLess() {
		return clueLessService.getClueLess();
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=leave")
	@ResponseBody
	public ClueLessModel leaveClueLess(@CookieValue("playerName") String playerName) {
		return clueLessService.leaveClueLess(playerName);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=init")
	@ResponseBody
	public ClueLessModel initClueLess() {
		return clueLessService.initClueLess();
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=move")
	@ResponseBody
	public ClueLessModel movePlayer(@CookieValue("playerName") String playerName, @RequestParam("location") String location) {
		return clueLessService.movePlayer(playerName, location);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=suggestion")
	@ResponseBody
	public SuggestionModel makeSuggestion(@CookieValue("playerName") String playerName, 
			@RequestParam("suspect") String suspect, @RequestParam("weapon") String weapon) {
		return clueLessService.makeSuggestion(playerName, suspect, weapon);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=disprove")
	@ResponseBody
	public SuggestionModel disproveSuggestion(@RequestParam("card") String card) {
		return clueLessService.disproveSuggestion(card);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=accusation")
	@ResponseBody
	public SolutionModel makeAccusation(@CookieValue("playerName") String playerName, 
			@RequestParam("room") String room, @RequestParam("suspect") String suspect, @RequestParam("weapon") String weapon) {
		return clueLessService.makeAccusation(playerName, room, suspect, weapon);
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=endTurn")
	@ResponseBody
	public ClueLessModel endTurn(@CookieValue("playerName") String playerName) {
		return clueLessService.endTurn(playerName);
	}
}
