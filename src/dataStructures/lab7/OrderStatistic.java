package dataStructures.lab7;

public class OrderStatistic {
    static int expectedLiner(int a[] , int l , int r , int k) {
        if(l == r) {
            return a[l];
        }
        int piv = (int)(Math.random()*(r-l + 1) + l);
        int ind =  partition(a , l , r , piv);
        if(ind == k) {
            return a[ind];
        }
        else if(ind < k) {
            return expectedLiner(a , ind + 1 , r , k);
        }
        return expectedLiner(a , l , ind - 1 , k);
    }

    static private int partition(int[] a, int low, int high, int piv) {
        swap(a , high , piv);
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
        swap(a , high , firstHigher);
        return firstHigher;
    }

    private static void swap(int[] a, int high, int piv) {
        int temp = a[high];
        a[high] = a[piv];
        a[piv] = temp;
    }

    public static void main(String args[]) {
        int a[] = new int[]{3,2,3,1,2,4,5,5,6};
        int n = a.length;
        System.out.println(expectedLiner(a , 0 , n-1 , 4));
    }

}
