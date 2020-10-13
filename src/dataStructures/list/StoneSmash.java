package dataStructures.list;

import java.util.*;


public class StoneSmash {
	
	static void addthis (Stack<Integer> Stones , int a) {
		Stack<Integer> temp = new Stack<Integer>();
		while(!Stones.isEmpty() && a<Stones.peek()) {
    		temp.push(Stones.pop());
    	}
    	Stones.push(a);
    	while(!temp.isEmpty()) {
    		Stones.push(temp.pop());
    	}
	}
	
	static int lastStoneWeight(int[] stones) {
        Stack<Integer> Stones = new Stack<Integer>();
        

        for(int i = 0 ; i < stones.length ; i++) {
        	addthis(Stones , stones[i]);
        }
        System.out.println(Stones);
        while(!(Stones.isEmpty() || Stones.size() == 1)) {
        	int a = Stones.pop();
        	int b = Stones.pop();
        	if(a == b) {
        		continue;
        	}
        	else {
        		addthis(Stones , Math.abs(a-b));
        	}
        	System.out.println(Stones);
        }
        if(Stones.isEmpty()) {
        	return 0;
        }
        else  {
        	return Stones.pop();
        }
    }

	public static void main(String[] args) {
		int a[] =  {2,7,4,1,8,1};
		System.out.println(lastStoneWeight(a));

	}

}
