package algorithms.gcd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class primeProductsSegmented {
    public static void main (String[] args) throws IOException {
        int m = (int)1e9 + 7;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");
        int t = Integer.parseInt(br.readLine());

        ArrayList<Integer> primes = sieve();

        while(t-- != 0) {
            st = new StringTokenizer(br.readLine());
            long l = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
            boolean[] isPrime = new boolean[(int)(r-l+1)];
            Arrays.fill(isPrime , true);
            for(int p : primes) {
                long i = Math.max((long)Math.ceil(l / (double)p) , 2) * p;
                while(i <= r) {
                    isPrime[(int)(i-l)] = false;
                    i += p;
                }
            }
            long prod = 1;
            int i = 0;
            for(boolean b : isPrime) {
                if(b) {
                    prod = prod * (i + l) % m;
                }
                i++;
            }
            sb.append(prod).append("\n");
        }
        System.out.print(sb);
    }

    static ArrayList<Integer> sieve() {
        int n = (int)1e5;
        boolean[] isPrime = sieveOfEratosthenes(n);
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        for(int i = 3 ; i <= n ; i += 2) {
            if(isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    static boolean[] sieveOfEratosthenes(int n)
    {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime , true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= n ; i++)
        {
            if(!isPrime[i]) {
                continue;
            }
            for(int j = 2 * i ; j <= n; j += i)
                isPrime[j] = false;
        }
        return isPrime;
    }
}
/*
2
1 10
1 20
 */