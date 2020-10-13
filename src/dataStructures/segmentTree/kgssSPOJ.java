package dataStructures.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

public class kgssSPOJ {
    private static class SegmentTree {
        int minV = -1;

        private class node {
            int first;
            int second;

            node() {
            }

            node(int first, int second) {
                this.first = first;
                this.second = second;
            }

            public void merge(node n1, node n2) {
                this.first = Math.max(n1.first , n2.first);
                this.second = Math.max(Math.min(n1.first , n2.first) , Math.max(n2.second , n1.second));
            }
        }

        int[] array;
        node[] tree;
        int n;

        SegmentTree(int a[], int n) {
            this.tree = new node[4*n + 1];
            for (int i = 0 ; i < 4*n+1 ; i++) {
                tree[i] = new node();
            }
            this.array = a;
            this.n = n;
            buildTree();
        }

        void buildTree() {
            buildTreeHelper(0, 0, n - 1);
        }

        void printTree() {
            for(int i = 0 ; i < 4*n + 1 ; i++) {
                System.out.print("(" + tree[i].first + " " + tree[i].second + ") ");
            }
            System.out.println();
        }

        void buildTreeHelper(int i, int start, int end) {
            if (start > end) {
                return;
            }
            if (start == end) {
                tree[i] = new node(array[start], -1);
                return;
            }
            int mid = (start + end) / 2;
            buildTreeHelper(2 * i + 1, start, mid);
            buildTreeHelper(2 * i + 2, mid + 1, end);
            tree[i].merge(tree[2 * i + 1], tree[2 * i + 2]);
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

        int maxQuery(int start, int end) {
            node temp = maxQueryHelper(0, 0, n - 1, start, end);
            return temp.first + temp.second;
        }
        node maxQueryHelper(int i, int start, int end, int qs, int qe) {
            if (overlap(start, end, qs, qe) == 0) {
                return new node(-1 , -1);
            }
            if (overlap(start, end, qs, qe) == 1) {
                int mid = (start + end) / 2;
                node n = new node();
                n.merge(maxQueryHelper(2 * i + 1, start, mid, qs, qe), maxQueryHelper(2 * i + 2, mid + 1, end, qs, qe));
                return n;
            }
            return tree[i];
        }
        void update(int ind, int t) { updateHelper(0, 0, n - 1, ind, t); }
        node updateHelper(int i, int start, int end, int ind, int t) {
            if (overlap(start, end, ind, ind) == 2) {
                tree[i].first = t;
            } else if(overlap(start, end, ind, ind) == 1){
                int mid = (start + end) / 2;
                tree[i].merge(updateHelper(2 * i + 1, start, mid, ind, t), updateHelper(2 * i + 2, mid + 1, end, ind, t));
            }
            return tree[i];
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder("");
        int n = Integer.parseInt(bf.readLine());
        int a[] = new int[n];
        StringTokenizer s = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s.nextToken());
        }
        SegmentTree st = new SegmentTree(a , n);
        //st.printTree();
        int q = Integer.parseInt(bf.readLine());
        while(q-- != 0) {
            s = new StringTokenizer(bf.readLine());
            if(s.nextToken().equals("Q")) {
                str.append(st.maxQuery(Integer.parseInt(s.nextToken()) - 1, Integer.parseInt(s.nextToken()) - 1)).append("\n");
            }
            else {
                st.update(Integer.parseInt(s.nextToken()) - 1 , Integer.parseInt(s.nextToken()));
                //st.printTree();
            }
        }
        System.out.println(str.toString());
    }
}
/*
20
12 23 0 0 9 9 9 0 8 0 8 86 8 87 8 9 70 9 1 4
10
Q 1 13
Q 9 15
U 10 100
12 23 0 0 9 9 9 0 8 100 8 86 8 87 8 9 70 9 1 4
U 1 0
0 23 0 0 9 9 9 0 8 100 8 86 8 87 8 9 70 9 1 4
U 6 50
0 23 0 0 9 50 9 0 8 100 8 86 8 87 8 9 70 9 1 4
Q 9 10

Q 13 19
Q 19 20
Q 3 14
Q 9 11
 */