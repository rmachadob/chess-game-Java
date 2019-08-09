package Application;

import Chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);

			}
			System.out.println();//quebra a linha

		}

		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");//se for nulo (sem pe�a) imprime tracinho
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");//pras pe�as n�o ficarem grudadas
	}

}
