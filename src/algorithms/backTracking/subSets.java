package algorithms.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class subSets {

    public static ArrayList<ArrayList<Integer>> allSubsets(int a[] , int start) {
        if(start == a.length) {
            ArrayList<ArrayList<Integer>> result =new ArrayList<>();
            result.add(new ArrayList<Integer>());
            return result;
        }
        ArrayList<ArrayList<Integer>> temp = allSubsets(a , start + 1);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(temp);
        Iterator<ArrayList<Integer>> iterator = temp.iterator();
        while(iterator.hasNext()) {
            ArrayList<Integer> tempSet = new ArrayList<>(iterator.next());
            tempSet.add(a[start]);
            result.add(tempSet);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        String s[] = bufferedReader.readLine().trim().split(" ");
        int a[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        ArrayList<ArrayList<Integer>> result = allSubsets(a , 0);
        System.out.println(result.size());
        for(int i = 0 ; i < result.size() ; i++) {
            System.out.print(result.get(i) + " ");
            System.out.println();
        }
    }
}