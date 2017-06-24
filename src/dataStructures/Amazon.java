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
		System.out.println(Amazon.class.getClassLoader().toString());
		output = warehouseScalability(input1,input2);
		for(int output_i=0; output_i < output.length; output_i++) {
			System.out.println(String.valueOf(output[output_i]));
		}
	}

	public static String[] warehouseScalability(String[] input1,String[] input2)
	{
		HashMap<String, ArrayList<Worker>> workers = new HashMap<>();
		HashMap<String, Integer> skills = new HashMap<>();
		ArrayList<Worker> pq;
		String skill;

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
		Collections.sort(priorities);

		for(Task task : priorities) {
			ArrayList<Worker> al = workers.get(task.skill);
			Collections.sort(al);
//			To change the assignment order, provide the correct ordering in comparator, currently it is wrong & giving NullPointerException 
//			Collections.sort(al, new Comps());
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
		String[] values = new String[assign.size()];
		return assign.toArray(values);
	}
}

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

class Comps implements Comparator<Worker> {

	@Override
	public int compare(Worker o1, Worker o2) {
		if(o1.time < o2.time && o1.taskIds != null && o2.taskIds != null && o1.taskIds.size() < o2.taskIds.size())
			return -1;
		else if(o1.time > o2.time && o1.taskIds != null && o2.taskIds != null && o1.taskIds.size() > o2.taskIds.size())
			return 1;
		else if(o1.time < o2.time && o1.taskIds == null && o1.remSkills != null && o1.remSkills.get(o1.skill)!= 0 && o1.remSkills.get(o1.skill) > 0)
			return -1;
		else if(o1.time > o2.time && o2.taskIds == null && o1.remSkills != null && o1.remSkills.get(o1.skill)!= 0 && o1.remSkills.get(o1.skill) > 0)
			return 1;
		else
			return o1.designation.compareTo(o2.designation);
	}

}

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
