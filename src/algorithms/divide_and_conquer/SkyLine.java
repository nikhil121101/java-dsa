package algorithms.divide_and_conquer;
import java.util.ArrayList;
import java.util.Arrays;

public class SkyLine {

    private static class point {
        int x , y;
        point(int x , int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<point> skyline(int a[][] , int i , int j) {
        System.out.println("i , j -> " + i + " , " + j);
        ArrayList<point> res = new ArrayList<point>();
        if(i == j) {
            System.out.println("terminating");
            res.add(new point(a[i][0] , a[i][2]));
            res.add(new point(a[i][1] , a[i][2]));
            printPoints(res);
            return res;
        }
        int mid = (i+j)/2;
        ArrayList<point> leftRes = skyline(a , i , mid);
        ArrayList<point> rightRes = skyline(a , mid + 1 , j);
        return Combine(a , mid , leftRes , rightRes);
    }

    static ArrayList<point> Combine(int[][] a, int mid, ArrayList<point> leftRes, ArrayList<point> rightRes) {
        System.out.println("Combining ");
        System.out.println("left -> ");
        printPoints(leftRes);
        System.out.println("right -> ");
        printPoints(rightRes);
        //TODO
        int n1 = leftRes.size() , n2 = rightRes.size();
        ArrayList<point> res = new ArrayList<point>();
        for(int i = 0 ; i < n1 - 1 ; i++) {
            res.add(new point(leftRes.get(i).x , leftRes.get(i).y));
        }
        if(leftRes.get(n1-1).x < rightRes.get(0).x) {
            res.add(new point(leftRes.get(n1-1).x , leftRes.get(n1-1).y));
            res.add(new point((leftRes.get(n1-1)).x , 0));
            res.add(new point((rightRes.get(0)).x , 0));
            res.add(new point(rightRes.get(0).x , rightRes.get(0).y));
        }
        else if(leftRes.get(n1-1).x > rightRes.get(0).x) {
            if(leftRes.get(n1-1).y > rightRes.get(0).y) {
                res.add(new point(leftRes.get(n1-1).x , leftRes.get(n1-1).y));
                res.add(new point(leftRes.get(n1-1).x , res.get(0).y));
            }
            if(leftRes.get(n1-1).y < rightRes.get(0).y) {
                res.add(new point(rightRes.get(0).x , leftRes.get(n1-1).y));
                res.add(new point(rightRes.get(0).x , rightRes.get(0).y));
            }
        }
        else {
            if(leftRes.get(n1-1).y != rightRes.get(0).y) {
                res.add(new point(leftRes.get(n1-1).x , leftRes.get(n1-1).y));
                res.add(new point(rightRes.get(0).x , rightRes.get(0).y));
            }
        }
        for(int i = 1 ; i < n2 ; i++) {
            res.add(rightRes.get(i));
        }
        System.out.println("after combining res -> ");
        printPoints(res);
        return res;
    }

    static void printPoints(ArrayList<point> result) {
        for(int i = 0 ; i < result.size() ; i++) {
            System.out.print("(" + result.get(i).x + "," + result.get(i).y + ") , ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]

        int[][] pts = new int[][]{ {2 ,9 ,10}, {3 ,7 ,15}, {5 ,12 ,12}, {15 ,20 ,10}, {19 ,24 ,8} };
        int n = 5;
        ArrayList<point> result = skyline(pts , 0 , n-1);
        result.add(new point(pts[n-1][1] , 0));
        printPoints(result);
    }


}
