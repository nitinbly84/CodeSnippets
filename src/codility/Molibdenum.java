package codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Molibdenum {
	static int s = 0;
	static int sum = Integer.MIN_VALUE;
	static Integer temp = null;
	static int val = 0;
	static int[] results;
	static int max = Integer. MIN_VALUE;
	static int maxV = 0;
	static Set<Integer> tempResults = new TreeSet<>();
	static int size = 0;
	public static void main(String[] args) {
		//												int[] A = {2,1,3,1,2,2,3};
		//												System.out.println(solution(3,5,A));
		//		int[] A = {1,2,2,1,2};
		//		System.out.println(solution(4,2,A));
		//				int[] A = {1,2,3};
		//				System.out.println(solution(1,3,A));
		//						int[] A = {1};
		//						System.out.println(solution(1,1,A));
		//				int[] A = {1,2,3};
		//				System.out.println(solution(3,3,A));
		//						int[] A = {3,4,5};
		//						System.out.println(solution(2,5,A));
//				int[] A = {1,1,1,1,1};
//				System.out.println(solution(2,5,A));
//		int[] A = {1,1,2,1,1};
//		System.out.println(solution(2,2,A));
		//		int[] A = {1,1,1,3,1,5,7};
		//		System.out.println(solution(4,7,A));
//				int[] A = {1,1,1,1,1,1};
//				System.out.println(solution(3,5,A));
		int[] A = {1};
		System.out.println(solution(1,1,A));
	}

	// Silver Award : https://app.codility.com/cert/view/certZ2UD77-8NGT8XUT8NFJM7YA/details/
	public static int[] solution1(int K, int M, int[] A) {

		int len = A.length;
		HashMap<Integer, Integer> counts = new HashMap<>();
		HashMap<Integer, Integer> countsCopy = new HashMap<>();
		HashSet<Integer> tempResults = new HashSet<>();

		for(int i : A) {
			if((temp = counts.get(i)) == null)
				counts.put(i, 1);
			else {
				counts.put(i, temp+1);
			}
		}

		countsCopy.putAll(counts);

		for(int i = 0; i < K; i++) {
			int num = A[i];
			if(countsCopy.get(num+1) == null) {
				countsCopy.put(num+1, 1);
				countsCopy.put(num, countsCopy.get(num)-1);
			}
			else {
				countsCopy.put(num+1, countsCopy.get(num+1)+1);
				countsCopy.put(num, countsCopy.get(num)-1);
			}
		}

		countsCopy.forEach((a,b) -> {
			if(max < b && b > len/2) {
				max = b;
				maxV = a;
			} else if(max == b) {
				maxV = -1;
			}
		});

		if(maxV > 0)
			tempResults.add(maxV);

		for(int i = 1; i+K <= len; i++) {
			int prev = A[i-1];
			int cur = A[i+K-1];
			if(countsCopy.get(prev) == null) {
				countsCopy.put(prev, 1);
				countsCopy.put(prev+1, countsCopy.get(prev+1)-1);
			}
			else {
				countsCopy.put(prev, countsCopy.get(prev)+1);
				countsCopy.put(prev+1, countsCopy.get(prev+1)-1);
			}
			if(countsCopy.get(cur+1) == null) {
				countsCopy.put(cur+1, 1);
				countsCopy.put(cur, countsCopy.get(cur)-1);
			} else {
				countsCopy.put(cur+1, countsCopy.get(cur+1)+1);
				countsCopy.put(cur, countsCopy.get(cur)-1);
			}

			max = 0;
			countsCopy.forEach((a,b) -> {
				if(max < b && b > len/2) {
					max = b;
					maxV = a;
				} else if(max == b) {
					maxV = -1;
				}
			});
			if(maxV > 0)
				tempResults.add(maxV);
		}

		results = new int[tempResults.size()];

		for(int i : tempResults) {
			results[s] = i;
			s++;
		}

		Arrays.sort(results);
		return results;
	}

	//	Silver Award : https://app.codility.com/cert/view/certAUQ2E2-MVUBQTFHC86RC3SX/details/
	public static int[] solution2(int K, int M, int[] A) {

		int len = A.length;
		HashMap<Integer, Integer> counts = new HashMap<>();
		HashMap<Integer, Integer> countsCopy = new HashMap<>();

		for(int i : A) {
			if((temp = counts.get(i)) == null)
				counts.put(i, 1);
			else {
				counts.put(i, temp+1);
			}
		}

		countsCopy.putAll(counts);

		for(int i = 0; i < K; i++) {
			int num = A[i];
			if(countsCopy.get(num+1) == null) {
				countsCopy.put(num+1, 1);
				countsCopy.put(num, countsCopy.get(num)-1);
			}
			else {
				countsCopy.put(num+1, countsCopy.get(num+1)+1);
				countsCopy.put(num, countsCopy.get(num)-1);
			}
		}

		countsCopy.forEach((a,b) -> {
			if(b > len/2) {
				tempResults.add(a);
			}
		});

		for(int i = 1; i+K <= len; i++) {
			int prev = A[i-1];
			int cur = A[i+K-1];

			if(countsCopy.get(prev) == null) {
				countsCopy.put(prev, 1);
				countsCopy.put(prev+1, countsCopy.get(prev+1)-1);
			}
			else {
				countsCopy.put(prev, countsCopy.get(prev)+1);
				countsCopy.put(prev+1, countsCopy.get(prev+1)-1);
			}
			if(countsCopy.get(cur+1) == null) {
				countsCopy.put(cur+1, 1);
				countsCopy.put(cur, countsCopy.get(cur)-1);
			} else {
				countsCopy.put(cur+1, countsCopy.get(cur+1)+1);
				countsCopy.put(cur, countsCopy.get(cur)-1);
			}

			countsCopy.forEach((a,b) -> {
				if(b > len/2) {
					tempResults.add(a);
				}
			});
		}

		results = new int[tempResults.size()];

		for(int i : tempResults) {
			results[s] = i;
			s++;
		}

		Arrays.sort(results);
		return results;
	}
	//  Silver Award : https://app.codility.com/cert/view/certNEE783-6XHAQY4P73A358JP/details/
	public static int[] solution3(int K, int M, int[] A) {

		int len = A.length;
		HashMap<Integer, Integer> counts = new HashMap<>();

		for(int i : A) {
			if((temp = counts.get(i)) == null)
				counts.put(i, 1);
			else {
				counts.put(i, temp+1);
			}
		}

		for(int i = 0; i < K; i++) {
			int num = A[i];
			Integer t = null;
			if((t=counts.get(num+1)) == null) {
				counts.put(num+1, 1);
				counts.put(num, counts.get(num)-1);
			}
			else {
				counts.put(num+1, t+1);
				counts.put(num, counts.get(num)-1);
			}
		}

		counts.forEach((a,b) -> {
			if(b > len/2) {
				tempResults.add(a);
			}
		});

		for(int i = 1; i+K <= len; i++) {
			int prev = A[i-1];
			int cur = A[i+K-1];
			Integer t = null;

			if((t=counts.get(prev)) == null) {
				counts.put(prev, 1);
				counts.put(prev+1, counts.get(prev+1)-1);
			}
			else {
				counts.put(prev, t+1);
				counts.put(prev+1, counts.get(prev+1)-1);
			}
			if((t=counts.get(cur+1)) == null) {
				counts.put(cur+1, 1);
				counts.put(cur, counts.get(cur)-1);
			} else {
				counts.put(cur+1, t+1);
				counts.put(cur, counts.get(cur)-1);
			}

			if(t > len/2)
				tempResults.add(cur+1);

		}

		results = new int[tempResults.size()];

		for(int i : tempResults) {
			results[s] = i;
			s++;
		}

		return results;
	}
	// Silver Award : https://app.codility.com/cert/view/certUF2K8W-DPVAUP65QV4ABST3/details/
	public static int[] solution4(int K, int M, int[] A) {

		int len = A.length;
		HashMap<Integer, Integer> counts = new HashMap<>();

		for(int i : A) {
			if((temp = counts.get(i)) == null)
				counts.put(i, 1);
			else {
				counts.put(i, temp+1);
			}
		}

		for(int i = 0; i < K; i++) {
			int num = A[i];
			if(num == M && K < len/2)
				continue;
			Integer t = null;
			if((t=counts.get(num+1)) == null) {
				counts.put(num+1, 1);
				counts.put(num, counts.get(num)-1);
			}
			else {
				counts.put(num+1, t+1);
				counts.put(num, counts.get(num)-1);
			}
		}

		counts.forEach((a,b) -> {
			if(b > len/2) {
				if(tempResults.add(a))
					size++;
			}
		});

		for(int i = 1; i+K <= len; i++) {
			int prev = A[i-1];
			int cur = A[i+K-1];
			Integer t = null;

			if((t=counts.get(prev)) == null) {
				counts.put(prev, 1);
				counts.put(prev+1, counts.get(prev+1)-1);
			}
			else {
				counts.put(prev, t+1);
				counts.put(prev+1, counts.get(prev+1)-1);
			}
			if(cur == M && K < len/2)
				continue;
			if((t=counts.get(cur+1)) == null) {
				counts.put(cur+1, 1);
				counts.put(cur, counts.get(cur)-1);
			} else {
				counts.put(cur+1, t+1);
				counts.put(cur, counts.get(cur)-1);
			}

			if(counts.get(cur+1) > len/2) {
				if(tempResults.add(cur+1))
					size++;
			}
			if(counts.get(prev) > len/2) {
				if(tempResults.add(prev))
					size++;
			}
			if(counts.get(cur) > len/2) {
				if(tempResults.add(cur))
					size++;
			}

		}

		results = new int[size];

		tempResults.forEach(a -> {
			results[s] = a;
			s++;
		});

		return results;
	}
	// Silver Award : https://app.codility.com/cert/view/certR6G4YP-AKSBP89FUZDBKA6F/details/ - best time performance
	public static int[] solution5(int K, int M, int[] A) {

		int len = A.length;
		HashMap<Integer, Integer> counts = new HashMap<>();

		for(int i : A) {
			if((temp = counts.get(i)) == null)
				counts.put(i, 1);
			else {
				if(counts.put(i, temp+1)+1 > len/2)
					max = i;
			}
		}

		for(int i = 0; i < K; i++) {
			int num = A[i];
			if(num == M && K < len/2)
				continue;
			Integer t = null;
			if((t=counts.get(num+1)) == null) {
				counts.put(num+1, 1);
				counts.put(num, counts.get(num)-1);
			}
			else {
				counts.put(num+1, t+1);
				counts.put(num, counts.get(num)-1);
			}
		}

		if(max > 0 && counts.get(max) > len/2 && tempResults.add(max)) {
			size++;
		} else {
			Iterator<Integer> itr = counts.keySet().iterator();
			while(itr.hasNext()) {
				max = itr.next();
				if(counts.get(max) > len/2 && tempResults.add(max)) {
					size++;
					break;
				}
			}
		}

		for(int i = 1; i+K <= len; i++) {
			int prev = A[i-1];
			int cur = A[i+K-1];
			Integer t = null;

			if((t=counts.put(prev, 1)) == null) {
				counts.put(prev+1, counts.get(prev+1)-1);
			}
			else {
				counts.put(prev, t+1);
				counts.put(prev+1, counts.get(prev+1)-1);
			}

			if((t=counts.get(cur+1)) == null) {
				counts.put(cur+1, 1);
				counts.put(cur, counts.get(cur)-1);
				t = 1;
			} else {
				t = counts.put(cur+1, t+1)+1;
				counts.put(cur, counts.get(cur)-1);
			}

			if(counts.get(prev) > len/2 && tempResults.add(prev)) {
				size++;
			} else if(t > len/2 && tempResults.add(cur+1)) {
				size++;
			} else if(counts.get(cur) > len/2 && tempResults.add(cur)) {
				size++;
			}

		}

		results = new int[size];

		tempResults.forEach(a -> {
			results[s] = a;
			s++;
		});

		return results;
	}
	
	// Failed : https://app.codility.com/cert/view/certPUZHXE-ESUP36VYUBWBT97M/details/
	public static int[] solution(int K, int M, int[] A) {

		int len = A.length;
		Map<Integer, Integer> counts = new TreeMap<>();

		for(int i : A) {
			if((temp = counts.get(i)) == null)
				counts.put(i, 1);
			else {
				if(counts.put(i, temp+1)+1 > len/2)
					max = i;
			}
		}

		for(int i = 0; i < K; i++) {
			int num = A[i];
			if(num == M && K < len/2)
				continue;
			Integer t = null;
			if((t=counts.get(num+1)) == null) {
				counts.put(num+1, 1);
				counts.put(num, counts.get(num)-1);
			}
			else {
				counts.put(num+1, t+1);
				counts.put(num, counts.get(num)-1);
			}
		}

		if(max > 0 && counts.get(max) > len/2 && tempResults.add(max)) {
			size++;
		} else {
			Iterator<Integer> itr = counts.keySet().iterator();
			while(itr.hasNext()) {
				max = itr.next();
				if(counts.get(max) > len/2 && tempResults.add(max)) {
					size++;
					break;
				}
			}
		}

		for(int i = 1; i+K <= len; i++) {
			int prev = A[i-1];
			int cur = A[i+K-1];
			Integer t = null;

			if((t=counts.put(prev, 1)) == null) {
				counts.put(prev+1, counts.get(prev+1)-1);
			}
			else {
				counts.put(prev, t+1);
				counts.put(prev+1, counts.get(prev+1)-1);
			}

			if((t=counts.get(cur+1)) == null) {
				counts.put(cur+1, 1);
				if(counts.put(cur, counts.get(cur)-1)-1 > len/2 && tempResults.add(cur))
					size++;
				t = 1;
			} else {
				if(counts.put(cur+1, t+1)+1 > len/2 && tempResults.add(cur+1))
					size++;
				if(counts.put(cur, counts.get(cur)-1)-1 > len/2 && tempResults.add(cur))
					size++;
			}

		}

		results = new int[size];

		tempResults.forEach(a -> {
			results[s] = a;
			s++;
		});

		return results;
	}
}
