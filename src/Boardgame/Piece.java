package Boardgame;

public class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board=board;
		position=null;//o null � autom�tico, apenas para facilitar a leitura
	}
	
	protected Board getBoard() {//protected:somente subclasses ou classes do mesmo pacote
		return board;
	}

	
	
}
