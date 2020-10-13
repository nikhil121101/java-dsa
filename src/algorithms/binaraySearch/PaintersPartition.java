package algorithms.binaraySearch;

import java.math.BigInteger;
import java.util.ArrayList;

public class PaintersPartition {
    public static void main (String args[]) {
        int b = 1000000 ;
        int arr[] = {1000000 , 1000000};
        int a = 1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < 2 ; i++) {
            list.add(arr[i]);
        }
        System.out.println(paint( a , b , list));

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

    public static int paint(int A, int B, ArrayList<Integer> C) {
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
        BigInteger bigInteger = new BigInteger(String.valueOf(maxLength));
        bigInteger = bigInteger.multiply(new BigInteger(String.valueOf(B)));
        return bigInteger.mod(new BigInteger((String.valueOf(10000003)))).intValue();
    }
}
