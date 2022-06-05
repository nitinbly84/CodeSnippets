package codility;

/**
 * @author Nitin Agrawal
 * @Date 29-Feb-2020
 */
//Golden
public class JatinPalladium {

	public static void main(String[] args) {
		long start = System.nanoTime();
		int[] H = {10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1};
		System.out.println(solution(H));
		System.out.println(System.nanoTime() - start);
	}

	public static int solution(int[] H) {

		if(H == null || H.length == 0) {
			return 0;
		}

		int[] minAreaLeft = new int[H.length];
		int[] minAreaRight = new int[H.length];

		int curMax = 0;
		for(int i = 0; i < H.length; i++) {
			int curHeight = H[i];
			if(curHeight > curMax) {
				curMax = curHeight;
			}
			minAreaLeft[i] = curMax * (i + 1);
		}

		//System.out.println(Arrays.toString(minAreaLeft));
		curMax = 0;
		for(int i = 1; i <= H.length; i++) {
			int index = H.length - i;
			int curHeight = H[index];
			if(curHeight > curMax) {
				curMax = curHeight;
			}
			minAreaRight[index] = curMax * i;
		}

		//System.out.println(Arrays.toString(minAreaRight));
		int currentMinTotalArea = minAreaLeft[H.length - 1];
		for(int i = 1; i < H.length; i++) {
			int currentArea = minAreaLeft[i - 1] + minAreaRight[i];
			if(currentArea < currentMinTotalArea) {
				currentMinTotalArea = currentArea;
			}
		}
		return currentMinTotalArea;
	}
}
