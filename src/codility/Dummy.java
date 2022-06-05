package codility;

public class Dummy {

	/**
	 * Correctness : 81%
	 * Performance : 45%
	 * URL : https://app.codility.com/cert/view/certZ7XQYN-MUBZBDAVTXBVSRZ4/details/
	 * @author nitin
	 *
	 */
	public static int count(int[] A) {
		int len = A.length;
		int start = 0;
		int maxCount = 0;
		int count = 0;
		int sum = 0;
		int lastSum = 0;
		int sameSum = 0;
		int lastSameSum = 0;

		for(int i = 0; i < len; i++) {
			sum = sum + A[i];
			if(lastSum == sum) {
				sameSum++;
				if(lastSameSum < sameSum) {
					lastSameSum = sameSum;
				}
			}
			if(sum >= 0 || (sum < 0 && Math.abs(sum) <= len-i-1))
				count++;
			else if(sum < 0 && Math.abs(sum) > len-i-1 && A[start] <= 0) {
				int j = start;

				while(sum < 0 && Math.abs(sum) > len-i-1 && j < i) {
					sum = sum - A[j];
					if(sum == lastSum && A[j] > 0 && A[i] < 0) {
						sum = sum - A[i] + A[j];
					} else {
						j++;
					}
					lastSum = sum;
				}
				if(sum >= 0) {
					count = i - j + 1;
				}
				start = j;
				count = i - start + 1;
			} else if(sum < 0 && Math.abs(sum) > len-i && A[start] > 0) {
				int j = i;
				while(sum < 0 && j > start) {
					sum = sum - A[j];
					count = j - start;
					j--;
				}
				i = j;
				start = j;
			}

			if(maxCount < count && sum >= 0) {
				maxCount = count;
			}
			lastSum = sum;
		}
		if(maxCount < lastSameSum) {
			return lastSameSum;
		}
		return maxCount;
	}
	
	/**
	 * Correctness : 81%
	 * Performance : 27%
	 * URL : https://app.codility.com/cert/view/certE5CYR7-MD7D8EKZTFNRB63Q/details/
	 * @author nitin
	 *
	 */
	public static int count9(int[] A) {
		int len = A.length;
		int sum = 0;
		int maxCount = 0;
		int same = 0;
		int pos = 0;
		int lastSum = 0;
		int[] counts = new int[len];

		for(int i = 0; i < len; i++) {
			sum = sum + A[i];
			if(sum >= 0 && maxCount < (i-pos+1)) {
				maxCount = i-pos+1;
				if(counts[i] < maxCount) {
					counts[i] = maxCount;
				}
			} else if(sum < 0 && sum > lastSum) {
				int j = i;
				int tempSum = A[i];
				int count = 0;
				while(tempSum >= 0 && j > 0) {
					j--;
					tempSum = tempSum + A[j];
					if(tempSum < 0) {
						count = i-(j+1) + 1;
					} else {
						count = i-j+1;
					}
					if(counts[j] < count)
						counts[j] = count;
				}
				if(count > maxCount)
					maxCount = count;
			} else if(sum == lastSum && sum < 0) {
				int j = i;
				same++;
				int tempSum = A[i];
				int count = 0;
				while(j > 0 && j >= Math.abs(tempSum)) {
					j--;
					tempSum = tempSum + A[j];
					if(tempSum >= 0) {
						count = i - j + 1;
						if(count > maxCount)
							maxCount = count;
					}
					if(counts[j] < count)
						counts[j] = count;
				}
				if(maxCount < same)
					maxCount = same;
			}
			lastSum = sum;
		}

		return maxCount;

	}
	/**
	 * It is the most correct one till now - 81%
	 * @param A
	 * @return
	 */
	public static int count8(int[] A) {

		int len = A.length;
		int curSum = 0;
		int lastSum = 0;
		int maxCount = 0;
		int pos = 0;
		int posMax = 0;
		int iMax = 0;
		int zeroes = 0;
		int sum = 0;

		for(int i : A) {
			sum = sum + i;
		}

		for(int i = 0; i < len; i++) {
			curSum = curSum + A[i];
			if(A[i] == 0)
				zeroes++;

			if(curSum > lastSum && curSum < 0 && (A[pos] < 0 || (A[pos] > 0 && i-pos > 1))) {
				int j = ++pos;
				while(A[pos] == 0) {
					pos++;
				}
				curSum = 0;
				while(j <= i) {
					curSum = curSum + A[j];
					j++;
				}
				if(curSum < 0 && pos < i) {
					i--;
				}
			} else if(curSum == 0 && lastSum == -1 && A[pos] < 0 && i != 0) {
				int j = ++pos;
				curSum = 0;
				while(j <= i) {
					curSum = curSum + A[j];
					j++;
				}
				if(curSum < 0 && pos < i) {
					i--;
				}
			}
			if((maxCount < i - pos + 1 && curSum >= 0) || (maxCount > i-pos+1 && iMax < i && posMax < pos && curSum >= 0)) {
				maxCount = i - pos + 1;
				iMax = i;
				posMax = pos;
			}

			lastSum = curSum;
		}
		int add = 0;
		if(pos >= lastSum-1 && pos > 0) {
			while(lastSum >= 0 && pos > 0) {
				pos--;
				lastSum = lastSum + A[pos];
				if(lastSum >= 0)
					add++;
			}
		}

		if(sum == 0) {
			return len;
		}

		if(maxCount == 0)
			return zeroes;
		return maxCount+add;

	}

