package dataStructures.Graph.directed.weighted;

import java.util.*;

public class airportLeetcode {
    private static class diGraph {
        public static class Edge implements Comparator<Edge> {
            public int V;
            public int W;

            public Edge()
            {
            }

            public Edge(int node, int cost)
            {
                this.V = node;
                this.W = cost;
            }

            @Override
            public int compare(Edge edge1, Edge edge2)
            {
                if (edge1.W < edge2.W)
                    return -1;
                if (edge1.W > edge2.W)
                    return 1;
                return 0;
            }
        }
        int V;
        HashMap<Integer , ArrayList<Edge>> adjList;
        diGraph(int V) {
            this.V = V;
            adjList = new HashMap<Integer , ArrayList<Edge>>();
            for (int i = 0; i < V; i++) {
                adjList.put(i , new ArrayList<Edge>());
            }
        }
        void addEdge(int u, int v , int W) {
            adjList.get(u).add(new Edge(v, W));
        }
        void printGraph() {
            System.out.println("graph veteces - >");
            for (int i = 0; i < V; i++) {
                for (Edge x : adjList.get(i)) {
                    System.out.print(i + "-" + x.W + "-" + x.V + " , ");
                }
            }
            System.out.println();
        }

        public static class airport implements Comparator<airport>{
            int dist;
            int k;
            airport() { }
            public airport(int dist , int k) {
                this.dist = dist;
                this.k = k;
            }
            public int compare(airport a1 , airport a2) {
                return a1.dist - a2.dist;
            }
            public boolean equals(Object o) {
                airport a = (airport)o;
                return this.dist == a.dist && this.k == a.k;
            }
        }

        int minDist(airport[] unfinalized, Set<Integer> finalized) {
            int min = Integer.MAX_VALUE;
            int minInd = -1;
            for(int i = 0 ; i < V ; i++) {
                if(unfinalized[i].dist < min && !finalized.contains(i)) {
                    min = unfinalized[i].dist;
                    minInd = i;
                }
            }
            return minInd;
        }

        int stp(int u , int v , int K) {
            Set<Integer> finalized = new HashSet<>();
            airport[] unfinalized = new airport[V];
            for(int i = 0 ; i < V ; i++) {
                if(i == u) {
                    unfinalized[i] = new airport(0 , 0);
                    continue;
                }
                unfinalized[i] = new airport(Integer.MAX_VALUE , 0);
            }
            int minDist = Integer.MAX_VALUE;
            while(finalized.size() != V) {
                int minAir = minDist(unfinalized , finalized);
                for(Edge neigh : adjList.get(minAir)) {
                    if(!finalized.contains(neigh.V) && unfinalized[neigh.V].dist < unfinalized[minAir].dist + neigh.W) {
                        unfinalized[neigh.V].dist = unfinalized[minAir].dist + neigh.W;
                        unfinalized[neigh.V].k = unfinalized[minAir].k + 1;
                        if(neigh.V == v && unfinalized[neigh.V].k <= K+1) {
                            minDist = Math.min(minDist , unfinalized[neigh.V].dist);
                        }
                    }
                }
                finalized.add(minAir);
            }
            return minDist;
        }

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        return 0;
    }
}
