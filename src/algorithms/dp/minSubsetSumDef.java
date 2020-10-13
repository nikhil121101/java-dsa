package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class minSubsetSumDef {
    public static void main (String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int t = Integer.parseInt(br.readLine());
//        while(t-- != 0) {
//            int n = Integer.parseInt(br.readLine());
//            int a[] = new int[n];
//            String s[] = br.readLine().split(" ");
//            for(int i = 0 ; i < n ; i++) {
//                a[i] = Integer.parseInt(s[i]);
//            }
//            System.out.println(minDef(a , n));
//        }
        int a[] = new int[]{1 , 4 , 3 , 2 , 5};
        System.out.println(Arrays.deepToString(subsetSumCount(a , 5)));
    }

    public static boolean[][] subsetSum(int a[] , int s) {
        int n = a.length;
        boolean dp[][] = new boolean[s+1][n+1];
        for(int i = 0 ; i < s + 1 ; i++) {
            Arrays.fill(dp[i] , i == 0);
        }
        for(int i = 1 ; i <= s ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(i >= a[j - 1]) {
                    dp[i][j] = dp[i - a[j - 1]][j - 1] || dp[i][j - 1];
                }
                else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp;
    }

    public static int[][] subsetSumCount(int a[] , int s) {
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
        return dp;
    }

    public static int minDef(int a[] , int n) {
        int maxSum = 0;
        for(int i = 0 ; i < n; i++) {
            maxSum += a[i];
        }
        int high = maxSum / 2;
        int low = 0;
        boolean dp[][] = new boolean[high + 1][n + 1];
        dp = subsetSum(a , high);
//        for(int i = 0 ; i < high + 1 ; i++) {
//            System.out.println(i + " -> " + Arrays.toString(dp[i]));
//        }
        for(int i = high ; i >= low ; i--) {
            if(dp[i][n]) {
                return maxSum - 2 * i;
            }
        }
        return -1;
    }
}
