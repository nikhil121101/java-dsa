package dataStructures.heap;
import java.util.*;

public class Implementaion {

	private static class MyMinHeap {
		int heap[];
		int capacity;
		int heapSize;
		MyMinHeap(int capacity) {
			this.capacity=capacity;
			this.heap=new int[capacity];
			this.heapSize=0;
		}
		MyMinHeap(int[] heap) {
			this.heap=heap;
			this.capacity=heap.length;
			this.heapSize=heap.length;
		}
		int parent(int i) {
			return (i-1)/2;
		}
		int left(int parent) {
			return 2*parent+1;
		}
		int right(int parent) {
			return 2*parent+2;
		}
		int[] getHeap() {
			return heap;	
		}
		void swap(int pos1 , int pos2) {
			int temp=heap[pos1];
			heap[pos1]=heap[pos2];
			heap[pos2]=temp;
		}
		boolean isLeaf(int pos) {
			if( left(pos)>=heapSize ) {
				return true;
			}
			else return false;
		}
		void heapify(int node) {
			if(!isLeaf(node)) {
				//System.out.println("Right index -> "+right(node)+" heapSize -> "+heapSize);
				if(right(node)>=heapSize) {
					//System.out.print("condition true ");
					if(heap[node]>heap[left(node)]) {
						//System.out.println("heap[node]-> "+heap[node]+" heap[left(node)] -> "+heap[left(node)]);
						//System.out.print("condition true ");
						swap(node,left(node));
						return;
					}
				}
				else if( heap[node]>heap[left(node)] || heap[node]>heap[right(node)]) {
					if(heap[left(node)]<heap[right(node)]) {
						swap(node,left(node));
						heapify(left(node));
					}
					else {
						swap(node,right(node));
						heapify(right(node));
					}
				}
			}
		}
		void insert(int data) {
			if(heapSize==capacity) {
				return;
			}
			heap[heapSize++]=data;
			int temp=heapSize-1;
			while(parent(temp)>=0 && heap[temp]<heap[parent(temp)]) {
				swap(temp,parent(temp));
				temp=parent(temp);
			}
		}
		void MinHeapify() {
			for(int i=heapSize/2-1 ; i>=0 ; i--) {
				heapify(i);
			}
		}
		int extractMin() {
			if(heapSize<=0) {
				return Integer.MAX_VALUE;
			}
			swap(0,--heapSize);
			heapify(0);
			return heap[heapSize];
		}
		void printHeap() {
			for( int i=0 ; i<heapSize ; i++ ) {
				System.out.print(heap[i]+" ");
			}
			System.out.println();
		}
	}
	
	
	
	private static class MyMaxHeap {
		int heap[];
		int capacity;
		int heapSize;
		MyMaxHeap(int capacity) {
			this.capacity=capacity;
			this.heap=new int[capacity];
			this.heapSize=0;
		}
		MyMaxHeap(int[] heap) {
			this.heap=heap;
			this.capacity=heap.length;
			this.heapSize=heap.length;
		}
		int parent(int i) {
			return (i-1)/2;
		}
		int left(int parent) {
			return 2*parent+1;
		}
		int right(int parent) {
			return 2*parent+2;
		}
		int[] getHeap() {
			return heap;	
		}
		void swap(int pos1 , int pos2) {
			int temp=heap[pos1];
			heap[pos1]=heap[pos2];
			heap[pos2]=temp;
		}
		boolean isLeaf(int pos) {
			if( left(pos)>=heapSize ) {
				return true;
			}
			else return false;
		}
		void heapify(int node) {
			if(!isLeaf(node)) {
				//System.out.println("Right index -> "+right(node)+" heapSize -> "+heapSize);
				if(right(node)>=heapSize) {
					//System.out.print("condition true ");
					if(heap[node]<heap[left(node)]) {
						//System.out.println("heap[node]-> "+heap[node]+" heap[left(node)] -> "+heap[left(node)]);
						//System.out.print("condition true ");
						swap(node,left(node));
						return;
					}
				}
				else if( heap[node]<heap[left(node)] || heap[node]<heap[right(node)]) {
					if(heap[left(node)]>heap[right(node)]) {
						swap(node,left(node));
						heapify(left(node));
					}
					else {
						swap(node,right(node));
						heapify(right(node));
					}
				}
			}
			return;
		}
		void insert(int data) {
			if(heapSize==capacity) {
				return;
			}
			heap[heapSize++]=data;
			int temp=heapSize-1;
			while(parent(temp)>=0 && heap[temp]>heap[parent(temp)]) {
				swap(temp,parent(temp));
				temp=parent(temp);
			}
		}
		void MinHeapify() {
			for(int i=heapSize/2-1 ; i>=0 ; i--) {
				heapify(i);
			}
		}
		int extractMax() {
			if(heapSize<=0) {
				return Integer.MIN_VALUE;
			}
			swap(0,--heapSize);
			heapify(0);
			return heap[heapSize];
		}
		void printHeap() {
			for( int i=0 ; i<heapSize ; i++ ) {
				System.out.print(heap[i]+" ");
			}
			System.out.println();
		}
		
	}
	
	
	
	static int[] heapSort(int a[]) {
		MyMinHeap myMinHeap=new MyMinHeap(a);
		myMinHeap.MinHeapify();
		for ( int i=0 ; i<a.length ; i++ ) {
			//myMinHeap.printHeap();
			myMinHeap.swap(0,myMinHeap.heapSize-1); 
			//System.out.print("swapping -> "+myMinHeap.heap[0]+","+myMinHeap.heap[myMinHeap.heapSize-1]);
			(myMinHeap.heapSize)--;
			//System.out.println();
			//System.out.print("heapsize became-> "+myMinHeap.heapSize);
			//System.out.println();
			//System.out.println("Heapifying ->"+myMinHeap.heap[0]);
			myMinHeap.heapify(0);
			if(myMinHeap.heapSize==0) {
				//System.out.println("Heap Empty **??// ");
			}
			else {
				//System.out.println("After Heapifying At Top ->"+myMinHeap.heap[0]);
			}
			//System.out.println();
		}
		return myMinHeap.heap;
	}

	public static void main(String[] args) {
		MyMinHeap myMinHeap1=new MyMinHeap(10);
		myMinHeap1.insert(13);
		myMinHeap1.insert(96);
		myMinHeap1.insert(34);
		myMinHeap1.insert(49);
		myMinHeap1.insert(78);
		myMinHeap1.insert(20);		
		myMinHeap1.printHeap();
		System.out.println(myMinHeap1.extractMin());
		myMinHeap1.printHeap();
//		int a[]= {74,57,29,47,69,48,67,37,4};
//		System.out.println("Unsorted -> "+Arrays.toString(a));
//		MyMinHeap myMinHeap2=new MyMinHeap(a);
//		myMinHeap2.MinHeapify();
//		//myMinHeap2.printHeap();
//		System.out.println("Sorted -> "+Arrays.toString(heapSort(a)));



	
	}

}
