import java.io.*;
import java.util.*;
public class Code3 {
    public static void main(String[] args) {
        int largest_int = Integer.MAX_VALUE;
        System.out.println("largest_int = " + largest_int); // 2.1 * 10^9, 2 is at 10th digit. 999999999

        /*
        add("149", "134");
        add("723", "1326");
        add("99999", "101");
        */
        // Fibonacci sequence : F_3 = F_2 + F_1
        int M = 2800;  // calculate F_M
        String[] Fibo1 = new String[M + 1];
        int[] Fibo2 = new int[M + 1];  // int take 4 bytes
        Fibo1[0] = "0";
        Fibo1[1] = "1";
        Fibo2[0] = 0;
        Fibo2[1] = 1;
        for (int k = 2; k <= M; k++) {
            Fibo1[k] = add(Fibo1[k - 1], Fibo1[k - 2]);
            // Fibo2[k] = Fibo2[k - 2] + Fibo2[k - 1];
        }
        for (int j = 0; j <= M; j++) {
            // System.out.println("Fibo1[" + j + "] = " + Fibo1[j] + "   Fibo2[" + j + "] = " + Fibo2[j]);
            System.out.println("Fibo1[" + j + "] has " +  Fibo1[j].length() + " digits");
        }
    }

    static String add(String s1, String s2) {
        char[] ca1 = s1.toCharArray();  // convert string to sequence of char
        char[] ca2 = s2.toCharArray();
        List<Integer> n1 = new ArrayList<>();
        List<Integer> n2 = new ArrayList<>();
        for(int k = ca1.length - 1; k >= 0; k--) {  // process unit digit first
            char y1 = ca1[k];
            n1.add(y1 - '0');  // ascii table; char - char allow us to compare using hex
        }
        for(int k = ca2.length - 1; k >= 0; k--) {
            char y2 = ca2[k];
            n2.add(y2 - '0');  // convert y2 automatically to int then char
        }
        /*
        System.out.println("s1=" + s1 + "  n1=" + n1);
        System.out.println("s2=" + s2 + "  n2=" + n2);
        for (int k = 0; k < n1.size(); k++) {  // for place, get digit
            System.out.println("1. k=" + k + " n1.get(k)=" + n1.get(k));
        }
        for (int k = 0; k < n2.size(); k++) {
            System.out.println("1. k=" + k + " n2.get(k)=" + n2.get(k));
        }
        System.out.println();
        */

        // stage 2: to add up to highest place of shorter number
        List<Integer> res = new ArrayList<>();

        int carry = 0;
        for (int k = 0; k < n1.size() && k < n2.size(); k++) {
            int d = carry + n1.get(k) + n2.get(k);
            if (d >= 10) {
                res.add(d - 10);
                carry = 1;
            } else { // d < 10
                res.add(d);
                carry = 0;
            }
        }
        /*
        System.out.println("res=" + res);
        System.out.println("  carry=" + carry);
        System.out.println();
        */

        // stage 3: to process the remaining of longer number
        List<Integer> n3 = null; // to hold the longer number
        if (n1.size() > n2.size()) {
            n3 = n1;
        } else {
            n3 = n2;
        }
        for (int k = Math.min(n1.size(), n2.size()); k < n3.size(); k++) {
            int d = carry + n3.get(k);
            if (d >= 10) {
                res.add(d - 10);
                carry = 1;
            } else { // 0 <= d < 10
                res.add(d);
                carry = 0;
            }
        }
        /*
        System.out.println("n3=" + n3);
        System.out.println("res=" + res);
        System.out.println("  carry=" + carry + "\n");
        */

        // stage 4. to process the last carry
        if (carry == 1) {
            res.add(1);
        }
        // System.out.println("res = " + res);
        StringBuilder sb = new StringBuilder("");
        for (int k = res.size() - 1; k >= 0; k--) {
            int d = res.get(k);
            char y = (char)(d + '0');  // convert to valid char by int + char or int d + int 48
            sb.append(y);
        }
        System.out.println("In add(), before return, sb = " + sb.toString());
        return sb.toString();
        // String s = "";

        // return s;
    }
}
