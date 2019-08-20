package Chess;

import Boardgame.Board;
import Boardgame.Piece;
import Boardgame.Position;

public abstract class ChessPiece extends Piece {
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);// ele repassa p/ o construtor da superclasse
		this.color = color;
	}// se vc n�o faz esse construtor o compilador reclama pq � uma subclasse de
		// Piece (q tem construtor)

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}// m�todo para retornar a chessPosition na classe clesspiece

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);// usa sempre o getBoard pra pegar a pe�a
		return p != null && p.getColor() != color;// testa se tem pe�a(!= de nulo) e se a cor � diferente dessa pe�a
	}

}
