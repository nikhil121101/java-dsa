package algorithms.string_algorithms;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KMP {

    static class FastReader {
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

    static int[] prefixFunction(String s) {
        int n = s.length();
        int pie[] = new int[n];
        for(int i = 1 ; i < n ; i++) {
            int j = pie[i-1];
            while(j >= 0) {
                if(s.charAt(j) == s.charAt(i)) {
                    pie[i] = j + 1;
                    break;
                }
                if(j == 0) {
                    break;
                }
                j = pie[j-1];
            }
        }
        return pie;
    }



    public static void main(String[] args) {
        int test = f.nextInt();
        for(int t = 0 ; t < test ; t++) {
            String s = f.nextToken();
            int[] pie = prefixFunction(s);
            int cnt = 0;
            int i = pie[s.length()-1];
            while(i > 0) {
                cnt++;
                i = pie[i-1];
            }
            System.out.println("Case " + t + ": " + cnt);
        }
    }

}
