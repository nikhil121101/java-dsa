package algorithms.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class subsetSum {
    static HashSet<Integer> subset = new HashSet<>();
    static List<Set<Integer>> res = new ArrayList<>();
    static int n;
    static void bruteForce(int[] set , int sum , int at) {
        if(sum == 0) {
            res.add(subset);
            return;
        }
        if(at == n) {
            return;
        }
        if(set[at] <= sum) {
            subset.add(set[at]);
            bruteForce(set , sum - set[at] , at + 1);
        }
        subset.remove(set[at]);
        bruteForce(set , sum , at + 1);
    }

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int s = Integer.parseInt(br.readLine());
        bruteForce(a , s , 0);
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