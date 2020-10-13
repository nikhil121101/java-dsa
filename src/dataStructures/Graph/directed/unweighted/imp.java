package dataStructures.Graph.directed.unweighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class dirUnwtdGraph {
    int V;
    HashMap<Integer , ArrayList<Integer>> adjList;
    dirUnwtdGraph(int V) {
        this.V = V;
        adjList = new HashMap<Integer , ArrayList<Integer>>();
        for (int i = 0; i < V; i++) {
            adjList.put(i , new ArrayList<Integer>());
        }
    }
    void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }
    void printGraph() {
        System.out.println("graph veteces - >");
        for (int i = 0; i < V; i++) {
            for (int x : adjList.get(i)) {
                System.out.print((i+1) + "-" + (x+1) + " , ");
            }
        }
        System.out.println();
    }

    void bfs(int s , int[] a , int at) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()) {
            int curNode = queue.remove();
            a[at--] = curNode;
            for(int node : adjList.get(curNode)) {
                if(!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        }
        System.out.println();
    }

    void dfs(int s , int[] a , int[] at , boolean[] visted) {
        visted[s] = true;
        for(int x : adjList.get(s)) {
            if(!visted[x])
                dfs(x , a , at , visted);
        }
        a[at[0]] = s+1;
        at[0]--;
    }
}

public class imp {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        dirUnwtdGraph g = new dirUnwtdGraph(n);
        for(int i = 0 ; i < m ; i++) {
            s = bf.readLine().split(" ");
            int u = Integer.parseInt(s[0]) - 1;
            int v = Integer.parseInt(s[1]) - 1;
            g.addEdge(u , v);
        }
        g.printGraph();
        int[] a = new int[n];
        int[] at = new int[]{n-1};
        g.dfs(0 , a , at , new boolean[n]);
        System.out.println(Arrays.toString(a));
    }

}
/*
8 9
1 4
1 2
4 2
4 3
3 2
5 2
3 5
8 2
8 6
 */