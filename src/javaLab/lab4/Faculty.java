package javaLab.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Faculty extends User {
    String departmentName;
    int noOfPublictions;

    Faculty(int userID) {
        super(userID);
    }

    Faculty(int userID, String name, String dateOfBirth , String departmentName , int noOfPublictions) {
        super(userID, name, dateOfBirth);
        this.departmentName = departmentName;
        this.noOfPublictions = noOfPublictions;
    }

    @Override
    void displayUserType() {
        System.out.println("Faculty");
    }

    @Override
    void showDetails() {
        super.showBasicDetails();
        System.out.print("departmentName -> ");
        System.out.println(departmentName);
        System.out.print("noOfPublictions - > ");
        System.out.println(noOfPublictions);
        System.out.println();
    }

    @Override
    void setDetails() throws IOException {
        super.setBasicDetails();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter departmentName -> ");
        this.departmentName = br.readLine();
        System.out.println("enter no of publictions -> ");
        this.noOfPublictions = Integer.parseInt(br.readLine());
    }

    boolean isPopular(String departmentName) {
        return departmentName.equals("cse");
    }

    boolean isPopular(int noOfPublictions) {
        return noOfPublictions >= 5;
    }

}
