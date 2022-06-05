package codility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Cuprum2018 {

	public static void main(String[] args) {
		String source = "6daa6ccaaa6d"; // Ans : 8
//		String source = "abcdefghhgfedcba"; // Ans : 16
//		String source = "abcda"; // Ans : 0
//		String source = "abbcda"; // Ans : 2
//		String source = "abbccadddda"; // Ans : 10
//		String source = "abbccaedddda"; // Ans : 6
		System.out.println(findSubstring(source));
	}
	
	
	public static int findSubstring(String source) {
		
		int result = 0;
		int last = 0;
		int i = 0;
		int len = source.length();
		HashMap<Integer, Boolean> odds = new HashMap<>();
		HashMap<Character, Integer> counts = new HashMap<>();
		
		while(i < len) {
			char ch = source.charAt(i);
			Integer count = counts.get(ch);
			if(count == null)
				count = 1;
			else
				count++;
			counts.put(ch, count);
			
			Set<Character> set = counts.keySet();
			for(char c : set) {
				if(counts.get(c)%2 != 0)
					odds.put(i, true);
			}
			i++;
		}
		
		i = 0;
		/*while(i < len) {
			char ch = source.charAt(i);
			Integer count = counts.get(ch);
			if(count > odds[i])
				last++;
			else {
				if(result < last)
					result = last;
				last = 0;
			}
			i++;
		}*/
		return result;
	}

	public static int findSubstring1(String source) {

		int result = 0;
		int len = source.length();
		int i = 0;
		int j = len - 1;
		HashMap<Character, Integer> counts = new HashMap<>();
		HashSet<Character> odds = new HashSet<>();
		while(i < len) {
			char ch = source.charAt(i);
			Integer count = counts.get(ch);
			if(count == null)
				count = 1;
			else
				count++;
			counts.put(ch, count);
			if(count%2 != 0) {
				odds.add(ch);
			} else {
				if(odds.contains(ch)) 
					odds.remove(ch);
			}
			i++;
		}

		i = 0;
		StringBuilder sb = new StringBuilder();
		String last = "";
		while(i <= j) {
			char ch = source.charAt(i);
			int count = counts.get(ch);
			if(odds.contains(ch)) {
				count--;
				if(count%2 == 0)
					odds.remove(ch);
				counts.put(ch, count);
				if(last.length() < sb.length())
					last = sb.toString();
				sb = new StringBuilder();
			} else {
				if(!odds.isEmpty()) {
					if(source.charAt(i) == source.charAt(j) && (count - 2) == 0) {
						count = count - 2;
						if(count == 0)
							counts.remove(ch);
						else
							counts.put(ch, count);
						j--;
					} else {
						char chj = source.charAt(i);
						if(odds.contains(chj)) {
							int countj = counts.get(chj);
							countj--;
							if(countj%2 == 0) {
								odds.remove(chj);
								counts.put(chj, countj);
							} else 
								counts.put(chj, countj);
						} else {
							sb.append(ch);
						}
					}
				} else {
					sb.append(ch);
				}
			}
			i++;
		}
		
		Set<Character> set = counts.keySet();
		for(char ch : set) {
			result = result + counts.get(ch);
		}

		System.out.println(last);
		return result;
	}

}
