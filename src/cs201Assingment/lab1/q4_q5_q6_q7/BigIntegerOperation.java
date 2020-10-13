package cs201Assingment.lab1.q4_q5_q6_q7;
import java.io.IOException;

public class BigIntegerOperation {

    private static class Node {
        char data;
        Node next;
        Node(char data , Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private static class MyLinkedList {
        Node head;

        void addLast(char data) {
            if(head == null) {
                head = new Node(data , null);
            }
            Node temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(data , null);
        }
    }

    static MyLinkedList makeList(String num) {
        MyLinkedList res = new MyLinkedList();
        for(int i = num.length() - 1 ; i >= 0 ; i--) {
            res.addLast(num.charAt(i));
        }
        return res;
    }

    static int dataOf(Node n) {
        return n == null ? 0 : n.data - 48;
    }

    static MyLinkedList addIntegers(MyLinkedList a , MyLinkedList b) {
        MyLinkedList res = new MyLinkedList();
        Node temp1 = a.head;
        Node temp2 = b.head;
        int carry = 0;
        while(temp1 != null || temp2 != null) {
            res.addLast((char)((dataOf(temp1)) + (dataOf(temp2)) % 10 + carry));
            carry = (dataOf(temp1) + dataOf(temp2)) / 10;
            if(temp1 != null)
            temp1 = temp1.next;
            if(temp2 != null)
            temp2 = temp2.next;
        }
        if(carry != 0) {
            res.addLast((char)carry);
        }
        return res;
    }

    static void printList(MyLinkedList list) {
        StringBuilder sb = new StringBuilder("");
        Node temp = list.head;
        while(temp != null) {
            sb.append(temp.data).append(" ");
            temp = temp.next;
        }
        sb.reverse();
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        MyLinkedList num1 = makeList("12342");
        MyLinkedList num2 = makeList("53635");
        printList(addIntegers(num1 , num2));
        //printList(addRealValues(num1 , num2));
    }

}
