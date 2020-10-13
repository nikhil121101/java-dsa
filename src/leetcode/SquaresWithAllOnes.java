package leetcode;

import java.util.Arrays;
public class SquaresWithAllOnes {

    private static class Solution {
        public int countSquares(int[][] a) {
            int m = a.length;
            int n = a[0].length;
            int dp[][] = new int[m+1][n+1];
            for(int i = 1 ; i <= m ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    if(a[i-1][j-1] == 1) {
                        dp[i][j] = Math.min(dp[i-1][j] , Math.min(dp[i][j-1] , dp[i-1][j-1])) + 1;
                    }
                }
            }
            //System.out.println(Arrays.deepToString(dp));
            int res = 0;
            for(int i = 1 ; i <= m ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    res += dp[i][j];
                }
            }
            return res;
        }
    }


    public static void main(String args[]) {
     Solution solution = new Solution();
     int a[][] = { {0,1,1,1} , {1,1,1,1} , {0,1,1,1}};
     System.out.println(solution.countSquares(a));
 }

}
