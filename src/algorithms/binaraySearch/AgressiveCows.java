package algorithms.binaraySearch;

import java.util.*;

public class AgressiveCows {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            int a[] = new int[n];
            for(int i = 0 ; i < n ; i++) {
                a[i] = sc.nextInt();
            }
            System.out.println( Code(a , n , c));
        }

    }

    static  int Code (int a[] , int  n , int c) {
        Arrays.sort(a);
        int result = 0;
        int low = 0 ;
        int high = a[n-1] - a[0] ;
        int mid = -1;
        while(low <= high) {
            mid = (low + high )/ 2;
            int i = 1;
            int prev = a[0];
            int cowsLeft = c - 1;
            while(i < n && cowsLeft > 0) {
                if(a[i] - prev >= mid) {
                    prev = a[i];
                    cowsLeft--;
                }
                i++;
            }
            if(cowsLeft == 0) {
                result = mid ;
                low = mid + 1;
            }
            else {
                high = mid -1 ;
            }
        }
        return result;
    }

}
