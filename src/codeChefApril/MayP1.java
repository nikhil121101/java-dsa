package codeChefApril;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MayP1 {
    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder("");
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            String[] str = bf.readLine().trim().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            str = bf.readLine().trim().split(" ");
            int a[] = new int[n];
            for(int i = 0 ; i  < n ; i++) {
                a[i] = Integer.parseInt(str[i]);
            }
            Code(a , n , k , stringBuilder);
        }
        System.out.println(stringBuilder.toString());

    }

    static void rotate(ArrayList<Integer> notFixed , ArrayList<Integer> diff , int maxRP) {
        int temp = diff.get(maxRP);
        diff.set(maxRP , diff.get(maxRP + 2) + 2);
        diff.set(maxRP + 2 , diff.get(maxRP + 1) + 1);
        diff.set(maxRP + 1 , temp);
        temp = notFixed.get(maxRP);
        notFixed.set(maxRP , notFixed.get(maxRP + 2));
        notFixed.set(maxRP + 2 , notFixed.get(maxRP + 1));
        notFixed.set(maxRP + 1 , temp);
        if(diff.get(maxRP) == 0) {
            diff.remove(maxRP);
            notFixed.remove(maxRP);
        }
        if(diff.get(maxRP + 1) == 0) {
            diff.remove(maxRP);
            notFixed.remove(maxRP);
        }
        if(diff.get(maxRP) == 0) {
            diff.remove(maxRP);
            notFixed.remove(maxRP);
        }
    }

    static void Code(int a[] , int n , int k , StringBuilder stringBuilder) {
        ArrayList<Integer> notFixed = new ArrayList<>();
        ArrayList<Integer> diff = new ArrayList<>();
        int sorted[] = a.clone();
        Arrays.sort(sorted);
        int i;
        for(i = 0 ; i < n ; i++) {
            int temp = Arrays.binarySearch(sorted , a[i]) - i;
            if(temp != 0) {
                notFixed.add(i);
                diff.add(temp);
            }
        }
        int curSize;
        while(!notFixed.isEmpty()) {
            i = 0;
            curSize = notFixed.size();
            HashMap<Integer, Integer> rotationPoint = new HashMap<>();
            Iterator<Integer> iterator = diff.iterator();
            int cur;
            while (iterator.hasNext()) {
                cur = iterator.next();
                if (cur == 1) {
                    if (i <= curSize - 3) {
                        if (rotationPoint.containsKey(i)) {
                            rotationPoint.replace(i, rotationPoint.get(i) + 1);
                        } else {
                            rotationPoint.put(i, 1);
                        }
                    }
                    if (i <= curSize - 2) {
                        if (i - 1 >= 0) {
                            if (rotationPoint.containsKey(i - 1)) {
                                rotationPoint.replace(i, rotationPoint.get(i - 1) + 1);
                            } else {
                                rotationPoint.put(i - 1, 1);
                            }
                        }
                    }
                }
                i++;
            }
            int maxRPValue = 0;
            int maxRP = -1;
            for (Map.Entry<Integer, Integer> map : rotationPoint.entrySet()) {
                if (map.getValue() > maxRPValue) {
                    maxRPValue = map.getValue();
                    maxRP = map.getKey();
                }
            }
            rotate(notFixed, diff, maxRP);
        }



    }
}
/*

sample input
1
20 2
stayinghomesaveslife
1
3

sample output
6
0

*/