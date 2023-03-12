package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";//essa letra vai entrar na hora de imprimir o tabuleiro, onde tiver a peça vai aparecer a letra
	}

	@Override
	public boolean[][] possibleMoves() {
		//criar uma matriz de booleanos com a mesma dimensão do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		return mat;
	}
	
}
