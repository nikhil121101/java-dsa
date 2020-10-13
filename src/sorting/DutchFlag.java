package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DutchFlag {
    private static void swap(int arr[] , int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void sort(int a[] , int n) {
        // we want to divide the array in three half
        // left - 0's
        // centre - 1's
        // right - 2's

        int start = 0;      // start represents the index where next zero is to be placed
        int end = n - 1;    // end represents the index where next two is to be placed

        int i = 0; // i pointer iterates over array until high is hit

        while(i < end) {
            if(a[i] == 0) {
                // if current num is 0 push it to left side and move i ahead
                swap(a , i , start);
                start++;
                i++;
            }
            else if(a[i] == 1) {
                // if current num is 1 dont push it either of the left or right side and move i ahead
                i++;
            }
            else {
                // if current num is 2 push it to right side
                swap(a, i, end);
                end--;
            }
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int m = (int)1e9 + 7;
    static int MAX = 500005;

    static int[] inputArray(int n) throws IOException {
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    static long modExpo(long x , long y , int mod){
        long res = 1;
        for(long i = 0 ; i < y ; i++){
            res = res * x % mod;
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

    public static void main(String[] args) throws IOException {
        System.out.println("---Enter no of elements---");
        int n = Integer.parseInt(br.readLine());
        int a[] = new int[n];
        System.out.println("---Enter elements---");
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println("---unsorted array---");
        System.out.println(Arrays.toString(a));

        sort(a , n);

        System.out.println("---sorted array---");
        System.out.println(Arrays.toString(a));


    }
}
