package cs201Assingment.lab3.q2;

public class MaxSubarray {
    public static void main(String args[]) {
        int a[] = new int[]{-2 , 1 , -3 , 4 , -1 , 2 , 1 ,-5 , 4};
        System.out.println(maxSumSubarray(a , 0 , a.length - 1));
        a = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSumSubarray(a , 0 , a.length - 1));
    }

    private static int maxSumSubarray(int[] a, int l , int r) {
        if(l > r) {
            return 0;
        }
        if(l == r) {
            return a[l];
        }
        int m = (l + r) / 2;
        int s1 = maxSumSubarray(a , l , m);
        int s2 = maxSumSubarray(a , m + 1 , r);
        int s3 = a[m];
        int i = m - 1 , j = m + 1;
        int maxSum = 0 , s = 0;
        while(i >= 0) {
            s += a[i];
            maxSum = Math.max(maxSum , s);
            i--;
        }
        s3 += maxSum;
        maxSum = 0; s = 0;
        while(j <= r) {
            s += a[j];
            maxSum = Math.max(maxSum , s);
            j++;
        }
        s3 += maxSum;
        return Math.max(s1 , Math.max(s2 , s3));
    }
}
