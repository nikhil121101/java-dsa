package dataStructures.Graph.undirected.weighted;

import java.io.*;
import java.util.*;

public class
Dijkstra {
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
    private static final int mod = (int) 1e9 + 7;
    static int MAX = 500005;

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }


    static long moduloInversePrime(long a) {
        long ans = modPow(a, mod - 2);
        //System.out.println("modulo inverse of " + a + " -> " + ans);
        return ans;
    }

    static long mult(long a, long b) {
        return (a * (long) b % mod);
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
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

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
    static int n , m , k;

    static long res = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        n = f.nextInt();
        m = f.nextInt();
        k = f.nextInt();
        adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0 ; i < m ; i++) {
            int u = f.nextInt() - 1 , v = f.nextInt() - 1 , w = f.nextInt();
            adj.get(u).add(new Node(v , w));
            adj.get(v).add(new Node(u , w));
        }
        printAllPaths(0 , n-1);
        if(res == Long.MAX_VALUE) {
            System.out.println(0);
        }
        else {
            System.out.println(res);
        }
    }

    public static void printAllPaths(int s, int d)
    {
        boolean[] isVisited = new boolean[n];

        printAllPathsUtil(s, d, isVisited, 0 , 0);
    }

    private static void printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   long pathLen , int maxEdge)
    {

        if (u.equals(d)) {
            //System.out.println("u - " + u + " pathLen - " + pathLen + " maxEdge - " + maxEdge);

            res = Math.min(res , (pathLen - maxEdge) + (maxEdge/k));
            return;
        }

        isVisited[u] = true;

        for (Node i : adj.get(u)) {
            if (!isVisited[i.v] && i.v != u) {

                pathLen += i.weight;
                printAllPathsUtil(i.v, d, isVisited, pathLen , Math.max(maxEdge , i.weight));

            }
        }

        isVisited[u] = false;
    }

    static long[] dijkstra(int s) {
        long[] dist = new long[n];
        Arrays.fill(dist , Integer.MAX_VALUE);
        boolean[] done = new boolean[n];
        dist[s] = 0;
        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(done));
        for(int i =0 ; i < n; i++) {
            int node = minDistance(dist , done);
            System.out.println((char)(node + 'a'));
            done[node] = true;
            for(Node n : adj.get(node))  {
                if(!done[n.v]) {
                    if(dist[n.v] > dist[node] + n.weight) {
                        dist[n.v] = dist[node] + n.weight;
                    }
                }
            }
            System.out.println(Arrays.toString(dist));
            System.out.println(Arrays.toString(done));
        }
        return dist;
    }

    static int minDistance(long[] distance , boolean done[]) {
        long min = Integer.MAX_VALUE;
        int res = 0;
        for(int i =0 ; i < n ; i++) {
            if(!done[i]) {
                if(distance[i] < min) {
                    min = distance[i];
                    res = i;
                }
            }
        }
        return res;
    }
}
/*

8 15
a d 4
d f 3
a f 2
d c 9
a b 5
b f 3
b g 1
f g 6
b c 5
a h 4
g h 5
g e 7
c h 2
h e 4
c e 7


 */