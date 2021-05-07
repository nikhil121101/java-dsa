package algorithms.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NQueen {
	static List<List<String>> res = new ArrayList<>();;
	static int[][] board ;
	static int n;

	static void printBoard() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void brutForce(int queens , int i , int j) {
		if(i == n) {
			return;
		}
		if(j == n) {
			brutForce(queens , i + 1 , 0);
		}
		if(queens == 0) {
			ArrayList<String> temp = new ArrayList<>();
			for(int k = 0 ; k < n ; k++) {
				StringBuilder s = new StringBuilder();
				for(int l = 0 ; l < n ; l++) {
					if(board[k][l] == 0) {
						s.append('*');
					}
					else {
						s.append('Q');
					}
				}
				temp.add(s.toString());
			}
			res.add(temp);
			return;
		}
		if(!is_Attacked(i , j)) {
			board[i][j] = 1;
			brutForce(queens-1 , i , j+1);
		}
		board[i][j] = 0;
		brutForce(queens , i , j+1);
	}

	static boolean is_Attacked(int a , int b) {
		//System.out.println("ist attacked - " + a + " , " + b);
//		if(!(a >= 0 && a < n && b >= 0 && b < n)) {
//			return false;
//		}
		for(int i = 0 ; i < a ; i++) {
        	if(board[i][b] == 1) {
				//System.out.println(i + " , " + b  + " = 1");
				return true;
			}
        }
		int i = a - 1 , j = b - 1;
		while(i >= 0 && i < n && j >= 0 && j < n) {
			if(board[i][j] == 1) {
				//System.out.println(i + " , " + j  + " = 1");
				return true;
			}
			i--;
			j--;
		}
		i = a - 1;
		j = b + 1;
		while(i >= 0 && i < n && j >= 0 && j < n) {
			if(board[i][j] == 1) {
				//System.out.println(i + " , " + j  + " = 1");
				return true;
			}
			i--;
			j++;
		}
		return false;
	}

	static void backtracking(int row) {
		//System.out.println("row - > " + row);
		//System.out.println("board");
		//printBoard();
        if(row == n) {
        	ArrayList<String> temp = new ArrayList<>();
        	for(int i = 0 ; i < n ; i++) {
        		StringBuilder s = new StringBuilder();
        		for(int j = 0 ; j < n ; j++) {
        			if(board[i][j] == 0) {
        				s.append('*');
					}
        			else {
        				s.append('Q');
					}
				}
        		temp.add(s.toString());
			}
        	res.add(temp);
        	return;
		}
		for(int i = 0 ; i < n ; i++) {
			if(!is_Attacked(row , i)) {
				board[row][i] = 1;
				//System.out.println("unattacked here");
				//printBoard();
				backtracking(row + 1);
				board[row][i] = 0 ;
			}
		}
    }
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		brutForce(n , 0 , 0);
		for(List<String> list : res) {
			for(String s : list) {
				System.out.println(s);
			}
			System.out.println();
		}
	}

}
