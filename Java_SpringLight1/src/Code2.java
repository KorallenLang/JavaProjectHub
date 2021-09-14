import java.util.*;
import java.io.*;
public class Code2 {
    public static void main(String[] args) {
        int a = 8;
        int b = 3;
		/* if (a > b) {
			System.out.println("a=" + a + " > " + b + "=b");
		} else if (a == b) {
			System.out.println("a=" + a + " == " + b + "=b");
		} else {
			System.out.println("a=" + a + " < " + b + "=b");
		}
    System.out.println("a + b = " + (a + b));
    System.out.println("a - b = " + (a - b));
    System.out.println("a * b = " + (a * b));
    System.out.println("a / b = " + (a / b)); */
        // int div round down
        // System.out.println("a % b = " + (a % b));
        /* overloading; based on class type will call different function */
    /* int a = 8; int b = 3;
    && only need one False to make false, won't evalute
    full condition if first is false; short circuit */
        if ((a % b == 1) && (b < 5)) {
            System.out.println("(a % b == 1) && (b < 5) ==> true");
        } else {
            System.out.println("(a % b == 1) && (b < 5) ==> false");
        }

        if ((a % b == 1) || (b < 5)) {
            System.out.println("(a % b == 1) || (b < 5) ==> true");
        } else {
            System.out.println("(a % b == 1) || (b < 5) ==> false");
        }

    }
}
