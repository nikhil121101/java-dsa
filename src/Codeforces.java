import java.io.*;
import java.util.*;

public class Codeforces {

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
    private static int m = (int) 1e9 + 7;
    static int MAX = 500005;
    static long[] fact;

    static int[] inputArray(int n) throws IOException {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = f.nextInt();
        }
        return a;
    }

    private static class SegmentTree {
        int[] array;
        int[] tree;
        int n;
        int max;

        SegmentTree(int a[], int n) {
            this.tree = new int[4 * n + 1];
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
                tree[i] = array[start];
                return;
            }
            int mid = (start + end) / 2;
            buildTreeHelper(2 * i + 1, start, mid);
            buildTreeHelper(2 * i + 2, mid + 1, end);
            tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
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

        int query(int start, int end) {
            return andQueryHelper(0, 0, n - 1, start, end);
        }

        int andQueryHelper(int i, int start, int end, int qs, int qe) {
            if (overlap(start, end, qs, qe) == 0) {
                return 0;
            }
            if (overlap(start, end, qs, qe) == 1) {
                int mid = (start + end) / 2;
                return Math.max(andQueryHelper(2 * i + 1, start, mid, qs, qe),
                        andQueryHelper(2 * i + 2, mid + 1, end, qs, qe));
            } else {
                return tree[i];
            }
        }
    }

    static int query(int l, int r) {
        System.out.println("? " + l + " " + r);
        System.out.flush();
        int res = f.nextInt();
        System.out.flush();
        return res;
    }

    public static void main(String[] args) throws IOException {

        int t = f.nextInt();
        while(t-- != 0) {
            int n = f.nextInt();
            int a[] = inputArray(n);
            Arrays.sort(a);
            TreeMap<Integer, Integer> frq = new TreeMap<>();
            for(int x : a) {
                if(frq.containsKey(x)) {
                    frq.replace(x, frq.get(x) + 1);
                }
                else {
                    frq.put(x, 1);
                }
            }

            int s = 0;
            ArrayList<Integer> list = new ArrayList<>();
            for(Map.Entry<Integer, Integer> en : frq.entrySet()) {
                s += en.getValue();
                list.add(en.getValue());
            }

            Collections.sort(list);

            if(list.get(list.size()-1) > s - list.get(list.size()-1)) {
                System.out.println(list.get(list.size()-1) - (s-list.get(list.size()-1)));
            }
            else {
                int diff = (s - list.get(list.size()-1)) - list.get(list.size()-1);
                if((diff&1) == 1) {
                    System.out.println(1);
                }
                else {
                    System.out.println(0);
                }
            }
        }

    }

}

/*
5
2 1
1 1
500 4
217871987498122 10
100000000000000001 1
 */


