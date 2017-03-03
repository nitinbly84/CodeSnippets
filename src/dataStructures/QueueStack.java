package dataStructures;

import java.util.Stack;

public class QueueStack {

	private static Stack<Integer> stackI = new Stack<>();
	private static Stack<Integer> stackO = new Stack<>();

	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			push(i);
		}

		for(int i = 0; i < 10; i++) {
			System.out.println(pop());
		}
	}

	public static void push(int a) {
		if(stackI.isEmpty() && stackO.isEmpty()) {
			stackI.push(a);
			return;
		}

		if(!stackI.isEmpty() || !stackO.isEmpty()) {
			while(!stackO.isEmpty()) {
				stackI.push(stackO.pop());
			}
			stackO.push(a);
			while(!stackI.isEmpty())
				stackO.push(stackI.pop());
		}
	}

	public static int pop() {
		return stackO.pop();
	}

	public static int peek() {
		return stackO.peek();
	}
}
