package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GoldMines {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            String s[] = br.readLine().split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            s = br.readLine().split(" ");
            int grid[][] = new int[m][n];
            int k = 0;
            for(int i =  0 ; i < m ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    grid[i][j] = Integer.parseInt(s[k++]);
                }
            }
            //System.out.println(Arrays.deepToString(grid));
            System.out.println(Code(grid , m , n));
        }
    }
    static int Code(int grid[][] , int m , int n) {
        int result = 0;
        int dp[][] = new int[m][n];
        for(int j = 0 ; j < n ; j++) {
            for(int i = 0 ; i < m ; i++) {
                if(j == 0) {
                    dp[i][j] = grid[i][j];
                }
                else if(i != 0 && i != n-1) {
                    dp[i][j] =
                            Math.max(Math.max(dp[i-1][j-1] + grid[i][j],
                                    dp[i][j-1] + grid[i][j]),
                                    dp[i+1][j-1] + grid[i][j]);
                }
                else if(i == 0) {
                    dp[i][j] =
                            Math.max(dp[i][j-1] + grid[i][j],
                                    dp[i + 1][j-1] + grid[i][j]);
                }
                else  {
                    dp[i][j] =
                            Math.max(dp[i-1][j-1] + grid[i][j],
                                    dp[i][j-1] + grid[i][j]);
                }
                if(j == n-1) {
                    if(dp[i][j] > result) {
                        result = dp[i][j];
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return result;
    }

}