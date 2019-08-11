
package Chess;

import Boardgame.BoardException;

public class ChessException extends /* RuntimeException */BoardException {
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
		super(msg);
	} // para repassar para o construtor da superclasse essa mensagem

}
