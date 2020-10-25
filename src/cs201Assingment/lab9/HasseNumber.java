package cs201Assingment.lab9;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class HasseNumber {
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

    static class Node implements Comparable<Node>{
        String id;
        TreeMap<Integer , Integer> partition;
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

    static String getID(TreeMap<Integer , Integer> part) {
        StringBuilder id = new StringBuilder();
        for(Map.Entry<Integer , Integer> x : part.entrySet()) {
            for(int i = 0; i < x.getValue() ; i++) {
                id.append(x.getKey());
            }
        }
        return id.toString();
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

    public static void main(String[] args) throws IOException {
        int n = f.nextInt();
        TreeMap<Node , ArrayList<Node>> adj = createGraph(n);
        System.out.println("res - ");
        printGraph(adj);
    }

    private static TreeMap<Node, ArrayList<Node>> createGraph(int n) {
        TreeMap<Node, ArrayList<Node>> adj = new TreeMap<Node , ArrayList<Node>>();
        TreeMap<Integer , Integer> curMap = new TreeMap<>();
        addToMap(curMap , n);
        Node s = new Node(String.valueOf(n) , curMap);
        adj.put(s , new ArrayList<Node>());
        createGraphHelper(adj , s);
        return adj;
    }

    static void printGraph(TreeMap<Node , ArrayList<Node>> adj) {
        for(Map.Entry<Node , ArrayList<Node>> element : adj.entrySet()) {
            System.out.print(element.getKey().toString() + " -> ");
            for(Node node : element.getValue()) {
                System.out.print(node.toString());
            }
            System.out.println();
        }
    }

    private static void createGraphHelper(TreeMap<Node, ArrayList<Node>> adj, Node src) {
        //System.out.println("src - " + src.toString() + " ");
        TreeMap<Integer , Integer> curMap = src.partition;
        //System.out.println("curMap - " + curMap);
        for(Map.Entry<Integer , Integer> element : curMap.entrySet()) {
            int x = element.getKey();
            //System.out.println("x - " + x);
            for(int i = 1 ; i <= (x / 2) ; i++) {
                //System.out.println("i - " + i + " <= " + x/2);
                TreeMap<Integer , Integer> tempMap = new TreeMap<>(curMap);
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
                    rec = true;
                }
                adj.get(src).add(newNode);
                adj.get(newNode).add(src);
                //System.out.println("adj - ");
                //printGraph(adj);
                if(rec)
                createGraphHelper(adj , newNode);
            }
        }
    }
}
