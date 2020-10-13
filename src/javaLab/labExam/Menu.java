package javaLab.labExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Menu {
    static ArrayList<Item> items;
    static ArrayList<Sell> soldItems;
    static ArrayList<Return> returnItems;

    static void modifyItem(String itemCode , int offset , int modifiedValue) {
        for(Item i : items) {
            if(i.itemCode.equals(itemCode)) {
                if(offset == 0) {
                    i.price = modifiedValue;
                }
                else {
                    i.available = modifiedValue;
                }
            }
        }
    }

    static void addNewStock(User user , Item item) {
        if(user.getClass().getSimpleName().equals("Admin") && ((Admin)user).permission)
        items.add(item);
    }

    static void deleteStock(User user , String itemCode) {
        if(user.getClass().getSimpleName().equals("Admin") && ((Admin)user).permission)
        items.removeIf(i -> i.itemCode.equals(itemCode));
    }

    static void sellItems(User user , String itemCode  , int quantity , Sell sellDetails) {
        if(user.getClass().getSimpleName().equals("Admin") && ((Admin)user).permission) {
           return;
        }

        boolean found = false;
        for(Item i : items) {
            if(i.itemCode.equals(itemCode)) {
                found = true;
                if(i.available >= quantity) {
                    i.available -= quantity;
                    if(i.available == 0) {
                        //TODO fix this
                        deleteStock(user , itemCode);
                    }
                    soldItems.add(sellDetails);
                }
                else {
                    System.out.println("Quantity not sufficient");
                }
            }
        }
        if(!found) {
            System.out.println("Item not found");
        }
    }

    static int getAmount(String itemCode , int quantity) {
        for(Item i : items) {
            if(i.itemCode.equals(itemCode)) {
                return i.price * quantity;
            }
        }
        return 0;
    }

    static void returnItems(User user , String itemCode  , int quantity , Return returnDetails) {
        if(user.getClass().getSimpleName().equals("Admin") && ((Admin)user).permission) {
            return;
        }
        boolean found = false;
        for(Item i : items) {
            if(i.itemCode.equals(itemCode) && i.getClass().getSimpleName().equals("NonFoodItem")) {
                found = true;
                ((NonFoodItem) i).returnCount += quantity;
                returnItems.add(returnDetails);
            }
        }
        if(!found) {
            System.out.println("Item not found");
        }
    }

    static void displayStock(User user , String itemCode) {
        if(user.getClass().getSimpleName().equals("Admin") && ((Admin)user).permission) {
            return;
        }
        boolean found = false;
        for(Item i : items) {
            if(i.itemCode.equals(itemCode)) {
                i.displayDetails();
            }
        }
    }

    void displaySell(User user , String start , String end) {
        if(user.getClass().getSimpleName().equals("Admin") && ((Admin)user).permission) {
            return;
        }
        for(Sell i : soldItems) {
            if(i.dateOfSell.compareTo(start) <= 0 && i.dateOfSell.compareTo(end) >= 0) {
                i.displaySell();
                return;
            }
        }
        System.out.println("No sale found");
    }

    public static void main(String args[]) throws IOException {
        Item[] iArray = new Item[5];
        iArray[0] = new FoodItem("F01" , 20 , 30 , "2021-03");
        iArray[1] = new FoodItem("F02" , 30 , 20 , "2022-03");
        iArray[2] = new FoodItem("F03" , 50 , 10 , "2020-12");
        iArray[3] = new NonFoodItem("NF01" , 100 , 20 , 5);
        iArray[4] = new NonFoodItem("NF02" , 500 , 10 , 2);

        int userCount = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashSet<String> validUsers = new HashSet<>();
        validUsers.add("nikhil");
        validUsers.add("nitin");
        validUsers.add("narendra");
        validUsers.add("aman");
        while(true) {
            System.out.println("type Application Admin/General Name");
            String cmd = br.readLine();
            if(cmd.equals("exit")) {
                break;
            }
            st = new StringTokenizer(cmd);
            st.nextToken();
            String type = st.nextToken();
            String name = st.nextToken();
            if(validUsers.contains(name)) {
                User curUser;
                if(type.equals("Admin")) {
                    curUser = new Admin(userCount);
                    curUser.setDetails();
                    userCount++;
                    System.out.println("enter add/remove");
                    type = br.readLine();
                    if(type.equals("add")) {
                        addNewStock(curUser , iArray[items.size()]);
                    }
                    else if(type.equals("delete")) {
                        System.out.println("enter itemCode");
                        String code = br.readLine();
                        deleteStock(curUser , code);
                    }
                }
                else {
                    curUser = new General(userCount);
                    curUser.setDetails();
                    userCount++;
                    System.out.println(" 0 - sell item");
                    System.out.println(" 1 - return item");
                    System.out.println(" 2 - display stock");
                    System.out.println(" 3 - display sell");
                    int code = Integer.parseInt(br.readLine());
                    if(code == 0) {
                        System.out.println("enter itemCode ");
                        String itemCode = br.readLine();
                        System.out.println("enter quantity ");
                        int quant = Integer.parseInt(br.readLine());
                        Sell s = new Sell("12-11-01" , itemCode , quant , getAmount(itemCode , quant));
                        sellItems(curUser , itemCode , quant , s);
                    }
                    else if(code == 1) {
                        
                    }
                }
            }
            else {
                System.out.println("invalid name");
            }
        }

    }
}
