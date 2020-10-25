package javaLab.lab8;

import java.io.*;
import java.util.*;

public class Question_two {

    static class  Student implements java.io.Serializable{
        final String firstName , lastName;
        final int rollNumber , age;
        final float cpi;

        Student(String firstName , String lastName , int rollNumber , int age , float cpi) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.rollNumber = rollNumber;
            this.age = age;
            this.cpi = cpi;
        }

        @Override
        public String toString() {
            return "firstName: " + firstName + " lastName: " + lastName +
                    " rollNo: " + rollNumber + " age: " + age + " cpi: " + cpi;
        }

        boolean isStartWithM() {
            return firstName.startsWith("M") || firstName.startsWith("m");
        }

    }

    public static void main(String[] args) throws IOException {
        // enter file path as command argument
        List<Student> studentList = new ArrayList<>();

        Student s1 = new Student("nikhil" , "mishra" , 23 , 20 , 5.4f);
        Student s2 = new Student("aman" , "jain" , 2 , 30 , 2.4f);
        Student s3 = new Student("nitin" , "arya" , 30 , 20 , 8.4f);
        Student s4 = new Student("narendra" , "gujjar" , 36 , 15 , 10.4f);
        Student s5 = new Student("manish" , "gujjar" , 3 , 19 , 0.4f);

        studentList.add(s1);studentList.add(s2);studentList.add(s3);studentList.add(s4);studentList.add(s5);

        try
        {
            FileOutputStream file = new FileOutputStream(args[0]);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(studentList);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        studentList = null;
        try
        {
            FileInputStream file = new FileInputStream(args[0]);
            ObjectInputStream in = new ObjectInputStream(file);
            studentList = (List<Student>) in.readObject();

            in.close();
            file.close();

            System.out.println("object obtained from the file - ");
            System.out.println(studentList);
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        System.out.println("student max cpi - ");
        assert studentList != null;
        System.out.println(age18(studentList).toString());

        System.out.println("student with name starting with M");
        for(Student s : studentList) {
            if(s.isStartWithM()) {
                System.out.println(s.toString());
            }
        }

    }

    static ArrayList<Student> age18(List<Student> studentList) {
        ArrayList<Student> res = new ArrayList<>();
        for(int i = 0; i < studentList.size() ; i++) {
            if(studentList.get(i).age > 18) {
                res.add(studentList.get(i));
            }
        }
        return res;
    }

}
