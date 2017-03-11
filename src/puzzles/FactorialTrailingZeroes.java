package puzzles;

import java.math.BigInteger;
/**
 * To find the number of trailing zeroes in the factorial of any number.
 * @author Nitin
 *
 */
public class FactorialTrailingZeroes {

	public static void main(String[] args) {
		int num = 30;
		int numb = num;
		BigInteger fact = new BigInteger("1");

		while(num > 1) {
			fact = fact.multiply(BigInteger.valueOf(num));
			num--;
		}

		System.out.println(fact);

		int count = numb/5;
		System.out.println(count+count/5);

	}

}
