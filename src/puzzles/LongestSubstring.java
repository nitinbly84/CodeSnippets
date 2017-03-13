package puzzles;

import java.util.HashSet;

/**
 * Longest Substring Without Repeating Characters
 * @author Nitin
 *
 */
public class LongestSubstring {
	
	public static void main(String[] args) {
		
		String str = "abccdefghcde";
		
		char[] chr = str.toCharArray();
		int count = 0;
		char cr = '\b';
		StringBuilder sb = new StringBuilder();
		HashSet<Character> set = new HashSet<>();
		String longest = new String();
		for(char ch : chr) {
			if(cr != ch && ch != '\b' && set.add(ch)) {
				count++;
				sb.append(ch);
			} else if(sb.length() > 0) {
				if(longest.length() < count) {
					longest = sb.toString();
					sb.delete(0, count);
					sb.append(ch);
					set.clear();
					set.add(ch);
					count = 1;
				}
			} else {
				longest = sb.toString();
				sb.delete(0, count);
			}
		}
		if(longest.length() < count) {
			longest = sb.toString();
		}
		System.out.println(longest);
	}
}
