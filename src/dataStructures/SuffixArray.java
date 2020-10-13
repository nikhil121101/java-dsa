package dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SuffixArray {

    static int[] suffixArray(String s) {
        s += '#';
        int n = s.length();

        int[][] a = new int[n][2];
        for(int i = 0 ; i < n ; i++) {
            a[i][0] = s.charAt(i);
            a[i][1] = i;
        }

        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] p = new int[n];
        for(int i = 0 ; i < n ;i++) {
            p[i] = a[i][1];
        }

        int[] c = new int[n];
        c[p[0]] = 0;
        for(int  i = 1 ; i < n ; i++) {
            if(a[i][0] == a[i-1][0]) {
                c[p[i]] = c[p[i-1]];
            }
            else {
                c[p[i]] = c[p[i-1]] + 1;
            }
        }

        int curSize = 1;
        suffixArrayHelper(s , n , p , c , curSize);
        return p;
    }

    static void countSort(int p[] , int c[] , int n , int curSize) {

        for(int i = 0 ; i < n ; i++) {
            p[i] = (p[i] - curSize + n) % n;
        }

        int[] cnt = new int[n];
        for(int i =  0 ; i  < n ; i++) {
            cnt[c[i]]++;
        }

        int[] pos = new int[n];
        pos[0] = 0;
        for(int i = 1 ; i < n ; i++) {
            pos[i] = pos[i-1] + cnt[i-1];
        }

        int[] sorted = new int[n];
        for(int i = 0 ; i < n ; i++) {
            int cls = c[p[i]];
            sorted[pos[cls]] = p[i];
            pos[cls]++;
        }
        for(int i = 0 ; i < n ; i++) {
            p[i] = sorted[i];
        }

    }

    static private void suffixArrayHelper(String s, int n, int[] p, int[] c, int curSize) {

        if(curSize > n) {
            return;
        }

        countSort(p , c , n , curSize);

        int[] new_c = new int[n];
        new_c[p[0]] = 0;
        for(int i = 1 ; i < n ; i++) {
            if(c[p[i]] == c[p[i-1]] && c[(p[i] + curSize) % n] == c[(p[i-1] + curSize) % n]) {
                new_c[p[i]] = new_c[p[i-1]];
            }
            else {
                new_c[p[i]] = new_c[p[i-1]] + 1;
            }
        }
        c = new_c;
        suffixArrayHelper(s , n , p, c , curSize * 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");
        String s = br.readLine();
        int t = Integer.parseInt(br.readLine());
        int[] suff = suffixArray(s);
        while (t-- != 0) {
            String p = br.readLine();
            System.out.println(containsSubst(s , p , suff) ? "Yes" : "No");
        }
    }

    static boolean containsSubst(String s , String p , int[] suff) {
        int n = s.length();
        int m = p.length();
        int low = 1 , mid = 0 , high = n;
        while(low <= high) {
            mid = (low + high) / 2;
            String substring = s.substring(suff[mid] , Math.min(suff[mid] + m , n));
            if(p.compareTo(substring) < 0) {
                high = mid - 1;
            }
            else if(p.compareTo(substring) > 0) {
                low = mid + 1;
            }
            else {
                return true;
            }
        }
        return false;
    }

}
//codeforces
//[0, 7, 2, 8, 3, 4, 5, 1, 6, 9]