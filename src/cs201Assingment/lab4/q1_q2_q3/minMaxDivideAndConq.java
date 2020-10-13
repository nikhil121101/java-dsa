package cs201Assingment.lab4.q1_q2_q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class minMaxDivideAndConq {
    static int[] findMinMax(int a[] , int i , int j) {
        if(i == j) {
            return new int[]{a[j] , a[j]};
        }
        int mid = (i+j) / 2;
        int[] res = new int[2];
        res[0] = Math.min(findMinMax(a , i , mid)[0] , findMinMax(a , mid + 1 , j)[0]);
        res[1] = Math.max(findMinMax(a , i , mid)[1] , findMinMax(a , mid + 1 , j)[1]);
        return res;
    }
    static int findMax(int a[] , int i , int j) {
        if(i == j) {
            return a[j];
        }
        int mid = (i+j) / 2;
        return Math.max(findMax(a , i , mid) , findMax(a , mid + 1 , j));
    }


    static void polynomialProd(int a[] , int b[] , int[] res , int s1 , int e1 , int s2 , int e2) {
        if(s1 == e1 || s2 == e2) {
            termination(a , b , res , s1 , s2 , e1 , e2);
            return;
        }
        int m1 = (s1 + e1) / 2 , m2 = (s2 + e2) / 2;
        polynomialProd(a , b , res , s1 , m1 , s2 , m2);
        polynomialProd(a , b , res , s1 , m1 , m2+1 , e2);
        polynomialProd(a , b , res , m1+1 , e1 , s2 , m2);
        polynomialProd(a , b , res , m1+1 , e1 , m2+1 , e2);
    }

    static void termination(int[] a, int[] b, int[] res, int s1, int s2, int e1, int e2) {
        if(s1 == e1 && s2 == e2) {
            // both having size equal to 1
            res[s1 + s2] += a[s1] * b[s2];
            return;
        }
        if(s1 == e1) {
            // one with size 1 but other with size 2
            res[s1 + s2] += a[s1] * b[s2];
            res[s1 + e2] += a[s1] * b[e2];
            return;
        }
        // one with size 1 but other with size 2
        res[s1 + s2] += a[s1] * b[s2];
        res[e1 + s2] += a[e1] * b[s2];
    }

    static void printPolynomial(int a[]) {
        for(int i = 0 ; i < a.length ; i++) {
            System.out.print(a[i] + ".x^"+ i + " + ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = new int[]{5 , 6 , 2 , 7 , 6 , 3};
        int n = 6;
        int res[] = findMinMax(arr , 0 , n-1);
        System.out.println("min - " + res[0] + " max - " + res[1]);
        n = 6;
        // ith index represent coefficient of x^i
        int a[] = new int[]{1 , 1 , 1 , 7 , 3 , 6};
        System.out.println("a -> ");
        printPolynomial(a);
        int b[] = new int[]{2 , 3 , 2 , 3 , 4 , 5};
        System.out.println("a -> ");
        printPolynomial(a);
        res = new int[2*n - 1];
        polynomialProd(a , b , res , 0 , n-1 , 0 , n-1);
        System.out.println("a*b");
        //printPolynomial(res);
        System.out.println(Arrays.toString(res));
    }

}
