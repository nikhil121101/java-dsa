package labExam;

import java.io.*;
import java.util.*;

/*  nikhil mishra
    1901120
    nikhilmishra1211@gmail.com
    6395959716
*/

public class Mid_1901120 {
    public static void main(String[] args) throws IOException {

        // please enter the path of input file in command line

        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double[] a = new double[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = Double.parseDouble(st.nextToken());
        }
        double[][] dp = new double[n][n];
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(dp[i] , -1);
        }
        sb.append(code(a , n , 0 , n - 1 , dp)).append("\n");
        //System.out.println(sb);
        bw.write(sb.toString());
        bw.close();
    }

    static double code(double a[] , int n , int i , int j , double[][] dp) {
        //System.out.println("i: " + i + " j: " + j);
        if(i > j) {
            // empty set this is no spaceship
            return 0;
        }
        //____memoization___
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        double maxRes = 0;

        double left = i - 1 >= 0 ? a[i-1] / 2 : 0;

        double right = j + 1 < n ? a[j+1] / 2 : 0;

        for(int itr = i ; itr <= j ; itr++) {
            // if a[itr] is the last spaceship to be destroyed;
            double tempRes = (a[itr] + left + right) +
                    code(a , n , i , itr-1 , dp) +
                    code(a , n , itr + 1 , j , dp);
            maxRes = Math.max(maxRes , tempRes);
        }

        dp[i][j] = maxRes;

        return maxRes;
    }

}
