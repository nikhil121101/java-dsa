package algorithms.maths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class longestPalindrome {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder("");
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        s = new String[n];
        for(int i = 0 ; i < n ; i++) {
            s[i] = bufferedReader.readLine();
        }
        Code(s , n , m , stringBuilder);
        System.out.println(stringBuilder);
    }

    static void Code(String[] words , int n , int m ,StringBuilder stringBuilder) {
        HashMap<String , Stack<Integer>> hashMap = new HashMap<>();
        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> end = new ArrayList<Integer>();
        String self = "";
        for(int i = 0 ; i  < n ; i++) {
            StringBuilder s = new StringBuilder(words[i]);
            String rev = s.reverse().toString();
            if(hashMap.containsKey(rev)) {
                start.add(hashMap.get(rev).pop());
                if(hashMap.get(rev).isEmpty()) {
                    hashMap.remove(rev);
                }
                end.add(i);
            }
            else if(hashMap.containsKey(words[i])) {
                hashMap.get(words[i]).push(i);
            }
            else {
                hashMap.put(words[i] , new Stack<Integer>());
                hashMap.get(words[i]).push(i);
            }
        }
        for(Map.Entry<String , Stack<Integer>> map : hashMap.entrySet()) {
            StringBuilder s = new StringBuilder(map.getKey());
            String rev = s.reverse().toString();
            if(map.getKey().equals(rev)) {
                self = map.getKey();
                break;
            }
        }
        StringBuilder res = new StringBuilder("");
        for(int i = 0 ; i < start.size() ; i++) {
            res.append(words[start.get(i)]);
        }
        res.append(self);
        for(int i = start.size() -1 ; i >= 0 ; i--) {
            res.append(words[end.get(i)]);
        }
        stringBuilder.append(res.length() + "\n");
        stringBuilder.append(res.toString());
    }

}
