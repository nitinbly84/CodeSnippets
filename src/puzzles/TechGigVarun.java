package puzzles;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TechGig ADA Coding problem.
 * @author Nitin
 *
 */
public class TechGigVarun {

	public static void main(String[] args) {

		int[] input1 = {1,2,3,4,6};
		int input2 = 5;
		int input3 = 3;

		System.out.println(find(input1, input2, input3));
	}

	public static int find(int[] input1, int input2, int input3 ) {

		if(input3 == 0 || input2 == 0 || input3 > input1.length) {
			return -1;
		}
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < input1.length; i++) {
			if(input1[i]%input2 == 0 && input3-1 == 0) {
				return input1[i];
			} if(input3 > 1)
				finder(input1, i+1, input1[i], input3-1, input2, list);
		}
		if(list.size() == 0)
			return -1;
		Collections.sort(list);
		return list.get(0);
	}

	public static void finder(int[] arr, int start, int sum, int count, int div, ArrayList<Integer> list) {

		if(count == 0) {
			return;
		}

		for(int i = start; i < arr.length; i++) {
			if((sum+arr[i])%div == 0 && count-1 == 0) {
				list.add(sum+arr[i]);
			} else if(count-1 != 0) {
				finder(arr, i+1, sum+arr[i], count-1, div, list);
			}
		}

	}
}