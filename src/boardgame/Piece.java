package boardgame;

public class Piece {

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
	
	
	
}
