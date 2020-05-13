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
		String num = "123456";
		System.out.println(num);
		System.out.println(largest(num));
		System.out.println(nextLargest(num));
	}
	
	static String largest(String num) {
		if(num == null)
			return "No Answer";
		char[] chr = num.toCharArray();
		int len = chr.length;
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
			return "No Answer";
		char[] chr = num.toCharArray();
		int len = chr.length;
		int stop = -1;
		for(int i = len-1; i > 0; i--) {
			if(chr[i] > chr[i-1]) {
				stop = i-1;
				break;
			}
		}
		if(stop == -1)
			return "No Answer";
		char[] in = new char[len-stop-1];
		char temp = chr[stop];
		in = Arrays.copyOfRange(chr, stop+1, len);
		Arrays.sort(in);
		len = 0;
		for(char c : in) {
			if(temp < c) {
				in[len] = temp;
				chr[stop] = c;
			}
			len++;
		}
		for(char c : in) {
			chr[++stop] = c;
		}
		return String.copyValueOf(chr);
	}

}
