package Chess.pieces;

import Boardgame.Board;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);// repassando o construtor pra superclasse

	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}// por enquanto, sempre q chamar as posi��es poss�veis do rei retorna tudo falso
		// por padr�o do boolean PERGUNTA: PORQUE S� ME OBRIGA A IMPLEMENTAR O M�TODO ABSTRATO??

}
