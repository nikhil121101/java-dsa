package dataStructures.Graph.undirected.unweighted;

import java.io.*;
import java.util.*;

public class Imp {

	private static class Tree {
		int index;
		ArrayList<Tree> childs;
		Tree(int index) {
			this.index = index;
			childs = new ArrayList<Tree>();
		}
	}
	static int V;
	static ArrayList<HashSet<Integer>> adjList;

	static void intialize(int n) {
		V = n;
		adjList = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < V; i++) {
			adjList.add(new HashSet<Integer>());
		}
	}

	static void addEdge(int u, int v) {
		adjList.get(u).add(v);
		adjList.get(v).add(u);
	}

	static Tree rootTree(int s , boolean[] visited) {
		Tree root = new Tree(s);
		visited[s] = true;
		for(int x : adjList.get(s)) {
			if(!visited[x]) {
				root.childs.add(rootTree(x , visited));
			}
		}
		return root;
	}

	static int nodeCount(Tree root , ArrayList<Integer> traffic , int n) {
		int totalNode = 1;
		for(Tree child : root.childs) {
			int nodes = nodeCount(child , traffic , n);
			traffic.add(nodes * (n - nodes));
			totalNode += nodes;
		}
		return totalNode;
	}

	static void printGraph() {
		System.out.println("graph veteces - >");
		for (int i = 0; i < V; i++) {
			for (Integer integer : adjList.get(i)) {
				System.out.print(i + "-" + integer + " , ");
			}
		}
		System.out.println();
	}

	static void BFS(int s , int[] levels) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[V];
		Arrays.fill(visited, false);
		queue.add(s);
		visited[s] = true;
		//System.out.println("BFS - > ");
		int curNode = -1;
		int dist = 0;
		while(true) {
			int width = queue.size();
			while (width > 0) {
				//System.out.print(queue.element() + " -> ");
				curNode = queue.remove();
				HashSet<Integer> curHashSet = adjList.get(curNode);
				for (int temp : curHashSet) {
					if (!visited[temp]) {
						visited[temp] = true;
						queue.add(temp);
					}
				}
				width--;
			}
			if(queue.isEmpty()) {
				break;
			}
			dist++;
		}

		levels[0] = curNode;
		levels[1] = dist;
		//System.out.println();
	}
	static int nodeCount(Tree tree) {
		int nodeCount = 1;
		for(Tree x : tree.childs) {
			nodeCount += nodeCount(x);
		}
		return nodeCount;
	}

	static int diameterOfTree() {
		int s = 0;
		int f1[] = new int[2];
		BFS(s , f1);
		//System.out.println(Arrays.toString(f1));
		int f2[] = new int[2];
		BFS(f1[0] , f2);
		//System.out.println(Arrays.toString(f1));
		return f2[1];
	}

	static void depth(int s, int[] depth, boolean[] visited, int curDepth) {
		visited[s] = true;
		depth[s] = curDepth;
		for(int c : adjList.get(s)) {
			if(!visited[c]) {
				depth(c , depth , visited , curDepth + 1);
			}
		}
	}

	static int lift(int s , int l , int[][] u) {
		int curLift = 0;
		while(l > 0) {
			int curBit = l % 2;
			s = u[s][curLift];
			curLift++;
			l = l>>2;
		}
		return s;
	}

	private static void DFS(int s, boolean[] visited, int[][] u) {
		visited[s] = true;
		for(int c : adjList.get(s)) {
			if(!visited[c]) {
				u[c][0] = s;
				DFS(c , visited , u);
			}
		}
	}

	static int[][] getUpliftArray() {
		int[][] u = new int[V][V];
		int n = V;
		for(int i = 0 ; i < n ; i++) {
			Arrays.fill(u[i] , -1);
		}
		//lift 1
		DFS(0 , new boolean[n] , u);
		int lift = 2;
		for(int j = 1 ; lift < n ; j++) {
			for(int i = 0 ; i < n ; i++) {
				if(u[i][j-1] != -1 && u[u[i][j-1]][j-1] != -1)
					u[i][j] = u[u[i][j-1]][j-1];
			}
			lift = lift<<1;
		}
		return u;
	}

	private static int LowestCommonAncestor(int n1, int n2) {
		int n = V;
		int[][] u = getUpliftArray();
		int[] depth = new int[n];
		depth(0 , depth , new boolean[n] , 0);
		if(depth[n1] > depth[n2]) {
			n1 = lift(n1 , depth[n1] - depth[n2] , u);
		}
		if(depth[n2] > depth[n1]) {
			n2 = lift(n2 , depth[n2] - depth[n1] , u);
		}
		if(n1 == n2) {
			return n1;
		}
		int low = 0;
		int res = 0 ,high = n- 1;
		while(low <= high) {
			int mid = (low + high) / 2;
			if(u[n1][mid] == u[n2][mid]) {
				high = mid - 1;
				res = u[n1][mid] ;
			}
			else {
				low = mid + 1;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		intialize(n);
		for(int i = 0 ; i < n-1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			addEdge(u , v);
		}
		//System.out.println(adjList);
		st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken()) - 1;
		int n2 = Integer.parseInt(st.nextToken()) - 1;
		System.out.println(LowestCommonAncestor(n1 , n2) + 1);
	}
}

/*
9
1 2
1 3
2 4
2 5
4 6
5 7
7 8
7 9
6 9
 */