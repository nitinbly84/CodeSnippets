package codility;

/**
 * @author Nitin Agrawal
 * @Date 29-Feb-2020
 */
public class Palladium2020_2 {

	public static void main(String[] args) {
		int[] H = {5,3,2,4}; //--10
		long start = System.nanoTime();
		System.out.println(solution(H));
		System.out.println(System.nanoTime()-start);
	}

	public static int solution(int[] H) {
		int len = H.length;
		int max = -1;
		int maxi = -1;

		int leftMax = 0;
		int leftMaxi = 0;
		int rightMax = 0;
		int rightMaxi = 0;
		for(int i = 0; i < len; i++) {
			if(max < H[i]) {
				leftMax = max;
				leftMaxi = maxi;
				max = H[i];
				maxi = i;
			} else if(max > H[i] && rightMax < H[i]) {
				rightMax = H[i];
				rightMaxi = i;
			}
		}


		int rightLen = 0;
		if(maxi < len-1)
			rightLen = len-maxi-1;
		int leftLen = 0;
		if(maxi > 0)
			leftLen = maxi+1;
		int area = max*len;
		int i = maxi;
		//Left Check
		int tempArea = 0;
		while(i >= 0) {
			int rightArea=max*(++rightLen);
			if(rightArea > area)
				break;
			if(i > 0) {
				if(i == leftMaxi) {
					int[] res = findMax(0, i-1, H);
					leftMax = res[0];
					leftMaxi = res[1];
				}
				tempArea = rightArea+leftMax*(--leftLen);
			} else
				tempArea = rightArea+H[0];

			if(tempArea < area)
				area = tempArea;
			i--;
		}

		//Right Check
		i = maxi+1;
		rightLen = 0;
		if(maxi < len-1)
			rightLen = len-maxi;
		leftLen = 0;
		if(maxi > 0)
			leftLen = maxi+1;
		int[] res = new int[2];
		while(i < len) {
			int leftArea=max*(++leftLen);
			if(leftArea > area)
				break;
			if(i < len-1) {
				if(i == rightMaxi) {
					res = findMax(i+1, len-1, H);
					rightMaxi = res[1];
				}
				tempArea = leftArea+rightMax*(--rightLen);
				rightMax = res[0];
			}
			else
				tempArea = leftArea+H[len-1];

			if(tempArea < area)
				area = tempArea;
			i++;
		}
		return area;
	}

	private static int[] findMax(int start, int end, int[] H) {
		int[] maxes = new int[2];
		int max = -1;
		for(int k = start; k <= end; k++) {
			if(max < H[k]) {
				max = H[k];
				maxes[0] = max;
				maxes[1] = k;
			}
		}
		return maxes;
	}
}