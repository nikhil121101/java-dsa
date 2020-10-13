package codeForces;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.*;



public class SortedAdjacentDifferences {
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
//		StringBuilder strBuilder = new StringBuilder("");
		int n = scanner.nextInt();
		int a[];
		while(t-- != 0) {
			//System.out.println("HI");
			n = scanner.nextInt();
			//System.out.println(n);
			a= new int[n];
			for(int i = 0 ; i < n ; i++) {
				a[i] = scanner.nextInt();
			}
			//System.out.println("Before code array -> " + Arrays.toString(a));
			Code( n , a /*, strBuilder*/);
		}
		scanner.close();
		//System.out.println(strBuilder.toString()); 
	}
	
//	static void printStack(Stack<Pair> stack) {
//		Iterator< Pair> iterator = stack.iterator();
//		while(iterator.hasNext()) {
//			Pair tempPair = iterator.next();
//			System.out.print("( " + tempPair.first + " , " + tempPair.second + " )");
//		}
//		System.out.print(" Empty");
//		System.out.println();
//	}
	
	static void Code (int n , int a[] /*, StringBuilder stringBuilder*/) {
//		Stack<Pair> stack= new Stack<Pair>();
//		Stack<Pair> temp= new Stack<Pair>();
//		Pair pair;
//		for(int i = 0 ; i < n/2 ; i++) {
//			pair = new Pair(a[2*i] , a[2*i +1] , Math.abs(a[2*i + 1] - a[2*i]));
//			//System.out.println("inserting " + a[2*i] + " " + a[2*i + 1]);
//			//System.out.println("Stack Before Inserting");
//			//printStack(stack);
//			while(! stack.isEmpty() && stack.peek().def < pair.def ) {
//				temp.push(stack.pop());
//			}
//			stack.push(pair);
//			while(!temp.isEmpty()) {
//				stack.push(temp.pop());
//			}
//			//System.out.println("Stack After Inserting");
//			//printStack(stack);
//		}
//		while(!stack.isEmpty()) {
//			pair = stack.pop();
////			stringBuilder.append(pair.first);
////			stringBuilder.append(" ");
////			stringBuilder.append(pair.second);
////			stringBuilder.append(" ");
//			System.out.print(pair.first + " " + pair.second + " ");
			
//		}
		//System.out.println(stringBuilder.toString());
//		stringBuilder.append("\n");
		Arrays.sort(a);
		for(int i = n/2 - 1 ; i >= 0; i++) {
			System.out.print(a[i] + " " + a[n - i - 1] + " ");
		}
		System.out.println();
	}

}
class Pair implements Comparator<Pair> {
	int first ;
	int second ;
	int def ;
	Pair(int first , int second , int dif) {
		this.first = first;
		this.second = second;
		this.def = dif;
	}
	public int compare (Pair p1 , Pair p2) {
		return p1.def - p2.def;
	}
}