package codility;

import java.util.Arrays;

/**
 * @author nitin
 * @Date 21-Nov-2021
 */
public class Gamers2021 {

	public static void main(String[] args) {
		System.out.println(solution("aa??bbb")); //3
		System.out.println(solution("a?b?aa?b?a")); //2
		System.out.println(solution("?????")); //1
		System.out.println(solution("aa?b?aa")); //3
		System.out.println(solution("a????a")); //2
		System.out.println(solution("????a?")); //1
		System.out.println(solution("ab?aabba??")); //2
		System.out.println(solution("?")); //1
		System.out.println(solution("??")); //1
		System.out.println(solution("aaa?bb?aaa")); //4
		System.out.println(solution("aaaaaaaaaaa")); //11
	}

	// Golden Award : https://app.codility.com/cert/view/certFRKBEX-XKZ96P7HA5MEA8DF/details/
	// Hoped that it will work better but no change
	public static int solution(String S) {
		int result = 1;
		int len = S.length();
		
		if(len <= 1)
			return len;
		
		char prev = S.charAt(0);
		int pos = 0;
		int count = 1;
		int[] counts = new int[len];
		char[] chars = S.toCharArray();
		
		if(prev == '?')
			counts[0] = -1;

		for(int i = 1; i < len; i++) {
			if(chars[i] == '?') {
				counts[i] = -1;
				if(chars[i-1] != '?') {
					counts[i-1] = count;
					setStart(counts, pos, count);
				}
				pos = -1;
			} else if(prev == chars[i]) {
				count++;
			} else {
				setStart(counts, pos, count);
				count = 1;
				pos = i;
			}
			prev = chars[i];
		}
		setStart(counts, pos, count);
		
		if(counts[0] == -1) {
			chars[0] = chars[1] == 'a'?'b':'a';
			counts[0] = 1;
		}
		
		for(int i = 1; i < len-1; i++) {
			if(counts[i] == -1) {
				if(counts[i-1] <= counts[i+1]) {
					if(chars[i-1] == chars[i+1]) {
						chars[i] = chars[i+1] == 'a'?'b':'a';
						counts[i] = 1;
					}
					else {
						chars[i] = chars[i-1];
						counts[i] = counts[i-1]+1;
					}
				} else {
					if(chars[i+1] != '?') {
						chars[i] = chars[i+1];
						counts[i] = counts[i+1]+1;
					} else {
						chars[i] = chars[i-1] == 'a'?'b':'a';
						counts[i] = 1;
					}
				}
			} else if(i > 0 && chars[i] == chars[i-1])
				counts[i] = Math.max(counts[i],counts[i-1]);
			if(result < counts[i])
				result = counts[i];
		}
		return result;
	}
	
	private static void setStart(int[] counts, int pos, int val) {
		if(pos != -1)
			counts[pos] = val;
	}

	// Golden Award : https://app.codility.com/cert/view/certNQ3ZSM-4K7TAN8DK2VRF93S/details/
	public static int solution1(String S) {
		int result = 1;
		int len = S.length();
		char prev = S.charAt(0);
		int pos = 0;
		int count = 1;
		int[] counts = new int[len];
		char[] chars = S.toCharArray();
		if(len <= 1)
			return len;
		if(prev == '?')
			counts[0] = -1;
		else
			counts[0] = 1;
		for(int i = 1; i < len; i++) {
			char cur = S.charAt(i);
			if(cur == '?') {
				counts[i] = -1;
				if(S.charAt(i-1) != '?') {
					counts[i-1] = count;
					if(pos != -1)
						counts[pos] = count;
				}
				count = 1;
				pos = -1;
			} else if(prev == cur) {
				count++;
			} else {
				if(pos != -1)
					counts[pos] = count;
				count = 1;
				pos = i;
			}
			prev = cur;
		}
		if(pos != -1) {
			counts[pos] = count;
		}
		for(int i = 0; i < len; i++) {
			if(counts[i] == -1) {
				if(i == 0) {
					chars[i] = chars[i+1] == 'a'?'b':'a';
					counts[0] = 1;
				} else if(i == len-1) {
					counts[i] = 1;
				} else {
					if(counts[i-1] <= counts[i+1]) {
						if(chars[i-1] == chars[i+1]) {
							chars[i] = chars[i+1] == 'a'?'b':'a';
							counts[i] = 1;
						}
						else {
							chars[i] = chars[i-1];
							counts[i] = counts[i-1]+1;
						}
					} else {
						if(chars[i+1] != '?') {
							chars[i] = chars[i+1];
							counts[i] = counts[i+1]+1;
						} else {
							chars[i] = chars[i-1] == 'a' ? 'b' : 'a';
							counts[i] = 1;
						}
					}
				}
			} else if(i > 0 && chars[i] == chars[i-1])
				counts[i] = Math.max(counts[i],counts[i-1]);
		}
		for(int val : counts) {
			if(result < val)
				result = val;
		}
		return result;
	}

	public static int solution2(String S) {
		int result1 = 1;
		int result2 = 1;
		int result3 = 1;
		int result4 = 1;
		int count = 1;
		char[] chars = S.toCharArray();
		int len = S.length();
		char[] copy1 = Arrays.copyOf(chars, len);
		char[] copy2 = Arrays.copyOf(chars, len);
		char[] copy3 = Arrays.copyOf(chars, len);
		char[] copy4 = Arrays.copyOf(chars, len);

		for(int i = 1; i < len; i++) {
			if(copy1[i] == '?') {
				copy1[i] = copy1[i-1] == 'a'?'b':'a';
			}
			if(copy1[i-1] == copy1[i]) {
				count++;
			} else {
				result1 = Math.max(result1, count);
				count = 1;
			}
		}
		result1 = Math.max(result1, count);
		count = 1;
		for(int i = 1; i < len; i++) {
			if(copy3[i] == '?') {
				copy3[i] = copy3[i-1] == 'a'?'a':'b';
			}
			if(copy3[i-1] == copy3[i]) {
				count++;
			} else {
				result3 = Math.max(result3, count);
				count = 1;
			}
		}
		result3 = Math.max(result3, count);
		result1 = Math.min(result1, result3);
		count = 1;
		for(int i = len-2; i >= 0; i--) {
			if(copy2[i] == '?') {
				copy2[i] = copy2[i+1] == 'a'&& copy3[i] != 'b'?'b':'a';
			}
			if(copy2[i+1] == copy2[i]) {
				count++;
			} else {
				result2 = Math.max(result2, count);
				count = 1;
			}
		}
		result2 = Math.max(result2, count);
		count = 1;
		for(int i = len-2; i >= 0; i--) {
			if(copy4[i] == '?') {
				copy4[i] = copy4[i+1] == 'a' && copy1[i] != 'a'?'a':'b';
			}
			if(copy4[i+1] == copy4[i]) {
				count++;
			} else {
				result4 = Math.max(result4, count);
				count = 1;
			}
		}
		result4 = Math.max(result4, count);
		result2 = Math.min(result2, result4);
		return Math.min(result1, result2);
	}
}
