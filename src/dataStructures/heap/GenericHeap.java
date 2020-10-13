package dataStructures.heap;

import java.util.Comparator;

class Node implements Comparator<Node> {
    int key;
    int value;
    Node() {

    }
    Node(int key , int value) {
        this.key = key;
        this.value = value;
    }
    public int compare(Node n1 , Node n2) {
        return n1.value - n2.value;
    }

    public boolean equals (Node n1 , Node n2) {
        return n1.value == n2.value;
    }
}

class GenPriorityQueue {

    Node heap[];
    int capacity;
    int heapSize;
    GenPriorityQueue(int capacity) {
        this.capacity=capacity;
        this.heap=new Node[capacity];
        for(Node n : heap) {
            n = new Node();
        }
        this.heapSize=0;
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
    //        int[] getHeap() {
//            return heap;
//        }
    void swap(int pos1 , int pos2) {
        Node temp = heap[pos1];
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
                if(heap[node].value >heap[left(node)].value) {
                    //System.out.println("heap[node]-> "+heap[node]+" heap[left(node)] -> "+heap[left(node)]);
                    //System.out.print("condition true ");
                    swap(node,left(node));
                    return;
                }
            }
            else if( heap[node].value > heap[left(node)].value || heap[node].value > heap[right(node)].value) {
                if(heap[left(node)].value < heap[right(node)].value) {
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
    void insert(Node data) {
        if(heapSize==capacity) {
            return;
        }
        heap[heapSize++] = data;
        int temp=heapSize-1;
        while(parent(temp) >= 0 && heap[temp].value < heap[parent(temp)].value) {
            swap(temp,parent(temp));
            temp=parent(temp);
        }
    }
    void MinHeapify() {
        for(int i=heapSize/2-1 ; i>=0 ; i--) {
            heapify(i);
        }
    }

    void printHeap() {
        for( int i=0 ; i<heapSize ; i++ ) {
            System.out.print(heap[i].key + " ");
        }
        System.out.println();
    }

    Node remove() {
        if(heapSize == 0) {
            return null;
        }
        swap(0 , heapSize - 1);
        heapSize--;
        heapify(0);
        return heap[heapSize];
    }

}

class GenMaxHeap {

    Node heap[];
    int capacity;
    int heapSize;
    GenMaxHeap(int capacity) {
        this.capacity=capacity;
        this.heap=new Node[capacity];
        for(Node n : heap) {
            n = new Node();
        }
        this.heapSize=0;
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
    //        int[] getHeap() {
//            return heap;
//        }
    void swap(int pos1 , int pos2) {
        Node temp = heap[pos1];
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
            if(right(node) <= heapSize) {
                //System.out.print("condition true ");
                if(heap[node].value < heap[left(node)].value) {
                    //System.out.println("heap[node]-> "+heap[node]+" heap[left(node)] -> "+heap[left(node)]);
                    //System.out.print("condition true ");
                    swap(node,left(node));
                    return;
                }
            }
            else if( heap[node].value < heap[left(node)].value || heap[node].value < heap[right(node)].value) {
                if(heap[left(node)].value > heap[right(node)].value) {
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
    void insert(Node data) {
        if(heapSize==capacity) {
            return;
        }
        heap[heapSize++] = data;
        int temp=heapSize-1;
        while(parent(temp) >= 0 && heap[temp].value > heap[parent(temp)].value) {
            swap(temp,parent(temp));
            temp=parent(temp);
        }
    }
    void MinHeapify() {
        for(int i = heapSize / 2 - 1 ; i >= 0 ; i--) {
            heapify(i);
        }
    }

    void printHeap() {
        for( int i=0 ; i<heapSize ; i++ ) {
            System.out.print(heap[i].key + " ");
        }
        System.out.println();
    }

    Node remove() {
        if(heapSize == 0) {
            return null;
        }
        swap(0 , heapSize - 1);
        heapSize--;
        heapify(0);
        return heap[heapSize];
    }

}

public class GenericHeap {

    public static void main(String[] args) {
        int a[] = new int[]{5 , 8 , 2 , 3 , 52 , 93, 10};
        int n = a.length;
//        GenPriorityQueue pq = new GenPriorityQueue(7);
//        for(int i = 0 ; i < n ; i++) {
//            pq.insert(new Node(i , a[i]));
//        }
//
//        for(int i = 0 ;  i < n ; i++) {
//            System.out.println(pq.remove().value);
//        }
        GenMaxHeap MaxHeap = new GenMaxHeap(7);
        for(int i = 0 ; i < n ; i++) {
            MaxHeap.insert(new Node(i , a[i]));
        }

        for(int i = 0 ;  i < n ; i++) {
            System.out.println(MaxHeap.remove().value);
        }
    }

}
