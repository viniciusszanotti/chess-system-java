package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;//o tabuleiro terá uma matriz de peças
	
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	
	//método responsável por colocar uma peça em uma determinada posição
	
	public void placePiece(Piece piece, Position position) {
		//atribuir a peça que veio como argumento na matriz de peças do tabuleiro
		pieces[position.getRow()][position.getColumn()] = piece;
		
		//agora a peça não está mais na posição nula, mas sim na posição "position"
		piece.position = position;//".position" é acessível pois está no mesmo pacote que a classe "Piece"
		
		
	}
			
}
