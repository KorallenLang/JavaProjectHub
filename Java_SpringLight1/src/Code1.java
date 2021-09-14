import java.util.*;
import java.io.*;
public class Code1 {
    public static void main(String[] args) {
        int p = 300;
        int q = 210;
        int f1 = gcd1(p, q);
        System.out.println("Algorithm 1, gcd of " + p + " and " + q + " is " + f1);
        int f2 = gcd2(p, q);
        System.out.println("Algorithm 2, gcd of " + p + " and " + q + " is " + f2);
    }

    // time O(N)
    static int gcd1(int p, int q) {
        int small = Math.min(p, q);
        int d = 1;  // returns as default; sequential search
        for(int k = 2; k <= small; k++) {
            if ((p % k == 0) && (q % k == 0)) {
                d = k;  // assign value k to d
            }
        }
        return d;  // runs after exit loop
    }

    // time O(logN), N = upper bound of random int
    static int gcd2(int n, int p) {
        int d = n % p;
        while(d > 0) {
            n = p;
            p = d;
            d = n % p;
        }
        return p;
    }
}
