package javaLab.endsemLab.serVerClient;

import javaLab.endsemLab.Question_one;
import javaLab.lab11.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class server {

    static ArrayList<Details> studentList;

    static String fileLocation = "C:\\Users\\ANSHIKA\\IdeaProjects\\java dsa\\src\\employee.ser";

    static class Details implements Serializable{
        String name;
        int rollNo;
        Details(String name , int rollNo) {
            this.name = name;
            this.rollNo = rollNo;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Details)) {
                return false;
            }
            Details d = (Details)obj;
            return this.name.equals(d.name) || this.rollNo == d.rollNo;
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000 , 3);
        System.out.println("Waiting for client to join");
        Socket connection = serverSocket.accept();
        System.out.println("Client accepted");
        System.out.println("waiting for client to send request");
        DataInputStream in = new DataInputStream(connection.getInputStream());
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        while(true) {
            int cmd = Integer.parseInt(in.readUTF());
        /*

        1 - add details
        2 - delete detail
        3 - modify detail
        4 - get by roll no
        5 - get by name
        6 - exit
         */
            if(cmd == 1) {
                String[] s = in.readUTF().split(" ");
                String name = s[0];
                int rollNo = Integer.parseInt(s[1]);
                Details details = new Details(name , rollNo);
                try {
                    FileInputStream file = new FileInputStream(fileLocation);
                    try {
                        ObjectInputStream in2 = new ObjectInputStream(file);
                        Object obj = in2.readObject();
                        studentList = (ArrayList<Details>) obj;
                    }
                    catch(java.io.EOFException exp) {
                        studentList = new ArrayList<>();
                    }
                    studentList.add(details);
                    FileOutputStream fileOut =
                            new FileOutputStream(fileLocation);
                    ObjectOutputStream out2 = new ObjectOutputStream(fileOut);
                    out2.writeObject(studentList);
                    System.out.println(studentList + " written ");
                    out.close();
                    fileOut.close();
                    // Method for deserialization of object
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            else if(cmd == 2) {
                String[] s = in.readUTF().split(" ");
                String name = s[0];
                int rollNo = Integer.parseInt(s[1]);
                Details details = new Details(name , rollNo);
                try {
                    FileInputStream file = new FileInputStream(fileLocation);
                    try {
                        ObjectInputStream in2 = new ObjectInputStream(file);
                        Object obj = in2.readObject();
                        studentList = (ArrayList<Details>) obj;
                    }
                    catch(java.io.EOFException exp) {
                        studentList = new ArrayList<>();
                    }
                    studentList.remove(details);
                    FileOutputStream fileOut =
                            new FileOutputStream(fileLocation);
                    ObjectOutputStream out2 = new ObjectOutputStream(fileOut);
                    out2.writeObject(studentList);
                    System.out.println(studentList + " written ");
                    out.close();
                    fileOut.close();
                    // Method for deserialization of object
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            else if(cmd == 3) {
                String s[] = in.readUTF().split(" ");
                Details prevDetails = new Details(s[0] , Integer.parseInt(s[1]));
                s = in.readUTF().split(" ");
                Details newDetails = new Details(s[0] , Integer.parseInt(s[1]));
                try {
                    FileInputStream file = new FileInputStream(fileLocation);
                    try {
                        ObjectInputStream in2 = new ObjectInputStream(file);
                        Object obj = in2.readObject();
                        studentList = (ArrayList<Details>) obj;
                    }
                    catch(java.io.EOFException exp) {
                        studentList = new ArrayList<>();
                    }
                    studentList.remove(prevDetails);
                    studentList.add(newDetails);
                    FileOutputStream fileOut =
                            new FileOutputStream(fileLocation);
                    ObjectOutputStream out2 = new ObjectOutputStream(fileOut);
                    out2.writeObject(studentList);
                    System.out.println(studentList + " written ");
                    out.close();
                    fileOut.close();
                    // Method for deserialization of object
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            else if(cmd == 4) {
                Details details = new Details("" , Integer.parseInt(in.readUTF()));
                try {
                    FileInputStream file = new FileInputStream(fileLocation);
                    try {
                        ObjectInputStream in2 = new ObjectInputStream(file);
                        Object obj = in2.readObject();
                        studentList = (ArrayList<Details>) obj;
                    }
                    catch(java.io.EOFException exp) {
                        studentList = new ArrayList<>();
                    }
                    boolean found = false;
                    for(Details d : studentList) {
                        if(d.equals(details)) {
                            System.out.println("reached");
                            out.writeUTF("name : " + d.name + " rollNo : " + d.rollNo);
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        out.writeUTF("user not found");
                    }
                    FileOutputStream fileOut =
                            new FileOutputStream(fileLocation);
                    ObjectOutputStream out2 = new ObjectOutputStream(fileOut);
                    out2.writeObject(studentList);
                    System.out.println(studentList + " written ");
                    out.close();
                    fileOut.close();
                    // Method for deserialization of object
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            else if(cmd == 5) {
                Details details = new Details(in.readUTF() , 0);
                try {
                    FileInputStream file = new FileInputStream(fileLocation);
                    try {
                        ObjectInputStream in2 = new ObjectInputStream(file);
                        Object obj = in2.readObject();
                        studentList = (ArrayList<Details>) obj;
                    }
                    catch(java.io.EOFException exp) {
                        studentList = new ArrayList<>();
                    }
                    boolean found = false;
                    for(Details d : studentList) {
                        if(d.equals(details)) {
                            System.out.println("reached");
                            out.writeUTF("name : " + d.name + " rollNo : " + d.rollNo);
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        out.writeUTF("user not found");
                    }
                    FileOutputStream fileOut =
                            new FileOutputStream(fileLocation);
                    ObjectOutputStream out2 = new ObjectOutputStream(fileOut);
                    out2.writeObject(studentList);
                    System.out.println(studentList + " written ");
                    out.close();
                    fileOut.close();
                    // Method for deserialization of object
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            //System.out.println("inputFileName : " + inputFileName);

            serverSocket.close();
            connection.close();
            in.close();
            out.close();
        }
    }
}
