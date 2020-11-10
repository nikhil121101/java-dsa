package cs201Assingment.lab11;


import java.io.*;
import java.util.*;

public class Question_four_five {
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

    static class Edge {
        int v , u;
        Edge() {

        }
        Edge(int v , int u) {
            this.v = v;
            this.u = u;
        }

        @Override
        public String toString() {
            return (v + 1) + " - " + (u + 1);
        }
    }

    static FastReader f = new FastReader();

    static int n , m;
    static ArrayList<Edge> edges;

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
        k(length of cut)
        v1 v2 v3 - - - - vk(vertexes in one of the cut)

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
        5
        1 2 3 4 5

        sample output

        Cross Edges :-
        1 - 6
        7 - 1
        4 - 10
        Edges cut respects :-
        7 - 8
        1 - 2
        2 - 3
        2 - 4
        4 - 5
        8 - 9
        9 - 11

         */

        //System.out.println("Enter |V| |E|");
        n = f.nextInt();
        m = f.nextInt();
        edges = new ArrayList<>();
        //System.out.println("Enter all edges one by one");
        for(int i = 0 ; i < m ; i++) {
            int u = f.nextInt() - 1 , v = f.nextInt() - 1;
            edges.add(new Edge(u , v));
        }
        int k = f.nextInt();
        Set<Integer> cut = new HashSet<>();
        for(int i = 0 ; i < k ; i++) {
            cut.add(f.nextInt() - 1);
        }
        Set<Edge> cross = new HashSet<>();
        for(int i = 0 ; i < m ; i++) {
            if((cut.contains(edges.get(i).u) && !cut.contains(edges.get(i).v))
                || (!cut.contains(edges.get(i).u) && cut.contains(edges.get(i).v))) {
                cross.add(edges.get(i));
            }
        }
        System.out.println("Cross Edges :- ");
        for(Edge e : cross) {
            System.out.println(e);
        }
        System.out.println("Edges cut respects :- ");
        for(Edge e : edges) {
            if(!cross.contains(e)) {
                System.out.println(e);
            }
        }
    }
}
