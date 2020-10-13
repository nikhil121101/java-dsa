package codeForces.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class berSUballsCodeForces {

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(bufferedReader.readLine());
        String s[] = bufferedReader.readLine().split(" ");
        int boys[] = new int[m];
        for(int i = 0 ; i < m ; i++) {
            boys[i] = Integer.parseInt(s[i]);
        }
        int n = Integer.parseInt(bufferedReader.readLine());
        s = bufferedReader.readLine().split(" ");
        int girls[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            girls[i] = Integer.parseInt(s[i]);
        }
        boolean booked[] = new boolean[m];
        Arrays.fill(booked , false);
        System.out.println(Code(boys , m ,girls , n));
    }

    static int Code(int boys[] , int m , int girls[] , int n) {
        Arrays.sort(boys);
        Arrays.sort(girls);
        int dp[][] = new int[m+1][n+1];
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(Math.abs(boys[i-1] - girls[j-1]) <= 1) {
                    dp[i][j] = Math.max(1 + dp[i-1][j-1] , Math.max(dp[i][j-1] , dp[i-1][j]));
                }
                else {
                    dp[i][j] = Math.max(dp[i][j-1] , dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }

}

/*

k = 4
12 34 53 56 46 35 77
12 34 35 46 53 56 77
16  -  -  -  - 70 73




 */