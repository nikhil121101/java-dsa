package algorithms.divide_and_conquer;

public class inversionPairs {
    public static int reversePairs(int[] a) {
        int n = a.length;
        if(n == 0) {
            return 0;
        }
        int[] sorted = new int[n];
        int res =  mergeSort(a , sorted , 0 , n - 1);
        return res;
    }

    public static int mergeSort(int a[], int[] sorted, int start, int end) {
        if(start == end) {
            sorted[start] = a[start];
            return 0;
        }
        int inv = 0;
        int mid = (start + end) / 2;
        inv += mergeSort(a , sorted , start , mid);
        inv += mergeSort(a , sorted , mid + 1 , end);
        inv += merge(a , sorted , start , mid , end);
        return inv;
    }

    static int merge(int a[], int[] sorted, int start, int mid, int end) {

        int i = start , j = mid + 1 , k = start;
        while(i <= mid && j <= end) {
            if(a[i] <= a[j]) {
                sorted[k] = a[i];
                k++;
                i++;
            }
            else {
                sorted[k] = a[j];
                k++;
                j++;
            }
        }
        while(i <= mid) {
            sorted[k] = a[i];
            i++;
            k++;
        }
        while(j <= end) {
            sorted[k] = a[j];
            j++;
            k++;
        }

        // counting inversions

        int inv = 0;
        i = start;
        j = mid + 1;
        while(i <= mid && j <= end) {
            if(a[i] <= 2 * (long)a[j]) {
                i++;
            }
            else {
                inv += mid - i + 1;
                j++;
            }
        }

        // counting inversion end here
        for(int itr = start ; itr <= end ; itr++) {
            a[itr] = sorted[itr];
        }
        return inv;
    }

    public static void main(String args[]) {
        int a[] = new int[]{1,3,2,3,1};
        System.out.println(reversePairs(a));
        a = new int[]{2,4,3,5,1};
        System.out.println(reversePairs(a));
    }

}
