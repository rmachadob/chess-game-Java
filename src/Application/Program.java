package Application;

import java.util.InputMismatchException;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {// pra rodar indefinido, ainda n tem l�gica de cheque {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());// esse getPieces j� devolve a matriz

				System.out.println();// pula uma linha antes de pedir o source position
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();// salta uma linha
				System.out.print("Target: ");// pede o destino

				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();// assim aguarda o usu�rio dar enter pra seguir
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}