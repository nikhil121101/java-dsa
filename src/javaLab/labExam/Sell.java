package javaLab.labExam;

public class Sell {
    String dateOfSell , itemCode;
    int quantity , totalAmount;
    Sell(String dateOfSell , String itemCode , int quantity , int totalAmount) {
        this.dateOfSell = dateOfSell;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
    void displaySell() {
        System.out.println("date of sell - " + dateOfSell);
        System.out.println("itemCode - " + itemCode);
        System.out.println("quantity - " + quantity);
        System.out.println("total amount - " + totalAmount);
    }
}
