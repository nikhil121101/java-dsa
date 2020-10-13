package javaLab.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Staff extends User {

    String sectionName , designation;

    Staff(int userID) {
        super(userID);
    }

    Staff(int userID, String name, String dateOfBirth , String sectionName , String designation) {
        super(userID, name, dateOfBirth);
        this.sectionName = sectionName;
        this.designation = designation;
    }

    boolean isEqual(Staff staff) {
        return this.designation.equals(staff.designation);
    }

    @Override
    void displayUserType() {
        System.out.println("staff");
    }

    @Override
    void showDetails() {
        super.showBasicDetails();
        System.out.print("sectionName -> ");
        System.out.println(sectionName);
        System.out.print("designation -> ");
        System.out.println(designation);
        System.out.println();
    }

    @Override
    void setDetails() throws IOException {
        super.setBasicDetails();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter sectionName -> ");
        this.sectionName = br.readLine();
        System.out.println("enter designation -> ");
        this.designation = br.readLine();
    }

    // method overloading

    boolean isBusy(String sectionName , String designation) {
        return sectionName.equals("adminsitration") || designation.equals("senior");
    }

    boolean isBusy(String designation) {
        return designation.equals("senior");
    }

}
