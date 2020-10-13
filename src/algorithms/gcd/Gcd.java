package algorithms.gcd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gcd {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder("");
	private static int m = (int)1e9 + 7;
	static long[] fact;
	static int MAX = 500005;

	static int gcd(int a , int b) {
		if(a % b == 0) {
			return b;
		}
		return gcd(b , a % b);
	}

	static int[] inputArray() throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		return a;
	}

	static long[][] inputPoints(int n) throws IOException {
		long[][] a = new long[n][3];
		for(int i = 0 ; i < n ;i++) {
			st = new StringTokenizer(br.readLine());
			a[i][0] = Long.parseLong(st.nextToken());
			a[i][1] = Long.parseLong(st.nextToken());
			a[i][2] = i;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		int a[] = new int[]{5 , 3 ,55 , 34, 6};
		System.out.println(Arrays.binarySearch(a , 5));
		System.out.println(Arrays.binarySearch(a , 3));
		System.out.println(Arrays.binarySearch(a , 55));
		System.out.println(Arrays.binarySearch(a , 34));
		System.out.println(Arrays.binarySearch(a , 6));
		System.out.println(Arrays.toString(a));
	}

	static long combination(int n , int i) {
		//System.out.println(fact[n] + " " + fact[i] + " " + fact[n-i]);
		long res = mult(mult(fact[n] , moduloInversePrime(fact[n-i])) , moduloInversePrime(fact[i]));
		//System.out.println("combination of (" + n + "," + i + ") -> " + res);
		return res;
	}

	static long extendEuclidHelper(long a , long b , long[] ans) {
		if (b == 0) {
			ans[0] = 0;
			ans[1] = 1;
			return a;
		}
		long[] tempAns = new long[2];
		long d = extendEuclidHelper(b , a % b , tempAns);
		ans[0] = tempAns[1] - tempAns[0] * (a / b);
		ans[1] = tempAns[0];
		return d;
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

	static long moduloInverseComposite(long a) {
		long[] res = new long[2];
		extendEuclidHelper(a , m , res);
		return res[0];
	}

}
