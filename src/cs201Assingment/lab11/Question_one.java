package cs201Assingment.lab11;

import java.io.*;
import java.util.*;

// TOPOLOGICAL SORTING

public class Question_one {

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

    static ArrayList<ArrayList<Integer>> adj;
    static int n , m;

    static FastReader f = new FastReader();

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

        11 10
        7 1
        7 8
        1 2
        1 6
        2 3
        2 4
        4 5
        4 10
        8 9
        9 11

        sample output

        [7, 8, 9, 11, 1, 6, 2, 4, 10, 5, 3]



         */



        System.out.println("Enter |V| |E|");
        n = f.nextInt();
        m = f.nextInt();
        adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            adj.add(new ArrayList<>());
        }
        System.out.println("enter all edges one by one");
        for(int i = 0 ; i < m ; i++) {
            int u = f.nextInt() - 1 , v = f.nextInt() - 1;
            adj.get(u).add(v);
        }
        System.out.println("Topological sort - ");
        ArrayList<Integer> ts = topologicalSort();
        for(int i = 0 ; i < n ; i++) {
            ts.set(i , ts.get(i) + 1);
        }
        System.out.println(ts);
    }

    static ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> a = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                dfs(i , a , visited);
            }
        }
        Collections.reverse(a);

        return a;
    }

    private static void dfs(int i, ArrayList<Integer> a , boolean[] visited) {
        visited[i] = true;
        for(int x : adj.get(i)) {
            if(!visited[x]) {
                dfs(x , a ,visited);
            }
        }
        a.add(i);
    }

}

/*
11 10
7 1
7 8
1 2
1 6
2 3
2 4
4 5
4 10
8 9
9 11
 */