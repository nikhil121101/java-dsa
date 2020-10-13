package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestSubsequence {

    static String max(String s1  ,String s2) {
        if(s1.length() >= s2.length()) {
            return s1;
        }
        else {
            return s2;
        }
    }
    static String longestSubSequence(String s1 , String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        String dp[][] = new String[n1+1][n2+1];
        for(int i = 0  ; i <= n1 ; i++) {
            dp[i][0] = "";
        }
        Arrays.fill(dp[0] , "");
        for(int i = 1 ; i < n1 + 1 ; i++) {
            for(int j = 1 ; j < n2 + 1 ; j++) {
                //System.out.println("i -> " + i + " j -> " + j);
                //System.out.println("s1.charAt("+(i - 1)+") -> " + s1.charAt(i - 1) + " s2.charAt("+(j - 1)+") -> " + s2.charAt(j - 1));
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    //System.out.println("dp["+i+"]["+j+"] = " + dp[i][j] + " dp["+(i-1)+"]["+(j-1)+"] -> " + dp[i-1][j-1]);
                    dp[i][j] = s1.charAt(i - 1) + dp[i-1][j-1];
                    //System.out.println("dp["+i+"]["+j+"] becomes -> " + dp[i][j]);
                }
                else {
                    //System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j] + " dp[" + (i-1) + "][" + j +"] -> " + dp[i-1][j] + " dp["+i+"]["+(j-1)+"] -> " + dp[i][j-1]);
                    dp[i][j] = max(dp[i][j - 1] , dp[i - 1][j]);
                    //System.out.println("dp["+i+"]["+j+"] becomes -> " + dp[i][j]);
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return dp[n1][n2];
    }
    public static int longestSubstring(String s1 , String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        int max = 0;
        for(int i = 1 ; i <= n1 ; i++) {
            for(int j = 1 ; j <= n2 ; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = 0;
                }
                if(dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }



    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            String[] s = bf.readLine().split(" ");
            int l1 = Integer.parseInt(s[0]);
            int l2 = Integer.parseInt(s[1]);
            String s1 = bf.readLine();
            String s2 = bf.readLine();
            sb.append(longestSubSequence(s1, s2)).append("\n");
        }
        System.out.println(sb.toString());
    }
}
/*
1
4 5
nijh
mklhj
 */