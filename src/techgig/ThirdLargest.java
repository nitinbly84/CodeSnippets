package techgig;

import java.util.ArrayList;
import java.util.Scanner;

public class ThirdLargest {
	public static void main(String[] args) {
		//		int[] arr = {-25,-26,7,8,10,11,79};
		//		System.out.println(find(arr), 7);

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
		System.out.println(find(arr, length));
		scanner.close();	
	}

	public static int find(int[] arr, int length) {
		if(length < 3)
			return 0;
		int[] numbers = new int[3];
		for(int i : arr) {
			int j = 0;
			while(j < 3) {
				if(i > numbers[j]) {
					int temp = numbers[j]; 
					numbers[j] = i;
					i = temp;
				}
				j++;
			}
		}
		return numbers[2];
	}

}
