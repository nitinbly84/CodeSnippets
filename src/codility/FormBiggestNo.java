package codility;

import java.util.Arrays;

//Technetium2019
public class FormBiggestNo {

	public static void main(String[] args) {
		int[][] arr =   {
				{6,6,6},
				{6,6,7},
				{6,6,6},
				{6,6,6}
		};
		System.out.println(getBiggestNo(arr));
	}

	// Silver Award : https://app.codility.com/cert/view/certW476VB-CJ9J4THYGYDPZPQ6/details/
	public static String getBiggestNo(int[][] arr) {
		int noOfRows = arr.length;
		int noOfCols = arr[0].length;
		String[] biggestNoForPrevRow = new String[noOfCols + 1];
		String[] biggestNoForCurRow = new String[noOfCols + 1];

		Arrays.fill(biggestNoForPrevRow, "");
		biggestNoForCurRow[0] = "";

		for(int i = 0; i < noOfRows; i++) {
			for(int j = 1; j <= noOfCols; j++) {
				String noFromTop = biggestNoForPrevRow[j];
				String noFromLeft = biggestNoForCurRow[j - 1];
				boolean isNoFromTopBigger = noFromTop.compareTo(noFromLeft) > 0;
				biggestNoForCurRow[j] = isNoFromTopBigger? noFromTop : noFromLeft;
				biggestNoForCurRow[j] += String.valueOf(arr[i][j - 1]);
			}
			//			System.out.println(Arrays.toString(biggestNoForCurRow));
			System.arraycopy(biggestNoForCurRow, 0, biggestNoForPrevRow, 0, noOfCols + 1);
			Arrays.fill(biggestNoForCurRow, "");
		}
		return biggestNoForPrevRow[noOfCols];
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
		int[] noOfNodesAtDistance = getNoOfNodesAtDistance(noOfDigitsInNo, maxNoOfNodesAtSameDistance);

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

	private static int[] getNoOfNodesAtDistance(int noOfDigitsInNo, int maxNoOfNodesAtSameDistance) {
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
