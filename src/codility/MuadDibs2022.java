package codility;

import java.util.Arrays;

/**
 * @author nitin
 * @Date 03-Apr-2022
 */
public class MuadDibs2022 {
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,2,4,5,7,29,30})); //11
		System.out.println(solution(new int[] {12, 13, 14, 17, 18})); //7
		System.out.println(solution(new int[] {1,3,5,6,15,18,19,21})); //14
		System.out.println(solution(new int[] {1,3,5,6,15,18,19,21,22})); //16
		System.out.println(solution(new int[] {1,3,5,6,15,18,19,21,31,33,35,36,40,45,49,51,52,53,54,60})); //34
		System.out.println(solution(new int[] {5, 7, 8, 9, 10, 12, 13})); //9
//		                                       2  4  6  7   7   9   9
		System.out.println(solution(new int[] {3, 6, 8, 10, 15, 18, 19, 28})); //16
	}
	
	// Golden Award : https://app.codility.com/cert/view/certQXMY8R-FKG4PAGKC3JKFPYJ/details/
	// This solution performance is at par with the silver solution but it faired well for those
	// 3 performance TCs, to make it Golden solution
	// Golden Award : https://app.codility.com/cert/view/cert57P76Z-UAHBVYT49Z5XT8JZ/details/
	// Golden Award : https://app.codility.com/cert/view/certBD5XHV-8Q56E2QEPQVPVBVK/details/
	// Golden Award : https://app.codility.com/cert/view/cert49H22W-WQGT3AT9RRV4D7J7/details/
	public static int solution(int[] A) {
		int len = A.length;
		int[] cost = new int[len];
		cost[0] = 2;
		int[] pkgs = new int[] {30,7,1};
		int[] costs = new int[] {25,7,2};
		
		for(int i = 1; i < len; i++) {
			cost[i] = Math.min(findMin(A, pkgs, 0, costs, A[i], cost, cost[i-1]+2), 
					           getNDaysCost(A[i]-A[0]+1, pkgs, 0, costs));
		}
		return cost[len-1];
	}
	
	private static int findMin(int[] A, int[] pkgs, int pkg, int[] costs, int value, int[] cost, int min) {
		if(pkg >= pkgs.length)
			return min;
		int pos = Arrays.binarySearch(A, value-pkgs[pkg]);
		if(pos >= 0) {
			min = Math.min(min, costs[pkg]+cost[pos]);
		} else {
			pos = -(pos+1);
			min = pos > 0 ? Math.min(costs[pkg]+cost[pos-1], min) : min;
		}
		return Math.min(min, findMin(A, pkgs, pkg+1, costs, value, cost, min));
	}
	
	private static int getNDaysCost(int days, int[] pkgs, int pkg, int[] costs) {
		if(pkg >= pkgs.length || days == 0)
			return 0;
		return (days/pkgs[pkg])*costs[pkg] + Math.min(costs[pkg], getNDaysCost(days%pkgs[pkg], pkgs, pkg+1, costs));
	}
	
	// 52 cases and 3 TCs are failing for performance
	// Silver Award : https://app.codility.com/cert/view/certSVDHXW-ZNAXKXBHFZZKWT48/details/
	// Silver Award : https://app.codility.com/cert/view/cert6ZMKDY-TPWZ4MYAHFMHMBDK/details/
	public static int solution1(int[] A) {
		int len = A.length;
		int[] cost = new int[len];
		cost[0] = 2;
		int staged = 0;
		
		for(int i = 1; i < len; i++) {
			staged = i-1;
			int min = Math.min((i+1)*2, cost[i-1]+2);
			while(staged >= 0 && A[i]-A[staged] < 30 && min+1 > cost[staged]) {
				min = Math.min(min, get30DaysCost(A[i]-A[staged]+1)+(staged>0?cost[staged-1]:0));
				staged--;
			}
			cost[i] = min;
		}
		return cost[len-1];
	}

	private static int get1DayCost(int days) {
		return days*2;
	}
	
	private static int get7DaysCost(int days) {
		return (days/7)*7 + Math.min(7, get1DayCost(days%7));
	}
	
	private static int get30DaysCost(int days) {
		return (days/30)*25 + Math.min(25, get7DaysCost(days%30));
	}

}
