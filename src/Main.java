import java.io.*;
import java.util.*;

public class Main {
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
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int mod = (int)1e9 + 7;
    static int MAX = 500005;
    static long[] fact;

    static int[] inputArray(int n) throws IOException {
        int[] a = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = f.nextInt();
        }
        return a;
    }

    static int gcd(int a , int b) {
        if(a % b == 0) {
            return b;
        }
        return gcd(b , a % b);
    }

    static void initializeFact() {
        fact = new long[MAX];
        for(int i = 0 ; i < fact.length ; i++) {
            if(i == 0) {
                fact[i] = 1;
            }
            else {
                fact[i] = fact[i-1] * i % mod;
            }
        }
    }

    static long mult(long a, long b)
    {
        return (a * b % mod);
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
        if(x < m) {
            return x;
        }
        long d = x / m;
        return x - d * m;
    }

    public static void primeFactors(long n , HashMap<Long , Integer> factors)
    {
        // Print the number of 2s that divide n
        while (n%2==0)
        {
            if(!factors.containsKey(2L)) {
                factors.put(2L, 0);
            }
            factors.replace(2L , factors.get(2L) + 1);
            n /= 2;
        }

        for (long i = 3; i <= Math.sqrt(n); i+= 2)
        {
            while (n%i == 0)
            {
                if(!factors.containsKey(i)) {
                    factors.put(i , 0);
                }
                factors.replace(i , factors.get(i) + 1);
                n /= i;
            }
        }
        if (n > 2) {
            if(!factors.containsKey(n)) {
                factors.put(n , 0);
            }
            factors.replace(n , factors.get(n) + 1);
        }

    }

    static int lower_bound(int a[] , int val , int low , int high) {
        int res = high + 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(a[mid] >= val) {
                high = mid - 1;
                res = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return res;
    }

    static int upper_bound(int a[] , int val , int low , int high) {
        int res = high + 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(a[mid] > val) {
                high = mid - 1;
                res = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return res;
    }

    static boolean[] sieveOfEratosthenes(int n)
    {
        boolean isPrime[] = new boolean[n+1];
        Arrays.fill(isPrime , true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= n ; i++)
        {
            for(int j = 2 * i ; j <= n; j += i)
                isPrime[j] = false;
        }
        return isPrime;
    }

    static char val(int x) {
        //x == 0  ? 'z' :
        return (char)(x + 'a');
    }

    static void addToMap(Map<Integer , Integer> map , int x , int fr) {
        if(map.containsKey(x)) {
            map.replace(x , map.get(x) + fr);
        }
        else {
            map.put(x , fr);
        }
    }

    static void removeFromMap(Map<Integer , Integer> map , int x) {
        map.replace(x , map.get(x) - 1);
        if(map.get(x) == 0) {
            map.remove(x);
        }
    }

    static String currentDirectory = "C:\\Users\\ANSHIKA\\IdeaProjects\\java dsa\\src\\javaLab\\lab11";

    public static void main(String[] args) throws IOException {
        String path = currentDirectory + "\\" + "abcd.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        System.out.println(br.readLine());
    }
}
/*

2
10
5 2 3 4 5 4 5 6 7 8
4
4 4 4 4

 */
