package codility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

// Golden Award : https://app.codility.com/cert/view/certM7JSYV-XTUTBN85DUNAAQEM/details/ --Pure implementation slower due to TreeMap
// Golden Award : https://app.codility.com/cert/view/certPAR7J7-YS3KDD9WQGBM7KVX/details/ --Pure implementation with best performance
// Golden Award : https://app.codility.com/cert/view/certJQYRH3-D2CRBK7WAS8T9AJE/details/
// Golden Award : https://app.codility.com/cert/view/certGSJ6CB-FWN62KASMBRSTCGD/details/ -- Quite slower than above, so below solution is slower
// Silver Award : https://app.codility.com/cert/view/cert3SA79Z-AU3HF33TR57FRAPY/details/
public class JurassicCode {
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {4,0,2,-2}, new int[]{4,1,2,-3}, "RGRR")); //2
		System.out.println(solution(new int[] {1, 1, -1, -1}, new int[]{1, -1, 1, -1}, "RGRG")); //4
		System.out.println(solution(new int[] {1, 0, 0}, new int[]{0, 1, -1}, "GGR")); //0
		System.out.println(solution(new int[] {0,0}, new int[]{1,1}, "GR")); //2
		System.out.println(solution(new int[] {5, -5, 5, -5}, new int[]{1, -1, -3, -1}, "GRGR")); //4
		System.out.println(solution(new int[] {5, -5, 5, 5}, new int[]{1, -1, -3, 1}, "GRGR")); //4
		System.out.println(solution(new int[] {5, -5, 5}, new int[]{1, -1, -3}, "GRG")); //2
		System.out.println(solution(new int[] {3000, -3000, 4100, -4100, -3000}, new int[]{5000, -5000, 4100, -4100, 5000}, "RRGRG")); //2
	}

	public static int solution(int[] X, int[] Y, String colors) {
		int count = X.length;
		int result = 0;
		SortedMap<Long, List<Character>> colorMap = new TreeMap<>();
		List<Character> chars = null;

		for(int i = 0; i < count; i++) {
			long dist = X[i]*X[i] + Y[i]*Y[i];
			Character col = colors.charAt(i);
			if((chars=colorMap.get(dist)) == null) {
				chars = new ArrayList<>();
				colorMap.put(dist, chars);
			}
			chars.add(col);
		}
		int countR = 0;
		int countG = 0;

		for(Map.Entry<Long, List<Character>> entry : colorMap.entrySet()) {
			List<Character> cols = entry.getValue();
			for(char col : cols) {
				if(col == 'R')
					countR++;
				else if(col == 'G')
					countG++;
			}
			if(countR == countG)
				result = countR+countG;
		}
		return result;
	}
}
