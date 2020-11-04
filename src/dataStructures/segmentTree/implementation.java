package dataStructures.segmentTree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class implementation {

    static class SegmentTree {
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
            //System.out.println(Arrays.toString(tree));
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
            tree[i] = Math.min(tree[2 * i + 1] , tree[2 * i + 2]);
        }

        char overlap(int start , int end , int index) {
            if(index < start || index > end) {
                return 0;
            }
            return 1;
        }

        void update(int index , int value) {
            updateHelper(0 , 0 , n-1 , index , value);
            //System.out.println(Arrays.toString(tree));
        }

        void updateHelper(int i , int start , int end , int index , int value) {
            //System.out.println("start - " + start + " end - " + end);
            if(overlap(start , end , index) == 0) {
                //System.out.println("no overlap");
                return;
            }
            if(start == end && end == index) {
                //System.out.println("index reached");
                tree[i] = value;
                return;
            }
            int mid = (start + end) / 2;
            updateHelper(2 * i + 1 , start , mid , index , value);
            updateHelper(2 * i + 2 , mid + 1 , end , index , value);
            tree[i] = Math.min(tree[2 * i + 1] , tree[2 * i + 2]);
        }
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(nextToken());
        }

        long nextLong() {
            return Long.parseLong(nextToken());
        }

        double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }

    static FastReader f = new FastReader();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int m = (int)1e9 + 7;
    static int MAX = 500005;
    static long[] fact;

    static int[] inputArray(int n) throws IOException {
        int[] a = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = f.nextInt();
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        int n = f.nextInt();
        int a[] = inputArray(n);
        SegmentTree st = new SegmentTree(a , n);
        System.out.println(Arrays.toString(st.tree));

    }
}
/*
5
2 4 5 6 7
 */