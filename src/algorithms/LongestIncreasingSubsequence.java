package algorithms;

public class LongestIncreasingSubsequence {
	
	public static void main(String[] args) {
		int[] numbers = {5,6,2,3,4,1,9,7,8,9,5,4,3,0};
		int i = numbers.length;
		int[] lengths = new int[i];
		int max = Integer.MIN_VALUE;
		int iMax = i-1;
		
		System.out.println(findLength(lengths, numbers, i-1, max, i, iMax));
		
	}
	
	public static int findLength(int[] lengths, int[] numbers, int i, int max, int len, int iMax) {
		
		if(i == len-1)
			lengths[i] = 1;
		else if(i < 1)
			return max;
		else {
			int j = i;
			lengths[j] = 1;
			while(j <= len-2) {
				if(numbers[i] < numbers[j+1] && lengths[i] <= lengths[j+1])
					lengths[i] = lengths[j+1] + 1;
				if(lengths[i] >= len-j)
					break;
				j++;
			}
		}
		if(max < lengths[i]) {
			max = lengths[i];
			iMax = i;
		}
		
		max = findLength(lengths, numbers, i-1, max, len, iMax);
		
		return max;
	}

}
