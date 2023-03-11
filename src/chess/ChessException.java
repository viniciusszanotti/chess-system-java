package chess;

public class ChessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ChessException(String msg) {
		super(msg);//irá repassar para o construtor da superclasse essa mensagem
	}

}
