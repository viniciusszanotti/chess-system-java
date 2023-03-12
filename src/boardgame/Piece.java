package boardgame;

public abstract class Piece {

	//somente classes do mesmo pacote e subclasses irão poder acessar o tabuleiro de uma peça
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}

	//retirado o método set pois o tabuleiro não pode ser alterado
	
	public abstract	boolean[][] possibleMoves();
		
	public boolean possibleMove(Position position) {//método concreto que utiliza o método abstrato (Hook Methods)
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();//matriz tem que ser percorrida para verificar se existe alguma posição verdadeira
		for (int i =0; i<mat.length; i++) {
			for (int j =0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
