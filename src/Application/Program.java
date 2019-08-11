package Application;

import java.util.Scanner;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {// pra rodar indefinido, ainda n tem lógica de cheque {
			UI.printBoard(chessMatch.getPieces());// esse getPieces já devolve a matriz

			System.out.println();// pula uma linha antes de pedir o source position
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);

			System.out.println();// salta uma linha
			System.out.print("Target: ");// pede o destino
			ChessPosition target = UI.readChessPosition(sc);

			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

		}
	}
}