package Application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	// c�digos especiais das cores que v�o ser impressas no console (cores das
	// letras)
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
//abaixo as cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);// recebendo o char da posi��o 0
			int row = Integer.parseInt(s.substring(1));// recebendo e convertendo em int a posi��o 1
			return new ChessPosition(column, row);// a� retorna o q recebeu
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading chess position. Valid values are from a1 to h8");
		} // pra evitar problemas com formato coloca tudo num bloco try catch e lan�a a
			// exce��o
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());// imprime o tabuleiro
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn : " + chessMatch.getTurn());// turnos
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());// jogador atual
		if(chessMatch.getCheck()) {//chama o m�todo do cheque e testa
			System.out.println("CHECK!");
		}
	}

	// esse metodo abaixo � pra imprimir o tabuleiro sem os movimentos poss�veis
	// (n�o t� recebendo possible moves como argumento q nem o de baixo), por isso
	// passa o valor
	// falso na hora de imprimir a pe�a
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);

			}
			System.out.println();// quebra a linha

		}

		System.out.println("  a b c d e f g h");
	}

	// � parecido com o de cima, mas imprime o tabuleiro considerando os movimentos
	// poss�veis. Por isso no printPiece eu passo possibleMoves e as posi��es ij.
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
//com o possible moves nesse print piece 
			}
			System.out.println();// quebra a linha

		}

		System.out.println("  a b c d e f g h");
	}

	// esse boolean background � uma vari�vel pra indicar se deve ou n�o colorir o
	// board
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {// se vari�vel background verdadeira imprime azul no board
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);// tem que resetar a cor depois de imprimir o tracinho tbm
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);// o ANSI_RESET limpa as cores depois de imprimir
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		// filtragem de lista usando express�o lambda. o "x-> x.getColor" � um predicado
		// que vai pegar um elemento da lista e a� verifica a condi��o desse elemento(ou
		// seja, filtrando da lista toda pe�a branca).
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());
		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));// macete padr�o pra imprimir Array de valores
		System.out.print(ANSI_RESET);

		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));// macete padr�o pra imprimir Array de valores
		System.out.print(ANSI_RESET);

	}
}
