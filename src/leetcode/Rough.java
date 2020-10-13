package leetcode;

public class Rough {

    public double findMedianSortedArrays(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        if(n < m) {
            int[] temp = a;
            a = b;
            b = temp;
            n = a.length;
            m = b.length;
        }
        // size(a) > size(b)
        int low = 0 , high = m - 1 , mid = 0;
        int partSize = (int) Math.ceil((n + m) / (double)2);
        while(low <= high) {
            mid = (low + high) / 2;
            if(m >= partSize) {
                high = mid - 1;
                continue;
            }
            int leftMax = b[mid] , rightMin = Integer.MAX_VALUE;
            if(partSize - mid - 2 >= 0) {
                leftMax = Math.max(b[mid] , a[partSize - mid - 2]);
            }
            if(mid + 1 < m) {
                rightMin = Math.min(rightMin , b[mid + 1]);
            }
            if(partSize - mid - 1 < n) {
                rightMin = Math.min(rightMin , a[partSize - mid - 1]);
            }
            if(leftMax <= rightMin) {
                return (m+n) % 2 == 0 ? (leftMax + rightMin) / (double)2 : leftMax;
            }
            if(mid + 1 < m && partSize - mid - 2 >= 0) {
                if(b[mid + 1] < a[partSize - mid - 2]) {
                    low = mid + 1;
                }
            }
            else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
