package algorithms.divide_and_conquer;

import java.util.Arrays;

public class MatrixMultiplication {

    static void normalMethod(int[][] c , int a[][] , int b[][] , int n) {
        long start = System.currentTimeMillis();
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                int s = 0;
                for(int k = 0 ; k < n ; k++) {
                    s += a[i][k] * b[k][j];
                }
                c[i][j] = s;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("normal " +
                (end - start) + "ms");
    }

    static void sumDAC(String op , int[][] c , int sr , int sc , int n , int[][] a , int[][] b) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(op.equals("add"))
                    c[i + sr][j + sc] = a[i][j] + b[i][j];
                else
                    c[i + sr][j + sc] = a[i][j] - b[i][j];
            }
        }
    }

    static void divideAndConquerMethod(int res[][] , int a[][] , int b[][] , int n , int r1A , int r2A , int c1A , int c2A ,
                                       int r1B , int r2B , int c1B, int c2B) {
        long start = System.currentTimeMillis();
        //System.out.println(n);
        //System.out.println(" r1A == r2A - " + r1A + "==" + r2A + " c2A == c1A " + c2A + "==" + c1A);
        //System.out.println(" r1B == r2B - " + r1B + "==" + r2B + " c2B == c1B " + c2B + "==" + c1B);
        if(r1A == r2A && c2A == c1A) {
            res[0][0] = a[r1A][c1A] * b[r1B][c1B];
            return;
        }
        int midXA = (r1A + r2A) / 2;
        int midYA = (c1A + c2A) / 2;
        int midXB = (r1B + r2B) / 2;
        int midYB = (c1B + c2B) / 2;
        int[][] p1 = new int[n/2][n/2] , p2 = new int[n/2][n/2];
        divideAndConquerMethod(p1 , a , b , n / 2 , r1A , midXA , c1A , midYA , r1B , midXB , c1B , midYB);
        divideAndConquerMethod(p2 , a , b , n / 2 , r1A , midXA , midYA + 1 , c2A , midXB + 1 , r2B , c1B , midYB);
        sumDAC("add" , res , 0 , 0 , n/2 , p1 , p2);
        divideAndConquerMethod(p1 , a , b , n / 2 , r1A , midXA , c1A , midYA , r1B , midXB , midYB + 1 , c2B);
        divideAndConquerMethod(p2 , a , b , n / 2 , r1A , midXA , midYA + 1 , c2A , midXB + 1 , r2B , midYB + 1 , c2B);
        sumDAC("add" , res , 0 , n/2 , n/2 , p1 , p2);

        divideAndConquerMethod(p1 , a , b , n / 2 , midXA + 1 , r2A , c1A , midYA , r1B , midXB , c1B , midYB);
        divideAndConquerMethod(p2 , a , b , n / 2 , midXA + 1 , r2A , midYA + 1 , c2A , midXB + 1 , r1B , c1B , midYB);
        sumDAC("add" , res , n/2 , 0 , n/2 , p1 , p2);
        divideAndConquerMethod(p1 , a , b , n / 2 , midXA + 1 , r2A , c1A , midYA , r1B , midXB , midYB + 1 , c2B);
        divideAndConquerMethod(p2 , a , b , n / 2 , midXA + 1 , r2A , midYA + 1 , c2A , midXB + 1 , r2B , midYB + 1 , c2B);
        sumDAC("add" , res , n/2 , n/2 , n/2 , p1 , p2);
        long end = System.currentTimeMillis();
        System.out.println("divide and conq " + (end - start) + "ms");
    }

    static void addSub(String op , int[][] c , int a[][] , int b[][] , int n) {
        for(int i = 0 ; i  < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(op.equals("add"))
                    c[i][j] = a[i][j] + b[i][j];
                else
                    c[i][j] = a[i][j] - b[i][j];
            }
        }
    }
    static void printArray(int[][] arr, int n)
    {
        int i, j;
        for(i=0; i<n; i++)
        {
            for(j=0; j<n; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    static void strassensMethod(int res[][] , int a[][] , int b[][] , int n) {
        long start = System.currentTimeMillis();
//        System.out.println("n " +  n);
//        System.out.println("r1A , r2A - (" + r1A + " , " + r2A + ") c1A , c2A (" + c1A + "," + c2A + ")");
//        System.out.println("r1B , r2B - (" + r1B + " , " + r2B + ") c1B , c2B (" + c1B + "," + c2B + ")");
        if(n == 1) {
            res[0][0] = a[0][0] * b[0][0];
            return;
        }
//
//        int midXA = n / 2 - 1;
//        int midYA = n / 2 - 1;
//        int midXB = n / 2 - 1;
//        int midYB = n / 2 - 1;

        int a1[][] = new int[n/2][n/2];
        int a2[][] = new int[n/2][n/2];
        int a3[][] = new int[n/2][n/2];
        int a4[][] = new int[n/2][n/2];
        int b1[][] = new int[n/2][n/2];
        int b2[][] = new int[n/2][n/2];
        int b3[][] = new int[n/2][n/2];
        int b4[][] = new int[n/2][n/2];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(i <= n/2 - 1 && j <= n/2 - 1) {
                    a1[i][j] = a[i][j];
                    b1[i][j] = b[i][j];
                }
                else if(i <= n/2 - 1) {
                    a2[i][j-n/2] = a[i][j];
                    b2[i][j-n/2] = b[i][j];
                }
                else if(j <= n/2 - 1) {
                    a3[i-n/2][j] = a[i][j];
                    b3[i-n/2][j] = b[i][j];
                }
                else {
                    a4[i-n/2][j-n/2] = a[i][j];
                    b4[i-n/2][j-n/2] = b[i][j];
                }
            }
        }

        int[][] p1 = new int[n/2][n/2] , p2 = new int[n/2][n/2] , p3 = new int[n/2][n/2] , p4 = new int[n/2][n/2];
        int[][] p5 = new int[n/2][n/2] , p6 = new int[n/2][n/2] , p7 = new int[n/2][n/2];
        int[][] temp1 = new int[n/2][n/2] , temp2 = new int[n/2][n/2] , temp3 = new int[n/2][n/2] , temp4 = new int[n/2][n/2];
        int[][] temp5a = new int[n/2][n/2] , temp5b = new int[n/2][n/2] , temp6a = new int[n/2][n/2] , temp6b = new int[n/2][n/2];
        int[][] temp7a = new int[n/2][n/2] , temp7b = new int[n/2][n/2];
        addSub("sub" , temp1 , b2 , b4 , n/2);
        //System.out.println("temp1");
        //System.out.println(Arrays.deepToString(temp1));

        strassensMethod(p1 , a1 , temp1 , n / 2);

//        System.out.println("p1");
//        System.out.println(Arrays.deepToString(p1));

        addSub("add" , temp2 , a1 , a2 , n/2);

//        System.out.println("temp2");
//        System.out.println(Arrays.deepToString(temp2));

        strassensMethod(p2 , temp1 ,  b4 ,n/2);

//        System.out.println("p2");
//        System.out.println(Arrays.deepToString(p2));

        addSub("add" , temp3 , a3 , a4 , n/2);

//        System.out.println("temp3");
//        System.out.println(Arrays.deepToString(temp3));

        strassensMethod(p3 , temp3 , b1 , n/2);

//        System.out.println("p3");
//        System.out.println(Arrays.deepToString(p3));

        addSub("sub" , temp4 , b3 , b1 , n/2);

//        System.out.println("temp4");
//        System.out.println(Arrays.deepToString(temp4));

        strassensMethod(p4 , a4 , temp4 , n/2);

//        System.out.println("p4");
//        System.out.println(Arrays.deepToString(p4));

        addSub("add" , temp5a , a1 , a4 , n/2);
        addSub("add" , temp5b , b1 , b4 , n/2);

//        System.out.println("temp5a");
//        System.out.println(Arrays.deepToString(temp5a));
//        System.out.println("temp5b");
//        System.out.println(Arrays.deepToString(temp5b));

        strassensMethod(p5 , temp5a , temp5b , n/2);

//        System.out.println("p5");
//        System.out.println(Arrays.deepToString(p5));

        addSub("sub" , temp6a , a2 , a4 , n/2);
        addSub("add" , temp6b , b3 , b4 , n/2);

//        System.out.println("temp6a");
//        System.out.println(Arrays.deepToString(temp6a));
//        System.out.println("temp6b");
//        System.out.println(Arrays.deepToString(temp6b));

        strassensMethod(p6 , temp6a , temp6b , n/2);

//        System.out.println("p6");
//        System.out.println(Arrays.deepToString(p6));

        addSub("sub" , temp7a , a1 , a3 , n/2);
        addSub("add" , temp7b , b1 , b2 , n/2);
//        System.out.println("temp7a");
//        System.out.println(Arrays.deepToString(temp7a));
//        System.out.println("temp7b");
//        System.out.println(Arrays.deepToString(temp7b));
        strassensMethod(p7 , temp7a , temp7b , n/2);

//        System.out.println("p7");
//        System.out.println(Arrays.deepToString(p7));
        printArray(p1 , n/2);
        printArray(p2 , n/2);
        printArray(p3 , n/2);
        printArray(p4 , n/2);
        printArray(p5 , n/2);
        printArray(p6 , n/2);
        printArray(p7 , n/2);



        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(i <= n/2 - 1 && j <= n/2 - 1) {
                    res[i][j] = p5[i][j] + p4[i][j] - p2[i][j] + p6[i][j];
                }
                else if(i <= n/2 - 1) {
                    res[i][j] = p1[i][j - n/2] + p2[i][j - n/2];
                }
                else if(j <= n/2 - 1) {
                    res[i][j] = p3[i - n/2][j] + p4[i - n/2][j];
                }
                else {
                    res[i][j] = p1[i - n/2][j - n/2] + p5[i - n/2][j - n/2] - p3[i - n/2][j - n/2] - p7[i - n/2][j - n/2];
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("strassen " +
                (end - start) + "ms");
    }


    public static void main(String[] args) {
        int a[][] = {{1 , 2 , 3 , 1} , {2 , 1 , 1 , 1} , {3 , 2 , 3 , 1} , {2 , 3 , 2 , 2}};
        int b[][] = {{3 , 1 , 1 , 2} , {3 , 1 , 1 , 2} , {1 , 1 , 3 , 1} , {1 , 1 , 1 , 2}};
        int n = 4;
//        int a[][] = new int[1024][1024];
//        int b[][] = new int[1024][1024];
//        for(int i = 0 ; i < n ; i++) {
//            for(int j = 0 ; j < n ; j++) {
//                a[i][i] = (int)(Math.random() * (10 - 3 + 1) + 3);
//                b[i][i] = (int)(Math.random() * (10 - 3 + 1) + 3);
//            }
//        }
        int[][] c = new int[n][n];
        divideAndConquerMethod(c , a , b , 4 , 0 , n-1 , 0 , n-1 , 0 , n-1 , 0 , n-1);
        System.out.println(Arrays.deepToString(c));
        for(int  i = 0 ; i < n ; i++) {
            Arrays.fill(c[i] , 0);
        }
        normalMethod(c , a , b , n);
        System.out.println(Arrays.deepToString(c));
        for(int  i = 0 ; i < n ; i++) {
            Arrays.fill(c[i] , 0);
        }
        strassensMethod(c , a , b, n);
        System.out.println(Arrays.deepToString(c));
    }

}
