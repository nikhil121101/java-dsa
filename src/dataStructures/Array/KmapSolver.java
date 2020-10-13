package dataStructures.Array;

public class KmapSolver {

    static String binary(int i , int m) {
        StringBuilder res = new StringBuilder(Integer.toBinaryString(i));
        while(res.length() != m) {
            res.insert(0, "0");
        }
        return res.toString();
    }

    static int zeroOrOne(int i , int j , String c , int m , int n) {
        String binaryForm = binary(i , m) + binary(j , n);
        int ind;
        boolean bol = true;
        if(c.length() == 1) {
            ind = (int)c.charAt(0) - 65;
        }
        else {
            ind = (int)c.charAt(0) - 65;
            bol = false;
        }
        if(bol && binaryForm.charAt(ind) == '1' || !bol && binaryForm.charAt(ind) == '0') {
            return 1;
        }
        else {
            return 0;
        }
    }

    static String K_map_solver(String s , int degree) {
        int m = degree / 2;
        int n = degree - (degree / 2);
        int M = (int)Math.pow(2 , m);
        int N = (int)Math.pow(2 , n);
        char[][] kMap = new char[M][N];
        String[] minTerms = s.split("//+");
        for(int i = 0 ; i < minTerms.length ; i++) {
        }
        return "";
    }

}
