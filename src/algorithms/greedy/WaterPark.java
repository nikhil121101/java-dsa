package algorithms.greedy;

import java.awt.image.DataBufferDouble;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//
//class friendData implements Comparator<friendData> {
//    LinkedList<Integer> index ;
//    int excitement ;
//    friendData() {
//
//    }
//    friendData(LinkedList<Integer> index , int excitement) {
//        this.index = index;
//        this.excitement = excitement;
//    }
//
//
//    @Override
//    public int compare(friendData o1, friendData o2) {
//        return o2.excitement - o1.excitement;
//    }
//}
//
public class WaterPark {
//    public static void main(String args[]) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int t = Integer.parseInt(bufferedReader.readLine());
//        final int size = (int) Math.pow(10 , 5);
//        int desc[] =new int[size];
//        int exc[] = new int[size];
//        while(t-- != 0) {
//            int n = Integer.parseInt(bufferedReader.readLine());
//            String[] s = bufferedReader.readLine().trim().split(" ");
//            for(int i = 0 ; i < n ; i++) {
//                exc[i] = Integer.parseInt(s[i]);
//            }
//            s = bufferedReader.readLine().trim().split(" ");
//            for(int i = 0 ; i < n ; i++) {
//                desc[i] = Integer.parseInt(s[i]);
//            }
//            Code(exc , desc , n);
//        }
//
//    }
//    static void Code(int[] exc , int[] desc , int n ) {
//        HashMap<Integer , LinkedList<Integer>> hashMap = new HashMap<>();
//
//        PriorityQueue<friendData> priorityQueue = new PriorityQueue<friendData>(n , new friendData());
//        for(int i = 0 ; i < n ; i++) {
//            if(hashMap.containsKey(exc[i])) {
//                hashMap.get(exc[i]).add(i);
//            }
//            else {
//                hashMap.put(exc[i] , new LinkedList<Integer>());
//                hashMap.get(exc[i]).add(i);
//            }
////            if(priorityQueue.contains()) {
////
////            }
//        }
//        for(Map.Entry<Integer , LinkedList<Integer>> itr : hashMap.entrySet()) {
//            LinkedList<Integer> temp = itr.getValue();
//            priorityQueue.add(new friendData(temp , itr.getKey()));
//        }
//        int rides = 0;
//        while(!priorityQueue.isEmpty() || rides<=50000) {
//            priorityQueue.peek().index.removeFirst()
//        }
//
//    }
//
}
