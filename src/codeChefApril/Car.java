package codeChefApril;

import java.util.Arrays;
import java.util.Scanner;

public class Car {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		   int t =0;
		if(sc.hasNextInt()) {
		   t = sc.nextInt();
		}
		String res = "" ;
		while(t-- != 0) {
		    int n = 0;
		    if(sc.hasNextInt()) {
			    n = sc.nextInt();
		    }
			int a[] = new int[n];
			
			for(int i = 0 ; i < n ; i++ ) {
			    if(sc.hasNextInt()) {
				    a[i] = sc.nextInt();
			    }
			}
			Arrays.sort(a);		
			//System.out.println(Arrays.toString(a));
			int profit = 0;
			int m = (int)Math.pow(10 , 9) + 7 ;
			for(int i = n-1 ; i >= 0 ; i--) {
				int value = a[i] !=0 ? (a[i] - (n-i-1)) : 0 ;
				//System.out.println("value -> " + value);
				profit =( profit + value ) % m;
			}
			res += profit + "\n";
		}
		System.out.println(res);

	}

}
