package javaLab.labExam;

public class Return {
    String dateOfReturn , itemCode;
    int quantity , returnAmount;
    Return(String dateOfReturn , String itemCode , int quantity , int returnAmount) {
        this.dateOfReturn = dateOfReturn;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.returnAmount = returnAmount;
    }
    void displayReturn() {
        System.out.println("date of return - " + dateOfReturn);
        System.out.println("itemCode - " + itemCode);
        System.out.println("quantity - " + quantity);
        System.out.println("return amount - " + returnAmount);
    }
}
