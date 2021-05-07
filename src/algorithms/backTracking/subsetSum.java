package algorithms.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class subsetSum {
    static List<Set<Integer>> res = new ArrayList<>();
    static int n;
    static int[] a;

    static void backtracking(boolean[] set , int i , int s) {
        //System.out.println(Arrays.toString(set) + " " + i + " " + s);
        if(s == 0) {
            HashSet<Integer> tempSet = new HashSet<>();
            for(int j = 0 ; j < n ; j++) {
                if(set[j]) {
                    tempSet.add(j);
                }
            }
            res.add(tempSet);
            //System.out.println(tempSet);
            return;
        }
        if(i == n) {
            return;
        }
        if(a[i] <= s) {
            set[i] = true;
            backtracking(set , i + 1 , s - a[i]);
            set[i] = false;
        }
        backtracking(set , i + 1 , s);
    }

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int s = Integer.parseInt(br.readLine());
        backtracking(new boolean[n] , 0 , s);
        for(Set<Integer> set : res) {
            System.out.println(set);
        }
    }

}
/*
10
1 2 3 4 5 6 7 8 9 10
7
 */