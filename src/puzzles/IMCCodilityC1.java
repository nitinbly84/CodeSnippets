package puzzles;

public class IMCCodilityC1 {
	
	public static void main(String[] args) {
		System.out.println(solution("ABBAAA"));
	}
	
	public static int solution(String S) {
		int count = 0;
		int cel = 0;
		for(char ch : S.toCharArray()) {
			if(ch == 'A') {
				count++;
				if(count == 1)
				cel++;
			} else {
				count--;
			}
		}
		return cel;
	}

}
