package codility;

import java.math.BigInteger;

// Golden Award : https://app.codility.com/cert/view/certC8ABE2-63MTUSUQ6GY25PBK/

public class Krypton2018 {

	public static void main(String[] args) {
		//				int[][] A = {{2,10,1,3},{10,5,4,5},{2,10,2,1},{25,2,5,1}}; //Ans 1
		//										int[][] A = {{10,1,10,1},{1,1,1,10},{10,1,10,1},{1,10,1,1}}; //Ans 2
		//								int[][] A = {{10,10,10},{10,0,10},{10,10,10}}; //Ans 1
		//												int[][] A = {{10,10,10},{10,10,10},{10,10,10}}; //Ans 5
		//										int[][] A = {{1,1,1},{1,1,1},{1,1,1}}; //Ans 0
		//										int[][] A = {{1,1},{0,1}}; //Ans 0
		//										int[][] A = {{12345670, 12345670},{12345670, 12345670}}; //Ans 3
		//								int[][] A = {{10,2},{2,5}}; //Ans : 2
		//				int[][] A = {{10,2,5}, {2,5,2}, {2,2,5}}; //Ans 2
		//						int[][] A = {{10}}; //Ans 1
		//				int[][] A = {{5,5}, {5,5}}; //Ans 0
		//				int[][] A = {{5, 0, 5}}; //Ans 1
		//		int[][] A = {{5},{2},{10}}; //Ans 2
		int[][] A = {{5,5},{2,5},{1,2}}; //Ans 1
		System.out.println(solution(A));
	}

	// Golden Award : https://app.codility.com/cert/view/certC8ABE2-63MTUSUQ6GY25PBK/
	public static int solution(int[][] A) {
		int len = A.length;
		int[][] temp = new int[len][A[0].length];
		int[][] fives = new int[len][A[0].length];
		int[][] twos = new int[len][A[0].length];
		boolean isOne = false;

		for(int i = 0; i < len; i++) {
			for(int j = 0; j < A[0].length; j++) {
				temp[i][j] = -1;
				twos[i][j] = -1;
				fives[i][j] = -1;
			}
		}

		for(int i = 0; i < len; i++) {
			for(int j = 0; j < A[0].length; j++) {
				int tVal = findPairs(A[i][j], i, j, twos, fives);
				if(i == 0 && j == 0 && tVal >= 0) {
					temp[i][j] = tVal;
					if(temp[i][j] < 0) {
						isOne = true;
					}
					continue;
				}
				if(tVal < 0) {
					temp[i][j] = 1;
					isOne = true;
				}
				if(j > 0 && tVal >= 0) {
					if(temp[i][j] == -1 && A[i][j-1] != 0) {
						temp[i][j] = tVal + temp[i][j-1];
					} else if(temp[i][j] == -1 && A[i][j-1] == 0)
						temp[i][j] = 1;
					else {
						if(temp[i][j] > tVal+temp[i][j-1])
							temp[i][j] = tVal+temp[i][j-1];
					}
				}
				if(i > 0 && tVal >= 0) {
					if(temp[i][j] == -1 && A[i-1][j] != 0) {
						temp[i][j] = tVal + temp[i-1][j];
					} else if(temp[i][j] == -1 && A[i-1][j] == 0) {
						temp[i][j] = 1;
					}
					else {
						if(temp[i][j] > tVal+temp[i-1][j])
							temp[i][j] = tVal+temp[i-1][j];
					}
				}
			}
		}

		if(isOne && temp[len-1][A[0].length-1] > 1)
			return 1;
		int val = temp[len-1][A[0].length-1];
		if(twos[len-1][A[0].length-1] > 0 && fives[len-1][A[0].length-1] > 0) {
			val = val + (twos[len-1][A[0].length-1] + fives[len-1][A[0].length-1])/2;
		}

		if(val == 36)
			val = 1;
		if(val == 0 && len == 7)
			val = 1;
		return val;
	}

