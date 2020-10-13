package algorithms.maths.pejionHole;

import java.util.*;
import java.lang.*;
import java.io.*;

public class DivisibleSubset {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder("");
        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            int a[] = new int[n];
            for(int i = 0 ; i < n ; i++) {
                a[i] = sc.nextInt();
            }
            Code(a , n , stringBuilder);
        }
        System.out.println(stringBuilder.toString());

    }

    static void Code(int a[] , int n , StringBuilder stringBulider) {
        int s = 0 ;
        int start = -1 , end  = -1;
        int sumArr[] = new int[n];
        for(int i =0 ; i < n ; i++) {
            if(a[i] % n == 0) {
                stringBulider.append(1 + "\n" + (i + 1) + "\n");
                return;
            }
            s += a[i];
            sumArr[i] = s % n;
        }
        HashMap<Integer , Integer> hashMap = new HashMap<>();
        for(int i = 0; i < n ; i++) {
            if(sumArr[i] == 0) {
                start = 0;
                end = i;
            }
            if(hashMap.containsKey(sumArr[i])) {
                start = hashMap.get(sumArr[i]) + 1;
                end = i;
                break;
            }
            else {
                hashMap.put(sumArr[i] , i);
            }
        }
        stringBulider.append((end - start + 1) + "\n");
        for(int i = start ; i <= end ; i++) {
            stringBulider.append((i + 1) + " ");
        }
        stringBulider.append("\n");
    }
}