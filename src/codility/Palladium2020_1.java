package codility;

/**
 * @author Nitin Agrawal
 * @Date 29-Feb-2020
 */
public class Palladium2020_1 {

	public static void main(String[] args) {
		long start = System.nanoTime();
		int[] H = {1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1}; //--46
		//				int[] H = {1,2,2,3,4,5,5,4,3,2,1, 1, 2, 2, 3, 4, 5, 5, 4, 3, 2, 1}; //105--
		//         0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21
		System.out.println(solution(H));
		System.out.println(System.nanoTime()-start);
	}
	// https://app.codility.com/cert/view/cert84HUKV-UNXU3TGRJS5FQERJ/details/
	// https://app.codility.com/cert/view/cert252CBS-RGG7T4SGM5P7FA54/details/
	// https://app.codility.com/cert/view/certF7SYA2-X53P6EQNKFERDURS/details/
	// https://app.codility.com/cert/view/certJP3BG5-S2JSZG4R9AX5NNNR/details/
	// https://app.codility.com/cert/view/certSDHGC9-6YK529JNANW6G7HR/details/
	public static int solution(int[] H) {
		int len = H.length;
		int max1 = -1;
		int maxi1 = -1;
		int maxi2 = -1;
		int max2 = -1;

		//		System.out.println(len/2 + ":" + H[len/2]);

		for(int i = 0; i < len/2; i++) {
			if(max1 < H[i]) {
				max1 = H[i];
				maxi1 = i;
			}
		}

		for(int i = len/2; i < len; i++) {
			if(max2 <= H[i]) {
				max2 = H[i];
				maxi2 = i;
			}
		}

		int area1 = Integer.MAX_VALUE;
		int area2 = Integer.MAX_VALUE;
		int area = area1+area2;

		if(max2 > max1) {
			area1 = findMinAreaRight(maxi2, H, area1, max2, len);
			area2 = findMinAreaLeft(maxi2, H, area2, max2, len);
			area = area1<area2?area1:area2;
		} else if(max2 < max1) {
			area1 = findMinAreaRight(maxi1, H, area1, max1, len);
			area2 = findMinAreaLeft(maxi1, H, area2, max1, len);
			area = area1<area2?area1:area2;
		} else {
			area1 = findMinAreaRight(maxi2, H, area1, max2, len);
			area2 = findMinAreaLeft(maxi2, H, area2, max2, len);
			area = area1<area2?area1:area2;
			area1 = findMinAreaRight(maxi1, H, area1, max1, len);
			area2 = findMinAreaLeft(maxi1, H, area2, max1, len);
			area = area1<area2?area1:area2;
		}
		return area;
	}

	private static int findMinAreaRight(int startPoint, int[]H, int area, int maxHeight, int len) {

		startPoint++;
		while(startPoint <= len-1) {
			int tempArea = maxHeight*(startPoint) + findMax(startPoint, len-1, H)*(len-startPoint);
			if(area > tempArea)
				area = tempArea;
			startPoint++;
		}
		return area;
	}

	private static int findMinAreaLeft(int endPoint, int[]H, int area, int maxHeight, int len) {

		endPoint--;
		while(endPoint >= 0) {
			int tempArea = maxHeight*(len-endPoint-1) + findMax(0, endPoint, H)*(endPoint+1);
			if(area > tempArea)
				area = tempArea;
			endPoint--;
		}
		return area;
	}

	private static int findMax(int start, int end, int[] H) {
		int max = -1;
		for(int k = start; k <= end; k++) {
			if(max < H[k])
				max = H[k];
		}
		return max;
	}

}