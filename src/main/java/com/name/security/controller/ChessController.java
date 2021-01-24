package com.name.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.name.chess.service.ChessService;

@RestController
@RequestMapping("/api/chess/v1")
public class ChessController {

	@Autowired
	ChessService chessService;
	
	
	@RequestMapping(value = "/start", method = RequestMethod.POST, headers = "Accept=application/json")
	public String startGame() {
		return chessService.startGame();
	}
	
}
