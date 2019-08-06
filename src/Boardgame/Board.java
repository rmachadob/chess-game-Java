package Boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {//para evitar de criar um board com linha ou coluna 0
		if(rows<1||columns<1) {
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
		if(!positionExists(row, column)) {//caso a posição não(!) exista, lança exceção
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}//mesmo que o metodo de cima, é q as vezes eh mais facil ver a 
	//posição pela posição mesmo ou pela linha e coluna

	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position))/*pra testar se ja tem peça na posição*/ {
			throw new BoardException("There is already a piece on position "+position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {//assim, quando chamar thereIsAPiece já testa se a posição existe
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}//pra saber se já tem uma peça ou nao, ver se eh diferente de null

}
