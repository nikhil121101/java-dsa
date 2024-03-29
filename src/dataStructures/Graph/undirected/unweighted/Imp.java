package dataStructures.Graph.undirected.unweighted;

import java.io.*;
import java.util.*;

class diameterImp {

	private static class Tree {
		int index;
		ArrayList<Tree> childs;
		Tree(int index) {
			this.index = index;
			childs = new ArrayList<Tree>();
		}
	}

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
	static StringBuilder sb = new StringBuilder("");

	static int n , maxN;
	static ArrayList<HashSet<Integer>> adjList;

	static void initializeGraph() {
		adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new HashSet<>());
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

	static void printGraph() {
		System.out.println("graph veteces - >");
		for (int i = 0; i < n; i++) {
			for (Integer integer : adjList.get(i)) {
				System.out.print(i + "-" + integer + " , ");
			}
		}
		System.out.println();
	}

	static void BFS(int s , int[] levels) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[n];
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

	static int diameterOfTree(int[] ends) {
		int s = 0;
		int f1[] = new int[2];
		BFS(s , f1);
		ends[0] = f1[0];
		//System.out.println(Arrays.toString(f1));
		int f2[] = new int[2];
		BFS(f1[0] , f2);
		ends[1] = f2[0];
		//System.out.println(Arrays.toString(f1));
		return f2[1];
	}

	static void solve() {
		int[] diameterEnds = new int[2];
		diameterOfTree(diameterEnds);
		int[] parent =new int[n];
		Arrays.fill(parent , -1);
		DFS_plus_parent(diameterEnds[0] , new boolean[n] , parent);
		ArrayList<Integer> path = new ArrayList<>();
		int cur = diameterEnds[1];
		while(cur != -1) {
			path.add(cur);
			cur = parent[cur];
		}
		Collections.reverse(path);
		int height[] = new int[n];
		for(int i = 1 ; i < path.size() - 1 ; i++) {
			getHeight(path.get(i) , path.get(i-1) , path.get(i+1) , -1 , height);
		}
	}

	private static int getHeight(int s, int prev, int next, int parent , int[] height) {
		if(height[s] != 0) {
			return height[s];
		}
		int max = 0;
		for(int c : adjList.get(s)) {
			if(c != prev && c != next && c != parent) {
				max = getHeight(c , prev , next , s , height);
			}
		}
		return height[s];
	}


