package dataStructures.Graph.undirected.unweighted;


import java.io.*;
import java.util.*;

public class Cf {
    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static FastReader f = new FastReader();
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int m = (int) 1e9 + 7;
    static int MAX = 500005;

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }


    static long moduloInversePrime(long a) {
        long ans = modPow(a, m - 2);
        //System.out.println("modulo inverse of " + a + " -> " + ans);
        return ans;
    }

    static long mult(long a, long b) {
        return (a * (long) b % m);
    }

    static long modPow(long a, int step) {
        long ans = 1;
        while (step != 0) {
            if ((step & 1) != 0)
                ans = mult(ans, a);
            a = mult(a, a);
            step >>= 1;
        }
        return ans;
    }

    static long longModulus(long x, long m) {
        long d = x / m;
        return x - d * m;
    }

    static int[] inputArray(int n) throws IOException {
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    static class Edge {
        int v , u;
        Edge() {

        }
        Edge(int v , int u) {
            this.v = v;
            this.u = u;
        }
    }
    static int n;
    static ArrayList<Edge> edges;
    static int[] size;
    static int[] parent , rank;

    static int find(int e) {
        if(parent[e] == e) {
            return e;
        }
        int p = find(parent[e]);
        parent[e] = p;
        return p;
    }

    static int union(int u , int v) {
        int p1 = find(u);
        int p2 = find(v);
        if(rank[p1] > rank[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
            return p1;
        }
        else if(rank[p1] < rank[p2]) {
            parent[p1] = p2;
            size[p2] += size[p1];
            return p2;
        }
        else {
            parent[p1] = p2;
            size[p2] += size[p1];
            rank[p2]++;
            return p2;
        }
    }

    static void initializeDSU() {
        parent = new int[n];
        for(int i = 0 ; i < n ; i++) parent[i] = i;
        size = new int[n];
        Arrays.fill(size , 1);
        rank = new int[n];
    }

    public static void main(String[] args) throws IOException {
        int t = f.nextInt();
        while(t-- != 0) {
            n = f.nextInt();
            int a[] = new int[n];
            for(int i = 0 ; i< n ; i++) {
                a[i] = f.nextInt();
            }
            edges = new ArrayList<>();
            for(int i = 0 ; i < n - 1 ; i++) {
                for(int j = i + 1; j < n ; j++) {
                    if(a[i] != a[j])
                    edges.add(new Edge(i , j));
                }
            }
            StringBuilder res = new StringBuilder("");
            initializeDSU();
            for(int i =0 ; i < edges.size() ; i++) {
                Edge e = edges.get(i);
                int p1 = find(e.u);
                int p2 = find(e.v);
                if(p1 != p2) {
                    int p = union(p1 , p2);
                    res.append(e.u+1).append(" ").append(e.v+1).append("\n");
                }
            }
            int p = parent[0];
            boolean flag = true;
            for(int i = 1; i < n ;i++) {
                if(parent[i] != p) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                System.out.println("YES");
                System.out.print(res);
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
