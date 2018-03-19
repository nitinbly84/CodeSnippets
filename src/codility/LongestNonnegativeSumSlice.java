package codility;

import java.util.Random;

/**
 * Correctness : 81%
 * Performance : 45%
 * URL : https://app.codility.com/cert/view/certZ7XQYN-MUBZBDAVTXBVSRZ4/details/
 * @author nitin
 *
 */
public class LongestNonnegativeSumSlice {

	public static void main(String[] args) {
		long start = System.nanoTime();
		//								int[] A = {-1, -1, 1, -1, 1, 0, 1, -1, -1}; //Ans : 7----------0
		//						int[] A = {1, 1, -1, -1, -1, -1, -1, 1, 1}; //Ans : 4---------1
		//		int[] A = {-1,-1,-1,-1,1,1,1,1,-1,1}; //Ans : 10----------2
		//								int[] A = {1, 1, -1, -1, -1, -1, -1, 1, 1, 1}; //Ans : 10----------3
		//								int[] A = {1,1,-1,-1,-1,1,1,-1,-1,-1,-1}; //Ans : 8-------------------4
		//										int[] A = {-1,-1,0,0,0,1}; //Ans : 5----------------5
		//								int[] A = {1,-1,0,1,-1,-1,-1,1,0}; //Ans : 5--------------------6
//		int[] A = {-1, -1, 1, -1, 0, 0, 0, 0, -1, -1};  //Ans : 6------------------7
		//								int[] A = {-1, 0, -1, 0, -1, -1, -1, 1, 0, 0}; //Ans : 4------------------8
		//				int[] A = {-1,-1,-1,-1,0,0,0,0,1,1,1}; //Ans : 10;-------------------9
		//				int[] A = {1, 1, -1, -1, 0, -1, -1, 1, 1}; //Ans : 9------------------10
		//				int[] A = {1, 1, 1, 1, 0, -1, -1, 1, 1}; // Ans: 9----------------------11
		//						int[] A = {-1, -1, -1, -1, 0, -1, -1, 1, 1}; //Ans : 5--------------------12
		//								int[] A = {-1, -1, -1, -1, 0, -1, -1, -1, -1}; //Ans : 1--------------------13
		//		int[] A = {-1, 1, -1, -1, -1, 1, 0, 0, 1, 0}; //Ans : 9------------------14
		//				int[] A = {-1,-1,-1,-1,0,0,0,0,1}; //Ans : 6---------------------15
		//								int[] A = {-1, 1, -1, -1, -1, 1, 0, 0, -1, 0}; //Ans : 5----------------------16
		//				int[] A = {0,-1,-1,-1,1,1,1}; //Ans : 7-----------------17
		//		int[] A = {1,0,-1,1,0,-1,0,1}; //Ans : 8------------------------18
//		int[] A = {1,1,1,-1,-1,-1,-1,-1,1,1,1,1,1,-1,-1,-1,-1,1,1,1}; //Ans : 20................19
		int[] A = new int[10];
		for(int i = 0; i < 10; i++) {
			Random r = new Random();
			int low = -1;
			int high = 2;
			int result = r.nextInt(high - low) + low;
			A[i] = result;
			System.out.print(result + " ");
		}
		System.out.println(count(A));
		/*int[][] testData = {{-1, -1, 1, -1, 1, 0, 1, -1, -1},{7},{1, 1, -1, -1, -1, -1, -1, 1, 1},{4}, {-1,-1,-1,-1,1,1,1,1,-1,1},{10},
				{1, 1, -1, -1, -1, -1, -1, 1, 1, 1},{10}, {1,1,-1,-1,-1,1,1,-1,-1,-1,-1},{8}, {-1,-1,0,0,0,1},{5}, {1,-1,0,1,-1,-1,-1,1,0},{5},
				{-1, -1, 1, -1, 0, 0, 0, 0, -1, -1},{6}, {-1, 0, -1, 0, -1, -1, -1, 1, 0, 0},{4},{-1,-1,-1,-1,0,0,0,0,1,1,1},{10}, {1, 1, -1, -1, 0, -1, -1, 1, 1}, {9},
				{1, 1, 1, 1, 0, -1, -1, 1, 1},{9},{-1, -1, -1, -1, 0, -1, -1, 1, 1},{5},{-1, -1, -1, -1, 0, -1, -1, -1, -1},{1}, {-1, 1, -1, -1, -1, 1, 0, 0, 1, 0},
				{9}, {-1,-1,-1,-1,0,0,0,0,1},{6},{-1, 1, -1, -1, -1, 1, 0, 0, -1, 0},{5},{0,-1,-1,-1,1,1,1},{7},{1,0,-1,1,0,-1,0,1},{8},{1,1,1,-1,-1,-1,-1,-1,1,1,1,1,1,-1,-1,-1,-1,1,1,1},{20}};
		boolean pass = true;
		int count = 0;
		for(int i = 0; i < testData.length; i=i+2) {
			int ans = 0;
			try {
				ans = count(testData[i]);
				if(ans != testData[i+1][0]) {
					pass = false;
					System.out.println(i/2 + "-----Expected : " + testData[i+1][0] + " But Got : "+ ans + " Array Length :" + testData[i].length);
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Exception for : " + i/2);
				e.printStackTrace();
			}
			count++;
		}

		if(pass) {
			System.out.println("All " + count + " test Cases passed!!!!");
		}
		System.out.println("Time taken in ns : " + (System.nanoTime() - start));*/

	}