	public static int count7(int[] A) {

		int sum = 0;
		int lastSum = 0;
		int cur = 0;
		int maxLen = 0;
		int zeroes = 0;

		for(int i = 0; i < A.length; i++) {
			sum = sum + A[i];
			if(A[i] == 0)
				zeroes++;

			if(sum > lastSum && sum <= 0 && lastSum < 0) {
				if(A[cur] <= 0 && sum < 0)
					cur++;
				int j = cur;
				sum = 0;
				while(j <= i) {
					sum = sum + A[j];
					j++;
				}
				if(sum < 0)
					i--;
			} else if (sum > lastSum && sum >= 0 && lastSum >= 0) {
				if(cur > 0 && sum > 0)
					cur--;
				int j = cur;
				sum = 0;
				while(j <= i) {
					sum = sum + A[j];
					j++;
				}
			} else if(lastSum == 0 && sum == -1 && i > 0 && A[cur] < 0) {
				cur++;
				sum = A[i]+A[i-1];
			}
			else if(sum < 0) {
				lastSum = sum;
				continue;
			}

			if(maxLen < (i-cur+1) && sum >= 0)
				maxLen = i-cur+1;
			lastSum = sum;
		}

		return maxLen > zeroes ? maxLen : zeroes;
	}

	public static int LargestLongestFirst(int[] source)
	{
		int curSum = 0, curStart = 0, curLength = 0,
				maxSum = 0, maxSart = 0, maxLength = 0;

		for (int index = 0; index < source.length; index++)
		{
			if (source[index] == -1)
			{
				if ((curSum < maxSum) || ((curSum == maxSum) && (curLength > maxLength))) 
				{
					maxSum = curSum;
					maxSart = curStart;
					maxLength = curLength;
				}
				curStart = index;
				curSum += source[index];
				//	            curSum = curLength = 0;
			}
			else
			{
				curSum += source[index];
				++curLength;
			}
		}
		if ((curSum >= maxSum) || ((curSum == maxSum) && (curLength > maxLength)))
		{
			maxSum = curSum;
			maxSart = curStart;
			maxLength = curLength;
		}
		//	    int[] result = new int[maxLength];
		//	    System.arraycopy(source, maxSart, result, 0, maxLength);
		return maxLength;
	}

	public static int count6(int[] A) {int minMax = -1;
	int minMin = -1;
	int sum = 0;
	int len = A.length;
	int posZero = -1;
	int posMax = 0;
	int posMin = 0;
	int Zeroes = 0;

	for(int i = 0; i < len; i++) {
		sum = sum + A[i];
		if(A[i] == 0) {
			Zeroes++;
		}
		if(sum >= 0) {
			posZero = i;
		}
		if(sum <= minMin) {
			minMin = sum;
			posMin = i;
		}
		if((sum >= minMax && posMax < i) ||(sum > minMax && posMax >= i)) {
			minMax = sum;
			posMax = i;
		}
	}

	if(posZero != -1)
		return posZero + 1;
	else if(posMax < posMin && posMin - posMax != len-1) {
		return Math.abs(posMax - posMin) - 1;
	} else if(posMax < posMin && posMin - posMax == len-1 && Zeroes == 0) {
		return 0;
	} else if(posMax > posMin) {
		return posMax;
	}
	else {
		return Zeroes;
	}
	}

