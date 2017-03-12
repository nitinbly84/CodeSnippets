package puzzles;

import java.util.HashMap;
/**
 * TechGig Surpassing of Kings problem.
 * @author Nitin
 *
 */
public class TechGigSurpassing {

	public static void main(String[] args) {
		int[] input1 = {2,7,5,3,0,8,1};
		int[] result = surpassers(input1);
		System.out.println(result[0]);
	}
	
	public static int[] surpassers(int[] input1) {
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		int len = input1.length;
		
		for(int i = 0; i < len; i++) {
			int count = 0;
			for(int j = i+1; j < len; j++) {
				if(input1[i] < input1[j])
					count++;
			}
			hm.put(input1[i], count);
		}
		
		int[] result = new int[len];
		for(int i = 0; i < len; i++) {
			
			result[i] = hm.get(input1[i]);					
		}
		
		return result;
	}
	
}
