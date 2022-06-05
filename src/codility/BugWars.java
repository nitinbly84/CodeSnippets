package codility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nitin
 * @Date 08-May-2022
 */
public class BugWars {
	
	public static void main(String[] args) {
//		int[] A = {4, 1, 4, 3, 3};
//		int[] X = {8, 10, 11, 13, 100}; //4
//		int[] A = {0, 1000000};
//		int[] X = {1, 1000000}; //2
//		int[] A = {0};
//		int[] X = {1}; //1
//		int[] A = {0, 1};
//		int[] X = {1, 2}; //2
//		int[] A = {0, 900, 0, 0};
//		int[] X = {200, 300, 500, 1000}; //4
//		int[] A = {15, 70, 0, 60, 10};
//		int[] X = {20, 30, 50, 90, 95}; //5
//		int[] A = {5, 70, 0, 60, 10};
//		int[] X = {20, 30, 50, 90, 95}; //5
//		int[] A = {0,0,20,30,0,30,0,70};
//		int[] X = {10, 20, 30, 40, 50, 60, 70, 10}; //8
//		int[] A = {0,  0,  20, 30, 0,  20, 0,  15,  70};
//		int[] X = {10, 20, 30, 40, 50, 60, 70, 95, 100}; //9
//		int[] A = {5, 70, 0, 55, 10};
//		int[] X = {20, 30, 50, 90, 95}; //4
//		int[] A = {0, 10, 0};
//		int[] X = {1, 2, 3}; //3
//		int[] A = {0, 1, 0, 1, 1, 1, 0};
//		int[] X = {1, 2, 3, 4, 5, 6, 7}; //4
//		int[] A = {0,0,0,0,0,0,0,0,0,0,0,26,0,0,0,0,0,0,0,0,0,0,0,0,0};
//		int[] X = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}; //19
		int[] A = {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,68,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] X = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50}; //47
		
		long start = System.currentTimeMillis();
		System.out.println(solution(A, X)); 
		System.out.println(System.currentTimeMillis()-start);
	}
	
	// Silver Award : https://app.codility.com/cert/view/certJHXEWF-HNXG2MWH9WERE8KS/details/
	// Silver Award : https://app.codility.com/cert/view/cert2U7BKU-7NTBHUVHHPY87959/details/
	// https://app.codility.com/cert/view/cert55S9NB-G84VESRP952HW225/details/
	// Best : https://app.codility.com/cert/view/certDU4A7T-PBZB78G8GMXM4CPG/details/
	// https://app.codility.com/cert/view/certFCK3RB-CKSF2X4NHE3RTG7D/details/
	// Best : https://app.codility.com/cert/view/certGEUWDY-78PA8ZYKWUZXVJHV/details/
	public static int solution(int[] A, int[] X) {
        final int len = X.length;
		int result = 1;
		
//		result = Stream.iterate(0, i -> i < len, i -> i+1)
//		.dropWhile(i -> A[i] == 0)
//		.parallel()
//		.mapToInt(i -> {
//			int[] maxPetrol = new int[len];
//			int[] visitedTill = new int[len];
//			return trace(Arrays.copyOf(A, len), X, i, len, maxPetrol, visitedTill, 0, new int[len]);
//		})
//		.max()
//		.orElse(1);
		
		
		int i = 0;
		while(i < len) {
			if(A[i] == 0) {
				i++;
				continue;
			}
			result = Math.max(result, trace(A, X, i, len, new int[len], new int[len], 0, new int[len], new HashSet<Integer>()));
			i++;
		}
		return result;
    }
	
	private static int getBestResult(int[] A, int[] X, Set<Integer> invalids, int len, int[] ends) {
		int result = 0;
		if(ends[0] > ends[1])
			return 0;
		
		int pos = findMaxI(A, ends[0], ends[1]);
		if(pos >= 0 && pos < len) {
			result = Math.max(result, trace(Arrays.copyOf(A, A.length), X, pos, A.length, new int[A.length], new int[A.length], 0, new int[A.length], invalids));
			markInvalid(X, pos, A[pos], invalids);
			result+=getBestResult(A, X, invalids, len, new int[] {ends[0], pos-1});
			if(ends[0] != ends[1])
				result+=getBestResult(A, X, invalids, len, new int[] {pos+1, ends[1]-1});
		}
		return result;
	}
	
	private static int findMaxI(int[] A, int start, int end) {
		if(start < 0 || end >= A.length)
			return -1;
		int maxI = 0;
		for(int i = 0; i <= end; i++) {
			if(A[maxI] < A[i])
				maxI = i;
		}
		return maxI;
	}
	
	private static void markInvalid(int[] X, int maxI, int maxPetrol, Set<Integer> invalids) {
		int start = Arrays.binarySearch(X, X[maxI]-maxPetrol);
		if(start < 0)
			start = -(start+1);
		if(X[start] < X[maxI]-maxPetrol)
			start++;
		int end = Arrays.binarySearch(X, X[maxI]+maxPetrol);
		if(end < 0)
			end = -(end+1);
		if(end == X.length || X[end] > X[maxI]+maxPetrol)
			end--;
		while(start <= end && start < X.length)
			invalids.add(start++);
	}
	
    private static int trace(int[] stationPetrol, int[] X, int i, int len, int[] maxPetrol, int[] visitedTill, int visits, int[] visitedCounts, Set<Integer> invalids) {
    	if(invalids.contains(i))
    		return 0;
    	if(stationPetrol[i] == 0 || (visits < visitedTill[i] && maxPetrol[i] > stationPetrol[i] && visitedCounts[i] > 0))
			return 1;
		
		int fResult = 1;
		int bResult = 1;
		int fwdPos = i+1;
		int bwdPos = i-1;
		int usePetrol = stationPetrol[i];
		

		while(fwdPos < len && stationPetrol[fwdPos] == -1 && usePetrol >= X[fwdPos]-X[i]) {
			fwdPos++;
		}
		while(bwdPos >= 0 && stationPetrol[bwdPos] == -1 && usePetrol >= X[i]-X[bwdPos]) {
			bwdPos--;
		}
		invalids.add(i);
		if(fwdPos < len && stationPetrol[fwdPos] != -1 && usePetrol >= X[fwdPos]-X[i]) {
			stationPetrol[fwdPos] = usePetrol-(X[fwdPos]-X[i])+stationPetrol[fwdPos];
			stationPetrol[i] = -1;
			fResult += trace(stationPetrol, X, fwdPos, len, maxPetrol, visitedTill, visits+1, visitedCounts, invalids);
			stationPetrol[fwdPos] -= (usePetrol-(X[fwdPos]-X[i]));
		}
		if(bwdPos >= 0 && stationPetrol[bwdPos] != -1 && usePetrol >= X[i]-X[bwdPos]) {
			stationPetrol[bwdPos] = usePetrol-(X[i]-X[bwdPos])+stationPetrol[bwdPos];
			stationPetrol[i] = -1;
			bResult += trace(stationPetrol, X, bwdPos, len, maxPetrol, visitedTill, visits+1, visitedCounts, invalids);
			stationPetrol[bwdPos] -= (usePetrol-(X[i]-X[bwdPos]));
		}
		stationPetrol[i] = usePetrol;
		int result = Math.max(fResult, bResult);
		if(result > visitedCounts[i]) {
			maxPetrol[i] = usePetrol;
			visitedTill[i] = visits;
			visitedCounts[i] = result;
		}
		invalids.remove(i);
		return result;
	}
}
