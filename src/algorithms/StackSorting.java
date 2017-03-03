package algorithms;

import java.util.Stack;

public class StackSorting {

	private static Stack<Integer> stack = new Stack<>();
	private static Stack<Integer> sortedStack = new Stack<>();

	public static void main(String[] args) {
		int[] arr = {4,3,5,6,7,9,2,-1,-8};
		for(int i : arr) {
			stack.push(i);
		}
		sort();
		while(!sortedStack.isEmpty())
			System.out.println(sortedStack.pop());
	}

	public static void sort() {

		if(stack.isEmpty() || stack.size() < 2) {
			return;
		}

		while(!stack.isEmpty() && stack.size() > 1) {
			int first = stack.pop();
			int sec = stack.pop();
			if(first > sec) {
				stack.push(sec);
				if(sortedStack.isEmpty()) {
					sortedStack.push(first);

				} else {
					int third = sortedStack.peek();
					while(third < first && !sortedStack.isEmpty()) {
						third = sortedStack.pop();
						stack.push(third);
						third = sortedStack.peek();
					}
					sortedStack.push(first);					
				}
			} else if(sec >= first) {
				stack.push(first);
				if(sortedStack.isEmpty()) {
					sortedStack.push(sec);

				} else {
					int third = sortedStack.peek();
					while(third < sec) {
						third = sortedStack.pop();
						stack.push(third);
						if(sortedStack.isEmpty())
							break;
						third = sortedStack.peek();
					}
					sortedStack.push(sec);					
				}
			}
		}
		
		if(stack.size() == 1) {
			sortedStack.push(stack.pop());
		}
	}
}
