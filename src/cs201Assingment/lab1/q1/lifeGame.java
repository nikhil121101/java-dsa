package cs201Assingment.lab1.q1;

import java.io.IOException;
import java.util.Arrays;

public class lifeGame {
    public static class GFG {

        static void intializeGrid(char[][] grid , int m , int n) {
            for(int i = 0 ; i < m ; i++) {
                Arrays.fill(grid[i] , ' ');
            }
        }

        static void printGrid(char[][] grid , int m , int n) {
            for(int i = 0 ; i < m ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        static int valueOf(char c) {
            return c == ' ' ? 0 : 1;
        }

        static int aliveCount(char[][] grid , int m , int n , int i , int j) {
            int res = 0;
            if(i-1 >= 0) {
                res += valueOf(grid[i-1][j]);
                if(j-1 >= 0) {
                    res += valueOf(grid[i-1][j-1]) + valueOf(grid[i][j-1]);
                }
                if(j + 1 < n) {
                    res += valueOf(grid[i-1][j+1]) + valueOf(grid[i][j+1]);
                }
            }

            if(i+1 < m) {
                res += valueOf(grid[i+1][j]);
                if(j-1 >= 0) {
                    res += valueOf(grid[i+1][j-1]);
                }
                if(j + 1 < n) {
                    res += valueOf(grid[i+1][j+1]);
                }
            }
            return res;
        }

        static void animate(char grid[][] , int m , int n) {
            char temp[][] = new char[m][n];
            for(int i = 0 ; i < m ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    temp[i][j] = grid[i][j];
                }
            }
            for(int i = 0 ; i < m ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    int alive = aliveCount(grid , m , n , i , j);
                    if(grid[i][j] == ' ') {
                        if(alive == 3) {
                            temp[i][j] = '*';
                        }
                    }
                    else {
                        if(alive < 2 || alive > 3) {
                            temp[i][j] = ' ';
                        }
                    }
                }
            }
            for(int i = 0 ; i < m ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    grid[i][j] = temp[i][j];
                }
            }
        }

        static void makeLoaf(char grid[][] , int m , int n) {
            int midX = n / 2;
            int midY = m / 2;
            grid[midY-1][midX] = '*';
            grid[midY-1][midX + 1] = '*';
            grid[midY][midX-1] = '*';
            grid[midY][midX+2] = '*';
            grid[midY+1][midX] = '*';
            grid[midY+1][midX+2] = '*';
            grid[midY+2][midX+1] = '*';
        }

        static void makeToad(char grid[][] , int m , int n) {
            int midX = n / 2;
            int midY = m / 2;
            grid[midY][midX-1] = '*';
            grid[midY][midX + 1] = '*';
            grid[midY][midX] = '*';
            grid[midY+1][midX-2] = '*';
            grid[midY+1][midX-1] = '*';
            grid[midY+1][midX] = '*';
        }

        static void makeBecon(char grid[][] , int m , int n) {
            int midX = n / 2;
            int midY = m / 2;
            grid[midY][midX] = '*';
            grid[midY][midX - 1] = '*';
            grid[midY-1][midX] = '*';
            grid[midY-1][midX-1] = '*';
            grid[midY+1][midX+1] = '*';
            grid[midY+1][midX+2] = '*';
            grid[midY+2][midX+1] = '*';
            grid[midY+2][midX+2] = '*';
        }

        static void makeGlider(char grid[][] , int m , int n) {
            grid[2][1] = '*';
            grid[1][3] = '*';
            grid[2][3] = '*';
            grid[3][2] = '*';
            grid[3][3] = '*';
        }

        static void makeLightWeightShip(char grid[][] , int m , int n) {
            int midY = m / 2;
            grid[midY-1][4] = '*';
            grid[midY-1][5] = '*';
            grid[midY][2] = '*';
            grid[midY][3] = '*';
            grid[midY][5] = '*';
            grid[midY][6] = '*';
            grid[midY+1][2] = '*';
            grid[midY+1][3] = '*';
            grid[midY+1][4] = '*';
            grid[midY+1][5] = '*';
            grid[midY+2][4] = '*';
            grid[midY+2][3] = '*';
        }

        static void intializeGame(char grid[][] , int  m , int n , int t) throws InterruptedException {
            while(t-- > 0) {
                printGrid(grid , m , n);
                Thread.sleep(1000);
                animate(grid , m , n);
            }
        }

        public static void main(String args[]) throws IOException, InterruptedException {

            int m = 10 , n = 50;
            char[][] grid = new char[m][n];
            intializeGrid(grid , m , n);
            // make loaf
            //makeLoaf(grid , m , n);
            //makeToad(grid , m , n);
            //makeBecon(grid , m , n);
            makeLightWeightShip(grid , m , n);
            intializeGame(grid , m  , n , 30);
        }

    }
}
