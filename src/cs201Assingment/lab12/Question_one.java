package cs201Assingment.lab12;

import java.io.*;
import java.util.*;

public class Question_one {

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

    public static void main(String[] args) throws IOException {
    }

    private static long[] belmonFord(int n, int m, int[][] edges, int s) {
        long[] dist = new long[n];
        for(int i = 0 ; i < n ; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        for(int i =0 ; i < n ; i++) {
            for(int j = 0 ; j < edges.length ; j++) {
                if(dist[edges[j][0]] + edges[j][2] < dist[edges[j][1]]) {
                    dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                }
            }
        }
        return dist;
    }
}
/*
5 8
1 2 -1
1 3 4
2 3 3
2 4 2
2 5 2
4 3 5
4 2 1
5 4 -3
1
 */