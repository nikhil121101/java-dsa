package dataStructures.stacks;

import java.util.*;

public class minBracketRev {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Stack<Character> stack=new Stack<Character>();
		int test=sc.nextInt();
		sc.nextLine();
		while(test--!=0) {
		    String s=sc.nextLine();
//		    System.out.println(s);
		    for( int i=0 ; i<s.length() ; i++ ) {
		    	
		        if(stack.isEmpty()) {
		        stack.push(s.charAt(i));
		        continue;
		        }
		        if(s.charAt(i)=='}' && stack.peek()=='{') {
		            stack.pop();
		        }
		        else {
		            stack.push(s.charAt(i));
		        }
		    }
    	    System.out.println(stack);
		    
		    int m=0,ct=0;
		    while(!stack.isEmpty()) {
		        char temp2=stack.pop();
		        if(stack.isEmpty()) {
		           m=-1;
		           break;
		        }
		        char temp1=stack.pop();
		        System.out.println("temp1-> "+temp1+" temp2-> "+temp2);
		        if(temp1=='{'&&temp2=='{'||temp1=='}'&&temp2=='}') {
		            m+=1;
		        }
		        else if(temp1=='}'&&temp2=='{') {
		            m+=2;
		        }
		        System.out.println(m);
		    }
		    
		    System.out.println(m);
		    
		  	            
		}

	}

}
