package applications;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		
		while(true) {
			try {
				
				// UI = User Interface
				UI.clearScreen();
				UI.printMatch(chessMatch, captured); // receber a matriz de peças da minha partida
				System.out.println();
				System.out.print("Source: "); //ler a posição de origem
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves); //imprimir o tabuleiro, porém colorindo as posições possíveis onde a peça pode mover
				
				System.out.println();
				System.out.print("Target: "); //ler a posição de destino
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				//sempre que eu executar um movimento e ele resultar em uma peça capturada, irá adicionar na lista de pçs capturadas
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();//programa irá aguardar apertar a tecla enter
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
	}	

}
	