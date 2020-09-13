import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class PlayingTicTacToe {

	// function to re-set the board.
	public static void settingBoard(String[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = " ";
			}
		}
	}

	// function to create a random toss
	public static boolean decidingToss() {
		Random r = new Random();
		return r.nextBoolean();
	}

	// calling a function to decide who will play first and there symbol choice.
	public static String[] choosingSymbol(boolean toss, Scanner sc) {
		String[] arr = new String[2];
		if (toss) {
			System.out.println("Enter a symbol");
			arr[0] = sc.nextLine();
			if (arr[0] == "X") {
				arr[1] = "O";
			} else {
				arr[1] = "X";
			}
		} else {
			if (decidingToss()) {
				arr[0] = "X";
				arr[1] = "O";
			} else {
				arr[0] = "O";
				arr[1] = "X";
			}
		}
		return arr;
	}

	private static int checkingIfPresent(Scanner sc, int index, List<Integer> occupiedPosition) {
		if (occupiedPosition.contains(index)) {
			System.out.println("enter a different position " + index + " is already present");
			index = sc.nextInt();
			checkingIfPresent(sc, index, occupiedPosition);
		}
		return index;
	}

	public static String[][] settingSymbol(String[][] board, String symbol, int index) {
		index -= 1;
		int x = (int) Math.floor(index / 3);
		int y = index % 3;
		board[x][y] = symbol;
		return board;

	}

	public static void playingTheGame(String[][] board) {

		Scanner sc = new Scanner(System.in);

		List<Integer> PlayerPosition = new ArrayList<>();

		List<Integer> occupiedPosition = new ArrayList<>();

		String[] SymbolArray = new String[2];

		// calling the function to set the re-set the board.
		settingBoard(board);

		// calling a function to decide who will play first and there symbol choice.
		boolean toss = decidingToss();

		// setting winning condition for both opponents.
		boolean playerResult = false;

		// calling a function to choose the player and cpu symbol.
		SymbolArray = choosingSymbol(toss, sc);
		String playerSymbol = SymbolArray[0];

		// playing till either of the competitor win or tie.
		do {
			if (toss) {
				// calling a function to display the board.
				printBoard(board);
				System.out.println("enter a the position you want to place your symbol, between 1-9");
				int index = sc.nextInt();
				index = checkingIfPresent(sc, index, occupiedPosition);
				occupiedPosition.add(index);
				PlayerPosition.add(index);
				board = settingSymbol(board, playerSymbol, index);

				toss = false;
			} else {
				toss = true;
			}

		} while (playerResult == false);

		printBoard(board);

		sc.close();
	}

	// function to print the board
	public static void printBoard(String board[][]) {
		System.out.println("/---|---|---\\");
		System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
		System.out.println("/---|---|---\\");
	}

}

class TicTacToe {
	public static void main(String[] args) {

		// creating the board as an 3*3 array
		String[][] board = new String[3][3];

		PlayingTicTacToe.playingTheGame(board);

	}
}
