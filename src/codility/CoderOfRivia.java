package codility;

/**
 * @author nitin
 * @Date 08-Aug-2021
 */
public class CoderOfRivia {

	public static void main(String[] args) {

//				int[] result = solution(new int[] {0, 2, 3, 4, 1, 1, 1, 3, 1}); //1, 2, 3, 4, 1, 1, 1, 3, 2
//				int[] result = solution(new int[] {1, 1, 1, 2, 2, 1, 2, 2, 1}); //1, 1, 3, 2, 2, 1, 2, 2, 1
//		int[] result = solution(new int[] {3, 2, 0, 0, 0, 0, 0, 3, 2}); //3, 2, 0, 2, 0, 3, 0, 3, 2
		int[] result = solution(new int[] {1, 4, 2, 1, 4, 5, 4, 4, 4}); //6, 4, 2, 2, 4, 6, 4, 4, 4
		for(int i : result) {
			System.out.print(i+", ");
		}
	}

	// Golden Award : https://app.codility.com/cert/view/cert3QGGYF-6RBP626E59YZWQ4W/details/
	// https://app.codility.com/cert/view/cert2PU6AW-JR82YC6223YA4BB3/details/
	public static int[] solution(int[] A) {
		int len = A.length;
		int[] result = new int[len];

		int[][] matrix = new int[4][4];
		int[] colSum = new int[3];
		int rows = 0;
		int cols = 0;
		int sum = 0;
		int max = 0;
		for(int i : A) {
			if(cols > 2) {
				matrix[rows][cols] = sum;
				cols = 0;
				rows++;
				sum = 0;
			}

			colSum[cols]+=i;
			matrix[rows][cols++] = i;
			sum+=i;
		}
		matrix[rows+1][0] = colSum[0]; 
		matrix[rows+1][1] = colSum[1]; 
		matrix[rows+1][2] = colSum[2];
		matrix[rows][cols] = sum;
		max = Math.max(matrix[0][3], Math.max(matrix[1][3], Math.max(matrix[2][3], Math.max(matrix[3][0], Math.max(matrix[3][1], matrix[3][2])))));

		sum = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(matrix[i][3] < max && matrix[3][j] < max) {
					int maxVal = Math.max(matrix[i][3], matrix[3][j]);
					int diff = max-maxVal;
					matrix[i][j]+=diff;
					matrix[i][3]+=diff;
					matrix[3][j]+=diff;
				}
				result[sum++] = matrix[i][j];
			}
		}
		return result;
	}

}
