package fileUpdaters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Below class will generate the sum of numbers which are less than 27000
 * and divisible by 2.
 * It uses the feature of Fork & Join to divide the array. Also as we may get
 * an array will some random numbers at random positions in it & we need to take
 * some actions as per some condition, so here using the feature of streaming
 * for that.
 *
 */
public class CustomRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private int[] arr;
 
    private static final int THRESHOLD = 200;
 
    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }
    
    public static void main(String[] args) {
    	int size = 100000000;
    	int[] arr = new int[size];
		for(int i = 1; i <= size; i++) {
			arr[i-1] = i;
		}
		CustomRecursiveTask crt = new CustomRecursiveTask(arr);
		System.out.println("Result : " + crt.compute());
	}
 
    /*
     * This method is main method of activity & I myself don't
     * understand it properly, how it is working exactly.
     */
    @Override
    protected Integer compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
              .stream()
              .mapToInt(ForkJoinTask::join)
              .sum(); //we can use reduce(0, Integer::sum) in place of sum() here;
        } else {
        	int val = processing(arr);
            return val;
        }
    }
 
    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }
 
    private Integer processing(int[] arr) {
        return Arrays.stream(arr)
          .filter(a -> a%2 == 0 && a < 27000)
          .map(a -> a * 10)
          .sum();
    }
}