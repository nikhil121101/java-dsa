import java.io.*;
import java.util.*;

public class Main {

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

    static int gcd(int a , int b) {
        if(a % b == 0) {
            return b;
        }
        return gcd(b , a % b);
    }

    static long mult(long a, long b)
    {
        return (a * (long)b % m);
    }

    static long modPow(long a, int step)
    {
        long ans = 1;
        while(step != 0)
        {
            if((step & 1) != 0)
                ans = mult(ans , a);
            a = mult(a , a);
            step >>= 1;
        }
        return ans;
    }

    static long longModulus(long x , long m) {
        long d = x / m;
        return x - d * m;
    }

    public static void main(String[] args) throws IOException {
        int n = f.nextInt();
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for(int i =0 ; i < n ; i++) {
            tree.add(new ArrayList<Integer>());
        }
        for(int i = 1 ; i < n ; i++) {
            int pi = f.nextInt() - 1;
            tree.get(pi).add(i);
        }
        //System.out.println(tree);
        int[] pop = new int[n];
        for(int i =0 ; i < n ; i++) {
            pop[i] = f.nextInt();
        }
        long[] size = new long[n];
        int[] leaves = new int[n];
        int[] maxLeaf = new int[n];
        calcSize(tree , n , pop , size , leaves , maxLeaf , 0);
//        System.out.println(Arrays.toString(size));
//        System.out.println(Arrays.toString(leaves));
//        System.out.println(Arrays.toString(maxLeaf));
        System.out.println(attack(tree , n , size , leaves , maxLeaf , pop , 0 , 0));
    }


    private static long attack(ArrayList<ArrayList<Integer>> tree, int n, long[] size,
                               int[] leaves , int[] maxLeaf , int[] pop, int i , int extra) {
        //System.out.println("i - " + i + " extra - " + extra);
        if(tree.get(i).isEmpty()) {
            return extra + pop[i];
        }
        long max = Integer.MIN_VALUE;
        int maxInd = 0;
        for(int x : tree.get(i)) {
            if(size[x] > max) {
                max = maxLeaf[x];
                maxInd = x;
            }
        }
        //System.out.println("max - " + max + " ind = " + maxInd);
        long emptySpace = max * leaves[i] - size[i];
        //System.out.println("emptySpace - " + emptySpace);
        if(emptySpace < pop[i]) {
            extra += Math.ceil((pop[i] - emptySpace) / (double)leaves[i]) ;
        }
        return attack(tree , n , size , leaves , maxLeaf , pop , maxInd , extra);
    }

    private static void calcSize(ArrayList<ArrayList<Integer>> tree,
                                 int n, int[] pop , long[] size ,
                                 int[] leaves , int[] maxLeaf, int src) {

        if(tree.get(src).isEmpty()) {
            size[src] = pop[src];
            leaves[src] = 1;
            maxLeaf[src] = pop[src];
        }
        for(int x : tree.get(src)) {
            calcSize(tree , n , pop , size , leaves , maxLeaf , x);
            size[src] += size[x];
            leaves[src] += leaves[x];
            maxLeaf[src] = Math.max(maxLeaf[src] , maxLeaf[x]);
        }
    }




}
/*

2 3 3
1 1 1
3 2 0
2 1 1 2 2
0 2 1 4 5
5 1 1 5 5
*/