//package algorithms.divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class ClosestPair {

    static float[] find_closest_pair(int[][] xSorted, int[][] ySorted , int n) {
        //System.out.println("xSorted");
        //System.out.println(Arrays.deepToString(xSorted));
        //System.out.println("ySorted");
        //System.out.println(Arrays.deepToString(ySorted));
        if(n <= 3) {
            //System.out.println("terminating");
            float[] res = termination(xSorted , n);
            //System.out.println("res");
            //System.out.println(Arrays.toString(res));
            return res;
        }
        int n1 = (int)Math.ceil(n / 2d) , n2 = (int)Math.floor(n / 2d);
        int[][] leftXSorted = new int[n1][3];
        int[][] leftYSorted = new int[n1][3];
        int[][] rightXSorted = new int[n2][3];
        int[][] rightYSorted = new int[n2][3];

        divide(leftXSorted , leftYSorted , rightXSorted , rightYSorted , xSorted , ySorted);

        //System.out.println("left xSorted");
        //System.out.println(Arrays.deepToString(leftXSorted));
        //System.out.println("left ySorted");
        //System.out.println(Arrays.deepToString(leftYSorted));
        //System.out.println("right xSorted");
        //System.out.println(Arrays.deepToString(rightXSorted));
        //System.out.println("right ySorted");
        //System.out.println(Arrays.deepToString(rightYSorted));

        float[] leftMin = find_closest_pair(leftXSorted , leftYSorted , n1);
        float[] rightMin = find_closest_pair(rightXSorted , rightYSorted , n2);
//        System.out.println("leftMin ");
//        System.out.println(Arrays.toString(leftMin));
//        System.out.println("rightMin ");
//        System.out.println(Arrays.toString(rightMin));

        float delta = Math.min(leftMin[6] , rightMin[6]);

//        System.out.println("delta " + delta);

        float[] mergedMin = mergedMin(ySorted , xSorted[n1 - 1][0] , n , delta);
//        System.out.println("mergedMin");
//        System.out.println(Arrays.toString(mergedMin));
        float[] res = minimumDistance(leftMin , rightMin , mergedMin);
//        System.out.println("res");
//        System.out.println(Arrays.toString(res));
        return res;
    }

    private static float[] minimumDistance(float[] leftMin, float[] rightMin, float[] mergedMin) {
        if(leftMin[6] < rightMin[6] && leftMin[6] < mergedMin[6]) {
            return leftMin;
        }
        else if(rightMin[6] < leftMin[6] && rightMin[6] < mergedMin[6]) {
            return rightMin;
        }
        else
        return mergedMin;
    }

    private static float[] mergedMin(int[][] ySorted, int midValue, int n, float delta) {
        int[][] legalPoints = new int[n][3];
        int k = 0;
        for(int i = 0 ; i < n ; i++) {
            if(Math.abs(midValue - ySorted[i][0]) <= delta) {
                legalPoints[k][0] = ySorted[i][0];
                legalPoints[k][1] = ySorted[i][1];
                legalPoints[k][2] = ySorted[i][2];
                k++;
            }
        }
        float res[] = new float[7];
        res[6] = Integer.MAX_VALUE;
        for(int i = 0 ; i < k ; i++) {
            int j = i - 1;
            int cnt = 6;
            while(j >= 0 && cnt > 0) {
                float dist = (float)Math.sqrt((legalPoints[i][0] - legalPoints[j][0])*(legalPoints[i][0] - legalPoints[j][0]) +
                        (legalPoints[i][1] - legalPoints[j][1])*(legalPoints[i][1] - legalPoints[j][1]));
                if(dist < res[6]) {
                    res[0] = legalPoints[i][0];
                    res[1] = legalPoints[i][1];
                    res[2] = legalPoints[i][2];
                    res[3] = legalPoints[j][0];
                    res[4] = legalPoints[j][1];
                    res[5] = legalPoints[j][2];
                    res[6] = dist;
                }
                j--;
                cnt--;
            }
        }
        return res;
    }

    private static float[] termination(int[][] pts, int n) {
        float dist1 = (float)Math.sqrt((pts[0][0] - pts[1][0]) * (pts[0][0] - pts[1][0]) + (pts[0][1] - pts[1][1]) * (pts[0][1] - pts[1][1]));
        if(n == 2) {
            return new float[]{pts[0][0] , pts[0][1] , pts[0][2] , pts[1][0] , pts[1][1] , pts[1][2] , dist1};
        }
        float dist2 = (float) Math.sqrt((pts[0][0] - pts[2][0]) * (pts[0][0] - pts[2][0]) + (pts[0][1] - pts[2][1]) * (pts[0][1] - pts[2][1]));
        float dist3 = (float) Math.sqrt((pts[2][0] - pts[1][0]) * (pts[2][0] - pts[1][0]) + (pts[2][1] - pts[1][1]) * (pts[2][1] - pts[1][1]));
        //System.out.println("dist1 - " + dist1 + " dist2 - " + dist2 + " dist3 - " + dist3);
        if(dist1 < dist2 && dist1 < dist3) {
            return new float[]{pts[0][0] , pts[0][1] , pts[0][2] , pts[1][0] , pts[1][1] , pts[1][2] , dist1};
        }
        else if(dist2 < dist3 && dist2 < dist1) {
            return new float[]{pts[0][0] , pts[0][1] , pts[0][2] , pts[2][0] , pts[2][1] , pts[2][2] , dist2};
        }
        else
            return new float[]{pts[2][0] , pts[2][1] , pts[2][2] , pts[1][0] , pts[1][1] , pts[1][2] , dist3};
    }

    private static void divide(int[][] leftXSorted, int[][] leftYSorted, int[][] rightXSorted, int[][] rightYSorted, int[][] xSorted, int[][] ySorted) {

        int n = xSorted.length , mid = n/2 , midValue = xSorted[mid][0];
        int left = 0 , right = 0;

        for(int i = 0 ; i < n ; i++) {
            if(ySorted[i][0] <=  midValue) {
                leftYSorted[left][0] = ySorted[i][0];
                leftYSorted[left][1] = ySorted[i][1];
                leftYSorted[left][2] = ySorted[i][2];
                left++;
            }
            else {
                rightYSorted[right][0] = ySorted[i][0];
                rightYSorted[right][1] = ySorted[i][1];
                rightYSorted[right][2] = ySorted[i][2];
                right++;
            }
        }

        for(int i = 0 ; i < n ; i++) {
            if(i <= mid) {
                leftXSorted[i][0] = xSorted[i][0];
                leftXSorted[i][1] = xSorted[i][1];
                leftXSorted[i][2] = xSorted[i][2];
            }
            else {
                rightXSorted[i - mid - 1][0] = xSorted[i][0];
                rightXSorted[i - mid - 1][1] = xSorted[i][1];
                rightXSorted[i - mid - 1][2] = xSorted[i][2];
            }
        }

    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int pts[][] = new int[n][3];
        for(int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pts[i][0] = Integer.parseInt(st.nextToken());
            pts[i][1] = Integer.parseInt(st.nextToken());
            pts[i][2] = i;
        }
        int[][] xSorted = pts.clone();
        Arrays.sort(xSorted, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(pts, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }

        });
        DecimalFormat df = new DecimalFormat("#.######");
        float res[] = find_closest_pair(xSorted , pts , n);
        System.out.println((int)Math.min(res[2] , res[5]) + " "  + (int)Math.max(res[2] , res[5]) + " " + df.format(res[6]));
    }

}
/*

5
0 0
0 1
100 45
2 3
9 9

5
0 0
-4 1
-7 -2
4 5
1 1

2
0 1
1 2

 */