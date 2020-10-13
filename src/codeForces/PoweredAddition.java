package codeForces;
import java.util.Scanner;
 
public class PoweredAddition {
 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		int n ;
		int a[] = new int[100000];
		while(t-- != 0 ) {
			n = scanner.nextInt();
			for(int i = 0 ; i < n ; i++) {
				a[i] = scanner.nextInt();
			}
			Code(a , n);
		}
		scanner.close();
	}
	
	static void Code(int a[] , int n ) {
		int global = 0 ,local ;
		for(int i = 1 ; i < n ; i++) {
			local = 0;
			if(a[i] < a[i-1]) {
				local = Integer.toBinaryString((a[i-1]-a[i])).length();
				a[i] = a[i-1];
			}
			if(local>global) {
				global = local;
			}
		}
		System.out.println(global);
	}
 
}