package dataStructures.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DQuery {
    private static class SegmentTree {
        int minV = (int)-1e9;
        private class node {
            Set<Integer> set;
            int size;
            node() {
            }
            node(Set<Integer> set) {
                this.set = set;
                this.size = set.size();
            }
        }
        int[] array;
        SegmentTree.node[] tree;
        int[] lastOcc;
        int n;
        SegmentTree(int a[], int n , int[] lastOcc) {
            this.tree = new node[5 * n + 1];
            for (int i = 0 ; i < 5*n + 1 ; i++) {
                tree[i] = new node();
            }
            this.array = a;
            this.lastOcc = lastOcc;
            this.n = n;
            for (int i = 0; i < n; i++) {
                
            }
            buildTree();
        }
        void buildTree() {
            buildTreeHelper(0, 0, n - 1);
        }

        void buildTreeHelper(int i, int start, int end) {
            HashSet<Integer> set = new HashSet<>();
            if (start > end) {
                return;
            }
            if (start == end) {
                set.add(array[start]);
                tree[i] = new node(set);
                return;
            }
            int mid = (start + end) / 2;
            buildTreeHelper(2 * i + 1, start, mid);
            buildTreeHelper(2 * i + 2, mid + 1, end);
            set.addAll(tree[2 * i + 1].set);
            set.addAll(tree[2 * i + 2].set);
            tree[i] = new node(set);
        }
        char overlap(int start, int end, int qs, int qe) {
            if (qe < start || qs > end || start > end) {
                return 0;
            }
            if (qs <= start && qe >= end) {
                return 2;
            }
            return 1;
        }

        node merge(node n1 , node n2) {
            HashSet<Integer> set = new HashSet<>();
            set.addAll(n1.set);
            set.addAll(n2.set);
            return new node(set);
        }

        int dQuery(int start, int end) {
            return maxQueryHelper(0, 0, n - 1, start, end).size;
        }
        node maxQueryHelper(int i, int start, int end, int qs, int qe) {
            if (overlap(start, end, qs, qe) == 0) {
                return new node(new HashSet<>());
            }
            if (overlap(start, end, qs, qe) == 1) {
                int mid = (start + end) / 2;
                return merge(maxQueryHelper(2 * i + 1, start, mid, qs, qe) , maxQueryHelper(2 * i + 2, mid + 1, end, qs, qe));
            }
            return tree[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder("");
        int n = Integer.parseInt(bf.readLine());
        int a[] = new int[n];
        StringTokenizer s = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s.nextToken());
        }
        //SegmentTree st = new SegmentTree(a , n);
        //st.printTree();
        int q = Integer.parseInt(bf.readLine());
        while(q-- != 0) {
            s = new StringTokenizer(bf.readLine());
            //str.append(st.dQuery(Integer.parseInt(s.nextToken()) - 1, Integer.parseInt(s.nextToken()) - 1)).append("\n");
        }
        System.out.println(str.toString());
    }

}