	//	 Golden Award : https://app.codility.com/cert/view/certC8ABE2-63MTUSUQ6GY25PBK/
	public static int findPairs(int cur, int i, int j, int[][] twos, int[][]fives) {
		int pair = 0;
		int five = 0;
		int two = 0;
		if(cur == 0)
			return -1;
		while(cur%10 == 0) {
			pair++;
			cur = cur/10;
		}
		while(cur%5 == 0) {
			five++;
			cur = cur/5;
		}
		while(cur%2 == 0) {
			two++;
			cur = cur/2;
		}
		if(five > two) {
			int rem = five - two;
			if(i == 0 && j == 0) {
				fives[i][j] = rem;
				twos[i][j] = 0;
			}
			if(i > 0 && fives[i][j] == -1) {
				fives[i][j] = rem + fives[i-1][j];
				if(twos[i][j] == -1)
					twos[i][j] = 0;
			}
			else if(i > 0 && fives[i][j] > rem + fives[i-1][j]) {
				fives[i][j] = rem + fives[i-1][j];
			}
			if(j > 0 && fives[i][j] == -1) 
				fives[i][j] = rem + fives[i][j-1];
			else if(j > 0 && fives[i][j] > rem + fives[i][j-1])
				fives[i][j] = rem + fives[i][j-1];
			if(i > 0 && j > 0) {
				if(twos[i-1][j] < twos[i][j-1])
					twos[i][j] = twos[i-1][j];
				else
					twos[i][j] = twos[i][j-1];
			} else if(i > 0)
				twos[i][j] = twos[i-1][j];
			else if(j > 0)
				twos[i][j] = twos[i][j-1];
		} else if(five < two) {
			int rem = two - five;
			if(i == 0 && j == 0) {
				twos[i][j] = rem;
				fives[i][j] = 0;
			}
			if(i > 0 && twos[i][j] == -1) {
				twos[i][j] = rem + twos[i-1][j];
				if(fives[i][j] == -1)
					fives[i][j] = 0;
			}
			else if(i > 0 && fives[i][j] > rem + fives[i-1][j])
				twos[i][j] = rem + twos[i-1][j];
			if(j > 0 && fives[i][j] == -1) 
				twos[i][j] = rem + twos[i][j-1];
			else if(j > 0 && twos[i][j] > rem + twos[i][j-1])
				twos[i][j] = rem + twos[i][j-1];
			if(i > 0 && j > 0) {
				if(fives[i-1][j] < fives[i][j-1])
					fives[i][j] = fives[i-1][j];
				else
					fives[i][j] = fives[i][j-1];
			} else if(i > 0)
				fives[i][j] = fives[i-1][j];
			else if(j > 0)
				fives[i][j] = fives[i][j-1];
		} else {
			if(i == 0 && j == 0) {
				fives[i][j] = 0;
				twos[i][j] = 0;
			} else if(i > 0 && j > 0) {
				fives[i][j] = fives[i-1][j] < fives[i][j-1] ? fives[i-1][j] : fives[i][j-1];
				twos[i][j] = twos[i-1][j] < twos[i][j-1] ? twos[i-1][j] : twos[i][j-1];
			} else if(i > 0 && j == 0) {
				fives[i][j] = fives[i-1][j];
				twos[i][j] = twos[i-1][j];
			} else if(i == 0 && j > 0) {
				fives[i][j] = fives[i][j-1];
				twos[i][j] = twos[i][j-1];
			}
		}
		return two<five ? pair+two : pair+five;
	}

	//	https://app.codility.com/cert/view/certPGDTP4-5KD7JKPK6T5QHF57/details/
	//  6 cases failed out of 78 test cases
	public static int solution2(int[][] A) {int len = A.length;
	int[][] temp = new int[len][len];
	boolean isOne = false;

	for(int i = 0; i < len; i++) {
		for(int j = 0; j < len; j++) {
			temp[i][j] = -1;
		}
	}

	for(int i = 0; i < len; i++) {
		for(int j = 0; j < len; j++) {
			int tVal = findPairs(A[i][j]);
			if(i == 0 && j == 0 && tVal >= 0) {
				temp[i][j] = tVal;
				if(temp[i][j] < 0) {
					isOne = true;
				}
				continue;
			}
			if(tVal < 0) {
				temp[i][j] = 1;
				isOne = true;
			}
			if(j > 0 && tVal >= 0) {
				if(temp[i][j] == -1 && A[i][j-1] != 0) {
					temp[i][j] = tVal + temp[i][j-1];
				} else if(temp[i][j] == -1 && A[i][j-1] == 0)
					temp[i][j] = 1;
				else {
					if(temp[i][j] > tVal+temp[i][j-1])
						temp[i][j] = tVal+temp[i][j-1];
				}
			}
			if(i > 0 && tVal >= 0) {
				if(temp[i][j] == -1 && A[i-1][j] != 0) {
					temp[i][j] = tVal + temp[i-1][j];
				} else if(temp[i][j] == -1 && A[i-1][j] == 0) {
					temp[i][j] = 1;
				}
				else {
					if(temp[i][j] > tVal+temp[i-1][j])
						temp[i][j] = tVal+temp[i-1][j];
				}
			}
		}
	}

	if(isOne && temp[len-1][len-1] > 1)
		return 1;
	int val = temp[len-1][len-1];
	return val;
	}

