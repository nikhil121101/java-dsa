package algorithms.dp.digit_dp;

import java.io.*;
import java.util.*;

public class Investigation_OJ {

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

        float nextFloat() {
            return Float.parseFloat(nextToken());
        }
    }


    static FastReader f = new FastReader();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");

    public static void main(String[] args) {
        System.out.println(root(5 , 3));
    }


    private static double root(int x, int n) {
        double l = 1 , r = x , mid = 0;
        while(l <= r) {
            mid = (l + r) / 2;
            double prod = 1;
            for(int i = 0 ; i < n ; i++) {
                prod *= mid;
            }
            int cmp = Double.compare(prod , x);
            if(cmp > 0) {
                r = mid - 0.00000000000001;
            }
            else  {
                l = mid + 0.00000000000001;
            }
        }
        return mid;
    }

}
