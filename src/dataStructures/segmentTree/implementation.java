package dataStructures.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.MarshalException;
import java.util.Arrays;
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

class SegmentTree {
    int[] array;
    long[] tree;
    int n;
    SegmentTree(int a[] , int n) {
        this.tree = new long[4*n + 1];
        this.array = a;
        this.n = n;
        buildTree();
    }

    void buildTree() {
        buildTreeHelper(0 , 0 , n-1);
    }

    void buildTreeHelper(int i , int start , int end) {
        if(start > end) {
            return;
        }
        if(start == end) {
            tree[i] = array[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTreeHelper(2*i + 1 , start , mid);
        buildTreeHelper(2*i + 2 , mid + 1 , end);
        tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
    }

    char overlap(int start , int end , int qs , int qe) {
        if(qe < start || qs > end || start > end) {
            return 0;
        }
        if(qs <= start && qe >= end) {
            return 2;
        }
        return 1;
    }

    long sumQuery(int start , int end) {
        return sumQueryHelper(0 , 0 , n-1 , start , end);
    }

    long sumQueryHelper(int i , int start , int end , int qs , int qe) {
        if(overlap(start , end , qs , qe) == 0) {
            return 0;
        }
        if(overlap(start , end , qs , qe) == 1) {
            int mid = (start + end) / 2;
                return sumQueryHelper(2 * i + 1 , start , mid , qs , qe) +
                        sumQueryHelper(2 * i + 2 , mid + 1 , end , qs , qe);
        }
        else {
            return tree[i];
        }
    }
}

public class implementation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int a[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int i = 0 , j = n-1;
        while(i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
        AVLTree avlTree = new AVLTree();
        int res[] = new int[n];
        avlTree.constructLowerArray(a , res , n);
        i = 0;
        j = n-1;
        while(i < j) {
            int temp = res[i];
            res[i] = res[j];
            res[j] = temp;
            i++;
            j--;
        }
        SegmentTree segTree = new SegmentTree(res , n);
        while(q-- != 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(segTree.sumQuery(l - 1 , r - 1)).append("\n");
        }
        System.out.print(sb.toString());
    }
}
