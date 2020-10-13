package algorithms.dp;

import java.util.Arrays;

public class TargetSum {

    public static void main(String args[]) {
        int a[] = {1 , 1 , 1 , 1 , 1};
        System.out.println(findTargetSumWays(a , 3));
    }

    public static int subsetSumCount(int a[] , int s) {
        int n = a.length;
        int dp[][] = new int[s+1][n+1];
        Arrays.fill(dp[0] , 1);
        for(int i = 1 ; i <= s ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(i >= a[j - 1]) {
                    dp[i][j] = dp[i - a[j - 1]][j - 1] + dp[i][j - 1];
                }
                else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s][n];
    }

    public static int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n ; i++) {
            sum += nums[i];
        }
        if((sum + S) % 2 != 0) {
            return 0;
        }
        else {
            return subsetSumCount(nums , (sum + S) / 2 );
        }
    }

}
