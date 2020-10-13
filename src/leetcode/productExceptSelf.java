package leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class productExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        final int n = nums.length;
        for(int i = 1 ; i < n ; i++) {
            nums[i] *= nums[i-1];
        }
        int temp = 1;
        for(int i = 0 ; i < n ; i++) {
            if(i == 0) {
                temp = nums[i];
                nums[i] = nums[n-1] / nums[i];
                continue;
            }
            nums[i] = temp * nums[n-1] / nums[i];
            temp = nums[i];
        }
        return  nums;
    }

}
