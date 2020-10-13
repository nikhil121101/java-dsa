package algorithms.maths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Summation_n_to_the_power_k {
    private static final int m = (int)1e9 + 7;
    static long[] fact;
    static int MAX = 500005;

    static long summation(int n , int k) {
        long[] sum = new long[k + 1];
        sum[0] = n;
        long tempRes = 0;
        //(n+1)^k - 1 =
        // for(i in [1 , n])
        //   kCi * sum[n^(k-i)]
        // sum[n^(k-1)] = ((n+1)^k - 1 - sum(.....)) / n
        for(int j = 1 ; j <= k + 1 ; j++) {
            long s = 0;
            for(int i = 2 ; i <= j ; i++) {
                s = (s + combination(j , i) * sum[j-i] % m ) % m;
            }
            tempRes = (modPow(n + 1 , k) - 1 - s + m ) % m;
            sum[j-1] = moduloInversePrime(j) * tempRes % m;
        }
        return sum[k];
    }

    static long combination(int n , int i) {
        //System.out.println(fact[n] + " " + fact[i] + " " + fact[n-i]);
        long res = mult(mult(fact[n] , moduloInversePrime(fact[n-i])) , moduloInversePrime(fact[i]));
        //System.out.println("combination of (" + n + "," + i + ") -> " + res);
        return res;
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

    public static void main(String args[]) throws IOException {
        fact = new long[500005];
        fact[0] = 1;
        for (int i = 1; i < MAX ; i++) {
            fact[i] = fact[i-1] * i % m;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(summation(n , k));
    }


}
