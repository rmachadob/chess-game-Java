package Chess.pieces;

import Boardgame.Board;
import Boardgame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);//

	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);// s� pra instanciar com algum valor

		// above - marcar de verdadeiro acima da pe�a
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		} // esse while vai marcando como true a posi��o ent�o a pe�a pode ir. Ele roda
			// todas as casas VAZIAS
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		} // Esse � pra testar se a pe�a � inimiga, a� tbm marca a posi��o como true

		// left - l�gica do de cima mas decrementando as colunas e o que recebe true � a
		// coluna
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right - l�gica do de cima mas INCREMENTANDO as colunas
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below -l�gica do de cima s� que a linha � +1 ao inv�s de -1
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}// esse -1 � a linha acima da posi��o da pe�a. while: enquanto a posi��o p
		// existir e n�o
		// tiver uma pe�a l�, retorna true

}
