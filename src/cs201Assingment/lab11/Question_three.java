package cs201Assingment.lab11;

// CONNECTED COMPONENTS IN UNDIRECTED GRAPH

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

    static int[] parent , rank;

    static void initializeDSU() {
        parent = new int[n];
        for(int i = 0 ; i  < n ; i++) {
            parent[i] = -1;
        }
        rank = new int[n];
    }

    static int find(int u) {
        while(parent[u] != -1) {
            u = parent[u];
        }
        return u;
    }

    static void union(int u , int v) {
        int p1 = find(u);
        int p2 = find(v);
        if(p1 != p2) {
            if(rank[p1] < rank[p2]) {
                parent[p1] = p2;
            }
            else if(rank[p1] > rank[p2]){
                parent[p2] = p1;
            }
            else {
                parent[p1] = p2;
                rank[p2]++;
            }
        }
    }

    static Edge[] edges;
    static int n , m;

    static class Edge {
        int v , u;
        Edge(int v , int u) {
            this.v = v;
            this.u = u;
        }
    }

    public static void main(String[] args) throws IOException {

        /*

        input format
        |V| |E|
        e1
        e2
        |
        |
        |

        em

        sample input

        11 8
        1 2
        2 3
        3 1
        2 4
        5 6
        6 8
        5 7
        9 10

        sample output

        Connected components :-
        0 1 2 3
        4 5 6 7
        8 9
        10


         */


        System.out.println("Enter |V| |E|");
        n = f.nextInt();
        int m = f.nextInt();
        edges = new Edge[n];
        System.out.println("Enter all edges one by one");
        for(int i =0 ; i < m ; i++) {
            int u = f.nextInt() - 1 , v = f.nextInt() - 1;
            edges[i] = new Edge(u , v);
        }
        initializeDSU();
        for(int i =0 ; i < m ; i++) {
            union(edges[i].u , edges[i].v);
        }
        HashMap<Integer , ArrayList<Integer>> map = new HashMap<>();
        for(int i =0 ; i < n ; i++) {
            if(parent[i] == -1) {
                map.put(i , new ArrayList<>());
            }
        }
        for(int i =0 ; i < n ; i++) {
            if(parent[i] != -1) {
                map.get(parent[i]).add(i);
            }
        }
        System.out.println("Connected components :-");
        for(Map.Entry<Integer , ArrayList<Integer>> e : map.entrySet()) {
            System.out.print(e.getKey() + " ");
            for(int x : e.getValue()) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}