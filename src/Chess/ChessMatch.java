package Chess;

import Boardgame.Board;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}// é essa classe q tem q saber o tamanho do board

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];// pega do board o tamanho
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}   // Os 2 for percorrem as posições.
		// pra cada posiçao i j do meu tabuleiro a matriz mat na linha i coluna j recebe
		// o board.piece ij. (ChessPiece) é downcasting pra ele entender q não é uma
		// piece.
		// somente a camada de xadrez interage com o program

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());

	}// metodo pra colocar as peças usando posiçao do xadrez e nao matriz.
		// o metodo .toPosition justamente converte do xadrez pra matriz

	private void initialSetup() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));

	}// pode tirar o board, trocar o metodo pra placeNewPiece.
		// aí colocar as posições de xadrez e instanciar a peça
		// board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

}
