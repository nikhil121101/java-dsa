package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ActivitySelection {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a[][] = new int[n][2];
            for(int i = 0 ; i < n ;  i++) {
                a[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n ;  i++) {
                a[i][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            System.out.println("rec -> ");
            System.out.println(rec(a , n , 0 , n - 1));
            System.out.println("topDown -> ");
            System.out.println(topDown(a , n , 0 , n - 1 , new ArrayList[n+1][n+1]));
        }
    }

    static ArrayList<Integer> rec(int a[][] , int n , int l , int r) {
        if(r < l || r >= n || l < 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> finalRes = new ArrayList<>();
        ArrayList<Integer> res;
        for(int i = l ; i <= r ; i++) {
            int j = i-1;
            while(j >= l && a[j][1] > a[i][0]) {
                j--;
            }
            res = new ArrayList<>(rec(a, n, l, j));
            res.add(i+1);
            j = i + 1;
            while(j <= r && a[j][0] < a[i][1]) {
                j++;
            }
            res.addAll(rec(a , n , j , r));
            if(res.size() > finalRes.size()) {
                finalRes = res;
            }
        }
        return finalRes;
    }

    static ArrayList<Integer> topDown(int a[][] , int n , int l , int r , ArrayList<Integer> dp[][]) {
        if(r < l || r >= n || l < 0) {
            return new ArrayList<>();
        }
        if(dp[l][r] != null) {
            return dp[l][r];
        }
        ArrayList<Integer> finalRes = new ArrayList<>();
        ArrayList<Integer> res;
        for(int i = l ; i <= r ; i++) {
            res = new ArrayList<>();
            int j = i-1;
            while(j >= l && a[j][1] > a[i][0]) {
                j--;
            }
            if(!(j < l || j >= n)) {
                if(dp[l][j] == null) {
                    dp[l][j] = topDown(a, n, l, j , dp);
                }
                res.addAll(dp[l][j]);
            }
            res.add(i+1);
            j = i + 1;
            while(j <= r && a[j][0] < a[i][1]) {
                j++;
            }
            if(!(r < j)) {
                if(dp[j][r] == null) {
                    dp[j][r] = topDown(a , n , j , r , dp);
                }
                res.addAll(dp[j][r]);
            }
            if(res.size() > finalRes.size()) {
                finalRes = res;
            }
        }
        dp[l][r] = finalRes;
        return finalRes;
    }

}
 /*

2
6
1 3 0 5 8 5
2 4 6 7 9 9
8
75250 50074 43659 8931 11273 27545 50879 77924
112960 114515 81825 93424 54316 35533 73383 160252

1 2 4 5
6 7 1

  */