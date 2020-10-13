package javaLab.labExam;

class FoodItem extends Item{
    String expiry;
    FoodItem(String itemCode, int price, int available , String expiry) {
        super(itemCode, price, available);
        this.expiry = expiry;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("expiry date - " + expiry);
        System.out.println();
    }
}
