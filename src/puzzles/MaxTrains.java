package puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maximum number of trains can cross a single platform with mishaps
 * Minimum number of platforms required to accommodate all trains.
 * Schedule for all trains is provided which has arrival & departure time
 * for all trains.
 *
 */
public class MaxTrains {
	
	public static void main(String[] args) {
		
		List<Float> arr = Arrays.asList(9.00f,  9.40f, 9.50f,  11.00f, 15.00f, 18.00f);
		List<Float> depa = Arrays.asList(9.10f, 12.00f, 11.20f, 11.30f, 19.00f, 20.00f);
		System.out.println("Number of minimum platforms required or maximum number of trains, if only one platform available : " + find(arr, depa));
	}
	
	public static int find(List<Float> arr, List<Float> depa) {
		
		if(arr.size() != depa.size()) {
			System.out.println("Data is incorrect!!");
			System.exit(1);			
		}
		Map<Float, Character> map = new HashMap<>();
		List<Float> all = new ArrayList<>();
		for(float f : arr) {
			map.put(f, 'A');
			all.add(f);
		}
		for(float f : depa) {
			map.put(f, 'D');
			all.add(f);
		}
		Collections.sort(all);
		int max = 0;
		int sum = 0;
		
		for(float f : all) {
			if(map.get(f) == 'A') {
				sum = sum + 1;
				if(sum > max) {
					max = sum;
				}
			} else if(map.get(f) == 'D') {
				sum = sum - 1;
			}
		}
		
		return max;
	}

}
