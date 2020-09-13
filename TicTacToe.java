import java.util.*;

class PlayingTicTacToe {

	static Scanner sc = new Scanner(System.in);
	String firstPlay = "";
	static String player;
	static String computer;
	// creating the board as an 3*3 array
	String[][] board = new String[3][3];

	// function to re-set the board.
	public void settingBoard() {

		System.out.println("board reseted!!");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = " ";
			}

		}

	}

	// function to print the board
	public void printBoard() {
		System.out.println("/---|---|---\\");
		System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
		System.out.println("/---|---|---\\");
	}

	// function to decide get toss number
	public static int getTossNumber() {
		Random random = new Random();
		return random.nextInt(2) + 1;
	}

	// function to decide get player symbol
	public static int getPlayerSymbol() {
		Random random = new Random();
		return random.nextInt(2) + 1;
	}

	// calling a function to decide who will play first and there symbol choice.
	void whoWinToss() {
		int toss = getTossNumber();
		int symbol = getPlayerSymbol();
		if (toss == 1) {

			if (symbol == 1) {
				player = "X";
				computer = "O";
			} else {
				player = "O";
				computer = "X";
			}
			System.out.println("computer won the toss and choose letter: " + computer);

		} else {
			System.out.println("you won toss");

			System.out.println("Enter 1 for 'X' and 2 for 'O' ");

			int yourSymbol = sc.nextInt();

			if (yourSymbol == 1) {
				player = "X";
				computer = "O";

			} else {
				player = "O";
				computer = "X";
			}
			System.out.println("you choose letter: " + player);

		}

	}
}

class TicTacToe {
	public static void main(String[] args) {

		// creating an object of PlayingTTT class.
		PlayingTicTacToe play = new PlayingTicTacToe();

		// calling the function to set the re-set the board.
		play.settingBoard();
		play.printBoard();
		play.whoWinToss();
	}
}
