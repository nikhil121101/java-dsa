package algorithms.dp;


import java.io.*;
import java.lang.invoke.StringConcatFactory;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class SubsetSum {

    static ArrayList<Integer> subSum(int a[] , int sum) {
        int n = a.length;
        ArrayList<Integer>[][] dp = new ArrayList[sum+1][n + 1];
        for(int i = 1 ; i < sum + 1 ; i++) {
            dp[i][0] = null;
        }
        Arrays.fill(dp[0] , new ArrayList());
        for(int i  = 1 ; i < sum + 1 ; i++) {
            for(int j = 1 ; j < n + 1; j++) {
                //System.out.println("finding dp[" + i + "][" + j + "]");
                if(a[j-1] > i) {
                    //System.out.print("since " + "(a[" + (j-1) + "] = " + a[j-1] + ") > (" + "i = " + i + ")");
                    dp[i][j] = dp[i][j-1];
                    //System.out.println(" => dp[" + i + "][" + j + "]" + " = dp[" + i + "][" + (j-1) + "] = " + dp[i][j-1]);
                }
                else {
                    //System.out.println("dp[" + i + "][" + (j-1) + "] = " + dp[i][j-1]);
                    //System.out.println("dp[" + (i-a[j-1]) + "][" + (j-1) + "] = " + dp[i-a[j-1]][j-1]);
                    if(dp[i-a[j-1]][j-1] != null) {
                        dp[i][j] = (ArrayList<Integer>)dp[i - a[j-1]][j-1].clone();
                        dp[i][j].add(a[j-1]);
                    }

                    else if(dp[i][j-1] != null) {
                        dp[i][j] = (ArrayList<Integer>) dp[i][j-1].clone();
                    }
                    else {
                        dp[i][j] = null;
                    }
                    //System.out.println("dp[" + i + "][" + j + "]" + " = " + dp[i][j]);
                }
            }
        }
        return dp[sum][n];
    }

    public  static void KnapScack(int weight[] , int val[] , int sum , StringBuilder str) {
        int n = weight.length;
        int[][] dp = new int[sum+1][n + 1];
        for(int i = 1 ; i < sum + 1 ; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        int maxValue = Integer.MIN_VALUE;
        Arrays.fill(dp[0] , 0);
        for(int i  = 1 ; i < sum + 1 ; i++) {
            for(int j = 1 ; j < n + 1; j++) {
                System.out.println("finding dp[" + i + "][" + j + "]");
                if(weight[j-1] > i) {
                    System.out.print("since " + "(a[" + (j-1) + "] = " + weight[j-1] + ") > (" + "i = " + i + ")");
                    dp[i][j] = dp[i][j-1];
                    if(maxValue < dp[i][j]) {
                        maxValue = dp[i][j];
                    }
                    System.out.println(" => dp[" + i + "][" + j + "]" + " = dp[" + i + "][" + (j-1) + "] = " + dp[i][j-1]);
                }
                else {
                    System.out.println("dp[" + i + "][" + (j-1) + "] = " + dp[i][j-1]);
                    System.out.println("dp[" + (i-weight[j-1]) + "][" + (j-1) + "] = " + dp[i-weight[j-1]][j-1]);
                    int temp1 = dp[i - weight[j-1]][j-1] + val[j-1];
                    int temp2 = dp[i][j-1] ;
                        dp[i][j] = Math.max(temp1 , temp2);
                        if(dp[i][j] > maxValue) {
                            maxValue = dp[i][j];
                        }
                    System.out.println("dp[" + i + "][" + j + "]" + " = " + dp[i][j]);
                }
            }
        }
        if(maxValue != Integer.MIN_VALUE) {
            str.append(maxValue).append("\n");
        }
        else {
            str.append(0 + "\n");
        }
    }

    public static void main(String args[]) throws IOException {
//        int t = Integer.parseInt(bufferedReader.readLine());
//        while(t-- != 0) {
//            int n = Integer.parseInt(bufferedReader.readLine());
//            int W = Integer.parseInt(bufferedReader.readLine());
//            int val[] = new int[n];
//            int weight[] = new int[n];
//            String[] s = bufferedReader.readLine().split(" ");
//            for(int i = 0 ; i < n ; i++) {
//                val[i] = Integer.parseInt(s[i]);
//            }
//            s = bufferedReader.readLine().split(" ");
//            for(int i = 0 ; i < n ; i++) {
//                weight [i] = Integer.parseInt(s[i]);
//            }
//            KnapScack(weight , val , W , stringBuilder);
//        }
        int a[] = new int[]{1, 3, 2, 5, 7, 4, 6};
        System.out.println(subSum(a, 9));
    }
}
