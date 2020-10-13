package algorithms.dp;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class KnightProbability {
    static double knightProbability(int n , int x , int y , int K) {
        double dp[][][] = new double[K+1][n+4][n+4];
        for(int i = 2 ; i < n + 2 ; i++) {
            for(int j = 2 ; j < n + 2 ; j++) {
                dp[0][i][j] = 1f;
            }
        }
        for(int k = 0 ; k <= K ; k++) {
            for(int i = 0 ; i < n + 4 ; i++) {
                for(int j = 0 ; j < n + 4 ; j++) {
                    if(i <= 1 || i > n + 1 || j <= 1 || j > n + 1)
                        dp[k][i][j] = 0f;
                }
            }
        }
        for(int k = 1 ; k <= K ; k++) {
            for(int i = 2 ; i < n + 2 ; i++) {
                for(int j = 2 ; j < n + 2 ; j++) {
                    dp[k][i][j] += 0.125 * dp[k-1][i-2][j+1];
                    dp[k][i][j] += 0.125 * dp[k-1][i-2][j-1];
                    dp[k][i][j] += 0.125 * dp[k-1][i+2][j-1];
                    dp[k][i][j] += 0.125 * dp[k-1][i+2][j+1];
                    dp[k][i][j] += 0.125 * dp[k-1][i+1][j-2];
                    dp[k][i][j] += 0.125 * dp[k-1][i-1][j-2];
                    dp[k][i][j] += 0.125 * dp[k-1][i-1][j+2];
                    dp[k][i][j] += 0.125 * dp[k-1][i+1][j+2];
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return dp[K][x+2][y+2];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int k = sc.nextInt();
            DecimalFormat df = new DecimalFormat("#.000000");
            String val = df.format(knightProbability(n , x , y , k));
            if(!val.equals("1.000000")) {
                val = "0" + val;
            }
            System.out.println(val);
        }
    }

    static double round(double val) {
        int intVal = 0;
        val = val * 1e6;
        if(val % 0.1d > 5) {
            val = val + 1;
            intVal = (int)val;
        }
        else if(val % 0.1d < 5) {
            intVal = (int)val;
        }
        else {
            if(val % 10 % 2 == 1) {
                val = val + 1;
                intVal = (int)val;
            }
        }
        val = intVal /= 1e6;
        return val;
    }

}
/*
22 2 3 4
 */