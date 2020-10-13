package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class shortestCommonSuperSeq {

    static String shortestComSupSeq(String s1 , String s2) {
        StringBuilder res = new StringBuilder();
        int n1 = s1.length();
        int n2 = s2.length();
        int dp[][] = new int[n1+1][n2+1];
        longestSS(s1 , s2 , dp);
//        for(int i = 1 ; i <= n1 ; i++) {
//            for(int j = 1 ; j <= n2 ; j++) {
//                System.out.print("(" + s1.charAt(i-1) + "," + s2.charAt(j-1) + ")-" + dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        int i = n1;
        int j = n2;
        while(i > 0 || j > 0) {
            if(i == 0) {
                res.append(s2.charAt(j-1));
                j--;
            }
            else if(j == 0) {
                res.append(s1.charAt(i-1));
                i--;
            }
            else if(s1.charAt(i-1) == s2.charAt(j-1)) {
                //System.out.println("At dp[" + i + "][" + j + "]");
                //System.out.println("s1.charAt(" + (i-1) + ") == s2.charAt(" + (j-1) + ")");
                //System.out.println("so appending -> " + s1.charAt(i-1) + " and i-- , j-- ");
                res.append(s1.charAt(i - 1));
                i--;
                j--;
            }
            else {
               if(dp[i-1][j] > dp[i][j-1]) {
                   //System.out.println("dp[" + (i-1) + "][" + j + "] > dp[" + i + "][" + (j-1) + "]");
                   //System.out.println("so appending -> " + s1.charAt(i-1) + " and i-- ");
                   res.append(s1.charAt(i-1));
                   i--;
               }
               else {
                   //System.out.println("dp[" + (i-1) + "][" + j + "] <= dp[" + i + "][" + (j-1) + "]");
                   //System.out.println("so appending -> " + s2.charAt(j-1) + " and j-- ");
                   res.append(s2.charAt(j-1));
                   j--;
               }
            }
        }
        return res.reverse().toString();
    }

    static void longestSS(String s1 , String s2 , int dp[][]) {
        int n1 = s1.length();
        int n2 = s2.length();
        for(int i = 1 ; i < n1 +1 ; i++) {
            for(int j = 1 ; j < n2 +1 ; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Integer.max(dp[i][j - 1] , dp[i - 1][j]);
                }
            }
        }
    }

    public static  void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s1 = bf.readLine();
        String s2 = bf.readLine();
        System.out.println(shortestComSupSeq(s1 , s2));
    }

}
/*

AGGTAB
GXTXAYB

AGGXTXAYB

*/