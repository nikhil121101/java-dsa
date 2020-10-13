package dataStructures;

import java.util.*;

public class ds {

	public static void main(String[] args) {
		Stack<Integer> stack=new Stack<Integer>();
		ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		ad.addFirst(7);
		ad.addFirst(50);
		ad.addFirst(36);
		ad.addFirst(22);
		ad.addAll(stack);
		System.out.println(ad);
	}

}
