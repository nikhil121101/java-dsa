package codeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class NextRound {
    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().trim().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        System.out.println(Code(n , k));
    }
    static int Code (int n , int m) {
        int result = 0;
        if(m > 2 * n) {
            if(m % 2 == 1) {
                while (n < (m + 1) / 2) {
                    n = n * 2;
                    result++;
                }
                result += (n - (m + 1) / 2) + 2;

            }
            else {
                while (n < m / 2) {
                    n = n * 2;
                    result++;
                }
                result += (n - m / 2) + 1;
            }
        }
        else if(m > n) {
            if(m == 2 * n) {
                result += 1;
            }
            else if(m == 2 * n -1) {
                result += 2;
            }
            else {
                if(m % 2 == 0) {
                    result += (n - m / 2) + 1;
                }
                else {
                    result += (n - (m + 1) / 2) + 2;
                }
            }
        }
        else {
            result = n - m;
        }
        return result;
    }
}
