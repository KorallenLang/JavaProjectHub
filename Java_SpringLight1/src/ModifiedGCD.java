import java.util.*;
import java.io.*;
public class ModifiedGCD {
    public static void main(String[] args) {
        /*
        int p = 300;
        int q = 210;
        int f1 = gcd1(p, q);
        System.out.println("Algorithm 1, gcd of " + p + " and " + q + " is " + f1);
        int f2 = gcd2(p, q);
        System.out.println("Algorithm 2, gcd of " + p + " and " + q + " is " + f2); */
        int M = 30000; // array size
        int N = 20000; // to generate random int: 1, 2, ..., N
        int[] A = new int[M];  // generate A of class 'array' w/ size M
        int[] B = new int[M];
        // Math.random(); return double x: 0 <= x < 1
        // double y = Math.random() * N : 0 <= y < N
        // (int)(Math.random() * N) : 0, 1, 2, ..., N - 1
        for (int k = 0; k < M; k++) {
            A[k] = 1 + (int) (Math.random() * N); // 1, 2, ..., N each with equal chance
            B[k] = 1 + (int) (Math.random() * N); // 1, 2, ..., N each with equal chance
        }
        int[] D1 = new int[M];
        int[] D2 = new int[M];

        long start1 = System.currentTimeMillis(); // time in ms from Jan 1, 1970
        for (int k = 0; k < M; k++) {
            D1[k] = gcd1(A[k], B[k]);  // generate M pairs using gcd1
        }
        long end1 = System.currentTimeMillis();  // runs after finish generating pairs
        long start2 = System.currentTimeMillis(); // time in ms from Jan 1, 1970
        for (int k = 0; k < M; k++) {
            int big = Math.max(A[k], B[k]);
            int small = Math.min(A[k], B[k]);
            D2[k] = gcd2(big, small); // mod algorithm
        }
        long end2 = System.currentTimeMillis();
        for (int k = 0; k < M; k++) {
            // System.out.println("k=" + k + " gcd2(" + A[k] + ", " + B[k] + ") = " + D2[k]);
            if ((A[k] % D2[k] != 0) || (B[k] % D2[k] != 0) || (D1[k] != D2[k])) {
                System.out.println("ERROR: k=" + k + " gcd1(" + A[k] + ", " + B[k] + ") = " + D1[k]);
            }
        }
        long time1 = end1 - start1;
        long time2 = end2 - start2;
        // System.out.println("time1= " + time1 + " ms   time2= " + time2 + " ms");
        System.out.println("time1= " + time1 + " ms time2= " + time2 + " ms Algorithm 2 runs " + (time1 / time2) + " times faster");


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
