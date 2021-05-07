package ai_assingment;

import java.io.IOException;
import java.util.*;

public class Q1 {

    static class Edge {
        int v , w;
        Edge(int v , int w) {
            this.v = v;
            this.w = w;
        }
    }

    static boolean dfs(int s , ArrayList<ArrayList<Edge>> adj , int depth , int maxDepth , boolean[] visited , int[] parent ,
                       int cost[] , char[] index , Stack<Integer> generated , ArrayList<Integer> expanded) {
        visited[s] = true;
        printDetails_DFS(expanded , generated , cost , index);
        expanded.add(generated.pop());
        if(s == 7) {
            //System.out.println("\nGoal node reached\n");
            return true;
        }
        System.out.println("Expanding : " + index[s]);

        if(depth == maxDepth) {
            //System.out.println("Maximum depth reached");
            return false;
        }

        ArrayList<Integer> child = new ArrayList<>();
        boolean res = false;
        for(Edge e : adj.get(s)) {
            if(!visited[e.v]) {
                System.out.println("Generating : " + index[e.v]);

                if(cost[e.v] != -1) {
                    if(cost[s] + e.w < cost[e.v]) {
                        cost[e.v] = cost[s] + e.w;
                        parent[e.v] = s;
                    }
                }
                else {
                    cost[e.v] = cost[s] + e.w;
                    child.add(e.v);
                    parent[e.v] = s;
                }
            }
        }

        for(int i = child.size() - 1 ; i >= 0 ; i--) {
            generated.push(child.get(i));
        }

        for(int i = 0 ; i < child.size() ; i++) {
            res = res | dfs(generated.peek() , adj , depth + 1 , maxDepth , visited , parent , cost , index , generated , expanded);
        }

        return res;
    }

