package javaLab.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Menu {
    public static void main(String args[]) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<User> userList = new ArrayList<>();
        int curUsers = 0;

        while(true) {
            System.out.println("Enter mode -> 1 for admin , 2 for Non-Admin , 3 to exit");
            int mode = Integer.parseInt(br.readLine());
            if(mode == 1) {
                // admin mode
                System.out.println("1 for inserting user");
                System.out.println("2 for deleting user");
                if(br.readLine().equals("1")) {
                    System.out.println("Enter type of user -> ");
                    // student  , faculty , staff
                    String type = br.readLine();
                    if(type.equals("student")) {
                        userList.add(new Student(curUsers));
                        userList.get(curUsers).setDetails();
                    }
                    else if(type.equals("faculty")) {
                        userList.add(new Faculty(curUsers));
                        userList.get(curUsers).setDetails();;
                    }
                    else if(type.equals("staff")){
                        userList.add(new Staff(curUsers));
                        userList.get(curUsers).setDetails();;
                    }
                    else {
                        System.out.println("invalid type");
                        continue;
                    }
                    curUsers++;
                    System.out.println("user inserted successfully");
                }
                else {
                    System.out.println("Enter userId of user to delete");
                    int userID = Integer.parseInt(br.readLine());
                    userList.removeIf(user -> user.userID == userID);
                    System.out.println("user deleted successfully");
                }
            }
            else if(mode == 2){
                System.out.println("Enter userID of the user you want details of");
                int userId = Integer.parseInt(br.readLine());
                boolean found = false;
                for(User user : userList) {
                    if(user.userID == userId) {
                        found = true;
                        user.showDetails();
                    }
                }
                if(!found) {
                    System.out.println("user not exists");
                }
            }
            else {
                break;
            }
        }
    }

    static void withoutUserInput(ArrayList<User> userList) {
        int curUsers = 0;
        User f1 = new Faculty(0 , "Felix" , "13-06-1990" , "cse" , 3);
        User f2 = new Faculty(1 , "sumit" , "15-01-1993" , "ece" , 7);
        User s1 = new Staff(2 , "john" , "13-06-1990" , "finance" , "senior");
        User s2 = new Staff(3 , "noah" , "1-06-1990" , "administration" , "junior");
        User sd1 = new Student(5 , "nikhil" , "12-11-2001" , 3 , "cse" , 5 , 0);
        User sd2 = new Student(6 , "nitin" , "23-06-2001" , 4 , "ece" , 10 , 5);
        int userCount = 0;
        userList.add(f1);
        userCount++;
        userList.add(f2);
        userCount++;
        // method overloading ->
        System.out.println("method overloading");
        Faculty f = (Faculty) userList.get(0);
        System.out.println(f.isPopular(f.departmentName));
        f = (Faculty) userList.get(1);
        System.out.println(f.isPopular(f.noOfPublictions));
        System.out.println();
        // method overriding
        System.out.println("method overriding");
        userList.get(0).showDetails();
        userList.get(1).showDetails();

        userList.add(s1);
        userCount++;
        userList.add(s2);
        userCount++;
        System.out.println("before deleting\n");
        userList.get(2).showDetails();
        userList.removeIf(user -> user.userID == 2);
        System.out.println("after deleting\n");
        userList.get(2).showDetails();
        userList.add(sd1);
        curUsers++;
        userList.add(sd2);
        curUsers++;
        for(User user : userList) {
            user.showDetails();
        }

    }
}


