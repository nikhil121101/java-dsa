package codeChefApril;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MayP4 {

    static  boolean isInRange(int a , int l , int r) {
        //System.out.println("a -> " + a);
        if(a >= l && a <= r) {
            //System.out.println("true");
            return true;
        }
        return false;
    }

    static String dotBinaryString(int x , int y) {
        String bx = Integer.toBinaryString(x);
        String by = Integer.toBinaryString(y);
        if(bx.length() > by.length()) {
            for (int i = 0; i < bx.length() - by.length(); i++) {
                by = "0" + by;
            }
        }
        if(bx.length() < by.length()) {
            for (int i = 0; i < by.length() - bx.length(); i++) {
                bx = "0" + bx;
            }
        }
        int n = bx.length();
        String res = "";
        for(int i = 0 ; i < n ; i++) {
            res += ((int)bx.charAt(i) - 48)*((int)by.charAt(i) - 48);
        }
        return res;
    }

    static String xor(String s1 , String s2) {
        String res = "";
        for(int i = 0 ; i < s1.length() ; i++) {
            res += ((int)s1.charAt(i) - 48)^((int)s2.charAt(i) - 48);
        }
        return res;
    }

    static void Code(int x , int y , int l , int r , StringBuilder stringBuilder) {

        String orignal = dotBinaryString(x , y);
        String z = new String(orignal);
        String temp = "";
        System.out.println(z);
        for(int i = 0 ; i < z.length() ; i++) {
            if(z.charAt(i) == '0') {
                temp = z.substring(0 , i) + "1" + z.substring(i+1);
                //System.out.println(temp);
                if(isInRange(Integer.parseInt(temp , 2) , l , r)) {
                    z = new String(temp);
                }
            }
            else {
                temp = z.substring(0 , i) + "0" + z.substring(i+1);
                //System.out.println(temp);
                if(isInRange(Integer.parseInt(temp , 2) , l , r)) {
                    z = new String(temp);
                }
            }
        }
        //System.out.println(z);
        stringBuilder.append(Integer.parseInt(xor(orignal , z) , 2)).append("\n");
    }

    public static void main(String args[]) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder stringBuilder = new StringBuilder("");
//        int t = Integer.parseInt(bufferedReader.readLine());
//        while(t-- != 0) {
//            String[] s = bufferedReader.readLine().split(" ");
//            int x = Integer.parseInt(s[0]);
//            int y = Integer.parseInt(s[1]);
//            int l = Integer.parseInt(s[2]);
//            int r = Integer.parseInt(s[3]);
//            Code(x , y , l , r , stringBuilder);
//        }
        Integer.parseInt("1");
    }
}
/*
input
2
7 12 4 17
7 12 0 8

output
15
7
 */