package codility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author nitin
 * @Date 28-Feb-2021
 */
// https://app.codility.com/cert/view/cert266AM9-QD8H4MA7K76V67WG/details/
// https://app.codility.com/cert/view/certFZSH7Z-KVSUESF4UNJA6WGF/details/
public class Doge2021 {

	public static void main(String[] args) {
		Doge2021 doge2021 = new Doge2021();
		int[] P = {2, 2, 2, 2, 1, 1, 1, 1};//{1, 1, 2};
		int[] T = {1, 1, 1, 1, 2, 2, 2, 2};//{2, 1, 1};
		int[] A = {0, 1, 2, 3, 4, 5, 6};
		int[] B = {1, 2, 3, 4, 5, 6, 7};
		System.out.println(doge2021.solution(P, T, A, B));
	}

	public boolean solution(int[] P, int[] T, int[] A, int[] B) {
		Map<Integer, List<Integer>> knowsWhoA = new HashMap<>();
		Set<Integer> visited = new HashSet<>();
		for(int i = 0; i < A.length; i++) {
			if(knowsWhoA.containsKey(A[i]))
				knowsWhoA.get(A[i]).add(B[i]);
			else {
				List<Integer> ls = new ArrayList<>();
				ls.add(B[i]);
				knowsWhoA.put(A[i], ls);
			}
		}
		for(int i = 0; i < P.length; i++) {
			if(P[i] != T[i]) {
				List<Integer> next = knowsWhoA.get(i);
				if(next == null || next.isEmpty())
					continue;
				visited.add(i);
				Integer pos = find(P, T, knowsWhoA, next, i, visited);
				if(pos != null) {
					int temp = T[pos];
					T[pos] = T[i];
					T[i] = temp;
				} 
			}
			visited.clear();
		}
		for(int i = 0; i < P.length; i++) {
			if(P[i] != T[i]) {
				return false;
			}
		}
		return true;
	}

	private Integer find(int[] P, int[] T, Map<Integer, List<Integer>> knowsWhoA, List<Integer> next, int org, Set<Integer> visited) {
		Integer result = null;
		if(next == null)
			return null;
		for(int i : next) {
			if(!visited.add(i)) {
				continue;
			}
			if(P[org] == T[i] && P[i] != T[i]) {
				return i;
			} else {
				result = find(P, T, knowsWhoA, knowsWhoA.get(i), org, visited);
				if(result != null)
					break;
			}
		}
		return result;
	}
}
