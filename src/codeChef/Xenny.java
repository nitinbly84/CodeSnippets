package codeChef;

public class Xenny {

	private static String[] input = {"2", "3", "2 1 2", "3 2 1", "3", "2 1 2", "3 2 1"};

	public static void main(String[] args) {
		find(input);
	}

	public static void find(String[] input) {
		int testCases = Integer.parseInt(input[0].trim());
		int sum = 0;
		for(int j = 0; j < testCases; j++) {
			String[] x = input[3*j+2].trim().split(" ");
			String[] y = input[3*j+3].trim().split(" ");
			int sum1 = 0;
			int sum2 = 0;
			int tasks = Integer.parseInt(input[3*j+1].trim());
			for(int i = 0; i < tasks; i++) {
				sum1 = sum1 + Integer.parseInt(x[i]);
				sum2 = sum2 + Integer.parseInt(y[i]);
				if(i+1 < tasks) {
					i = i+1;
					sum1 = sum1 + Integer.parseInt(y[i]);
					sum2 = sum2 + Integer.parseInt(x[i]);
				}
			}
			if(sum1 < sum2){
				sum = sum + sum1;
				System.out.println(sum1);
			}
			else{
				sum = sum + sum2;
				System.out.println(sum2);
			}
		}
	}
}
