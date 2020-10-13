package javaLab.labExam;

class NonFoodItem extends Item{
    int returnCount;
    NonFoodItem(String itemCode, int price, int available , int returnCount) {
        super(itemCode, price, available);
        this.returnCount = returnCount;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("return Count " + returnCount);
        System.out.println();
    }
}
