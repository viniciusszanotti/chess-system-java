package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {//na hora que for criado a partida o método irá:
		//criar um tabuleiro 8x8 e chamar o initialSetup()
		board = new Board (8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){ //deve retornar uma matriz de peças de xadrez correspondete à esa partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		//percorrer a matriz de peças do tabuleiro -> p/ cada peça fazer um downcasting para ChessPiece
		for (int i =0; i<board.getRows(); i++) {
			for (int j =0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);//(ChessPiece) para interpretado como uma peça de xadrez ao inves de simplesmente uma peça comum
		
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		//método responsável por iniciar a partida de xadrez colocando as peças no tabuleiro
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
		
		
	}

}
