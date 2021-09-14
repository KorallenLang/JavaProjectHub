import java.io.*;
import java.util.*;
public class Knight3 {
    static int[][] board;  // declare board matrix
    public static void main (String [] args) throws IOException {
        board = new int[8][8];  // initialize w/ all zeros
        int k = 0; // row k
        int j = 2; // col j

        int[] dy = {-1, -1, 1, 1, -2, -2, 2, 2};  // possible moves in rows
        int[] dx = {-2, 2, -2, 2, -1, 1, -1, 1};  // possible moves in columns

        int step = 1;  // recording history of where object is
        move(k, j, step, dy, dx);  // calling static method

        display(board);  // display history recorded at the end
    }
    static boolean move(int k, int j, int step, int[] dy, int[] dx) {  // static = call w/o object
        board[k][j] = step;  // at row k, col j the value of step is assigned
        if (step == 64) {
            return true;
        }


        for (int i = 0; i < 8; i++) {  // 8 is the size of dy and dx arrays
            int k2 = k + dy[i];  // initial + new
            int j2 = j + dx[i];
            if (k2 < 0 || k2 >= 8 || j2 < 0 || j2 >= 8) { // out of board
                continue;
            }
            if ((k2 > 1 && k2 < 6) && (j2 > 1 && j2 < 6)) {
                if (step < 40) {
                    /*
                    The outer two rings are 48 in total steps so how to know that 40 is my largest
                    possible option?
                     */
                    continue;
                }
            }

            // next position (k2, j2) is valid
            if (board[k2][j2] > 0) { // visited
                continue;
            }


            // (k2, j2) is available
            boolean flag = move(k2, j2, step + 1, dy, dx);  // recursive call
            if (flag) { // the sequence of 64 positions is complete
                return true;
            }
        }

        // to restore the state for backtracking
        board[k][j] = 0;
        return false;  // the step was not valid so make sure not in future use
    }
    static void display(int[][] A) {
        for (int k = 0; k < A.length; k++) { // row k
            for (int j = 0; j < A[0].length; j++) { // col j
                System.out.printf("%5d", A[k][j]); // %3d : field of width 3 for int
            }
            System.out.println();
        }
    }
}


