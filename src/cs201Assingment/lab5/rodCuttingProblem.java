package cs201Assingment.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rodCuttingProblem {
    static double withRecursion(double cost[] , int len) {
        if(len == 1) {
            return cost[1];
        }
        double totalCost = cost[len];
        for(int i = 1 ; i <= len - 1 ; i++) {
            totalCost = Math.max(totalCost , cost[i] + withRecursion(cost , len - i));
        }
        return totalCost;
    }

    static double topDown(double cost[] , int len , double[] mem) {
        if(mem[len] != -1) {
            return mem[len];
        }
        if(len == 1) {
            mem[len] = cost[1];
            return cost[1];
        }
        double totalCost = cost[len];
        for(int i = 1 ; i <= len - 1 ; i++) {
            double curCost = topDown(cost , len - i , mem);
            totalCost = Math.max(totalCost , cost[i] + curCost);
        }
        mem[len] = totalCost;
        return totalCost;
    }

    static double bottomUp(double cost[] , int n) {
        double dp[] = new double[n+1];
        for(int i = 1 ; i <= n ; i++) {
            double maxCost = cost[i];
            for(int j = i - 1 ; j > 0 ; j--) {
                maxCost = Math.max(maxCost , cost[i-j] + dp[j]);
            }
            dp[i] = maxCost;
        }
        return dp[n];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        double a[] = new double[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println("By reccursion -> ");
        System.out.println(withRecursion(a , n));

        System.out.println("By topDown -> ");
        double mem[] = new double[n+1];
        Arrays.fill(mem , -1);
        System.out.println(topDown(a , n , mem));

        System.out.println("By BottomUp -> ");
        System.out.println(bottomUp(a , n));

    }

}
/*

5
1 3 2 4 4

 */