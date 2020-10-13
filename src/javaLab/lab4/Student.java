package javaLab.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Student extends User{

    int semester;
    String branch;
    float cgpa;
    int projects;

    Student(int userId) {
        super(userId);
    }

    Student(int userID, String name, String dateOfBirth , int semester , String branch , float cgpa , int projects) {
        super(userID, name, dateOfBirth);
        this.semester = semester;
        this.branch = branch;
        this.cgpa = cgpa;
        this.projects = projects;
    }

    @Override
    void displayUserType() {
        System.out.println("Student");
    }

    @Override
    void showDetails() {
        super.showBasicDetails();
        System.out.print("semester -> ");
        System.out.print(semester + "\n");
        System.out.print("branch -> ");
        System.out.print(branch + "\n");
        System.out.print("cgpa -> ");
        System.out.print(cgpa + "\n");
        System.out.print("projects -> ");
        System.out.println(projects);
        System.out.println();
    }

    @Override
    void setDetails() throws IOException {
        super.setBasicDetails();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter semester -> ");
        this.semester = Integer.parseInt(br.readLine());
        System.out.println("enter branch -> ");
        this.branch = br.readLine();
        System.out.println("enter cgpa -> ");
        this.cgpa = Float.parseFloat(br.readLine());
        System.out.println("enter no of projects -> ");
        this.projects = Integer.parseInt(br.readLine());
    }

    // method overloading

    boolean isGoodStudent(float cgpa) {
        return cgpa >= 8;
    }

    boolean isGoodStudent(int projects) {
        return  projects >= 3;
    }

}
