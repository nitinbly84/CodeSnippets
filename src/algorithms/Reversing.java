package algorithms;

import java.lang.reflect.Field;

/**
 * Reversing the String where new String is created.
 * Reversing the actual String with no extra string created.
 * Reversing the number.
 * @author Nitin
 *
 */
public class Reversing {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String str = "Nitin Agrawal";
		String str2 = "Nitin Agrawal";
		char[] chr = str.toCharArray();
		int len = chr.length;
		int i = 0;
		int j = len-1;
		// Below way reverses the string but it creates the new string with reversed characters.
		while(i < j) {
			char temp = chr[i];
			chr[i] = chr[j];
			chr[j] = temp;
			i++;
			j--;
		}

		System.out.println(new String(chr));

		// Below way reverses the actual string with no extra string created
		Field field = String.class.getDeclaredField("value");
		field.setAccessible(true);
		chr = (char[])field.get(str);

		i = 0;
		j = len-1;
		while(i < j) {
			char temp = chr[i];
			chr[i] = chr[j];
			chr[j] = temp;
			i++;
			j--;
		}
		System.out.println(str2);

		// Below way reverses the given number.
		int num = 0;
		int a = 456467768;
		while(a >= 1) {
			int temp = a%10;
			a = a/10;
			num = num*10 + temp;
		}
		System.out.println(num);
	}

}
