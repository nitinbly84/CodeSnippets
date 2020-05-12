package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nitin Agrawal
 * @Date 10-May-2020
 * It finds if there is any combination of numbers exists whose sum is equal to the given number.
 * And number of elements in such combination should be exactly equal to the count given,
 * It prints all such possible combinations from the given array of numbers.
 */
public class ElementsSum {

	public static void main(String[] args) {
		int[] numbers = {3,4,2,5,3,2,7};
		int count = 2;
		int sum = 11; 
		findCombination(numbers, count, sum).forEach(System.out::println);
	}

	private static Set<List<Integer>> findCombination(int[] numbers, int count, int sum) {
		List<Integer> combination = new ArrayList<>(count);
		Set<List<Integer>> result = new HashSet<>();
		Arrays.sort(numbers);
		List<Integer> inputNumbers = new ArrayList<>();
		for(int num : numbers)
			inputNumbers.add(num);
		System.out.println(inputNumbers);
		find(inputNumbers, 0, combination, result, count, sum);
		return result;
	}

	private static void find(List<Integer> numbers, int start, List<Integer> combination, Set<List<Integer>> result,  int count, int sum) {
		List<Integer> orgNumbers = new ArrayList<>(numbers);
		List<Integer> orgCombination = new ArrayList<>(combination);
		if(start < numbers.size()) {
			combination.add(numbers.get(start));
			numbers.remove(numbers.get(start));
			int curSum = sum(combination);
			if(combination.size() < count && curSum < sum)
				find(numbers, start, combination, result, count, sum);
			else if(combination.size() == count && curSum == sum)
				result.add(combination);
		}
		while(start < numbers.size()) {
			combination = new ArrayList<>(orgCombination);
			numbers = new ArrayList<>(orgNumbers);
			start++;
			find(numbers, start, combination, result, count, sum);
		}
	}

	private static Integer sum(List<Integer> combination) {
		int sum = 0;
		for(int n : combination)
			sum+=n;
		return sum;
	}
}
