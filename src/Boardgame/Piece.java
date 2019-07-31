package Boardgame;

public class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board=board;
		position=null;//o null é automático, apenas para facilitar a leitura
	}
	
	protected Board getBoard() {
		return board;
	}

	
	
}
