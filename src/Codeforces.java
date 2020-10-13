import java.io.*;
import java.util.*;

public class Codeforces {

    static int[] subarrayWithMaxSum(int[] nums)
    {
        int endIndex = 0, currMax = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; ++i)
        {
            currMax = Math.max(nums[i],
                    nums[i] + currMax);
            if (currMax > globalMax)
            {
                globalMax = currMax;
                endIndex = i;
            }
        }

        int startIndex = endIndex;
        while (startIndex >= 0)
        {
            globalMax -= nums[startIndex];

            if (globalMax == 0)
                break;
            startIndex--;
        }
        int s = 0;
        for(int i = startIndex ; i <= endIndex ; i++) {
            s += nums[i];
        }
        return new int[]{startIndex , endIndex , s};
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        StringBuilder sb = new StringBuilder("");
//        int n = Integer.parseInt(br.readLine());
//        int res = 0;
//        boolean found = false;
//        for(int i = 0 ; i < n ; i++) {
//            st = new StringTokenizer(br.readLine());
//            if(st.nextToken().equals(st.nextToken())) {
//                res++;
//            }
//            else {
//                res = 0;
//            }
//            if(res == 3) {
//                found = true;
//            }
//        }
//        if(found) {
//            System.out.println("Yes");
//        }
//        else {
//            System.out.println("No");
//        }
        int k = 3;
        int x = 0;
        int a[] = {1 ,3 ,6 ,7 ,8};
        int n = a.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(a);
        for(int i = 0 ; i < n ; i++) {
            list.add(a[i]);
        }
        boolean contains = false;
        for(int i = 0 ; i < n ;i++) {
            if(a[i] > x) {
                break;
            }
            if (a[i] == x) {
                contains = true;
                break;
            }
        }
        System.out.println(Arrays.toString(a));
        System.out.println(list);
        if(!contains) {
            boolean added = false;
            list.remove(k);
            for(int i = 0 ; i < n - 1 ; i++) {
                if(list.get(i) >= x) {
                    list.add(i , x);
                    added = true;
                    break;
                }
            }
            if(!added)
                list.add(x);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(list);
    }
}
/*
5
2 1
1 1
500 4
217871987498122 10
100000000000000001 1
 */
