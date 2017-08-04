package puzzles;

import java.util.Arrays;

/**
 * To find the pairs of integers in the given array x, whose sum is equal to
 * given number K. Its complexity is n.
 * @author Nitin
 *
 */
public class Pairs {

	public static void main(String[] args) {
		int[] x = {-1, 2, 3, 4};
		int K = 6;
		System.out.println(findPairs(x, K, true));
	}

	public static int findPairs(int[] x, int K, boolean isSorted) {
		if(!isSorted) {
			Arrays.sort(x);
		}
		int len = x.length;
		int count = 0;
		int pos = 0;
		int mover = 0;

		if(len == 0)
			return 0;

		if(K > x[len-1]) {
			if(x[0] < 0) {
				for(int i = 0; i < len; i++) {
					if(x[i] > 0) {
						pos = i;
						break;
					}
				}
			}
			mover = len-1;
			while(pos < mover) {
				if(x[pos]+x[mover] == K) {
					count++;
					if((mover-1) >= 0 && x[mover-1] < x[mover]) {
						if((pos+1) <= mover && x[pos+1] > x[pos]) {
							mover--;
							pos++;
						}
					} else {
						count++;
						mover--;
					}
				} else if(x[pos]+x[mover] < K) {
					pos++;
				} else if(x[pos]+x[mover] > K) {
					mover--;
				} 
			} 
		} else if(K < x[len-1]) {
			mover = len-1;
			if(x[0] > 0) {
				return 0;
			} else {
				while(x[pos] < 0) {
					if(x[pos]+x[mover] == K) {
						count++;
						if((mover-1) >= 0 && x[mover-1] < x[mover]) {
							if((pos+1) <= mover && x[pos+1] > x[pos]) {
								mover--;
								pos++;
							}
						} else {
							count++;
							mover--;
						}
					} else if(x[pos]+x[mover] < K) {
						pos++;
					} else if(x[pos]+x[mover] > K) {
						mover--;
					} 				
				}				
			}
		}
		return count;
	}
}
