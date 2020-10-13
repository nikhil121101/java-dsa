package codeForces;
import java.util.*;

class FirstUnique {

    HashMap<Integer , Integer> queue;
    Queue<Integer> actualQueue;
    int index;
    public FirstUnique(int[] nums) {
        queue = new HashMap<>();
        actualQueue = new LinkedList<Integer>();
        int index = 0;
        for(int i = 0 ; i < nums.length ; i++) {
            actualQueue.add(nums[i]);
            if(queue.containsKey(nums[i])) {
                queue.replace(nums[i] , queue.get(nums[i]) + 1);
            }
            else {
                queue.put(nums[i] , 1);
            }
        }
    }

    public int showFirstUnique() {
        Iterator<Integer> integerIterator = actualQueue.iterator();
        while (integerIterator.hasNext()) {
            int curValue = integerIterator.next();
            if(queue.get(curValue) == 1) {
                return curValue;
            }
        }
        return -1;
    }

    public void add(int value) {
        actualQueue.add(value);
        if(queue.containsKey(value)) {
            queue.replace(value , queue.get(value) + 1 );
        }
        else {
            queue.put(value , 1);
        }
    }
}

public class CoinsChange {

    int CoinChangeWays(int s , int coins[]) {
        Arrays.sort(coins);
        int k = coins.length;
        int dp[][] = new int[s + 1][k+1];
        dp[0][0] = 1;
        int prevCoin = -1;
        for(int i = 1 ; i < s + 1 ; i++) {
            int j = 0;
//            while(j < coins.length && i - coins[j] > 0) {
//                prevCoin = coin[j];
//                dp[i] += dp[i - coins[j]];
//                j++;
//            }
            while(j < coins.length && i - coins[j] >= 0) {
                boolean include = true;
                for(int t = 1 ; t < k + 1 ; t++) {
                    if(dp[i - coins[j]][k] ==1 && coins[k - 1] > coins[j]) {
                        include = false;
                    }
                }
                if(include) {

                }
            }
        }
        return 0;
    }

    public  static void main(String args[]) {
        int a[] = new int[]{698,866,349,680,733,916,961,652,161,960,417,813,474,170,802,406,442,454,780,886,899,367,786,157,953,621,29,273,485,55,563,275,343,157,715,683,608,932,874,241,796,877,845,26,719,167,415,287,411,95,196,118,291,811,969,27,805,323,707,625,651,588,445,690,706,694,317,978,327,764,366,18,104,753,791,94,912,171,923,969,457,717,694,147,329,12,84,650,592,383,506,924,939,495,999,120,529,117,446,830,740,108,791,518,83,164,606,172,783,808};
        int b[] = new int[]{809};
        FirstUnique Class = new FirstUnique(b);
        System.out.println(Class.showFirstUnique());
        Class.add(809);
        System.out.println(Class.showFirstUnique());
        //System.out.println(Class.showFirstUnique());
    }


}
