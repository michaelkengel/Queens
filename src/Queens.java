import java.util.Arrays;
/**
 * Created by Michael on 3/22/16.
 */
public class Queens {

	// SET GAMESIZE
	private static final int gameSize = 8; 
	// Gameboard array
	private static int[][] gameBoard = new int[gameSize][gameSize];

	// MAIN DRIVER
	public static void main(String args[]) {
		// Clear the board
		clear();
		// Show empty board
		showBoard(); 
		System.out.println();
		
		// Call recursive solving algorithm
		if(!playQueens(0)){
			System.out.println("No Answer");
		};
	}

	// RECURSIVE PROGRAM
	private static boolean playQueens(int QueenPiece){

		// If the queen is at 8, it means 0-7 filled 
		if (QueenPiece >= gameSize){
			System.out.println("Solved.");
			showBoard();
			return true;
		}
		else{ // i is the row, Queen tracked by column (0-7)
			for (int i = 0; i < gameSize; i++){ // For all rows of this Queen(Column)
				if (DiagonalIsValid(i, QueenPiece) && rowIsValid(i) && colIsValid(QueenPiece)){ // If the space is valid
					gameBoard[i][QueenPiece] = 1; // 1 will be the queen
					if (playQueens(QueenPiece + 1)){ // Run this function for the next queen RECURSE
						return true; // Will return the function true. Not 100% needed, could remove
					}
					else{
						// HERE IS THE BACKTRACKING IF THE ABOVE FAILS
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
		for (int i = 0; i < gameSize; i++){ // For columns of this row
			if (gameBoard[Row][i] == 1){ // If there's queen false
				return false;
			}
		}
		return true; // else it is valid   	
	}

	// CHECK IF COLUMN IS VALID
	private static boolean colIsValid(int Column){
		for (int i =0;i< gameSize;i++){ // For all the rows of this column
			if(gameBoard[i][Column] ==1) return false;  // If there's queen false
		}
		return true; // else it is valid
	}

	// CHECK IF DIAGNAL IS VALID
	private static boolean DiagonalIsValid(int Row, int Column){
		int colTrack = Column-1; // Working by column, working left to right
		int rowTrackUp = Row-1; // Need to check both up
		int rowTrackDown = Row+1; // And down

		// CHECK DIAGONAL UP
		while (colTrack >=0 && rowTrackUp >=0){ // 
			if (gameBoard[rowTrackUp][colTrack] == 1){ // If we hit a queen
				return false; // send a false
			}
			colTrack--; // Move to next column <--
			rowTrackUp--; // Move to next row ^
		}

		// CHECK DIAGONAL DOWN
		colTrack = Column-1; // Reset the column to the column to the left
		while (colTrack >=0 && rowTrackDown <= (gameSize-1)){
			if (gameBoard[rowTrackDown][colTrack] == 1){ // If there is a queen
				return false;
			}
			colTrack--; // Move to next column <--
			rowTrackDown++; // Move to next row ^
		}
		return true;   
	}
}
