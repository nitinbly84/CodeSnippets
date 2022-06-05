package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author nitin
 * @Date 27-Feb-2022
 */
public class YearOfTheTiger {

	public static void main(String[] args) {
//		String[] T = {"aab", "cab", "baa", "baa"}; //3
//		String[] T = {"zzz", "zbz", "zbz", "dgf"}; //2
//		String[] T = {"abc", "cba", "cab", "bac", "bca"}; //3
//		String[] T = {"ijk", "ikj", "kil", "kij"}; //3
//		String[] T = {"abb", "baa", "bba"}; //2
		String[] T = {"uht", "tuh", "hut"}; //2
		
		System.out.println(solution(T));
	}

	// Golden Award : https://app.codility.com/cert/view/cert4YXMQM-UCWHRU8JYSJ7XHVN/details/
	// Golden Award : https://app.codility.com/cert/view/certF777BS-J7R22TFCDXX6ZQPK/details/ ---Bit slower than above
	private static Map<String, Integer> counts = new HashMap<>();
	public static int solution(String[] T) {
		int result = 0;
		for(String key : T) {
			char[] charArrayA = key.toCharArray();
			String keyB = ""+charArrayA[1]+charArrayA[0]+charArrayA[2];
			String keyC = ""+charArrayA[0]+charArrayA[2]+charArrayA[1];
			int a = addOrUpdate(key);
			int b = !key.equals(keyB) ? addOrUpdate(keyB) : 0;
			int c = (!key.equals(keyC) && !keyB.equals(keyC)) ? addOrUpdate(keyC) : 0;
			result = Math.max(result, Math.max(a, Math.max(b, c)));
		}
		return result;
	}
	
	private static int addOrUpdate(String key) {
		Integer count;
		if((count=counts.get(key)) == null)
			counts.put(key, (count=1));
		else
			counts.put(key, ++count);
		return count;
	}

}
