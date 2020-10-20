package javaLab.lab8;

import java.io.*;
import java.util.*;

public class Question_one {

    static class Container {
        String word;
        int line;
        Container(String word , int line) {
            this.word = word;
            this.line = line;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(new File(args[0])));
        BufferedReader br2 = new BufferedReader(new FileReader(new File(args[1])));
        int i = 1;
        Map<String , ArrayList<Integer>> wordsInFileOne = new HashMap<>();
        Map<String , ArrayList<Integer>> wordsInFileTwo = new HashMap<>();

        while(true) {
            String line = br1.readLine();
            //System.out.println(line);
            if(line == null || line.equals("")) {
                break;
            }
            StringTokenizer st1 = new StringTokenizer(line);
            while(st1.hasMoreElements()) {
                String word = st1.nextToken();
                if (!wordsInFileOne.containsKey(word)) {
                    wordsInFileOne.put(word, new ArrayList<>());
                }
                wordsInFileOne.get(word).add(i);
            }
            i++;
        }
        i = 1;
        while(true) {
            String line = br2.readLine();
            //System.out.println(line);
            if(line == null || line.equals("")) {
                break;
            }
            StringTokenizer st2 = new StringTokenizer(line);
            while(st2.hasMoreElements()) {
                String word = st2.nextToken();
                if (!wordsInFileTwo.containsKey(word)) {
                    wordsInFileTwo.put(word, new ArrayList<>());
                }
                wordsInFileTwo.get(word).add(i);
            }
            i++;
        }
        for(Map.Entry<String , ArrayList<Integer>> c1 : wordsInFileOne.entrySet()) {
            for(Map.Entry<String , ArrayList<Integer>> c2 : wordsInFileTwo.entrySet()) {
                if(c1.getKey().equals(c2.getKey())) {
                    System.out.print(c1.getKey() + ": length " + c1.getKey().length() + ", appeared in ");
                    for(int j : c1.getValue()) {
                        System.out.print(j + "th ," );
                    }
                    System.out.print("line of " + args[0] + " and ");
                    for(int j : c2.getValue()) {
                        System.out.print(j + "th ," );
                    }
                    System.out.println("line of " + args[1]);
                }
            }
        }
    }
}
