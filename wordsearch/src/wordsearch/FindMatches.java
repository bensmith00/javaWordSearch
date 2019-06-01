package wordsearch;

public class FindMatches {

	// check whether given cell (row, col) is a valid 
	// cell or not. 
	static boolean isvalid(int row, int col, int prevRow, int prevCol, char [][] letterMatrix) { 
		// return true if row number and column number 
		// is in range 
		return (row >= 0) && (row < letterMatrix.length) && 
			(col >= 0) && (col < letterMatrix[0].length) && 
			!(row== prevRow && col == prevCol); 
	} 

	// A utility function to do DFS for a 2D boolean 
	// matrix. It only considers the 8 neighbors as 
	// adjacent vertices 
	public static void DFS(char[][] letterMatrix, int row, int col, int prevRow, int prevCol, char[] word, String path, int index) { 
		
		// These arrays are used to get row and column 
		// numbers of 8 neighbors of a given cell 
		int rowNum[] = {-1, 0, 0, 1}; 
		int colNum[] = {0, -1, 1, 0}; 
		
		// return if current character doesn't match with 
		// the next character in the word 
		if (index > word.length - 1 || letterMatrix[row][col] != word[index]) {
			return; 
		}

		// current character matches with the last character 
		// in the word 
		if(index == word.length 
                || letterMatrix.length <= row 
                || row < 0 || col < 0 
                || letterMatrix[row].length <= col
                || letterMatrix[row][col] != word[index]){
            return;
        }

		// Recur for all connected neighbors 
		for (int k = 0; k < 4; ++k) 
			if (FindMatches.isvalid(row + rowNum[k], col + colNum[k], 
						prevRow, prevCol, letterMatrix)) 

				DFS(letterMatrix, row + rowNum[k], col + colNum[k], 
					row, col, word, path, index+1); 
	}
}