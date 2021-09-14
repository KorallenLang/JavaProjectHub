import java.io.*;
import java.util.*;

public class Sudoku2 {
    int[][] puzzle;

    boolean[][] row; // initialize
    boolean[][] col;
    boolean[][][] block;

    int[] rnd_digits;  // shuffled digits

    public boolean create(int r, int c) {
        for(int y = 0; y < 9; y++) {
            int d = rnd_digits[y];
            boolean flag = check_av(r, c, d - 1);  // d - 1 for indexing purposes
            if (flag) {
                assign_used(r, c, d - 1);
                puzzle[r][c] = d;
                if (r == 8 && c == 8) {  // assign valid d at end
                    return true;  // finished
                }
                int k = r;  // row k, if need be you still have r to revert to
                int j = c + 1;  // col j, explore right neighbor
                if (j > 8) {
                    k = r  + 1;
                    j = 0;
                }  // [k, j] is valid
                boolean flag_explore = create(k, j);
                // recursive call until return true or invalid moves left
                if (flag_explore) {  // are able to create entire map successfully
                    return true;
                } else {
                    clear(r, c, d - 1);  // release current digit from position
                    puzzle[r][c] = 0;  // back to initial
                }
            }
        }
        return false;  // nowhere to go
    }

    // to assign used 2D arrays
    public void assign_used(int r, int c, int d) { // d=0, 1, 2, ..., 8 since initialization starts at 0
        row[r][d] = true;
        col[c][d] = true;
        block[r/3][c/3][d] = true;
    }

    // to release digit d from cell (r, c)
    public void clear(int r, int c, int d) {  // backtrack to original state; initialization
        row[r][d] = false;
        col[c][d] = false;
        block[r/3][c/3][d] = false;
    }

    // to check whether digit d is available for cell (r, c)
    public boolean check_av(int r, int c, int d) {
        if (row[r][d]) {
            return false;
        }
        if (col[c][d]) {
            return false;
        }
        if (block[r/3][c/3][d]) {
            return false;
        }
        return true;  // after check all three
    }

    public Sudoku2() {
        row = new boolean[9][9]; // used; [row][digit - 1] for index
        col = new boolean[9][9];
        block = new boolean[3][3][9];  // [0][0] which block, 3rd[] means digit - 1
        puzzle = new int[9][9];
        rnd_digits = new int[9];
        // shuffle and assign digits from 1 to 9 to rnd_digits[]
        List<Integer> p = new ArrayList<>();
        for(int d = 1; d <= 9; d++) {
            p.add(d);  // p = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        }
        int k = 0;  // index for rnd_digits[]
        for(int i = 1; i <= 8; i++) {
            int n = p.size();  // size is 9
            int j = (int) (Math.random() * n);  // get index from 0 through n - 1 w/ equal chance
            // Math.random() return doubles s.t. 0.0 <= x < 1.0
            // double cast to int; round down
            int d = p.remove(j);  // random position
            rnd_digits[k] = d;
            k++;
        }
        // k = 8 because we did n - 1  loops so n is left to assign
        rnd_digits[k] = p.get(0);
    }

    public void display() {
        for (int k = 0; k < 9; k++) {
            if (k % 3 == 0) {
                System.out.println(" +-----------+-----------+-----------+");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0)
                    System.out.print(" | ");
                System.out.print(" " + puzzle[k][j] + " ");
            }
            System.out.println(" | ");
        }
        System.out.println(" +-----------+-----------+-----------+");
    }

    public static void main(String[] args) throws IOException {
        Sudoku2 sudoku = new Sudoku2();
        sudoku.create(0, 0);  // recursive call
        sudoku.display();
    }
}

