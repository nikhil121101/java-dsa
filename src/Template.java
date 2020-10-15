import java.awt.desktop.AboutHandler;
import java.io.*;
import java.util.*;

public class Template {

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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int m = (int)1e9 + 7;
    static int MAX = 500005;

    static boolean[] sieveOfEratosthenes(int n)
    {
        boolean isPrime[] = new boolean[n+1];
        Arrays.fill(isPrime , true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= n ; i++)
        {
            for(int j = 2 * i ; j <= n; j += i)
                isPrime[i] = false;
        }
        return isPrime;
    }

    static ArrayList<Set<Integer>> primeFactorization(int n) {
        ArrayList<Set<Integer>> fact = new ArrayList<Set<Integer>>();
        for(int i = 0 ; i <= n ; i++) {
            TreeSet<Integer> set = new TreeSet<Integer>();
            set.add(1);
            set.add(i);
            fact.add(set);
        }
        fact.get(1).add(1);
        for(int i = 2 ; i <= Math.sqrt(n) ; i++) {
            for(int j = 2*i ; j <= n ; j += i) {
                fact.get(j).add(i);
            }
        }
        return fact;
    }

    static long modExpo(long x , long y , int mod){
        long res = 1;
        for(long i = 0 ; i < y ; i++){
            res=res*x%mod;
        }
        return res;
    }

    static int gcd(int a , int b) {
        if(a % b == 0) {
            return b;
        }
        return gcd(b , a % b);
    }


    static long moduloInversePrime(long a) {
        long ans = modPow(a , m - 2);
        //System.out.println("modulo inverse of " + a + " -> " + ans);
        return ans;
    }

    static long mult(long a, long b)
    {
        return (a * (long)b % m);
    }

    static long modPow(long a, int step)
    {
        long ans = 1;
        while(step != 0)
        {
            if((step & 1) != 0)
                ans = mult(ans , a);
            a = mult(a , a);
            step >>= 1;
        }
        return ans;
    }

    static long longModulus(long x , long m) {
        long d = x / m;
        return x - d * m;
    }

    static long[] inputArray(int n) throws IOException {
        long a[] = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        return a;
    }

    static int search(int numbers[] , int target) {
        //calculates first occurance
        int endIndex = -1;
        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (numbers[mid] > target) {
                high = mid - 1;
            } else if (numbers[mid] == target) {
                endIndex = mid;
                low = mid + 1;
            } else {
                low = mid + 1;
                endIndex = mid;
            }
        }
        return endIndex;
    }


    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(1);
        pq.add(3);
        pq.add(5);pq.add(2);
        pq.add(2);
        pq.add(2);
        pq.add(3);
        System.out.println(pq);
        pq.remove(2);
        System.out.println(pq);
    }



}
/*
10 5
1 2 3 4 5
5 3 2 3 5 3 1 2 3 2
 */