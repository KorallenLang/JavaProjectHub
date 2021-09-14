import java.util.*;
import java.io.*;
public class Salary {
    public static void main(String[] args) {
    /* int wage;

    Scanner sc = new Scanner(System.in);
    wage = sc.nextInt();

    System.out.print("Salary is ");
    System.out.println(wage * 40 * 52);
    end of scanner wage program */

    /* for(A,B,C){D}
    set A, check B true, execute D, check C true */
        int sum = 0;  // scope
		for (int k = 1; k <= 5; k++) {
			sum +=k;
			System.out.println("k=" + k + " sum=" + sum);
		}
		System.out.println("after for-loop, sum=" + sum);  // end program
        int n = 3;
        sum = 0;
        for (int k = 1; k <= n; k++) {
            sum += (k * k);
        }
        System.out.println("sum = " + sum);
    }
}
