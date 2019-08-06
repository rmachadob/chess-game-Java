package Chess;

import Boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instatiating ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	// pode tirar os sets pra ningu�m mexer no tabuleiro
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow());
	}// mesma l�gica do de cima, mas o contr�rio, do xadrez pra matriz

	@Override // precisa fazer o toString dessa posi��o as aways
	public String toString() {
		return "" + column + row;
	}// essa primeira String vazia � pra for�ar o compilador a entender q vamos concatenar strings

}
/*
 * pra converter de linha do xadrez (a at� h) pra matriz, fica 8-linha
 * 
 * para as colunas, a l�gica � 'a'-'a'=0 (pode usar os operadores com unicode *
 * normal) 'b'-'a'=1 e assim em diante
 */