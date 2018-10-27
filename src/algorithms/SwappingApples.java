package algorithms;

public class SwappingApples {

	public static void main(String[] args) {

		int[] fruits = {1,1,1,0,1,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0};

		int swaps = 4;
		int maxContOnes = 0;
		int k = 0;
		int swap = swaps;
		int start = 0;

		for(int i : fruits) {
			if(i == 0) {
				if(swap > 0)
					swap--;
				// There is no chance to swap any zero so move the start counter to next value.
				else if(swaps == 0) {
					start = k+1;
				}
				else {
					// Set the max count value till the current pointer as all available swaps are consumed now.
					if(maxContOnes < k-start) {
						maxContOnes = k - start;
					}
					// if swap = 0 then move the start pointer till one zero is crossed.
					while(swap == 0 && start < k) {
						if(fruits[start] == 0)
							swap++;
						start++;
					}
					// Minus the count of zero by one to count the current zero.
					swap--;
				}
			}
			k++;
		}
		// This is to calculate the value once more if any zero was processed in the last of above iteration.
		if(maxContOnes < k-start) {
			maxContOnes = k - start;
		}
		System.out.println(maxContOnes);
	}
}
