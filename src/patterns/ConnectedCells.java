package patterns;

/**
 * Below is the code to find the maximum number of connected 1's in the given matrix.
 * @author HackerRank
 *
 */
public class ConnectedCells {
	
	public static void main(String[] args) {
//		int[][] matrix = {{0,0,0,1,1,0,0},{0,1,0,0,1,1,0},{1,1,0,1,0,0,1},{0,0,0,0,0,1,0},{1,1,0,0,0,0,0},{0,0,0,1,0,0,0}};
		int[][] matrix = {{0,0,0,1,1,0,0},{0,1,0,0,1,1,0}};
		int rows = matrix.length;
		int columns = matrix[0].length;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println("Biggest connected region possible " + getBiggestRegion(matrix));
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	private static int getMaxRegion(int[][] matrix, int row, int column) {
		if(row < 0 || column < 0 || row >= matrix.length || column >= matrix[row].length) {
			return 0;
		}
		if(matrix[row][column] == 0) {
			return 0;
		}
		matrix[row][column] = 0;
		int size = 1;
		for(int r = row-1; r <= row+1; r++) {
			for(int c = column-1; c <= column+1; c++) {
				if(r != row || c != column) {
					size += getMaxRegion(matrix, r, c);
				}
			}
		}
		return size;
	}
	
	public static int getBiggestRegion(int[][] matrix) {
		int maxRegion = 0;
		for(int row = 0; row < matrix.length; row++) {
			for(int column = 0; column < matrix[row].length; column++) {
				if(matrix[row][column] == 1) {
					int size = getMaxRegion(matrix, row, column);
					maxRegion = Math.max(size, maxRegion);
				}
			}
		}
		return maxRegion;
	}

}
