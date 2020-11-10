package cs201Assingment.lab11;

import java.io.*;
import java.util.*;

public class Question_two {
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

    static ArrayList<ArrayList<Integer>> adj;
    static ArrayList<ArrayList<Integer>> reverse;
    static int n;

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

        11 11
        7 1
        7 8
        1 2
        6 1
        2 3
        2 4
        4 5
        4 10
        8 9
        9 11
        4 6

        sample output

        [[2], [4], [6], [7], [8], [0, 1, 3, 5], [9], [10]]


         */

        System.out.println("Enter |V| |E|");
        n = f.nextInt();
        int m = f.nextInt();
        adj = new ArrayList<>();
        for(int i =0 ; i < n ; i++) {
            adj.add(new ArrayList<>());
        }
        System.out.println("Enter all edges one by one");
        for(int i =0 ; i < m ; i++) {
            int u = f.nextInt() - 1 , v = f.nextInt() - 1;
            adj.get(u).add(v);
        }
        Set<Set<Integer>> scc = stronglyConnectedComponents();
        System.out.println("strongly connected component");
        System.out.println(scc);
    }

    static ArrayList<ArrayList<Integer>> reverse() {
        reverse = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            reverse.add(new ArrayList<>());
        }
        for(int i = 0; i  < n ; i++) {
            for(int x : adj.get(i)) {
                reverse.get(x).add(i);
            }
        }
        return reverse;
    }

    private static Set<Set<Integer>> stronglyConnectedComponents() {
        Set<Set<Integer>> res = new HashSet<>();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i =0 ; i < n ; i++) {
            if(!visited[i]) {
                dfs1(i , stack , visited);
            }
        }
        reverse();
        Arrays.fill(visited , false);
        while(!stack.isEmpty()) {
            HashSet<Integer> set = new HashSet<>();
            int x = stack.pop();
            if(!visited[x]) {
                dfs2(x , set , visited);
                res.add(set);
            }
        }
        return res;
    }

    private static void dfs2(int i, HashSet<Integer> set, boolean[] visited) {
        visited[i] = true;
        set.add(i);
        for(int x : reverse.get(i)) {
            if(!visited[x]) {
                dfs2(x , set , visited);
            }
        }
    }

    private static void dfs1(int i, Stack<Integer> stack, boolean[] visited) {
        visited[i] = true;
        for(int x : adj.get(i)) {
            if(!visited[x]) {
                dfs1(x , stack , visited);
            }
        }
        stack.push(i);
    }
}
/*
11 11
7 1
7 8
1 2
6 1
2 3
2 4
4 5
4 10
8 9
9 11
4 6
 */