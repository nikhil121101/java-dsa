package cs201Assingment.lab11;


import java.io.*;
import java.util.*;

public class Question_six {
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

    static class Edge implements Comparator<Edge> {
        int v , u , weight;
        Edge() {

        }
        Edge(int v , int u , int weight) {
            this.v = v;
            this.u = u;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "v - " + (v+1) + ", u - " + (u+1) + ", weight - " + weight;
        }

        public int compare (Edge e1 , Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    static int n , m;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        /*

        input format
        |V| |E|
        e1(u v weight)
        e2
        |
        |
        |

        em
        k(length of cut)
        v1 v2 v3 - - - - vk(vertexes in one of the cut)

        sample input

        11 10
        7 1 5
        7 8 4
        1 2 3
        1 6 2
        2 3 1
        2 4 5
        4 5 6
        4 10 7
        8 9 2
        9 11 10
        5
        1 2 3 4 5

        sample output

        v - 1, u - 6, weight - 2


         */

        //System.out.println("Enter |V| |E|");
        n = f.nextInt();
        m = f.nextInt();
        edges = new ArrayList<>();
        //System.out.println("Enter all edges one by one");
        for(int i = 0 ; i < m ; i++) {
            int u = f.nextInt() - 1 , v = f.nextInt() - 1 , w = f.nextInt();
            edges.add(new Edge(u , v , w));
        }
        int k = f.nextInt();
        Set<Integer> cut = new HashSet<>();
        for(int i = 0 ; i < k ; i++) {
            cut.add(f.nextInt() - 1);
        }
        edges.sort(new Edge());
        for(int i = 0 ; i < m ; i++) {
            if((cut.contains(edges.get(i).u) && !cut.contains(edges.get(i).v))
                    || (!cut.contains(edges.get(i).u) && cut.contains(edges.get(i).v))) {
                System.out.println("Light Edge :- ");
                System.out.println(edges.get(i));
                break;
            }
        }
    }
}
