package algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * LCA is the root of the minimum sub-tree which has all the elements.
 * 
 * Sol - Use DFS and count the no of elements seen. As soon as the count
 * becomes equal to the no elements we have our root
 * 
 * There is an even better solution if it is known that all the elements are present
 * 
 * @author agraj
 *
 */
public class LCA_BestAlgo {

	private static TreeNode lca = null;
	
	public static void main(String[] args) {
		TreeNode tn0 = new TreeNode(0);
		TreeNode tn1 = new TreeNode(1);
		TreeNode tn2 = new TreeNode(2);
		TreeNode tn3 = new TreeNode(3);
		TreeNode tn4 = new TreeNode(4);
		TreeNode tn5 = new TreeNode(5);
		TreeNode tn6 = new TreeNode(6);
		TreeNode tn7 = new TreeNode(7);
		TreeNode tn8 = new TreeNode(8);
		TreeNode tn9 = new TreeNode(9);
		TreeNode tn10 = new TreeNode(10);
		TreeNode tn11= new TreeNode(11);
		
		tn0.leftChild = tn1;
		tn0.rightChild = tn2;
		
		tn1.leftChild = tn3;
		tn1.rightChild = tn4;
		
		tn2.leftChild = tn5;
		tn2.rightChild = tn6;
		
		tn3.leftChild = tn7;
		tn3.rightChild = tn8;
		
		tn4.leftChild = tn9;
		tn4.rightChild = tn10;
		
		tn5.leftChild = tn11;
		
		TreeNode root = tn0;
		Set<TreeNode> nodesToFindLCAFor = new HashSet<>();
		nodesToFindLCAFor.add(tn1);
		nodesToFindLCAFor.add(tn5);
		nodesToFindLCAFor.add(tn11);
//		nodesToFindLCAFor.add(tn4);
		findLCA(root, nodesToFindLCAFor);
		lca = null;
//		System.out.println(findLCA(tn1, tn10));
//		System.out.println(findLCA(tn8, tn2));
//		System.out.println(findLCA(tn8, tn11));
//		System.out.println(findLCA(tn1, tn3));
	}
	
	private static void findLCA(TreeNode root, Set<TreeNode> nodesToFindLCAFor){
		noOfNodesSeen(root, nodesToFindLCAFor);
		System.out.println(lca.value);
	}
	
	private static int noOfNodesSeen(TreeNode node, Set<TreeNode> nodesToFindLCAFor){
		if(node == null)
			return 0;
		
		int leftCount = noOfNodesSeen(node.leftChild, nodesToFindLCAFor);
		if(lca != null){
			return -1;
		}
		
		int rightCount = noOfNodesSeen(node.rightChild, nodesToFindLCAFor);
		if(lca != null){
			return -1;
		}
		int count = leftCount + rightCount; 
		if(nodesToFindLCAFor.contains(node)){
			count++;
		}
		if(count == nodesToFindLCAFor.size()){
			lca = node;
			return -1;
		}else{
			return count;
		}
	}
	
	private static TreeNode findLCAEfficient(TreeNode node, List<Integer> elements){
		if(node == null) {
			return null;
		}
		
		if(elements.contains(node.value)) {
			return node;
		}
		TreeNode leftSubtreeLCA = findLCAEfficient(node.leftChild, elements);
		TreeNode rightSubtreeLCA = findLCAEfficient(node.rightChild, elements);
		if(leftSubtreeLCA != null && rightSubtreeLCA != null) {
			return node;
		} else if(leftSubtreeLCA == null) {
			return node.rightChild;
		} else {
			return node.leftChild;
		}
		
	}
	
	public static class TreeNode {
		
		int value;
		public TreeNode(int data) {
			this.value = data;
		}
		TreeNode leftChild;
		TreeNode rightChild;
	}
}
