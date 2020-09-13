import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class PlayingTTT {

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

	public static boolean checkForWin(List<Integer> board) {
		if (checkRowsForWin(board) || checkColumnsForWin(board) || checkDiagonalsForWin(board)) {
			return true;
		}
		return false;
	}

	public static boolean checkRowsForWin(List<Integer> board) {
		List<Integer> row0 = new ArrayList<>();
		row0.add(1);
		row0.add(2);
		row0.add(3);
		List<Integer> row1 = new ArrayList<>();
		row1.add(4);
		row1.add(5);
		row1.add(6);
		List<Integer> row2 = new ArrayList<>();
		row2.add(7);
		row2.add(8);
		row2.add(9);
		if (board.containsAll(row0) || board.containsAll(row1) || board.containsAll(row2)) {
			return true;
		}
		return false;
	}

	public static boolean checkColumnsForWin(List<Integer> board) {
		List<Integer> col0 = new ArrayList<>();
		col0.add(1);
		col0.add(4);
		col0.add(7);
		List<Integer> col1 = new ArrayList<>();
		col1.add(2);
		col1.add(5);
		col1.add(8);
		List<Integer> col2 = new ArrayList<>();
		col2.add(3);
		col2.add(6);
		col2.add(9);
		if (board.containsAll(col0) || board.containsAll(col1) || board.containsAll(col2)) {
			return true;
		}
		return false;
	}

	public static boolean checkDiagonalsForWin(List<Integer> board) {
		List<Integer> dig0 = new ArrayList<>();
		dig0.add(1);
		dig0.add(5);
		dig0.add(9);
		List<Integer> dig1 = new ArrayList<>();
		dig1.add(4);
		dig1.add(5);
		dig1.add(6);
		if (board.containsAll(dig0) || board.containsAll(dig1)) {
			return true;
		}
		return false;
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
		boolean cpuResult = false;

		// calling a function to choose the player and cpu symbol.
		SymbolArray = choosingSymbol(toss, sc);
		String playerSymbol = SymbolArray[0];
		int checkIndex = 0;
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
				playerResult = checkForWin(PlayerPosition);
				if (playerResult) {
					System.out.println("Player Wins");
				}
				checkIndex++;
				toss = false;
			} else {
				toss = true;
			}

			if (checkForWin(PlayerPosition) != true && checkIndex == 5) {
				System.out.println("draw");
				break;
			}

		} while (playerResult == false && cpuResult == false);

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

		PlayingTTT.playingTheGame(board);

	}
}
