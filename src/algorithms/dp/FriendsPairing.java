package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class FriendsPairing {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        while(t-- != 0) {
            int n = Integer.parseInt(bf.readLine());
            System.out.println(Code(n));
        }
    }

    static int Code(int n) {
        int dp[] = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2 ; i < n ; i++) {
            dp[i] = dp[i - 1] + (i - 1) * dp[i-2];
        }
        return dp[n-1];
    }
}
