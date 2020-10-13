package algorithms.greedy;

import java.util.*;
import java.io.*;

public class ArrayManipualtion {

    static long arrayManipulation(int n, int[][] queries) {
        int m = queries.length; 
        long a[] = new long[n];
        Set<Integer> temp = new HashSet<Integer>();
        for(int i = 0 ; i < queries.length ; i++) {
            a[queries[i][0]-1] += queries[i][2];
            if(queries[i][1] < n ) {
            	a[queries[i][1]] -= queries[i][2];
            	temp.add(queries[i][1]);
            }
            
            temp.add(queries[i][0] - 1) ;
        }
        Object temp2[] = temp.toArray();
        Arrays.sort(temp2);
        //System.out.println("temp -> " + Arrays.toString(temp2));
        //System.out.println("a -> " + Arrays.toString(a));

        long max = 0 ;
        long sum = 0 ;
        try {
	        for( int i = 0 ; i < temp2.length ; i++ ) {
	        	sum += a[(int)temp2[i]] ;
	        	//System.out.println("temp[i] -> " + (int)temp2[i] + " a[temp[i]] -> " + a[(int)temp2[i]] + " max -> " + max );
	            if(sum > max) {
	                max = sum ;
	            }
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);
        
        System.out.println(result);
    
        scanner.close();
    
    }

	

}
