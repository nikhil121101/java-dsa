package codeChefApril;

import java.util.*;

public class rootOfTheProblem {
//    HashMap<Integer , Integer> findSums(int nodes[][] , int n , Set<Integer> alreadyChild , int[] parent , int sum) {
//        HashMap<Integer , Integer> res = new HashMap<>();
//        int left = 0;
//        int right = n-1;
//        while(left <= right) {
//            if(nodes[left][0] == sum && !alreadyChild.contains(nodes[left][0]) && !isParent(parent , )) {
//                res.put(nodes[left][0] , -1);
//            }
//            if(right == sum) {
//                res.put(nodes[right][0] , -1);
//            }
//            if(nodes[left][0] + nodes[right][0] < sum) {
//                left++;
//            }
//            else if(nodes[left][0] + nodes[right][0] > sum) {
//                right--;
//            }
//            else {
//                res.put(nodes[left][0] , nodes[right][0]);
//                left++;
//                right--;
//            }
//        }
//        return res;
//    }
//
//    int root(int nodes[][] , int n) {
//        Arrays.sort(nodes, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        });
//        Set<Integer> alreadyChild = new HashSet<>();
//        int parent[] = new int[n];
//        Arrays.fill(parent , -1);
//        for(int i = 0 ; i < n ; i++) {
//            if(!alreadyChild.contains(i)) {
//                HashMap<Integer , Integer> temp = findSums(nodes , n , alreadyChild , parent , nodes[i][1]);
//            }
//        }
//    }
}
