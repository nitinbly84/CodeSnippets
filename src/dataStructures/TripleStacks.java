package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class TripleStacks {

	private static List<int[]> setSets = null;
	private static int curp = 0;
	private static int curs = 0;
	private final static int arrSize = 10;

	public static void main(String[] args) {
		
		for(int i = 0; i < 400; i++) {
			push(i);
		}
		curp--;
		
		System.out.println(peek());
		
		for(int i = 0; i < 500; i++) {
			System.out.println(pop());
		}
	}

	public static boolean push(int a) {
		if(setSets == null) {
			setSets = new ArrayList<>();
			int[] set = new int[10];
			setSets.add(set);
		}
		
			if(curp == 10){
				curs++;
				curp = 0;
			}
			if(curs == setSets.size()) {
				int[] set = new int[arrSize];
				setSets.add(set);
			}
			
			if(curp < 10) {
				setSets.get(curs)[curp] = a;
				curp++;
			} 
			return true;
	}

	public static int pop() {
		if(curp >= 0 || curs >= 0) {
			if(curp < 0) {
				curs--;
				
				if(curs >= 0) {
					curp = setSets.get(curs).length-1;
					return setSets.get(curs)[curp--];
				}
			} else {
				return setSets.get(curs)[curp--];
			}
		}
		System.out.println("Stack is empty now!!!");
		System.exit(0);
		return 0;
	}
	
	public static int peek() {
		return setSets.get(curs)[--curp];
	}
}
