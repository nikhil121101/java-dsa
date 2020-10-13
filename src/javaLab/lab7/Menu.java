package javaLab.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
    public static void main (String args[])
            throws IOException, OfficialDetailsException, PersonalDetailsException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("create username");
            String username = br.readLine();
            System.out.println("create password");
            String password = br.readLine();
            System.out.println("Enter email");
            String email = br.readLine();
            System.out.println("Enter first name");
            String first_name = br.readLine();
            System.out.println("Enter last name");
            String last_name = br.readLine();
            System.out.println("Enter PAN number");
            String PAN = br.readLine();
            System.out.println("Enter address PIN code");
            String address_PIN = br.readLine();
            PersonalDetails p = new PersonalDetails(username , password ,
                    email , first_name , last_name , PAN);
            OfficialDetails o = new OfficialDetails(username , password ,
                    email , 1 , address_PIN);
            validate(p, o);
            System.out.println("User is Valid");
    }

    static class PersonalDetailsException extends Exception {
        public String toString() {
            return "Invalid personal details exception";
        }
    }

    static class OfficialDetailsException extends Exception{
        public String toString() {
            return "Invalid Official details exception";
        }
    }

    private static void validate(
            PersonalDetails personalDetails , OfficialDetails officialDetails)
            throws PersonalDetailsException, OfficialDetailsException {
        if(!personalDetails.validate()) {
            throw new PersonalDetailsException();
        }
        if(!officialDetails.validate()) {
            throw new OfficialDetailsException();
        }
    }
}
