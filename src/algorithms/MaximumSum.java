package algorithms;

import java.util.Arrays;

/**
 * Maximum Sum of Non-adjacent Elements
 * @author Nitin
 *
 */
public class MaximumSum {

//		static int[] array = {1,0,3,9,2}; //ans : 10
//	static int[] array = {1,5,3,1,4}; //ans : 9
//	static int[] array = {5,1,3,1,4}; //ans : 12
//	static int[] array = {1,2,7,1,4}; //ans : 12
//	static int[] array = {1,7,7,1,4}; //ans : 12
	static int[] array = {1,2,3,4,7}; //ans : 11
	static int max = Integer.MIN_VALUE;
	static int prev = 0;

	public static void main(String[] args) {
		max(array);
		System.out.println(max);
	}

	static boolean max(int[] arr) {
		int len = arr.length;
		if(len == 2){
			if(arr[0] > arr[1]){
				max = arr[0];
				prev = arr[1];
				return false;
			}
			else{
				max = arr[1];
				prev = arr[0];
				return true;
			}
		}
		boolean result = max(Arrays.copyOf(arr, len-1));
		if(!result && max < max+arr[len-1]){
			prev = max;
			max = max+arr[len-1];
			return true;
		} else if(result && max < arr[len-1]+prev) {
			int temp = max;
			max = arr[len-1]+prev;
			prev = temp;
			return true;
		} else 
			return false;
	}
}
