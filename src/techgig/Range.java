package techgig;

import java.util.ArrayList;
import java.util.Scanner;

public class Range {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> inputs = new ArrayList<>();
		String line = null;
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			if(line.trim().length() != 0) {
				inputs.add(line);
			}
			else
				break;
		}

		if(inputs.size() == 0)
			return;

		int length = Integer.parseInt(inputs.get(0));
		String[] numbers = inputs.get(1).split(" ");
		int[] arr = new int[length];
		for(int j = 0; j < length; j++) {
			arr[j] = Integer.parseInt(numbers[j]);
		}
		System.out.print(find(arr, length));
		scanner.close();
	}
	
	public static int find(int[] arr, int length) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i : arr) {
			if(min > i)
				min = i;
			if(max < i)
				max = i;
		}
		return max - min;
	}

}
