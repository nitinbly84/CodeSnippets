package codility;

/**
 * @author nitin
 * @Date 29-Jan-2023
 */
// Golden Award : https://app.codility.com/cert/view/certZCGUGZ-JZ2T44U8SXGCF6DK/details/
// Golden Award : https://app.codility.com/cert/view/certKYFHBW-V47SW9EB7KXZ3A8P/details/
public class YearOfTheRabbit {
	
	public static void main(String[] args) {
		YearOfTheRabbit yearOfTheRabbit = new YearOfTheRabbit();
		int[] A = {1, 3, 5, 2, 8, 7};
		int[] B = {7, 1, 9, 8, 5, 7};
		System.out.println(yearOfTheRabbit.solution(A, B));
	}
	
	public int solution(int[] A, int[] B) {
		int len = B.length;
		int rotate = 0;
		while(rotate < len) {
			int diff = compare(A, B, len);
			if(diff == len)
				return rotate;
			if(rotate == len-1)
				return -1;
			rotate(B, len);
			rotate++;
		}
		return -1;
	}
	
	private int compare(int[] A, int[] B, int len) {
		int count = 0;
		for(int i = 0; i < len; i++) {
			if(A[i] != B[i])
				count++;
		}
		return count;
	}
	
	private void rotate(int[] B, int len) {
		int temp = B[0];
		for(int i = 1; i < len; i++) {
			int prev = B[i];
			B[i] = temp;
			temp = prev;
		}
		B[0] = temp;
	}

}
