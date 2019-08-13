package Application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();// lista q recebe as peças capturadas

		while (true) {// pra rodar indefinido, ainda n tem lógica de cheque {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);// chessMatch já vem com tudo, turnos e current player
				System.out.println();// pula uma linha antes de pedir o source position
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);// sobrecarga desse printBoard, passando também os
																		// movimentos possíveis

				System.out.println();// salta uma linha
				System.out.print("Target: ");// pede o destino
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				} // sempre q eu mandar executar o movimento de xadrez, testa pra ver se
					// capturou alguma peça e adiciona na lista

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();// assim aguarda o usuário dar enter pra seguir
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}