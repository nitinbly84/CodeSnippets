package algorithms;

public class MoveZeroes {

	static int[] arr = {1, 2, 0, 3, 0, 1, 2};

	public static void main(String[] args) {
		move(arr);
		for(int i = 0; i < arr.length; i++) {
			if(i != arr.length-1)
				System.out.print(arr[i] + " ");
			else
				System.out.print(arr[i]);
		}
	}

	public static void move(int[] nums) {
		int len = nums.length;
		int count = 0;
		boolean first = false;
		int firstI = -1;

		for(int i = 0; i < len; i++) {
			if(nums[i] == 0) {
				count++;
				first = true;
				firstI = i;
			} else {
				first = false;
				firstI = -1;
			}
			if(firstI > 0 && first && i == len){
				len = len - firstI;
			}
		}

		if(count == len){
			return;
		}
		while(count > 0) {
			for(int i = 0; i < len-1; i++) {
				if(nums[i] == 0){
					int temp = nums[i];
					nums[i] = nums[i+1];
					nums[i+1] = temp;
				}
			} 
			count--;
			len--;
		}

	}
}