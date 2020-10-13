package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bitwiseAndOfRange {

    public static void main(String[] args) throws IOException {
        System.out.println(rangeBitwiseAnd(4 , 7));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        String bm = Integer.toBinaryString(m);
        String bn = Integer.toBinaryString(n);
        if(bm.length() < bn.length()) {
            return 0;
        }
        String res = "";
        int i ;
        for(i = 0 ; i < bm.length() ; i++) {
            if(bm.charAt(i) == bn.charAt(i)) {
                res += bm.charAt(i);
            }
            else {
                break;
            }
        }
        while(i<bm.length()) {
            res += "0";
            i++;
        }
        return Integer.parseInt(res , 2);
    }

}
