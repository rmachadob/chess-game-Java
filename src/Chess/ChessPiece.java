package Chess;

import Boardgame.Board;
import Boardgame.Piece;

public abstract class ChessPiece extends Piece {
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);// ele repassa p/ o construtor da superclasse
		this.color = color;
	}// se vc n�o faz esse construtor o compilador reclama pq � uma subclasse de
		// Piece (q tem construtor)

	public Color getColor() {
		return color;
	}

}
