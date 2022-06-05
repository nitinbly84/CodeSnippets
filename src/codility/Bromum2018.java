package codility;

import java.util.ArrayList;
import java.util.HashMap;

public class Bromum2018 {

	public static void main(String[] args) {
		int N = 3;
		int Q = 2;
		int[] B = {1, 2, 0, 1, 1, 0, 0, 1};
		int[] C = {0, 3, 0, 2, 0, 3, 0, 0};
		System.out.println(solution(N, Q, B, C));

	}

	// https://app.codility.com/cert/view/cert4KRN87-KZH2YM5CRJYQ5R4B/details/
	public static int solution(int N, int Q, int[] B, int[] C) {

		ArrayList<Integer[]> al = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			Integer[] hm = new Integer[100000];
			al.add(hm);
		}

		int len = C.length;
		for(int i = 0; i < len; i++) {
			Integer[] hm1 = al.get(B[i]);
			Integer val = hm1[C[i]];
			int count = val == null ? 0 : val;
			count++;
			if(count == Q)
				return i;
			hm1[C[i]] = count;

		}
		return -1;
	}

	// https://app.codility.com/cert/view/certG89VEB-VTX597GAY6XZ5JDH/details/
	public static int solution1(int N, int Q, int[] B, int[] C) {

		HashMap<Integer, Integer>[] al = new HashMap[N];
		for(int i = 0; i < N; i++) {
			HashMap<Integer, Integer> hm = new HashMap<>();
			al[i] = hm;
		}

		int len = C.length;
		for(int i = 0; i < len; i++) {
			HashMap<Integer, Integer> hm1 = al[B[i]];
			Integer val = hm1.get(C[i]);
			int count = val == null ? 0 : val;
			count++;
			if(count == Q)
				return i;
			hm1.put(C[i], count);

		}
		return -1;
	}

}
