package algorithms.dp;
import java.util.*;
import java.lang.*;
import java.io.*;
public class MaximizeCutsegments {

    public static void main (String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine().trim());
        while(t-- != 0) {
            int n = Integer.parseInt(bf.readLine().trim());
            int[] cuts = new int[3];
            String[] s = bf.readLine().trim().split(" ");
            for(int i = 0 ; i < 3 ; i++) {
                cuts[i] = Integer.parseInt(s[i]);
            }
            System.out.println(Code(n , cuts));
        }
    }

    static boolean isPossible(boolean[] dpPossible , int i , int[] cuts) {
        if(i == 0) {
            return true;
        }
        else if(i < cuts[0]) {
            return false;
        }
        boolean res = false;
        for(int j = 0 ; j < 2 ; j++) {
            if(i - cuts[j] >= 0) {
                res = res || dpPossible[i-cuts[j]];
            }
        }
        if(i == 100) {
            System.out.println(res);
        }
        return res;
    }

    static int Code(int n , int[] cuts) {

        Arrays.sort(cuts);
        int[] dp = new int[n+1];
        boolean[] dpPossible = new boolean[n+1];
        Arrays.fill(dpPossible , false);
        dpPossible[0] = true;

        for(int i = cuts[0] ; i < n + 1 && isPossible(dpPossible , i , cuts) ; i++) {
            if(i >= cuts[1] && i >= cuts[2]) {
                    dp[i] = 1 + Math.max(Math.max(dp[i-cuts[0]] , dp[i-cuts[1]]) , dp[i-cuts[2]]);
                    dpPossible[i] = true;
            }
            else if(i >= cuts[1]) {
                    dp[i] = 1 + Math.max(dp[i - cuts[0]], dp[i - cuts[1]]);
                    dpPossible[i] = true;
            }
            else {
                    dp[i] = 1 + dp[i - cuts[0]];
                    dpPossible[i] = true;
            }
        }

        //System.out.println(isPossible(dpPossible , 5 , cuts));
        System.out.println(Arrays.toString(dpPossible));
        for(int i = 0 ; i  < n+1 ; i++) {
            System.out.println(i + " - " + dpPossible[i]);
        }

        return dp[n];

    }

}
/*

2
4
2 1 1
5
5 3 2

4
2

 */