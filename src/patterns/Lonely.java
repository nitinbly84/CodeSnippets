package patterns;

/**
 * An array contains some numbers, & all are getting repeated
 * but one or all are getting repeated twice but one is getting
 * repeated twice. To find such element or lonely element.
 * @author nitin
 *
 */
public class Lonely {
	public static void main(String[] args) {
		int[] numbers = {1,2,3,3,2,4,1};
		int result = 0;
		for(int num : numbers) {
			result = result ^ num;
		}
		
		System.out.println("Number : " + result);
	}

}
