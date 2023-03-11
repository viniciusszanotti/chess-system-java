package applications;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		
		while(true) {
			// UI = User Interface
			UI.printBoard(chessMatch.getPieces()); // receber a matriz de peças da minha partida
			System.out.println();
			System.out.print("Source: "); //ler a posição de origem
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Target: "); //ler a posição de destino
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece captChessPiece = chessMatch.performChessMove(source, target);
			
		}
		
	}	

}
	