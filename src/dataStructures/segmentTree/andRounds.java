package dataStructures.segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class andRounds {
    private static class SegmentTree {
        int[] array;
        int[] tree;
        int n;
        int max;
        SegmentTree(int a[] , int n , int max) {
            this.tree = new int[4*n + 1];
            this.array = a;
            this.n = n;
            this.max = max;
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
            tree[i] = tree[2 * i + 1] & tree[2 * i + 2];
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

        int andQuery(int start , int end) {
            return andQueryHelper(0 , 0 , n-1 , start , end);
        }

        int andQueryHelper(int i , int start , int end , int qs , int qe) {
            if(overlap(start , end , qs , qe) == 0) {
                return max;
            }
            if(overlap(start , end , qs , qe) == 1) {
                int mid = (start + end) / 2;
                return andQueryHelper(2 * i + 1 , start , mid , qs , qe) &
                        andQueryHelper(2 * i + 2 , mid + 1 , end , qs , qe);
            }
            else {
                return tree[i];
            }
        }
    }
    public static void main (String[] args) throws java.lang.Exception {
        int max = 0;
        int pow = 1;
        while(max < (int)Math.pow(10 , 9)) {
            max = max + pow;
            pow *= 2;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder("");
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            StringTokenizer s = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(s.nextToken());
            int q = Integer.parseInt(s.nextToken());
            int[] a = new int[n];
            s = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(s.nextToken());
            }
            Code(a , n , q , str , max);
            if(!(t == 0)) {
                str.append("\n");
            }
        }
        System.out.print(str.toString());
    }
    static void Code(int[] a , int n , int q , StringBuilder str , int max) {
        SegmentTree st = new SegmentTree(a , n , max);
        //System.out.println(Arrays.toString(st.tree));
        q = Math.min(q , n/2);
        //System.out.println("q -> "+ q);
        for(int i = 0 ; i < n ; i++) {
            int temp;
            if(i - q < 0) {
                //System.out.println(st.andQuery(0 , i-1) + " " + st.andQuery((i-q) % n , n-1));
                temp = st.andQuery(0 , i-1) & st.andQuery((i-q) % n , n-1);
            }
            else {
                //System.out.println(st.andQuery(i-q , i-1));
                temp = st.andQuery(i-q , i-1);
            }
                //System.out.println("temp -> " + temp);
            if(i + q >= n) {
                //System.out.println(st.andQuery(i , n-1) + " " + st.andQuery(0 , (i+q) % n));
                temp = temp & (st.andQuery(i , n-1) & st.andQuery(0 , (i+q) % n));
            }
            else {
                //System.out.println(st.andQuery(i , i+q));
                temp = temp & st.andQuery(i , i+q);
            }
                //System.out.println("temp -> " + temp);
            str.append(temp);
            if(!(i == n-1)) {
                str.append(" ");
            }
        }
    }
}

/*
2
4 1
27 26 28 31
4 2
27 26 28 31

2
3 1
1 2 3
5 100
1 11 111 1111 11111
*/