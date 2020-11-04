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
        int n = f.nextInt() , x = f.nextInt();
        long a[] = new long[n];
        for(int i = 0; i < n ; i++) {
            a[i] = f.nextLong();
        }
        System.out.println(Code(a , n , x));
    }

    static long[] maxSumSubArray(long a[] , int n) {
        long[] res = new long[3];
        res[1] = -1;
        res[2] = 0;
        int start = 0;
        long s = 0;
        for(int i = 0 ; i < n ; i++) {
            s += a[i];
            if(s < 0) {
                start = i + 1;
                s = 0;
            }
            if(s > res[2]) {
                res[0] = start;
                res[1] = i;
                res[2] = s;
            }
        }
        return res;
    }

    static long[] minSumSubArray(long a[] , int n) {
        long[] res = new long[3];
        res[1] = -1;
        int start = 0;
        long s = 0;
        for(int i = 0 ; i < n ; i++) {
            s += a[i];
            if(s > 0) {
                start = i + 1;
                s = 0;
            }
            if(s < res[2]) {
                res[0] = start;
                res[1] = i;
                res[2] = s;
            }
        }
        return res;
    }

    private static long Code(long a[] , int n , int x) {
        if(x <= 0) {
            long[] min = minSumSubArray(a , n);
            for(int i = (int) min[0]; i <= min[1] ; i++) {
                a[i] *= x;
            }
        }
        else {
            long[] max = maxSumSubArray(a , n);
            for(int i = (int) max[0]; i <= max[1] ; i++) {
                a[i] *= x;
            }
        }
        return maxSumSubArray(a , n)[2];
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
