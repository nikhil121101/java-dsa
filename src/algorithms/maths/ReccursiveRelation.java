package algorithms.maths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class ReccursiveRelation {

    public static void Code(int b[] , int c[] , int k , int n , StringBuilder stringBuilder) {
        BigInteger f1[][] =new BigInteger[k][1];

        for(int i = 0 ; i < k ; i++) {
            f1[i][0] = BigInteger.valueOf(b[i]);
        }

        BigInteger trans[][] = new BigInteger[k][k];

        for(int i = 0; i< k ; i++) {
            for(int j = 0; j < k ; j++) {
                if(i == k - 1) {
                    trans[i][j] = BigInteger.valueOf(c[k - j -1]);
                } else if(j == i + 1) {
                    trans[i][j] = BigInteger.valueOf(1);
                }
            }
        }
        BigInteger z = mult(pow(trans , n-1) , f1)[0][0];
        stringBuilder.append(z.toString());
        stringBuilder.append("\n");

    }

    static BigInteger[][] mult(BigInteger a[][] , BigInteger b[][]) {
        int m1 = a.length;
        int m2 = b.length;
        int n1 = a[0].length;
        int n2 = b[0].length;
        BigInteger result[][] = new BigInteger[m1][n2];
        for(int i = 0; i < m1 ; i++) {
            for(int j = 0 ; j < n2 ; j++) {
                BigInteger s = new BigInteger("0");
                for(int k = 0 ; k < n1 ; k++) {
                    s = s.add(a[i][k].multiply(b[k][j]));
                }
                result[i][j] = s;
            }
        }
        return result;
    }

    static BigInteger[][] pow(BigInteger a[][] , int k) {
        if(k == 1) {
            return a;
        }
        if(k % 2 == 1) {
            return mult(pow(a , k-1) , a);
        }
        BigInteger x[][] = pow(a , k/2);
        return mult(x , x);
    }

    public static void main (String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder("");
        int t = Integer.parseInt(bufferedReader.readLine().trim());
        while(t-- != 0) {
            int k = Integer.parseInt(bufferedReader.readLine().trim());
            String s[] = bufferedReader.readLine().trim().split(" ");
            int b[] = new int[k];
            for(int i = 0 ; i < k ; i++) {
                b[i] = Integer.parseInt(s[i]);
            }
            s = bufferedReader.readLine().trim().split(" ");
            int c[] = new int[k];
            for(int i = 0 ; i < k ; i++) {
                c[i] = Integer.parseInt(s[i]);
            }
            int n = Integer.parseInt(bufferedReader.readLine().trim());
            Code(b , c , k , n , stringBuilder);
        }
        System.out.println(stringBuilder.toString());

    }
}
