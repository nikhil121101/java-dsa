package cs201Assingment.lab1.q2_q3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class BinarySearchTree {

	Node minHeightRoot;
	Node maxHeightRoot;

	BinarySearchTree(int a[] , int n) {
		insertArray(a , n);
	}

	private class Node {
		int key;
		Node left, right;

		Node(int key , Node left , Node right) {
			this.key = key;
			this.left = left;
			this.right = right;
		}
	}

	void inorder(Node temp) {
		if (temp == null)
			return;
		inorder(temp.left);
		System.out.print(temp.key + " ");
		inorder(temp.right);
	}

	void preorder(Node temp) {
		if (temp == null)
			return;
		System.out.print(temp.key + " ");
		preorder(temp.left);
		preorder(temp.right);
	}

	void postorder(Node temp) {
		if (temp == null)
			return;
		postorder(temp.left);
		postorder(temp.right);
		System.out.print(temp.key + " ");
	}

	Node insert(Node root , int val) {
		if(root == null) {
			return new Node(val , null , null);
		}
		if(val > root.key) {
			root.right = insert(root.right , val);
		}
		else if(val < root.key) {
			root.left = insert(root.left , val);
		}
		return root;
	}

	void insertArray(int a[] , int n) {
		// min height tree;
		minHeightRoot = null;
		Arrays.sort(a);
		int i = n/2 - 1 , j = n/2 + 1;
		minHeightRoot = insert(minHeightRoot , a[n/2]);
		while(i >= 0) {
			minHeightRoot = insert(minHeightRoot , a[i--]);
			minHeightRoot = insert(minHeightRoot , a[j++]);
		}
		maxHeightRoot = null;
		for(int itr = 0 ; itr < n ; itr++) {
			maxHeightRoot = insert(maxHeightRoot , a[itr]);
		}
	}
}

public class Example {

	static int[] minNodesForGivenHeight() {
		int nodeCount[] = new int[100];
		nodeCount[0] = 0;
		nodeCount[1] = 1;
		for(int i = 2 ; i < 100 ; i++) {
			nodeCount[i] = nodeCount[i-1] + nodeCount[i-2] + 1;
		}
		return nodeCount;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a[] = new int[n];
		for(int i = 0 ; i < n ; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		BinarySearchTree bst = new BinarySearchTree(a , n);
		System.out.println("Min Height tree -> ");
		System.out.print("preorder - ");
		bst.preorder(bst.minHeightRoot);
		System.out.print("\ninorder - ");
		bst.inorder(bst.minHeightRoot);
		System.out.print("\npostorder - ");
		bst.postorder(bst.minHeightRoot);
		System.out.println();
		System.out.println("\n Max Height tree");
		System.out.print("preorder - ");
		bst.preorder(bst.maxHeightRoot);
		System.out.print("\ninorder - ");
		bst.inorder(bst.maxHeightRoot);
		System.out.print("\npostorder - ");
		bst.postorder(bst.maxHeightRoot);
		System.out.println();
		System.out.println("second Question Enter n");
		n = Integer.parseInt(br.readLine());
		System.out.println("minHeight -> ");
		System.out.println(log2(n+1));
		System.out.println("maxHeight -> ");
		int[] nodeCount = minNodesForGivenHeight();
		for(int i = 0 ; i < 100 ; i++) {
			if(nodeCount[i] > n) {
				System.out.println(i-1);
				break;
			}
		}
	}
	public static int log2(int N)
	{
		return (int)(Math.log(N) / Math.log(2));
	}
}
/*
5
7 4 9 2 5
 */