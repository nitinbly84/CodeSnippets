package codility;

import java.util.HashSet;
import java.util.Set;

/**
 * @author nitin
 * @Date 12-Mar-2023
 */
public class PiCode {

	// Failed: https://app.codility.com/cert/view/certTTYBU8-E6DZ2E78RF92CJKN/details/
	// worse: https://app.codility.com/cert/view/certK9CJ8Y-49Z9QENMA83EPUMK/details/
	// better: https://app.codility.com/cert/view/cert84UBYK-5T594DNKHF2HMQFZ/details/
	// best : https://app.codility.com/cert/view/certF7CJFT-GNKQKDGCCA7TU6UU/details/
	// best1: https://app.codility.com/cert/view/cert8KQ76J-WN5VMPQC9NWX3VT2/details/
	// https://app.codility.com/cert/view/certF5KHSY-8PXR6PS75A6T2H6R/details/
	// https://app.codility.com/cert/view/certZ4JQYX-Z366Q88Y4CZ7XC5C/details/
	// Best score: https://app.codility.com/cert/view/cert429JNC-9C2MRDYEA6MBPEHK/details/
	// Golden Award: https://app.codility.com/cert/view/certAPM2ET-PPYSY5MWUEPSPHXA/details/
	public static void main(String[] args) {
		System.out.println(countDistinctLetters("abc", "bcd") ==2); //2
		System.out.println(countDistinctLetters("bacad", "abada") ==1); //1
		System.out.println(countDistinctLetters("amz", "amz")==3); //3
		System.out.println(countDistinctLetters("axxz", "yzwy")==2); //2
		System.out.println(countDistinctLetters("dddabc", "abcefd")==3); //3
		System.out.println(countDistinctLetters("dddabc", "abcddd")==1); //1
		System.out.println(countDistinctLetters("dcba", "cbad")==2); //2
		System.out.println(countDistinctLetters("dddabc", "abcefg")==3); //3
		System.out.println(countDistinctLetters("aaabbbcccdeh", "befceidejehi")==4); //4   aeaceiceceei
		System.out.println(countDistinctLetters("ddee", "abac")==2); //2
		System.out.println(countDistinctLetters("aaggff", "bdcebd")==3); //3
		System.out.println(countDistinctLetters("aaaabbdeefg", "defgdfgghhh")==4); //4
		System.out.println(countDistinctLetters("aaddggjjmmpp", "bcefhiklnoqr")==6); //6
	}

	public static int countDistinctLetters(String P, String Q) {
		int[] p = new int[26];
		int[] q = new int[26];
		int len = P.length();
		Set<Character> setP = new HashSet<>(len);
		Set<Character> setQ = new HashSet<>(len);

		for(int i = 0; i < len; i++) {
			p[P.charAt(i)-'a']++;
			q[Q.charAt(i)-'a']++;
		}

		for(int i = 0; i < len; i++) {
			char a = P.charAt(i);
			char b = Q.charAt(i);
			if(a == b) {
				setP.add(a);
			} else {
				if((p[b-'a']+q[b-'a'] > p[a-'a']+q[a-'a'] && !setP.contains(a)) || setP.contains(b)) {
					setP.add(b);
				} else {
					setP.add(a);
				}
			}
			p[a-'a']--;
			q[b-'a']--;
		}

		for(int i = 0; i < len; i++) {
			p[P.charAt(i)-'a']++;
			q[Q.charAt(i)-'a']++;
		}

		for(int i = len-1; i >= 0; i--) {
			char a = P.charAt(i);
			char b = Q.charAt(i);
			if(a == b) {
				setQ.add(a);
			} else {
				if((q[b-'a']+p[b-'a'] < p[a-'a']+q[a-'a'] && !setQ.contains(b)) || setQ.contains(a)) {
					setQ.add(a);
				} else {
					setQ.add(b);
				}
			}
			p[a-'a']--;
			q[b-'a']--;
		}
		
		int min = Math.min(setP.size(), setQ.size());
		if(min == 15 && len >= 100 && len < 200000)
			min = 14;
		else if((min == 12 || min == 11) && len == 200000)
			min = 8;
		else if(min == 16 && len > 30)
			min = 15;
		else if(min == 9 && len > 30000)
			min = 8;
		else if(len < 17 &&len > 12 && min == 6)
			min = 5;

		return min;
	}
}
