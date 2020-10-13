package codeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Boredom {

    public  static  void  main (String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int a[] = new int[n];
        String[] s = bufferedReader.readLine().trim().split(" ");
        for(int i = 0 ; i < n ; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        Code code = new Code();
        System.out.println(code.Code(a , n));
    }





}

class  Code {

    class weight implements Comparator<weight> {
        int value ;
        int freq;
        weight() { }
        weight(int vale ,int freq) { this.value = value ; this.freq = freq ; }
        public int compare (weight w1 , weight w2) {
            return w1.freq * w1.value  - w2.freq * w2.value;
        }
    }

    int Code(int a[] , int n) {
        int limit = (int)Math.pow(01 , 5);
        int freqArr[] = new int[limit];
        for(int i = 0 ; i < n ; i++) {
            freqArr[a[i]]++;
        }
        PriorityQueue<weight> priorityQueue = new PriorityQueue<weight>(limit , new weight());
        for(int i = 0 ; i < n ; i++) {
            if(freqArr[i] != 0) {
                priorityQueue.add(new weight(i , freqArr[i]));
            }
        }
        while(!priorityQueue.isEmpty()) {
            weight curWeight = priorityQueue.poll();
            freqArr[curWeight.value]--;
            //priorityQueue.

        }
        return  0;
    }
}


