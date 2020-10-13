package algorithms.binaraySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eka {

    public static void main (String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s[] = bf.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int a[] = new int[n];
        s = bf.readLine().split(" ");
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        System.out.println(Code(a , n , m));
    }
    static long Code(int a[] , int n , int m) {
        int maxHeight = 0;
        for(int i = 0 ; i < n ; i++) {
            if(a[i] > maxHeight) {
                maxHeight = a[i];
            }
        }
        int low = 0 , mid = 0;
        int high = maxHeight;
        long optimalHeight = 0;
        while(low <= high) {
            mid = (low + high) / 2;
            long totWood = 0;
            for(int i = 0 ; i < n ; i++) {
                if(a[i] > mid) {
                    totWood += a[i] - mid;
                }
            }
            if(totWood < m) {
                high = mid - 1;
            }
            else {
                optimalHeight = mid;
                low = mid + 1;
            }
        }
        return optimalHeight;
    }

}
