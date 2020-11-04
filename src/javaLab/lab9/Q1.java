package javaLab.lab9;

import java.io.*;
import java.util.*;

public class Q1 {

    private static class ReadFile extends Thread {
        BufferedReader br1;
        BufferedReader br2;
        Map<String , Map<Integer , Set<Integer>>> wordsInFileOne;
        Map<String , Map<Integer , Set<Integer>>> wordsInFileTwo;

        ReadFile(String path1 , String path2) throws IOException {
            br1 = new BufferedReader(new FileReader(new File(path1)));
            br2 = new BufferedReader(new FileReader(new File(path2)));
        }

        @Override
        public void run() {
            int i = 1;
            wordsInFileOne = new TreeMap<>();
            wordsInFileTwo = new TreeMap<>();

            while(true) {
                String line = null;
                try {
                    line = br1.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(line);
                if(line == null || line.equals("")) {
                    break;
                }
                StringTokenizer st1 = new StringTokenizer(line);
                int j = 1;
                while(st1.hasMoreElements()) {
                    String word = st1.nextToken();
                    if (!wordsInFileOne.containsKey(word)) {
                        wordsInFileOne.put(word, new TreeMap<>());
                    }
                    if(!wordsInFileOne.get(word).containsKey(i)) {
                        wordsInFileOne.get(word).put(i , new TreeSet<>());
                    }
                    wordsInFileOne.get(word).get(i).add(j);
                    j++;
                }
                i++;
            }
            i = 1;
            while(true) {
                String line = null;
                try {
                    line = br2.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(line);
                if(line == null || line.equals("")) {
                    break;
                }
                StringTokenizer st2 = new StringTokenizer(line);
                int j = 1;
                while(st2.hasMoreElements()) {
                    String word = st2.nextToken();
                    if (!wordsInFileTwo.containsKey(word)) {
                        wordsInFileTwo.put(word, new TreeMap<>());
                    }
                    if(!wordsInFileTwo.get(word).containsKey(i)) {
                        wordsInFileTwo.get(word).put(i , new TreeSet<>());
                    }
                    wordsInFileTwo.get(word).get(i).add(j);
                    j++;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ReadFile fileReader = new ReadFile(args[0] , args[1]);

        fileReader.start();
        fileReader.join();

        for(Map.Entry<String , Map<Integer , Set<Integer>>> c1 : fileReader.wordsInFileOne.entrySet()) {
            for(Map.Entry<String , Map<Integer , Set<Integer>>> c2 : fileReader.wordsInFileTwo.entrySet()) {
                if(c1.getKey().equals(c2.getKey())) {
                    System.out.print(c1.getKey() + ": length " + c1.getKey().length() + ", appeared in ");
                    for(Map.Entry<Integer , Set<Integer>> e1 : c1.getValue().entrySet()) {
                        System.out.print(e1.getKey() + "th line ( at poisitions : " );
                        for(int x : e1.getValue()) {
                            System.out.print(x + ", ");
                        }
                        System.out.print(") , ");
                    }
                    System.out.print(" of " + args[0] + " and ");
                    for(Map.Entry<Integer , Set<Integer>> e1 : c2.getValue().entrySet()) {
                        System.out.print(e1.getKey() + "th line ( at poisitions : " );
                        for(int x : e1.getValue()) {
                            System.out.print(x + ", ");
                        }
                        System.out.print(") , ");
                    }
                    System.out.println(" of " + args[1]);
                }
            }
        }
    }
}
