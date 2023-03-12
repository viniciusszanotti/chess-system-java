package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece{

	public Queen(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "Q";//essa letra vai entrar na hora de imprimir o tabuleiro, onde tiver a peça vai aparecer a letra
	}

	@Override
	public boolean[][] possibleMoves() {
		//criar uma matriz de booleanos com a mesma dimensão do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position (0,0);
		
		// above
		p.setValue(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()-1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// left
		p.setValue(position.getRow(), position.getColumn()-1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()-1);;
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// right
		p.setValue(position.getRow(), position.getColumn()+1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()+1);;
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValue(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição p existir e não tiver uma peça lá, marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()+1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
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
