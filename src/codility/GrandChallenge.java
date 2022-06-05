package codility;

import java.util.HashMap;

public class GrandChallenge {

	public static void main(String[] args) {
//				String S = "cabbacc"; //Ans : 4
//				String S = "abababa"; //Ans : 6
//						String S = "aaaaaaaaaabbbaabbabbbbbb"; //Ans : 22
//								String S = "aaaaaaa";
		String S = "abcabbac"; //Ans : 4
		int len = 0;
		System.out.println(length(S, len));
	}

	public static int length(String S, int len) {
		HashMap<Character, Integer> hm = new HashMap<>();
		char[] chr = S.toCharArray();
		int start = 0;
		int curr = 0;
		int max = Integer.MIN_VALUE;
		for(char c :chr) {
			if(hm.size() < 2) {
				if(hm.get(c) == null)
					hm.put(c, 1);
				else
					hm.put(c, hm.get(c)+1);
				curr++;
			} else if(hm.size() <= 2 && hm.get(c) != null) {
				hm.put(c, hm.get(c)+1);
				curr++;
			} else if(hm.size() == 2 && hm.get(c) == null) {
				if(max < (curr-start) && start <= S.length())
					max = curr-start;
				int i = curr-1;
				hm.clear();
				hm.put(chr[i], 1);
				while(i > start && chr[i] == chr[i-1]) {
					hm.put(c, hm.get(chr[i])+1);
					i--;
				}
				start = i-1;
				hm.put(c, 1);
			}
		}
		if(hm.size() < 2)
			return 0;
		Integer[] vals = new Integer[2]; 
		hm.values().toArray(vals);
		if(max < (curr-start+1) && start <= S.length() && vals[0] == vals[1])
			max = curr-start+1;
		else if(vals[0] < vals[1] && max < vals[0]*2)
			max = vals[0]*2;
		else if(vals[0] > vals[1] && max < vals[1]*2)
			max = vals[1]*2;
		return max;
	}

}
