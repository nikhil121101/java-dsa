package javaLab.lab7;

public class PersonalDetails extends Details{
    String first_name , last_name , PAN;
    PersonalDetails(String username, String password , String email ,
                    String first_name , String last_name , String PAN) {
        super(username, password , email);
        this.first_name = first_name;
        this.last_name = last_name;
        this.PAN = PAN;
    }

    @Override
    boolean validate() {
        if(super.validate()) {
            if(first_name == null || last_name == null || PAN == null) {
                System.out.println("first_name , last_name & PAN field cant be empty");
                return false;
            }
            if(!(PAN.charAt(0) >= 'A' && PAN.charAt(0) <= 'Z')) {
                System.out.println("First character of PAN should be capital letter");
                return false;
            }
            for(int i = 0 ; i < PAN.length() ; i++) {
                if(!super.isAlphabet(PAN.charAt(i)) && !super.isNumber(PAN.charAt(i))) {
                    System.out.println("PAN must contain alpha numeric values only");
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
