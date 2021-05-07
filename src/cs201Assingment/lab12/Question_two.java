package cs201Assingment.lab12;

import java.io.*;
import java.util.*;

public class Question_two {
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

    static class Edge {
        int v , w;
        Edge(int v , int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "v - " + v + " w - " + w;
        }
    }

    static FastReader f = new FastReader();
    static ArrayList<ArrayList<Edge>> adj;
    static int n , m;

    public static void main(String[] args) throws IOException {
        n = f.nextInt();
        m = f.nextInt();
        adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0 ; i < m ; i++) {
            int u = f.nextInt() -1  , v = f.nextInt() - 1 , w = f.nextInt();
            adj.get(u).add(new Edge(v , w));
        }
        //System.out.println(adj);
        int s = f.nextInt() - 1;
        ArrayList<Integer> sorted = topologicalSort();
        //System.out.println(sorted);
        long[] dist = new long[n];
        for(int i = 0 ; i < n ; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        for(int i = 0 ; i < n ; i++) {
            int cur = sorted.get(i);
            //System.out.println("cur " + cur);
            for(Edge e : adj.get(cur)) {
                dist[e.v] = Math.min(dist[e.v] , dist[cur] + e.w);
            }
            //System.out.println(Arrays.toString(dist));
        }
        System.out.println(Arrays.toString(dist));
    }

    static ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> a = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                dfs(i , a , visited);
            }
        }
        Collections.reverse(a);

        return a;
    }

    private static void dfs(int i, ArrayList<Integer> a , boolean[] visited) {
        visited[i] = true;
        for(Edge x : adj.get(i)) {
            if(!visited[x.v]) {
                dfs(x.v , a ,visited);
            }
        }
        a.add(i);
    }
}
/*

7 7
1 2 2
1 3 3
2 4 1
4 6 2
3 5 3
5 7 1
6 7 6
1


 */