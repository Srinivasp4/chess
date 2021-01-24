package com.name.chess.api.Game;

import com.name.chess.api.Pieces.Rook;

public class main {
	
	public static void main(String[] args) {
		Game game = new Game();
		// game.gameBoard.movePiece(new Bishop(0, 0, game.player1), 0, 0);
		
		System.out.println(game.gameBoard.isValidMove(new Rook(1, 1, game.player1), 1, 1));
		// game.gameBoard.
	}

}
