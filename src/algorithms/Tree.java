package algorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Prints the nodes' values in breath first order, inorder, preorder, postorder
 * If needs the elements in the sorted order as implemented in Node
 * then use the PriorityQueue
 * @author Nitin
 *
 */
public class Tree {

	static Node tree = new Node();
	static Queue<Node> st1 = new PriorityQueue<>();
	static ArrayList<Node> st = new ArrayList<>();

	// Prepares the tree
	static {
		tree.value = 1;
		Node n1 = new Node();
		n1.value = 2;
		tree.left = n1;
		Node n2 = new Node();
		n2.value = 3;
		tree.right = n2;
		Node n3 = new Node();
		n3.value = 4;
		n1.left = n3;
		Node n4 = new Node();
		n4.value = 5;
		n1.right = n4;
		Node n5 = new Node();
		n5.value = 6;
		n2.left = n5;
		Node n6 = new Node();
		n6.value = 7;
		n2.right = n6;
	}

	public static void main(String[] args) {
		st.add(tree);
		//		breadthFirstValues();
		postOrderValues(tree);
	}

	static void breadthFirstValues() {
		if(st == null)
			return;
		while(!st.isEmpty()) {
			Node n = st.remove(0);
			if(n != null) {
				System.out.println(n.value);
			}
			if(n.left != null) {
				st.add(n.left);
			}
			if(n.right != null) {
				st.add(n.right);
			}
			if(!st.isEmpty())
				breadthFirstValues();
		}
	}

	static void inOrderValues(Node n) {
		if(n == null)
			return;
		if(n.left != null) {
			inOrderValues(n.left);
		}
		System.out.println(n.value);
		if(n.right != null) {
			inOrderValues(n.right);
		}
	}

	static void postOrderValues(Node n) {
		if(n == null)
			return;
		if(n.left != null) {
			postOrderValues(n.left);
		}
		if(n.right != null) {
			postOrderValues(n.right);
		}
		System.out.println(n.value);
	}

	static void preOrderValues(Node n) {
		if(n == null)
			return;
		System.out.println(n.value);
		if(n.left != null) {
			preOrderValues(n.left);
		}
		if(n.right != null) {
			preOrderValues(n.right);
		}
	}

}

// Implements Comparable to use its objects in PriorityQueue
class Node implements Comparable<Node>{
	Node left;
	Node right;
	int value;
	@Override
	public int compareTo(Node n) {
		if(this.value < n.value)
			return -1;
		else if(this.value > n.value)
			return 1;
		else
			return 0;
	}
}
