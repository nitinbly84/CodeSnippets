package codility;

import rNd.LargeIntArray;
import rNd.LargeLongArray;

/**
 * @author Nitin Agrawal
 * @Date 22-Feb-2020
 */
public class Palladium2020 {
	public static void main(String[] args) {
		int[] H = {7,7,3,7,7}; //--
		System.out.println(solution1(H));
		int a = 2;
		long l = 9999999999l-9;
		long prod = a*l;
		System.out.println(prod);
	}
	// Golden : https://app.codility.com/cert/view/certZ7NRUR-W4BADF6HDHH3MWWA/details/
	// Golden : https://app.codility.com/cert/view/certEEVFZ7-ZK7P83MC5QWWD45Z/details/
	// Golden : https://app.codility.com/cert/view/certTGMHKV-R8A6SAU7V52CD9KJ/details/
	// Golden : https://app.codility.com/cert/view/certUD4ZSF-JVY3HP3D5X2Y5JGA/details/
	// Golden : https://app.codility.com/cert/view/certQH27S4-U7GKA8RK4PMEE4AA/details/
	public static int solution(int[] H) {
		int len = 0;
		if(H == null || (len=H.length) == 0)
			return 0;
		int[] rightAreas = new int[len];
		for(int j = len-1, max = 0; j >= 0;) {
			if(max < H[j]) 
				max = H[j];
			rightAreas[j] = max*(len-(j--));
		}
		int area = rightAreas[0];
		for(int j = 0, max = 0, leftArea = 0; j < len-1;) {
			if(max < H[j]) 
				max = H[j];
			if((leftArea = max*(++j)) > area)
				break;
			leftArea = leftArea + rightAreas[j];
			if(area > leftArea) {
				area = leftArea;
			}
		}
		return area;
	}

	// Bit slower than above, not sure why?
	public static int solution1(int[] H) {
		int len = 0;
		if(H == null || (len=H.length) == 0)
			return 0;
		int[] rightMax = new int[len];
		for(int j = len-1,max = 0; j >= 0; j--) {
			if(max < H[j])
				max = H[j];
			rightMax[j] = max;
		}
		int area = rightMax[0]*len;
		for(int j = 0, max = 0, leftArea = 0; j < len-1; ) {
			if(max < H[j]) 
				max = H[j];
			if((leftArea = max*(++j)) > area)
				break;
			leftArea = leftArea + rightMax[j]*(len-j);
			if(area > leftArea) {
				area = leftArea;
			}
		}
		return area;
	}

	// Use for extremely large arrays
	// Not sure why it is giving bit different result than the above.
	// But it is about 3 times faster than above.
	public static long solution(LargeIntArray lia) {
		long len = 0;
		if(lia == null || (len=lia.size()) == 0)
			return 0;
		long temp = 0;
		long max = 0;
		LargeLongArray rightAreas = new LargeLongArray(len);
		for(long j = len-1; j >= 0;) {
			if(max < (temp=lia.getValue(j))) 
				max = temp;
			rightAreas.setValue(j, max*(len-(j--)));
		}
		max = 0;
		long area = rightAreas.getValue(0);
		for(long j = 0, leftArea = 0; j < len-1;) {
			if(max < (temp=lia.getValue(j))) 
				max = temp;
			if((leftArea = max*(++j)) > area)
				break;
			leftArea = leftArea + rightAreas.getValue(j);
			if(area > leftArea) {
				area = leftArea;
			}
		}
		rightAreas.destroy();
		return area;
	}

	// Use for extremely large arrays
	public static long solution1(LargeIntArray lia) {
		long len = 0;
		if(lia == null || (len=lia.size()) == 0)
			return 0;
		long j = len--;
		LargeIntArray rightAreas = new LargeIntArray(len);
		for(int temp = 0, max = 0; j >= 0; j--) {
			if(max < (temp=lia.getValue(j))) 
				max = temp;
			rightAreas.setValue(j, max);
		}
		j = 0;
		long area = rightAreas.getValue(0)*len;
		int max1 = 0, temp = 0;
		for(long leftArea = 0; j < len-1;) {
			if(max1 < (temp=lia.getValue(j)))
				max1 = temp;
			if((leftArea = max1*(++j)) > area)
				break;
			leftArea = leftArea + rightAreas.getValue(j)*(len-j);
			if(area > leftArea) {
				area = leftArea;
			}
		}
		rightAreas.destroy();
		return area;
	}
}