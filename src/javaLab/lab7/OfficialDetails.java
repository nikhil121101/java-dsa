package javaLab.lab7;

public class OfficialDetails extends Details{
    int empID;
    String address_PIN;
    OfficialDetails(String username, String password, String email , int empID , String address_PIN) {
        super(username, password, email);
        this.empID = empID;
        this.address_PIN = address_PIN;
    }
    @Override
    boolean validate() {
        if(!super.validate()) {
            return false;
        }
        if(empID < 0 || address_PIN == null) {
            System.out.println("address PIN field cant be empty");
            return false;
        }
        if(address_PIN.length() != 6) {
            System.out.println("address PIN field must have exactly 6 digits");
            return false;
        }
        for(int i = 0 ; i < address_PIN.length() ; i++) {
            if(!super.isNumber(address_PIN.charAt(i))) {
                System.out.println("address PIN field must contain digits only");
                return false;
            }
        }
        return true;
    }
}
