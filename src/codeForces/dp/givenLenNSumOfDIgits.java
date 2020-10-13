package codeForces.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class givenLenNSumOfDIgits {

//    static int Code(int m , int n) {
//        int dp[][] = new int[m+1][n+1];
//        Arrays.fill(dp[0] , -1);
//        for(int i = 1 ; i <= m ; i++) {
//            dp[i][0] = -1;
//        }
//        for(int i = 1 ; i <= m ; i++) {
//            for(int j = 1 ; j <= n ; j++) {
//                int min = Integer.MAX_VALUE;
//                for(int k = 0 ; k <= 9 && j >= k ; k++) {
//                    min = Math.min(min , dp[i-1][j-k]);
//                }
//                dp[i][j] = min * 10
//            }
//        }
//        return dp[m][n];
//    }

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s[] = bufferedReader.readLine().split(" ");
        int m = Integer.parseInt(s[0]);
        int n = Integer.parseInt(s[1]);
        //String res = Code(m , n);
        //System.out.print(res + " ");
        //StringBuilder stringBuilder = new StringBuilder(res);
        //System.out.print(stringBuilder.reverse().toString());
        System.out.println(Integer.parseInt(""));
    }

}
