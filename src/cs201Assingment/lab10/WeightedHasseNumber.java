package cs201Assingment.lab10;

import java.io.*;
import java.util.*;

public class WeightedHasseNumber {
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

    // DSU

    static int[] parent , rank;

    static void initializeDSU() {
        parent = new int[n];
        for(int i = 0 ; i < n ; i++) {
            parent[i] = i;
        }
        rank = new int[n];
    }

    static int find(int e) {
        if(parent[e] == e) {
            return e;
        }
        int p = find(parent[e]);
        parent[e] = p;
        return p;
    }

    static void union(int u , int v) {
        int p1 = find(u);
        int p2 = find(v);
        if(rank[p1] > rank[p2])
            parent[p2] = p1;
        else if(rank[p1] < rank[p2])
            parent[p1] = p2;
        else {
            parent[p1] = p2;
            rank[p2]++;
        }
    }

    //  DSU

    static class Node implements Comparable<Node>{
        String id;
        TreeMap<Integer , Integer> partition;
        int index;
        Node(String id , TreeMap<Integer , Integer> partition) {
            this.id = id;
            this.partition = partition;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node n1 = (Node)o;
            return n1.id.equals(this.id);
        }
        @Override
        public String toString() {
            return id + " , ";
        }

        @Override
        public int compareTo(Node o) {
            return o.id.compareTo(this.id);
        }
    }

    static class Edge implements Comparable<Edge>{
        Node v , u;
        int weight;
        Edge(Node u , Node v , int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + u.id+ " , " + v.id + " , " + weight + ")";
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static void addToMap(Map<Integer , Integer> map , int x) {
        if(map.containsKey(x)) {
            map.replace(x , map.get(x) + 1);
        }
        else {
            map.put(x , 1);
        }
    }

    static void removeFromMap(Map<Integer , Integer> map , int x) {
        map.replace(x , map.get(x) - 1);
        if(map.get(x) == 0) {
            map.remove(x);
        }
    }

    static int mapSize(TreeMap<Integer , Integer> map) {
        int n = 0;
        for(Map.Entry<Integer , Integer> entry : map.entrySet()) {
            n += entry.getValue();
        }
        return n;
    }

    private static int calcWeight(Node u , Node v) {
//        System.out.println("Debug");
//        System.out.println(u.partition + " , " + v.partition);
        int n1 = mapSize(u.partition);
        int n2 = mapSize(v.partition);
        int n = Math.min(n1 , n2);
        int[] p = new int[n];
        int i = 0;
        boolean exit = false;
        for(Map.Entry<Integer , Integer> entry : u.partition.entrySet()) {
            for(int j = 0 ; j < entry.getValue() ; j++) {
                p[i++] = entry.getKey();
                if(i == n) {
                    exit = true;
                    break;
                }
            }
            if(exit) {
                break;
            }
        }
        i = 0;
        exit = false;
        for(Map.Entry<Integer , Integer> entry : v.partition.entrySet()) {
            for(int j = 0 ; j < entry.getValue() ; j++) {
                p[i++] *= entry.getKey();
                if(i == n) {
                    exit = true;
                    break;
                }
            }
            if(exit) {
                break;
            }
        }
        //System.out.println("p - " + Arrays.toString(p));
        int res = 0;
        for(i = 0 ; i < n ; i++) {
            res += p[i];
        }
        return res;
    }

    static String getID(TreeMap<Integer , Integer> part) {
        StringBuilder id = new StringBuilder();
        for(Map.Entry<Integer , Integer> x : part.entrySet()) {
            for(int i = 0; i < x.getValue() ; i++) {
                id.append(x.getKey());
            }
        }
        return id.toString();
    }

    static void printGraph() {
        for(Map.Entry<Node, ArrayList<Node>> element : adj.entrySet()) {
            System.out.print(element.getKey().toString() + " -> ");
            for(Node node : element.getValue()) {
                System.out.print(node.toString());
            }
            System.out.println();
        }
    }

    private static void createGraphHelper(Node src , int[] index) {
        //System.out.println("src - " + src.toString() + " ");

        TreeMap<Integer , Integer> curMap = src.partition;

        //System.out.println("curMap - " + curMap);

        for(Map.Entry<Integer , Integer> element : curMap.entrySet()) {
            int x = element.getKey();

            //System.out.println("x - " + x);

            for(int i = 1 ; i <= (x / 2) ; i++) {

                //System.out.println("i - " + i + " <= " + x/2);

                TreeMap<Integer , Integer> tempMap = new TreeMap<>(Collections.reverseOrder());
                tempMap.putAll(curMap);
                int p1 = i , p2 = x - i;

                //System.out.println("p1 - " + p1 + " p2 - " + p2);

                removeFromMap(tempMap , x);
                addToMap(tempMap , p1);
                addToMap(tempMap , p2);

                //System.out.println("tempMap - " + tempMap);

                String id = getID(tempMap);

                //System.out.println("id- " + id);

                Node newNode = new Node(id , tempMap);
                boolean rec = false;
                if(!adj.containsKey(newNode)) {
                    adj.put(newNode , new ArrayList<>());
                    index[0]++;
                    newNode.index = index[0];
                    rec = true;
                }
                adj.get(src).add(newNode);
                adj.get(newNode).add(src);
                edges.add(new Edge(src , newNode , calcWeight(src , newNode)));

                //System.out.println("adj - ");
                //printGraph(adj);

                if(rec)
                    createGraphHelper(newNode , index);
            }
        }
    }

    private static void createGraph(int n) {
        adj = new TreeMap<>();
        edges = new ArrayList<>();
        TreeMap<Integer , Integer> curMap = new TreeMap<>(Collections.reverseOrder());
        addToMap(curMap , n);
        Node s = new Node(String.valueOf(n) , curMap);
        s.index = 0;
        adj.put(s , new ArrayList<Node>());
        createGraphHelper(s , new int[1]);
    }

    static FastReader f = new FastReader();
    static int n;
    static TreeMap<Node, ArrayList<Node>> adj;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {

        int N = f.nextInt();
        createGraph(N);

        int res = 0;

        ArrayList<Edge> mstEdges = new ArrayList<>();

        n = adj.size();

        initializeDSU();

        Collections.sort(edges);

        for(Edge e : edges) {
            int p1 = find(e.u.index);
            int p2 = find(e.v.index);
            if(p1 != p2) {
                res += e.weight;
                mstEdges.add(e);
                union(p1 , p2);
            }
        }

        System.out.println("mst distance - " + res);
        System.out.println("mst size - " + mstEdges.size());
        System.out.println("MST -> ");
        System.out.println(mstEdges);
    }

}