package dataStructures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * To assign the tasks to the available people depending on the kind of skills & designation
 * To get the maximum utilization of people.
 * @author Nitin
 *
 */
public class Amazon {

	public static void main(String[] args) throws IOException{
		String[] output = null;
		/*String[] input1 = {"W4#S1", "W2#S2", "W3#S3", "W1#S1"};
		String[] input2 = {"S1#40#10#101", "S2#10#5#102", "S3#90#15#103", "S1#40#15#104"};*/
		String[] input1 = {"W1#S1", "W2#S2", "W3#S3", "W4#S1"};
		String[] input2 = {"S1#40#10#101", "S2#10#5#102", "S3#90#15#103", "S3#91#20#104", "S2#20#5#105", "S1#20#10#106", "S1#90#15#107", "S2#30#20#108", "S3#40#5#109", "S1#50#5#110"};
		long start = System.currentTimeMillis();
		output = warehouseScalability(input1,input2);
		System.out.println("Time taken to calculate & assign the tasks : " + (System.currentTimeMillis() - start) + "ms");
		/**
		 * Printing the results
		 */
		for(int output_i=0; output_i < output.length; output_i++) {
			System.out.println(String.valueOf(output[output_i]));
		}
	}

	/**
	 * @param input1 - Array of workers with their skills
	 * @param input2 - Array of tasks with skill required, priority, time & task id
	 * @return - Array of strings containing the worker & tasks assigned
	 */
	public static String[] warehouseScalability(String[] input1,String[] input2)
	{
		HashMap<String, ArrayList<Worker>> workers = new HashMap<>();
		/**
		 * This HashMap is used to keep the number of remaining tasks related to particular skill, which helps in deciding when to use the
		 * comparator to sort the workers.
		 */
		HashMap<String, Integer> skills = new HashMap<>();
		ArrayList<Worker> pq;
		String skill;

		/**
		 * Creating the worker objects and storing the list of workers mapped to the skill in hashmap
		 */
		for(String str : input1) {
			Worker worker = new Worker();
			skill = str.split("#")[1];
			worker.skill = skill;
			worker.designation = str.split("#")[0];
			if((pq = workers.get(skill)) == null) {
				pq = new ArrayList<>();
				pq.add(worker);
			} else {
				pq.add(worker);
			}
			workers.put(skill, pq);
		}

		/**
		 * Creating the Task objects & putting them in ArrayList
		 */
		ArrayList<Task> priorities = new ArrayList<>(input2.length);
		for(String str : input2) {
			String[] arr = str.split("#");
			Task task = new Task();
			task.taskId = Integer.parseInt(arr[3]);
			task.skill = arr[0];
			task.priority = Integer.parseInt(arr[1]);
			task.time = Integer.parseInt(arr[2]);
			int count = skills.get(task.skill)==null ? 0 : skills.get(task.skill);
			skills.put(task.skill, count+1);
			priorities.add(task);
		}
		/**
		 * Sorting the ArrayList according to the comparable logic given in Task class
		 */
		Collections.sort(priorities);

		/**
		 * Calculating the number of related tasks remaining to schedule & assigning the tasks accordingly to the concerned person
		 */
		for(Task task : priorities) {
			ArrayList<Worker> al = workers.get(task.skill);
			/**
			 * If remaining related tasks are greater than 1 then sorting the workers according to the time & not designation, so using the comparator.
			 * Currently not using the above logic to sort, currently sorting using comparator for every scenario, but user can change it per the
			 * required assignment rules.
			 */
			//			if(skills.get(task.skill) >= 1)
			Collections.sort(al, new Comps());
			/*else
				Collections.sort(al);*/
			Worker worker = al.get(0);
			ArrayList<Integer> taskIds;
			if((taskIds = worker.taskIds) == null) {
				taskIds = new ArrayList<>();
				taskIds.add(task.taskId);
			} else {
				taskIds.add(task.taskId);
			}
			worker.taskIds = taskIds;
			int count = skills.get(task.skill);
			skills.put(task.skill, count-1);
			worker.time = worker.time + task.time;
			worker.remSkills = skills;
		}

		/**
		 * Fetching the results as String in ArrayList.
		 * String[] could have been used but not using because don't know the size in advance.
		 * So first getting results in ArrayList then converting that String[] to return.
		 */
		ArrayList<String> assign = new ArrayList<>();
		for(ArrayList<Worker> al : workers.values()) {
			for(Worker worker : al) {
				String str = new String();
				str = worker.designation + "#";
				for(int id : worker.taskIds) {
					str = str + id + "#";
				}
				str = str.substring(0, str.length()-1);
				assign.add(str);
			}
		}
		/**
		 * Sorting the ArrayList according to the designation of workers to get the proper formatted output
		 */
		Collections.sort(assign);
		String[] values = new String[assign.size()];
		return assign.toArray(values);
	}
}

/**
 * @author Nitin
 *
 */
class Worker implements Comparable<Worker>{
	String designation;
	String skill;
	ArrayList<Integer> taskIds;
	int time;
	HashMap<String, Integer> remSkills;
	@Override
	public int compareTo(Worker o) {
		if(this.time == o.time)
			return this.designation.compareTo(o.designation);
		else if(this.designation.compareTo(o.designation) < 0 && this.time < o.time)
			return 1;
		else
			return -1;
	}
}

/**
 * This comparator is used to sort the Worker objects in different way than mentioned in Worker class.
 * @author Nitin
 *
 */
class Comps implements Comparator<Worker> {

	@Override
	public int compare(Worker o1, Worker o2) {
		if(o1.time < o2.time)
			return -1;
		else if(o1.time > o2.time)
			return 1;
		else
			return o1.designation.compareTo(o2.designation);
	}

}

/**
 * @author Nitin
 *
 */
class Task implements Comparable<Task>{
	int taskId;
	String skill;
	int time;
	int priority;
	@Override
	public int compareTo(Task o) {
		if(this.priority < o.priority)
			return 1;
		else if(this.priority > o.priority)
			return -1;
		else if(this.priority == o.priority && this.time > o.time)
			return -1;

		return 0;
	}
}
