package patterns;

/**
 * Prob31 from https://www.techgig.com/challenge/pattern-problem
 * @author Nitin
 *
 */
public class Prob31 {
	public static void main(String[] args) {
		int startCh = 64;

		int count = Integer.parseInt(args[0]);
		int temp = count;
		int row = 1;
		boolean done = false;
		int dec = 1;

		while(temp > 0) {
			if(!done){
				for(int j = 0; j < count-row+temp-1; j++) {
					System.out.print(" ");
				}
				for(int i = 0; i < row; i++) {
					char ch = (char)(startCh + count - i);
					if(i == row-1)
						System.out.print(ch);
					else
						System.out.print(ch + " ");
				}
				row++;
				System.out.println();
			}

			temp--;
			if(row > count && !done) {
				temp = count;
				done = true;
				row--;
			}

			if(done) {
				for(int j = 0; j < dec*2; j++) {
					System.out.print(" ");
				}
				for(int i = 0; i < row-1; i++) {
					char ch = (char)(startCh + count - i);
					if(i == row-2)
						System.out.print(ch);
					else
						System.out.print(ch + " ");
				}
				System.out.println();
				row--;
				dec++;
			}
		}
	}
}
