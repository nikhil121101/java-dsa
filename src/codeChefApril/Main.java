package codeChefApril;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Node {
	int key;
	Node left;
	Node right;
	int height;
	int size;
	Node(int key , Node left , Node right , int height , int size) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.height = height;
		this.size = size;
	}
}

class AVLTree {
	int height(Node N) {
		if (N == null)
			return 0;
		return N.height;
	}

	int size(Node N) {
		if (N == null)
			return 0;
		return N.size;
	}

	Node newNode(int key) {
		Node node = new Node(key , null , null , 1 , 1);
		return (node);
	}

	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		x.right = y;
		y.left = T2;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.size = size(y.left) + size(y.right) + 1;
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		y.left = x;
		x.right = T2;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.size = size(x.left) + size(x.right) + 1;
		y.size = size(y.left) + size(y.right) + 1;
		return y;
	}

	int getBalance(Node N) {
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	Node insert(Node node, int key, int[] count) {
		/* 1.  Perform the normal BST rotation */
		if (node == null)
			return (newNode(key));

		if (key < node.key) {
			node.left = insert(node.left, key, count);
		count[0] = count[0] + size(node.right) + 1;
		} else {
			node.right = insert(node.right, key, count);
		}
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		node.size = size(node.left) + size(node.right) + 1;
		int balance = getBalance(node);
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	void constructLowerArray(int arr[], int countSmaller[], int n) {
		int i, j;
		Node root = null;

		for (i = 0; i < n; i++)
			countSmaller[i] = 0;
		for (i = n - 1; i >= 0; i--) {
			int temp[] = new int[1];
			root = insert(root, arr[i], temp);
			countSmaller[i] = temp[0];
		}
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		while(t-- != 0) {
			int n = Integer.parseInt(br.readLine());
			int a[] = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < n ; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			int mod = (int)1e9 + 7;
			long pow[] = new long[n];
			pow[0] = 1;
			for(int i = 1 ; i < n ; i++) {
				pow[i] = (pow[i-1] * 2) % mod;
			}
			for(int i = 0 ; i < n ; i++) {
				sb.append(pow[n - a[i]]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