	public static int count5(int[] A) {

		int len = A.length;
		int[] sums = new int[len];
		int sum = 0;
		int lastSum = 0;
		int count = 0;
		int maxCount = -1;
		int last = 0;
		int pos = -1;
		boolean isZero = false;
		for(int i = 0; i < len; i++) {
			sum = sum + A[i];
			sums[i] = sum;
			if(last != sum) {
				last = sum;
				count = 1;
			} else if(last == sum) {
				count++;
			}
			if(maxCount < count) {
				maxCount = count;
			}
			if(sum >= 0) {
				pos = i;
				isZero = true;
			} else if(lastSum < sum && sum < 0 && !isZero) {
				pos = i;
			}
			lastSum = sum;
		}

		if(pos != -1 && (sum >= 0 || isZero)) {
			return pos+1;
		} else if(sum < 0 && pos != 0 && pos > Math.abs(sum)) {
			return A.length + sums[pos];
		} else {
			return ++maxCount;
		}
	}

	public static int count4(int[] A) {

		int sum = 0;
		int pos = -1;
		int lastSum = -1;
		int len = 0;
		boolean start = false;
		boolean end = false;

		for(int i = 0; i < A.length; i++) {
			sum = sum + A[i];
			if(sum >= 0) {
				pos = i;
			}
			if(lastSum >= sum && pos > 0) {
				start = true;
				end = false;
			} else if(sum >= 0){
				start = false;
				end = true;
			}
			lastSum = sum;
		}
		if(start)
			return pos+1;
		else
			return A.length-pos;
	}

	public static int count3(int[] A) {

		int sum = 0;
		int lastSum = Integer.MIN_VALUE;
		int prevSum = 0;
		int position = -1;
		int count = 0;
		for(int i = 0; i < A.length; i++) {
			sum = sum + A[i];
			if((lastSum >= sum && sum == 0) || (lastSum < sum && sum != 0) || (prevSum <= 0 && sum == 0)) {
				position = i;
				prevSum = sum;
			}
			lastSum = sum;
		}
		if(lastSum == 0 && position == A.length-1) {
			return A.length;
		}

		int i = position;
		sum = 0;
		while(i >= 0 && sum >= 0) {
			sum = sum + A[i];
			i--;
		}

		return A.length-(i+2);
	}

	public static int count2(int[] A) {

		int sum = 0;
		int count = 0;
		int countf = 0;
		boolean started = false;
		boolean pos = false;
		for(int i = 0; i < A.length; i++) {
			sum = sum + A[i];
			if(sum >= 0 && started) {
				count++;
				countf = count;
				pos = true;
			} else if(started && pos) {
				count = 0;
				pos = false;
			} else {
				started = true;
				count++;
			}
		}

		return countf;
	}

	public static int count1(int[] A) {		
		int sum = 0;
		int max = 0;
		int count = 0;
		int len = A.length;
		int maxNeg = 0;
		int neg = 0;
		int maxPos = 0;
		int pos = 0;

		for(int i = 0; i < len; i++) {
			if(A[i] < 0) {
				neg++;
				if(maxPos < pos)
					maxPos = pos;
				pos = 0;
			} else if(A[i] > 0) {
				pos++;
				if(maxNeg < neg)
					maxNeg = neg;
				neg = 0;
			} else if(A[i] == 0) {
				if(maxPos < pos)
					maxPos = pos;
				pos = 0;
				if(maxNeg < neg)
					maxNeg = neg;
				neg = 0;
			}
			sum = sum + A[i];
			count++;
			if(sum >= 0) {
				if(max < count) {
					max = count;
				}
			}
		}

		if(max == 0 && sum < 0) {
			if(maxPos > 0) {
				while(sum < 0 && maxPos > 0) {
					count--;
					sum++;
					maxPos--;
				}
				max = count;
			}
			if(maxNeg > 0 && sum < 0) {
				while(sum < 0 && maxNeg > 0) {
					count--;
					sum++;
					maxNeg--;
				}
				max = count;
			}
			while(sum < 0) {
				sum++;
				max--;
			}
		}

		return max;
	}

}
