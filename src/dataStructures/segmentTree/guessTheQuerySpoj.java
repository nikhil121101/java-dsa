package dataStructures.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class guessTheQuerySpoj {
    private static class SegmentTree {
        int minV = (int)-1e9;
        private class node {
            long sum;
            long ps;
            long ss;
            long bs;
            node() {
            }
            node(long sum, long ps, long ss, long bs) {
                this.sum = sum;
                this.ps = ps;
                this.ss = ss;
                this.bs = bs;
            }
        }
        int[] array;
        node[] tree;
        int n;
        SegmentTree(int a[], int n) {
            this.tree = new node[5 * n + 1];
            for (int i = 0 ; i < 5*n + 1 ; i++) {
                tree[i] = new node();
            }
            this.array = a;
            this.n = n;
            buildTree();
        }
        void buildTree() {
            buildTreeHelper(0, 0, n - 1);
        }

        void buildTreeHelper(int i, int start, int end) {
            if (start > end) {
                return;
            }
            if (start == end) {
                tree[i] = new node(array[start], array[start], array[start] ,array[start]);
                return;
            }
            int mid = (start + end) / 2;
            buildTreeHelper(2 * i + 1, start, mid);
            buildTreeHelper(2 * i + 2, mid + 1, end);
            tree[i] = rand(tree[2 * i + 1], tree[2 * i + 2]);
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

        long maxQuery(int start, int end) {
            return maxQueryHelper(0, 0, n - 1, start, end).bs;
        }
        node maxQueryHelper(int i, int start, int end, int qs, int qe) {
            if (overlap(start, end, qs, qe) == 0) {
                return null;
            }
            if (overlap(start, end, qs, qe) == 1) {
                int mid = (start + end) / 2;
                return rand(maxQueryHelper(2 * i + 1, start, mid, qs, qe) , maxQueryHelper(2 * i + 2, mid + 1, end, qs, qe));
            }
            return tree[i];
        }
        node rand(node l, node r) {
            node n = new node();
            if(r == null)
                return new node(l.sum , l.ps, l.ss, l.bs);
            if(l == null)
                return new node(r.sum , r.ps, r.ss, r.bs);;
            n.sum = l.sum + r.sum;
            n.ps = Math.max(l.ps, l.sum + r.ps);
            n.ss = Math.max(r.ss, l.ss + r.sum);
            n.bs = Math.max(l.ss + r.ps, Math.max(l.bs, r.bs));
            return n;
        }
        void update(int ind , int t) {
            updateHelper(0 , 0 , n-1 , ind , t);
        }
        void add(int i , int add) {
            tree[i].sum = add;
            tree[i].ps = add;
            tree[i].ss = add;
            tree[i].bs = add;
        }
        node updateHelper(int i , int start , int end , int ind , int target) {
            if(overlap(start , end , ind , ind) == 0) {
            }
            else if(overlap(start , end , ind , ind) == 2) {
                add(i , target);
            }
            else {
                int mid = (start + end) / 2;
                tree[i] = rand(updateHelper(2 * i + 1, start, mid, ind , target) , updateHelper(2 * i + 2, mid + 1, end, ind , target));
            }
            return tree[i];
        }
    }

    public  static void main(String args[]) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder("");
        int n = Integer.parseInt(b.readLine());
        int a[] = new int[n];
        StringTokenizer s = new StringTokenizer(b.readLine());
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(s.nextToken());
        }
            SegmentTree st = new SegmentTree(a , n);
            int q = Integer.parseInt(b.readLine());
            while(q-- != 0) {
                s = new StringTokenizer(b.readLine());;
                if(s.nextToken().equals("0")) {
                    int ind = Integer.parseInt(s.nextToken()) - 1;
                    int target = Integer.parseInt(s.nextToken());
                    st.update(ind , target);
                }
                else {
                    str.append(st.maxQuery(Integer.parseInt(s.nextToken()) - 1 , Integer.parseInt(s.nextToken()) - 1)).append("\n");
                }
            }
            System.out.println(str.toString());
    }
}
