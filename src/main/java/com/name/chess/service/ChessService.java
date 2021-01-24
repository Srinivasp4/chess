package com.name.chess.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.name.chess.dao.ChessDao;

@Service("chessService")
public class ChessService {

	@Autowired
	ChessDao chessDao;

	@Transactional
	public String startGame() {
		return chessDao.startGame();
	}
}
