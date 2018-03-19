package algorithms;

/**
 * To find the break point in the string so that both parts have same number of open & close brackets
 * i.e. if left part has 2 open brackets then right part must have same number of closing brackets.
 * Please take the help of below given test cases to understand the problem.
 * @author nitin
 *
 */
public class NGData {
	
	public static void main(String[] args) {
//		System.out.println(solution("(())))(")); //Ans : 4
//		 System.out.println(solution("(())")); //Ans : 2
//		 System.out.println(solution(")))")); //Ans : 3
		System.out.println(solution("(((")); //Ans : 0
//		System.out.println(solution("(((()))")); //Ans : 3
//		System.out.println(solution("(()))")); //Ans : 3
//		System.out.println(solution("((()()))")); //Ans : 4
	}
	
	/**
	 * @param S
	 * @return
	 */
	public static int solution(String S) {
		int len = S.length();
		int kMax = -1;
		int K = 0;
		int Kl = 0;
		int Kr = 0;
		int i = 0;
		int j = len-1;
		boolean open = false;
		boolean close = false;
		if(len == 0)
			return len;
		while(j >= 0) {
			if(S.charAt(i) == '(') {
				Kl++;
				open = true;
			}
			if(S.charAt(j) == ')' && j > i) {
				Kr++;
				close = true;
			}
			if(Kl == Kr && kMax <= Kl && K <= i) {
				kMax = Kl;
				K = i;
			} else if(Kl < Kr && kMax < Kr && K < j) {
				kMax = Kr;
				K = j;
			} else if(Kl > Kr && kMax < Kl && K < i) {
				kMax = Kl;
				K = i-1;
			}
			j--;
			i++;
			if(i > j)
				break;
		}

		if(!open && close)
			return len;
		else if (open && !close)
			return 0;
		return K+1;
	}	
}
