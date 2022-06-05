package codility;

public class Accedo {
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,4,-1,2,3}));
//		System.out.println(solution("abccb"));
	}
	
	public static int solution(int[] A) {
		if(A.length == 0 || A[0] == -1)
			return 0;
		
		int count = 1;
		int i = 0;
		
		while(A[i] != -1) {
			i = A[i];
			count++;
		}
		return count;
    }
	
	static String solution2(String S) {
        int[] occurrences = new int[26];
        for (char ch : S.toCharArray()) {
            occurrences[ch - 'a']++;
        }

        char best_char = 'a';
        int  best_res  = 0;

        for (int i = 0; i < 26; i++) {
            if (occurrences[i] > best_res) {
                best_char = (char)((int)'a' + i);
                best_res  = occurrences[i];
            }
        }

        return Character.toString(best_char);
    }

}
