package codility;

import java.util.Arrays;

/**
 * @author nitin
 * @Date 09-Oct-2021
 */
public class Spooktober2021 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {2, 3, 1, 3})); //5
		System.out.println(solution(new int[] {3, 7, 0, 5})); //9
		System.out.println(solution(new int[] {1, 1, 1, 1, 1})); //1
		System.out.println(solution(new int[] {3, 1, 10})); //11
		System.out.println(solution(new int[] {3, 1, 2})); //4
		System.out.println(solution(new int[] {4, 10, 7, 2, 7})); //16
		System.out.println(solution(new int[] {4, 10, 7, 2, 7, 10, 6})); //18
		System.out.println(solution(new int[] {4, 10, 7, 2, 7, 9, 6, 4})); //18
	}
	
	// https://app.codility.com/cert/view/certNV9MSM-2DF46U9ATUM3GBFV/details/
	// Thought it will do better as removed one iteration but it took a bit more time.
	public static int solution(int[] A) {
		int len = A.length;
		if(len == 1)
			return A[0];
		
		int result = A[0];

		int[] bkd = new int[len];
		bkd[len-1] = A[len-1];
		for(int i = len-1; i > 0; i--) {
			bkd[i-1] = A[i-1] + (bkd[i]/2);
		}

		result = Math.max(bkd[0], A[0]+bkd[1]/2);
		for(int i = 1; i < len-1; i++) {
			A[i] = A[i] + (A[i-1]/2);
			result = Math.max(result, Math.max(A[i]+bkd[i+1]/2, bkd[i]+A[i-1]/2));
		}

		result = Math.max(result, Math.max(A[len-1]+A[len-2]/2, bkd[len-1]+A[len-2]/2));
		return result;
	}
	
	// https://app.codility.com/cert/view/certS6MGRZ-KGEYMTMMU5SXJZBR/details/
	public static int solution3(int[] A) {
		int len = A.length;
		if(len == 1)
			return A[0];
		
		int result = A[0];

		int[] fwd = new int[len];
		int[] bkd = new int[len];
		fwd[0] = A[0];
		bkd[len-1] = A[len-1];

		for(int i = 1; i < len; i++) {
			fwd[i] = A[i] + (fwd[i-1]/2);
		}
		for(int i = len-1; i > 0; i--) {
			bkd[i-1] = A[i-1] + (bkd[i]/2);
		}

		result = Math.max(bkd[0], fwd[0]+bkd[1]/2);
		for(int i = 1; i < len-1; i++) {
			result = Math.max(result, Math.max(fwd[i]+bkd[i+1]/2, bkd[i]+fwd[i-1]/2));
		}
		result = Math.max(result, Math.max(fwd[len-1], bkd[len-1]+fwd[len-2]/2));
		return result ;
	}

	// https://app.codility.com/cert/view/certBX5QCW-R4SCB6R64M3UWNJN/details/
	public static int solution2(int[] A) {
		int len = A.length;
		int result = A[0];

		int[] fwd = Arrays.copyOf(A, len);
		int[] bkd = Arrays.copyOf(A, len);

		for(int i = 1; i < len; i++) {
			fwd[i] = fwd[i]+(fwd[i-1]/2);
		}
		for(int i = len-1; i > 0; i--) {
			bkd[i-1]+=(bkd[i]/2);
		}

		if(len > 1) {
			result = Math.max(bkd[0], fwd[0]+bkd[1]/2);
			for(int i = 1; i < len-1; i++) {
				result = Math.max(result, Math.max(fwd[i]+bkd[i+1]/2, bkd[i]+fwd[i-1]/2));
			}
			result = Math.max(result, Math.max(fwd[len-1], bkd[len-1]+fwd[len-2]/2));
		}
		return result ;
	}

	// https://app.codility.com/cert/view/certKWUBZQ-SRYV7WT9HWWD9ZQG/details/
	public static int solution1(int[] A) {
		int len = A.length;
		int result = A[0];
		int maxI = 0;
		int[] fwd = Arrays.copyOf(A, len);
		int[] bkd = Arrays.copyOf(A, len);

		for(int i = 1; i < len; i++) {
			if(fwd[i-1] > 1) {
				fwd[i] = fwd[i]+(fwd[i-1]/2);
				fwd[i-1]=fwd[i-1]%2;
			}
			if(result < fwd[i]) {
				result = fwd[i];
				maxI = i;
			}
		}
		fwd[maxI] = result;
		for(int i = maxI+1; i < len; i++) {
			fwd[i] = A[i];
		}
		for(int i = len-1; i > maxI; i--) {
			if(fwd[i] > 1) {
				fwd[i-1]+=(fwd[i]/2);
				fwd[i] = (fwd[i]%2);
			}
			if(result < fwd[i]) {
				result = fwd[i];
				maxI = i;
			}
		}

		int bkdResult = 0;
		for(int i = len-1; i > 0; i--) {
			if(bkd[i] > 1) {
				bkd[i-1]+=(bkd[i]/2);
				bkd[i] = (bkd[i]%2);
			}
			if(bkdResult < bkd[i-1]) {
				bkdResult = bkd[i-1];
				maxI = i-1;
			}
		}
		bkd[maxI] = bkdResult;
		for(int i = 0; i < maxI; i++) {
			bkd[i] = A[i];
		}
		for(int i = 0; i < maxI; i++) {
			if(bkd[i] > 1) {
				bkd[i+1]+=bkd[i]/2;
			}
			if(bkdResult < bkd[i+1]) {
				bkdResult = bkd[i+1];
			}
		}
		return  result > bkdResult ? result : bkdResult;
	}

}
