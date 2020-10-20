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
        ArrayList<Container> wordsInFileOne = new ArrayList<>();
        ArrayList<Container> wordsInFileTwo = new ArrayList<>();
        while(true) {
            String line = br1.readLine();
            System.out.println(line);
            if(line == null || line.equals("")) {
                break;
            }
            StringTokenizer st1 = new StringTokenizer(line);
            while(st1.hasMoreElements()) {
                wordsInFileOne.add(new Container(st1.nextToken() , i));
            }
            i++;
        }
        i = 1;
        while(true) {
            String line = br2.readLine();
            System.out.println(line);
            if(line == null || line.equals("")) {
                break;
            }
            StringTokenizer st2 = new StringTokenizer(line);
            while(st2.hasMoreElements()) {
                wordsInFileTwo.add(new Container(st2.nextToken() , i));
            }
            i++;
        }
        for(Container c1 : wordsInFileOne) {
            for(Container c2 : wordsInFileTwo) {
                if(c1.word.equals(c2.word)) {
                    System.out.println(c1.word + ": length " + c1.word.length() + ", appeared in "
                            + c1.line + "th line of " + args[0] + " and "+ c2.line + "th line in " + args[1]);
                }
            }
        }
    }
}
