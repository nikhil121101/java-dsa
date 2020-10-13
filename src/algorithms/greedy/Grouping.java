package algorithms.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Grouping {
    public static void main (String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder("");
        int t = Integer.parseInt(bufferedReader.readLine());
        int a[] = new int[1000000];
        while(t-- != 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            String[] s = bufferedReader.readLine().split(" ");
            for(int  i = 0 ; i < n ; i++) {
                a[i] = Integer.parseInt(s[i]);
            }
            //GroupChilderen(a , n , stringBuilder);
        }
        System.out.println(stringBuilder.toString());
    }



}
