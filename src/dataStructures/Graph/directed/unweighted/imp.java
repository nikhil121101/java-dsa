package dataStructures.Graph.directed.unweighted;

import java.io.IOException;
import java.util.*;

public class imp {

    static int n;
    static ArrayList<ArrayList<Integer>> adjList;

    static void init(int n) {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }
    }

    static void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    static void printGraph() {
        System.out.println("graph veteces - >");
        for (int i = 0; i < n; i++) {
            for (int x : adjList.get(i)) {
                System.out.print((i+1) + "-" + (x+1) + " , ");
            }
        }
        System.out.println();
    }

    static void bfs(int s , int[] a , int at) {
        boolean[] visited = new boolean[n];
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

    static Set<Set<Integer>> stronglyConnectedComp() {
        Set<Set<Integer>> res = new HashSet<>();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i , visited , stack);
            }
        }
        Arrays.fill(visited , false);
        ArrayList<ArrayList<Integer>> transposeList = transpose();
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                HashSet<Integer> set = new HashSet<>();
                dfs2(i , visited , set);
                res.add(set);
            }
        }
        return res;
    }

    private static ArrayList<ArrayList<Integer>> transpose() {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0 ; j < adjList.get(i).size() ; j++) {
                res.get(adjList.get(i).get(j)).add(i);
            }
        }
        return res;
    }

    static void dfs(int s , boolean[] visited , Stack<Integer> stack) {
        visited[s] = true;
        for(int x : adjList.get(s)) {
            if(!visited[x])
                dfs(x , visited , stack);
        }
        stack.push(s);
    }
    static void dfs2(int s , boolean[] visited , Set<Integer> set) {
        visited[s] = true;
        set.add(s);
        for(int x : adjList.get(s)) {
            if(!visited[x])
                dfs2(x , visited , set);
        }
    }

    public static void main(String[] args) throws IOException {

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