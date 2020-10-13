package dataStructures.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;



public class ZeroSumSubArray {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine().trim());
		int n ; 
		int a[] = new int[1000000];
		String s[] = new String[1000000];
		StringBuilder stringBuilder = new StringBuilder("");
		while(t-- != 0) {
			n = Integer.parseInt(bufferedReader.readLine().trim());
			s = bufferedReader.readLine().trim().split(" ");
			for(int i = 0 ; i < n ; i++) {
				a[i] = Integer.parseInt(s[i]);
			}
			Code(a , n , stringBuilder);
		}
		System.out.println(stringBuilder.toString());

	}
	
	static void Code(int a[] , int n , StringBuilder stringBuilder) {
		
		int res = 0 ;
		int sumArray[] = new int[n];
		sumArray[0] = a[0];
		HashMap<Integer , ArrayList<Integer>> sumSet = new HashMap<>();
		
		for(int i = 0 ; i < n ; i ++) {
			if(i == 0) {
				sumArray[0] = a[i];
			}
			else {
				sumArray[i] = sumArray[i-1] + a[i];
			}
			if(sumArray[i] == 0) {
				res++;
				continue;
			}
			if(sumSet.containsKey(sumArray[i])) {
				sumSet.get(sumArray[i]).add(i);
			}
			sumSet.put(sumArray[i], new ArrayList<Integer>());
			sumSet.get(sumArray[i]).add(i);
			
		}
		
		for(int i = 0 ; i < n && sumArray[i] != 0; i++) {
			System.out.println(sumSet);
			sumSet.get(sumArray[i]).remove(i);
			if(sumSet.get(sumArray[i]).size()>0) {
				res  +=  sumSet.get(sumArray[i]).size();
			}
			
			
		}
		System.out.println(res);
		stringBuilder.append(res + "\n");
	}

}