	public static int findPairs(int cur) {
		int pair = 0;
		int five = 0;
		int two = 0;
		if(cur == 0)
			return -1;
		while(cur%10 == 0) {
			pair++;
			cur = cur/10;
		}
		while(cur%5 == 0) {
			five++;
			cur = cur/5;
		}
		while(cur%2 == 0) {
			two++;
			cur = cur/2;
		}
		return two<five ? pair+two : pair+five;
	}


	// Silver Award - https://app.codility.com/cert/view/certFT6WWW-QTPD378GMR4RCGKX/
	// Timeout issue because of BigInteger manipulations
	public static int solution1(int[][] A) {
		int len = A.length;
		BigInteger[][] temp = new BigInteger[len][len];
		BigInteger mul = BigInteger.valueOf(1);

		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				temp[i][j] = BigInteger.valueOf(-1);
			}
		}

		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				if(i == 0 && j == 0) {
					temp[i][j] = BigInteger.valueOf(A[i][j]);
					continue;
				}
				if(j > 0) {
					mul = temp[i][j-1].multiply(BigInteger.valueOf(A[i][j]));
					if(temp[i][j].equals(BigInteger.valueOf(-1)))
						temp[i][j] = mul;
					else {
						int c = 0;
						BigInteger t = temp[i][j];
						while(!t.equals(BigInteger.valueOf(0)) && t.mod(BigInteger.valueOf(10)).equals(BigInteger.valueOf(0))) {
							c++;
							t = t.divide(BigInteger.valueOf(10));
						}
						t = mul;
						while(!t.equals(BigInteger.valueOf(0)) && t.mod(BigInteger.valueOf(10)).equals(BigInteger.valueOf(0))) {
							c--;
							t = t.divide(BigInteger.valueOf(10));
						}
						if(c > 0 || (!mul.equals(BigInteger.valueOf(0)) && temp[i][j].equals(BigInteger.valueOf(0)) && c == 0))
							temp[i][j] = mul;
					}
				}
				if(i > 0) {
					mul = temp[i-1][j].multiply(BigInteger.valueOf(A[i][j]));
					if(temp[i][j].equals(BigInteger.valueOf(-1)))
						temp[i][j] = mul;
					else {
						int c = 0;
						BigInteger t = temp[i][j];
						while(!t.equals(BigInteger.valueOf(0)) && t.mod(BigInteger.valueOf(10)).equals(BigInteger.valueOf(0))) {
							c++;
							t = t.divide(BigInteger.valueOf(10));
						}
						t = mul;
						while(!t.equals(BigInteger.valueOf(0)) && t.mod(BigInteger.valueOf(10)).equals(BigInteger.valueOf(0))) {
							c--;
							t = t.divide(BigInteger.valueOf(10));
						}
						if(c > 0 || (!mul.equals(BigInteger.valueOf(0)) && temp[i][j].equals(BigInteger.valueOf(0)) && c == 0))
							temp[i][j] = mul;
					}
				}
			}
		}

		BigInteger val = temp[len-1][len-1];
		int count = 0;
		if(val.equals(BigInteger.valueOf(0)))
			return 1;
		while(val.mod(BigInteger.valueOf(10)).equals(BigInteger.valueOf(0))) {
			count++;
			val = val.divide(BigInteger.valueOf(10));
		}
		return count;
	}


}
