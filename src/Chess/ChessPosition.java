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

	// pode tirar os sets pra ninguém mexer no tabuleiro
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
	}// mesma lógica do de cima, mas o contrário, do xadrez pra matriz

	@Override // precisa fazer o toString dessa posição as aways
	public String toString() {
		return "" + column + row;
	}// essa primeira String vazia é pra forçar o compilador a entender q vamos concatenar strings

}
/*
 * pra converter de linha do xadrez (a até h) pra matriz, fica 8-linha
 * 
 * para as colunas, a lógica é 'a'-'a'=0 (pode usar os operadores com unicode *
 * normal) 'b'-'a'=1 e assim em diante
 */