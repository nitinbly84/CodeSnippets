package codility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Technetium2019 {

	private static StringBuilder longest = new StringBuilder();

	public static void main(String[] args) {
		//						int[][] A = {{6,6,6},{6,6,7},{6,6,6},{6,6,6}}; //Ans : 666766
		//												int[][] A = {{9,9,7},{9,7,2},{6,9,5},{9,1,2}}; //Ans : 997952
		//												int[][] A = {{1,1,1,1,1,1,1,1,1,1}}; //Ans : 1111111111
		//										int[][] A = {{1},{1},{1},{1},{1},{1},{1},{1},{1},{1}}; //Ans : 1111111111
		//										int[][] A = {{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,7,6,6},
		//												{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6},{6,6,6,6}}; //Ans : 66666666766666666
		//						int[][] A = {{5,1,6,1,5},{1,1,1,1,1},{6,2,8,1,8},{1,9,1,1,1},{9,1,3,1,9}};
		//				int[][] A = {{2,6},{2,6},{2,6},{2,6},{2,6},{2,6},{2,6},{6,7},{2,6},{2,6},{2,6},{2,6},{2,6},{2,6},{2,6}};
		//		int[][] A = {{6,6,6,6,6,6,6,6,6,6},{6,6,6,6,6,6,6,6,6,6},{6,6,6,7,6,6,6,6,6,6},{6,6,6,8,6,6,6,6,6,6},{6,6,6,6,9,6,6,6,6,6}}; 
		int r = 10000;
		int c = 10000;
		int[][] B = new int[r][c];
		int[][] A = buildArray(r,c,B);
		int rows = A.length;
		int cols = A[0].length;
		//		for(int i = 0; i < r; i++) {
		//			for(int j = 0; j < c; j++) {
		//				System.out.print(A[i][j] + " ");
		//			}
		//			System.out.println();
		//		}

		long start = System.nanoTime();
		//		StringBuilder sb = new StringBuilder();
		//		solution(0, 0, A, sb, rows, cols);
		//		System.out.println("Nishu: " + longest);
		//		System.out.println("Time taken by longer method : " + (System.nanoTime()-start) + " ns");

		start = System.nanoTime();
		String strA = solutionA(A);
		System.out.println("Ashu : " + strA);
		double time2 = (System.nanoTime()-start)/1000;
		System.out.println("Time taken by Ashu method  : " + (time2) + " ms");

		start = System.nanoTime();
		String strC = solution(A, rows, cols);
		System.out.println("NishuN : " + strC);
		double time3 = (System.nanoTime()-start)/1000;
		System.out.println("Time taken by NishuN method  : " + (time3) + " ms");

		start = System.nanoTime();
		String strB = solution3(B, rows, cols);
		System.out.println("NishuO : " + strB);
		double time1 = (System.nanoTime()-start)/1000;
		System.out.println("Time taken by NishuO method : " + (time1) + " ms");

		System.out.println("NishuN vs NishuO : " + strB.equals(strC));
		System.out.println("Improvement : " + ((float)(time1-time3)/time1)*100 + "%");
		System.out.println("NishuN vs Ashu : " + strA.equals(strC));
		System.out.println("Improvement : " + ((float)(time2-time3)/time2)*100 + "%");
		System.out.println("NishuO vs Ashu : " + strA.equals(strB));
		System.out.println("Improvement : " + ((float)(time2-time1)/time2)*100 + "%");
		int len = strC.length();

		int i = 0;
		if(!strB.equals(strC)) {
			System.out.print("NishuO : ");
			while(i < len) {
				if(strC.charAt(i) == strB.charAt(i))
					System.out.print(strB.charAt(i));
				else
					break;
				i++;
			}
		}


	}

	private static int[][] buildArray(int rows, int cols, int[][] B) {
		Random random = new Random(1);
		int[][] array = new int[rows][cols];
		int n;
		for(int i = 0; i < rows; i++) {
			int j = 0;
			for(; j < cols; j++) {
				n = Math.abs(random.nextInt()%10);
				array[i][j] = n;
				B[i][j] = n;
				//				array[i][j] = 6;
				//				if(i == rows/2 && j == cols/2)
				//					array[i][j] = 7;
			}
		}
		return array;
	}

	// https://app.codility.com/cert/view/certMTESPN-ZH48UQ67TBW557U7/details/
	public static void solution(int i, int j, int[][] A, StringBuilder sb, int rows, int cols) {
		sb.append(Integer.toString(A[i][j]));
		int slen = sb.length();
		int len = longest.length();
		if(len >= slen) {
			int lent = slen;
			while(lent > 0) {
				if(sb.charAt(lent-1) > longest.charAt(lent-1)) {
					longest.setLength(0);
					longest.append(sb);
				} else if(sb.charAt(lent-1) < longest.charAt(lent-1) && lent == len)
					return;
				lent--;
			}
		}
		else if(len < slen || longest.length() == 0) {
			longest.setLength(0);
			longest.append(sb);
		}
		else
			return;
		if(i < rows-1) {
			StringBuilder temp = new StringBuilder();
			temp.append(sb);
			solution(i+1, j, A, temp, rows, cols);
		}
		if(j < cols-1) {
			StringBuilder temp = new StringBuilder();
			temp.append(sb);
			solution(i, j+1, A, temp, rows, cols);
		}
	}

	// https://app.codility.com/cert/view/certRZFBKD-UPKMAYUKZQ6BJQ92/details/
	public static String solution1(int[][] A, int rows, int cols) {
		int len = rows+cols-1;
		int[] arr = new int[len];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(arr[i+j] < A[i][j]) {
					arr[i+j] = A[i][j];
				}
			}
		}
		StringBuilder sb = new StringBuilder(rows+cols-1);
		for(int i = 0; i < len; i++)
			sb.append(arr[i]);
		String result = sb.toString();

		return result;
	}

	// https://app.codility.com/cert/view/cert3FXUQP-M8CE4YVA6PEAP7EY/details/
	public static String solution2(int[][] A, int rows, int cols) {
		int len = rows+cols-1;
		int[] arr = new int[len];
		for(int i = 0; i < rows; i++) {
			boolean replace = false;
			for(int j = 0; j < cols; j++) {
				if(arr[i+j] < A[i][j]) {
					int a = i;
					int b = j;

					if(arr[a+b] == 0) {
						replace = true;
						arr[i+j] = A[i][j];
						continue;						
					}
					while(b > 0) {
						b--;
						if(arr[a+b] != A[a][b]) {
							replace = false;
							break;
						} else
							replace = true;
					}
					if(!replace) {
						b = j;
						while(a > 0) {
							a--;
							if(arr[a+b] != A[a][b]) {
								replace = false;
								break;
							} else
								replace = true;
						}
					}
				}
				if(replace)
					arr[i+j] = A[i][j];
			}
		}
		StringBuilder sb = new StringBuilder(rows+cols-1);
		for(int i = 0; i < len; i++)
			sb.append(arr[i]);
		String result = sb.toString();

		return result;
	}

	// https://app.codility.com/cert/view/certYMFGR9-79C2RQVHKDC63TFF/details/
	// Silver Award : https://app.codility.com/cert/view/certBUN7D8-7E8R5TC3AV7EVGVD/details/
	// Silver Award : https://app.codility.com/cert/view/cert9R2JS5-TK22FV3DPYCMZXQP/details/
	// Silver Award : https://app.codility.com/cert/view/certA6UJZN-QF8TJZF4Y8AFNDNP/details/
	// Silver Award : https://app.codility.com/cert/view/certXCJQ7Y-W9X33WPEB6CPVE3B/details/
	// Silver Award : https://app.codility.com/cert/view/certNB8VAN-ADBSMP348T6Z9F5N/details/
	// Golden Award : https://app.codility.com/cert/view/cert68DQVX-63R98XC2HJNX7GKB/details/
	// Best Golden Award : https://app.codility.com/cert/view/certVUTHYR-F8R2ZD2UV83WWJB4/details/
	public static String solution3(int[][] A, int rows, int cols) {
		Node max = new Node(-1, -1, -1);
		int len = rows+cols-1;
		StringBuilder arr = new StringBuilder(len);
		LinkedList<Node> queue = new LinkedList<>();
		Node node = new Node(0, 0, A[0][0]);
		queue.add(node);
		arr.append(A[0][0]);
		len--;
		while(len > 0) {
			LinkedList<Node> children = new LinkedList<>();
			int added = 0;
			while(!queue.isEmpty()) {
				node = queue.pop();
				int nextI = node.i+1;
				int nextJ = node.j+1;
				int curI = node.i;
				int curJ = node.j;

				int child1 = -1;
				int child2 = -1;
				if(curI < rows-1 && A[nextI][curJ] != -1) {
					child1 = A[nextI][curJ];
					A[nextI][curJ] = -1;
				}
				if(curJ < cols-1 && A[curI][nextJ] != -1) {
					child2 = A[curI][nextJ];
					A[curI][nextJ] = -1;
				}

				if(((child1 != -1 && child2 != -1 && child1 > child2) || 
						(child1 != -1 && child2 == -1)) && max.data <= child1) {
					if(max.data < child1) {
						max.i = nextI;
						max.j = curJ;
						max.data = child1;
						children = new LinkedList<>();
					} else {
						node.i = nextI;
						node.j = curJ;
						node.data = child1;
						children.add(node);
						added = 1;
					}


				}
				else if(((child1 != -1 && child2 != -1 && child2 > child1) || 
						(child1 == -1 && child2 != -1)) && max.data <= child2) {
					if(max.data < child2) {
						max.i = curI;
						max.j = nextJ;
						max.data = child2;
						children = new LinkedList<>();
					} else {
						node.i = curI;
						node.j = nextJ;
						node.data = child2;
						children.add(node);
						added = 1;
					}
				}
				else if(child1 != -1 && child2 != -1 && child2 == child1 
						&& max.data <= child1) {
					if(max.data < child1) {
						children = new LinkedList<>();
						max.i = nextI;
						max.j = curJ;
						max.data = child1;
					}
					Node child = new Node(curI, nextJ, child1);
					children.add(child);
					child = new Node(nextI, curJ, child2);
					children.add(child);
					added = 1;
				}
			}

			if(max.data != -1) {
				arr.append(max.data);
				queue.add(max);
			}
			max = new Node(-1, -1, -1);

			if(added != 0)
				queue.addAll(children);
			len--;
		}

		return arr.toString();
	}
	
	// Best Golden Award : https://app.codility.com/cert/view/certAM9BQC-JYUDSQEVG9VPZYVX/details/
	public static String solution(int[][] A, int rows, int cols) {
		Cell max = new Cell(-1,-1,-1);
		StringBuilder arr = new StringBuilder(rows+cols-1);
		Cell cur = new Cell(0, 0, A[0][0]);
		Cell curC = null;
		arr.append(A[0][0]);
		while(cur != null) {
			int nextI = cur.i+1;
			int nextJ = cur.j+1;
			int curI = cur.i;
			int curJ = cur.j;

			int child1 = -1;
			int child2 = -1;
			if(nextI < rows && A[nextI][curJ] != -1) {
				child1 = A[nextI][curJ];
				A[nextI][curJ] = -1;
			}
			if(nextJ < cols && A[curI][nextJ] != -1) {
				child2 = A[curI][nextJ];
				A[curI][nextJ] = -1;
			}

			if(child1 > child2) {
				if(max.data < child1) {
					max = new Cell(nextI, curJ, child1);
					curC = max;
				} else if(max.data == child1) {
					curC.next = new Cell(nextI, curJ, child1);
					curC = curC.next;
				}


			}
			else if(child2 > child1) {
				if(max.data < child2) {
					max = new Cell(curI, nextJ, child2);
					curC = max;
				} else if(max.data == child2) {
					curC.next = new Cell(curI, nextJ, child2);
					curC = curC.next;
				}
			}
			else if(child1 != -1 && child2 == child1) {
				if(max.data < child1) {
					max = new Cell(nextI, curJ, child1);
					curC = max;
					curC.next = new Cell(curI, nextJ, child2);
					curC = curC.next;
				} else if(max.data == child1) {
					Cell child = new Cell(curI, nextJ, child1);
					curC.next = child;
					curC = child;
					child = new Cell(nextI, curJ, child2);
					curC.next = child;
					curC = child;
				}
			}
			cur = cur.next;
			if(cur != null) {
				continue;
			} else {
				if(max.data != -1) {
					arr.append(max.data);
					cur = max;
				}
				max = new Cell(-1,-1,-1);
			}
		}
		return arr.toString();
	}
	
	public static String solutionA(int[][] arr) {
		if(arr == null || arr.length == 0 || arr[0].length == 0){
			return "";
		}
		int noOfRows = arr.length;
		int noOfCols = arr[0].length;

		int noOfDigitsInNo = noOfRows + noOfCols - 1;
		int maxNoOfNodesAtSameDistance = Math.min(noOfRows, noOfCols);

		char[] finalString = new char[noOfDigitsInNo];
		boolean[] prevLevelNodesReachable = new boolean[maxNoOfNodesAtSameDistance];
		boolean[] curLevelNodesReachable = new boolean[maxNoOfNodesAtSameDistance];
		int[] noOfNodesAtDistance = getNoOfNodesAtDistanceA(noOfDigitsInNo, maxNoOfNodesAtSameDistance);

		//    System.out.println("noOfDigitsInNo " + noOfDigitsInNo);
		//    System.out.println("maxNoOfNodesAtSameDistance " + maxNoOfNodesAtSameDistance);
		//    System.out.println("noOfNodesAtDistance " + Arrays.toString(noOfNodesAtDistance));
		finalString[0] = (char)(arr[0][0] + 48);
		prevLevelNodesReachable[0] = true;
		int noOfNodesAtPrevLevel = noOfNodesAtDistance[0];

		int maxDistanceToProcess = noOfDigitsInNo - 2;
		for(int distance = 1; distance <= maxDistanceToProcess; distance++) {
			int noOfNodesAtCurLevel = noOfNodesAtDistance[distance];
			// mark the current level nodes which are reachable from previous level reachable nodes
			for(int prevLevelNodeNo = 0; prevLevelNodeNo < noOfNodesAtPrevLevel; prevLevelNodeNo++) {
				if(prevLevelNodesReachable[prevLevelNodeNo]) {
					if(noOfNodesAtCurLevel > noOfNodesAtPrevLevel) {
						curLevelNodesReachable[prevLevelNodeNo] = true;
						curLevelNodesReachable[prevLevelNodeNo + 1] = true;
					} else if(noOfNodesAtCurLevel == noOfNodesAtPrevLevel) {
						if(noOfRows < noOfCols) {
							curLevelNodesReachable[prevLevelNodeNo] = true;
							if(prevLevelNodeNo < (noOfNodesAtPrevLevel - 1))
								curLevelNodesReachable[prevLevelNodeNo + 1] = true;
						} else {
							if(prevLevelNodeNo != 0)
								curLevelNodesReachable[prevLevelNodeNo - 1] = true;
							curLevelNodesReachable[prevLevelNodeNo] = true;
						}
					} else {
						if(prevLevelNodeNo != 0)
							curLevelNodesReachable[prevLevelNodeNo - 1] = true;
						if(prevLevelNodeNo < (noOfNodesAtPrevLevel - 1))
							curLevelNodesReachable[prevLevelNodeNo] = true;
					}
				}
			}
			//                      System.out.println("Distance [" + distance + "] reachable nodes from Prev " + Arrays.toString(curLevelNodesReachable));
			int curLevelStartRow = 0;
			int curLevetStartCol = Math.min(distance, noOfCols - 1);
			if(distance >= noOfCols) {
				curLevelStartRow = distance - noOfCols + 1;
			}
			int curLevelRow = curLevelStartRow;
			int curLevelCol = curLevetStartCol;
			// find max digit at cur level from the nodes which are reachable from prev level
			int maxDigit = -1;
			for(int curLevelNodeNo = 0; curLevelNodeNo < noOfNodesAtCurLevel; curLevelNodeNo++) {
				int curNodeValue = arr[curLevelRow++][curLevelCol--];
				if(curLevelNodesReachable[curLevelNodeNo] && maxDigit < curNodeValue) {
					maxDigit = curNodeValue;
				}
			}
			finalString[distance] = (char)(maxDigit + 48);
			//                      System.out.println("MaxDigit at distance [" + distance + "] is " + maxDigit);
			// Mark the nodes which don't have the max reachable value as unreachable
			curLevelRow = curLevelStartRow;
			curLevelCol = curLevetStartCol;
			for(int curLevelNodeNo = 0; curLevelNodeNo < noOfNodesAtCurLevel; curLevelNodeNo++) {
				int curNodeValue = arr[curLevelRow++][curLevelCol--];
				if(curLevelNodesReachable[curLevelNodeNo] && curNodeValue != maxDigit) {
					curLevelNodesReachable[curLevelNodeNo] = false;
				}
			}
			//                      System.out.println("Distance [" + distance + "] reachable nodes final " + Arrays.toString(curLevelNodesReachable));

			// set prev as current
			System.arraycopy(curLevelNodesReachable, 0, prevLevelNodesReachable, 0, noOfNodesAtCurLevel);
			noOfNodesAtPrevLevel = noOfNodesAtCurLevel;
			Arrays.fill(curLevelNodesReachable, false);
		}
		finalString[noOfDigitsInNo - 1] = (char)(arr[noOfRows - 1][noOfCols - 1] + 48);
		return new String(finalString);

	}

	private static int[] getNoOfNodesAtDistanceA(int noOfDigitsInNo, int maxNoOfNodesAtSameDistance) {
		int noOfNodesAtCurLevelToIncreaseTillDistance = maxNoOfNodesAtSameDistance - 1;
		int noOfNodesAtCurLevelToDecreaseAfterDistance = noOfDigitsInNo - maxNoOfNodesAtSameDistance;
		int[] noOfNodesAtDistance = new int[noOfDigitsInNo];
		int noOfNodes = 0;
		for(int distance = 0; distance < noOfDigitsInNo; distance++) {
			if(distance <= noOfNodesAtCurLevelToIncreaseTillDistance) {
				noOfNodesAtDistance[distance] = ++noOfNodes;
			} else if(distance > noOfNodesAtCurLevelToDecreaseAfterDistance) {
				noOfNodesAtDistance[distance] = --noOfNodes;
			} else {
				noOfNodesAtDistance[distance] = noOfNodes;
			}
		}
		return noOfNodesAtDistance;
	}
}

class Node {
	public int i;
	public int j;
	public int data;

	public Node(int i, int j, int data) {
		this.i = i;
		this.j = j;
		this.data = data;
	}
}

class Cell {
	public int i;
	public int j;
	public int data;
	public Cell next = null;

	public Cell(int i, int j, int data) {
		this.i = i;
		this.j = j;
		this.data = data;
	}
}
