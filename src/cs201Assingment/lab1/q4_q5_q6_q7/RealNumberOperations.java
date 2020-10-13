package cs201Assingment.lab1.q4_q5_q6_q7;

import java.util.*;

public class RealNumberOperations {
    static void appendFront(LinkedList<Integer> A,
                     LinkedList<Integer> B)
    {
        int n = A.size();
        int m = B.size();
        int diff = Math.abs(n - m);

        if (n < m) {
            for (int i = 0; i < diff; i++) {
                A.addFirst(0);
            }
        }
        else {
            for (int i = 0; i < diff; i++) {
                B.addFirst(0);
            }
        }
    }

    static void appendBack(LinkedList<Integer> A,
                    LinkedList<Integer> B)
    {
        int n = A.size();
        int m = B.size();
        int diff = Math.abs(n - m);

        if (n < m) {
            for (int i = 0; i < diff; i++) {
                A.addLast(0);
            }
        }
        else {
            for (int i = 0; i < diff; i++) {
                B.addLast(0);
            }
        }
    }

    static void addReal(String s1, String s2)
    {
        LinkedList<Integer> i1 = new LinkedList<>() , i2 = new LinkedList<>();
        LinkedList<Integer> f1 = new LinkedList<>() , f2 = new LinkedList<>();
        int i;
        for (i = s1.length() - 1; i > -1; i--) {

            if (s1.charAt(i) == '.') {
                break;
            }
            f1.addLast(s1.charAt(i) - '0');
        }

        i--;
        for (; i > -1; i--) {
            i1.addLast(s1.charAt(i) - '0');
        }

        for (i = s2.length() - 1; i > -1; i--) {

            if (s2.charAt(i) == '.') {
                break;
            }
            f2.addLast(s2.charAt(i) - '0');
        }

        i--;
        for (; i > -1; i--) {
            i2.addLast(s2.charAt(i) - '0');
        }

        appendFront(f1, f2);
        appendBack(i1, i2);

        int carry = 0;
        ListIterator<Integer> itr1 = f1.listIterator();
        ListIterator<Integer> itr2 = f2.listIterator();
        while (itr1.hasNext()  && itr2.hasNext()) {
            int d1 = itr1.next() , d2 = itr2.next();
            int sum = d1
                    + d2
                    + carry;
            //System.out.println("d1 - " + d1 + " d2 - " + d2 + " sum - " + sum);
            itr1.set(sum % 10);
            carry = sum / 10;
        }

        itr1 = i1.listIterator();
        itr2 = i2.listIterator();
        while (itr1.hasNext() && itr2.hasNext()) {
            int sum = itr1.next()
                    + itr2.next()
                    + carry;
            itr1.set(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0)
            i1.addLast(carry);

        Collections.reverse(i1);
        Iterator<Integer> itr = i1.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next());
        }
        System.out.print(".");
        Collections.reverse(f1);
        itr = f1.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next());
        }
        System.out.println();
    }

    static String multiplyInteger(String s1 , String s2) {
            int n = s1.length();
            int m = s2.length();

            int result[] = new int[n + m];

            int i1 = 0;
            int i2 = 0;

            for (int i = n - 1; i >= 0; i--)
            {
                int carry = 0;
                int n1 = s1.charAt(i) - '0';
                i2 = 0;
                for (int j = m - 1; j >= 0; j--)
                {
                    int n2 = s2.charAt(j) - '0';
                    int sum = n1 * n2 + result[i1 + i2] + carry;
                    carry = sum / 10;
                    result[i1 + i2] = sum % 10;

                    i2++;
                }
                if (carry > 0)
                    result[i1 + i2] += carry;
                i1++;
            }
            int i = result.length - 1;
            while (i >= 0 && result[i] == 0)
                i--;
            if (i == -1)
                return "0";
            String s = "";

            while (i >= 0)
                s += (result[i--]);

            return s;
    }

    static String multiplyReal(String s1 , String s2) {
        int k = 0 , i;
        for(i = 0 ; i < s1.length() ; i++) {
            if(s1.charAt(i) == '.') {
                k += (s1.length() - i - 1);
                break;
            }
        }
        s1 = s1.substring(0 , i) + s1.substring(i + 1);
        for(i = 0 ; i < s2.length() ; i++) {
            if(s2.charAt(i) == '.') {
                k += (s2.length() - i - 1);
                break;
            }
        }
        s2 = s2.substring(0 , i) + s2.substring(i + 1);
        String res = multiplyInteger(s1 , s2);
        res = res.substring(0 , res.length() - k) + "." + res.substring(res.length() - k);
        return res;
    }

    public static void main(String args[]) {
        String num1 = "584506134.87368350839565308" , num2 = "30598657.0330473560587475634983";
        addReal(num1 , num2);
        String str1 = "12354214154.54545454545454544";
        String str2 = "1714546546546545454544.548544544545";
        System.out.println(multiplyReal(str1 , str2));
    }

}
