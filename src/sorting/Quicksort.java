package sorting;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Quicksort {

    static void quickSort(int a[] , int low , int high) {
            if(low < high) {
                int piv = findPivoit(a , low , high);
                quickSort(a , low , piv - 1);
                quickSort(a , piv + 1  , high);
                System.out.println(piv);
                for(int i = low ; i <= high ; i++) {
                    System.out.print(a[i] + " ");
                }
                System.out.println();
                System.out.println(Arrays.toString(a));
            }
    }
    static int findPivoit(int a[] , int low , int high) {
        int firstHigher = low;
        int i = low;
        while(i < high) {
            if(a[i] < a[high]) {
                int temp = a[i];
                a[i] = a[firstHigher];
                a[firstHigher] = temp;
                firstHigher++;
            }
            i++;
        }
        int temp = a[high];
        a[high] = a[firstHigher];
        a[firstHigher] = temp;
        return firstHigher;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader((System.in)));
        String[] s = bf.readLine().trim().split(" ");
        int n = s.length;
        int a[] = new int[n];
        for(int i = 0 ; i  < n ; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        quickSort(a , 0 , n-1);
        System.out.println(Arrays.toString(a));
    }
}
