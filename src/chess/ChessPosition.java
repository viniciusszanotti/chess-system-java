package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}

	//métodos set() apagados
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	// # -> protected
	protected Position toPosition() { //convertar a posição inserida para a matriz
		return new Position(8 - row, column - 'a');
	}
	
	// # e sublinhada -> protected static
	protected static ChessPosition fromPosition(Position position) {//método deve retornar a fórmula inversa da anterior
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());//no xadrez primeiro é falado a coluna e depois a linha
		/*casting para char pois a conversão não é automática*/
	}
	
	@Override
	public String toString() {
		return "" + column + row;//"" serve para o copilador entender que isso é uma concatenação de strings
	}
	

}
