package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;//o tabuleiro terá uma matriz de peças
	
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1 ) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 columns");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	//retirado o método set das linhas e colunas, pois uma vez informado elas não serão alteradas
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
			
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
			

		return pieces[position.getRow()][position.getColumn()];
	}
	
	
	//método responsável por colocar uma peça em uma determinada posição
	
	public void placePiece(Piece piece, Position position) {
		
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		
		
		//atribuir a peça que veio como argumento na matriz de peças do tabuleiro
		pieces[position.getRow()][position.getColumn()] = piece;
		
		//agora a peça não está mais na posição nula, mas sim na posição "position"
		piece.position = position;//".position" é acessível pois está no mesmo pacote que a classe "Piece"

	}
		
	private boolean positionExists(int row, int column) {
		//verificando se uma posição em uma dada linha e coluna existe
		return row >= 0 && row < rows && column >=0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {//1- testa se a posição existe / 2- se já tem alguma peça na posição
		
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
			
		return piece(position) != null;//utilizando o  método da linha 40
	}
	
	
}
