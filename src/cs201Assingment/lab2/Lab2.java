package cs201Assingment.lab2;

import java.util.*;

class Node {
    char data;
    Node prev;
    Node next;
    Node(char data , Node prev , Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class notepad {
    Node text;
    int length;
    int cursorIndex;
    Node cursorNode;
    String clipboard;
    void print()
    {
        Node temp = text;
        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }

    void printCursor()
    {
        System.out.println(cursorIndex);
    }

    void insert(char c)
    {
        if(text == null) {
            text = new Node(c , null , null);
            cursorIndex = 1;
            cursorNode = text;
        }
        else {
            cursorNode.next = new Node(c , cursorNode , cursorNode.next);
            if(cursorNode.next.next != null)
            cursorNode.next.next.prev = cursorNode.next.next;
            cursorNode = cursorNode.next;
            cursorIndex++;
        }
        length++;
    }

    void delete(int x) {
        Node temp = null;
        if(x == 1) {
            if(cursorIndex == 0) {
                return;
            }
            else if(cursorIndex == 1) {
                temp = text;
                text = text.next;
                text.prev = null;
            }
            else {
                temp = cursorNode;
                cursorNode.prev.next = cursorNode.next;
                cursorNode = cursorNode.prev;
            }
        }
        else {
            if(cursorIndex <= 1) {
                return;
            }
            if(cursorIndex == 2) {
                temp = text;
                text = text.next;
                text.prev = null;
            }
            else {
                temp = cursorNode.prev;
                cursorNode.prev = cursorNode.prev.prev;
                cursorNode.prev.prev.next = cursorNode;
            }
        }
        temp = null;
        length--;
        cursorIndex--;
    }

    void copy(int x , int y) {
        if(x < 0 || y > length || x > y) {
            return;
        }
        Node temp = text;
        int curIndex = 0;
        while(curIndex++ <= x) {
            temp = temp.next;
        }
        String s = "";
        curIndex = x;
        while(curIndex != y) {
            s += temp.data;
            temp =temp.next;
            curIndex++;
        }
        clipboard = s;
    }
    void cut(int x , int y) {
        if(x < 0 || y > length || x > y) {
            return;
        }
        Node temp = text;
        int curIndex = 0;
        while(curIndex++ <= x) {
            temp = temp.next;
        }
        String s = "";
        curIndex = x;
        Node prev = temp.prev;
        while(curIndex != y) {
            s += temp.data;
            temp =temp.next;
            temp.prev = null;
            curIndex++;
        }
        if(temp != null) {
            temp.prev = prev;
        }
        prev.next = temp;
        cursorNode = prev;
        clipboard = s;
    }

}

class OperationList {
    class OpToken {
        String op ;
        int x;
        int y;
        char c;
        Node head;
        OpToken next;

        OpToken(String op) {
            this.op = op;
        }
    }

    OpToken head;

    void push(OpToken s) {
        s.next = head;
        head = s;
    }

    OpToken pop() {
        if(head == null)
        return null;
        OpToken temp = head;
        head = temp.next;
        return temp;
    }
}


class Notepad {
    StringBuilder text;
    String clipboard;
    int cursor;
    Stack<String> stack;

    Notepad() {
        text = new StringBuilder();
        String clipboard = "";
        cursor = 0;
        stack = new Stack<String>();
    }

    void insert(char c) {
        text.insert(cursor , c);
        cursor++;
        stack.push("insert " + c);
    }

    void seek(int x) {
        stack.push("seek " + cursor);
        if(x < text.length()) {
            cursor = x == -1 ? text.length() : x;
        }
    }

    void delete(int x) {
        stack.push("delete " + x);
        if(x == 1) {
            if(text.length() != 0) {
                text.deleteCharAt(cursor-1);
                cursor--;
            }
        }
        else {
            if(text.length() > 1) {
                text.deleteCharAt(cursor-2);
                cursor--;
            }
        }
    }

    void copy(int x , int y) {
        clipboard = text.substring(x , y + 1);
    }

    void printClipboard() {
        System.out.println("clipboard -> " + clipboard);
    }

    void cut(int x , int y) {
        stack.push("cut " + x + " " + y);
        clipboard = text.substring(x , y + 1);
        text.delete(x , y+1);
    }

    void paste(int x) {
        stack.push("paste " + x);
        text.insert(x , clipboard);
        cursor = x + clipboard.length();
    }

    void undo() {
        String prev[] = stack.pop().split(" ");
        if(prev[0].equals("insert")) {

        }
        else if(prev[0].equals("seek")) {

        }
        else if(prev[0].equals("delete")) {

        }
        else if(prev[0].equals("cut")) {

        }
        else {

        }
    }
}

public class Lab2 {
    public static void main(String args[]) {

    }
}
