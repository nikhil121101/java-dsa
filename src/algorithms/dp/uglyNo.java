package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uglyNo {

    public static void main(String arags[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t =Integer.parseInt(bufferedReader.readLine());
        while(t-- != 0 ) {
            int n = Integer.parseInt(bufferedReader.readLine());
            System.out.println(UglyNo(n));
        }
    }

    static int UglyNo(int n) {
        int dp[] = new int[n];
        dp[0] = 1;
        int p2 = 0 , p3 = 0 , p5 = 0 , multiple_2 = 1, multiple_3 = 1, multiple_5 = 1 ;
        for(int i  = 1 ; i < n ; i++) {
            multiple_2 = dp[p2] * 2 ;
            multiple_3 = dp[p3] * 3 ;
            multiple_5 = dp[p5] * 5 ;
            //System.out.println("multiple_2 -> " + multiple_2 + " multiple_3 -> " + multiple_3 + " multiple_5 -> " + multiple_5);
            dp[i] = Math.min(Math.min(multiple_2 ,multiple_3) ,multiple_5);
            //System.out.println("dp[" + i + "] = " + dp[i]);
            if(dp[i] == multiple_2) {
                p2 = i;
            }
            if(dp[i] == multiple_3) {
                p3 = i;
            }
            if(dp[i] == multiple_5) {
                p5 = i;
            }
            //System.out.println("p2 -> " + p2 + " p3 -> " + p3 + " p5 -> " + p5);
        }
        return dp[n-1];
    }



}
