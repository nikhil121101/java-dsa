package javaLab.lab4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public abstract class User {
    final int userID;
    String name , dateOfBirth;

    User(int userID) {
        this.userID = userID;
    }

    User(int userID , String name , String dateOfBirth) {
        this.userID = userID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User c = (User) o;
        return userID == c.userID;
    }

    abstract void displayUserType();

    void showBasicDetails() {
        System.out.print("name -> ");
        System.out.print(name + "\n");
        System.out.print("userID -> ");
        System.out.print(userID + "\n");
        System.out.print("dateOfBirth -> ");
        System.out.print(dateOfBirth + "\n");
    }

    abstract void showDetails();

    void setBasicDetails() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter name -> ");
        this.name = br.readLine();
        System.out.print("enter dateOfBirth -> ");
        this.dateOfBirth = br.readLine();
    }

    abstract void setDetails() throws IOException;

}
