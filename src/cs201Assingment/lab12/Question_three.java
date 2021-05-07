package cs201Assingment.lab12;


import java.io.*;
import java.util.*;

public class Question_three {

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

    static class Node implements Comparable<Node> {
        int v , weight;
        Node() {

        }
        Node(int v , int weight) {
            this.v = v;
            this.weight = weight;
        }
        public boolean equals(Object o) {
            if(o == this) {
                return true;
            }
            if(!(o instanceof Node)) {
                return false;
            }
            Node n = (Node)o;
            return n.v == v;
        }

        @Override
        public String toString() {
            return "(" + v + "," + weight + ")";
        }

        @Override
        public int compareTo(Node o) {
            return o.v == v ? 0 : this.weight - o.weight;
        }
    }

    static ArrayList<ArrayList<Node>> adj;
    static int n , m;

    public static void main(String[] args) throws IOException {
        n = f.nextInt();
        m = f.nextInt();
        adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0 ; i < m ; i++) {
            int u = f.nextInt() , v = f.nextInt() , w = f.nextInt();
            adj.get(u).add(new Node(v , w));
            adj.get(v).add(new Node(u , w));
        }
        int s = f.nextInt();
        System.out.println(Arrays.toString(dijkstra(s)));
    }

    static long[] dijkstra(int s) {
        long[] dist = new long[n];
        Arrays.fill(dist , Integer.MAX_VALUE);
        HashSet<Integer> done = new HashSet<>();
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s , 0));
        while(!(done.size() == n)) {
            int node = pq.remove().v;
            if(done.contains(node)) {
                continue;
            }
            done.add(node);
            for(Node n : adj.get(node))  {
                if(!done.contains(n.v)) {
                    if(dist[n.v] > dist[node] + n.weight) {
                        dist[n.v] = dist[node] + n.weight;
                        pq.add(new Node(n.v , (int) dist[n.v]));
                    }
                }
            }
        }
        return dist;
    }
}
/*

9 14
0 1 4
0 7 8
1 7 11
1 2 8
7 8 7
7 6 1
2 8 2
8 6 6
2 3 7
2 5 4
6 5 2
3 5 14
3 4 9
5 4 10
0
 */