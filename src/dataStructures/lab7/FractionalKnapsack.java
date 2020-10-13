package dataStructures.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FractionalKnapsack {
    static double solve(int w[], int v[], int n, int W) {
        double[][] valuePerKg = new double[n][2];
        for(int i = 0 ; i < n ; i++) {
            valuePerKg[i][0] = v[i] / (double)w[i];
            valuePerKg[i][1] = i;
        }
        Arrays.sort(valuePerKg, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o2[0], o1[0]);
            }
        });
        double points = 0;
        int i = 0;
        while (i < n && W > 0) {
            int cur = (int)valuePerKg[i][1];
                points += Math.min(W , w[cur]) * valuePerKg[i][0];
                W -= Math.min(W , w[cur]);
            i++;
        }
        return points;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] v = new int[n];
            int[] w = new int[n];
            for(int i = 0 ; i < n ; i++) {
                v[i] = Integer.parseInt(st.nextToken());
                w[i] = Integer.parseInt(st.nextToken());
            }
            double roundOff = Math.round(solve(w , v , n , W)*100)/(double)100;
            System.out.println(roundOff);
        }
    }

}
