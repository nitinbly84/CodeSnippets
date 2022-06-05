package codility;

import java.util.HashMap;
import java.util.Map;

public class Strontium2019 {

	public static void main(String[] args) {

	}

	public int solution(String[] words) {

		int max = 0;
		Map<String, Integer> records = new HashMap<>();
		Map[] positions = new Map[3];
		int count = 0;
		int temp = 0;
		int midA = 0;
		int midB = 0;
		int length = words.length;

		for(int i = 0; i < length-1; i++) {
			String wordI = words[i];
			StringBuffer tempStringF = new StringBuffer();
			StringBuffer tempStringB = new StringBuffer();
			for(int j = i+1; j < length; j++) {
				String wordJ = words[j];
				char ch = wordI.charAt(0);
				for(char c : wordI.toCharArray()) {
					if(c == ch) {
						temp++;
					} else if(c == wordI.charAt(wordI.length()-1)) {
						
					}
				}
				if(j == i+1 && wordI.charAt(0) == wordJ.charAt(wordJ.length()-1)) {
					tempStringF = tempStringF.append(wordJ).append(wordI);
				} else if(j == i+1 && wordI.charAt(wordI.length()-1) == wordJ.charAt(0)) {
					tempStringB = tempStringB.append(wordI).append(wordJ);
				}
				
			}

		}

		return 1;
	}

}
