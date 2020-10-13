package dataStructures.Graph.undirected.weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class spojCost {
    static int mod = (int)1e9;
    static class Edge implements Comparator<Edge> {
        int v , u , weight;
        Edge() {

        }
        Edge(int v , int u , int weight) {
            this.v = v;
            this.u = u;
            this.weight = weight;
        }
        public int compare (Edge e1 , Edge e2) {
            return e2.weight - e1.weight;
        }
    }
    static int n , m;
    static Edge[] edges;
    static int[] size;
    static int[] parent , rank;

    static int find(int e) {
        if(parent[e] == e) {
            return e;
        }
        int p = find(parent[e]);
        parent[e] = p;
        return p;
    }

    static int union(int u , int v) {
        int p1 = find(u);
        int p2 = find(v);
        if(rank[p1] > rank[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
            return p1;
        }
        else if(rank[p1] < rank[p2]) {
            parent[p1] = p2;
            size[p2] += size[p1];
            return p2;
        }
        else {
            parent[p1] = p2;
            size[p2] += size[p1];
            rank[p2]++;
            return p2;
        }
    }

    static void initializeDSU() {
        parent = new int[n];
        for(int i = 0 ; i < n ; i++) parent[i] = i;
        size = new int[n];
        Arrays.fill(size , 1);
        rank = new int[n];
    }

    static long nC2(int n) {
        if(n < 2) {
            return 0;
        }
        return (long)n * (n-1) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new Edge[m];
        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()) - 1 ,
                    Integer.parseInt(st.nextToken()) - 1 , Integer.parseInt(st.nextToken()));
        }
        if(m == 0) {
            System.out.println(0);
            System.exit(0);
        }
        Arrays.sort(edges , new Edge());
        initializeDSU();
        long res = 0;
        long cur = 0;
        for(int i = 0 ; i < m ; i++) {
            int p1 = find(edges[i].u);
            int p2 = find(edges[i].v);
            if(p1 != p2) {
                cur -= nC2(size[p1]);
                cur -= nC2(size[p2]);
                int p = union(p1 , p2);
                cur = (cur + nC2(size[p])) % mod;
            }
            res = (res +  cur * edges[i].weight % mod) % mod;
        }
        System.out.println(res);
    }

}
