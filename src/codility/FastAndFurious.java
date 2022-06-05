package codility;

/**
 * @author nitin
 * @Date 04-Jul-2021
 */
public class FastAndFurious {
	
	public static void main(String[] args) {
//		int[] A = {2, 6, 7, 8, 12}; //9
//		int[] A = {1, 5, 9, 12};//7
		int[] A = {5,15};//0
//		int[] A = {1, 6, 8, 9, 11};//10
		System.out.println(solution(A));
	}
	
	// Golden Award(Best Performance) : https://app.codility.com/cert/view/cert9AC4RP-UBUKZ5MV4YU6UCQS/details/
	// Golden Award : https://app.codility.com/cert/view/certS9HGQR-XKEZ22ECW5ZJSNWV/details/
	// Golden Award : https://app.codility.com/cert/view/certEWUNDZ-H7KXWRSVWFHFTQBC/details/
	public static int solution(int[] A) {
		long result = Long.MAX_VALUE;
		int len = A.length;
		long[] leftSums = new long[len];
		long rightSums = 0l;
		
		for(int i = 1; i < len; i++) {
			leftSums[i] = ((long)i*(A[i]-A[i-1]))%Long.MAX_VALUE+leftSums[i-1];
		}
		
		for(int i = len-2; i >= 0; i--) {
			long cur = leftSums[i] + rightSums;
			if(result > cur) {
				result = cur;
			}
			rightSums = rightSums + (A[len-1] - A[i]);
		}
		return (int)(result%1000000007);
	}
	
	// Golden Award : https://app.codility.com/cert/view/cert32YKXZ-3CAFWP48P9XB5RC3/details/
	// https://app.codility.com/cert/view/certRUE8V9-3K5TBAZGBUZGJP99/details/
	//https://app.codility.com/cert/view/cert86JV88-63NGB8W93W7M6RXX/details/
	public static int solution1(int[] A) {
		long result = Long.MAX_VALUE;
		int limit = 1000000007;
		int len = A.length;
		long[] leftSums = new long[len];
		long[] rightSums = new long[len];
		
		for(int i = 1; i < len; i++) {
			leftSums[i] = (((long)i*(A[i]-A[i-1])%Long.MAX_VALUE)%Long.MAX_VALUE+leftSums[i-1])%Long.MAX_VALUE;
		}
		
		for(int i = len-2; i >= 0; i--) {
			rightSums[i] = (rightSums[i+1] + (A[len-1] - A[i]))%Long.MAX_VALUE;
		}
		
		for(int i = 0; i < len-1; i++) {
            long cur = leftSums[i] + rightSums[i+1];
			if(result > cur) {
				result = cur;
			}
		}
		return (int)(result%limit);
	}
}
