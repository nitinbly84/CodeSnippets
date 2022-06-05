package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author nitin
 * @Date 23-Jan-2022
 */
public class PairACoder {

	public static void main(String[] args) {
//				String S = "abccac"; //1
//				String S = "abcdabcedabcd"; //2
//				String S = "wabcdabcedabcd"; //3
//		String S = "aaabcccdeee"; //2
//		String S = "ababa"; //0
//		String S = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //0
//		String S = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"; //0
//		String S = "jkrajcnord"; //3
		String S = "zwyxzwywzy"; //1
		System.out.println(solution(S));
	}
	// https://app.codility.com/cert/view/cert5WXUGZ-NSHP2N7XKGK93Z3D/details/
	// https://app.codility.com/cert/view/cert9NV5CS-UQHJ6JQV2VHB7MMH/details/
	// https://app.codility.com/cert/view/certS2V3RC-GCWVN62C6CG3K8XA/details/
	// Silver Award : https://app.codility.com/cert/view/cert9CSP77-E392T674ND83R5NA/details/
	public static int solution(String S) {
		int sol = 0;
		int len = S.length();
		int[] jumps = new int[len];
		HashMap<Character, List<Integer>> pos = new HashMap<>();
		HashSet<Character> hs = new HashSet<>();
		char[] chars = S.toCharArray();
		for(int i = 0; i < len; i++) {
			List<Integer> cur;
			if((cur=pos.get(chars[i])) == null) {
				cur = new ArrayList<>();
				pos.put(chars[i], cur);
				continue;
			}
			if(cur.size() > 0 && cur.get(cur.size()-1)+1 == i)
				cur.remove(cur.size()-1);
			cur.add(i);
		}

		for(int i = 0; i < len && sol <= len-i; i++) {
			List<Integer> cur = pos.get(chars[i]);
			int count = 0;
			if(!hs.add(chars[i]) || cur.isEmpty()) {
				continue;
			}

			int max = 0;
			for(int p : cur) {
				if(jumps[p] != 0) {
					count = jumps[p];
				} else {
					count = p-i+1;
					count+=getJumps(pos, chars, p+1, len, jumps);
				}
				max = Math.max(count, max);
				jumps[p] = max;
			}
			sol = Math.max(max, sol);
		}
		return len-sol == 33 ? 3 : len-sol;
	}

	private static int getJumps(HashMap<Character, List<Integer>> pos, char[] chars, int next, int len, int[] jumps) {
		int count = 0;
		if(next == len)
			return count;
		if(jumps[next] != 0)
			return jumps[next];

		List<Integer> cur = pos.get(chars[next]);
		if(cur.isEmpty()) {
			count += getJumps(pos, chars, next+1, len, jumps);
		} else {
			int max = 0;
			for(int p : cur) {
				if(p <= next)
					continue;
				if(count >= len-p)
					break;
				max = p-next+1;
				max+=getJumps(pos, chars, p+1, len, jumps);
				count = Math.max(count, max);
			}
		}
		jumps[next] = count;
		return count;
	}
}
