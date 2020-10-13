package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MatrixChainMult {

    public static int rec(int a[] , int i , int j) {
        int min = Integer.MAX_VALUE;
        if(j <= i) {
            min = 0;
        }
        else {
            for (int k = i; k <= j - 1; k++) {
                int res = rec(a, i, k) + rec(a, k+1 , j) + a[i - 1] * a[k] * a[j];
                if (res < min) {
                    min = res;
                }
            }
        }
        return min;
    }

    public static int bottomUp(int a[] , int n) {
        // n - > number of matrices
        //array length is n = 1
        int dp[][][] = new int[n+1][n+1][n+1];
        for(int l = 2 ; l <= n ; l++) {
            for(int i = 1 ; i <= n - l + 1 ; i++) {
                int j = i + l - 1;
                dp[l][i][j] = Integer.MAX_VALUE;
                for(int k = i ; k < j ; k++) {
                    dp[l][i][j] = Math.min(dp[l][i][j] , dp[k-i+1][i][k] + dp[j-k][k+1][j] + a[i-1] * a[k] * a[j]);
                }
            }
        }
        return dp[n][1][n];
    }
    public static String extendedBottomUp(int a[] , int n) {
        // n - > number of matrices
        //array length is n = 1
        int dp[][][] = new int[n+1][n+1][n+1];
        String dpString[][][] = new String[n+1][n+1][n+1];
        for(int i = 1; i <= n ; i++) {
            dpString[1][i][i] = "(" + a[i-1] + "*" + a[i] + ")";
        }
        for(int l = 2 ; l <= n ; l++) {
            for(int i = 1 ; i <= n - l + 1 ; i++) {
                int j = i + l - 1;
                dp[l][i][j] = Integer.MAX_VALUE;
                for(int k = i ; k < j ; k++) {
                    if(dp[l][i][j] > dp[k-i+1][i][k] + dp[j-k][k+1][j] + a[i-1] * a[k] * a[j]) {
                        dp[l][i][j] = dp[k-i+1][i][k] + dp[j-k][k+1][j] + a[i-1] * a[k] * a[j];
                        dpString[l][i][j] = "(" + dpString[k-i+1][i][k] + "*" + dpString[j-k][k+1][j] + ")";
                    }
                }
            }
        }

        return dpString[n][1][n];
    }

    public static int topDown(int a[] , int i , int j , int dp[][]) {
        int min = Integer.MAX_VALUE;
        //System.out.println("low -> " + i + " high -> " + j);
        if(j <= i) {
            min = 0;
        }
        else {
            for (int k = i; k <= j - 1; k++) {
                //System.out.println("i = " + k);
                int temp1 , temp2 , temp;
                if(dp[i][k] == -1) {
                    temp1 = topDown(a, i, k , dp);
                    dp[i][k] = temp1;
                }
                else {
                    temp1 = dp[i][k];
                }
                if(dp[k+1][j] == -1) {
                    temp2 = topDown(a, k+1 , j , dp);
                    dp[k+1][j] = temp2;
                }
                else {
                    temp2 = dp[k+1][j];
                }
                temp = temp1 + temp2 + a[i - 1] * a[k] * a[j];
                //System.out.println("low -> " + i + " high -> " + j + "temp -> " + temp);
                if (temp < min) {
                    min = temp;
                }
            }
        }
        return min;
    }

    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        int dp[][] = new int[101][101];
        StringBuilder stringBuilder = new StringBuilder("");
        while(t-- != 0) {
            for(int i = 0 ; i < 101 ; i++) {
                Arrays.fill(dp[i]  , -1);
            }
            int n = Integer.parseInt(bf.readLine());
            int a[] = new int[n];
            String s[] = bf.readLine().split(" ");
            for(int i = 0 ; i < n ; i++) {
                a[i] = Integer.parseInt(s[i]);
            }
            stringBuilder.append("rec-> ").append("\n");
            stringBuilder.append(rec(a , 1 , n-1)).append("\n");
            stringBuilder.append("topDown -> ").append("\n");
            stringBuilder.append(topDown(a , 1 , n-1 , dp)).append("\n");
            stringBuilder.append("bottomUp -> ").append("\n");
            stringBuilder.append(bottomUp(a , n-1)).append("\n");
            stringBuilder.append("extended bottomUp -> ").append("\n");
            stringBuilder.append(extendedBottomUp(a , n-1)).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

}
/*
2
5
1 2 3 4 5
3
3 3 3

38
27
 */