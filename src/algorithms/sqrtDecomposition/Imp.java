package algorithms.sqrtDecomposition;

import java.io.*;
import java.util.*;

public class Imp {
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


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static FastReader f = new FastReader();
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int m = (int) 1e9 + 7;
    static int MAX = 500005;

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }


    static long moduloInversePrime(long a) {
        long ans = modPow(a, m - 2);
        //System.out.println("modulo inverse of " + a + " -> " + ans);
        return ans;
    }

    static long mult(long a, long b) {
        return (a * (long) b % m);
    }

    static long modPow(long a, int step) {
        long ans = 1;
        while (step != 0) {
            if ((step & 1) != 0)
                ans = mult(ans, a);
            a = mult(a, a);
            step >>= 1;
        }
        return ans;
    }

    static long longModulus(long x, long m) {
        long d = x / m;
        return x - d * m;
    }

    static int[] inputArray(int n) throws IOException {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = f.nextInt();
        }
        return a;
    }

    /*

    i th entry  represent i*root(n)...(i+1)*root - 1

     */
    static int n;
    static long[] b;
    static int[] a;
    static int len;

    static void init() {
        b = new long[len];
        for(int i = 0 ; i < n ; i++) {
            b[i/len] += a[i];
        }
    }

    static long query(int l , int r) {
        int b1 = l / len + 1 , b2 = r / len - 1;
        long lefSum = 0 , rightSum = 0 , blockSum = 0;
        for(int i = l ; i <= l + (len - l % len) - 1 && i <= r ; i++) {
            lefSum += a[i];
        }
        if(b1 != b2 + 2)
        for(int i = r ; i >= r - r % len ; i--) {
            rightSum += a[i];
        }
        for(int i = b1 ; i <= b2 ; i++) {
            blockSum += b[i];
        }
        return lefSum + blockSum + rightSum;
    }

    public static void main(String[] args) throws IOException {
        n = f.nextInt();
        len = (int) Math.sqrt(n + 0.1) + 1;
        a = inputArray(n);
        init();
        System.out.println(Arrays.toString(b));
        int q = f.nextInt();
        while(q-- != 0) {
            int l = f.nextInt() , r = f.nextInt();
            System.out.println(query(l - 1 , r - 1));
        }
    }
}

/*
6
1 2 3 4 5 6
5
3 6
3 5
1 4
4 5
5 6
 */
