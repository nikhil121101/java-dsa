package javaLab.labExam;

abstract class Item {
    String itemCode;
    int price , available;
    Item(String itemCode , int price , int available) {
        this.itemCode = itemCode;
        this.price = price;
        this.available = available;
    }
    void displayDetails() {
        System.out.println("itemCode - " + itemCode);
        System.out.println("price - " + price);
        System.out.println("available quantity - " + available);
    }
}
