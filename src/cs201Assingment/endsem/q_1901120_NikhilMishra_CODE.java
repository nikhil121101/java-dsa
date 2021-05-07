package cs201Assingment.endsem;

import java.io.*;
import java.util.*;

public class q_1901120_NikhilMishra_CODE {

    static int[][] graph;

    static List<int[]> cycles = new ArrayList<int[]>();

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("1901120_nikhilMishra_output.txt")));
        sb = new StringBuilder("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[m][2];
        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) + 1;
            int v = Integer.parseInt(st.nextToken()) + 1;
            graph[i][0] = u;
            graph[i][1] = v;
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                findNewCycles(new int[]{graph[i][j]});
            }
        }

//        ArrayList<TreeSet<Integer>> res = new ArrayList<>();
//        TreeSet<String> ids = new TreeSet<>();
//        for (int[] cy : cycles) {
//            String id = getID(cy);
//            ids.add(id);
//        }
        sb.append(cycles.size()).append("\n");
        for (int[] cy : cycles)
        {
            String s = "" + (cy[0] - 1);

            for (int i = 1; i < cy.length; i++)
            {
                s += " " + (cy[i] - 1);
            }

            o(s);
        }
//        System.out.println(ids.size());
//        for(String s : ids) {
//            System.out.println(s);
//        }
        //System.out.println(sb);
        bw.write(sb.toString());
        bw.close();
    }

    private static String getID(int[] cy) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int x : cy) {
            set.add(x);
        }
        StringBuilder res = new StringBuilder();
        for(int x : set) {
            res.append(x-1).append(" ");
        }
        return res.toString();
    }

    static void findNewCycles(int[] path)
    {
        int n = path[0];
        int x;
        int[] sub = new int[path.length + 1];

        for (int i = 0; i < graph.length; i++)
            for (int y = 0; y <= 1; y++)
                if (graph[i][y] == n)
                {
                    x = graph[i][(y + 1) % 2];
                    if (!visited(x, path))
                    {
                        sub[0] = x;
                        System.arraycopy(path, 0, sub, 1, path.length);
                        findNewCycles(sub);
                    }
                    else if ((path.length > 2) && (x == path[path.length - 1]))
                    {
                        int[] p = normalize(path);
                        int[] inv = invert(p);
                        if (isNew(p) && isNew(inv))
                        {
                            cycles.add(p);
                        }
                    }
                }
    }

    static Boolean equals(int[] a, int[] b)
    {
        Boolean ret = (a[0] == b[0]) && (a.length == b.length);

        for (int i = 1; ret && (i < a.length); i++)
        {
            if (a[i] != b[i])
            {
                ret = false;
            }
        }

        return ret;
    }

    static int[] invert(int[] path)
    {
        int[] p = new int[path.length];

        for (int i = 0; i < path.length; i++)
        {
            p[i] = path[path.length - 1 - i];
        }

        return normalize(p);
    }

    static int[] normalize(int[] path)
    {
        int[] p = new int[path.length];
        int x = smallest(path);
        int n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x)
        {
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    static Boolean isNew(int[] path)
    {
        Boolean ret = true;

        for(int[] p : cycles)
        {
            if (equals(p, path))
            {
                ret = false;
                break;
            }
        }

        return ret;
    }

    static void o(String s)
    {
        sb.append(s).append("\n");
    }

    static int smallest(int[] path)
    {
        int min = path[0];

        for (int p : path)
        {
            if (p < min)
            {
                min = p;
            }
        }

        return min;
    }

    static Boolean visited(int n, int[] path)
    {
        Boolean ret = false;

        for (int p : path)
        {
            if (p == n)
            {
                ret = true;
                break;
            }
        }

        return ret;
    }
}
