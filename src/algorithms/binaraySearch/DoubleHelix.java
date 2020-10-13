package algorithms.binaraySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class DoubleHelix {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n1 = sc.nextInt();
            if(n1 == 0) {
                break;
            }
            int a[] = new int[n1];
            for(int i = 1 ; i <= n1 ; i++) {
                a[i-1] = sc.nextInt();
            }
            int n2 = sc.nextInt();
            int b[] = new int[n2];
            for(int i = 1 ; i <= n2 ; i++) {
                b[i-1] = sc.nextInt();
            }
            System.out.println(Code(a , n1 ,  b , n2));
        }
    }
    static int Code(int[] a, int n1 , int b[] , int n2) {
        int[] s1 = new int[n1] , s2 = new int[n2] ;
        int sum = 0;
        for(int i = 0 ; i < n1 ; i++) {
            sum += a[i];
            s1[i] = sum;
        }
        sum = 0;
        for(int i = 0 ; i < n2 ; i++) {
            sum += b[i];
            s2[i] = sum;
        }
        sum = 0;
        int prevMatched1 = 0;
        int prevMatched2 = 0;
        //System.out.println(Arrays.toString(a));
        //System.out.println(Arrays.toString(b));
        for(int i = 0 ; i < n1; i++) {
            int temp = Arrays.binarySearch(b , a[i]);
            if(temp >= 0) {
                int max = Math.max(s1[i] - prevMatched1, s2[temp] - prevMatched2);
                //System.out.println(max);
                sum += max;
                prevMatched1 = s1[i];
                prevMatched2 = s2[temp];
            }
        }
        int max = Math.max(s1[n1 - 1] - prevMatched1, s2[n2 - 1] - prevMatched2);
        //System.out.println(max);
        sum += max;
        return sum;
    }
}
/*
13 3 5 7 9 20 25 30 40 55 56 57 60 62
11 1 4 7 11 14 25 44 47 55 57 100
4 -5 100 1000 1005
3 -12 1000 1001
0

450
2100
 */