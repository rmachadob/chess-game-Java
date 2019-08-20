package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Boardgame.Board;
import Boardgame.Piece;
import Boardgame.Position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;

	// listas para controle das pe�as no tabuleiro e as capturadas
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
		// essas listas em cima eu poderia tamb�m colocar aqui dentro do construtor, ia
		// instanciar do mesmo jeito junto com o ChessMatch
	}// � essa classe q tem q saber o tamanho do board

	// esses gets abaixo servem para ter acesso no programa principal
	public int getTurn() {
		return turn;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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
	} // Os 2 for percorrem as posi��es.
		// pra cada posi�ao i j do meu tabuleiro a matriz mat na linha i coluna j recebe
		// o board.piece ij. (ChessPiece) � downcasting pra ele entender q n�o � uma
		// piece.
		// somente a camada de xadrez interage com o program

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();// convertendo posi��o de xadrez pra uma normal
		validateSourcePosition(position);// j� consigo validar a posi��o logo q o usu�rio entra com ela
		return board.piece(position).possibleMoves();// essa linha aqui retorna a posi��o COM o possible moves (fica
														// pintado no board)
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();// toPosition pra trocar do xadrez pra matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);// pra validar se tinha pe�a na origem
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}

		check = (testCheck(opponent(currentPlayer))) ? true : false;// testa se o advers�rio foi posto em cheque

		if (testCheckMate(opponent(currentPlayer))) {// se estiver em cheque mate recebe true, sen�o chama o pr�x. turno
			checkMate = true;
		} else {
			nextTurn();

		}

		return (ChessPiece) capturedPiece;// downcasting da Piece pra ChessPiece
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);// tira a pe�a da origem
		Piece capturedPiece = board.removePiece(target);// para remover eventual pe�a no destino,
		// q logicamente � uma pe�a capturada e guardar na variavel capturedPiece
		board.placePiece(p, target);// agora sim coloca a pe�a de origem no destino
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);// remove e add nas respectivas listas
			capturedPieces.add(capturedPiece);// nessa linha o add tava dando problema pq tipo da vari�vel capturedPiece
												// � do tipo Piece, enquanto a lista estava como ChessPiece. Resolveu
												// trocando na lista pra ficar mais gen�rico(Piece)
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}// desfaz a l�gica do m�todo makeMove, devolvendo eventual pe�a capturada

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		} // o m�todo thereIsAPiece retorna um BoardException, mas a valida�ao desse
			// m�todo retorna um ChessException. Mas na real toda exce��o de xadrez � tbm
			// uma de tabuleiro s� q mais espec�fica
			// ou seja, l� na classe ChessException eu atualizo, tiro o RuntimeException e
			// deixo Board
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is nor yours");
		} // se o jogador for diferente do board.position e da cor, est� tentando mexer
			// uma pe�a q n�o � dele. O getColor � uma propriedade do ChessPiece, s� que o
			// board.Piece � da classe mais gen�rica (piece), por isso tem q fazer o
			// downcasting pra ChessPiece
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		} // nesse segundo if, estou acessando o tabuleiro, a partir do tabuleiro acesso a
			// pe�a na posi��o de origem e desse ponto chamo o m�todo pra ver se tem
			// movimento poss�vel.Tem que negar a condi��o tbm(!).

	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}// � s� validar testando se a posi��o de destino � um movimento poss�vel em
		// rela��o � pe�a q est� na posi��o de origem

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}// express�o condicional tern�ria: se o currentPlayer for igual a Color.WHITE,
		// ent�o agora ser� black(? Color.BLACK). Caso contr�rio ser� color.WHITE (:
		// Color.WHITE)

	private Color opponent(Color color) {
		return (color == color.WHITE) ? Color.BLACK : Color.WHITE;
	}// devolve o oponente e sua respectiva cor. Se a cor que eu recebi como
		// argumento for branca, devolve preta

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}// exece��o pra nunca acontecer

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {// corre a matriz de pe�as advers�rias e se a posi��o retorna true o rei est� em
			// cheque, sen�o retorna falso
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {// se essa cor n�o estiver em cheque, tamb�m n�o est� em cheque mate
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece) p).getChessPosition().toPosition();// n�o da pra usar a posi��o
																							// direto da matriz pq � da
																							// classe piece e private
						Position target = new Position(i, j);// s� instanciar com a posi��o poss�vel (target), passando
						// i e j
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);// vai testar se ainda est� em cheque
						undoMove(source, target, capturedPiece);// como est� testando, precisa desfazer o movimento
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);// esse aqui coloca na lista as pe�as do tabuleiro.piecesOnTheBoard � o nome da
									// lista(toda hora to achando q eh m�todo).
	}// metodo pra colocar as pe�as usando posi�ao do xadrez e nao matriz.
		// o metodo .toPosition � do ChessPosition e converte da matriz pro xadrez

	private void initialSetup() {

//		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
//		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
//		placeNewPiece('d', 8, new King(board, Color.BLACK));
//		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
//		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
//		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
//
//		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
//		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
//		placeNewPiece('d', 1, new King(board, Color.WHITE));
//		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
//		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
//		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));

		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));

	}
}// pode tirar o board, trocar o metodo pra placeNewPiece.
	// a� colocar as posi��es de xadrez e instanciar a pe�a
	// board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
