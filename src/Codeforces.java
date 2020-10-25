import java.io.*;
import java.util.*;
public class Codeforces {

    static class FastReader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
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

        public double nextDouble() throws IOException
        {
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader f = new FastReader();
        int n = f.nextInt() , m = f.nextInt() , K = f.nextInt();
        char a[][] = new char[n][m];
        int tot = 0;
        for(int i =0 ; i < n ; i++) {
            String s = f.readLine();
            for(int j = 0 ; j < m ; j++) {
                a[i][j] = s.charAt(j);
                if(s.charAt(j) == '#') {
                    tot++;
                }
            }
        }
        int ans = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                int cur = 0;
                for(int k = 0 ; k < n ; k++) {
                    if(a[k][j] == '#') {
                        cur++;
                    }
                }
                for(int k = 0 ; k < m ; k++) {
                    if(a[i][k] == '#' && k != j) {
                        cur++;
                    }
                }
                if(tot-cur == K) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    private static int solve(int[] a, int[][] dp , int n, int i, int flag) {
        if(i < 0) {
            return 0;
        }
        if(dp[i][flag] != -1) {
            return dp[i][flag];
        }
        int res = 0;
        if(a[i] == 1) {
            if(flag > 0)
                res = Math.min(1 + solve(a , dp , n , i - 1 , flag) , 1 + solve(a , dp , n , i , flag - 1));
            else
                res =  solve(a , dp , n , i - 1 , 0);
        }
        else {
            res = solve(a , dp , n , i - 1 , flag + 1);
        }
        dp[i][flag] = res;
        return res;
    }
}
/*
5
2 1
1 1
500 4
217871987498122 10
100000000000000001 1
 */
