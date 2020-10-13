package javaLab.labExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class General extends User{
    String dateOfJoining;
    int salary , dutyHours;
    General(int userId) {
        super(userId);
    }

    General(int userID, String name, String dob, String address, String pan ,
          String dateOfJoining , int salary , int dutyHours) {
        super(userID, name, dob, address, pan);
        this.salary = salary;
        this.dutyHours = dutyHours;
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
        System.out.println("enter hours per day");
        this.dutyHours = Integer.parseInt(br.readLine());
    }
}
