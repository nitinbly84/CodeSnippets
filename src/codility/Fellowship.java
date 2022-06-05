package codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nitin
 * @Date 06-Jun-2021
 */
public class Fellowship {

	public static void main(String[] args) {
//		String S = "yyuppppnnljifffbbbaa"; int k = 10; // jyyuppppnnlifffbbbaa
//		String S = "decade"; int k = 4; // adcede
//		String S = "bbbabbb"; int k = 2; // babbbbb
//		String S = "pqprprpspspvtwvwvwvwyzzzx"; int k = 29; // ppppppqrrsstvvvvwwwwxyzzz
//		String S = "abracadabra"; int k = 15;// aaaaabrcdbr
//		String S = "edcghijakl"; int k = 3; // cdeghijakl
//		String S = "xzztxty"; int k = 2; // xtzzxty
//		String S = "bcdefxyzjnm"; int k = 4; // bcdefjxynzm
		String S = "abcdefghijklm"; int k = 14; // abcdefghijklm
		System.out.println(solution(S, k));
	}
	
	// Silver : https://app.codility.com/cert/view/certSC2NNC-GZMDFPHJTY4CQ4KD/details/
	// https://app.codility.com/cert/view/certSZAXX9-UQYJXN46MMXK8K56/details/
	// https://app.codility.com/cert/view/certNFUECK-TM8HMFXB95JQQD49/details/
	public static String solution2(String S, int K) {
		if(S == null || S.isEmpty())
			return S;
		int start = 0;
		
		int len = S.length();
		boolean full = K >= len ? true : false;
		
		char[] chars = S.toCharArray();

		while(K > 0 && start < len) {
			int index;
			if(full) {
				index = getMinIndex(chars, start, len-1);
			} else {
				index = getMinIndex(chars, start, start+K);
			}
			
			while(index > start && chars[index] < chars[index-1] && K > 0) {
				char temp = chars[index];
				chars[index] = chars[index-1];
				chars[--index] = temp;
				K--;
			}
			start++;
		}
		return new String(chars);
	}
	
	private static int getMinIndex(char[] chars, int start, int end) {
		int min = start;
		while(start++ < end) {
			if(chars[start] < chars[min])
				min = start;
		}
		return min;
	}

	// https://app.codility.com/cert/view/certHKDMBD-288HGSAFSTS3QEAM/details/
	// https://app.codility.com/cert/view/certPBFUR6-QX4E7SWRXD4TEU6B/details/
	// https://app.codility.com/cert/view/cert2TXFHA-RUZWY2KCWVXZ4G93/details/
	// https://app.codility.com/cert/view/certHE3CFM-TB52XF4F4KBV7V4P/details/
	// https://app.codility.com/cert/view/cert67JPGV-9HMUTJZ6PY7WTY3C/details/
	public static String solution(String S, int K) {
		if(S == null || S.isEmpty())
			return S;
		int org = K;
		int start = 0;
		
		int len = S.length();
		
		if(org >= len) {
			org = len-1;
		}
		List<Character> batch = new ArrayList<>(org+1);
		char[] chars = S.toCharArray();
		for(int i = 0; i <= org; i++) {
			batch.add(chars[i]);
		}
		Collections.sort(batch);

		while(!batch.isEmpty() && K > 0 && start < len-1) {
			int end = start+K < len ? start+K : len-1;
			int index = getMinIndex(chars, start, end);
			boolean isRemoved = batch.remove((Character)chars[index]);

			while(isRemoved && index > 0 && chars[index] < chars[index-1] && K > 0) {
				char temp = chars[index];
				chars[index] = chars[index-1];
				chars[--index] = temp;
				K--;
			}

			start++;

			if(start+K < len && start+K > end) {
				int pos = Collections.binarySearch(batch, chars[start+K]);
				if(pos < 0) {
					pos = -(pos+1);
				}
				batch.add(pos, chars[start+K]);
			}
		}
		return new String(chars);
	}

	private static int getIndex(char[] chars, int start, int end, char ch) {
		while(start <= end) {
			if(chars[start] == ch)
				return start;
			start++;
		}
		return -1;
	}
}
