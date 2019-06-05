package techgig;

import java.util.HashMap;

public class ModalValue {

	public static void main(String[] args) {
				int[] arr = {6,3,9,6,6,5,9,3};
				System.out.println(find(arr, 7));

		/*Scanner scanner = new Scanner(System.in);
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
		scanner.close();	*/
	}

	public static int find(int[] arr, int length) {
		HashMap<Integer, Integer> data = new HashMap<>();
		int max = 0;
		int modal = -1;
		for(int i : arr) {
			if(data.get(i) != null) {
				int temp = data.get(i);
				if(temp+1 > max) {
					max = temp+1;
					modal = i;
				}
			}
			else {
				data.put(i, 1);
				if(max < 1) {
					max = 1;
					modal = i;
				}
			}
		}
		return modal;
	}
}
