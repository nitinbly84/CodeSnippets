package codility;

public class Cobaltum {

	public static void main(String[] args) {
		//														int[] A = {7,8,10};
		//														int[] B = {6,7,9};
		//														int[] A = {1,1};
		//														int[] B = {0,4};
		//														int[] A = {3,1,2,4,5};
		//														int[] B = {0,4,5,6,8};
		//		int[] A = {5, 3, 7, 10, 9};
		//		int[] B = {1, 6, 8, 8, 11};
		//								int[] A = {5, 3, 8, 7, 10};
		//								int[] B = {1, 6, 8, 9, 9};
		int[] A = {5, 3, 7, 7, 10};
		int[] B = {1, 6, 6, 9, 9};
		//		int[] A = {5, 3, 7, 7, 10};
		//		int[] B = {1, 6, 6, 9, 7};
		//						int[] A = {1,3,7,10,9};
		//						int[] B = {5,6,8,8,11};
		//				int[] A = {2,1,7,6};
		//				int[] B = {0,3,5,8};
		//		int[] A = {3,2};
		//		int[] B = {3,4};
		//		int[] A = {5,-3,6,4,8};
		//		int[] B = {2,6,-5,1,0};

		System.out.println(solution(A, B));
	}

	public static int solution(int[] A, int[] B) {
		int len = A.length;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		boolean[] swapped = new boolean[len];

		if(len < 2)
			return -1;
		if( len == 2) {
			if(A[0] >= A[1] || B[0] >= B[1]) {
				if(A[0] < B[1] && B[0] < A[1])
					return 1;
				else
					return -1;
			}
			return 0;
		}
		for(int i = 0; i < len-1; i++) {
			if(i <= len-2 && (A[i] >= A[i+1] || B[i] >= B[i+1])) {
				if(A[i] < B[i+1] && B[i] < A[i+1]) {
						count1++;
					swapped[i] = true;
				}
				else
					count1 = Integer.MIN_VALUE;
			}
			if((i <= len-2 && i > 0)&& ((A[i] <= A[i-1] || A[i] >= A[i+1]) || (B[i] <= B[i-1] || B[i] >= B[i+1]))) {
				if(A[i] < B[i+1] && A[i] > B[i-1] && B[i] < A[i+1] && B[i] > A[i-1]) {
						count2++;
					swapped[i] = true;
				}
				else
					count2 = Integer.MIN_VALUE;
			}
			if(i > 0 && (A[i] <= A[i-1] || B[i] <= B[i-1])) {
				if(A[i] > B[i-1] && B[i] > A[i-1]) {
						count3++;
					swapped[i] = true;
				}
				else
					count3 = Integer.MIN_VALUE;
			}
		}

		if(count1 > 0 && count2 > 0 && count3 > 0 && count1 <= count2 && count1 <= count3)
			return count1;
		else if(count1 > 0 && count2 > 0 && count3 > 0 && count2 <= count1 && count2 <= count3)
			return count2;
		else if(count1 > 0 && count2 > 0 && count3 > 0 && count3 <= count1 && count3 <= count2)
			return count3;
		else if(count1 >= 0 && count2 < 0 && count3 > 0)
			return count1 < count3 ? count1 : count3;
		else if(count2 > 0 && count1 < 0 && count3 > 0)
			return count3 < count2 ? count3 : count2;
		else if(count1 > 0 && count2 > 0 && count3 < 0)
			return count1 < count2 ? count1 : count2;
		else if(count1 >= 0 && count2 < 0 && count3 < 0) 
			return count1;
		else if(count2 >= 0 && count1 < 0 && count3 < 0)
			return count2;
		else if(count3 >= 0 && count1 < 0 && count2 < 0)
			return count1;
		else if(count1 < 0 && count2 < 0 && count3 < 0)
			return -1;
		else
			return 0;


	}

	public static int solution1(int[] A, int[] B) {

		int len = A.length;
		int prevA = A[0];
		int prevB = B[0];
		int count = 0;

		if(len < 2)
			return 0;
		if(len == 2) {
			if(A[0] >= A[1] || B[0] >= B[1]) {
				if(A[0] < B[1] && B[0] < A[1]) {
					return 1;
				} 			
			} else if (A[0] < A[1] && B[0] < B[1]) {
				return 0;
			}
			return -1;
		}
		for(int i = 1; i < len; i++) {
			if(prevA >= A[i] || prevB >= B[i]) {
				if(i >= 1 && i < len-1 && prevA < A[i+1] && prevB < B[i+1] && A[i] < B[i+1] && B[i] < A[i+1] && prevA < B[i] && prevB < A[i]) {
					count++;
					prevA = B[i];
					prevB = A[i];
					continue;
				} else if(prevA < B[i] && prevB < A[i]) {
					count++;
					prevA = A[i];
					prevB = B[i];
					continue;
				} else if(!(i >= 1 && i < len-1 && prevA < A[i+1] && prevB < B[i+1] && A[i] < B[i+1] && B[i] < A[i+1] && prevA < B[i] && prevB < A[i])) {
					return -1;
				} 
			} else {
				prevA = A[i];
				prevB = B[i];
			}

		}

		return count;
	}

	public static int solution2(int[] A, int[] B) {

		int len = A.length;
		int prevA = A[0];
		int prevB = B[0];
		int count = 0;
		boolean got = false;

		if(len < 2)
			return -1;
		if(len == 2) {
			if(prevA >= A[1] || prevB >= B[1]) {
				got = true;
				if(prevA < B[1] && prevB < A[1]) {
					int temp = A[0];
					A[0] = B[0];
					B[0] = temp;
					count++;
				} 			
			}
			if(got && count == 0) {
				return -1;
			}
			return count;
		}
		for(int i = 1; i < len-1; i++) {
			if(prevA >= A[i] || prevB >= B[i]) {
				got = true;
				if(A[i] < B[i] && B[i] > prevA && B[i] < A[i+1] && prevB < A[i] && A[i] < B[i+1]) {
					int temp = A[i];
					A[i] = B[i];
					B[i] = temp;
					got = false;
					count++;
				} else if(A[i] > B[i] && B[i] > prevA && B[i] < A[i+1] && prevB < A[i] && A[i] < B[i+1]) {
					int temp = A[i];
					A[i] = B[i];
					B[i] = temp;
					got = false;
					count++;
				} else if((prevA > A[i] || prevB > B[i]) && prevA < B[i] && prevB < A[i]) {
					int temp = A[i-1];
					A[i-1] = B[i-1];
					B[i-1] = temp;
					got = false;
					count++;
				}
				if(got)
					count = 0;
			}
			prevA = A[i];
			prevB = B[i];
		}

		if(got && count == 0) {
			return -1;
		}
		return count;
	}

}
