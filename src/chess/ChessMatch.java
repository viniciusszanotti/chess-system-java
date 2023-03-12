package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {//na hora que for criado a partida o método irá:
		//criar um tabuleiro 8x8 e chamar o initialSetup()
		board = new Board (8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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
	
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){ //imprimir posições possíveis a partir de uma posição de origem
		Position position = sourcePosition.toPosition();//converter uma posição de xadrez para uma posição de matriz normal
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//convertendo as duas posições inseridas para posições da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		//validar se na posição de origem já havia uma peça
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;			
		}
		else {		
			nextTurn();
		}
		
		// SPECIAL MOVE EN PASSANT
		if (movedPiece instanceof Pawn && (target.getRow() == source.getRow()-2 || target.getRow() == source.getRow()+2)) {
			enPassantVulnerable = movedPiece;
		}
		else {
			enPassantVulnerable = null;
		}
		
		return (ChessPiece)capturedPiece;//downcasting para ChessPiece pq essa peça era do tipo Piece
	}
	
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);//remover a peça da posição inicial 
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);//remover a possível peça que esteja na posição de destino, que será a peça capturada
		board.placePiece(p, target);//colocar a peça em questão na posição de destino
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		// SPECIAL MOVE CASTLING KINGSIDE ROOK
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(),source.getColumn()+3);
			Position targetT = new Position(source.getRow(),source.getColumn()+1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);//retirar a torre de onde ela está
			board.placePiece(rook, targetT);
			rook.increaseMoveCount();
		}
		
		// SPECIAL MOVE CASTLING QUEENSIDE ROOK
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(),source.getColumn()-4);
			Position targetT = new Position(source.getRow(),source.getColumn()-1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);//retirar a torre de onde ela está
			board.placePiece(rook, targetT);
			rook.increaseMoveCount();
		}
		
		// SPECIAL MOVE EN PASSANT
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {//testar se o meu pião andou na diagonal e não capturou peça
				Position pawnPosition;
				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(target.getRow()+1, target.getColumn()); 
				}
				else {
					pawnPosition = new Position(target.getRow()-1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		
		return capturedPiece;
	}
	
	private void undoMove (Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
		// SPECIAL MOVE CASTLING KINGSIDE ROOK
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(),source.getColumn()+3);
			Position targetT = new Position(source.getRow(),source.getColumn()+1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetT);
			board.placePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}
		
		// SPECIAL MOVE CASTLING QUEENSIDE ROOK
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(),source.getColumn()-4);
			Position targetT = new Position(source.getRow(),source.getColumn()-1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetT);
			board.placePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}
		
		// SPECIAL MOVE EN PASSANT
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece)board.removePiece(target);//tirar a peça do seu lugar errado
				Position pawnPosition;
				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(3, target.getColumn()); 
				}
				else {
					pawnPosition = new Position(4, target.getColumn());
				}
				
				board.placePiece(pawn, pawnPosition);
			}
		}

		
	}
	
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)){//se não existir uma peça nessa posição, então:
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {//faz o downcast da peça nessa posição para ChessPiece e testa a cor dela
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {//se não tiver nenhum movimento possível...
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) { //se a posição de destino não é um movimento possível, não posso mexer para lá
			throw new ChessException("The chosen piece can't move to the target position");
		}
	}
	
	private void nextTurn() {
		turn++;
		//expressão condicional ternária:
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();//posição do rei no formato de matriz
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		//acima: lista de peças do oponente
		for (Piece p: opponentPieces) {
			boolean[][] mat = p.possibleMoves();//matriz de movimentos possíveis da peça adversaria 'p'
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {//se o movimento até a casa do rei for possível, retornar verdadeiro
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p: list) {
			boolean[][] mat = p.possibleMoves();
			for(int i =0;i<board.getRows();i++) {
				for(int j =0;j<board.getColumns();j++) {
					if(mat[i][j]) {//testar se essa posição irá tirar do check
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
			
		}
		return true;
	
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {//operação de colocar peça passando as posições nas coordenadas do xadrez
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());//instanciando uma ChessPosition com os dados informados e converter para a posição de matriz (.toPosition())
		piecesOnTheBoard.add(piece);//adiciona a peça na lista do tabuleiro
	}
	
	private void initialSetup() {
		//método responsável por iniciar a partida de xadrez colocando as peças no tabuleiro
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}

}
