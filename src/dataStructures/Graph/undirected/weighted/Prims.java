package dataStructures.Graph.undirected.weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prims {
    static int V;
    static int graph[][];
    static int minKey(int key[], Boolean mstSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }
    static int primMST(int graph[][])
    {
        int res = 0;
        int parent[] = new int[V];

        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V ; count++) {
            int u = minKey(key, mstSet);
            if(parent[u] != -1)
            res += graph[u][parent[u]];
            mstSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        V = n;
        int d = Integer.parseInt(st.nextToken());
        int a[][] = new int[n][d];
        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < d ; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        graph = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(i != j) {
                    int weight = 0;
                    for(int k = 0  ; k < d ; k++) {
                        weight += Math.abs(a[i][k] - a[j][k]);
                    }
                    graph[i][j] = -weight;
                }
            }
        }
        //System.out.println(Arrays.deepToString(graph));
        System.out.println(-primMST(graph));
    }
}
