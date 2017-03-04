package puzzles;

/*
 * There are one hundred closed lockers in a hallway.
 * A man begins by opening all one hundred lockers.
 * Next, he closes every second locker.
 * Then he goes to every third locker and closes it if it is open or opens it if it is closed
 * (e.g., he toggles every third locker).
 * After his one hundredth pass in the hallway, in which he toggles only locker number one hundred,
 * how many lockers are open?
 */
public class Doors {

	public static void main(String[] args) {
		boolean[] doors = new boolean[100];
		for(int i = 0; i < 100; i++) {
			doors[i] = false;
		}
		int round = 1;
		while(round <= 100) {
			for(int i = 0; i < 100; i++) {
				if((i+1)%round == 0) {
					if(doors[i])
						doors[i] = false;
					else
						doors[i] = true;
				} 
			}
			round++;
		}

		for(int i = 0; i < 100; i++) {
			if(doors[i]) {
				System.out.println(i+1);
			}
		}
	}

}
