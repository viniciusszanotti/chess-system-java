package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";//essa letra vai entrar na hora de imprimir o tabuleiro, onde tiver a peça vai aparecer a letra
	}

	@Override
	public boolean[][] possibleMoves() {
		//criar uma matriz de booleanos com a mesma dimensão do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position (0,0);
		
		// nw
		p.setValue(position.getRow() - 1, position.getColumn()-1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValue(p.getRow()-1, p.getColumn()-1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// ne
		p.setValue(position.getRow()-1, position.getColumn()+1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValue(p.getRow()-1, p.getColumn()+1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// se
		p.setValue(position.getRow() +1 , position.getColumn()+1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValue(p.getRow()+1, p.getColumn()+1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// sw
		p.setValue(position.getRow() + 1, position.getColumn()-1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValue(p.getRow()+1, p.getColumn()-1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
	
}
