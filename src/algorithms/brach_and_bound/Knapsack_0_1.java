package algorithms.brach_and_bound;

import java.io.*;
import java.util.*;
//TODO gives wrong answer in gfg
public class Knapsack_0_1 {
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

    static class Node implements Comparator<Node>{
        int ind , weightLeft , curPoints;
        double upperBound;
        Node(int ind , double upperBound , int weightLeft , int curPoints) {
            this.ind = ind;
            this.upperBound = upperBound;
            this.weightLeft = weightLeft;
            this.curPoints = curPoints;
        }
        Node() {

        }
        @Override
        public int compare(Node o1, Node o2) {
            return (int) (o2.upperBound - o1.upperBound);
        }
        @Override
        public String toString() {
            return "ind : " + ind + ", weightLeft : " + weightLeft + " curPoints : " + curPoints + " ub : " + upperBound;
        }
    }

    static class Item implements Comparator<Item>{
        int weight;
        int points;
        Item() {

        }
        Item(int weight , int points) {
            this.weight = weight;
            this.points = points;
        }
        @Override
        public int compare(Item o1, Item o2) {
            double val = (o2.points / (double)o2.weight) - (o1.points / (double) o1.weight);
            return val > 0 ? 1 : val == 0 ? 0 : -1 ;
        }
        @Override
        public String toString() {
            return "weight - " + weight + " point - " + points;
        }
    }

    static double upper_bound(int curPoints , int weightLeft , int ind , Item[] items , int n) {
        double res = 0;
        int i = ind;
        while(weightLeft > 0 && i < n) {
            if(weightLeft - items[i].weight > 0) {
                res += items[i].points;
                weightLeft -= items[i].weight;
                i++;
            }
            else {
                res += weightLeft * ((double)items[i].points / items[i].weight);
                weightLeft = 0;
            }
        }
        return res + curPoints;
    }

    static int solve(int maxWeight , Item[] items , int n) {
        int maxAns = 0;
        Arrays.sort(items , new Item());
        PriorityQueue<Node> pq = new PriorityQueue<>(new Node());
        double ub = upper_bound(0 , maxWeight , 0 , items , n);
        pq.add(new Node(0 , ub , maxWeight , 0));
        while(!pq.isEmpty()) {
            Node cur = pq.remove();
            if(cur.upperBound < maxAns) {
                break;
            }
            if(cur.ind == n) {
                maxAns = Math.max(maxAns , cur.curPoints);
                continue;
            }
            if(cur.weightLeft >= items[cur.ind].weight) {
                int tempPoint = cur.curPoints + items[cur.ind].points;
                int tempWeight = cur.weightLeft - items[cur.ind].weight;
                ub = upper_bound(tempPoint , tempWeight , cur.ind + 1 , items , n);
                pq.add(new Node(cur.ind + 1 , ub , tempWeight , tempPoint));
            }
            ub = upper_bound(cur.curPoints , cur.weightLeft , cur.ind + 1 , items , n);
            pq.add(new Node(cur.ind + 1 , ub , cur.weightLeft , cur.curPoints));
        }
        return maxAns;
    }

    public static int solveKnapsack(int[] A, int[] B, int C) {
        int n = A.length;
        Item[] item = new Item[n];
        for(int i =0 ; i < n ; i++) {
            item[i] = new Item(A[i] , B[i]);
        }
        return solve(C , item , n);
    }

    public static void main(String[] args) throws IOException {
        int t = f.nextInt();
        while(t-- != 0) {
            int n = f.nextInt() , W = f.nextInt();
            Item items[] = new Item[n];
            for(int i = 0 ; i < n ; i++) {
                items[i] = new Item();
                items[i].points = f.nextInt();
            }
            for(int i = 0 ; i < n ; i++) {
                items[i].weight = f.nextInt();
            }
            sb.append(solve(W , items , n)).append("\n");
        }
        System.out.print(sb);
    }
}
/*
1
3
50
60 100 120
10 20 30
 */