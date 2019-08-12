package Boardgame;

public abstract class Piece {// abstract pra evitar que possa ser instanciada

	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;// o null � autom�tico, apenas para facilitar a leitura
	}

	protected Board getBoard() {// protected:somente subclasses ou classes do mesmo pacote
		return board;
	}

	public abstract boolean[][] possibleMoves();// matriz que retorna falso quando a pe�a n�o pode ocupar a posi�ao e
												// verdadeiro quando pode.por isso o tipo boolean
	// abstrato pq pe�a � gen�rico

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}// m�todo concreto que utiliza m�todo abstrato (m�todo gancho)
		// pode ser concreto pq est� chamando uma poss�vel implementa��o de alguma
		// subclasse concreta daclasse piece

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();// vari�vel auxiliar recebendo a chamada do m�todo abstrato
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {// se minha matriz na linha i coluna j for verdadeira existe movimento poss�vel,
								// ent�o true
					return true;
				}
			}
		} // os dois for correm a matriz buscando uma posi��o verdadeira
		return false;// fora dos for, se n�o achou nada � false(a pe�a est� travada)
	}

}
