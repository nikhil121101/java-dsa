package dataStructures.lab7;

import java.lang.*;
import java.io.*;
import java.util.*;

class meeting implements Comparator<meeting>{
    int start;
    int end;
    int index;
    meeting() {

    }
    meeting(int start , int end , int index) {
        this.start = start;
        this.end = end;
        this.index = index;
    }
    public int compare(meeting m1 , meeting m2) {
        return m1.end - m2.end;
    }
}

class Logic {

    public StringBuilder Code(meeting meet[] , int n) {

        Arrays.parallelSort(meet , new meeting());
        StringBuilder s = new StringBuilder("");
        s.append((meet[0].index + 1)).append(" ");
        int last = 0;
        for(int i = 1 ; i < n ; i++) {
            //System.out.println(meet[i].start + " " + meet[i-1].end);
            if(meet[i].start >= meet[last].end) {
                s.append((meet[i].index + 1)).append(" ");
                last = i;
            }
        }
        return s;
    }

}

public class ActivitySelection {

    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            int n = Integer.parseInt(bf.readLine());
            meeting[] meet = new meeting[n];
            String s[] = bf.readLine().split(" ");
            for(int i = 0 ; i < n ; i++) {
                meet[i] = new meeting();
                meet[i].start = Integer.parseInt(s[i]);
                meet[i].index = i;
            }
            s = bf.readLine().split(" ");
            for(int i = 0 ; i < n ; i++) {
                meet[i].end = Integer.parseInt(s[i]);
            }
            Logic logic = new Logic();
            System.out.println(logic.Code(meet , n).toString());
        }
    }

}
/*
2
6
1 3 0 5 8 5
2 4 6 7 9 9
8
75250 50074 43659 8931 11273 27545 50879 77924
112960 114515 81825 93424 54316 35533 73383 160252
 */
