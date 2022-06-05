package codility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nitin
 * @Date 20-Dec-2021
 */
public class CodeAlone2021_solved {
	
	public static void main(String[] args) {
//		System.out.println(solution("abbbbaa")); // 4
//		System.out.println(solution("aabababb")); // 2
//		System.out.println(solution("bbbababaaab")); // 0
//		System.out.println(solution("abbabb")); // -1
//		System.out.println(solution("b")); // -1
		System.out.println(solution("ababab")); // 3--
		System.out.println(solution("abbaabab")); // 3--
		System.out.println(solution("abbbbabbba")); // 7
		System.out.println(solution("aababb")); // 1--
		System.out.println(solution("aabbaaaaabbaab")); // 2
//		System.out.println(solution("aabbaabbaabbaa")); // 3--
//		System.out.println(solution("aabaabaabaa")); // 4--
//		System.out.println(solution("baabaab")); // 6--
	}
	
	public static int solution(String S) {
		int len = S.length();
		if(len < 6)
			return -1;
		char[] charArray = S.toCharArray();
		int prevA = -1;
		int prevB = -1;
		int totalA = 0;
		int totalB = 0;
		int countA = 0;
		int countB = 0;
		boolean isA = false;
		boolean isB = false;
		int[] nearest = new int[len];
		int minMoves = 0;
		
		for(int i = 0; i < len; i++) {
			if(charArray[i] == 'a') {
				countA++;
				countB = 0;
				totalA++;
				nearest[i] = prevA;
				if(prevA != -1) {
					if(nearest[prevA] == -1)
						nearest[prevA] = i;
					else if(i-prevA < prevA-nearest[prevA] || (i-prevA == prevA-nearest[prevA] && i > nearest[prevA]))
						nearest[prevA] = i;
				}
				prevA = i;
			} else {
				countB++;
				countA = 0;
				totalB++;
				nearest[i] = prevB;
				if(prevB != -1) {
					if(nearest[prevB] == -1)
						nearest[prevB] = i;
					if(i-prevB < prevB-nearest[prevB])
						nearest[prevB] = i;
				}
				prevB = i;
			}			
			if(countA == 3)
				isA = true;
			if(countB == 3)
				isB = true;
		}
		if(isA && isB)
			return 0;
		if(totalA < 3 || totalB < 3)
			return -1;
		if(isB || (!isA && !isB))
			minMoves = countAMoves(nearest, charArray);
		return minMoves;
	}
	
	private static int countAMoves(int[] nearest, char[] charArray) {
		int len = nearest.length;
		boolean done = false;
		int maxMoves = Integer.MAX_VALUE;
		for(int i = 0; i < len && !done; i++) {
			if(charArray[i] == 'a' && Math.abs(nearest[i] - i) > 1 && nearest[nearest[i]] > nearest[i] && nearest[i] > i) {
				maxMoves = Math.min(Math.min(maxMoves, Math.abs(nearest[i]-i)-1 + Math.abs(nearest[nearest[i]]-i)-2),
						nearest[i]-i-1+Math.abs(nearest[nearest[i]]-nearest[i])-1);				
			}
		}
		
		return maxMoves;
	}
	
}