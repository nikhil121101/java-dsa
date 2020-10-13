package algorithms.dp;

import java.util.*;
        import java.lang.*;
        import java.io.*;

public class MinCoins {
    static int minCoins(int sum , int[] coins , int n) {
        Arrays.sort(coins);
        //System.out.println(Arrays.toString(coins));
        int dp[][] = new int[sum + 1][n + 1];
        for(int i = 1 ; i <= sum ; i++) {
            Arrays.fill(dp[i] , (int)Math.pow(10 , 6) + 1);
        }
        Arrays.fill(dp[0] , 0);
        dp[0][0] = (int)Math.pow(10 , 6) + 1;
        for(int i = 1 ; i <= sum ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(i >= coins[j-1]) {
                    dp[i][j] = Math.min(dp[i - coins[j-1]][j] , dp[i][j-1]) + 1;
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        for(int i = 0 ; i <= sum ; i++) {
            for(int  j = 0  ; j <= n ; j++) {
                System.out.print(dp[i][j] + "  ");
            }
            System.out.println();
        }
        return dp[sum][n];
    }
    public static void main (String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            String s[] = bf.readLine().split(" ");
            int sum = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int coins[] = new int[n];
            s = bf.readLine().split(" ");
            for(int i = 0 ;  i < n ; i++) {
                coins[i] = Integer.parseInt(s[i]);
            }
            System.out.println(minCoins(sum , coins , n));
        }
    }
}

/*
7540 46
13 96 38 43 17 31 53 85 59 87 22 100 12 24 63 79 36 93 73 14 34 54 3 95 46 15 40 88 58 81 99 51 35 11 41 55 42 97 10 77 48 16 44 76 18 84

*/