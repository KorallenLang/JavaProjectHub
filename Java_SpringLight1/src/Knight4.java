import java.io.*;
import java.util.*;
public class Knight4 {
    static int[][] board;
    public static void main (String [] args) throws IOException {
        board = new int[8][8];
        int k = 0; // row k
        int j = 3; // col j

        int[] dy = {-1, -1, 1, 1, -2, -2, 2, 2};
        int[] dx = {-2, 2, -2, 2, -1, 1, -1, 1};

        int step = 1;
        move(k, j, step, dy, dx);

        display(board);
    }
    static int dist(int[] loc) {
        double k = (double) loc[0];
        double j = (double) loc[1];
        return (int) (2 * (Math.abs(k - 3.5) + Math.abs(j - 3.5)));
    }
    static boolean move(int k, int j, int step, int[] dy, int[] dx) {
        board[k][j] = step;
        // order set by distance from center
        TreeSet<int[]> p = new TreeSet<>((int[] y1, int[]y2) -> (dist(y2) - dist(y1)));
        // use y2 - y1 big to small priority
        if (step == 64) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int k2 = k + dy[i];
            int j2 = j + dx[i];
            if (k2 < 0 || k2 >= 8 || j2 < 0 || j2 >= 8) { // out of board
                continue;
            }
            // next position (k2, j2) is valid
            if (board[k2][j2] > 0) { // visited
                continue;
            }
            p.add(new int[]{k2, j2});  // add this move and then lambda sorts it
        }
        while (p.size() > 0) {
            int[] loc = p.pollFirst();
            // System.out.println("k2 = " + loc[0] + " j2 = " + loc[1]);
            int k2 = loc[0];
            int j2 = loc[1];
            boolean flag = move(k2, j2, step + 1, dy, dx);
            if (flag) {
                return true;  // function done
            }
        }
        board[k][j] = 0;  // original state was matrix initialization of all zeros
        return false;  // cannot complete entire sequence
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

