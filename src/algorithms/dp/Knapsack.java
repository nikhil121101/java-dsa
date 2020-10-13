package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Knapsack {
    static void KnapSack(int weight[] , int val[] , int W , StringBuilder stringBuilder) {
        int n = weight.length;
        int dp[][] = new int[W + 1][n + 1];
        for(int i = 1 ; i < W + 1 ; i++) {
            for(int j = 1 ; j < n + 1 ; j++) {
                if(i >= weight[j-1]) {
                    dp[i][j] = Math.max(dp[i - weight[j-1]][j-1] + val[j-1], dp[i][j-1]);
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        stringBuilder.append(dp[W][n]).append("\n");
    }

    static void KnapSackUnbounded(int weight[] , int val[] , int W , StringBuilder stringBuilder) {
        int n = weight.length;
        int dp[][] = new int[W + 1][n + 1];
        for(int i = 1 ; i < W + 1 ; i++) {
            for(int j = 1 ; j < n + 1 ; j++) {
                if(i >= weight[j-1]) {
                    dp[i][j] = Math.max(dp[i - weight[j-1]][j] + val[j-1], dp[i][j-1]);
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        stringBuilder.append(dp[W][n]).append("\n");
    }





    public static void main (String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder("");
        int t = Integer.parseInt(bufferedReader.readLine());
        while(t-- != 0) {
            String s[] = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int W = Integer.parseInt(s[1]);
            int val[] = new int[n];
            int weight[] = new int[n];
            s = bufferedReader.readLine().split(" ");
            for(int i = 0 ; i < n ; i++) {
                val[i] = Integer.parseInt(s[i]);
            }
            s = bufferedReader.readLine().split(" ");
            for(int i = 0 ; i < n ; i++) {
                weight [i] = Integer.parseInt(s[i]);
            }
            KnapSackUnbounded(weight , val , W , stringBuilder);
        }
        System.out.println(stringBuilder.toString());
    }
}
