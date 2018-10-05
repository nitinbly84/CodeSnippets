package algorithms;

public class MoveZeroesAdv {
	
//	static int[] arr = {1, 2, 0, 3, 0, 1, 2, 0};
//	static int[] arr = {1, 0, 1, 0, 1, 1, 1, 0};
	static int[] arr = {0,0,0,0,0,0,1};

	public static void main(String[] args) {
		
		int i = 0;
		int j = 0;
		int len = arr.length;

		for(int k = 0; k < len; k++) {
			if(arr[k] !=  0) {
				if(i < j) {
					int temp = arr[k];
					arr[k] = arr[i];
					arr[i] = temp;
				}
					i++;
					j++;
			} else {
				j++;
			}
		}
		
		for(i = 0; i < arr.length; i++) {
			if(i != arr.length-1)
				System.out.print(arr[i] + " ");
			else
				System.out.print(arr[i]);
		}
	}

}
