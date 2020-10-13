package dataStructures.Graph.directed.unweighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class toplogical {

    private static class myGraph {
        int V;
        HashMap<Integer , ArrayList<Integer>> adjList;
        myGraph(int V) {
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

        int[] topologicalSort() {
            int[] result = new int[V];
            boolean[] visited = new boolean[V];
            int[] at = new int[]{V-1};
            for(int i = V-1 ; i >= 0 ; i--) {
                if(!visited[i]) {
                    dfs(i , result , at , visited);
                }
            }
            return result;
        }

        void dfs(int s , int[] a , int[] at , boolean[] visited) {
            visited[s] = true;
            for(int x = adjList.get(s).size() - 1 ; x >= 0 ; x--) {
                if(!visited[adjList.get(s).get(x)]) {
                    dfs(adjList.get(s).get(x) , a , at , visited);
                }
            }
            a[at[0]] = s+1;
            at[0]--;
        }

        boolean isCyclic() {
            int[] visited = new int[V];

            for(int i = 0 ; i < V; i++) {
                if(visited[i] == 0 && isCyclicDFS(i , visited)) {
                    return true;
                }
            }
            return false;
        }

        boolean isCyclicDFS(int s , int[] visted) {
            boolean res = false;
            visted[s] = 1;
            for(int x : adjList.get(s)) {
                if(visted[x] == 0) {
                    if(isCyclicDFS(x , visted)) {
                        res =  true;
                        break;
                    }
                }
                if(visted[x] == 1) {
                    res = true;
                    break;
                }
            }
            visted[s] = 2;
            return res;
        }

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

2 2
1 2
2 1
*/