package hashCode;

import java.io.*;
import java.util.*;

public class Code {
    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(String s) {
            try {
                br = new BufferedReader(new FileReader("C:\\Users\\ANSHIKA\\Downloads\\f.txt"));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public FastReader() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("C:\\Users\\ANSHIKA\\Downloads\\f.txt"));
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

        float nextFloat() {
            return Float.parseFloat(nextToken());
        }
    }


    static FastReader f;

    static {
        try {
            f = new FastReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder("");
    private static int mod = 998244353;
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
        if(a == 0 || b == 0) {
            return Math.max(a , b);
        }
        //System.out.println("a - " + a + " b - " + b);
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

    static long longModulus(long x , long m) {
        if(x < m) {
            return x;
        }
        long d = x / m;
        return x - d * m;
    }


    static boolean[] sieveOfEratosthenes(int n)
    {
        boolean[] isPrime = new boolean[n+1];
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

    static long moduloInversePrime(long a) {
        //System.out.println("modulo inverse of " + a + " -> " + ans);
        return modPow(a , mod - 2);
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

    static class Edge {
        int v,w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static class Car implements Comparator<Car> {
        int car, time;
        Car(int car, int time) {
            this.car = car;
            this.time = time;
        }

        Car() {

        }

        @Override
        public String toString() {
            return "(car : " + car + " , " + time + ")";
        }

        @Override
        public int compare(Car o1, Car o2) {
            return o1.time - o2.time;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        int D = f.nextInt();
        int I = f.nextInt();
        int S = f.nextInt();
        int V = f.nextInt();
        int F = f.nextInt();
        System.setOut(new PrintStream("C:\\Users\\ANSHIKA\\Desktop\\d_res.txt"));
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < I ; i++) {
            adj.add(new ArrayList<>());
        }

        HashMap<Integer, String> name = new HashMap<>();
        HashMap<String, Integer> index = new HashMap<>();


        int[] streetTime = new int[S];

        for(int i = 0 ; i < S ; i++) {
            int u = f.nextInt(), v = f.nextInt();
            String n = f.nextToken();
            name.put(i, n);
            index.put(n,i);
            int e = f.nextInt();
            streetTime[i] = e;
            adj.get(v).add(i);
        }

        ArrayList<ArrayList<Integer>> cars = new ArrayList<>();

        for(int i = 0 ; i < V ; i++) {
            int P = f.nextInt();
            cars.add(new ArrayList<Integer>());
            for(int j = 0 ; j < P ; j++) {
                String n = f.nextToken();
                cars.get(i).add(index.get(n));
            }
        }

        ArrayList<ArrayList<Car>> streetBuffer = new ArrayList<>();
        for(int i = 0 ; i < S ; i++) {
            streetBuffer.add(new ArrayList<>());
        }

        for(int i = 0 ; i < V ; i++) {
            int time = 0;
            for(int j : cars.get(i)) {
                streetBuffer.get(j).add(new Car(i, time));
                time += streetTime[j];
            }

        }

        for(int i = 0 ; i < S ; i++) {
            streetBuffer.get(i).sort(new Car());
        }

//        System.out.println(streetBuffer);

        System.out.println(I);

        for(int i = 0 ; i < I ; i++) {

            int busyRoads = 0;
            int startTime = Integer.MAX_VALUE , endTime = Integer.MIN_VALUE;
            for(int incoming : adj.get(i)) {

                if(streetBuffer.get(incoming).size() > 0) {
                    busyRoads++;
                    startTime = Math.min(startTime , streetBuffer.get(incoming).get(0).time);
                    endTime = Math.max(endTime , streetBuffer.get(incoming).get(streetBuffer.get(incoming).size()-1).time);
                }
            }

            System.out.println();

            if(busyRoads == 0) {
                continue;
            }
            int timeLength = endTime - startTime;
            int sliceTime = timeLength / busyRoads;
            int curStartTime = startTime;
            int curEndTime = startTime + sliceTime;
            int noOfSlices = timeLength / sliceTime;


            int[][] carCountPerSlice = new int[noOfSlices][adj.get(i).size()];
            for(int j = 0 ; j < adj.get(i).size() ; j++) {
                int incoming = adj.get(i).get(j);
                for(Car car : streetBuffer.get(incoming)) {
                    carCountPerSlice[car.time/sliceTime][j]++;
                }
            }

            System.out.println("carCountPerSlice");
            System.out.println(Arrays.deepToString(carCountPerSlice));


            int[] maxCar = new int[noOfSlices];
            for(int j = 0 ; j < noOfSlices ; j++) {
                int max = 0, maxInd = -1;
                for(int k = 0 ; k < adj.get(i).size() ; k++) {
                    if(carCountPerSlice[j][k] > max) {
                        max = carCountPerSlice[j][k];
                        maxInd = k;
                    }
                }
                maxCar[j] = maxInd;
            }

            System.out.println("maxCar");
            System.out.println(Arrays.toString(maxCar));

        }

    }



}
