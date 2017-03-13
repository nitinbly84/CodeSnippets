package algorithms;

import java.util.Arrays;

/**
 * A function that takes a number as input and outputs the biggest number with the same set of digits.
 * A function that gets the next greater number with the same set of digits.
 * @author Nitin
 *
 */
public class LargestNumber {
	
	public static void main(String[] args) {
		String num = "423862";
		System.out.println(largest(num));
		System.out.println(nextLargest(num));
	}
	
	static String largest(String num) {
		if(num == null)
			return null;
		char[] chr = num.toCharArray();
		int len = chr.length;
		if(len == 0)
			return null;
		Arrays.sort(chr);
		char[] result = new char[len];
		int j = 0;
		for(int i = len-1; i >= 0; i--) {
			result[j] = chr[i];
			j++;
		}
		return String.copyValueOf(result);
	}
	
	static String nextLargest(String num) {
		if(num == null)
			return null;
		char[] chr = num.toCharArray();
		int len = chr.length;
		if(len == 0)
			return null;
		int stop = -1;
		for(int i = len-1; i > 0; i--) {
			if(chr[i] < chr[i-1])
				continue;
			else {
				stop = i-1;
				break;
			}				
		}
		char temp = chr[stop];
		chr[stop] = chr[stop+2];
		chr[stop+2] = temp;
		char[] in = new char[len-stop-1];
		in = Arrays.copyOfRange(chr, stop+1, len);
		Arrays.sort(in);
		for(char c : in) {
			chr[++stop] = c;
		}
		
		return String.copyValueOf(chr);
	}

}
