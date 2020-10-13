package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class burstBaloons {

    public static void main(String args[]) throws IOException {
        System.out.println((1e9 + 7));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().trim().split(" ");
        int n = s.length;
        int a[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        System.out.println(new SolutionBB().maxCoins(a));
    }

}

class SolutionBB {

    int maxCoinsHelper(int nums[] , int low , int high , int dp[][]) {

        if(low > high) {
            return 0;
        }
        int coins = 0 ;
        int maxCoins = 0;;
        for(int k = low ; k <= high ; k++) {
            //burst k
            int left = low - 1 >= 0 ? nums[low - 1] : 1;
            int right = high + 1 < nums.length ? nums[high + 1] : 1;
            coins += left * nums[k] * right;
            if(k - 1 >= 0 && dp[low][k-1] != -1) {
                coins += dp[low][k-1];
            }
            else {
                coins += maxCoinsHelper(nums , low , k-1 , dp);
            }
            if(k + 1 < nums.length && dp[k+1][high] != -1) {
                coins += dp[k+1][high];
            }
            else {
                coins += maxCoinsHelper(nums , k+1 , high , dp);
            }
            //System.out.println("low - " + low  + " high - " + high + " k - " + k + " coins - " + coins);
            if(coins > maxCoins) {
                maxCoins = coins;
            }
            coins = 0;
        }
        dp[low][high] = maxCoins;
        return maxCoins;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][n];
        for(int i = 0 ; i < nums.length ; i++) {
            Arrays.fill(dp[i] , -1);
        }
        return maxCoinsHelper(nums , 0 , nums.length - 1 , dp);
    }
}