import java.util.Random;

class PlayingTicTacToe {

	String firstPlay = "";

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

	// function to create a random toss
	public boolean decidingToss() {
		final Random r = new Random();
		return r.nextBoolean();
	}

	// calling a function to decide who will play first.
	public void whoWinToss() {

		if (decidingToss()) {
			firstPlay = firstPlay + "player";
		} else {
			firstPlay = firstPlay + "computer";
		}
		System.out.println(firstPlay + ": win the toss");
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