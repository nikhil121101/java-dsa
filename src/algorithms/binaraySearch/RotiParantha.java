package algorithms.binaraySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class RotiParantha {

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            int p = Integer.parseInt(bf.readLine());
            String[] s = bf.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int a[] = new int[n];
            for(int i = 1 ; i <= n ; i++) {
                a[i-1] = Integer.parseInt(s[i]);
            }
            System.out.println(Code(a , n , p));
        }
    }

    static int parathaCount(int time , int rank) {
        int parath = 0;
        int i = 1;
        int curTime = 0;
        while(true) {
            curTime += rank * i;
            if(curTime > time) {
                break;
            }
            parath++;
            i++;
        }
        return parath;
    }

    static int Code(int a[] , int n , int p) {
        //System.out.println(Arrays.toString(a) + " " + n + " " + p);
        int minTime = 0;
        int maxRank = 0;
        for(int i = 0 ; i < n ; i++) {
            if(a[i] > maxRank) {
                maxRank = a[i];
            }
        }
        int maxTime = p * (p + 1) / 2 * maxRank;
        //System.out.println(maxRank + " " + maxTime);
        int low = 0;
        int high = maxTime;
        int mid;
        while(low <= high) {
            mid = (low + high) / 2;
            int parath = 0;
            for(int i = 0 ; i < n ; i++) {
                parath += parathaCount(mid , a[i]);
            }
            if(parath >= p) {
                minTime = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return minTime;
    }


}
