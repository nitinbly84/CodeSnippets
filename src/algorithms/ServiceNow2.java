package algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ServiceNow2 implements Comparator<String>{
	
//	public static Integer[] nums = {9,90,900,45,110,11};
	public static Integer[] nums = {8, 100, 22, 101, 77};
	
	public static void main(String[] args) {
		String[] sorted = new String[nums.length];
		int i = 0;
		for(int num : nums) {
			String str = Integer.toString(num);
			sorted[i] = str;
			i++;
		}
		Arrays.sort(sorted);
		Arrays.sort(sorted, new ServiceNow2());
		String result = "";
		for(String str : sorted) {
			result = result + str;
		}
		System.out.println(result);
	}

	@Override
	public int compare(String cur, String o) {
		String curr = cur.toString();
		String next = o;
		int i = 0;
		int j = 0;
		while(i <= curr.length()-1 && j <= next.length()-1) {
			if(curr.charAt(i) > next.charAt(j)) {
				return -1;
			} else if(curr.charAt(i) < next.charAt(j)) {
				return 1;
			}
			i++;
			j++;
		}
		if(curr.length() > next.length()) {
			return 1;
		}
		return 0;
	}

}