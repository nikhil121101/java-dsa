package codeChefApril;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interactive {
    private static class sol {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        boolean inRange(int r1 , int r2 , int c1 , int c2) {
            return r1 <= r2 && c1 <= c2;
        }

        int query(int r1 , int r2 , int c1 , int c2) throws IOException {
            System.out.println("1 " + r1 + " " + r2 + " " + c1 + " " + c2);
            return Integer.parseInt(bf.readLine());
        }

        int find(int[][] grid, int r1 , int r2 , int c1 , int c2) throws IOException {
            if(!inRange(r1 , r2 , c1 , c2)) {
                return -1;
            }
            int q = query(r1 , c1 , r2 , c2);
            if(q == 0) {
                return 0;
            }
            if(r1 == r2 && c1 == c2) {
                grid[r1-1][c1-1] = q;
                return q;
            }
            int mid;
            if(r2 - r1 > c2 - c1) {
                mid = (r1 + r2) / 2;
                int temp = find(grid , r1 , mid , c1 , c2);
                if(temp == q) {
                    return q;
                }
                find(grid , mid + 1 , r2 , c1 , c2);
            }
            return q;
        }

        int Code(int N , int P) throws IOException {
            int[][] grid = new int[60][60];
            int mid = (1+N) / 2;
            int temp = find(grid , 1 , mid , 1 , N);
            if(!(temp == 36 * P)) {
                find(grid , mid + 1 , N , 1 , N);
            }
            System.out.println("2");
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            return Integer.parseInt(bf.readLine());
        }
    }

//r1 > 0 && r2 > 0 && r2 < 61 && c1 > 0 && c2 > 0 && c2 < 61 &&


    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while(t-- != 0) {
            String s[] = bf.readLine().split(" ");
            int N = Integer.parseInt(s[0]);
            int P = Integer.parseInt(s[1]);
            if(new sol().Code(N , P) == -1) {
                break;
            }
        }
    }
}
