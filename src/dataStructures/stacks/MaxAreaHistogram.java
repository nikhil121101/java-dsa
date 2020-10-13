
package dataStructures.stacks;

import java.util.*;
import java.lang.*;
import java.io.*;
public class MaxAreaHistogram {
	/*package whatever //do not write package name here */

	

	
		public static void main (String[] args) {
			Scanner sc=new Scanner(System.in);
			int test=sc.nextInt();
			while(test--!=0)
			{
			    int n=sc.nextInt();
			    double a[]=new double[n];
			    for(int i=0;i<n;i++) {
			        a[i]=sc.nextDouble();
			    }
			    Stack<Integer> stack=new Stack<Integer>();
			    double area[]=new double[n];
			    int i=0;
			    while(i<n) {
			        if(stack.empty()) {
			            stack.push(i);
			            System.out.println("1 "+stack.peek());
			            i++;
			            continue;
			        }
			        if(a[i]>=a[stack.peek()]) {
			            stack.push(i);
			            System.out.println("2 "+stack.peek());
			        }   
			        else {
			        while(!stack.empty()&&a[stack.peek()]>=a[i])
			        {
			            
			            int temp=stack.pop();
			            System.out.println("poped "+temp);
			            if(stack.empty()) {
			                  area[temp]=a[i]*(i-temp+1);
			                  System.out.println("area of "+temp+" "+area[temp]);
			            }
			            else {
			            System.out.println("peek "+stack.peek());
			            area[temp]=Math.min(a[i],a[stack.peek()])*(i-stack.peek()+1);
			            System.out.println("area of "+temp+" "+area[temp]);
			            }
			        }
			        stack.push(i);
			        System.out.println("3 "+stack.peek());
			        }
			        
			        i++;
			    }
			    while(!stack.empty()) {
			    	int temp=stack.pop();
			    	if(stack.empty()) {
			    		area[temp]=a[temp]*n;
			    	}
			    	else {
			    	area[temp]=(n-stack.peek())*a[stack.peek()];
			    	}
			    }
			    System.out.println(Arrays.toString(area));
			    double max=area[0];
			    for(int j=1;j<n;j++) {
			      if(area[j]>max)
			      max=area[j];
			    }
			    System.out.println(max);
			    
			}
		}
	
}
