package interviewBit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Slope {

    int num;
    int denom;

    public Slope(int x, int y) {
        this.num = x;
        this.denom = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slope)) return false;
        Slope key = (Slope) o;
        return num == key.num && denom == key.denom;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + denom;
        return result;
    }

}

public class ColinearPoints {

    static int gcd(int a , int b) {
        if(a % b == 0) {
            return b;
        }
        return gcd(b , a % b);
    }

    static  void printHashMap(HashMap<Slope , Integer> hashMap) {
        for(Map.Entry<Slope , Integer> map : hashMap.entrySet()) {
            Slope slope = map.getKey();
            System.out.print("(" + slope.num + " , " + slope.denom + ") - " + map.getValue() + " , ");
        }
        System.out.println();
    }

    static int colinearPoints(ArrayList<Integer> X , ArrayList<Integer> Y) {
        int result = 0;
        HashMap<Slope, Integer> hashMap = new HashMap<>();
        int n = X.size();
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                int y = Y.get(j) - Y.get(i);
                int x = X.get(j) - X.get(i);
                if(y == 0 && x == 0) {
                    continue;
                }
                int g = gcd(x , y);
                //System.out.println("gcd(" + x + " " + y + ") = " + g);
                int num = Math.abs(y / g);
                int denom = Math.abs(x / g);
                Slope slope = new Slope(num , denom);
                if(hashMap.containsKey(slope)) {
                    hashMap.replace(slope , hashMap.get(slope) + 1);
                }
                else {
                    hashMap.put(slope , 1);
                }
            }
            //printHashMap(hashMap);
            int maxOccured = 0;
            for(Map.Entry<Slope , Integer> map : hashMap.entrySet()) {
                if(map.getValue() > maxOccured) {
                    maxOccured = map.getValue();
                }
            }
//            System.out.println(maxOccured);
            if(maxOccured > result) {
                result = maxOccured;
            }
            hashMap.clear();
        }
        return result + 1 ;
    }

    public static void main(String args[]) throws IOException
    {
        //System.out.println(gcd(0 , 1));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        while(t-- != 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            ArrayList<Integer> X = new ArrayList<Integer>();
            ArrayList<Integer> Y = new ArrayList<Integer>();
            String s[];
            for(int i = 0 ; i < n ; i++) {
                s = bufferedReader.readLine().split(" ");
                X.add(Integer.parseInt(s[0]));
                Y.add(Integer.parseInt(s[1]));
            }
            //System.out.println(X);
            //System.out.println(Y);
            System.out.println(colinearPoints(X , Y));
        }
    }
}
/*
1
5
1 0
2 1
3 2
4 4
-8 -8

3
 */