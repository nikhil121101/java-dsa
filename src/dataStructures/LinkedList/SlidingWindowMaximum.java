package dataStructures.LinkedList;

import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
    	int res[]=new int[nums.length-k];
        LinkedList<Integer> win = new LinkedList<Integer>();
        int winCount=-1;
        for(int i=0; i<nums.length;i++) {
        	if(i>=k-1)
        	winCount++;
            if(i==0) {
                win.addLast(i);
            }
            while(nums[i]>win.peekLast()) {
            	win.removeLast();
            }
            win.addLast(i);
            
            
            
        }
        return res;
    }
}
