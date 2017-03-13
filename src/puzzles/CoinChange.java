package puzzles;

/**
 * Coin change problem
 * @author Nitin
 *
 */
public class CoinChange {
	static int[] coins = {10, 5, 2, 1};
	static int count = 0;

	public static void main(String[] args) {

		int amount = 2307;
		count = amount/coins[0];
		if(amount%coins[0] != 0) {
			change(amount%coins[0], coins[1], 1);
		} 
		
		System.out.println("Number of coins required : " + count);
	}

	public static void change(int amount, int next, int i) {

		count = count + amount/next;
		if(i == coins.length-1 || amount%next == 0)
			return;
		change(amount%next, coins[i+1], i+1);
	}
}
