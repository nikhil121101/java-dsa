package javaLab.labExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class User {
    int userID;
    String name , dob , address , pan;
    User(int userID) {
        this.userID = userID;
    }
    User(int userID , String name , String dob , String address , String pan) {
        this.userID = userID;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.pan = pan;
    }
    void setDetails() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter name");
        this.name = br.readLine();
        System.out.println("enter dob");
        this.dob = br.readLine();
        System.out.println("enter address");
        this.address = br.readLine();
        System.out.println("enter pan");
        this.pan = br.readLine();
    }
}
