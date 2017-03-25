package codeChef;

public class BearandLadder {
	
	public static void main(String[] args) {
		int len = args.length;
		for(int i = 1; i < len; i+=2) {
			int a = Integer.parseInt(args[i].trim());
			int b = Integer.parseInt(args[i+1].trim());
			if(a > b) {
				int temp = a;
				a = b;
				b = temp;
			}
			int div = a/2;
			if(b-(div*2) == 2)
				System.out.println("YES");
			else if(a%2 == 1 && b%2 == 1 && b-a == 2)
				System.out.println("YES");
			else if(a%2 == 0 && b%2 == 0 && b-a == 2)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

}
