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

	/**
	 * function to chose a symbol for the computer or the user.
	 */
	public static String[] choosingSymbol(boolean toss, Scanner sc, String[] symbolArray) {
		if (toss) {
			System.out.println("Enter a symbol 'X' or 'O'");
			String symbol = sc.nextLine();
			if (symbol.equals("X")) {
				symbolArray[0] = "X";
				symbolArray[1] = "O";
			} else {
				symbolArray[0] = "O";
				symbolArray[1] = "X";
			}
		} else {
			if (decidingToss()) {
				symbolArray[0] = "X";
				symbolArray[1] = "O";
				System.out.println("computer choose: "+symbolArray[1]);
			} else {
				symbolArray[0] = "O";
				symbolArray[1] = "X";
				System.out.println("computer choose: "+symbolArray[1]);

			}
		}
		return symbolArray;
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

	public static boolean checkForDraw(String[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == " ") {
					return false;
				}
			}
		}
		return true;
	}

	
	
	
	 /**
     * function to get the cell index from the computer.
     * 
     * @param occupiedPosition - index's of the occupiedPosition in the game board.
     * @param playerPosition   - index's of the position used by the user.
     * @param position         - index's of the position used by the CPU.
     */
    private static int cpuMove(List<Integer> occupiedPosition, List<Integer> PlayerPosition, List<Integer> position) {

        Random r = new Random();
        int index = r.nextInt(9) + 1;
        while (occupiedPosition.contains(index)) {
            index = r.nextInt(9) + 1;
        }

        int firstIndex = index;

        index = possibleBestPosition(position, occupiedPosition, index);
        if (firstIndex != index) {
            return index;
        }

        return index;
    }
	
	  /**
     * function to get the best possible index where the symbol can be placed on the
     * board to win or stop the opponent from winning.
     * 
     * @param position         - list of index from which we have to compare.
     * @param occupiedPosition - index's of the occupiedPosition in the game board.
     * @param index            - taking index for comparison.
     */
    private static int possibleBestPosition(List<Integer> position, List<Integer> occupiedPosition, int index) {
        int[][] winning = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 },
                { 3, 5, 7 } };

        // variables to calculate index and count the number of matching element.
        int k = 0;
        List<Integer> unMatched = new ArrayList<>();

        while (k < 8) {
            int matchCount = 0;
            unMatched.clear();

            for (int i = 0; i < winning[k].length; i++) {
                if (occupiedPosition.contains(winning[k][i])) {
                    if (position.contains(winning[k][i])) {
                        matchCount++;
                    }
                } else {
                    unMatched.add(winning[k][i]);
                }
            }

            if (matchCount == 2 && unMatched.size() == 1) {
                index = unMatched.get(0);
                break;
            }
            k++;
        }
        return index;
    }


	public static void playingTheGame(String[][] board) {

		Scanner sc = new Scanner(System.in);

		List<Integer> PlayerPosition = new ArrayList<>();
		List<Integer> cpuPosition = new ArrayList<>();
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
		SymbolArray = choosingSymbol(toss, sc, SymbolArray);
		String playerSymbol = SymbolArray[0];
		String cpuSymbol = SymbolArray[1];

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
				toss = false;
			} else {
				// calling a function to get cell index from the cpu.
				
                int index = cpuMove(occupiedPosition, PlayerPosition, cpuPosition);

				cpuPosition.add(index);
				occupiedPosition.add(index);

				// calling a function for setting the symbol at the given index.
				board = settingSymbol(board, cpuSymbol, index);

				// calling a function to check for the winning condition.
				cpuResult = checkForWin(cpuPosition);
				if (cpuResult) {
					System.out.println("cpu Wins");
					break;
				}
				toss = true;
			}

			// checking for draw.
			if (checkForDraw(board)) {
				System.out.println("It's a draw");
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

		PlayingTicTacToe.playingTheGame(board);

	}
}
