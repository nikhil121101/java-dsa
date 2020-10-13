import java.awt.desktop.AboutHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Template {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");

    boolean[] sieveOfEratosthenes(int n)
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

    ArrayList<Set<Integer>> primeFactorization(int n) {
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

    static long[] inputArray(int n) throws IOException {
        long a[] = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        return a;
    }

    static int search(int numbers[] , int target) {
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

    public int findLeastNumOfUniqueInts(int[] a, int k) {
        int n = a.length;
        HashMap<Integer , Integer> freq = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            if(freq.containsKey(a[i])) {
                freq.replace(a[i] , freq.get(a[i]) + 1);
            }
            else {
                freq.put(a[i] , 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer , Integer> map : freq.entrySet()) {
            list.add(map.getValue());
        }
        Collections.sort(list);
        int i = 0;
        while(k > 0) {
            k -= list.get(i);
            i++;
        }
        return n-i;
    }


}
/*
10 5
1 2 3 4 5
5 3 2 3 5 3 1 2 3 2
 */