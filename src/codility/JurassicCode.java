package codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Golden Award : https://app.codility.com/cert/view/certVN2TFA-VZ2GK39W44R69TUY/details/ --Outperforms Best soln for performance Tests
// Golden Award : https://app.codility.com/cert/view/certUZ73KX-HPUGS2CFN4T6W68P/details/ --Outperforms Best soln for performance Tests
// Golden Award : https://app.codility.com/cert/view/certCFHG3G-Y3MR3KVGEXQVM4NG/details/ --Pure Implementation but bit better than the Best
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
		Map<Integer, int[]> colorMap = new HashMap<>();
		int[] cols = null;
		int[] dists = new int[count]; 

		for(int i = 0; i < count; i++) {
			int dist = X[i]*X[i] + Y[i]*Y[i];
			dists[i] = dist;
			Character col = colors.charAt(i);
			if((cols=colorMap.get(dist)) == null) {
				cols = new int[2];
				colorMap.put(dist, cols);
			}
			if(col == 'R')
				cols[0]++;
			else
				cols[1]++;
		}

		Arrays.sort(dists);
		int colR = 0;
		int colG = 0;
		int prev = 0;
		for(int dist : dists) {
			if(prev == dist)
				continue;
			int[] colrs = colorMap.get(dist);
			colR+=colrs[0];
			colG+=colrs[1];
			if(colR == colG)
				result = colR+colG;
			prev = dist;
		}
		return result;
	}
}
