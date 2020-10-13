package sorting;

import java.util.Scanner;

public class InsertionSort {
static void sort(int a[])
	{ int n=a.length,j;
		for(int i=1;i<n;i++)
		{ j=i;
		while(j!=0)
			if(a[j]<a[j-1])
			{
				int temp=a[j];
				a[j]=a[j-1];
				a[j-1]=temp;
				j--;
			}
			else j=0;
			
		}
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int []a=new int[n];
		for(int i=0;i<n;i++)
		{ a[i]=sc.nextInt();}
		sort(a);
		for(int i=0;i<n;i++)
			{System.out.println(a[i]);}
	}
	

}
