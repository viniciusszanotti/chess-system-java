package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}
	
	private boolean canMove(Position position) { // o Rei pode mover ou não para uma determinada posição
		ChessPiece p = (ChessPiece)getBoard().piece(position) ;
		return p == null || p.getColor() != getColor(); //ou a casa está vazia, ou tem uma peça adversária
	}

	@Override
	public boolean[][] possibleMoves() {
		//criar uma matriz de booleanos com a mesma dimensão do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		
		
		p.setValue(position.getRow()-1, position.getColumn()-2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValue(position.getRow()-2, position.getColumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValue(position.getRow()-2, position.getColumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValue(position.getRow()-1, position.getColumn()+2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
	
		p.setValue(position.getRow()+1, position.getColumn()+2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValue(position.getRow()+2, position.getColumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValue(position.getRow()+2, position.getColumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValue(position.getRow()+1, position.getColumn()-2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
}
