package Chess;

import Boardgame.Board;
import Boardgame.BoardException;
import Boardgame.Piece;
import Boardgame.Position;
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
	} // Os 2 for percorrem as posições.
		// pra cada posiçao i j do meu tabuleiro a matriz mat na linha i coluna j recebe
		// o board.piece ij. (ChessPiece) é downcasting pra ele entender q não é uma
		// piece.
		// somente a camada de xadrez interage com o program

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();// toPosition pra trocar do xadrez pra matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);// pra validar se tinha peça na origem
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;// downcasting da Piece pra ChessPiece
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);// tira a peça da origem
		Piece capturedPiece = board.removePiece(target);// para remover eventual peça no destino,
		// q logicamente é uma peça capturada
		board.placePiece(p, target);// agora sim coloca a peça de origem no destino
		return capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		} // o método thereIsAPiece retorna um BoardException, mas a validaçao desse
			// método retorna um ChessException. Mas na real toda exceção de xadrez é tbm
			// uma de tabuleiro só q mais específica
			// ou seja, lá na classe ChessException eu atualizo, tiro o RuntimeException e
			// deixo Board
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());

	}// metodo pra colocar as peças usando posiçao do xadrez e nao matriz.
		// o metodo .toPosition é do ChessPosition e converte da matriz pro xadrez

	private void initialSetup() {
//		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
//		placeNewPiece('e', 8, new King(board, Color.BLACK));
//		placeNewPiece('e', 1, new King(board, Color.WHITE));

		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
//        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
//        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
//        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
//        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
//        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
//        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
//        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
//        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
//        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
//        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
//        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
//        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
//        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
//        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
//        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
//        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}

}// pode tirar o board, trocar o metodo pra placeNewPiece.
	// aí colocar as posições de xadrez e instanciar a peça
	// board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
