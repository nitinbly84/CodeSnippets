package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Zirconium2019 {

	static int max = 0;

	public static void main(String[] args) {
		/*int[] A = {4,2,1}; //Ans : 10
		int[] B = {2,5,3};*/
		/*int[] A = {1}; // Ans : 1
		int[] B = {4};*/
		/*int[] A = {5,5,5}; // Ans : 15
		int[] B = {5,5,5};*/
		/*int[] A = {7, 1, 4, 4}; //Ans : 18
		int[] B = {5, 3, 4, 3};*/
		/*int[] A = {7, 1, 4, 3}; //Ans : 17
		int[] B = {5, 3, 4, 3};*/
		/*int[] A = {4,2,3,6}; //Ans : 19
		int[] B = {2,5,3,7};*/
		/*int[] A = {4,7,4,6}; //Ans : 21
		int[] B = {2,5,3,7};*/
		/*int[] A = {4,7,4,6}; //Ans : 18
		int[] B = {2,5,3,1};*/
		/*int[] A = {4,7,4,6}; //Ans : 21
		int[] B = {2,5,3,1};*/
		int[] A = new int[200000];
		int[] B = new int[200000];
		Random rand = new Random();
		for(int i = 0; i < 200000; i++) {
			A[i] = rand.nextInt(200000);
			B[i] = rand.nextInt(200000);
		}
		int F = 200000;

		System.out.println(solution1(A, B, F));
	}

	//https://app.codility.com/cert/view/certTMYGE7-RAUZEF9VUS477MXQ/details/
	public static int solution(int[] A, int[] B, int F) {

		if(A == null && B == null)
			return 0;
		else if((A != null && F >= A.length) || B == null) {
			return Arrays.stream(A).sum();
		} else {
			if(A != null && F > 0 && B != null) {
				int sum = 0;
				sum = finding(A, B, F);
				return sum;
			} else if(F == 0 && B!= null)
				return Arrays.stream(B).sum();
		}
		return 0;
	}

	public static int finding(int[] A, int[] B, int F) {

		int sum = 0;
		ArrayList<Integer> list = new ArrayList<>(F);
		for(int i = 0; i < F; i++) {
			sum = sum + A[i];
			list.add(i);
		}
		for(int i = F; i < A.length; i++) {
			int min = -1;
			int remove = -1;
			int j = 0;
			if(min != -1)
				j = remove;
			for(; j < F; j++) {
				if((A[list.get(j)] <= A[i] && (B[list.get(j)] > B[i] || A[list.get(j)] < B[i])) || (A[list.get(j)] > A[i] && B[list.get(j)] > B[i] && B[i] > A[list.get(j)])) {
					min = list.get(j);
					remove = j;
				}
			}
			if(min != -1) {
				int tempSum1 = 0;
				int tempSum2 = 0;
				tempSum1 = sum - A[min] + A[i] + B[min];
				tempSum2 = sum + B[i];
				if(tempSum1 > tempSum2) {
					list.remove(remove);
					list.add(i);
					sum = tempSum1;
				} else {
					sum = tempSum2;
				}
			}
			else
				sum = sum + B[i];
		}
		return sum;
	}

	// https://app.codility.com/cert/view/certSANCSY-7UY6N7UNZ3Y98PAH/details/
	public static int solution1(int[] A, int[] B, int F) {

		int sum1 = 0;
		int sum2 = 0;
		if(A == null && B == null)
			return 0;
		else if((A != null && F >= A.length) || B == null) {
			return Arrays.stream(A).sum();
		} else if(A.length > 0) {
			sum1 = A[0] + finding1(A, B, 1, F-1);
			sum2 = B[0] + sum1 - A[0];
		}

		return sum1 > sum2 ? sum1 : sum2;
	}

	public static int finding1(int[] A, int[] B, int curr, int F) {

		int sum1 = 0;
		int sum2 = 0;
		if(curr == A.length)
			return 0;
		if(F > 0) {
			sum1 = A[curr] + finding1(A, B, curr+1, F-1);
		}
		if(F == 0)
			sum1 = B[curr] + finding1(A, B, curr+1, F);
		else if(A.length-curr > F)
			sum2 = B[curr] + finding1(A, B, curr+1, F);

		if(sum1 > sum2) {
			return sum1;
		} else
			return sum2;
	}
}
