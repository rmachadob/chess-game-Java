package Boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		} // se a posição não existir lança nova exceção
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		} // mesma lógica do de cima, mas com position
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;// tem q falar q essa peça nao eh mais nula

	}// ele vai na matriz de peças do tabuleiro e atribui à essa posição a peça q
		// veio como argumento
		// o pieces já foi instanciado no construtor

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) {
			return null;
		} // se a peça nessa posição for nula, não tem peça entao retorna o nulo
		Piece aux = piece(position);// variável auxiliar q recebe a peça da posição
		aux.position = null;// vai pegar essa peça aux e falar que agora é nulo(saiu do tabuleiro)
		pieces[position.getRow()][position.getColumn()] = null;
		// essa matriz de peças (pieces) nessa posição vai receber nulo,
		// ou seja, na matriz foi removida a peça e agora é nulo
		return aux;// manda retornar aux q contém a peça q foi retirada
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}// a posição tem q ser maior ou igual a zero e menor q o limite do
		// tabuleiro()rows e columns

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}// troquei pra public pra resolver o problema de acesso no chesspieces. se der
		// ruim depois pode ser isso

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}

}