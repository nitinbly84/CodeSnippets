package dataStructures;

/**
 * Below code generates the matrix in rounded sequence.
 * @author Nitin
 *
 */
public class RoundMatrix {

	static int[][] mat;
	public static void main(String[] args) {
		create(5);
		show();
	}

	static void create(int num) {

		int row = 0;
		int col = 0;
		int i = 0, j = 0;
		int point = num;
		int count = 1;
		int fin = num*num;
		mat = new int[num][num];

		while(fin > 0) {
			//Right
			for(int k = row; k < point; k++) {
				mat[row][k] = count;
				count++;
				j = k;
				fin--;
			}

			//Down
			for(int k = i+1; k < point; k++) {
				mat[k][j] = count;
				count++;
				i = k;
				fin--;
			}

			//Left
			for(int k = j-1; k >= col; k--) {
				mat[i][k] = count;
				count++;
				j = k;
				fin--;
			}

			//Top
			for(int k = i-1; k > row; k--) {
				mat[k][j] = count;
				count++;
				i = k;
				fin--;
			}
			row++;
			col++;
			point--;
		}

	}

	static void show() {
		int n = mat.length;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(mat[i][j]);
				if(mat[i][j] >= 100)
					System.out.print(" ");
				if(mat[i][j] < 10)
					System.out.print("   ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
}
