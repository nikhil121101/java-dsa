package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

class Pair2 implements Comparator<Pair2> {
    int i ;
    int j ;
    Pair2( int i , int j ) {
        this.i = i ;
        this.j = j ;
    }

    public Pair2() {

    }

    public int compare (Pair2 p1 , Pair2 p2) {
        return p1.i - p2.i;
    }
}

public class noOfIslands {


    static void printStack(Stack<Pair2> stack) {
        Iterator<Pair2> iterator = stack.iterator();
        while(iterator.hasNext()) {
            Pair2 pair = iterator.next();
            System.out.print(pair.i + " , " + pair.j + " ");
        }
        System.out.println();
    }

    public static int numIslands(char[][] grid) {
        int islads = 0 ;
        int m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], false);
        }
        Stack<Pair2> stack = new Stack<Pair2>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    visited[i][j] = true;
                    stack.push(new Pair2(i, j));
                    Pair2 curPair = null;
                    while (!stack.isEmpty()) {
                        //printStack(stack);
                        curPair = stack.pop();
                        //System.out.println("curPair -> " + curPair.i + " , " + curPair.j);
                        if ( curPair.j - 1 >= 0 && grid[curPair.i][curPair.j - 1] == '1' && visited[curPair.i][curPair.j - 1] == false) {
                            //LEFT
                           // System.out.println("Left");
                            stack.push(new Pair2(curPair.i, curPair.j - 1));
                            visited[curPair.i][curPair.j - 1] = true;
                        }
                        if ( curPair.j + 1 < n && grid[curPair.i][curPair.j + 1] == '1' && visited[curPair.i][curPair.j+1] == false) {
                            //RIGHT
                            //System.out.println("right");
                            stack.push(new Pair2(curPair.i, curPair.j + 1));
                            visited[curPair.i][curPair.j + 1] = true;
                        }
                        if ( curPair.i + 1 < m && grid[curPair.i + 1][curPair.j] == '1' && visited[curPair.i+1][curPair.j] == false) {
                            //DOWN
                            //System.out.println("down");
                            stack.push(new Pair2(curPair.i + 1, curPair.j));
                            visited[curPair.i + 1][curPair.j] = true;
                        }
                        if ( curPair.i - 1 >= 0 && grid[curPair.i - 1][curPair.j] == '1' && visited[curPair.i-1][curPair.j] == false) {
                            //UP
                            //System.out.println("up");
                            stack.push(new Pair2(curPair.i - 1, curPair.j));
                            visited[curPair.i - 1][curPair.j] = true;
                        }

                    }
                    islads++;
                }

            }
        }
        return islads;
    }


    public static void main(String args[]) {
        char a[][] = { {'1' ,'1' ,'1' ,'1' ,'0'} ,
                      {'1' ,'1' ,'0' ,'1' ,'0'} ,
                      {'1' ,'1' ,'0' ,'0' ,'0'} ,
                      {'0' ,'0' ,'0' ,'0' ,'0'} };
        System.out.println(numIslands(a));
    }


}

