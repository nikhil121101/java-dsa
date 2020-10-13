package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class longestRepeatingSubSeq {

    static int longestRepeatingSubSeq(String s) {
        int n = s.length();
        int dp[][] = new int[n+1][n+1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(s.charAt(i-1) == s.charAt(j-1) && i != j) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }
        return dp[n][n];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        System.out.println(longestRepeatingSubSeq(s));
    }

}
