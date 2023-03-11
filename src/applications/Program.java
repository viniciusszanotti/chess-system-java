package applications;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		ChessMatch chessMatch = new ChessMatch();
		//UI = User Interface
		UI.printBoard(chessMatch.getPieces()); //receber a matriz de peças da minha partida
		
		
		
	}	

}
