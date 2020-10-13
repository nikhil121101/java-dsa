package codeForces;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class CompoundWeight {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0) {
            String s =sc.nextLine();
            System.out.println(Code(s));
        }
    }
    static int Code (String s) {
        Stack<Character> stack = new Stack<Character>();
        int wt = 0;
        int prevPow = 1;
        for(int i = s.length() - 1 ; i >= 0  ; i--) {
            if(s.charAt(i) == '(') {
                int tempWt = 0;
                int prevInt = 0 ;
                while(stack.peek() != '(') {
                   char curChar = stack.pop();
                   if(curChar == 'C') {
                       tempWt += 12*prevInt;
                       prevInt =1;
                   }
                   if(curChar == 'H') {
                       tempWt += 1*prevInt;
                       prevInt = 1;
                   }
                   else {
                        prevInt = (int)curChar - 48 ;
                   }
                }
                wt += tempWt;

            }
            else if(s.charAt(i) == ')') {
                stack.push(s.charAt(i));
            }
            else {

            }
        }
        while(!stack.isEmpty()) {

        }
        return wt;
    }


}
