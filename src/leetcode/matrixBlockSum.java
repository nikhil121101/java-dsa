package leetcode;

import java.util.Arrays;

class Solution {

    public int matSum(int sum[][] , int i , int j , int k) {
        int m = sum.length - 1;
        int n = sum[0].length - 1;
        int i1 = Math.max(i - k, 1);
        int i2 = Math.min(i + k, m);
        int j1 = Math.max(j - k, 1);
        int j2 = Math.min(j + k, n);
        return sum[i2][j2] + sum[i1-1][j1-1] - sum[i1-1][j2] - sum[i2][j1-1];
    }

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = new int[m+1][n+1];
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                preSum[i][j] =
                        preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + mat[i-1][j-1];
            }
        }
        int[][] res = new int[m][n];
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n; j++) {
                res[i][j] = matSum(preSum , i+1 , j+1 , K);
            }
        }
        return res;
    }
}

public class matrixBlockSum {

    public static void main(String args[]) {
        int a[][] = {{3 , 23 , 12} , {3 , 6 ,7} , {15 , 12 , 8}};
        Solution sol = new Solution();
        System.out.println(Arrays.deepToString(sol.matrixBlockSum(a ,1)));
    }

}