    static void bfs(ArrayList<ArrayList<Edge>> adj , boolean[] visited , int[] parent ,
                    int cost[] , char[] index) {

        Arrays.fill(cost , -1);
        cost[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        ArrayList<Integer> expanded = new ArrayList<>();

        while(!queue.isEmpty()) {
            printDetails_BFS(expanded , queue , cost , index);
            int n = queue.size();
            for(int i = 0 ; i < n ; i++) {
                int p = queue.remove();
                expanded.add(p);
                System.out.println("Expanding : " + index[p]);
                for(Edge e : adj.get(p)) {
                    if(!visited[e.v]) {
                        visited[e.v] = true;
                        System.out.println("Generating : " + index[e.v]);
                        cost[e.v] = cost[p] + e.w;
                        parent[e.v] = p;
                        queue.add(e.v);
                    }
                }
            }
        }

    }

    static void UCS(int s , int n , ArrayList<ArrayList<Edge>> adj , char[] index , int[] cost , int[] parent) {
        Arrays.fill(cost , Integer.MAX_VALUE);
        boolean[] done = new boolean[n];
        ArrayList<Integer> expanded = new ArrayList<>();
        LinkedHashSet<Integer> generated = new LinkedHashSet<>();
        generated.add(s);
        cost[s] = 0;
        for(int i =0 ; i < n; i++) {
            printDetails_UCS(expanded , generated , cost , index);
            int node = minDistance(cost , done , n);
            System.out.println("Expanding : " + index[node]);
            generated.remove(node);
            expanded.add(node);
            done[node] = true;
            for(Edge e : adj.get(node))  {
                if(!done[e.v]) {
                    if(cost[e.v] == Integer.MAX_VALUE) {
                        System.out.println("Generating : " + index[e.v]);
                        generated.add(e.v);
                    }
                    if(cost[e.v] > cost[node] + e.w) {
                        parent[e.v] = node;
                        cost[e.v] = cost[node] + e.w;
                    }
                }
            }
        }
        printDetails_UCS(expanded , generated , cost , index);
    }

    static int minDistance(int[] distance , boolean done[] , int n) {
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

    private static void printDetails_UCS(ArrayList<Integer> expanded, LinkedHashSet<Integer> queue, int[] cost, char[] index) {
        System.out.println("Expanded Nodes : ");
        System.out.print("{ ");
        for(int x : expanded) {
            System.out.print("(" + index[x] + "," + cost[x] + ") , ");
        }
        System.out.println(" }");
        System.out.println("Generated Nodes : ");
        System.out.print("{ ");
        for(int x : queue) {
            System.out.print("(" + index[x] + "," + cost[x] + ") , ");
        }
        System.out.println(" }");
    }


    private static void printDetails_BFS(ArrayList<Integer> expanded, Queue<Integer> queue, int[] cost, char[] index) {
        System.out.println("Expanded Nodes : ");
        System.out.print("{ ");
        for(int x : expanded) {
            System.out.print("(" + index[x] + "," + cost[x] + ") , ");
        }
        System.out.println(" }");
        System.out.println("Generated Nodes : ");
        System.out.print("{ ");
        for(int x : queue) {
            System.out.print("(" + index[x] + "," + cost[x] + ") , ");
        }
        System.out.println(" }");
    }

    private static void printDetails_DFS(ArrayList<Integer> expanded, Stack<Integer> generated, int[] cost , char[] index) {
        System.out.println("Expanded Nodes : ");
        System.out.print("{ ");
        for(int x : expanded) {
            System.out.print("(" + index[x] + "," + cost[x] + ") , ");
        }
        System.out.println(" }");
        System.out.println("Generated Nodes : ");
        System.out.print("{ ");
        for(int x : generated) {
            System.out.print("(" + index[x] + "," + cost[x] + ") , ");
        }
        System.out.println(" }");
    }

    static void printPath(int[] cost , int[] parent , char index[]) {
        System.out.println();

        System.out.println("Final cost to reach G : " + cost[7]);
        System.out.println("\nPath to reach G : ");
        int p = 7;
        ArrayList<Integer> path = new ArrayList<>();
        while(p != -1) {
            path.add(p);
            p = parent[p];
        }
        Collections.reverse(path);
        for(int x : path) {
            System.out.print(index[x] + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        char[] index = {'S' , 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G'};
        int n = 8 , m = 13;

        int[][] edges =
                {

                        {0 , 1 , 1},
                        {0 , 4 , 10},
                        {1 , 2 , 20},
                        {1 , 5 , 15},
                        {2 , 3 , 4},
                        {2 , 5 , 1},
                        {3 , 1 , 2},
                        {3 , 6 , 2},
                        {3 , 7 , 2},
                        {4 , 3 , 1},
                        {4 , 6 , 5},
                        {5 , 7 , 3},
                        {6 , 7 , 3}

                };


        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for(int i = 0 ; i  < n ; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0 ;  i < m ; i++) {
            adj.get(edges[i][0]).add(new Edge(edges[i][1] , edges[i][2]));
        }

//        Stack<Integer> generated;
//
//        ArrayList<Integer> expanded;
        int[] parent = new  int[n];
        int[] cost = new  int[n];

//        for(int k = 0 ; k < 10 ; k++) {
//
//            System.out.println("\nk = " + k);
//            System.out.println();
//
//            generated = new Stack<>();
//            generated.push(0);
//
//            expanded = new ArrayList<>();
//            parent = new int[n];
//            cost = new int[n];
//            Arrays.fill(cost  , -1);
//            cost[0] = 0;
//            parent[0] = -1;
//            if(dfs(0 , adj , 0 , k , new boolean[n] , parent , cost , index , generated , expanded)) {
//                break;
//            }
//        }

        parent[0] = -1;
        //bfs(adj , new boolean[n] , parent , cost , index);

        UCS(0 , n , adj , index , cost , parent);

        printPath(cost , parent , index);

    }

}
/*


/*

{

{0 , 1 , 1},
{0 , 4 , 10},
{1 , 2 , 20},
{1 , 5 , 15},
{2 , 3 , 4},
{2 , 5 , 1},
{3 , 1 , 2},
{3 , 6 , 2},
{3 , 7 , 2},
{4 , 3 , 1},
{4 , 6 , 5},
{5 , 7 , 3},
{6 , 7 , 3}

}

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
a aa aaa aaaa aaaaa aaaaaa aaaaaaa aaaaaaaa aaaaaaaaa aaaaaaaaaa

 */

