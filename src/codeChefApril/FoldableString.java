package codeChefApril;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FoldableString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            sb.append(Code(s , n , k)).append("\n");
        }
        System.out.print(sb.toString());
    }

    static String Code(String s , int n , int k) {
        if(n % k != 0) {
            return "IMPOSSIBLE";
        }
        int folds = n / k;
        int zeros = 0 , ones = 0;
        for(int i = 0 ; i < n ; i++)
        {
            if(s.charAt(i) == '0') {
                zeros++;
            }
            else {
                ones++;
            }
        }
        if(zeros % folds != 0 || ones % folds != 0) {
            return "IMPOSSIBLE";
        }
        int zeroPerFold = zeros / folds , onePerFold = ones / folds;
        StringBuilder res = new StringBuilder("");
        for(int i = 0 ; i < folds ; i++) {
            if(i % 2 == 0) {
                for(int j = 0 ; j < zeroPerFold ; j++) {
                    res.append("0");
                }
                for(int j = 0 ; j < onePerFold ; j++) {
                    res.append("1");
                }
            }
            else {
                for(int j = 0 ; j < onePerFold ; j++) {
                    res.append("1");
                }
                for(int j = 0 ; j < zeroPerFold ; j++) {
                    res.append("0");
                }
            }
        }
        return res.toString();
    }
}
