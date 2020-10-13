package dataStructures.heap;

import java.util.*;

public class CollectionHeap {

	public static void main(String[] args) {
		
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		heap.add(10);
		heap.add(97);
		heap.add(46);
		heap.add(69);
		heap.add(34);
		heap.add(76);
		heap.add(12);
		System.out.println(heap.poll());
		

	}

}
