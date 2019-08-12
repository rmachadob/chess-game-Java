package Chess.pieces;

import Boardgame.Board;
import Boardgame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);// repassando o construtor pra superclasse

	}

	@Override
	public String toString() {
		return "K";
	}

	// método auxiliar pra falar se o rei pode mover para uma determinada posição
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);// pra saber se o meu rei pode se mover pra essa posição,
																// tem q pegar a peça p da posição
		return p == null || p.getColor() != getColor();
	}// meu rei vai poder mover quando a casa for vazia (ou seja p==null) ou então
		// quando a cor é diferente(peça adver)

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above(linha -1)
		p.setValues(position.getRow() - 1, position.getColumn());// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		} // nesse if: se a posição existe e a peça pode se mmover, a posição do vetor
			// recebe true

		// below (linha +1)
		p.setValues(position.getRow() + 1, position.getColumn());// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left (coluna -1)
		p.setValues(position.getRow(), position.getColumn() - 1);// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right (coluna +1)
		p.setValues(position.getRow(), position.getColumn() + 1);// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// NW NorthWest (linha -1, coluna -1)
		p.setValues(position.getRow() - 1, position.getColumn() - 1);// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// NE NorthEast(linha -1, coluna +1)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// SW SouthWest(linha +1, coluna -1)
		p.setValues(position.getRow() + 1, position.getColumn() - 1);// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// SE SouthEast(linha +1, coluna +1)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);// essa linha é a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}// PERGUNTA: PORQUE SÓ ME OBRIGA A IMPLEMENTAR O MÉTODO ABSTRATO??

}
