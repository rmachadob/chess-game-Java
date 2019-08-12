package Boardgame;

public abstract class Piece {// abstract pra evitar que possa ser instanciada

	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;// o null é automático, apenas para facilitar a leitura
	}

	protected Board getBoard() {// protected:somente subclasses ou classes do mesmo pacote
		return board;
	}

	public abstract boolean[][] possibleMoves();// matriz que retorna falso quando a peça não pode ocupar a posiçao e
												// verdadeiro quando pode.por isso o tipo boolean
	// abstrato pq peça é genérico

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}// método concreto que utiliza método abstrato (método gancho)
		// pode ser concreto pq está chamando uma possível implementação de alguma
		// subclasse concreta daclasse piece

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();// variável auxiliar recebendo a chamada do método abstrato
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {// se minha matriz na linha i coluna j for verdadeira existe movimento possível,
								// então true
					return true;
				}
			}
		} // os dois for correm a matriz buscando uma posição verdadeira
		return false;// fora dos for, se não achou nada é false(a peça está travada)
	}

}
