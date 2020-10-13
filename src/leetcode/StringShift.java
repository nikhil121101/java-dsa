package leetcode;

public class StringShift {

    public static String stringShift(String s, int[][] shift) {
        String temp1 = "";
        String temp2 = "";
        for(int i = 0 ; i < shift.length ; i++) {
            if(shift[i][0] == 0) {
                temp2 = s.substring(0 , shift[i][1]);
                temp1 = s.substring(shift[i][1]);
                s = temp1 + temp2;
            }
            else {
                temp2 = s.substring(0 , s.length() - shift[i][1]);
                temp1 = s.substring(s.length() - shift[i][1]);
                s = temp1 + temp2;
            }
        }
        return s;
    }

    public static void main(String args[]) {

        String s = "abcdefg";
        int a[][] = {{1,1},{1,1},{0,2},{1,3}};
        System.out.println(stringShift(s , a));
    }
}
