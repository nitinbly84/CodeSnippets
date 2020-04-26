package algorithms;

import java.util.ArrayList;

/**
 * @author Nitin Agrawal
 * @Date 26-Apr-2020
 * Question as given on 
 * https://www.glassdoor.co.in/Interview/CDK-Global-Senior-Java-Developer-Interview-Questions-EI_IE870191.0,10_KO11,32.htm
 */
public class FourVarTruthTable {

	public static void main(String[] args) {
		// Provide the variables count in truth table
		int varCount = 4;
		// Provide the digits to be present in the truth table.
		int[] digits = {0, 1};
		int[][] truthTable = buildTable(varCount, digits);
		System.out.println("Number of rows : " + truthTable.length);
		int i = 1;
		while(i <= varCount)
			System.out.print("Var"+(i++)+" ");
		System.out.println();
		for(int[] row : truthTable) {
			System.out.print(" ");
			for(int val : row) {
				System.out.print(val + "    ");
			}
			System.out.println();
		}
	}

	public static int[][] buildTable(int varCount, int[] digits) {
		int digitsCount = digits.length;
		int rows = (int)Math.pow(digitsCount, varCount);
		ArrayList<Integer[]> lists = new ArrayList<>(varCount);
		int[][] truthTable = new int[rows][varCount];
		int orgCount = varCount--;
		while(varCount >= 0) {
			Integer[] arr = new Integer[rows];
			int cur = 0;
			int rowNum = 0;
			int repeat = (int)Math.pow(digitsCount, varCount);
			int orgRepeat = repeat;
			while(repeat > 0) {
				arr[rowNum] = digits[cur];
				rowNum++;
				repeat--;
				if(repeat == 0 && cur < digitsCount-1) {
					cur++;
					repeat = orgRepeat;
				}
				else if(repeat == 0 && rowNum < rows-1 && cur == digitsCount-1) {
					cur = 0;
					repeat = orgRepeat;
				}
			}
			lists.add(arr);
			varCount--;
		}
		for(int i = 0; i< rows; i++) {
			for(int j = 0; j < orgCount; j++) {
				truthTable[i][j] = lists.get(j)[i];
			}
		}

		return truthTable;
	}

}
