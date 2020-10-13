package javaLab.labExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Admin extends User{
    String dateOfJoining;
    int salary;
    boolean permission;
    Admin(int userId) {
        super(userId);
    }
    Admin(int userID, String name, String dob, String address, String pan ,
          String dateOfJoining , int salary , boolean permission) {
        super(userID, name, dob, address, pan);
        this.salary = salary;
        this.permission = permission;
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    void setDetails() throws IOException {
        super.setDetails();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter date of joining");
        this.dateOfJoining = br.readLine();
        System.out.println("enter salary");
        this.salary = Integer.parseInt(br.readLine());
    }
}
