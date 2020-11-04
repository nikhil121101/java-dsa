package dataStructures.Graph.undirected.weighted;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparator<Edge> {
	int v , u , weight;
	Edge() {

	}
	Edge(int v , int u , int weight) {
		this.v = v;
		this.u = u;
		this.weight = weight;
	}
	public int compare (Edge e1 , Edge e2) {
		return e2.weight - e1.weight;
	}
}

public class WtdUndirGraph {

	static int n;
	static Edge[] edges;
	static int[] parent , rank;

	static void initializeDSU() {
		parent = new int[n];
		for(int i = 0 ; i < n ; i++) {
			parent[i] = i;
		}
		rank = new int[n];
	}
	public static void main(String args[]) throws IOException {
		int res = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int a[][] = new int[n][d];
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < d ; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int edgesCount = (n * (n - 1)) / 2;
		edges = new Edge[edgesCount];
		int e = 0;
		for(int i = 0 ; i < n - 1 ; i++) {
			for(int j = i + 1 ; j < n ; j++) {
				int weight = 0;
				for(int k = 0 ; k < d ; k++) {
					weight += Math.abs(a[i][k] - a[j][k]);
				}
				edges[e++] = new Edge(i , j , weight);
			}
		}
		initializeDSU();
		Arrays.sort(edges , new Edge());
		for(int i = 0 ; i < edgesCount ; i++) {
			int p1 = find(edges[i].u);
			int p2 = find(edges[i].v);
			if(p1 != p2) {
				res += edges[i].weight;
				union(p1 , p2);
			}
		}
		System.out.println(res);
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

	ArrayList<ArrayList<Integer>> permutation(int n) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if(n == 1) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(1);
			res.add(temp);
			return res;
		}
		ArrayList<ArrayList<Integer>> prev = permutation(n-1);
		for(int i = 0 ; i <= n ; i++) {
			for(ArrayList<Integer> list : prev) {
				ArrayList<Integer> temp = new ArrayList<>(list);
				temp.add(i , n);
				res.add(temp);
			}
		}
		return res;
	}

}
/*
5 2
1 1
0 1
3 2
-3 5
3 -4
 */
