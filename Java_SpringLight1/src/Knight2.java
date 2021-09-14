import java.io.*;
import java.util.*;
public class Knight2 {
    static int[][] board;
    public static void main (String [] args) throws IOException {
		board = new int[8][8];
		int k = 0; // row k
		int j = 2; // col j

		int[] dy = {-1, -1, 1, 1, -2, -2, 2, 2};
		int[] dx = {-2, 2, -2, 2, -1, 1, -1, 1};

		int step = 1;
		move(k, j, step, dy, dx);

		display(board);
	}
    static boolean move(int k, int j, int step, int[] dy, int[] dx) {
		board[k][j] = step;
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

			// (k2, j2) is available
			boolean flag = move(k2, j2, step + 1, dy, dx);
			if (flag) { // the sequence of 64 positions is complete
				return true;
			}
		}

		// to restore the state for backtracking
		board[k][j] = 0;
		return false;
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

