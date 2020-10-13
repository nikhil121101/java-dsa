package leetcode;

import java.util.*;


public class ConteguosSubarray {

    private static class Pair implements  Comparator<Pair>{
        int start ;
        int end ;
        Pair( int deadline , int profit ) {
            this.start = deadline ;
            this.end = profit ;
        }

        public Pair() {

        }

        public int compare (Pair p1 , Pair p2) {
            return (p1.end - p1.start) -
                    (p2.end - p2.start);
        }
    }

    static public ArrayList<Pair> findMaxLength(int[] nums) {
        ArrayList<Pair> result = new ArrayList<Pair>() ;
        HashMap< Integer , ArrayList<Integer> > sumSet = new HashMap<>();
        int n = nums.length;
        int s = 0;
        for(int i  = 0 ; i < n ; i++) {
            s += (nums[i] == 1) ? 1 : -1 ;
//            if(s == 0) {
//                res.add(new Pair(i , i));
//                continue;
//            }
            if(sumSet.containsKey(s)) {
                sumSet.get(s).add(i);
            }
            else {
                sumSet.put(s , new ArrayList<Integer>());
                sumSet.get(s).add(i);
            }
        }
        s = 0;
        System.out.println(sumSet);
        for(int i = 0 ; i < n ; i++) {
            int curSum = (nums[i] == 1) ? 1 : -1 ;
            s += curSum;
            if(s != 0) {
                sumSet.get(s).remove(0);
                if (sumSet.get(s).size() > 0) {
                    Iterator<Integer> integerIterator = sumSet.get(s).iterator();
                    while (integerIterator.hasNext()) {
                        result.add(new Pair(i + 1, integerIterator.next()));
                    }
                }
            }
            else {
                result.add(new Pair(0, i));
            }
        }
        return result;
    }

    public static void main(String args[])
    {
        int a[] =  {0,1};;
        ArrayList<Pair> ConteguosSubarray = findMaxLength(a);
        Collections.sort(ConteguosSubarray , new Pair());
        for(int i  = 0 ; i < ConteguosSubarray.size() ; i++) {
            Pair curPair = ConteguosSubarray.get(i);
            System.out.println(curPair.start + " - " + curPair.end);
        }
    }

}