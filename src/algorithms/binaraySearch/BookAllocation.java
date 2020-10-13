package algorithms.binaraySearch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class BookAllocation {

    public static void main (String args[]) {
        int b = 26 ;
        int a[] = {97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24};
        int n = a.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < n ; i++) {
            list.add(a[i]);
        }
        System.out.println(books(list , b));

    }

    public static boolean ifOptimal(ArrayList<Integer> C , int A ,  int mid) {
        int tempSum = 0;
        int n = C.size();
        for(int i = 0 ; i < n && A != 0 ; i++) {
            if(C.get(i) > mid) {
                return false;
            }
            tempSum += C.get(i);
            if(tempSum > mid) {
                A--;
                tempSum = C.get(i);
            }
        }
        if(A == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public static int books(ArrayList<Integer> C , int A) {
        int maxLength = 0;
        int n = C.size();
        int totalBoardLen = 0;
        for(int i = 0 ; i < n ; i++) {
            totalBoardLen += C.get(i);
        }
        int low  = 0;
        int high = totalBoardLen;
        int mid;
        while(low <= high) {
            mid = (low + high) / 2;
            //System.out.println("low " + low + " high " + high + " mid " + mid);
            if(ifOptimal(C , A , mid)) {
                high = mid - 1;
                maxLength = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return maxLength;
    }

}
