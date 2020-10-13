import java.io.*;
import java.util.*;



public class Main {

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

    static long modulus(long x , long m) {
        long d = x / m;
        return x - d * m;
    }

    static boolean isTriangle(int a , int b , int c) {
        return a + b > c && a + c > b && c + b > a;
    }

    private static void swap(int arr[] , int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static ArrayList<ArrayList<Integer>> permutation(int n) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(n == 1) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(1);
            res.add(temp);
            return res;
        }
        ArrayList<ArrayList<Integer>> prev = permutation(n-1);
        //System.out.println("n- " + n + " prev size " + prev.size());
        for(ArrayList<Integer> list : prev) {
            //System.out.println("n " + n);
            //System.out.println("list - " + list);
            for(int i = 0 ; i < n ; i++) {
                ArrayList<Integer> temp = new ArrayList<>(list);
                temp.add(i, n);
                //System.out.println("temp - " + temp);
                res.add(temp);
            }
        }
        return res;
    }

    public static int reversePairs(int[] a) {
        int n = a.length;
        if(n == 0) {
            return 0;
        }
        int[] sorted = new int[n];
        int res =  mergeSort(a , sorted , 0 , n - 1);
        return res;
    }

    public static int mergeSort(int a[], int[] sorted, int start, int end) {
        if(start == end) {
            sorted[start] = a[start];
            return 0;
        }
        int inv = 0;
        int mid = (start + end) / 2;
        inv += mergeSort(a , sorted , start , mid);
        inv += mergeSort(a , sorted , mid + 1 , end);
        inv += merge(a , sorted , start , mid , end);
        return inv;
    }

    static int merge(int a[], int[] sorted, int start, int mid, int end) {

        int i = start , j = mid + 1 , k = start;
        while(i <= mid && j <= end) {
            if(a[i] <= a[j]) {
                sorted[k] = a[i];
                k++;
                i++;
            }
            else {
                sorted[k] = a[j];
                k++;
                j++;
            }
        }
        while(i <= mid) {
            sorted[k] = a[i];
            i++;
            k++;
        }
        while(j <= end) {
            sorted[k] = a[j];
            j++;
            k++;
        }

        // counting inversions

        int inv = 0;
        i = start;
        j = mid + 1;
        while(i <= mid && j <= end) {
            if(a[i] <= 2 * (long)a[j]) {
                i++;
            }
            else {
                inv += mid - i + 1;
                j++;
            }
        }

        // counting inversion end here
        for(int itr = start ; itr <= end ; itr++) {
            a[itr] = sorted[itr];
        }
        return inv;
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

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            System.out.println(rec(x1 , y1 , x2 , y2 , 6));
        }
    }

    private static int rec(int x1, int y1, int x2, int y2, int i) {
        if(i == 0) {
            return Integer.MAX_VALUE;
        }
        if(x1 == x2 && y1 == y2) {
            return 0;
        }
        int res = Math.min(rec(x1 + 2*y1 , y1 , x2 , y2 , i-1) , rec(x1 , y1 + 2*x1 , x2 , y2 , i-1));
        if(y1 + 2*x1 > 0) {
            res = Math.min(res , rec(x1 , y1 + 2*x1 , x2 , y2 , i-1));
        }
        else {
            res = Math.min(res , rec(-x1 , -(y1 + 2*x1) , x2 , y2 , i-1));
        }
        if(y1 - 2*x1 > 0) {
            res = Math.min(res , rec(x1 , y1 - 2 * x1 , x2 , y2 , i-1));
        }
        else {
            res = Math.min(res , rec(-x1 , -(y1 - 2 * x1) , x2 , y2 , i-1));
        }
        return res + 1;
    }
}
/*
100 100 10 10
23 45 12 78 34 67 24 88 44 22
32 5 22 68 54 97 42 8 49 20
 */