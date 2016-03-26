import java.util.Arrays;
/**
 * Created by Michael on 3/22/16.
 */
public class Queens {

	private static final int gameSize = 30; // SET GAMESIZE
	private static int[][] gameBoard = new int[gameSize][gameSize];

	// MAIN DRIVER
	public static void main(String args[]) {
		clear();
		showBoard(); 
		System.out.println();
		if(!playQueens(0)){
			System.out.println("No Answer");
		};
	}

	// RECURSIVE PROGRAM
	private static boolean playQueens(int QueenPiece){

		// If the column is at 8, it means 0-7 filled
		if (QueenPiece >= gameSize){
			System.out.println("Solved.");
			showBoard();
			return true;
		}
		else{ // i is the row, Queen tracked by column (0-7)
			for (int i = 0; i < gameSize; i++){
				if (DiagnalIsValid(i, QueenPiece) && rowIsValid(i) && colIsValid(QueenPiece)){
					gameBoard[i][QueenPiece] = 1; // 1 will be the queen
					if (playQueens(QueenPiece + 1)){ // Recursion happens here at next queen
						return true;
					}
					else{
						// HERE IS THE BACKTRACKING
						gameBoard[i][QueenPiece]=0;
					}		
				}
			}
			// FALSE HERE IF THE RECURSION FAILS OR NO SOLUTION
			return false;
		}
	} 	

	// DISPLAY BOARD
	private static void showBoard(){
		String[][] board = new String[gameSize][gameSize];
		System.out.println("_________________");
		for(int i = 0; i< gameSize; i++){
			for (int k = 0; k<gameSize; k++){
				if (k == 0){
					System.out.print("|");
				}

				if (gameBoard[i][k] == 0){
					System.out.print("_");
				}
				else
					System.out.print("Q");
				System.out.print("|");
			}
			System.out.println();
		}
	}

	// CLEAR BOARD
	private static void clear(){
		for(int[] row: gameBoard){
			Arrays.fill(row, 0);
		}	 
	}

	// CHECK IF ROW IS VALID
	private static boolean rowIsValid(int Row){
		for (int i = 0; i < gameSize; i++){
			if (gameBoard[Row][i] == 1){
				return false;
			}
		}
		return true;    	
	}

	// CHECK IF COLUMN IS VALID
	private static boolean colIsValid(int Column){
		for (int i =0;i< gameSize;i++){
			if(gameBoard[i][Column] ==1) return false;
		}
		return true;
	}

	// CHECK IF DIAGNAL IS VALID
	private static boolean DiagnalIsValid(int Row, int Column){
		int colTrack = Column-1; // Working by column, working left to right
		int rowTrackUp = Row-1; // Need to check both up
		int rowTrackDown = Row+1; // And down

		// CHECK DIAGNAL UP
		while (colTrack >=0 && rowTrackUp >=0){ // 
			if (gameBoard[rowTrackUp][colTrack] == 1){ // If we hit a queen
				return false; // Bad
			}
			colTrack--; // Move to next column <--
			rowTrackUp--; // Move to next row ^
		}

		// CHECK DIAGNAL DOWN
		colTrack = Column-1; // Reset the column to the column to the left
		while (colTrack >=0 && rowTrackDown <= (gameSize-1)){
			if (gameBoard[rowTrackDown][colTrack] == 1){
				return false;
			}
			colTrack--; // Move to next column <--
			rowTrackDown++; // Move to next row ^
		}
		return true;   
	}
}
