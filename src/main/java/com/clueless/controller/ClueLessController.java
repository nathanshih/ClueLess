package com.clueless.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clueless.model.ClueLessModel;
import com.clueless.service.ClueLessService;

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
		return this.clueLessService.joinClueLess(session.getId(), player);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ClueLessModel getClueLess(HttpSession session) {
		return this.clueLessService.getClueLess();
	}
	
	@RequestMapping(method=RequestMethod.POST, params="action=leave")
	@ResponseBody
	public ClueLessModel leaveClueLess(HttpSession session) {
		return this.clueLessService.leaveClueLess(session.getId());
	}
}
