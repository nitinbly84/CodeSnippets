package codility;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import rNd.LargeIntArray;

/**
 * @author Nitin Agrawal
 * @Date 23-Feb-2020
 */
@RunWith(Parameterized.class)
public class Palladium2020Test {

	private TestCase testCase;

	public Palladium2020Test(TestCase testCase) {
		super();
		this.testCase = testCase;
	}

	@Parameterized.Parameters
	public static Collection input() {
		ArrayList<TestCase> testCases = new ArrayList<>();
		TestCase t0 = new TestCase(new int[]{3,1,4}, 10);
		TestCase t1 = new TestCase(new int[]{5,4,3,2,1}, 19);
		TestCase t2 = new TestCase(new int[]{5,3,2,4}, 17);
		TestCase t3 = new TestCase(new int[]{5,3,5,2,1}, 19);
		TestCase t4 = new TestCase(new int[]{1,3,5,2,5}, 21);
		TestCase t5 = new TestCase(new int[]{7,7,3,7,7}, 35);
		TestCase t6 = new TestCase(new int[]{1,1,7,6,6,6}, 30);
		TestCase t7 = new TestCase(new int[]{6,6,6,7,1,1}, 30);
		TestCase t8 = new TestCase(new int[]{5,4,5,4,4,4,4}, 31);
		TestCase t9 = new TestCase(new int[]{5,4}, 9);
		TestCase t10 = new TestCase(new int[]{5,4,5,4,5,4,5}, 35);
		TestCase t11 = new TestCase(new int[]{1,1,1,1,2,2}, 8);
		TestCase t12 = new TestCase(new int[]{1,2,2,3,4,5,5,4,3,2,1}, 46);
		TestCase t13 = new TestCase(new int[]{1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1}, 101);
		TestCase t14 = new TestCase(new int[]{1,5,1,1,5,5,1,1,2,1}, 38);
		TestCase t15 = new TestCase(new int[]{1,3,3,2,1,3,2,3,5}, 29);
		TestCase t16 = new TestCase(new int[]{5,4,3,2,5,4,5,3,2,1}, 44);
		TestCase t17 = new TestCase(new int[]{1,4,3,2,5,4,5,3,2,5}, 46);
		TestCase t18 = new TestCase(new int[]{1,4,5,2,4,4,4,3,2,3}, 43);
		TestCase t19 = new TestCase(new int[]{10,9,8,7,5,5,4,3,2,1}, 70);
		TestCase t20 = new TestCase(new int[]{1,2,3,4,4,3,2,1}, 28);
		TestCase t21 = new TestCase(new int[]{1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1}, 156);
		TestCase t22 = new TestCase(new int[]{1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1,1,2,2,3,4,5,5,4,3,2,1}, 486);
		TestCase t23 = new TestCase(new int[]{10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1,10,9,8,7,5,5,4,3,2,1}, 770);
		int[] H = generate(50, 20);
		int val = JatinPalladium.solution(H);
		TestCase t24 = new TestCase(H, val);
		int size = 99999999;
		H = generate(size, 10000);
		System.out.println("Array generated of size..." + size);
		long start = System.nanoTime();
		val = JatinPalladium.solution(H);
//		val = Palladium2020.solution(H);
		System.out.println("Value by Jatin : " + val);
		System.out.println("Time taken by Jatin : " + ((System.nanoTime()-start)/1000000f) + " ms");
		
		start = System.nanoTime();
		System.out.println("Value by Nitin : " + Palladium2020.solution1(H));
		System.out.println("Time taken by Nitin : " + ((System.nanoTime()-start)/1000000f) + " ms");
		TestCase t25 = new TestCase(H, val);

            // Extra large array testing
//			long hugeSize = Integer.MAX_VALUE + 99999999l;
//			LargeIntArray lia = new LargeIntArray(hugeSize);
//			Random random = new Random(1);
//			for(long i = 0; i < hugeSize; i++) {
//				lia.setValue(i,random.nextInt(20));
//			}
//			System.out.println("Large Array generated of size..." + hugeSize);
//			start = System.nanoTime();
//			System.out.println("Value by Nitin : " + Palladium2020.solution1(lia));
//			System.out.println("Time taken by Nitin : " + ((System.nanoTime()-start)/1000000f) + " ms");
//			start = System.nanoTime();
//			System.out.println("Value by Nitin first : " + Palladium2020.solution(lia));
//			System.out.println("Time taken by Nitin first : " + ((System.nanoTime()-start)/1000000f) + " ms");
//			lia.destroy();

		testCases.add(t0);
		testCases.add(t1);
		testCases.add(t2);
		testCases.add(t3);
		testCases.add(t4);
		testCases.add(t5);
		testCases.add(t6);
		testCases.add(t7);
		testCases.add(t8);
		testCases.add(t9);
		testCases.add(t10);
		testCases.add(t11);
		testCases.add(t12);
		testCases.add(t13);
		testCases.add(t14);
		testCases.add(t15);
		testCases.add(t16);
		testCases.add(t17);
		testCases.add(t18);
		testCases.add(t19);
		testCases.add(t20);
		testCases.add(t21);
		testCases.add(t22);
		testCases.add(t23);
		testCases.add(t24);
		testCases.add(t25);
		return testCases;
	}

	public static int[] generate(int size, int range) {
		int[] array = new int[size];
		Random random = new Random(1);
		for(int i = 0; i < size; i++) {
			array[i] = random.nextInt(range);
		}
		return array;
	}

	/**
	 * Test method for {@link codility.Palladium2020#solution(int[])}.
	 */
	@Test
	public final void testSolution() {
		assertTrue(Palladium2020.solution1(testCase.getS()) == testCase.getResult());
	}

	static class TestCase {
		private int[] s;
		private int result;

		public TestCase(int[] s, int result) {
			this.s = s;
			this.result = result;
		}

		public int[] getS() {
			return s;
		}
		public void setS(int[] s) {
			this.s = s;
		}
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
	}
}
