package puzzles;

/**
 * Addition of two numbers without using any addition operator
 *
 */
public class BitsAddition {
	
	public static void main(String[] args) {
		System.out.println(addition(5, 4));
	}
	
	static int addition(int a, int b) {
		if(b == 0){
			return a;
		}
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return addition(sum, carry);
	}

}
