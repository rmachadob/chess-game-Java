package Chess;

import java.util.ArrayList;
import java.util.List;

import Boardgame.Board;
import Boardgame.Piece;
import Boardgame.Position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	// listas para controle das peças no tabuleiro e as capturadas
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
		// essas listas em cima eu poderia também colocar aqui dentro do construtor, ia
		// instanciar do mesmo jeito junto com o ChessMatch
	}// é essa classe q tem q saber o tamanho do board

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

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

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();// convertendo posição de xadrez pra uma normal
		validateSourcePosition(position);// já consigo validar a posição logo q o usuário entra com ela
		return board.piece(position).possibleMoves();// essa linha aqui retorna a posição COM o possible moves (fica
														// pintado no board)
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();// toPosition pra trocar do xadrez pra matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);// pra validar se tinha peça na origem
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;// downcasting da Piece pra ChessPiece
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);// tira a peça da origem
		Piece capturedPiece = board.removePiece(target);// para remover eventual peça no destino,
		// q logicamente é uma peça capturada e guardar na variavel capturedPiece
		board.placePiece(p, target);// agora sim coloca a peça de origem no destino
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);// nessa linha o add tava dando problema pq tipo da variável capturedPiece
												// é do tipo Piece, enquanto a lista estava como ChessPiece. Resolveu
												// trocando na lista pra ficar mais genérico(Piece)
		}

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
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is nor yours");
		} // se o jogador for diferente do board.position e da cor, está tentando mexer
			// uma peça q não é dele. O getColor é uma propriedade do ChessPiece, só que o
			// board.Piece é da classe mais genérica (piece), por isso tem q fazer o
			// downcasting pra ChessPiece
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		} // nesse segundo if, estou acessando o tabuleiro, a partir do tabuleiro acesso a
			// peça na posição de origem e desse ponto chamo o método pra ver se tem
			// movimento possível.Tem que negar a condição tbm(!).

	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}// é só validar testando se a posição de destino é um movimento possível em
		// relação à peça q está na posição de origem

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}// expressão condicional ternária: se o currentPlayer for igual a Color.WHITE,
		// então agora será black(? Color.BLACK). Caso contrário será color.WHITE (:
		// Color.WHITE)

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);// esse aqui coloca na lista as peças do tabuleiro.piecesOnTheBoard é o nome da
									// lista(toda hora to achando q eh método).
	}// metodo pra colocar as peças usando posiçao do xadrez e nao matriz.
		// o metodo .toPosition é do ChessPosition e converte da matriz pro xadrez

	private void initialSetup() {

		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));

	}
}// pode tirar o board, trocar o metodo pra placeNewPiece.
	// aí colocar as posições de xadrez e instanciar a peça
	// board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
