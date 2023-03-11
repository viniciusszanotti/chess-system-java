package chess;

import boardgame.Board;
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {//operação de colocar peça passando as posições nas coordenadas do xadrez
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());//instanciando uma ChessPosition com os dados informados e converter para a posição de matriz (.toPosition())
		
	}
	
	private void initialSetup() {
		//método responsável por iniciar a partida de xadrez colocando as peças no tabuleiro
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
		
		
	}

}
