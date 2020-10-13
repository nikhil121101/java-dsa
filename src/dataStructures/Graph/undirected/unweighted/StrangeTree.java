package dataStructures.Graph.undirected.unweighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StrangeTree {

    class Node {
        int index , population , fruits;
        Node(int index , int population , int fruits) {
            this.index = index;
            this.population = population;
            this.fruits = fruits;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            int n = Integer.parseInt(br.readLine());
            int u[] = new int[n-1];
            int v[] = new int[n-1];
            for(int i = 0 ; i < n - 1 ; i++) {
                st = new StringTokenizer(br.readLine());
                u[i] = Integer.parseInt(st.nextToken());
                v[i] = Integer.parseInt(st.nextToken());
            }
            int p[] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n ; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }
            int a[] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n ; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int b[] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n ; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }
            int res[] = Code(n , u , v , p , a , b);
            for(int i = 0 ; i < n ; i++) {
                sb.append(res[i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(HashMap<Integer , HashSet<Integer>> adjList , int[] a , int b[] , int res[] , int curLooters , int src , boolean visited[] , int day) {
        visited[src] = true;
        if(res[src] == -1) {
            b[src] = Math.max(0 , b[src] - a[curLooters]);
            if(b[src] == 0)
            res[src] = day;
        }
        if(adjList.get(src) != null) {
            for(int x : adjList.get(src)) {
                if(!visited[x]) {
                    dfs(adjList , a , b , res , curLooters , x , visited , day);
                }
            }
        }
    }

    static int[] Code(int n , int[] u , int[] v , int[] p , int[] a , int[] b) {
        int res[] = new int[n];
        Arrays.fill(res , -1);
        HashMap<Integer , HashSet<Integer>> adjList = new HashMap<>();
        for(int i = 0 ; i < n - 1 ; i++) {
            if(!adjList.containsKey(u[i] - 1)) {
                adjList.put(u[i] - 1 , new HashSet<>());
            }
            adjList.get(u[i] - 1).add(v[i] - 1);
            if(!adjList.containsKey(v[i] - 1)) {
                adjList.put(v[i] - 1 , new HashSet<>());
            }
            adjList.get(v[i] - 1).add(u[i] - 1);
        }
        //System.out.println(adjList);
        for(int i = 0 ; i < n ; i++) {
            int curLooters = p[i] - 1;
            dfs(adjList , a , b , res , curLooters , curLooters , new boolean[n] , i + 1);
            //System.out.println("currentLooters " + curLooters);
            if(adjList.get(curLooters) != null) {
                for(Iterator<Integer> itr = adjList.get(curLooters).iterator() ; itr.hasNext() ; ) {
                    int x = itr.next();
                    adjList.get(x).remove(curLooters);
                }
                adjList.get(curLooters).clear();
            }
            //System.out.println("adjList Became " + adjList);
        }
        return res;
    }

}