	private static void DFS_plus_parent(int s, boolean[] visited, int[] parent) {
		visited[s] = true;
		for(int c : adjList.get(s)) {
			if(!visited[c]) {
				parent[c] = s;
				DFS(c , visited , u);
			}
		}
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

	static int[] depth;

	static void calculateDepth(int s, boolean[] visited, int curDepth) {
		visited[s] = true;
		depth[s] = curDepth;
		for(int c : adjList.get(s)) {
			if(!visited[c]) {
				calculateDepth(c , visited , curDepth + 1);
			}
		}
	}

	static int logBase2(int n) {
		return (int) (Math.log(n) / Math.log(2));
	}

	static int[][] u;

	static void initializeLCE() {
		maxN = logBase2(n);
		u = new int[n][maxN+1];
		for(int i = 0 ; i < n ; i++) {
			Arrays.fill(u[i] , -1);
		}
		//lift 1
		DFS(0 , new boolean[n] , u);
		int lift = 2;
		for(int j = 1 ; j <= maxN ; j++) {
			for(int i = 0 ; i < n ; i++) {
				if(u[i][j-1] != -1)
					u[i][j] = u[u[i][j-1]][j-1];
			}
		}
		depth = new int[n];
		calculateDepth(0 , new boolean[n] , 0);
	}


	static int lift(int s , int l) {
		int curLift = 0;
		while(l > 0) {
			if(s == -1) {
				break;
			}
			int curBit = l & 1;
			if(curBit == 1) {
				s = u[s][curLift];
			}
			curLift++;
			l = l>>1;
		}
		return s;
	}


	private static int lowestCommonAncestor(int n1, int n2) {
		if(depth[n1] > depth[n2]) {
			n1 = lift(n1 , depth[n1] - depth[n2]);
		}
		if(depth[n2] > depth[n1]) {
			n2 = lift(n2 , depth[n2] - depth[n1]);
		}
		if(n1 == n2) {
			return n1;
		}
		int low = 0;
		int res = 0 ,high = maxN;
		int cur1 = n1 , cur2 = n2;
		int lca = 0;
		while(low <= high) {
			while(low <= high) {
				int mid = (low + high) / 2;
				//System.out.println("low - " + low + " high - " + high + " mid - " + mid);
				//System.out.println(u[cur1][mid] + "==" + u[cur2][mid]);
				if (u[cur1][mid] == u[cur2][mid]) {
					high = mid - 1;
					if(u[cur1][mid] != -1) {
						//System.out.println("matched");
						res = mid;
						lca = u[cur1][mid];
					}
				} else {
					low = mid + 1;
				}
			}
			/// cur - 2 ^ res is the lowest node with matching parent
			if(res == 0) {
				break;
			}
			cur1 = u[cur1][res-1];
			cur2 = u[cur2][res-1];
			low = 0;
			high = res - 2;
		}
		return lca;
	}

	// FIND THE MIDDLE ELEMENT BETWEEN TWO NODES

	static int distance(int x , int y) {
		int lca = lowestCommonAncestor(x , y);
		return depth[x] + depth[y] - 2*depth[lca];
	}

	static int[] findMiddlePair(int x , int y) {
		//x - jerry , y - tom
		int dist = distance(x , y);
		if(depth[x] > depth[y]) {
			return new int[]{lift(x , (dist - 1) / 2) , lift(x , (dist + 1) / 2)};
		}
		else {
			return new int[]{lift(y , (dist + 1) / 2) , lift(y , (dist - 1) / 2)};
		}
	}

	static int solve(int x , int y) {
		int[] mid = findMiddlePair(x , y);
		//System.out.println("mid - " + Arrays.toString(mid));
		int[] farthest = new int[2];
		//BFS(mid[0] , farthest , mid[1]);
		//System.out.println(" farthest - " + Arrays.toString(farthest));
		return farthest[1] + distance(mid[0] , y);
	}

	static void print2dArray(int[][] a) {
		for(int i = 0 ; i < a.length ; i++) {
			System.out.print(i + " - ");
			for(int j = 0 ; j < a[0].length ; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[] inputArray(int n) throws IOException {
		int[] a = new int[n];
		for(int i = 0 ; i < n ; i++) {
			a[i] = f.nextInt();
		}
		return a;
	}

	static long[] square;
	static HashMap<Pair , Long> res;
	static long mod = (long) Math.pow(2 , 32);

	public static void main(String[] args) throws IOException {
		n = f.nextInt();
		int q = f.nextInt();
		long coins[] = new long[n];
		for(int i =0 ; i < n ; i++) {
			coins[i] = f.nextLong();
		}
		initializeGraph();
		for(int i = 0 ; i < n - 1 ; i++) {
			int u = f.nextInt() - 1 , v = f.nextInt() - 1;
			addEdge(u , v);
			addEdge(v , u);
		}
		//System.out.println(adjList);
		initializeLCE();
		setSquare(coins);
		//System.out.println(Arrays.toString(square));
		res = new HashMap<>();
		while(q-- != 0) {
			int u = f.nextInt() - 1 , v = f.nextInt() - 1;
			sb.append(Code(u , v , coins)).append("\n");
		}
		System.out.print(sb);
	}

	static long Code(int U , int v , long[] coins) {
		if(res.containsKey(new Pair(U , v))) {
			return res.get(new Pair(U , v));
		}
		//int lce = lowestCommonAncestor(U , v);
		long s = 0;
		while(U != -1) {
			s = (s + (coins[U] * coins[v]) % mod) % mod;
			U = u[U][0];
			v = u[v][0];
		}
		res.put(new Pair(U , v) , s);
		return s;
	}

	private static class Pair {
		int u , v;
		Pair(int u , int v) {
			this.u = u;
			this.v = v;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Pair)
				return ((Pair)obj).u == u && ((Pair)obj).v == v;
			return false;
		}
	}

	private static void setSquare(long[] coins) {
		square = new long[n];
		dfs(0 , new boolean[n] , coins);
	}

	private static void dfs(int i , boolean[] visited , long[] coins) {
		visited[i] = true;
		square[i] = (square[i] + (coins[i] * coins[i]) % mod) % mod;
		for(int x : adjList.get(i)) {
			if(!visited[x]) {
				square[x] = square[i];
				dfs(x , visited , coins);
			}
		}
	}

}
/*
5 2
5 4 3 2 1
1 2
1 3
2 4
2 5
2 3
4 4
 */