package leetcode;

import java.util.Arrays;

public class SearchInRotatedSortedArray {

    public static int search(int[] nums, int target) {
        int n = nums.length;
        if(n == 0) {
            return -1;
        }
        int rotationPoint = findRotationPoint(nums);
        System.out.println(rotationPoint);
        if(rotationPoint == -1) {
            if(Arrays.binarySearch(nums , target) >=0) {
                return Arrays.binarySearch(nums , target);
            }
            else {
                return -1;
            }
        }
        if(Arrays.binarySearch(nums ,0 , rotationPoint + 1 , target) >= 0) {
            return Arrays.binarySearch(nums ,0 , rotationPoint +1, target);
        }
        if(Arrays.binarySearch(nums ,rotationPoint+1 , n , target) >= 0) {
            return Arrays.binarySearch(nums ,rotationPoint+1 , n , target);
        }
        return -1;
    }

    static int findRotationPoint(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n-1;
        while(low <= high) {
            int mid = (low + high)/2;
            if(mid != n-1 && nums[mid] - nums[mid+1] > 0) {
                return mid;
            }
            else if(nums[mid] < nums[0] || nums[mid] <= nums[n-1] ) {
                //GO left
                high = mid -1 ;
            }
            else {
                //GO right
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void  main(String args[]) {
        int a[] = {3 ,1};
        int b[] = {5 ,6 ,7 ,};
        System.out.println(search(a , 3));

    }
}
