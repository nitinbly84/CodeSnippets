package codility;

import java.util.Arrays;

/**
 * @author nitin
 * @Date 20-Dec-2021
 */
public class CodeAlone2021 {
	
	public static void main(String[] args) {
//		System.out.println(solution("abbbbaa")); // 4
//		System.out.println(solution("aabababb")); // 2
//		System.out.println(solution("bbbababaaab")); // 0
//		System.out.println(solution("abbabb")); // -1
//		System.out.println(solution("b")); // -1
		System.out.println(solution1("ababab")); // 3--
//		System.out.println(solution("abbbbabbba")); // 7--
//		System.out.println(solution("aababb")); // 1--
//		System.out.println(solution("aabbaaaaabbaab")); // 2
//		System.out.println(solution("aabbaabbaabbaa")); // 3--
//		System.out.println(solution("aabaabaabaa")); // 4--
//		System.out.println(solution("baabaab")); // 6--
	}
	
	public static int solution(String S) {
		int result = 0;

		char[] charArray = S.toCharArray();
		int len = charArray.length;
		int[] aS = new int[len];
		int[] bS = new int[len];
		char cur = 'a';
		int pos = -1;
		int i = 0;
		int k = 0;
		int contA = 0;
		int contB = 0;
		int countA = 0;
		int countB = 0;

		for(char ch : charArray) {
			if(ch == cur) {
				countA++;
				if(pos == -1) {
					pos = i;
					k++;
				} else {
					aS[k++] = pos;
					if(pos == aS[pos])
						aS[pos] = i-aS[pos];
					else
						aS[pos] = Math.min(pos-aS[pos], i-pos);
					pos = i;
				}
				if(contA == 0)
					contA = 1;
				else
					contA++;
			}else {
				aS[k++] = Integer.MAX_VALUE;
				contA = 0;
			}
			if(contA == 3)
				break;
			i++;
		}
		if(countA < 3)
			return -1;
		k = 0;
		i = 0;
		cur = 'b';
		pos = -1;
		for(char ch : charArray) {
			if(ch == cur) {
				countB++;
				if(pos == -1) {
					pos = i;
					k++;
				} else {
					bS[k++] = i-pos-1;
					pos = i;
				}
				if(contB == 0)
					contB = 1;
				else
					contB++;
			} else {
				bS[k++] = Integer.MAX_VALUE;
				contB = 0;
			}
			if(contB == 3)
				break;
			i++;
		}
		if(countB < 3) {
			return -1;
		}
		if(contA == 3 && contB == 3)
			return 0;
		i = 0;
		if(contA != 3) {
			Arrays.sort(aS);
			while(aS[++i] == 0 && aS[i] != Integer.MAX_VALUE);
			result = aS[i-1]+aS[i];
		}
		i = 0;
		if(contB != 3) {
			Arrays.sort(bS);
			while(bS[++i] == 0 && bS[i] != Integer.MAX_VALUE);
			if(contA == 3)
				result = bS[i-1]+bS[i];
			else
				result+=bS[i];
		}

		return result;
}
	
