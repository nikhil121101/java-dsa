package javaLab.lab7;

import java.util.Objects;

public class Details {
    String username , password , email;

    Details(String username , String password ,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    boolean validate() {
        if(username == null || password == null) {
            System.out.println("username & password cant be empty");
            return false;
        }
        if(username.length() < 6) {
            System.out.println("Please enter a larger username");
            return false;
        }
        return isValidPassword(password) && isValidEmail();
        //TODO validate email
    }

    private boolean isValidEmail() {
        String end = "@gmail.com";
        if(!email.endsWith(end)) {
            System.out.println("Invalid email address");
            return false;
        }
        return true;
    }

    boolean isValidPassword(String val) {
        boolean specialChar = false;
        boolean number = false;
        boolean size = false;
        if(val.length() >= 8) {
            size = true;
        }
        for(int i  = 0 ; i < val.length() ; i++) {
            if(isNumber(val.charAt(i))) {
                number = true;
            }
            else if(isAlphabet(val.charAt(i))){

            }
            else {
                specialChar = true;
            }
        }
        if(specialChar && size && number) {
            return true;
        }
        if(!size) {
            System.out.println(" password must have atlest 8 letters ");
        }
        System.out.println(" password must contain a number & a special char ");
        return false;
    }
    boolean isAlphabet(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }
    boolean isNumber(int c) {
        return c > 47  && c < 58;
    }
}
