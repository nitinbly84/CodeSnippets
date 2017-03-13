package algorithms;

/**
 * Search an Element in a Sorted and Rotated Array
 * @author Nitin
 *
 */
public class RotatedArray {

	//	static int[] arr = {7,8,10,20,1,3,5};
	//	static int[] arr = {};
	static int[] arr = {7,2,6};

	public static void main(String[] args) {
		int start = 0;
		int end = arr.length-1;
		int num = 6;
		System.out.println(search(start, end, num));
	}

	static boolean search(int start, int end, int num) {
		int middle;
		if(end < 0)
			return false;
		if(start == end && start == 0) {
			if(arr[start] == num)
				return true;
			return false;
		}
		if(end-start == 1) {
			if(arr[start] == num || arr[end] == num)
				return true;
			return false;
		}

		middle = (end-start)/2;
		if(num == arr[middle])
			return true;
		if(num == arr[end] || num == arr[start])
			return true;
		if(num > arr[middle]) {
			if(num < arr[end]) {
				start = middle+1;
				end = end-1;
				return search(start, end, num);
			} else if(num < arr[start]) {
				return false;
			} else if(num > arr[start]) {
				start = start+1;
				end = middle-1;
				return search(start, end, num);
			}
		} else if(num < arr[middle]) {
			if(num > arr[start]) {
				start = start+1;
				end = middle-1;
				return search(start, end, num);
			} else if(num < arr[start]) {
				start = middle+1;
				end = end-1;
				return search(start, end, num);
			}
		}
		return false;
	}
}