	public static int solution2(String S) {
		int len = S.length();
		char[] chars = S.toCharArray();
		Node[] nodes = new Node[len];
		int prevA = -1;
		int prevB = -1;
		boolean isA = true;
		boolean isB = true;
		int countA = 0;
		int countB = 0;
		int contA = 1;
		int contB = 1;
		int i = 0;
		
		for(char cur : chars) {
			if(cur == 'b' && isB) {
				if(prevB != -1 && chars[i-1] == cur) {
					nodes[prevB].count++;
					nodes[prevB].right = i;
					nodes[i] = new Node(nodes[prevB].count, prevB, -1);	
					contB++;
				} else {
					if(prevB == -1) {
						nodes[i] = new Node(1, -1, -1);
					} else {
						nodes[i] = new Node(1, -1, -1);
						nodes[i].left = prevB;
						nodes[prevB].right = i;
					}
				}
				if(nodes[i].count == 3) {
					isB = false;
				}
				prevB = i;
				countB++;
			}
			else if(cur == 'a' && isA) {
				if(prevA != -1 && chars[i-1] == cur) {
					nodes[prevA].count++;
					nodes[prevA].right = i;
					nodes[i] = new Node(nodes[prevA].count, prevA, -1);
					contA++;
				} else {
					if(prevA == -1) {
						nodes[i] = new Node(1, -1, -1);
					} else {
						nodes[i] = new Node(1, -1, -1);
						nodes[i].left = prevA;
						nodes[prevA].right = i;
					}
				}
				if(nodes[i].count == 3) {
					isA = false;
				}
				prevA = i;
				countA++;
			}
			i++;
		}
		if(!isA && !isB)
			return 0;
		if(countA < 3 || countB < 3)
			return -1;
		
		int min = Integer.MAX_VALUE;
		int minI = -1;
		
		for(i = 0; i < len; i++) {
			if(isA && chars[i] == 'a') {
				if(nodes[i].left == -1 && nodes[nodes[i].right].count == 1) {
					min = (nodes[i].right-i-1) + (nodes[nodes[i].right].right-i-2);
				} else if(nodes[i].right != -1 && nodes[i].left != -1) {
					if(nodes[i].count == 1 && !isB)
						min = Math.min(min, ((nodes[i].right-i-1)+(i-nodes[i].left-1)));
					else if(chars[i] != chars[i-1]) {
						int next = Math.min(i-nodes[i].left-1, nodes[nodes[i].right].right-i-2 < 0 ? Integer.MAX_VALUE : nodes[nodes[i].right].right-i-2);
						min = Math.min(min, next);
					}
				}
			} else if(isB && chars[i] == 'b') {
				if(nodes[i].left == -1 && nodes[nodes[i].right].count == 1) {
					min = (nodes[i].right-i-1) + (nodes[nodes[i].right].right-i-2);
				} else if(nodes[i].right != -1 && nodes[i].left != -1) {
					if(nodes[i].count == 1 && !isA)
						min = Math.min(min, ((nodes[i].right-i-1)+(i-nodes[i].left-1)));
					else if(chars[i] != chars[i-1]) {
						int next = Math.min(i-nodes[i].left-1, nodes[nodes[i].right].right-i-2 < 0 ? Integer.MAX_VALUE : nodes[nodes[i].right].right-i-2);
						min = Math.min(min, next);
					}
				}
			}			
		}
		return min;
	}
	
	static class Node {
		public Node(int count, int left, int right) {
			this.count = count;
			this.left = left;
			this.right = right;
		}
		int count;
		int left;
		int right;
	}
	
	// https://app.codility.com/cert/view/certH5BDNE-ZHA6QTTX88QNRBQV/details/
	public static int solution1(String S) {
		int result = 0;
		
		char[] charArray = S.toCharArray();
		int len = charArray.length;
		
		if(len < 6)
			return -1;
		int[] aS = new int[len];
		int[] bS = new int[len];
		char cur = 'a';
		int pos = -1;
		int i = 0;
		int k = 0;
		int countA = 0;
		int countB = 0;
		boolean progress = true;
		
		for(char ch : charArray) {
			if(ch == cur) {
				countA++;
				if(pos == -1) {
					pos = i;
					aS[k++] = 0;
				} else {
					if(aS[k-1] != Integer.MAX_VALUE)
						aS[k++] = aS[k-1] - 1;
					else
						aS[k++] = i-pos-1;
					pos = i;
				}
				if(aS[k-1] == -2) {
					progress = false;
					break;
				}
			}else {
				aS[k++] = Integer.MAX_VALUE;
			}
			i++;
		}
		if(countA < 3)
			return -1;
		k = 0;
		i = 0;
		cur = 'b';
		pos = -1;
		for(char ch : charArray) {
			if(ch == cur) {
				countB++;
				if(pos == -1) {
					pos = i;
					bS[k++] = 0;
				} else {
					if(bS[k-1] != Integer.MAX_VALUE)
						bS[k++] = bS[k-1] - 1;
					else
						bS[k++] = i-pos-1;
					pos = i;
				}
				if(bS[k-1] == -2) {
					progress = progress || false;
					break;
				}
			} else {
				bS[k++] = Integer.MAX_VALUE;
			}
			i++;
		}
		if(countB < 3) {
			return -1;
		}
		if(!progress)
			return 0;
		i = 0;
		if(progress) {
			Arrays.sort(aS);
			while(i < len-1 && (aS[i++] == 0 || aS[i] == Integer.MAX_VALUE));
			result = aS[i-1]+aS[i];
		}
		i = 0;
		if(progress) {
			Arrays.sort(bS);
			while(i < len && (bS[i++] == 0 || bS[i] == Integer.MAX_VALUE));
			if(progress)
				result = bS[i-1]+bS[i];
			else
				result += bS[i];
		}
		
		return result;
	}
}
