import javax.swing.*;
import java.io.*;
import java.util.*;

public class Matrix {

    public static void main(String[] args) {
        /*
         * int m = 3; // N_rows int n = 2; // N_cols int[][] A = new int[m][n]; //
         * creates an (m x n) matrix
         */

        int[][] A = { { 1, 2 }, { 2, 3 }, { 3, 4 } };

        // m x n 2D array
        int m = A.length; // number of rows
        int n = A[0].length; // number of columns
        System.out.println("A is " + m + "x" + n + " matrix (2D array)");

        A[0][1] = 28;
        A[1][0] = 31;
        A[2][0] = 43;
        for (int k = 0; k < m; k++) { // row k
            for (int j = 0; j < n; j++) { // col j
                System.out.printf("%3d", A[k][j]); // %3d : field of width 3 for int
            }
            System.out.println();
        }

        // to assign A with a sequence of consecutive int: start, start + 1, ..., start
        // + 5
        int start = 6;
        int d = start;
        for (int k = 0; k < m; k++) { // row k
            for (int j = 0; j < n; j++) { // col j
                A[k][j] = d;
                d++;
            }
        }
        display(A);
    }

    static void display(int[][] A) {
        for (int k = 0; k < A.length; k++) { // row k
            for (int j = 0; j < A[0].length; j++) { // col j
                System.out.printf("%3d", A[k][j]); // %3d : field of width 3 for int
            }
            System.out.println();
        }
    }

}
