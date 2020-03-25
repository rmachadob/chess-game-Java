package Chess.pieces;

import Boardgame.Board;
import Boardgame.Position;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);// repassando o construtor pra superclasse
		this.chessMatch=chessMatch;
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
	// quando a cor é diferente(peça adversaria)

	//metodo para testar se pode fazer a jogada Roque 
	//a torre está apta para Roque quando o total de movimentos dela for zero
	private boolean testRookCastling (Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount()==0;
	}
	
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

		//#special move castling (Roque)
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//#specialmove castling Kingside rook
			Position posT1 = new Position(position.getRow(), position.getColumn() +3);
		
			if(testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() +1);
				Position p2 = new Position(position.getRow(), position.getColumn() +2);
				
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					
					mat[position.getRow()][position.getColumn() +2] = true;
				}
			}
			
			//#specialmove castling Queenside rook - Roque grande
			Position posT2 = new Position(position.getRow(), position.getColumn() -4);
			
			if(testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() -1);
				Position p2 = new Position(position.getRow(), position.getColumn() -2);
				Position p3 = new Position(position.getRow(), position.getColumn() -3);
				
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					
					mat[position.getRow()][position.getColumn() -2] = true;
				}
			}
		}
		
		
		
		return mat;
	}// PERGUNTA: PORQUE SÓ ME OBRIGA A IMPLEMENTAR O MÉTODO ABSTRATO??

}
