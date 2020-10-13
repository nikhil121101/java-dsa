package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SolutionminCostTickets {
    public int mincostTickets(int[] days, int[] cost) {
        int dayCount[] = new int[3];
        dayCount[0] = 1;
        dayCount[1] = 7;
        dayCount[2] = 30;
        int n = days.length;
        int dp[] = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            int minCost = Integer.MAX_VALUE;
            for(int k = 0 ; k < 3 && days[i] - dayCount[k] + 1 >= days[0] ; k++) {
                int expiry = days[i] - dayCount[k] + 1;
                int j = i;
                while(days[j-1] >= expiry) {
                    j--;
                }
                if(dp[j] + cost[k] < minCost) {
                    minCost = dp[j] + cost[k];
                }
            }
            dp[i] = minCost;
        }
        return dp[n];
    }
}

public class minflightTickets {

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s[] = bf.readLine().split(" ");
        int days[] = new int[s.length];
        for(int i = 0 ; i < s.length ; i++) {
            days[i] = Integer.parseInt(s[i]);
        }
        s = bf.readLine().split(" ");
        int cost[] = new int[3];
        for(int i = 0 ; i < 3 ; i++) {
            cost[i] = Integer.parseInt(s[i]);
        }
        System.out.println(new SolutionminCostTickets().mincostTickets(days , cost));
    }

}
