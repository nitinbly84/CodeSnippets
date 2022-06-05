package codility;

import java.util.Arrays;

/**
 * @author nitin
 * @Date 07-May-2021
 */
public class May4_2021 {
	
	public static void main(String[] args) {
//		int[] A = {2,3,3,4};
//		int[] A = {1,4,5,5};
		int[] A = {5,2,5,2};
//		int[] A = {1,5,5};
		
		System.out.println(solution(A, 8, 1));
	}

	// Golden Award : https://app.codility.com/cert/view/certN688M4-NKD8CGYDEJ23ZEJM/details/
	public static int solution(int[] A, int L, int R) {
		int result = 0;
		
		Arrays.sort(A);
		
		int lPos = Arrays.binarySearch(A, L);
		int rPos = Arrays.binarySearch(A, R);
		
		int len = A.length;
		
		if(lPos < 0) {
			lPos = -(lPos+1);
		}
		if(rPos < 0) {
			rPos = -(rPos+1);
		}
		
		while(--lPos >= 0) {
			if(A[lPos] < L) {
				result++;
				L = A[lPos];
				A[lPos] = -1;
			}
		}
		while(rPos < len) {
			if(A[rPos] > R) {
				result++;
				R = A[rPos];
				A[rPos] = -1;
			}
			rPos++;
		}
		
		return result;
	}
	
	// Golden Award : https://app.codility.com/cert/view/certW5D9NN-PWW8J2T7R6EEFVED/details/
	public static int solution1(int[] A, int L, int R) {
		int result = 0;
		
		Arrays.sort(A);
		
		int lPos = Arrays.binarySearch(A, L);
		int rPos = Arrays.binarySearch(A, R);
		
		int len = A.length;
		
		if(lPos < 0) {
			lPos = -(lPos+1);
			if(lPos == len)
				lPos--;
		}
		
		if(rPos < 0) {
			rPos = -(rPos+1);
			if(rPos == len)
				rPos--;
		}
		
		int prev = L;
		
		while(lPos >= 0) {
			if(A[lPos] < prev) {
				result++;
				prev = A[lPos];
				A[lPos] = -1;
			}
			lPos--;
		}
		
		prev = R;
		while(rPos < len) {
			if(A[rPos] > prev) {
				result++;
				prev = A[rPos];
				A[rPos] = -1;
			}
			rPos++;
		}
		
		return result;
		
	}
}
