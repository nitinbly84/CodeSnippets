package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServiceNow {

	public static void main(String[] arg) {
//		int[] args = {-1, 3, 5, 7, 10, 20, 1902, 8233, 13, 17, 19, 11, 97, 53, 12, 6, 89, 90, 48, 66, 65, 32, 111};
//				int[] args = {1, 3, 10, 61, 13};
//		int[] args = {-1, 3, 5, 7, 10, 20, 1902, 8233};
		int[] args = {-1, 3, 5, 7, 10, 20, 1902, 8233, 11, 13, 17, 23, 19, 41, 29, 97, 53};
		List<Integer> primes = new ArrayList<>();

		// Below creates the list of prime numbers present in the array/list
		for(int number : args) {
			if(number == 2 || number == 3 || number == 7) {
				primes.add(number);
				continue;
			} else if(number < 0 || number == 0 || number == 1)
				continue;

			boolean isPrime = true;
			int sqrRoot = (int)Math.floor(Math.sqrt(number));
			int i = 2;
			for(; i <= sqrRoot; i++) {
				if(number%i == 0 && sqrRoot != 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				primes.add(number);
			}
		}

		// Below sorts the discovered prime numbers
		Collections.sort(primes);

		Integer[] arr = new Integer[primes.size()];
		Map<Integer, List<String>> finalRes = new LinkedHashMap<>();
		long start1 = System.currentTimeMillis();
		for(int i = 1; i <= primes.size(); i++)
			kick(primes.toArray(arr), finalRes, i);
		for(Integer num : finalRes.keySet()) {
			List<String> results = finalRes.get(num);
			for(String res : results)
				System.out.println(res);
		}
//		System.out.println("Time Taken " + (System.currentTimeMillis() - start1) + "ms ............................");
		long end = System.currentTimeMillis();

		long start = System.currentTimeMillis();
		for(int i = 1; i <= primes.size(); i++)
			printCombination(primes.toArray(arr), primes.size(), i);

		System.out.println("Time Taken " + (end - start1) + "ms ............................");
		System.out.println("Time Taken " + (System.currentTimeMillis() - start) + "ms ............................");
	}

	/**
	 * @Created : Nitin Agrawal
	 * @param primes : Sorted array of prime numbers
	 * @param finalRes : It will be holding the results in ordered manner. 
	 * @param count : number of digits in the combination 
	 */
	public static void kick(Integer[] primes, Map<Integer, List<String>> finalRes, int count) {

		List<String> results = new ArrayList<>();
		int i = 0;
		int j = 0;
		int len = primes.length;
		for(int num : primes) {
			if(count == 1)
				results.add(num + "");
			while(i < len-1 && count > 1) {
				counter(primes, results, count-1, i, len-1, num+"");
				i++;
			}
			i = j+1;
			if(finalRes.get(num) == null)
				finalRes.put(num, results);
			else {
				List<String> results1 = finalRes.get(num);
				results1.addAll(results);
				finalRes.put(num, results1);
			}
			results = new ArrayList<>();
			j++;
		}
	}

	/**
	 * @Created : Nitin Agrawal
	 * @param primes
	 * @param results
	 * @param count
	 * @param start
	 * @param end
	 * @param curr
	 */
	private static void counter(Integer[] primes, List<String> results, int count, int start, int end, String curr) {

		if(start >= primes.length-1)
			return;
		String result = primes[start+1] + "";
		if(count > 0) {
			result = curr + ", " + result;
			count--;
		}
		if(count > 0) {
			counter(primes, results, count, start+1, end, result);
		}
		else
			results.add(result);
		if(primes.length-2 >= start && count > 0)
			counter(primes, results, count, start+2, end, result);

	}

	// Below 2 methods are taken from https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	private static void combinationUtil(Integer arr[], int data[], int start, 
			int end, int index, int r) 
	{ 
		// Current combination is ready to be printed, print it 
		if (index == r) 
		{ 
			for (int j=0; j<r; j++) 
				System.out.print(data[j]+" "); 
			System.out.println(""); 
			return; 
		} 

		// replace index with all possible elements. The condition 
		// "end-i+1 >= r-index" makes sure that including one element 
		// at index will make a combination with remaining elements 
		// at remaining positions 
		for (int i=start; i<=end && end-i+1 >= r-index; i++) 
		{ 
			data[index] = arr[i]; 
			combinationUtil(arr, data, i+1, end, index+1, r); 
		} 
	} 

	// The main function that prints all combinations of size r 
	// in arr[] of size n. This function mainly uses combinationUtil() 
	static void printCombination(Integer arr[], int n, int r) 
	{ 
		// A temporary array to store all combination one by one 
		int data[]=new int[r]; 

		// Print all combination using temprary array 'data[]' 
		combinationUtil(arr, data, 0, n-1, 0, r); 
	} 
}
