package algorithms.dp_bitMasking;

import java.io.*;
import java.util.*;

public class Trevelling_salesman {
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

    public static void main(String[] args) throws IOException {
        int t = f.nextInt();
        while(t-- != 0) {
            int n = f.nextInt();
            int cost[][] = new int[n][n];
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    cost[i][j] = f.nextInt();
                }
            }
            long[][] dp = new long[n][1<<n];
            for(int i = 0 ; i < n ; i++) {
                Arrays.fill(dp[i] , -1);
            }
            sb.append(solve(cost , n , dp , 0 , (1<<n) - 2)).append("\n");
        }
        System.out.println(sb);
    }

    static long solve(int[][] cost, int n, long[][] dp, int i, int mask) {
        if(mask == 0) {
            return cost[i][0];
        }
        if(dp[i][mask] != -1) {
            return dp[i][mask];
        }
        long ans = Integer.MAX_VALUE;
        for(int j = 0 ; j < n ; j++) {
            if((mask & (1<<j)) != 0) {
                ans = Math.min(ans , cost[i][j] + solve(cost , n , dp , j , mask ^ (1<<j)));
            }
        }
        dp[i][mask] = ans;
        return ans;
    }
}
/*
1
4
0 2000 6000 7000
3000 0 8000 3000
5000 9000 0 1000
8000 4000 1000 0
 */