package algorithms.divide_and_conquer;

import java.util.Scanner;

public class Karastuba {
    public static long multiply(long x, long y) {
        //System.out.println("x -> " + x + " y -> " + y);
        if(x <= 10) {
            return x*y;
        }
        int len = (int)Math.log10(x) + 1;
        long pow = (long) Math.pow(10 , len / 2);
        //System.out.println(" len - " + len + " , pow - " + pow);
        long a = multiply(x/pow , y/pow);
        //System.out.println("a - " + a);
        long b = multiply(x % pow , y % pow);
        //System.out.println("b - " + b);
        long e =  multiply((x / pow + x % pow) , (y / pow + y % pow)) - a - b;
        long res = a * pow * pow + e * pow + b;
        //System.out.println("res - " + res);
        return res;
    }

    public static void main (String[] args)
    {
        System.out.println(multiply(1234 , 4321));
    }
}
