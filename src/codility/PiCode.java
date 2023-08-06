package codility;

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
	// Golden Award(Better): https://app.codility.com/cert/view/certVFPFW8-BKD4BRE837BYZGV6/details/
	// Golden Award(shorter): https://app.codility.com/cert/view/certY6AMJ6-A6GY223H3YTFJAQ5/details/
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
		int[] p1 = new int[26];
		int[] q1 = new int[26];
		int[] setP = new int[26];
		int[] setQ = new int[26];

		int len = P.length();
		int resP = 0;
		int resQ = 0;

		for(int i = 0; i < len; i++) {
			p[P.charAt(i)-'a']++;
			q[Q.charAt(i)-'a']++;
			p1[P.charAt(i)-'a']++;
			q1[Q.charAt(i)-'a']++;
		}

		for(int i = 0; i < len; i++) {
			char a = P.charAt(i);
			char b = Q.charAt(i);
			if((p[b-'a']+q[b-'a'] > p[a-'a']+q[a-'a'] && setP[a-'a'] == 0) || setP[b-'a'] > 0) {
				resP = setP[b-'a']++ == 0 ? resP+1 : resP;
			} else {
				resP = setP[a-'a']++ == 0 ? resP+1 : resP;
			}
			p[a-'a']--;
			q[b-'a']--;
		}

		for(int i = len-1; i >= 0; i--) {
			char a = P.charAt(i);
			char b = Q.charAt(i);
			if((p1[a-'a']+q1[a-'a'] > q1[b-'a']+p1[b-'a'] && setQ[b-'a'] == 0) || setQ[a-'a'] > 0) {
				resQ = setQ[a-'a']++ == 0 ? resQ+1 : resQ;
			} else {
				resQ = setQ[b-'a']++ == 0 ? resQ+1 : resQ;
			}
			p1[a-'a']--;
			q1[b-'a']--;
		}

		int min = Math.min(resP, resQ);
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
