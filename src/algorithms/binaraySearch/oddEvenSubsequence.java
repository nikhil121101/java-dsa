package algorithms.binaraySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class oddEvenSubsequence {
    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder("");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(minSubseq(a , n , k));
    }
    // 1 2 3 4 5 6 7 8 9 10
    //k = 7
    //limit = 4
    //odd can exceed
    //1 2 3 4
    static boolean isValid(int a[] , int n , int k , int limit) {
        //greedy
        int curLen = 0;
        // if odd can exceed
        boolean res = false;
        for(int i = 0 ; i < n ; i++) {
            if(curLen == k) {
                break;
            }
            if(curLen % 2 == 1) {
                curLen++;
            }
            else {
                if(a[i] <= limit) {
                    curLen++;
                }
            }
        }
        res = curLen >= k;
        //if even can exceed
        curLen = 0;
        for(int i = 0 ; i < n ; i++) {
            if(curLen == k) {
                break;
            }
            if(curLen % 2 == 0) {
                curLen++;
            }
            else {
                if(a[i] <= limit) {
                    curLen++;
                }
            }
        }
        res = res || curLen == k;
        return res;
    }

    static int minSubseq(int a[] , int n , int k) {
        int high = Integer.MIN_VALUE , low = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++) {
            if(a[i] > high) {
                high = a[i];
            }
            if(a[i] < low) {
                low = a[i];
            }
        }
        int mid;
        int res = 0;
        //binary serch
        while(low <= high) {
            mid = (low + high) / 2;
            if(isValid(a , n , k , mid)) {
                res = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return res;
    }
}
