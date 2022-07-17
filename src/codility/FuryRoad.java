package codility;

public class FuryRoad {
	
	public static void main(String[] args) {
		System.out.println(solution("ASAASS")); //115
		System.out.println(solution("SSA")); //80
		System.out.println(solution("SSSSAAA")); //175
	}
	
	// Golden Award : https://app.codility.com/cert/view/cert4RT276-ZDC86FYPQ2M5BK7B/details/
	// Golden Award with negligible improvement : https://app.codility.com/cert/view/certAU69B4-8ZH9YQ667V4Y5TB9/details/
	public static int solution(String R) {
		int result = Integer.MAX_VALUE;
		int len = R.length();
		int[] vals = {20, 30, 5, 40};
		int[][] time = new int[2][len];
		char[] patches = R.toCharArray();
		int i = -1, lastW = 0, lastS = 0;
		while(++i < len) {
			if(patches[i] == 'A')
				time[1][i] = lastS+vals[2];
			else
				time[1][i] = lastS+vals[3];
			if(patches[len-i-1] == 'A')
				time[0][len-i-1] = lastW+vals[0];
			else
				time[0][len-i-1] = lastW+vals[1];
			lastW = time[0][len-i-1];
			lastS = time[1][i];
		}
		result = Math.min(time[0][0], time[1][len-1]);
		for(i = 0; i < len-1; i++) {
			result = Math.min(result, time[0][i+1]+time[1][i]);
		}
		return result;
	}

}
