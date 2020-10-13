package codeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class c1p1 {

    static boolean granniesPossible(int a[] , int n , int high , int present , int temp) {
//        System.out.println("low -> " + low + " " + a[low] + ", high -> " + high + " " + a[high] + " present -> " + present);
        for(int i = temp ; i <= high ; i++) {
            if(a[i] > present) {
                return false;
            }
        }
        return true;
    }
    static int Code(int a[] , int n) {
        Arrays.sort(a);
        int i = 0;
        int temp = 0;
        int maxGrannies = 1;
        while(i <= n-1) {
            boolean tempgrannies = granniesPossible(a , n , i , i + 1 , temp);
            //System.out.println("mid -> " + i + " tempGrannies -> " + tempgrannies);
            if(tempgrannies) {
                maxGrannies = i + 2;
                temp = i;
            }
            i++;
        }
        return maxGrannies;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            String s[] = bf.readLine().split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);

            System.out.println(n / 2 * m + m/2);
        }
    }
}
