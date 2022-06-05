package codility;

public class Asml2018 {

	public static void main(String[] args) {
		//						String S = "MMMLMMM"; // 2 and K = 4
		//								String S = "MLMMLLM"; // 1 and K = 3
		//								String S = "MLMMMLMMMM"; // 2 and K = 2
		//								String S = "MMLLMLMLML"; // 0 and K = 2
		//				String S = "MLLLMLMLML"; // 1 and K = 2
		//				String S = "MLMLMLML"; // 1 and K = 2
		//				String S = "MLMLMLM"; // 2 and K = 2
		//				String S = "LLMMLLMMMMLLMMMLLLMM"; // 2 and K = 9
				String S = "LLLLLLLMMMLLLLMMMMMLLL"; // 0 and K = 5
//				String S = "LLMMLLMMM M MMM M MMM M MMM M MMLLLLMML"; //4 and K = 3
//		String S = "MMMMMMMMMMMMMMMMMM"; // 4 and K = 3
		int K = 5;
		System.out.println(solution(S, K));
	}

	public static int solution(String S, int K) {
		int result = 0;
		int m = 0, done = 0, currM = 0, lm = 0, pm = 0, om = 0, min = Integer.MAX_VALUE;
		char[] chars = S.toCharArray();

		for(char ch : chars) {
			if(ch == 'M') {
				m++;
				currM++;
				if(currM > K) {
					result++;
					currM = 0;
					done++;
				} else if(currM == K) {
					done++;
				}
				if(m > K) {
					lm++;
					m = 0;
					if(min > lm) {
						min = lm;
					}
					lm = 0;
				}
			} else if(ch == 'L') {
				currM = 0;
				if(m < K && done == 0) {
					lm++;
					m++;
				} else if(m == K) {
					if(min > lm) {
						min = lm;
					}
					lm = 0;
					m = 0;
				}
			}
		}
		if(min > 100000)
			min = 0;
		if(done > 0)
			return result;
		else
			return min;
	}

	// Silver award - https://app.codility.com/cert/view/cert55AJVT-6XRVSGKNVWR5KBH5/details/
	public static int bestSolution(String S, int K) {
		int result = 0;
		int m = 0, done = 0, i = 0, currM = 0, lm = 0, min = Integer.MAX_VALUE;
		char[] chars = S.toCharArray();
		int len = S.length();

		for(char ch : chars) {
			if(ch == 'M') {
				m++;
				currM++;
				if(currM > K) {
					result++;
					currM = 0;
					m = 0;
					done++;
				}
			} else if(ch == 'L') {
				int j = 0;
				currM = 0;

				if(m < K) {
					j = i;
					while(m <= K && j < len) {
						if(chars[j] == 'M') {
							m++;
							if(m > K) {
								lm++;
							}
						} else if(chars[j] == 'L' && m < K){
							lm++;
							m++;
						} else
							break;
						j++;
					}
				}
				if(min > lm && m >= K) {
					min = lm;
				}
				lm = 0;
				m = 0;
			}
			i++;
		}
		if(min > 100000)
			min = 0;
		if(done > 0)
			return result;
		else
			return min;
	}

	// Best Result 42% - https://app.codility.com/cert/view/certZRQR6N-3B3S3U6H8536ARXV/details/
	public static int betterSolution(String S, int K) {
		int result = 0;
		int m = 0, done = 0, i = 0;
		char[] chars = S.toCharArray();
		int len = S.length();

		for(char ch : chars) {
			if(ch == 'M') {
				m++;
				if(m > K) {
					result++;
					m = 0;
					done++;
				}
			} else if(ch == 'L') {
				if((len - i - 2 == K - m || len - i - 1 < K) && m != K && done == 0) {
					result++;
					m++;
				} else if(m == K) {
					m = 0;
					done++;
				} else 
					m = 0;
			}
			i++;
		}
		return result;
	}

	public static int solution2(String S, int K) {

		int result = 0;
		char[] chars = S.toCharArray();
		int m = 0, lm = 0, ml = 0,done = 0;
		int i = 0;
		int len = S.length();

		for(int ch : chars) {
			if(ch == 'M') {
				m++;
				if(m > K) {
					result++;
					m = 0;
					ml++;
				}
			} else if(ch == 'L') {
				if(m == K-1 && i < len-1) {
					if(chars[i+1] != 'M') {
						m = 0;
						result++;
						lm++;
					} else {
						result++;
						m++;
						lm++;
					}
				} else if(m == K-1 && i == len-1) {
					m = 0;
					result++;
				} else {
					if(m == K && ml == 0)
						done++;
					m = 0;
				}
			}
			i++;
		}
		if(ml < result && done == 0 && ml != 0)
			return ml;
		if(done != 0)
			return 0;
		if(lm < result)
			return result - lm;
		return result;
	}

	/*
	 * Result is 28% -  https://app.codility.com/cert/view/certKW92UF-DY78AVUBRHA8CKKC/details/
	 * 
	 */
	public static int solution1(String S, int K) {
		int result = 0;
		int m = 0, lastL = 0, lCause = 0, mCause = 0, series = 0;
		char[] chars = S.toCharArray();
		int i = 0;
		int len = S.length();

		for(char ch : chars) {
			if(ch == 'L') {
				if(m < K && len-i >= K) {
					result++;
					lastL++;
					m++;
					lCause++;
					series++;
				} else if(m == K) {
					m = 0;
					series++;
				}
				lastL++;
			} else if(ch == 'M') {
				m++;
				if(m > K) {
					if(lastL > 0 && len-i >= K) {
						result--;
						lastL = 0;
						m = 1;
					}
					else {
						result++;
						m = 0;
						mCause++;
						lastL++;
						series++;
					} 
				} else if(lastL > 1 && lastL-1+m <= K) {
					m = lastL-1+m;
					result = result + lastL - 1;
				}
			}
			i++;
		}
		if(lCause > mCause && mCause != 0 && series != 0)
			return mCause;
		else if(lCause != 0 && lCause < result && series != 0)
			return lCause;
		else if(series != 0)
			return result;
		return 0;
	}
}
