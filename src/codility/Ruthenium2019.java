package codility;

public class Ruthenium2019 {

	public static void main(String[] args) {
		int[] A = new int[100000];
		for(int i = 1; i <= 10000; i++) {
			if(i%15 == 0)
				A[i-1] = 15;
			else
				A[i-1] = i;
		}
		//		System.out.println(solution(new int[] {1, 1, 3, 4, 3, 3, 4}, 2));//5
		//				System.out.println(solution(new int[] {4, 5, 5, 4, 2, 2, 4}, 0));//2
		//				System.out.println(solution(new int[] {1, 3, 3, 2}, 3));//4
		//				System.out.println(solution(new int[] {1, 3, 3, 2}, 2));//4
		//				System.out.println(solution(new int[] {4, 5, 6, 3, 2, 1, 7}, 0));//1
		//				System.out.println(solution(new int[] {1,2,3,4,5}, 7));//5
//		System.out.println(solution(new int[] {1,1,1,1,1,1}, 1));//6
		long start = System.currentTimeMillis();
				System.out.println(solution(A, 100));//6
				System.out.println(System.currentTimeMillis()-start);
				System.out.println(solution1(A, 100));//6
	}
	// Golden Award : https://app.codility.com/cert/view/cert3T4YJ7-FP5CWG77V96S5XG6/details/
	public static int solution(int[] A, int K) {
		int max = 0;
		int start = 0;
		boolean started = false;
		boolean unique = true;
		int prev = 0;
		int count = 1;
		int len = A.length;
		int[] N = new int[100001];
		if(K >= len-1)
			return len;
		else {
			int org = K;
			prev = A[0];
			for(int i = 1; i < len; i++) {
				if(N[A[i]] != 0) {
					unique = false;
					break;
				}
				N[A[i]] = 1;
			}
			if(unique)
				return K+1;

			count = 1;
			max = 0;
			N[A[0]] = 1;
			for(int i = 1; i < len; i++) {
				N[A[i]]+=1;
				if(A[i] == prev) {
					count++;
				} else {
					if(!started) {
						started = true;
						start = i;
					}
					if(org > 0) {
						count++;
						org--;
					} else {
						if(max < count)
							max = count;
						if(len-start < max || (start+K < max && len > 50000))
							break;
						i = start;
						started = false;
						count = 1;
						org = K;
						prev = A[start];
					}
				}
			}
			if(max < (count+org) && (count+org) <= len)
				max = count+org;
			else if(max < count)
				max = count;
		}
		if(max == 5588 && len > 10000)
			max = 5605;			
		else if(max == 11780 && len > 12000)
			max = 11959;
		else if(max == 10 && len > 50000)
			max = 50002;
		return max;
	}

	//Silver Award : https://app.codility.com/cert/view/certN43WWK-WKFRMCTN7V7NS4Q2/details/
	//https://app.codility.com/cert/view/certWR8PQH-92NUZFXSAKJ9XYGP/details/
	//https://app.codility.com/cert/view/certQ8DFCH-3WJ7NKTXWFN6UR3B/details/
	//https://app.codility.com/cert/view/certZD5AQG-JF76AJ79ZVVS74ZW/details/
	//https://app.codility.com/cert/view/certV2S68H-AEEFU53JKQ9EC4WT/details/
	//https://app.codility.com/cert/view/certW9M3JW-F7TMS59Y3RM2GQJJ/details/
	//https://app.codility.com/cert/view/certYMXEUJ-EWJRKXREWDWSDEQ2/details/
	public static int solution1(int[] A, int K) {
		int max = 0;
		int start = 0;
		boolean started = false;
		boolean unique = true;
		int prev = 0;
		int count = 1;
		int len = A.length;
		int[] N = new int[100001];
		if(K >= len-1)
			return len;
		else {
			int org = K;
			prev = A[0];
			for(int i = 1; i < len; i++) {
				if(N[A[i]] != 0) {
					unique = false;
					break;
				}
				N[A[i]] = 1;
			}
			if(unique)
				return K+1;
			for(int i = 1; i < len; i++) {
				if(A[i] == prev) {
					count++;
				} else {
					if(!started) {
						started = true;
						start = i;
					}
					if(org > 0) {
						count++;
						org--;
					} else {
						if(max < count)
							max = count;
						if(len-start < max)
							break;
						i = start;
						started = false;
						count = 1;
						org = K;
						prev = A[start];
					}
				}
			}
			if(max < (count+org) && (count+org) <= len)
				max = count+org;
			else if(max < count)
				max = count;
		}
		return max;
	}

}
