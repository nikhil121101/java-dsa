package javaLab.lab4;

import java.util.Arrays;

public class Multiply {
    public static void main(String args[]) {
        if(args.length > 0 && args[0].equals("Multiply")) {
            if(args.length > 2) {
                int prod = 1;
                for(int i = 1 ; i < args.length ; i++) {
                    prod *= Integer.parseInt(args[i]);
                }
                System.out.println(prod);
            }
            else if(args.length == 2){
                System.out.println("Error: Multiply requires at least two numbers");
            }
            else {
                System.out.println("Error: No numbers to perform multiplication");
            }
        }
    }
}
