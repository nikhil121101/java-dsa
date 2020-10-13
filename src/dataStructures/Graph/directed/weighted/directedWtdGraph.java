package dataStructures.Graph.directed.weighted;

import java.util.*;

class diGraph {
	public class Node implements Comparator<Node> {
		public int V;
		public int W;

		public Node()
		{
		}

		public Node(int node, int cost)
		{
			this.V = node;
			this.W = cost;
		}

		@Override
		public int compare(Node node1, Node node2)
		{
			if (node1.W < node2.W)
				return -1;
			if (node1.W > node2.W)
				return 1;
			return 0;
		}
	}
	int V;
	HashMap<Integer , ArrayList<Node>> adjList;
	diGraph(int V) {
		this.V = V;
		adjList = new HashMap<Integer , ArrayList<Node>>();
		for (int i = 0; i < V; i++) {
			adjList.put(i , new ArrayList<Node>());
		}
	}
	void AddEdge(int u, int v , int W) {
		adjList.get(u).add(new Node(v, W));
	}
	void printGraph() {
		System.out.println("graph veteces - >");
		for (int i = 0; i < V; i++) {
			for (Node x : adjList.get(i)) {
				System.out.print(i + "-" + x.W + "-" + x.V + " , ");
			}
		}
		System.out.println();
	}

	void BFS(int s) {
		boolean[] visited = new boolean[V];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		while(!queue.isEmpty()) {
			int curNode = queue.remove();
			System.out.print(curNode + " ");
			for(Node node : adjList.get(curNode)) {
				if(!visited[node.V]) {
					queue.add(node.V);
					visited[node.V] = true;
				}
			}
		}
		System.out.println();
	}

	int shortestPath(int u , int v , int k) {
		int[][] dp = new int[V][V];
		for(int i = 0 ; i < V ; i++) {
			Arrays.fill(dp[i] , -1);
		}
		boolean[] visited = new boolean[V];
		return shortestPathHelper(u , v , visited , dp , k);
	}

	int shortestPathHelper(int u , int v , boolean[] visited , int[][] dp , int k) {
		int maxValue = (int)1e6;
		if(v == u) {
			return 0;
		}
		if(k == 0) {
			return maxValue;
		}
		visited[u] = true;
		int minPath = maxValue;
		for(Node x : adjList.get(u)) {
			if(!visited[x.V]) {
				int temp = dp[x.V][v] != -1 ? dp[x.V][v] : shortestPathHelper(x.V , v , visited , dp , k-1) + x.W;;
				dp[x.V][v] = temp;
				if(temp < minPath) {
					minPath = temp;
				}
			}

		}
		return minPath;
	}
}

public class directedWtdGraph {

	//	MAA CHUDAYE PRIMS ALGORITHM

	public static void main(String[] args) {
		diGraph graph = new diGraph(10);
		graph.AddEdge(5, 4 , 3);
		graph.AddEdge(0, 1 , 2);
		graph.AddEdge(0 , 2 , 6);
		graph.AddEdge(0, 4 , 7);
		graph.AddEdge(2 , 3 , 1);
		graph.AddEdge(1, 3 , 5);
		graph.AddEdge(1, 2 , 4);
		graph.AddEdge(3, 6 , 8);
		graph.AddEdge(6 , 5 , 2);
		graph.printGraph();
		graph.BFS(0);
	}


}
