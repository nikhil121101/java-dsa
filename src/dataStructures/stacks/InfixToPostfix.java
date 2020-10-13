package dataStructures.stacks;

import java.util.*;

public class InfixToPostfix {
	static int OpPres(char a) {
		switch(a) {
		case '+' :
		case '-' : return 1; 
		case '*' :
		case '/' : return 2;
		case '^' : return 3;
		default  : return 0;
		}
	}
	static String InfToPof(String inf) {
		Stack<Character> stack=new Stack<Character>();
		String pof="";
		for(int i=0; i<inf.length() ; i++) {
			if(OpPres(inf.charAt(i))==0) {
				pof+=inf.charAt(i);
			}
			else {
				if(stack.isEmpty()) {
					stack.push(inf.charAt(i));
					continue;
				}
				if( OpPres(inf.charAt(i))==OpPres(stack.peek())&&OpPres(stack.peek())==3) {
					while( !stack.isEmpty() && OpPres(inf.charAt(i))<OpPres(stack.peek()) ) {
						pof+=stack.pop();	
					}
					stack.push(inf.charAt(i));
					continue;
				}
				while( !stack.isEmpty() && OpPres(inf.charAt(i))<=OpPres(stack.peek()) ) {
					pof+=stack.pop();	
				}
				stack.push(inf.charAt(i));
			}
		}
		while(!stack.isEmpty()) {
			pof+=stack.pop();		
		}
		return pof;
	}
//	static String PofToInf(String pof) {
//		Stack<Character> stack=new Stack<Character>();
//		String inf="";
//		for(int i=0; i<inf.length() ; i++) {
//			if(OpPres(inf.charAt(i))==0) {
//				pof+=inf.charAt(i);
//			}
//			else {
//				if(stack.isEmpty()) {
//					stack.push(inf.charAt(i));
//					continue;
//				}
//				while(OpPres(inf.charAt(i))<OpPres(stack.peek())) {
//					pof+=stack.pop();	
//				}
//				stack.push(inf.charAt(i));
//			}
//		}
//		while(!stack.isEmpty()) {
//			pof+=stack.pop();		
//		}
//		return inf;
//	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		System.out.println(InfToPof(s));

	}

}
