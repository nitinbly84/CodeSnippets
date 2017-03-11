package puzzles;

/**
 * Find max of two numbers without using if-else & any comparison operator.
 *
 */
public class Comparison {

	public static void main(String[] args) {
		int a = 500;
		int b = 10000;
		int c = a-b;
		int k = (c>>31) & 0x1;
		int result = a - k*c;
		
		System.out.println("Max number is : " + result);
	}
}