	public static int count(int[] A) {
		int len = A.length;
		int start = 0;
		int maxCount = 0;
		int count = 0;
		int sum = 0;
		int lastSum = 0;
		int sameSum = 0;
		int lastSameSum = 0;

		for(int i = 0; i < len; i++) {
			sum = sum + A[i];
			if(lastSum == sum) {
				sameSum++;
				if(lastSameSum < sameSum) {
					lastSameSum = sameSum;
				}
			}
			if(sum >= 0 || (sum < 0 && Math.abs(sum) <= len-i-1))
				count++;
			else if(sum < 0 && Math.abs(sum) > len-i-1 && A[start] <= 0) {
				int j = start;

				while(sum < 0 && Math.abs(sum) > len-i-1 && j < i) {
					sum = sum - A[j];
					if(sum == lastSum && A[j] > 0 && A[i] < 0) {
						sum = sum - A[i] + A[j];
					} else {
						j++;
					}
					lastSum = sum;
				}
				if(sum >= 0) {
					count = i - j + 1;
				}
				start = j;
				count = i - start + 1;
			} else if(sum < 0 && Math.abs(sum) > len-i && A[start] > 0) {
				int j = i;
				while(sum < 0 && j > start) {
					sum = sum - A[j];
					count = j - start;
					j--;
				}
				i = j;
				start = j;
			}

			if(maxCount < count && sum >= 0) {
				maxCount = count;
			}
			lastSum = sum;
		}
		if(maxCount < lastSameSum) {
			return lastSameSum;
		}
		return maxCount;
	}

	public static int count1(int[] A) {

		int sum = 0;
		int lastSum = 0;
		int count = 0;
		int maxCount = 0;
		int len = A.length;
		int start = 0;

		for(int i = 0; i < len; i++) {
			sum = sum + A[i];
			count++;
			if(sum >= 0) {
				if(maxCount <= count) {
					maxCount = count;
				}
			} else if(sum < 0 && Math.abs(sum) >= len-i && lastSum <= sum) {
				while(A[start] < 0 && Math.abs(sum) >= len-i) {
					sum = sum - A[start];
					start++;
					count--;
				}
				int tempSum = sum;
				int j = i;
				while(A[start] > A[j] && tempSum < 0) {
					tempSum = tempSum - A[j];
					count--;
					j--;
				}
				if(maxCount <= count && tempSum>= 0) {
					maxCount = count;
				}
				//				i = start;
				//				count = 0;
			} else if(sum < 0 && Math.abs(sum) > len-i && lastSum >= sum) {
				if(sum < 0 && Math.abs(sum) > len-i) {
					//					i--;
					count = 0;
					sum = 0;
					int j = i;
					while(sum >= 0) {
						j--;
						sum = sum + A[j];
						count++;
					}
					if(Math.abs(sum) <= j) {
						while(sum < 0 && j > 0) {
							j--;
							sum = sum + A[j];
							//							count++;
						}
					}
					if(count > maxCount) {
						maxCount = count;
					}
					continue;
				}
				int tempSum = sum - A[start];
				count--;
				start++;
				while(tempSum < 0 && start < i && A[start] < 0) {
					tempSum = tempSum - A[start];
					count--;
					start++;
				}
				if(A[start] > A[i]) {
					int j = i;
					while(tempSum < 0 && j > start) {
						tempSum = tempSum - A[j];
						j--;
						count--;
					}
				}
				if(maxCount <= count) {
					maxCount = count;
				}
			}
			lastSum = sum;
		}

		return maxCount;
	}
}
